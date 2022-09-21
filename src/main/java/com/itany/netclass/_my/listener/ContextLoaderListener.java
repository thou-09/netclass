package com.itany.netclass._my.listener;

import com.itany.netclass._my.ioc.ClassPathXmlApplicationContext;
import com.itany.netclass.exception.BeanFactoryErrorException;
import com.itany.netclass._my.ioc.BeanFactory;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 监听 ServletContext 的创建和销毁，创建 beanFactory
 *
 * @author Thou
 * @date 2022/8/30
*/
// @WebListener
public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
        String contextConfigLocation = application.getInitParameter("contextConfigLocation");
        BeanFactory beanFactory = null;
        if (StringUtils.isNotEmpty(contextConfigLocation)) {
            try {
                beanFactory = new ClassPathXmlApplicationContext(contextConfigLocation);
            } catch (BeanFactoryErrorException e) {
                e.printStackTrace();
            }
        } else {
            try {
                beanFactory = new ClassPathXmlApplicationContext();
            } catch (BeanFactoryErrorException e) {
                e.printStackTrace();
            }
        }
        application.setAttribute("beanFactory", beanFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
