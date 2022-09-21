package com.itany.netclass.controller;

import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.netclass.constant.StatusCodeEnum;
import com.itany.netclass.constant.SystemConfigConsts;
import com.itany.netclass.entity.Comment;
import com.itany.netclass.entity.Praise;
import com.itany.netclass.entity.User;
import com.itany.netclass.factory.ObjectFactory;
import com.itany.netclass.query.CommentQuery;
import com.itany.netclass.service.CommentService;
import com.itany.netclass.util.RequestParamUtils;
import com.itany.netclass.util.ResponseResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 评论 Controller
 *
 * @author Thou
 * @date 2022/9/3
 */
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService = ObjectFactory.getObject("commentService");

    /**
     * 根据条件分页查询评论
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

        PageInfo<Comment> pageInfo = null;
        try {
            CommentQuery commentQuery = RequestParamUtils.parse(request, CommentQuery.class,
                    SystemConfigConsts.DEFAULT_DATE_PATTERN);
            pageInfo = commentService.listComments(Integer.parseInt(pageNo), Integer.parseInt(pageSize), commentQuery);
            result = ResponseResult.success(pageInfo);
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.COMMENT_SELECT_ERROR.getCode(),
                    StatusCodeEnum.COMMENT_SELECT_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 修改评论状态
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/3
     */
    @RequestMapping("/modify_status")
    @ResponseBody
    public ResponseResult modifyStatus(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        try {
            Comment comment = RequestParamUtils.parse(request, Comment.class);
            commentService.modify(comment);
            result = ResponseResult.success();
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.USER_MODIFY_ERROR.getCode(),
                    StatusCodeEnum.USER_MODIFY_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 发布评论
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/13
     */
    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        try {
            Comment comment = RequestParamUtils.parse(request, Comment.class);
            commentService.add(comment);
            result = ResponseResult.success();
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.USER_MODIFY_ERROR.getCode(),
                    StatusCodeEnum.USER_MODIFY_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }

    /**
     * 点赞评论
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/13
     */
    @RequestMapping("/praise")
    @ResponseBody
    public ResponseResult praise(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;
        try {
            Praise praise = RequestParamUtils.parse(request, Praise.class);
            commentService.praise(praise);
            result = ResponseResult.success();
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.USER_MODIFY_ERROR.getCode(),
                    StatusCodeEnum.USER_MODIFY_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }
}
