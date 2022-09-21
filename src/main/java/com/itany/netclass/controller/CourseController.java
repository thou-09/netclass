package com.itany.netclass.controller;

import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.netclass.constant.StatusCodeEnum;
import com.itany.netclass.constant.SystemConfigConsts;
import com.itany.netclass.entity.Course;
import com.itany.netclass.entity.CourseType;
import com.itany.netclass.entity.User;
import com.itany.netclass.factory.ObjectFactory;
import com.itany.netclass.query.CourseQuery;
import com.itany.netclass.service.CourseService;
import com.itany.netclass.util.FileLoadUtils;
import com.itany.netclass.util.RequestParamUtils;
import com.itany.netclass.util.ResponseResult;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 课程 Controller
 *
 * @author Thou
 * @date 2022/9/3
 */
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService = ObjectFactory.getObject("courseService");

    /**
     * 根据后台查询条件分页查询课程信息
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

        PageInfo<Course> pageInfo = null;
        try {
            CourseQuery courseQuery = RequestParamUtils.parse(request, CourseQuery.class,
                    SystemConfigConsts.DEFAULT_DATE_PATTERN);
            pageInfo = courseService.list(Integer.parseInt(pageNo), Integer.parseInt(pageSize), courseQuery);
            result = ResponseResult.success(pageInfo);
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.COURSE_SELECT_ERROR.getCode(),
                    StatusCodeEnum.COURSE_SELECT_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 新增一个课程信息
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param multiFileList List<com.itany.mvc.util.CommonsMultipartFile>
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
            result = ResponseResult.fail(StatusCodeEnum.COURSE_ADD_ERROR.getCode(),
                    StatusCodeEnum.COURSE_ADD_ERROR.getMessage(), "上传文件为空");
            return result;
        }
        CommonsMultipartFile file = multiFileList.get(0);
        try {
            Course course = RequestParamUtils.parse(request, Course.class);
            // 暂不使用，上传到阿里云 OSS
            /* String uploadUrl = FileLoadUtils.uploadAliyunOss(file, request); */
            String fileUrl = FileLoadUtils.upload(file, realPath);
            course.setCoverImageUrl(fileUrl);
            courseService.add(course);
            result = ResponseResult.success();
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.COURSE_ADD_ERROR.getCode(),
                    StatusCodeEnum.COURSE_ADD_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 根据 id 获取指定的课程信息
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/8
     */
    @RequestMapping("/get")
    @ResponseBody
    public ResponseResult get(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        try {
            String id = request.getParameter("id");
            Course course = courseService.getOne(Integer.parseInt(id));
            result = ResponseResult.success(course);
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.COURSE_SELECT_ERROR.getCode(),
                    StatusCodeEnum.COURSE_SELECT_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 修改课程信息
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param multiFileList List<com.itany.mvc.util.CommonsMultipartFile>
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/3
     */
    @RequestMapping("/modify")
    @ResponseBody
    public ResponseResult modify(HttpServletRequest request, HttpServletResponse response, List<CommonsMultipartFile> multiFileList) {
        ResponseResult result = null;
        String realPath = request.getSession().getServletContext().getRealPath("/") + SystemConfigConsts.UPLOAD_PATH;
        String fileUrl = null;
        try {
            Course course = RequestParamUtils.parse(request, Course.class);
            if (!multiFileList.isEmpty()) {
                CommonsMultipartFile file = multiFileList.get(0);
                fileUrl = FileLoadUtils.upload(file, realPath);
            }
            course.setCoverImageUrl(fileUrl);
            courseService.modifyById(course);
            result = ResponseResult.success();
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.COURSE_MODIFY_ERROR.getCode(),
                    StatusCodeEnum.COURSE_MODIFY_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 修改课程状态，及对应的资源状态
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
            Course course = RequestParamUtils.parse(request, Course.class);
            courseService.modifyStatusById(course);
            result = ResponseResult.success();
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.COURSE_TYPE_MODIFY_ERROR.getCode(),
                    StatusCodeEnum.COURSE_TYPE_MODIFY_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 查询一级课程类别下所有课程点击量前四的课程
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/10
     */
    @RequestMapping("/top4")
    @ResponseBody
    public ResponseResult listTopFour(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        String typeId = request.getParameter("courseTypeId");
        if (StringUtils.isEmpty(typeId.trim())) {
            result = ResponseResult.fail(StatusCodeEnum.COURSE_TYPE_SELECT_ERROR.getCode(),
                    StatusCodeEnum.COURSE_TYPE_SELECT_ERROR.getMessage(), "课程编号为空");
            return result;
        }
        try {
            List<Course> list = courseService.listTopFour(Integer.parseInt(typeId));
            result = ResponseResult.success(list);
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.COURSE_TYPE_SELECT_ERROR.getCode(),
                    StatusCodeEnum.COURSE_TYPE_SELECT_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 查询所有课程的点击量前十的课程
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/10
     */
    @RequestMapping("/top10")
    @ResponseBody
    public ResponseResult listTopTen(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        try {
            List<Course> list = courseService.listTopTen();
            result = ResponseResult.success(list);
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.COURSE_TYPE_SELECT_ERROR.getCode(),
                    StatusCodeEnum.COURSE_TYPE_SELECT_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 根据前台查询条件分页查询课程信息
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
            CourseQuery courseQuery = RequestParamUtils.parse(request, CourseQuery.class,
                    SystemConfigConsts.DEFAULT_DATE_PATTERN);
            User loginUser = (User) request.getSession().getAttribute("loginUser");
            courseQuery.setLoginUserId(loginUser.getId());
            PageInfo<Course> info = courseService.frontSelect(Integer.parseInt(pageNo),
                    Integer.parseInt(pageSize), courseQuery);
            result = ResponseResult.success(info);
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.COURSE_SELECT_ERROR.getCode(),
                    StatusCodeEnum.COURSE_SELECT_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 通过 id 查询课程，课程类别，所有章节和章节的资源，以及课程下的所有评论和评论的用户
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/12
     */
    @RequestMapping("/course")
    public String get4Front(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        if (StringUtils.isEmpty(id) || StringUtils.isBlank(id)) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "";
        }
        try {
            Course course = courseService.get4Front(Integer.parseInt(id));
            request.setAttribute("course", course);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "";
        }
        return "front_course";
    }
}
