package com.itany.netclass.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.itany.netclass.annotation.Comment;
import com.itany.netclass.constant.SystemConfigConsts;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 积分金币记录实体类
 *
 * @author Thou
 * @date 2022/8/30
 */
@Data
public class GoldPoints implements Serializable {

    private static final long serialVersionUID = 7613615459771067037L;
    /**
     * 积分金币主键
     */
    private Integer id;
    /**
     * 积分金币所属用户
     */
    private Integer userId;
    /**
     * 使用或获得的积分数
     */
    @Comment("积分数量")
    private Integer pointCount;
    /**
     * 使用或获得的金币数
     */
    @Comment("金币数量")
    private Integer goldCount;
    /**
     * 操作的内容简单说明
     */
    private String info;
    /**
     * 操作时间
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date createDate;

    /**
     * 积分金币所属用户
     */
    private User user;
}
