package com.itany.netclass.factory;

import com.itany.netclass.exception.DataAccessException;
import com.itany.netclass.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * 工厂
 *
 * @author teacher
 * @date 2018-8-22
 */
public class ObjectFactory {

	private static Map<String, Class<?>> clazzes = new HashMap<String, Class<?>>();
	private static Map<String, Object> objects = new HashMap<String, Object>();

	static {
		BufferedReader br = null;
		try {
			br = new BufferedReader(
					new InputStreamReader(
							ObjectFactory.class
									.getClassLoader()
									.getResourceAsStream("objects-mybatis.txt")));
			String s = null;
			while ((s = br.readLine()) != null) {
				if (s.startsWith("#")) {
					continue;
				}
				String[] entry = s.split("=");
				// 空行
				if(entry.length != 2 ){
					continue;
				}
				String key = entry[0].trim();
				String value = entry[1].trim();
				Class<?> c = Class.forName(value);
				clazzes.put(key, c);
				// dao接口
				if(c.isInterface()){
					objects.put(key, c);
					continue;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("ObjectFactory初始化错误" + e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T getObject(String key) {
		Object obj = objects.get(key);
		if(null == obj){
			synchronized (ObjectFactory.class) {
				if(null == obj){
					try {
						Class<?> c = clazzes.get(key);
						if(null == c){
							throw new NullPointerException("工厂中不存在key:" + key);
						}
						obj = c.newInstance();
						objects.put(key, obj);
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
		//返回dao的代理对象
		if (obj instanceof Class && ((Class<?>) obj).isInterface()) {
			Object daoProxy = Proxy.newProxyInstance(
					ObjectFactory.class.getClassLoader(), 
					new Class[] { (Class<?>) obj },
					new DaoHandler(obj)
				);
			return (T) daoProxy;
		}
		return (T) obj;
	}
}

/**
 * dao的Handler
 *
 * @author teacher
 * @date 2018-8-22
 */
class DaoHandler implements InvocationHandler {
	
	private Object target;
	
	public DaoHandler(Object target) {
		super();
		this.target = target;
	}
	
	@Override
	public Object invoke(Object proxy, Method method,
						 Object[] args) throws Throwable {
		Object result = null;
		try {
			SqlSession session = MyBatisUtil.getSession();
			Object dao = session.getMapper((Class<?>) target);
			Class<?> c = dao.getClass();
			String name = method.getName();
			Class<?>[] parameterTypes = method.getParameterTypes();
			Method daoMethod = c.getMethod(name, parameterTypes);
			result = daoMethod.invoke(dao, args);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("数据访问异常");
		}
		return result;
	}
}
