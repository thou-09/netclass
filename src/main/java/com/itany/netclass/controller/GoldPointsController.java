package com.itany.netclass.controller;

import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.netclass.constant.StatusCodeEnum;
import com.itany.netclass.constant.SystemConfigConsts;
import com.itany.netclass.entity.Comment;
import com.itany.netclass.entity.GoldPoints;
import com.itany.netclass.entity.User;
import com.itany.netclass.factory.ObjectFactory;
import com.itany.netclass.service.GoldPointsService;
import com.itany.netclass.util.RequestParamUtils;
import com.itany.netclass.util.ResponseResult;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 金币积分 Controller
 *
 * @author Thou
 * @date 2022/9/13
 */
@RequestMapping("/goldPoint")
public class GoldPointsController {

    private final GoldPointsService goldPointsService = ObjectFactory.getObject("goldPointsService");

    /**
     * 分页查询用户积分金币记录
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/9/13
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
        String userId = request.getParameter("userId");
        if (StringUtils.isEmpty(userId) || StringUtils.isBlank(userId)) {
            result = ResponseResult.fail(StatusCodeEnum.GOLD_POINT_SELECT_ERROR.getCode(),
                    StatusCodeEnum.GOLD_POINT_SELECT_ERROR.getMessage(), "用户编号为空");
            return result;
        }

        PageInfo<GoldPoints> pageInfo = null;
        try {
            pageInfo = goldPointsService.list(Integer.parseInt(pageNo), Integer.parseInt(pageSize), Integer.parseInt(userId));
            result = ResponseResult.success(pageInfo);
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.GOLD_POINT_SELECT_ERROR.getCode(),
                    StatusCodeEnum.GOLD_POINT_SELECT_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }


    @RequestMapping("/exchange")
    @ResponseBody
    public ResponseResult exchange(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult result = null;

        User loginUser = (User) request.getSession().getAttribute("loginUser");
        try {
            GoldPoints goldPoints = RequestParamUtils.parse(request, GoldPoints.class);
            User user = goldPointsService.exchange(goldPoints, loginUser);
            request.getSession().setAttribute("loginUser", user);
            result = ResponseResult.success();
        } catch (Exception e) {
            result = ResponseResult.fail(StatusCodeEnum.GOLD_POINT_SELECT_ERROR.getCode(),
                    StatusCodeEnum.GOLD_POINT_SELECT_ERROR.getMessage(), e.getMessage());
        }
        return result;
    }
}
