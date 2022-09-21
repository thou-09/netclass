package com.itany.netclass.dao;

import com.itany.netclass.entity.GoldPoints;
import com.itany.netclass.exception.DataAccessException;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 积分金币记录持久层接口
 *
 * @author Thou
 * @date 2022/8/31
 */
public interface GoldPointsMapper {

    /**
     * 新增一个积分金币记录
     *
     * @param goldPoints 封装了积分金币信息的积分金币对象
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/31
     */
    void saveGoldPoints(GoldPoints goldPoints) throws DataAccessException;

    /**
     * 根据 id 删除积分金币记录
     *
     * @param id 积分金币主键
     * @author Thou
     * @date 2022/8/31
     */
    void removeGoldPointsById(@Param("id") Integer id);

    /**
     * 根据 id 查询积分金币记录
     *
     * @param id 积分金币主键
     * @return com.itany.netclass.entity.GoldPoints
     * @author Thou
     * @date 2022/8/31
     */
    GoldPoints getGoldPointsById(@Param("id") Integer id);

    /**
     * 根据 userId、createDate、type 查询积分金币记录
     *
     * @param userId 用户主键
     * @param createDateStart 创建开始时间
     * @param createDateEnd 创建结束事件
     * @param type 操作类型
     * @return com.itany.netclass.entity.GoldPoints
     * @author Thou
     * @date 2022/9/10
     */
    GoldPoints getGoldPointByUserIdAndCreateDateAndType(
            @Param("uid") Integer userId,
            @Param("dateStart") Date createDateStart,
            @Param("dateEnd") Date createDateEnd,
            @Param("type") Integer type
    );


    /**
     * 根据 userId 查询积分金币记录
     *
     * @param userId 用户主键
     * @return java.util.List<com.itany.netclass.entity.GoldPoints>
     * @author Thou
     * @date 2022/8/31
     */
    List<GoldPoints> listGoldPointsByUserId(@Param("uid") Integer userId);

    /**
     * 根据 userId 查询积分总数
     *
     * @param userId 用户主键
     * @return java.lang.Integer
     * @author Thou
     * @date 2022/8/31
     */
    Integer sumPointByUserId(@Param("uid") Integer userId);

    /**
     * 根据 userId 查询金币总数
     *
     * @param userId 用户主键
     * @return java.lang.Integer
     * @author Thou
     * @date 2022/8/31
     */
    Integer sumGoldByUserId(@Param("uid") Integer userId);
}
