package com.itany.netclass.vo;

import com.itany.netclass.annotation.Comment;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户资源
 *
 * @author Thou
 * @date 2022/9/12
 */
@Data
public class UserResourceVO implements Serializable {

    private static final long serialVersionUID = 7885213794345407184L;
    /**
     * 资源主键
     */
    private Integer resourceId;
    /**
     * 资源标题
     */
    @Comment("资源标题")
    private String resourceTitle;
    /**
     * 资源封面图片地址
     */
    private String resourceCoverImageUrl;
    /**
     * 资源相对路径
     */
    private String resourcePath;
    /**
     * 资源原始文件名
     */
    private String resourceOriginalName;
    /**
     * 资源文件大小（MB）
     */
    private String resourceFileSize;
    /**
     * 资源文件类型（后缀）
     */
    private String resourceFileType;
    /**
     * mp4 文件总时长（单位秒，保留两位小数）
     */
    private String resourceTotalTime;
    @Comment("资源付款方式")
    private Integer resourceCostType;
    /**
     * 资源所需费用
     */
    @Comment("资源所需费用")
    private Integer resourceCostNumber;
    /**
     * 上传用户主键
     */
    private Integer userId;
}
