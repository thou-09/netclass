package com.itany.netclass.util;

import com.itany.netclass.entity.CourseType;
import com.itany.netclass.entity.Resource;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Classname ResultMapUtil
 * @Description 快速生成resultMap
 * @date  2019/12/18
 * @author  by zhaoqian
 */
@SuppressWarnings("all")
public class ResultMapUtil {
    private static String mainHead = "<resultMap id=\"map\" type=\"classPath\">";
    private static String mainIdBody ="  <id property=\"pname\" column=\"cname\"/>";
    private static String mainBody = "  <result property=\"pname\" column=\"cname\"/>";
    private static String mainOver = "</resultMap>";

    private static String assHead = "  <association property=\"pname\" javaType=\"classPath\">";
    private static String assIdBody = "    <id property=\"pname\" column=\"cname\"/>";
    private static String assBody = "    <result property=\"pname\" column=\"cname\"/>";
    private static String assOver = "  </association>";

    private static String colHead = "  <collection property=\"pname\" ofType=\"classPath\">";
    private static String colIdBody = "    <id property=\"pname\" column=\"cname\"/>";
    private static String colBody = "    <result property=\"pname\" column=\"cname\"/>";
    private static String colOver = "  </collection>";

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    private static List<Class> filterList = new ArrayList<>();

    public static void main( String[] args ) {
        //把需要生成resultMap的对象作为参数执行
        getResultMap(new CourseType());
    }

    public static void getResultMap(Object obj) {
        init();
        Class<?> clazz = obj.getClass();
        String path = clazz.getName();
        System.out.println(mainHead.replace( "classPath",path));
        Field[] fields = clazz.getDeclaredFields();
        List<Field> others = new ArrayList<>();
        // 处理所有普通类型(包含包装类、 String、 Date)
        for (int i = 0; i < fields.length; i++) {
            // 跳过 serialVersionUID 属性
            if ("serialVersionUID".equals(fields[i].getName())) {
                continue;
            }
            fields[i].setAccessible(true);
            if (judgeFields(fields[i]) == 1) {
                String pname = fields[i].getName();
                String cname = humpToLine(pname);
                String str = null;
                if ("id".equals(fields[i].getName())) {
                    str = mainIdBody.replace("pname", pname).replace("cname", cname);
                } else{
                    str = mainBody.replace("pname", pname).replace("cname", cname);
                }
                System.out.println(str);
            }else{
                others.add(fields[i]);
            }
        }

        // 处理特殊类型
        for (int i = 0; i < others.size(); i++) {
            Field field = others.get(i);
            field.setAccessible(true);
            if (judgeFields(field) == 3) {
                Class<?> type = field.getType();
                String path2 = type.getName();
                String name = field.getName();
                System.out.println(assHead.replace("classPath", path2).replace("pname", name));
                Field[] fields2 = type.getDeclaredFields();
                for (int j = 0; j < fields2.length; j++) {
                    // 跳过 serialVersionUID 属性
                    if ("serialVersionUID".equals(fields2[j].getName())) {
                        continue;
                    }
                    fields2[j].setAccessible(true);
                    String pname = fields2[j].getName();
                    String cname = humpToLine(pname);
                    String str = null;
                    if ("id".equals(fields2[j].getName())) {
                        str = assIdBody.replace("pname", pname).replace("cname", cname);
                    } else{
                        str = assBody.replace("pname", pname).replace("cname", cname);
                    }
                    System.out.println(str);
                }
                System.out.println(assOver);
            }
        }

        // 处理 listByParentId
        for (int i = 0; i < others.size(); i++) {
            Field field = others.get(i);
            field.setAccessible(true);
            if (judgeFields(field) == 2) {
                Class<?> type = field.getType();
                Class<?> trueType = null;
                Type genericType = field.getGenericType();
                if (genericType instanceof ParameterizedType) {
                    ParameterizedType pt = (ParameterizedType)genericType;
                    Class<?> c = (Class<?>)pt.getActualTypeArguments()[0];
                    trueType = c;
                }
                String path3 = trueType.getName();
                String name = field.getName();
                System.out.println(colHead.replace("classPath", path3).replace("pname", name));
                Field[] fields3 = trueType.getDeclaredFields();
                for (int j = 0; j < fields3.length; j++) {
                    // 跳过 serialVersionUID 属性
                    if ("serialVersionUID".equals(fields3[j].getName())) {
                        continue;
                    }
                    fields3[j].setAccessible(true);
                    String pname = fields3[j].getName();
                    String cname = humpToLine(pname);
                    String str = null;
                    if ("id".equals(fields3[j].getName())){
                        str = colIdBody.replace("pname", pname).replace("cname", cname);
                    }else{
                        str = colBody.replace("pname", pname).replace("cname", cname);
                    }
                    System.out.println(str);
                }
                System.out.println(colOver);
            }
        }
        System.out.println(mainOver);
    }

    /**
     * 驼峰转下划线
     * @param str
     * @return
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 判断类型
     * @param field
     * @return 1基础类型包装类String 2List 3其他特殊类型
     */
    public static Integer judgeFields(Field field) {
        Class<?> type = field.getType();
        if (type.isPrimitive()) {
            return 1;
        }
        for (Class<?> clazz : filterList) {
            if (type == clazz) {
                return 1;
            }
        }
        if (type == List.class) {
            return 2;
        }
        return 3;
    }

    public static void init() {
        filterList.add(String.class);
        filterList.add(Byte.class);
        filterList.add(Short.class);
        filterList.add(Integer.class);
        filterList.add(Long.class);
        filterList.add(Double.class);
        filterList.add(Float.class);
        filterList.add(Boolean.class);
        filterList.add(Character.class);
        filterList.add(Date.class);
        filterList.add(BigDecimal.class);
    }
}
