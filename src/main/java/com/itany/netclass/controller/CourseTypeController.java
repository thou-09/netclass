package com.itany.netclass.controller;

import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.netclass.constant.StatusCodeEnum;
import com.itany.netclass.constant.SystemConfigConsts;
import com.itany.netclass.entity.CourseType;
import com.itany.netclass.factory.ObjectFactory;
import com.itany.netclass.service.CourseTypeService;
import com.itany.netclass.util.RequestParamUtils;
import com.itany.netclass.util.ResponseResult;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 课程类别 Controller
 *
 * @author Thou
 * @date 2022/9/3
 */
@RequestMapping("/course_type")
public class CourseTypeController {

    private final CourseTypeService courseTypeService = ObjectFactory.getObject("courseTypeService");

    /**
     * 查询所有课程类别，树状结果
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/10
     */
    @RequestMapping("/all")
    @ResponseBody
    public ResponseResult listAll(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        try {
            List<CourseType> list = courseTypeService.listAll();
            result = ResponseResult.success(list);
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.COURSE_TYPE_SELECT_ERROR.getCode(),
                    StatusCodeEnum.COURSE_TYPE_SELECT_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 根据 parentId 分页查询
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

        PageInfo<CourseType> pageInfo = null;
        try {
            String parentId = request.getParameter("parentId");
            Integer pid = null;
            if (null != parentId) {
                pid = Integer.parseInt(parentId);
            }
            pageInfo = courseTypeService.listByParentId(Integer.parseInt(pageNo), Integer.parseInt(pageSize), pid);
            result = ResponseResult.success(pageInfo);
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.COURSE_TYPE_SELECT_ERROR.getCode(),
                    StatusCodeEnum.COURSE_TYPE_SELECT_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 查询属于兄弟节点的课程类别
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/10
     */
    @RequestMapping("/list_siblings")
    @ResponseBody
    public ResponseResult listSiblings(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        String pageNo = request.getParameter("pageNo");
        if (null == pageNo) {
            pageNo = "1";
        }
        String pageSize = request.getParameter("pageSize");
        if (null == pageSize) {
            pageSize = SystemConfigConsts.DEFAULT_PAGE_SIZE + "";
        }

        PageInfo<CourseType> pageInfo = null;
        try {
            String parentId = request.getParameter("parentId");
            Integer pid = null;
            if (null != parentId) {
                pid = Integer.parseInt(parentId);
            }
            pageInfo = courseTypeService.listParentSiblingsByParentId(Integer.parseInt(pageNo), Integer.parseInt(pageSize), pid);
            result = ResponseResult.success(pageInfo);
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.COURSE_TYPE_SELECT_ERROR.getCode(),
                    StatusCodeEnum.COURSE_TYPE_SELECT_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 新增一个课程类别
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/3
     */
    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        String typeName = request.getParameter("typeName");
        if (StringUtils.isEmpty(typeName.trim())) {
            result = ResponseResult.fail(StatusCodeEnum.COURSE_TYPE_ADD_ERROR.getCode(),
                    StatusCodeEnum.COURSE_TYPE_ADD_ERROR.getMessage(), "参数：typeName 为空");
            return result;
        }
        try {
            CourseType courseType = new CourseType();
            courseType.setTypeName(typeName);
            String parentId = request.getParameter("parentId");
            if (StringUtils.isEmpty(parentId.trim())) {
                courseType.setParentId(null);
            } else {
                courseType.setParentId(Integer.parseInt(parentId.trim()));
            }
            courseTypeService.add(courseType);
            result = ResponseResult.success();
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.COURSE_TYPE_ADD_ERROR.getCode(),
                    StatusCodeEnum.COURSE_TYPE_ADD_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 根据 id 查询指定的课程类别
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
            CourseType courseType = courseTypeService.get(Integer.parseInt(id));
            result = ResponseResult.success(courseType);
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.COURSE_TYPE_SELECT_ERROR.getCode(),
                    StatusCodeEnum.COURSE_TYPE_SELECT_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 修改课程类别
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/3
     */
    @RequestMapping("/modify")
    @ResponseBody
    public ResponseResult modify(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        try {
            CourseType courseType = RequestParamUtils.parse(request, CourseType.class);
            courseTypeService.modify(courseType);
            result = ResponseResult.success();
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.COURSE_TYPE_MODIFY_ERROR.getCode(),
                    StatusCodeEnum.COURSE_TYPE_MODIFY_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 修改状态
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/10
     */
    @RequestMapping("/modify_status")
    @ResponseBody
    public ResponseResult modifyStatus(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        try {
            CourseType courseType = RequestParamUtils.parse(request, CourseType.class);
            courseTypeService.modifyStatus(courseType);
            result = ResponseResult.success();
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.COURSE_TYPE_MODIFY_ERROR.getCode(),
                    StatusCodeEnum.COURSE_TYPE_MODIFY_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 查询所有三级课程类别
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/10
     */
    @RequestMapping("/three_level")
    @ResponseBody
    public ResponseResult listThreeLevelTypes(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        try {
            List<CourseType> list = courseTypeService.listThreeLevelTypes();
            result = ResponseResult.success(list);
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.COURSE_TYPE_SELECT_ERROR.getCode(),
                    StatusCodeEnum.COURSE_TYPE_SELECT_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }
}
