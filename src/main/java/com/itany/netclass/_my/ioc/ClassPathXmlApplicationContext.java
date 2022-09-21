package com.itany.netclass._my.ioc;

import com.itany.netclass.exception.BeanFactoryErrorException;
import com.itany.netclass.exception.DataAccessException;
import com.itany.netclass.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * 解析 applicationContext.xml 配置文件，管理所有的 bean 对象
 *
 * @author Thou
 * @date 2022/8/30
*/
public class ClassPathXmlApplicationContext implements BeanFactory {

    public static Logger logger = Logger.getLogger(ClassPathXmlApplicationContext.class);
    private final Map<String, Object> beanMap = new HashMap<>();

    public ClassPathXmlApplicationContext() throws BeanFactoryErrorException {
        this("com/itany/netclass/_my/applicationContext.xml");
    }

    public ClassPathXmlApplicationContext(String path) throws BeanFactoryErrorException {
        // 1. 读取 applicationContext.xml 配置文件
        InputStream is = ClassPathXmlApplicationContext.class
                .getClassLoader()
                .getResourceAsStream(path);
        // 2. 创建 documentBuilderFactory 对象
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            logger.debug("beanFactory-info: beanFactory start scanning bean.");
            // 3. 创建 documentBuilder 对象
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            // 4. 获得 document 对象
            Document document = documentBuilder.parse(is);
            // 5. 获得所有的 bean 标签
            NodeList beanNodeList = document.getElementsByTagName("bean");
            for (int i = 0; i < beanNodeList.getLength(); i++) {
                Node beanNode = beanNodeList.item(i);
                if (beanNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element beanElement = (Element)beanNode;
                    String beanId = beanElement.getAttribute("id");
                    String beanClass = beanElement.getAttribute("class");
                    StringBuffer msg = new StringBuffer();
                    msg.append("beanFactory-info: load bean [id=").append(beanId)
                            .append(", class=").append(beanClass).append("]");
                    logger.debug(msg.toString());
                    // 6. 创建 bean 实例
                    Class<?> clazz = Class.forName(beanClass);
                    // dao 层接口
                    if (clazz.isInterface()) {
                        Object daoProxy = Proxy.newProxyInstance(DaoHandler.class.getClassLoader(),
                                new Class[]{clazz}, new DaoHandler(clazz));
                        beanMap.put(beanId, daoProxy);
                        continue;
                    }
                    Object beanObj = Class.forName(beanClass).newInstance();
                    // 7. 将 bean 实例交给 beanMap 容器保存
                    beanMap.put(beanId, beanObj);
                }
            }
            // 8. 组装 bean 与 bean 之间的依赖关系
            logger.debug("beanFactory-info: beanFactory start inject dependencies.");
            for (int i = 0; i < beanNodeList.getLength(); i++) {
                Node beanNode = beanNodeList.item(i);
                if (beanNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element beanElement = (Element)beanNode;
                    String beanId = beanElement.getAttribute("id");
                    // 9. 获得 bean 标签内的所有子节点
                    NodeList beanChildNodes = beanElement.getChildNodes();
                    for (int j = 0; j < beanChildNodes.getLength(); j++) {
                        Node beanChildNode = beanChildNodes.item(j);
                        if (beanChildNode.getNodeType() == Node.ELEMENT_NODE && "property".equals(beanChildNode.getNodeName())) {
                            Element propertyElement = (Element) beanChildNode;
                            String propertyName = propertyElement.getAttribute("name");
                            String propertyRef = propertyElement.getAttribute("ref");
                            StringBuffer msg = new StringBuffer();
                            msg.append("beanFactory-info: dependency inject [beanId=").append(beanId)
                                    .append(", name=").append(propertyName)
                                    .append(", ref=").append(propertyRef).append("]");
                            logger.debug(msg.toString());
                            // 10. 在 beanMap 中找到对应的 bean 实例
                            Object refObj = beanMap.get(propertyRef);
                            // 11. 将 bean 对象设置到当前的对象的属性上去
                            Object beanObj = beanMap.get(beanId);
                            Field propertyField = beanObj.getClass().getDeclaredField(propertyName);
                            propertyField.setAccessible(true);
                            propertyField.set(beanObj, refObj);
                        }
                    }
                }
            }
            logger.debug("beanFactory-info: beanFactory end inject dependencies.");
            logger.debug("beanFactory-info: beanFactory end scanning bean.");
        } catch (Exception e) {
            logger.error("beanFactory-info: beanFactory scanning error.", e);
            throw new BeanFactoryErrorException("beanFactory scanning error", e);
        }
    }

    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }
}

/**
 * Mapper 处理器，通过反射调用接口中的方法并返回数据
 *
 * @author Thou
 * @date 2022/8/31
 */
class DaoHandler implements InvocationHandler {

    private Object target;

    public DaoHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object returnObj = null;
        try {
            SqlSession session = MyBatisUtil.getSession();
            // 获得 mapper 接口
            Object mapper = session.getMapper((Class<?>) target);
            // 获得 mapper 接口的 class
            Class<?> mapperClass = mapper.getClass();
            // 获得要调用的方法名
            String methodName = method.getName();
            // 获得方法的参数类型
            Class<?>[] parameterTypes = method.getParameterTypes();
            // 获得要调用的方法
            Method mapperMethod = mapperClass.getMethod(methodName, parameterTypes);
            // 调用方法
            returnObj = mapperMethod.invoke(mapper, args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("数据访问异常");
        }
        return returnObj;
    }
}
