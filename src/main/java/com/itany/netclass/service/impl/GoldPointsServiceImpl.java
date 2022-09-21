package com.itany.netclass.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.netclass.dao.GoldPointsMapper;
import com.itany.netclass.entity.GoldPoints;
import com.itany.netclass.entity.User;
import com.itany.netclass.exception.GoldPointsErrorException;
import com.itany.netclass.factory.ObjectFactory;
import com.itany.netclass.service.GoldPointsService;

import java.util.Date;
import java.util.List;

/**
 * 积分金币 ServiceImpl
 *
 * @author Thou
 * @date 2022/9/13
 */
public class GoldPointsServiceImpl implements GoldPointsService {

    private final GoldPointsMapper goldPointsMapper = ObjectFactory.getObject("goldPointsMapper");

    @Override
    public PageInfo<GoldPoints> list(Integer pageNo, Integer pageSize, Integer userId) {
        PageHelper.startPage(pageNo, pageSize);
        List<GoldPoints> list = goldPointsMapper.listGoldPointsByUserId(userId);
        return new PageInfo<>(list);
    }

    @Override
    public User exchange(GoldPoints goldPoints, User loginUser) throws GoldPointsErrorException {
        GoldPoints gp1 = new GoldPoints();
        GoldPoints gp2 = new GoldPoints();
        int pointCount = (goldPoints.getGoldCount() * 10);
        if (loginUser.getTotalPoint() < pointCount) {
            throw new GoldPointsErrorException("积分不足");
        }

        // 减少积分
        gp1.setUserId(goldPoints.getUserId());
        gp1.setPointCount(-pointCount);
        gp1.setInfo("积分兑换金币，消耗" + pointCount + "积分");
        gp1.setCreateDate(new Date());
        loginUser.setTotalPoint(loginUser.getTotalPoint() - pointCount);

        // 增加金币
        gp2.setUserId(goldPoints.getUserId());
        gp2.setGoldCount(goldPoints.getGoldCount());
        gp2.setInfo("积分兑换金币，获得" + goldPoints.getGoldCount() + "金币");
        gp2.setCreateDate(new Date());
        loginUser.setTotalGold(loginUser.getTotalGold() + goldPoints.getGoldCount());

        goldPointsMapper.saveGoldPoints(gp1);
        goldPointsMapper.saveGoldPoints(gp2);

        return loginUser;
    }
}
