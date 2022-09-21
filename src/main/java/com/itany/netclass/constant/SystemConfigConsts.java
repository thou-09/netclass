package com.itany.netclass.constant;

/**
 * 系统配置相关常量
 *
 * @author Thou
 * @date 2022/8/30
 */
public interface SystemConfigConsts {

    /**
     * 过滤器默认字符集
     */
    String FILTER_CHARSET_UTF8 = "UTF-8";
    /**
     * 默认日期时间格式
     */
    String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * 默认分页大小
     */
    Integer DEFAULT_PAGE_SIZE = 5;
    /**
     * 文件上传路径
     */
    String UPLOAD_PATH = "../upload";
    /**
     * 图片转码前缀
     */
    String IMAGE_BASE64 = "data:image/jpeg;base64,";
    /**
     * MP4
     */
    String MP4_SUFFIX = "MP4";
    /**
     * PDF
     */
    String PDF_SUFFIX = "PDF";
    /**
     * 默认 pdf 文件封面图片路径
     */
    String DEFAULT_PDF_COVER_IMAGE_URL = "/upload/resource/image/default/default_pdf.png";
}
