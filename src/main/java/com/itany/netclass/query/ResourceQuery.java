package com.itany.netclass.query;

import com.itany.netclass.annotation.Comment;
import com.itany.netclass.entity.Resource;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 资源查询对象，封装 request 请求中资源相关的查询参数
 *
 * @author Thou
 * @date 2022/9/9
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ResourceQuery extends Resource {

    private static final long serialVersionUID = -4156500152516152328L;
    /**
     * 上传用户的用户名
     */
    @Comment("用戶名")
    private String userLoginName;
    /**
     * 创建日期开始时间
     */
    private Date createDateStart;
    /**
     * 创建日期结束时间
     */
    private Date createDateEnd;

    // 前台相关
    /**
     * 排序方式：最新 1 or 最热 2
     */
    @Comment("排序方式")
    private Integer sortType;
    /**
     * 隐藏已参加（购买）
     */
    @Comment("是否隐藏已参加课程")
    private Boolean hideLearned;
    /**
     * 登录用户编号
     */
    @Comment("登录用户编号")
    private Integer loginUserId;
}
