package com.itany.netclass.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.netclass.exception.FileUploadErrorException;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.librealsense.frame;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 文件上传工具类
 *
 * @author Thou
 * @date 2022/9/7
 */
public class FileLoadUtils {

    private static final String MP4_SUFFIX = "MP4";
    private static final String PDF_SUFFIX = "PDF";
    private static final String[] IMAGE_SUFFIX_ARRAY = new String[] {
            "JPG", "JPEG", "PNG"
    };
    private static final String BUCKET_NAME = "";
    private static final String ENDPOINT = "";
    private static final String ACCESS_KEY_ID = "";
    private static final String ACCESS_KEY_SECRET = "";

    /**
     * 上传文件
     *
     * @param file CommonsMultipartFile
     * @param uploadPath 上传地址
     * @return java.lang.String fileUrl
     * @author Thou
     * @date 2022/9/7
     */
    public static String upload(CommonsMultipartFile file, String uploadPath) throws FileUploadErrorException {
        // 获取文件的原始名称
        String name = file.getOriginalFilename();
        // 获取文件后缀
        String suffix = name.substring(name.lastIndexOf(".") + 1).toUpperCase();
        // 文件生成新的文件名
        String newName = System.currentTimeMillis() + "." + suffix;
        List<String> imgSuffix = Arrays.asList(IMAGE_SUFFIX_ARRAY);
        // 拼接路径
        StringBuffer bf = new StringBuffer();
        if (MP4_SUFFIX.equals(suffix)) {
            // mp4
            bf.append(File.separator).append("video");
        } else if (PDF_SUFFIX.equals(suffix)) {
            // pdf
            bf.append(File.separator).append("pdf");
        } else if (imgSuffix.contains(suffix)) {
            // 图片
            bf.append(File.separator).append("image");
        } else {
            // 其他
            bf.append(File.separator).append("other");
        }
        // 根据日期划分路径
        Calendar c = Calendar.getInstance();
        Date d = new Date();
        c.setTime(d);
        bf.append(File.separator)
                .append(c.get(Calendar.YEAR))
                .append(File.separator)
                .append((c.get(Calendar.MONTH) + 1 < 10) ? "0" + (c.get(Calendar.MONTH) + 1) : c.get(Calendar.MONTH) + 1)
                .append(File.separator)
                .append((c.get(Calendar.DAY_OF_MONTH) < 10) ? "0" + c.get(Calendar.DAY_OF_MONTH) : c.get(Calendar.DAY_OF_MONTH));
        String filePath = bf.toString();
        String fileUrl = File.separator + "upload" + filePath + File.separator + newName;
        // 创建目录
        File dir = new File(uploadPath + filePath);
        if (!dir.exists()) {
            boolean flag = dir.mkdirs();
            if (!flag) {
                throw new FileUploadErrorException("文件目录创建失败");
            }
        }
        // 生成新文件
        File f = new File(dir, newName);
        // 保存文件到本地
        FileOutputStream output = null;
        InputStream input = null;
        try {
            // 获取文件 IO 流对象
            input = file.getInputStream();
            output = new FileOutputStream(f);
            // 读写
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = input.read(b)) != -1) {
                output.write(b, 0, len);
            }
            output.flush();
        } catch (Exception e) {
            throw new FileUploadErrorException("文件上传失败", e);
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileUrl;
    }

    /**
     * 上传文件到阿里云 oss
     *
     * @param file CommonsMultipartFile
     * @param request HttpServletRequest
     * @return java.lang.String
     * @author Thou
     * @date 2022/9/8
     */
    public static String uploadAliyunOss(CommonsMultipartFile file, HttpServletRequest request) throws FileUploadErrorException {
        String uploadUrl = null;
        try {
            // 创建 OSSClient
            OSS ossClient = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
            // 获得上传文件流
            InputStream inputStream = file.getInputStream();
            // 构建文件上传路径
            String contextPath = request.getContextPath();
            contextPath = contextPath.substring(1);
            String fileUrl = contextPath + createFileUrl(file);
            fileUrl = fileUrl.replace("\\", "/");
            // 上传文件
            ossClient.putObject(BUCKET_NAME, fileUrl, inputStream);
            // 关闭 OSSClient
            ossClient.shutdown();
            // 获取访问 url 地址
            uploadUrl = "https://" + BUCKET_NAME + "."  + ENDPOINT + "/" + fileUrl;
        } catch (Exception e) {
            throw new FileUploadErrorException("文件上传失败");
        }
        return uploadUrl;
    }

    /**
     * 构建文件路径
     *
     * @param file CommonsMultipartFile
     * @return java.lang.String
     * @author Thou
     * @date 2022/9/8
     */
    private static String createFileUrl(CommonsMultipartFile file) {
        // 获取文件的原始名称
        String name = file.getOriginalFilename();
        // 获取文件后缀
        String suffix = name.substring(name.lastIndexOf(".") + 1).toUpperCase();
        // 文件生成新的文件名
        String newName = System.currentTimeMillis() + "." + suffix;
        List<String> imgSuffix = Arrays.asList(IMAGE_SUFFIX_ARRAY);
        // 拼接路径
        StringBuffer bf = new StringBuffer();
        if (MP4_SUFFIX.equals(suffix)) {
            // mp4
            bf.append(File.separator).append("video");
        } else if (PDF_SUFFIX.equals(suffix)) {
            // pdf
            bf.append(File.separator).append("pdf");
        } else if (imgSuffix.contains(suffix)) {
            // 图片
            bf.append(File.separator).append("image");
        } else {
            // 其他
            bf.append(File.separator).append("other");
        }
        // 根据日期划分路径
        Calendar c = Calendar.getInstance();
        Date d = new Date();
        c.setTime(d);
        bf.append(File.separator)
                .append(c.get(Calendar.YEAR))
                .append(File.separator)
                .append((c.get(Calendar.MONTH) + 1 < 10) ? "0" + (c.get(Calendar.MONTH) + 1) : c.get(Calendar.MONTH) + 1)
                .append(File.separator)
                .append((c.get(Calendar.DAY_OF_MONTH) < 10) ? "0" + c.get(Calendar.DAY_OF_MONTH) : c.get(Calendar.DAY_OF_MONTH));
        String filePath = bf.toString();
        return File.separator + "upload" + filePath + File.separator + newName;
    }

    /**
     * 保存文件到本地
     *
     * @param file CommonsMultipartFile
     * @param f 处理 CommonsMultipartFile 后得到的 File
     * @author Thou
     * @date 2022/9/9
     */
    public static void saveFile(CommonsMultipartFile file, File f) throws FileUploadErrorException {
        // 保存文件到本地
        FileOutputStream output = null;
        InputStream input = null;
        try {
            // 获取文件 IO 流对象
            input = file.getInputStream();
            output = new FileOutputStream(f);
            // 读写
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = input.read(b)) != -1) {
                output.write(b, 0, len);
            }
            output.flush();
        } catch (Exception e) {
            throw new FileUploadErrorException("文件上传失败", e);
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获得视频文件总时长
     *
     * @param file 视频文件
     * @return java.lang.String
     * @author Thou
     * @date 2022/9/9
     */
    public static String getLengthInTime(File file) throws FileUploadErrorException {
        FFmpegFrameGrabber grabber = null;
        String lengthInTime = null;
        try {
            grabber = new FFmpegFrameGrabber(file);
            grabber.start();
            double len = grabber.getLengthInTime() / 1000000.00;
            lengthInTime = String.format("%.2f", len);
        } catch (Exception e) {
            throw new FileUploadErrorException("文件上传失败", e);
        } finally {
            if (grabber != null) {
                try {
                    grabber.stop();
                    grabber.release();
                } catch (FrameGrabber.Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return lengthInTime;
    }

    /**
     * 截取视频文件第一帧作为封面图片，保存图片并返回图片相对地址
     *
     * @param file 视频文件
     * @param uploadPath 上传地址
     * @return java.lang.String
     * @author Thou
     * @date 2022/9/9
     */
    public static String getCoverImageUrl(File file, String uploadPath) throws FileUploadErrorException {
        FFmpegFrameGrabber grabber = null;
        String coverImageUrl = null;
        try {
            grabber = new FFmpegFrameGrabber(file);
            grabber.start();
            grabber.setTimestamp(100_0000L);
            Frame f = grabber.grabImage();
            Java2DFrameConverter converter = new Java2DFrameConverter();
            BufferedImage bi = converter.getBufferedImage(f);
            String imagePath = new StringBuffer()
                    .append(File.separator).append("resource")
                    .append(File.separator).append("image")
                    .append(DateUtils.getYmd()).toString();
            String newName = System.currentTimeMillis() + ".JPG";
            coverImageUrl = new StringBuffer()
                    .append(File.separator).append("upload")
                    .append(imagePath).append(File.separator)
                    .append(newName).toString();
            File dir = new File(uploadPath + imagePath);
            if (!dir.exists()) {
                boolean flag = dir.mkdirs();
                if (!flag) {
                    throw new FileUploadErrorException("文件目录创建失败");
                }
            }
            ImageIO.write(bi, "jpg", new File(dir, newName));
        } catch (Exception e) {
            throw new FileUploadErrorException("文件上传失败", e);
        } finally {
            if (grabber != null) {
                try {
                    grabber.stop();
                    grabber.release();
                } catch (FrameGrabber.Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return coverImageUrl;
    }
}
