package com.itany.netclass.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 验证码图片
 *
 * @author Thou
 * @date 2022/9/5
 */
@Data
public class CodeImageVO implements Serializable {

    private static final long serialVersionUID = 4609326545250653363L;
    /**
     * 加密后的验证码图片字符串
     */
    private String imageStr;
    /**
     * 验证码
     */
    private String code;
}
