package com.itany.netclass.service;

import com.github.pagehelper.PageInfo;
import com.itany.netclass.entity.GoldPoints;
import com.itany.netclass.entity.User;
import com.itany.netclass.exception.GoldPointsErrorException;

/**
 * 积分金币 Service
 *
 * @author Thou
 * @date 2022/9/13
 */
public interface GoldPointsService {

    /**
     * 根据 userId 分页查询积分金币记录
     *
     * @param pageNo 当前页
     * @param pageSize 页面大小
     * @param userId 用户主键
     * @return com.github.pagehelper.PageInfo<com.itany.netclass.entity.Comment>
     * @author Thou
     * @date 2022/9/13
     */
    PageInfo<GoldPoints> list(Integer pageNo, Integer pageSize, Integer userId);

    /**
     * 积分兑换金币（10 - 1）
     *
     * @param goldPoints 封装了积分金币信息的积分金币对象
     * @param loginUser 登录用户
     * @return com.itany.netclass.entity.User
     * @throws GoldPointsErrorException 积分不足
     * @author Thou
     * @date 2022/9/13
     */
    User exchange(GoldPoints goldPoints, User loginUser) throws GoldPointsErrorException;
}
