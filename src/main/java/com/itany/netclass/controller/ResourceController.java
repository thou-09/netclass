package com.itany.netclass.controller;

import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.netclass.constant.StatusCodeEnum;
import com.itany.netclass.constant.SystemConfigConsts;
import com.itany.netclass.entity.Course;
import com.itany.netclass.entity.Resource;
import com.itany.netclass.entity.User;
import com.itany.netclass.entity.UserResource;
import com.itany.netclass.exception.FileUploadErrorException;
import com.itany.netclass.exception.ResourceExistException;
import com.itany.netclass.factory.ObjectFactory;
import com.itany.netclass.query.ResourceQuery;
import com.itany.netclass.service.ResourceService;
import com.itany.netclass.util.DateUtils;
import com.itany.netclass.util.FileLoadUtils;
import com.itany.netclass.util.RequestParamUtils;
import com.itany.netclass.util.ResponseResult;
import com.itany.netclass.vo.CourseResourceVO;
import com.itany.netclass.vo.UserResourceVO;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 资源 Controller
 *
 * @author Thou
 * @date 2022/9/9
 */
@RequestMapping("/resource")
public class ResourceController {

    private final ResourceService resourceService = ObjectFactory.getObject("resourceService");

    /**
     * 根据 chapterId 获取指定的资源<br>
     * mp4 文件跳转页面播放
     * pdf 文件以流的方式下载
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return java.lang.String
     * @author Thou
     * @date 2022/9/9
     */
    @RequestMapping("/search")
    public String get(HttpServletRequest request, HttpServletResponse response) {
        String realPath = request.getSession().getServletContext().getRealPath("/") + SystemConfigConsts.UPLOAD_PATH;
        Resource resource = null;

        String id = request.getParameter("id");
        if (StringUtils.isEmpty(id.trim())) {
            return "";
        }
        try {
            resource = resourceService.getById(Integer.parseInt(id));
        } catch (ResourceExistException e) {
            e.printStackTrace();
            return "";
        }

        if (null == resource) {
            return "";
        }

        String fileType = resource.getFileType();
        // mp4
        if (SystemConfigConsts.MP4_SUFFIX.equals(fileType)) {
            String path = resource.getPath();
            path = path.replaceAll("\\\\", "/");
            request.setAttribute("path", path);
            String coverImageUrl = resource.getCoverImageUrl();
            coverImageUrl = coverImageUrl.replaceAll("\\\\", "/");
            request.setAttribute("coverImageUrl", coverImageUrl);
            return "back_resourceDetailSet";
        }

        // pdf
        if (SystemConfigConsts.PDF_SUFFIX.equals(fileType)) {
            String path = resource.getPath();
            path = path.replaceAll("\\\\", "/");
            path = path.substring(path.indexOf("/upload") + "/upload".length());
            String filePath = realPath + path;
            String filename = resource.getOriginalName();

            // 设置 response Header
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" +
                            new String(filename.getBytes(StandardCharsets.UTF_8),
                                    StandardCharsets.ISO_8859_1) + '.' + fileType);

            // 需要下载的文件
            File f = new File(filePath);
            InputStream fis = null;
            ServletOutputStream out = null;
            try {
                // 以流的方式下载
                fis = new BufferedInputStream(new FileInputStream(f));
                // 获得 ServletOutputStream
                out = response.getOutputStream();
                int b = 0;
                byte[] bf = new byte[1024];
                while ((b = fis.read(bf)) != -1) {
                    out.write(bf, 0, b);
                }
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            } finally {
                try {
                    if (null != fis) {
                        fis.close();
                    }
                    if (null != out) {
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    /**
     * 根据 chapterId 获取指定资源（前台使用）
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return java.lang.String
     * @author Thou
     * @date 2022/9/12
     */
    @RequestMapping("/search_front")
    public String search4Front(HttpServletRequest request, HttpServletResponse response) {
        String realPath = request.getSession().getServletContext().getRealPath("/") + SystemConfigConsts.UPLOAD_PATH;
        Resource resource = null;

        String id = request.getParameter("id");
        if (StringUtils.isEmpty(id.trim())) {
            return "";
        }
        String see = request.getParameter("see");
        if (null == see || "".equals(see)) {
            see = "0.00";
        }

        try {
            resource = resourceService.search4Front(Integer.parseInt(id));
            request.setAttribute("resource", resource);
        } catch (ResourceExistException e) {
            e.printStackTrace();
            return "";
        }

        if (null == resource) {
            return "";
        }

        String fileType = resource.getFileType();
        // mp4
        if (SystemConfigConsts.MP4_SUFFIX.equals(fileType)) {
            String path = resource.getPath();
            path = path.replaceAll("\\\\", "/");
            request.setAttribute("path", path);
            String coverImageUrl = resource.getCoverImageUrl();
            coverImageUrl = coverImageUrl.replaceAll("\\\\", "/");
            request.setAttribute("coverImageUrl", coverImageUrl);
            request.setAttribute("see", see);
            return "front_courseDetail";
        }

        // pdf
        if (SystemConfigConsts.PDF_SUFFIX.equals(fileType)) {
            String path = resource.getPath();
            path = path.replaceAll("\\\\", "/");
            path = path.substring(path.indexOf("/upload") + "/upload".length());
            String filePath = realPath + path;
            String filename = resource.getOriginalName();

            // 设置 response Header
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" +
                            new String(filename.getBytes(StandardCharsets.UTF_8),
                                    StandardCharsets.ISO_8859_1) + '.' + fileType);

            // 需要下载的文件
            File f = new File(filePath);
            InputStream fis = null;
            ServletOutputStream out = null;
            try {
                // 以流的方式下载
                fis = new BufferedInputStream(new FileInputStream(f));
                // 获得 ServletOutputStream
                out = response.getOutputStream();
                int b = 0;
                byte[] bf = new byte[1024];
                while ((b = fis.read(bf)) != -1) {
                    out.write(bf, 0, b);
                }
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            } finally {
                try {
                    if (null != fis) {
                        fis.close();
                    }
                    if (null != out) {
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    /**
     * 更新 userResource 信息
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/13
     */
    @RequestMapping("/update_ur")
    @ResponseBody
    public ResponseResult updateUserResource(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        String seeTime = request.getParameter("seeTime");
        if (StringUtils.isEmpty(seeTime) || StringUtils.isBlank(seeTime)) {
            seeTime = "0.00";
        }
        String userId = request.getParameter("userId");
        if (StringUtils.isEmpty(userId) || StringUtils.isBlank(userId)) {
            result = ResponseResult.fail(StatusCodeEnum.RESOURCE_SELECT_ERROR.getCode(),
                    StatusCodeEnum.RESOURCE_SELECT_ERROR.getMessage(), "用户编号为空");
            return result;
        }
        String resourceId = request.getParameter("resourceId");
        if (StringUtils.isEmpty(resourceId) || StringUtils.isBlank(resourceId)) {
            result = ResponseResult.fail(StatusCodeEnum.RESOURCE_SELECT_ERROR.getCode(),
                    StatusCodeEnum.RESOURCE_SELECT_ERROR.getMessage(), "资源编号为空");
            return result;
        }

        try {
            resourceService.updateUserResource(Integer.parseInt(userId), Integer.parseInt(resourceId), seeTime);
            result = ResponseResult.success();
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.RESOURCE_SELECT_ERROR.getCode(),
                    StatusCodeEnum.RESOURCE_SELECT_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 检查用户是否已购买了资源
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/12
     */
    @RequestMapping("/owned")
    @ResponseBody
    public ResponseResult ownedResource(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        String resourceId = request.getParameter("resourceId");
        if (StringUtils.isEmpty(resourceId) || StringUtils.isBlank(resourceId)) {
            result = ResponseResult.fail(StatusCodeEnum.RESOURCE_SELECT_ERROR.getCode(),
                    StatusCodeEnum.RESOURCE_SELECT_ERROR.getMessage(), "资源编号为空");
            return result;
        }
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        try {
            Boolean owned = resourceService.checkOwned(loginUser.getId(), Integer.parseInt(resourceId));
            result = ResponseResult.success(owned);
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.RESOURCE_SELECT_ERROR.getCode(),
                    StatusCodeEnum.RESOURCE_SELECT_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 购买资源
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/13
     */
    @RequestMapping("/purchase")
    @ResponseBody
    public ResponseResult purchase(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        String resourceId = request.getParameter("resourceId");
        if (StringUtils.isEmpty(resourceId) || StringUtils.isBlank(resourceId)) {
            result = ResponseResult.fail(StatusCodeEnum.RESOURCE_SELECT_ERROR.getCode(),
                    StatusCodeEnum.RESOURCE_SELECT_ERROR.getMessage(), "资源编号为空");
            return result;
        }
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        try {
            User u = resourceService.purchase(loginUser, Integer.parseInt(resourceId));
            request.getSession().setAttribute("loginUser", u);
            result = ResponseResult.success();
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.RESOURCE_SELECT_ERROR.getCode(),
                    StatusCodeEnum.RESOURCE_SELECT_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 根据条件分页查询资源
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/9
     */
    @RequestMapping("/list_with_user")
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

        PageInfo<Resource> pageInfo = null;
        try {
            ResourceQuery resourceQuery = RequestParamUtils.parse(request, ResourceQuery.class,
                    SystemConfigConsts.DEFAULT_DATE_PATTERN);
            pageInfo = resourceService.listResourcesWithUser(Integer.parseInt(pageNo), Integer.parseInt(pageSize), resourceQuery);
            result = ResponseResult.success(pageInfo);
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.RESOURCE_SELECT_ERROR.getCode(),
                    StatusCodeEnum.RESOURCE_SELECT_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 根据 id 修改资源状态
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/9
     */
    @RequestMapping("/modify_status")
    @ResponseBody
    public ResponseResult modifyStatus(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        try {
            Resource resource = RequestParamUtils.parse(request, Resource.class);
            resourceService.modifyStatus(resource);
            result = ResponseResult.success();
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.RESOURCE_MODIFY_ERROR.getCode(),
                    StatusCodeEnum.RESOURCE_MODIFY_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 根据前台查询条件分页查询用户发布资源
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/11
     */
    @RequestMapping("/front_select")
    @ResponseBody
    public ResponseResult frontSelect(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        String pageNo = request.getParameter("pageNo");
        if (null == pageNo) {
            pageNo = "1";
        }
        String pageSize = request.getParameter("pageSize");
        if (null == pageSize) {
            pageSize = SystemConfigConsts.DEFAULT_PAGE_SIZE + "";
        }

        PageInfo<Course> pageInfo = null;
        try {
            ResourceQuery resourceQuery = RequestParamUtils.parse(request, ResourceQuery.class,
                    SystemConfigConsts.DEFAULT_DATE_PATTERN);
            PageInfo<Resource> info = resourceService.frontSelect(Integer.parseInt(pageNo),
                    Integer.parseInt(pageSize), resourceQuery);
            result = ResponseResult.success(info);
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.COURSE_SELECT_ERROR.getCode(),
                    StatusCodeEnum.COURSE_SELECT_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 新增一个资源（前台用户使用）
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param multiFileList List<CommonsMultipartFile>
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/12
     */
    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(HttpServletRequest request, HttpServletResponse response, List<CommonsMultipartFile> multiFileList) {
        ResponseResult result = null;
        String realPath = request.getSession().getServletContext().getRealPath("/") + SystemConfigConsts.UPLOAD_PATH;
        if (multiFileList.isEmpty()) {
            result = ResponseResult.fail(StatusCodeEnum.RESOURCE_ADD_ERROR.getCode(),
                    StatusCodeEnum.RESOURCE_ADD_ERROR.getMessage(), "上传文件为空");
            return result;
        }
        CommonsMultipartFile file = multiFileList.get(0);
        try {
            UserResourceVO urVO = RequestParamUtils.parse(request, UserResourceVO.class);
            // 原文件名
            String name = file.getOriginalFilename();
            String originalName = name.substring(0, name.lastIndexOf("."));
            // 后缀
            String suffix = name.substring(name.lastIndexOf(".") + 1).toUpperCase();
            // 新文件名
            String newName = System.currentTimeMillis() + "." + suffix;
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

            urVO.setResourceFileType(suffix);
            urVO.setResourceOriginalName(originalName);
            urVO.setResourcePath(fileUrl);
            urVO.setResourceFileSize(fileSize);
            urVO.setResourceTotalTime(lengthInTime);
            urVO.setResourceCoverImageUrl(coverImageUrl);

            resourceService.add(urVO);
            result = ResponseResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            result = ResponseResult.fail(StatusCodeEnum.RESOURCE_ADD_ERROR.getCode(),
                    StatusCodeEnum.RESOURCE_ADD_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 查询指定用户发布的资源
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/12
     */
    @RequestMapping("/list_my")
    @ResponseBody
    public ResponseResult listMy(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        String pageNo = request.getParameter("pageNo");
        if (null == pageNo) {
            pageNo = "1";
        }
        String pageSize = request.getParameter("pageSize");
        if (null == pageSize) {
            pageSize = SystemConfigConsts.DEFAULT_PAGE_SIZE + "";
        }
        String userId = request.getParameter("userId");
        if (StringUtils.isEmpty(userId.trim())) {
            result = ResponseResult.fail(StatusCodeEnum.RESOURCE_SELECT_ERROR.getCode(),
                    StatusCodeEnum.RESOURCE_SELECT_ERROR.getMessage(),"用户编号为空");
            return result;
        }

        PageInfo<Resource> pageInfo = null;
        try {
            pageInfo = resourceService
                    .listUserResource(Integer.parseInt(pageNo), Integer.parseInt(pageSize), Integer.parseInt(userId));
            result = ResponseResult.success(pageInfo);
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.RESOURCE_SELECT_ERROR.getCode(),
                    StatusCodeEnum.RESOURCE_SELECT_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 查询用户购买的资源
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/13
     */
    @RequestMapping("/list_purchase")
    @ResponseBody
    public ResponseResult listPurchase(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        String pageNo = request.getParameter("pageNo");
        if (null == pageNo) {
            pageNo = "1";
        }
        String pageSize = request.getParameter("pageSize");
        if (null == pageSize) {
            pageSize = SystemConfigConsts.DEFAULT_PAGE_SIZE + "";
        }
        String userId = request.getParameter("userId");
        if (StringUtils.isEmpty(userId) || StringUtils.isBlank(userId)) {
            result = ResponseResult.fail(StatusCodeEnum.RESOURCE_SELECT_ERROR.getCode(),
                    StatusCodeEnum.RESOURCE_SELECT_ERROR.getMessage(),"用户编号为空");
            return result;
        }

        PageInfo<UserResource> info = null;
        try {
            info = resourceService
                    .listPurchase(Integer.parseInt(pageNo), Integer.parseInt(pageSize), Integer.parseInt(userId));
            result = ResponseResult.success(info);
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.RESOURCE_SELECT_ERROR.getCode(),
                    StatusCodeEnum.RESOURCE_SELECT_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 查询指定 id 的资源信息
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/12
     */
    @RequestMapping("/get")
    @ResponseBody
    public ResponseResult get4Front(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;

        String id = request.getParameter("id");
        if (StringUtils.isEmpty(id) || StringUtils.isBlank(id)) {
            result = ResponseResult.fail(StatusCodeEnum.RESOURCE_SELECT_ERROR.getCode(),
                    StatusCodeEnum.RESOURCE_SELECT_ERROR.getMessage(), "资源编号为空");
            return result;
        }

        try {
            Resource resource = resourceService.getById(Integer.parseInt(id));
            result = ResponseResult.success(resource);
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.RESOURCE_SELECT_ERROR.getCode(),
                    StatusCodeEnum.RESOURCE_SELECT_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 修改指定 id 的资源信息
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param multiFileList List<CommonsMultipartFile>
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/12
     */
    @RequestMapping("/modify")
    @ResponseBody
    public ResponseResult modify(HttpServletRequest request, HttpServletResponse response, List<CommonsMultipartFile> multiFileList) {
        ResponseResult result = null;
        String realPath = request.getSession().getServletContext().getRealPath("/") + SystemConfigConsts.UPLOAD_PATH;
        String name = null;
        String suffix = null;
        String originalName = null;
        String fileUrl = null;
        String fileSize = null;
        String lengthInTime = null;
        String coverImageUrl = null;
        try {
            UserResourceVO urVO = RequestParamUtils.parse(request, UserResourceVO.class);
            if (!multiFileList.isEmpty()) {
                CommonsMultipartFile file = multiFileList.get(0);
                // 原文件名
                name = file.getOriginalFilename();
                originalName = name.substring(0, name.lastIndexOf("."));
                // 后缀
                suffix = name.substring(name.lastIndexOf(".") + 1).toUpperCase();
                // 新文件名
                String newName = System.currentTimeMillis() + "." + suffix;
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

            urVO.setResourceFileType(suffix);
            urVO.setResourceOriginalName(originalName);
            urVO.setResourcePath(fileUrl);
            urVO.setResourceFileSize(fileSize);
            urVO.setResourceTotalTime(lengthInTime);
            urVO.setResourceCoverImageUrl(coverImageUrl);

            resourceService.modify(urVO);
            result = ResponseResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            result = ResponseResult.fail(StatusCodeEnum.CHAPTER_ADD_ERROR.getCode(),
                    StatusCodeEnum.CHAPTER_ADD_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 删除指定 id 的资源
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/12
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseResult delete(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        String id = request.getParameter("id");
        if (StringUtils.isEmpty(id) || StringUtils.isBlank(id)) {
            result = ResponseResult.fail(StatusCodeEnum.RESOURCE_SELECT_ERROR.getCode(),
                    StatusCodeEnum.RESOURCE_SELECT_ERROR.getMessage(), "资源编号为空");
            return result;
        }
        try {
            resourceService.delete(Integer.parseInt(id));
            result = ResponseResult.success();
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.RESOURCE_MODIFY_ERROR.getCode(),
                    StatusCodeEnum.RESOURCE_MODIFY_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }
}
