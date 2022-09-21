package com.itany.netclass.controller;

import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.netclass.constant.StatusCodeEnum;
import com.itany.netclass.constant.SystemConfigConsts;
import com.itany.netclass.entity.User;
import com.itany.netclass.factory.ObjectFactory;
import com.itany.netclass.query.UserQuery;
import com.itany.netclass.service.UserService;
import com.itany.netclass.util.CommonUtil;
import com.itany.netclass.util.RequestParamUtils;
import com.itany.netclass.util.ResponseResult;
import com.itany.netclass.util.CommonUtil.MyImage;
import com.itany.netclass.vo.CodeImageVO;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Encoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;

/**
 * 用户 Controller
 *
 * @author Thou
 * @date 2022/9/1
 */
@RequestMapping("/user")
public class UserController {

    private final UserService userService = ObjectFactory.getObject("userService");

    /**
     * 注册
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author Thou
     * @date 2022/9/1
     */
    @RequestMapping("/register")
    @ResponseBody
    public ResponseResult register(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        String repassword = request.getParameter("rePassword");
        try {
            User user = RequestParamUtils.parse(request, User.class);
            if (!user.getPassword().equals(repassword)) {
                result = ResponseResult.fail(StatusCodeEnum.USER_REGISTER_ERROR.getCode(),
                        StatusCodeEnum.USER_REGISTER_ERROR.getMessage(), "两次输入密码不相等");
                return result;
            }
            userService.register(user);
            result = ResponseResult.success();
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.USER_REGISTER_ERROR.getCode(),
                    StatusCodeEnum.USER_REGISTER_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 登录
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/1
     */
    @RequestMapping("/login")
    @ResponseBody
    public ResponseResult login(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        String autoLogin = request.getParameter("autoLogin");
        try {
            User u = RequestParamUtils.parse(request, User.class);
            User user = userService.login(u);
            result = ResponseResult.success(user);

            // 自动登录
            if (autoLogin != null && !"".equals(autoLogin)) {
                Cookie username = new Cookie("USERNAME", user.getLoginName());
                username.setPath("/");
                username.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(username);
                Cookie password = new Cookie("PASSWORD", user.getPassword());
                password.setPath("/");
                password.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(password);
            }

            request.getSession().setAttribute("loginUser", user);
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.USER_LOGIN_ERROR.getCode(),
                    StatusCodeEnum.USER_LOGIN_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 管理员登录
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return java.lang.String
     * @author Thou
     * @date 2022/9/3
     */
    @RequestMapping("/admin_login")
    @ResponseBody
    public ResponseResult managerLogin(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        // 验证码
        String code = request.getParameter("code");
        if (StringUtils.isEmpty(code.trim())) {
            result = ResponseResult.fail(StatusCodeEnum.MANAGER_LOGIN_ERROR.getCode(),
                    StatusCodeEnum.MANAGER_LOGIN_ERROR.getMessage(), "验证码为空");
            return result;
        }
        String codeAnswer = request.getParameter("codeAnswer");
        if (!code.equals(codeAnswer)) {
            result = ResponseResult.fail(StatusCodeEnum.MANAGER_LOGIN_ERROR.getCode(),
                    StatusCodeEnum.MANAGER_LOGIN_ERROR.getMessage(), "验证码错误");
            return result;
        }

        try {
            User u = RequestParamUtils.parse(request, User.class);
            User user = userService.managerLogin(u);
            result = ResponseResult.success();

            request.getSession().setAttribute("loginAdmin", user);
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.MANAGER_LOGIN_ERROR.getCode(),
                    StatusCodeEnum.MANAGER_LOGIN_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 根据条件分页查询
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return java.lang.String
     * @author Thou
     * @date 2022/9/1
     */
    @RequestMapping("/list")
    @ResponseBody
    public ResponseResult list(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        String pageNo = request.getParameter("pageNo");
        if (null == pageNo) {
            pageNo = "1";
        }
        String pageSize = request.getParameter("pageSize");
        if (null == pageSize) {
            pageSize = SystemConfigConsts.DEFAULT_PAGE_SIZE + "";
        }

        PageInfo<User> pageInfo = null;
        try {
            UserQuery userQuery = RequestParamUtils.parse(request, UserQuery.class,
                    SystemConfigConsts.DEFAULT_DATE_PATTERN);
            pageInfo = userService.listUsers(Integer.parseInt(pageNo), Integer.parseInt(pageSize), userQuery);
            result = ResponseResult.success(pageInfo);
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.USER_SELECT_ERROR.getCode(),
                    StatusCodeEnum.USER_SELECT_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 根据 id 查询用户信息
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/10
     */
    @RequestMapping("/get")
    @ResponseBody
    public ResponseResult getOne(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        try {
            String id = request.getParameter("id");
            User user = userService.getUser(Integer.parseInt(id));
            result = ResponseResult.success(user);
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.USER_SELECT_ERROR.getCode(),
                    StatusCodeEnum.USER_SELECT_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 修改用户信息
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/3
     */
    @RequestMapping("/modify")
    @ResponseBody
    public ResponseResult modify(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        try {
            User user = RequestParamUtils.parse(request, User.class);
            userService.modify(user);
            result = ResponseResult.success();
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.USER_MODIFY_ERROR.getCode(),
                    StatusCodeEnum.USER_MODIFY_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 修改用户信息
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/13
     */
    @RequestMapping("/modify4_front")
    @ResponseBody
    public ResponseResult modify4Front(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        User user = new User();
        String id = request.getParameter("id");
        user.setId(Integer.parseInt(id));

        String password = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");
        String rePassword = request.getParameter("rePassword");
        if (!CommonUtil.isEmpty(password) || !CommonUtil.isEmpty(newPassword) || !CommonUtil.isEmpty(rePassword)) {
            if (CommonUtil.isEmpty(password)) {
                result = ResponseResult.fail(StatusCodeEnum.USER_MODIFY_ERROR.getCode(),
                        StatusCodeEnum.USER_MODIFY_ERROR.getMessage(), "旧密码不能为空");
                return result;
            }
            if (CommonUtil.isEmpty(newPassword)) {
                result = ResponseResult.fail(StatusCodeEnum.USER_MODIFY_ERROR.getCode(),
                        StatusCodeEnum.USER_MODIFY_ERROR.getMessage(), "新密码不能为空");
                return result;
            }
            if (CommonUtil.isEmpty(rePassword)) {
                result = ResponseResult.fail(StatusCodeEnum.USER_MODIFY_ERROR.getCode(),
                        StatusCodeEnum.USER_MODIFY_ERROR.getMessage(), "确认密码不能为空");
                return result;
            }
            if (!password.equals(rePassword)) {
                result = ResponseResult.fail(StatusCodeEnum.USER_MODIFY_ERROR.getCode(),
                        StatusCodeEnum.USER_MODIFY_ERROR.getMessage(), "旧密码与确认密码不一致");
                return result;
            }
            user.setPassword(newPassword);
        }

        user.setNickname(request.getParameter("nickname"));
        user.setEmail(request.getParameter("email"));
        try {
            User u = userService.modify4Front(user);
            request.getSession().setAttribute("loginUser", u);
            result = ResponseResult.success();
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.USER_MODIFY_ERROR.getCode(),
                    StatusCodeEnum.USER_MODIFY_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 生成验证码图片
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/5
     */
    @RequestMapping("/code_image")
    @ResponseBody
    public ResponseResult getCodeImage(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        try {
            MyImage image = CommonUtil.getImage(null, 4, false, false);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(out);
            jpegEncoder.encode(image.getImage());
            BASE64Encoder encoder = new BASE64Encoder();
            String imgStr = encoder.encode(out.toByteArray());

            CodeImageVO codeImageVO = new CodeImageVO();
            codeImageVO.setImageStr(SystemConfigConsts.IMAGE_BASE64 + imgStr);
            codeImageVO.setCode(image.getCode());
            result = ResponseResult.success(codeImageVO);
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.CODE_IMAGE_ERROR.getCode(),
                    StatusCodeEnum.CODE_IMAGE_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 管理员退出登录
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/9
     */
    @RequestMapping("/admin_logout")
    @ResponseBody
    public ResponseResult logout(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        HttpSession session = request.getSession();
        User loginAdmin = (User)session.getAttribute("loginAdmin");
        if (null != loginAdmin) {
            session.removeAttribute("loginAdmin");
            result = ResponseResult.success();
        }
        return result;
    }

    /**
     * 管理员退出登录
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/9
     */
    @RequestMapping("/user_logout")
    @ResponseBody
    public ResponseResult logoutUser(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        HttpSession session = request.getSession();
        User loginUser = (User)session.getAttribute("loginUser");
        if (null != loginUser) {
            session.removeAttribute("loginUser");
            result = ResponseResult.success();
        }
        Cookie username = new Cookie("USERNAME", "");
        username.setPath("/");
        username.setMaxAge(0);
        response.addCookie(username);
        Cookie password = new Cookie("PASSWORD", "");
        password.setPath("/");
        password.setMaxAge(0);
        response.addCookie(password);

        return result;
    }

    /**
     * 签到
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/10
     */
    @RequestMapping("/sign_in")
    @ResponseBody
    public ResponseResult signIn(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (null == loginUser) {
            result = ResponseResult.fail(StatusCodeEnum.USER_SIGN_IN_ERROR.getCode(),
                    StatusCodeEnum.USER_SIGN_IN_ERROR.getMessage(), "未登录");
            return result;
        }
        try {
            User user = userService.signIn(loginUser);
            request.getSession().setAttribute("loginUser", user);
            result = ResponseResult.success();
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.USER_SIGN_IN_ERROR.getCode(),
                    StatusCodeEnum.USER_SIGN_IN_ERROR.getMessage(), e.getMessage());
            return result;
        }
        return result;
    }

    /**
     * 查询用户金币和积分
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/13
     */
    @RequestMapping("/get_gold_point")
    @ResponseBody
    public ResponseResult getGoldPoint(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (null == loginUser) {
            result = ResponseResult.fail(StatusCodeEnum.USER_SIGN_IN_ERROR.getCode(),
                    StatusCodeEnum.USER_SIGN_IN_ERROR.getMessage(), "未登录");
            return result;
        }
        try {
            User u = userService.getGoldPoint(loginUser);
            request.getSession().setAttribute("loginUser", u);
            result = ResponseResult.success();
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.USER_SIGN_IN_ERROR.getCode(),
                    StatusCodeEnum.USER_SIGN_IN_ERROR.getMessage(), e.getMessage());
            return result;
        }
        return result;
    }

    /**
     * 忘记密码
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/13
     */
    @RequestMapping("/forget")
    @ResponseBody
    public ResponseResult forget(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        try {
            User user = RequestParamUtils.parse(request, User.class);
            String rePassword = request.getParameter("rePassword");
            if (CommonUtil.isEmpty(rePassword)) {
                result = ResponseResult.fail(StatusCodeEnum.USER_MODIFY_ERROR.getCode(),
                        StatusCodeEnum.USER_MODIFY_ERROR.getMessage(), "确认密码为空");
                return result;
            }
            if (!user.getPassword().equals(rePassword)) {
                result = ResponseResult.fail(StatusCodeEnum.USER_MODIFY_ERROR.getCode(),
                        StatusCodeEnum.USER_MODIFY_ERROR.getMessage(), "两次密码输入不一致");
                return result;
            }
            userService.forget(user);
            result = ResponseResult.success();
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.USER_MODIFY_ERROR.getCode(),
                    StatusCodeEnum.USER_MODIFY_ERROR.getMessage(), e.getMessage());
            return result;
        }
        return result;
    }
}
