package com.itany.netclass.controller;

import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.netclass.constant.StatusCodeEnum;
import com.itany.netclass.constant.SystemConfigConsts;
import com.itany.netclass.entity.Chapter;
import com.itany.netclass.entity.CourseType;
import com.itany.netclass.entity.User;
import com.itany.netclass.exception.FileUploadErrorException;
import com.itany.netclass.factory.ObjectFactory;
import com.itany.netclass.query.ChapterQuery;
import com.itany.netclass.service.ChapterService;
import com.itany.netclass.util.DateUtils;
import com.itany.netclass.util.FileLoadUtils;
import com.itany.netclass.util.RequestParamUtils;
import com.itany.netclass.util.ResponseResult;
import com.itany.netclass.vo.CourseResourceVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * 课程章节 Controller
 *
 * @author Thou
 * @date 2022/9/3
 */
@RequestMapping("/chapter")
public class ChapterController {

    private final ChapterService chapterService = ObjectFactory.getObject("chapterService");

    /**
     * 根据条件分页查询指定 courseId 的课程章节信息
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/3
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

        PageInfo<Chapter> pageInfo = null;
        try {
            ChapterQuery chapterQuery = RequestParamUtils.parse(request, ChapterQuery.class,
                    SystemConfigConsts.DEFAULT_DATE_PATTERN);
            pageInfo = chapterService.listChapters(Integer.parseInt(pageNo), Integer.parseInt(pageSize), chapterQuery);
            result = ResponseResult.success(pageInfo);
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.CHAPTER_SELECT_ERROR.getCode(),
                    StatusCodeEnum.CHAPTER_SELECT_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 新增一个课程章节信息
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param multiFileList List<CommonsMultipartFile>
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/3
     */
    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(HttpServletRequest request, HttpServletResponse response, List<CommonsMultipartFile> multiFileList) {
        ResponseResult result = null;
        String realPath = request.getSession().getServletContext().getRealPath("/") + SystemConfigConsts.UPLOAD_PATH;
        if (multiFileList.isEmpty()) {
            result = ResponseResult.fail(StatusCodeEnum.CHAPTER_ADD_ERROR.getCode(),
                    StatusCodeEnum.CHAPTER_ADD_ERROR.getMessage(), "上传文件为空");
            return result;
        }
        CommonsMultipartFile file = multiFileList.get(0);
        try {
            CourseResourceVO crVO = RequestParamUtils.parse(request, CourseResourceVO.class);
            // 原文件名
            String name = file.getOriginalFilename();
            String originalName = name.substring(0, name.lastIndexOf("."));
            // 后缀
            String suffix = name.substring(name.lastIndexOf(".") + 1).toUpperCase();
            // 新文件名
            String newName = System.currentTimeMillis() + "." + suffix;
            // 格式不匹配
            if (!crVO.getResourceFileType().equals(suffix)) {
                result = ResponseResult.fail(StatusCodeEnum.CHAPTER_ADD_ERROR.getCode(),
                        StatusCodeEnum.CHAPTER_ADD_ERROR.getMessage(), "资源格式不匹配");
                return result;
            }
            // 拼接文件路径
            String filePath = new StringBuffer()
                    .append(File.separator).append("resource")
                    .append(File.separator).append("file")
                    .append(DateUtils.getYmd()).toString();
            // 文件相对路径
            String fileUrl = new StringBuffer()
                    .append(File.separator).append("upload")
                    .append(filePath).append(File.separator)
                    .append(newName).toString();
            // 创建目录
            File dir = new File(realPath + filePath);
            if (!dir.exists()) {
                boolean flag = dir.mkdirs();
                if (!flag) {
                    throw new FileUploadErrorException("文件目录创建失败");
                }
            }
            // 保存文件
            File f = new File(dir, newName);
            FileLoadUtils.saveFile(file, f);
            // 文件大小（单位 MB，保留两位小数）
            String fileSize = String.format("%.2f", (file.getSize() / 1024.0 / 1024.0));
            String lengthInTime = null;
            String coverImageUrl = null;
            // mp4 文件，设置封面，获取视频长度（单位 s，保留两位小数）
            if (SystemConfigConsts.MP4_SUFFIX.equals(suffix)) {
                lengthInTime = FileLoadUtils.getLengthInTime(f);
                coverImageUrl = FileLoadUtils.getCoverImageUrl(f, realPath);
            }
            // pdf 文件
            if (SystemConfigConsts.PDF_SUFFIX.equals(suffix)) {
                coverImageUrl = SystemConfigConsts.DEFAULT_PDF_COVER_IMAGE_URL;
            }

            crVO.setResourceOriginalName(originalName);
            crVO.setResourcePath(fileUrl);
            crVO.setResourceFileSize(fileSize);
            crVO.setResourceTotalTime(lengthInTime);
            crVO.setResourceCoverImageUrl(coverImageUrl);

            chapterService.add(crVO);
            result = ResponseResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            result = ResponseResult.fail(StatusCodeEnum.CHAPTER_ADD_ERROR.getCode(),
                    StatusCodeEnum.CHAPTER_ADD_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 修改课程章节信息
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/3
     */
    @RequestMapping("/modify")
    @ResponseBody
    public ResponseResult modify(HttpServletRequest request, HttpServletResponse response, List<CommonsMultipartFile> multiFileList) {
        ResponseResult result = null;
        String realPath = request.getSession().getServletContext().getRealPath("/") + SystemConfigConsts.UPLOAD_PATH;
        String name = null;
        String originalName = null;
        String fileUrl = null;
        String fileSize = null;
        String lengthInTime = null;
        String coverImageUrl = null;
        try {
            CourseResourceVO crVO = RequestParamUtils.parse(request, CourseResourceVO.class);
            if (!multiFileList.isEmpty()) {
                CommonsMultipartFile file = multiFileList.get(0);
                // 原文件名
                name = file.getOriginalFilename();
                originalName = name.substring(0, name.lastIndexOf("."));
                // 后缀
                String suffix = name.substring(name.lastIndexOf(".") + 1).toUpperCase();
                // 新文件名
                String newName = System.currentTimeMillis() + "." + suffix;
                // 格式不匹配
                if (!crVO.getResourceFileType().equals(suffix)) {
                    result = ResponseResult.fail(StatusCodeEnum.CHAPTER_ADD_ERROR.getCode(),
                            StatusCodeEnum.CHAPTER_ADD_ERROR.getMessage(), "资源格式不匹配");
                    return result;
                }
                // 拼接文件路径
                String filePath = new StringBuffer()
                        .append(File.separator).append("resource")
                        .append(File.separator).append("file")
                        .append(DateUtils.getYmd()).toString();
                // 文件相对路径
                fileUrl = new StringBuffer()
                        .append(File.separator).append("upload")
                        .append(filePath).append(File.separator)
                        .append(newName).toString();
                // 创建目录
                File dir = new File(realPath + filePath);
                if (!dir.exists()) {
                    boolean flag = dir.mkdirs();
                    if (!flag) {
                        throw new FileUploadErrorException("文件目录创建失败");
                    }
                }
                // 保存文件
                File f = new File(dir, newName);
                FileLoadUtils.saveFile(file, f);
                // 文件大小（单位 MB，保留两位小数）
                fileSize = String.format("%.2f", (file.getSize() / 1024.0 / 1024.0));
                // mp4 文件，设置封面，获取视频长度（单位 s，保留两位小数）
                if (SystemConfigConsts.MP4_SUFFIX.equals(suffix)) {
                    lengthInTime = FileLoadUtils.getLengthInTime(f);
                    coverImageUrl = FileLoadUtils.getCoverImageUrl(f, realPath);
                }
                // pdf 文件
                if (SystemConfigConsts.PDF_SUFFIX.equals(suffix)) {
                    coverImageUrl = SystemConfigConsts.DEFAULT_PDF_COVER_IMAGE_URL;
                }
            }

            crVO.setResourceOriginalName(originalName);
            crVO.setResourcePath(fileUrl);
            crVO.setResourceFileSize(fileSize);
            crVO.setResourceTotalTime(lengthInTime);
            crVO.setResourceCoverImageUrl(coverImageUrl);

            chapterService.modify(crVO);
            result = ResponseResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            result = ResponseResult.fail(StatusCodeEnum.CHAPTER_ADD_ERROR.getCode(),
                    StatusCodeEnum.CHAPTER_ADD_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 修改章节状态
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/12
     */
    @RequestMapping("/modify_status")
    @ResponseBody
    public ResponseResult modifyStatus(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        try {
            Chapter chapter = RequestParamUtils.parse(request, Chapter.class);
            chapterService.modifyStatus(chapter);
            result = ResponseResult.success();
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.COURSE_TYPE_MODIFY_ERROR.getCode(),
                    StatusCodeEnum.COURSE_TYPE_MODIFY_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 根据 id 查询指定课程章节信息
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/3
     */
    @RequestMapping("/get")
    @ResponseBody
    public ResponseResult get(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        try {
            String id = request.getParameter("id");
            Chapter chapter = chapterService.getChapterWithResourceById(Integer.parseInt(id));
            result = ResponseResult.success(chapter);
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.USER_SELECT_ERROR.getCode(),
                    StatusCodeEnum.USER_SELECT_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 根据 id 查询指定课程类别的相关评论
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/3
     */
    @RequestMapping("/list_comments")
    @ResponseBody
    public ResponseResult listComments(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
