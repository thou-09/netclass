package com.itany.netclass._my.ioc;

/**
 * bean 工厂
 *
 * @author Thou
 * @date 2022/8/30
*/
public interface BeanFactory {

    /**
     * 获得对应的 bean 对象
     *
     * @param id beanId
     * @return java.lang.Object bean 对象实例
     * @author Thou
     * @date 2022/8/30
     */
    Object getBean(String id);
}
