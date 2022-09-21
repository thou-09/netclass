package com.itany.netclass.query;

import com.itany.netclass.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 用户查询对象，封装 request 请求中用户相关的查询参数
 *
 * @author Thou
 * @date 2022/9/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQuery extends User {

    private static final long serialVersionUID = -8348766741098555640L;
    /**
     * 创建日期开始时间
     */
    private Date createDateStart;
    /**
     * 创建日期结束时间
     */
    private Date createDateEnd;
    /**
     * 登录日期开始时间
     */
    private Date signDateStart;
    /**
     * 登录日期结束时间
     */
    private Date signDateEnd;
}
