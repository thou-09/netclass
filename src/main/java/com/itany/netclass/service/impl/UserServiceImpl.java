package com.itany.netclass.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.netclass.constant.GoldPointsConsts;
import com.itany.netclass.constant.RegexConsts;
import com.itany.netclass.constant.SystemConfigConsts;
import com.itany.netclass.constant.UserConsts;
import com.itany.netclass.dao.GoldPointsMapper;
import com.itany.netclass.dao.UserMapper;
import com.itany.netclass.entity.GoldPoints;
import com.itany.netclass.entity.User;
import com.itany.netclass.exception.StatusErrorException;
import com.itany.netclass.exception.UserExistException;
import com.itany.netclass.exception.UserNotFindException;
import com.itany.netclass.exception.UserPermissionException;
import com.itany.netclass.factory.ObjectFactory;
import com.itany.netclass.query.UserQuery;
import com.itany.netclass.service.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;

/**
 * 用户 ServiceImpl
 *
 * @author Thou
 * @date 2022/8/30
*/
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper = ObjectFactory.getObject("userMapper");
    private final GoldPointsMapper goldPointsMapper = ObjectFactory.getObject("goldPointsMapper");

    @Override
    public void register(User user) throws DataFormatException, UserExistException {
        // 登录名校验
        String loginName = user.getLoginName();
        Pattern rLoginName = Pattern.compile(RegexConsts.LOGIN_NAME_REGEX);
        Matcher mLoginName = rLoginName.matcher(loginName);
        if (!mLoginName.matches()) {
            throw new DataFormatException("登录名格式错误，只能是字母、数字、下划线组合，6-18位");
        }

        // 密码校验
        String password = user.getPassword();
        Pattern rPassword = Pattern.compile(RegexConsts.PASSWORD_REGEX);
        Matcher mPassword = rPassword.matcher(password);
        if (!mPassword.matches()) {
            throw new DataFormatException("密码格式错误，不能含有空格以及特殊字符，6-18位");
        }

        // 昵称校验
        String nickname = user.getNickname();
        if (nickname.length() > UserConsts.USER_NICKNAME_MAX_LENGTH) {
            throw new DataFormatException("昵称长度不可超过60个字符");
        }

        // 邮箱校验
        String email = user.getEmail();
        Pattern rEmail = Pattern.compile(RegexConsts.EMAIL_REGEX);
        Matcher mEmail = rEmail.matcher(email);
        if (!mEmail.matches()) {
            throw new DataFormatException("邮箱格式错误");
        }

        // 存在性校验
        User u1 = userMapper.getUserByLoginName(loginName);
        if (null != u1) {
            throw new UserExistException("当前登录名已被使用");
        }
        User u2 = userMapper.getUserByEmail(email);
        if (null != u2) {
            throw new UserExistException("当前邮箱已被使用");
        }

        // 设置初始值
        user.setCreateDate(new Date());
        user.setRole(UserConsts.USER_ROLE_NORMAL);
        user.setStatus(UserConsts.USER_STATUS_ENABLE);
        // 新增用戶
        userMapper.saveUserReturnPrimaryKey(user);

        // 注册奖励 50 积分
        GoldPoints goldPoints = new GoldPoints();
        goldPoints.setUserId(user.getId());
        goldPoints.setPointCount(50);
        goldPoints.setCreateDate(new Date());
        goldPoints.setInfo("新用户注册奖励50积分");
        goldPointsMapper.saveGoldPoints(goldPoints);
    }

    @Override
    public User login(User u) throws UserNotFindException, StatusErrorException, ParseException {
        User user = userMapper.getUserByLoginNameAndPassword(u.getLoginName(), u.getPassword());

        // 登录校验
        if (null == user || !UserConsts.USER_ROLE_NORMAL.equals(user.getRole())) {
            throw new UserNotFindException("用户名或密码错误");
        }

        // 状态校验
        if (UserConsts.USER_STATUS_DISABLE.equals(user.getStatus())) {
            throw new StatusErrorException("用户已被禁用");
        }

        // 今天是否签到
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String nowDate = f.format(new Date());
        Date signDate = user.getSignDate();
        if (null != signDate) {
            String sd = f.format(user.getSignDate());
            if (sd.equals(nowDate)) {
                user.setSignInToday(true);
            }
        }

        // 积分金币
        Integer gold = goldPointsMapper.sumGoldByUserId(user.getId());
        Integer point = goldPointsMapper.sumPointByUserId(user.getId());
        user.setTotalGold(gold);
        user.setTotalPoint(point);

        return user;
    }

    @Override
    public User managerLogin(User u) throws UserNotFindException, UserPermissionException,
            StatusErrorException {
        User user = userMapper.getUserByLoginNameAndPassword(u.getLoginName(), u.getPassword());

        // 登录校验
        if (null == user) {
            throw new UserNotFindException("用户名或密码错误");
        }

        // 权限校验
        if (!UserConsts.USER_ROLE_ADMIN.equals(user.getRole())) {
            throw new UserPermissionException("用户权限不足");
        }

        // 状态校验
        if (UserConsts.USER_STATUS_DISABLE.equals(user.getStatus())) {
            throw new StatusErrorException("用户已被禁用");
        }
        return user;
    }

    @Override
    public PageInfo<User> listUsers(Integer pageNo, Integer pageSize, UserQuery userQuery) {
        PageHelper.startPage(pageNo,pageSize);
        List<User> list = userMapper.listUsers(userQuery);
        return new PageInfo<>(list);
    }

    @Override
    public User getUser(Integer id) throws UserNotFindException {
        User user = userMapper.getUserById(id);
        if (null == user) {
            throw new UserNotFindException("用户不存在");
        }
        return user;
    }

    @Override
    public void modify(User user) throws DataFormatException, UserExistException {
        if (null != user.getEmail()) {
            // 邮箱校验
            Pattern r = Pattern.compile(RegexConsts.EMAIL_REGEX);
            String email = user.getEmail();
            Matcher m = r.matcher(email);
            if (!m.matches()) {
                throw new DataFormatException("邮箱格式错误");
            }
            User u = userMapper.getUserByEmail(email);
            if (!u.getId().equals(user.getId())) {
                throw new UserExistException("当前邮箱已被使用");
            }
        }
        userMapper.updateUserById(user);
    }

    @Override
    public User signIn(User u) {
        // 更新签到时间
        User user = new User();
        user.setId(u.getId());
        user.setSignDate(new Date());
        userMapper.updateUserById(user);

        // 新增签到记录
        GoldPoints goldPoints = new GoldPoints();
        goldPoints.setUserId(u.getId());
        goldPoints.setPointCount(GoldPointsConsts.SIGN_POINT);
        goldPoints.setCreateDate(new Date());
        goldPoints.setInfo("签到奖励5积分");
        goldPointsMapper.saveGoldPoints(goldPoints);

        // 设置今天已签到
        u.setSignInToday(true);

        // 更新积分
        u.setTotalPoint(u.getTotalPoint() + GoldPointsConsts.SIGN_POINT);

        return u;
    }

    @Override
    public User getGoldPoint(User user) {
        Integer gold = goldPointsMapper.sumGoldByUserId(user.getId());
        Integer point = goldPointsMapper.sumPointByUserId(user.getId());
        user.setTotalGold(gold);
        user.setTotalPoint(point);
        return user;
    }

    @Override
    public User modify4Front(User user) throws DataFormatException, UserExistException {
        // 密码校验
        String password = user.getPassword();
        if (password != null) {
            Pattern rPassword = Pattern.compile(RegexConsts.PASSWORD_REGEX);
            Matcher mPassword = rPassword.matcher(password);
            if (!mPassword.matches()) {
                throw new DataFormatException("密码格式错误，不能含有空格以及特殊字符，6-18位");
            }
        }

        // 昵称校验
        String nickname = user.getNickname();
        if (nickname != null && !"".equals(nickname)) {
            if (nickname.length() > UserConsts.USER_NICKNAME_MAX_LENGTH) {
                throw new DataFormatException("昵称长度不可超过60个字符");
            }
        }

        // 邮箱校验
        String email = user.getEmail();
        if (email != null && !"".equals(email)) {
            Pattern rEmail = Pattern.compile(RegexConsts.EMAIL_REGEX);
            Matcher mEmail = rEmail.matcher(email);
            if (!mEmail.matches()) {
                throw new DataFormatException("邮箱格式错误");
            }
            User u2 = userMapper.getUserByEmail(email);
            if (null != u2) {
                throw new UserExistException("当前邮箱已被使用");
            }
        }
        userMapper.updateUserById(user);

        User u = userMapper.getUserById(user.getId());
        // 今天是否签到
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String nowDate = f.format(new Date());
        Date signDate = u.getSignDate();
        if (null != signDate) {
            String sd = f.format(u.getSignDate());
            if (sd.equals(nowDate)) {
                u.setSignInToday(true);
            }
        }
        // 积分金币
        Integer gold = goldPointsMapper.sumGoldByUserId(u.getId());
        Integer point = goldPointsMapper.sumPointByUserId(u.getId());
        u.setTotalGold(gold);
        u.setTotalPoint(point);
        return u;
    }

    @Override
    public void forget(User user) throws UserExistException {
        User u = userMapper.getUserByLoginNameAndEmail(user.getLoginName(), user.getEmail());
        if (null == u) {
            throw new UserExistException("登录名或邮箱错误");
        }

        u.setPassword(user.getPassword());
        userMapper.updateUserById(u);
    }
}
