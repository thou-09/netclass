package com.itany.netclass.constant;

/**
 * 资源相关常量
 *
 * @author Thou
 * @date 2022/8/30
 */
public interface ResourceConsts {

    /**
     * 付款类型：积分
     */
    Integer RESOURCE_COST_TYPE_POINT = 0;
    /**
     * 付款类型：金币
     */
    Integer RESOURCE_COST_TYPE_GOLD = 1;
    /**
     * 资源状态：启用
     */
    Integer RESOURCE_STATUS_ENABLE = 1;
    /**
     * 资源状态：禁用
     */
    Integer RESOURCE_STATUS_DISABLE = -1;
    /**
     * 默认点击量
     */
    Integer DEFAULT_CLICK_COUNT = 0;
}
