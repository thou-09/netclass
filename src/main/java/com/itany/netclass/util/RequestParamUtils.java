package com.itany.netclass.util;

import com.itany.netclass.annotation.Comment;
import com.itany.netclass.exception.RequestParameterErrorException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用于处理 request 中的 param 与实体类属性的绑定
 *
 * @author Thou
 * @date 2022/8/30
 */
public class RequestParamUtils {

    /**
     * 将 request 中的参数绑定到指定的对象中，返回对应类的对象
     *
     * @param request HttpServletRequest 请求对象
     * @param clazz 需要绑定的对象的类
     * @return T 对应类的对象
     * @throws RequestParameterErrorException 请求参数错误异常，当请求参数类型转换失败时，类属性字段类型未知时，反射发生错误时抛出此异常
     * @author Thou
     * @date 2022/9/1
     */
    public static <T> T parse(HttpServletRequest request, Class<T> clazz) throws RequestParameterErrorException {
        return parse(request, clazz, null);
    }

    /**
     * 将 request 中的参数绑定到指定的对象中，返回对应类的对象
     *
     * @param request HttpServletRequest 请求对象
     * @param clazz 需要绑定的对象的类
     * @param datePattern 日期格式，可为 null，默认使用 yyyy-MM-dd HH:mm:ss 格式
     * @throws RequestParameterErrorException 请求参数错误异常，当请求参数类型转换失败时，类属性字段类型未知时，反射发生错误时抛出此异常
     * @return T 对应类的对象
     * @author Thou
     * @date 2022/8/30
     */
    public static <T> T parse(HttpServletRequest request, Class<T> clazz, String datePattern) throws RequestParameterErrorException {
        T obj = null;
        String fieldName = null;
        String commentValue = null;
        try {
            // 实例化对象
            obj = clazz.newInstance();
            // 获得所有请求参数
            Map<String, String[]> parameterMap = request.getParameterMap();
            Class<? super T> superclass = clazz.getSuperclass();
            // 反射获得所有属性
            Field[] fields = clazz.getDeclaredFields();
            // 反射获得父类属性
            if (Object.class != superclass) {
                Field[] superClassFields = clazz.getSuperclass().getDeclaredFields();
                fields = ArrayUtils.addAll(fields, superClassFields);
            }
            // 遍历属性
            for (Field field : fields) {
                field.setAccessible(true);
                fieldName = field.getName();
                // 参数中包含该属性
                if (parameterMap.containsKey(fieldName)) {
                    // 获得 Comment 注解
                    Comment comment = field.getAnnotation(Comment.class);
                    if (null != comment) {
                        commentValue = comment.value();
                    }
                    String[] paramValues = parameterMap.get(fieldName);
                    // 排除空的情况
                    if (ObjectUtils.isEmpty(paramValues) || StringUtils.isEmpty(paramValues[0].trim())) {
                        String msg = null;
                        if (null != commentValue) {
                            msg = commentValue + "为空";
                        } else {
                            msg = "参数：" + fieldName + " 为空";
                        }
                        throw new RequestParameterErrorException(msg);
                    }
                    Class<?> fieldType = field.getType();
                    // 类型转换
                    if (paramValues.length > 1) {
                        field.set(obj, paramValues);
                    } else {
                        String value = paramValues[0];
                        if (String.class == fieldType) {
                            field.set(obj, value);
                        } else if (Integer.class == fieldType) {
                            field.set(obj, Integer.valueOf(value));
                        } else if (Long.class == fieldType) {
                            field.set(obj, Long.valueOf(value));
                        } else if (Double.class == fieldType) {
                            field.set(obj, Double.valueOf(value));
                        } else if (Boolean.class == fieldType) {
                            field.set(obj, Boolean.valueOf(value));
                        } else if (BigDecimal.class == fieldType) {
                            field.set(obj, BigDecimal.valueOf(Long.parseLong(value)));
                        } else if (Date.class == fieldType) {
                            if (datePattern == null) {
                                datePattern = "yyyy-MM-dd HH:mm:ss";
                            }
                            SimpleDateFormat format = new SimpleDateFormat(datePattern);
                            field.set(obj, format.parse(value));
                        } else {
                            throw new RequestParameterErrorException(fieldName + "字段类型未知");
                        }
                    }
                }
            }
        }  catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RequestParameterErrorException("服务器繁忙，请联系管理员");
        } catch (ParseException | NumberFormatException e) {
            String msg = null;
            if (null != commentValue) {
                msg = commentValue + "格式错误";
            } else {
                msg = "参数：" + fieldName + " 格式错误";
            }
            throw new RequestParameterErrorException(msg, e);
        }
        return obj;
    }

    /**
     * 判断 request 中请求参数是否不为空<br>
     * 为空报出异常包含具体信息，反之返回 true<br>
     *
     * <b>不使用，返回的错误不清晰</b>
     * @param request HttpServletRequest
     * @return java.lang.Boolean
     * @author Thou
     * @date 2022/9/1
     */
    @Deprecated
    public static Boolean isParamsNotEmpty(HttpServletRequest request) throws RequestParameterErrorException {
        // 获得所有请求参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String[] value = entry.getValue();
            String key = entry.getKey();
            if (value.length == 1) {
                if (StringUtils.isEmpty(value[0]) || StringUtils.isBlank(value[0])) {
                    throw new RequestParameterErrorException("参数：" + key + " 为空");
                }
            }
        }
        return true;
    }
}
