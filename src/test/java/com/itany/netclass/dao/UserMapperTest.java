package com.itany.netclass.dao;

import com.itany.netclass.constant.RegexConsts;
import com.itany.netclass.constant.SystemConfigConsts;
import com.itany.netclass.constant.UserConsts;
import com.itany.netclass.entity.User;
import com.itany.netclass.query.UserQuery;
import com.itany.netclass.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * UserMapper Tester
 *
 * @author Thou
 * @date 2022/8/31
 */
public class UserMapperTest {

    @Test
    public void testSaveUser() {
        // SqlSession session = MyBatisUtil.getSession();
        //
        // UserMapper mapper = session.getMapper(UserMapper.class);
        // User user = new User();
        // user.setLoginName("admin");
        // user.setNickname("管理员");
        // user.setPassword("admin");
        // user.setRole(UserConsts.USER_ROLE_ADMIN);
        // user.setEmail("admin@163.com");
        // user.setCreateDate(new Date());
        // user.setStatus(UserConsts.USER_STATUS_ENABLE);
        // mapper.saveUserReturnPrimaryKey(user);
        //
        // session.commit();
        // MyBatisUtil.closeSession();

        Pattern r = Pattern.compile("^[^\\s~!@#\\$%^&*()_\\-+=<>?:\"\\{\\}|,./;'\\\\\\[\\]·~！@#￥%…&*（）—\\-+={}|《》？：“”【】、；‘'，。]{6,18}$");
        Matcher m = r.matcher("12345");
        if (m.matches()) {
            System.out.println(1);
        } else {
            System.out.println(2);
        }
    }

    @Test
    public void testSaveUsers() {
        SqlSession session = MyBatisUtil.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);

        List<User> users = new ArrayList<>();

        User user = new User();
        user.setLoginName("1234564");
        user.setNickname("赵六");
        user.setPassword("123456");
        user.setRole(UserConsts.USER_ROLE_NORMAL);
        user.setEmail("zhaoyi@163.com");
        user.setCreateDate(new Date());
        user.setStatus(UserConsts.USER_STATUS_ENABLE);
        users.add(user);

        User user1 = new User();
        user1.setLoginName("1234565");
        user1.setNickname("赵七");
        user1.setPassword("123456");
        user1.setRole(UserConsts.USER_ROLE_NORMAL);
        user1.setEmail("zhaoyi@163.com");
        user1.setCreateDate(new Date());
        user1.setStatus(UserConsts.USER_STATUS_ENABLE);
        users.add(user1);

        mapper.saveUsers(users);

        session.commit();
        MyBatisUtil.closeSession();
    }

    @Test
    public void testRemoveUserById() {
        SqlSession session = MyBatisUtil.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);
        mapper.removeUserById(3);

        session.commit();
        MyBatisUtil.closeSession();
    }

    @Test
    public void testUpdateUserById() {
        SqlSession session = MyBatisUtil.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = new User();
        user.setId(6);
        user.setSignDate(new Date());
        user.setNickname("赵一");
        user.setPassword("123456");
        // 不会修改
        user.setLoginName("123");
        user.setStatus(UserConsts.USER_STATUS_DISABLE);
        mapper.updateUserById(user);

        session.commit();
        MyBatisUtil.closeSession();
    }

    @Test
    public void testGetUserById() {
        SqlSession session = MyBatisUtil.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = mapper.getUserById(2);
        System.out.println("user = " + user);

        session.commit();
        MyBatisUtil.closeSession();
    }

    @Test
    public void testGetUserByLoginNameAndPassword() {
        SqlSession session = MyBatisUtil.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = mapper.getUserByLoginNameAndPassword("123456", "123456");
        System.out.println("user = " + user);

        session.commit();
        MyBatisUtil.closeSession();
    }

    @Test
    public void testListUsers() {
        SqlSession session = MyBatisUtil.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);
        UserQuery userQuery = new UserQuery();
        List<User> users = mapper.listUsers(userQuery);
        users.forEach(System.out::println);

        session.commit();
        MyBatisUtil.closeSession();
    }

    @Test
    public void testDemo() {
    }
}