package com.zdf.utils;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: EmptyUtils
 * @Description: 判空工具类
 */
public class EmptyUtils {

    /**
     * @Title: isEmpty
     * @Description: 判断字符串是否为空，长度为0被认为是空字符串.
     * @param str
     * @return
     * @return Integer
     */
    public static boolean isEmpty(String str) {
        if (null != str) {
            return str.trim().length() == 0;
        } else {
            return true;
        }
    }

    /**
     * @Title: isEmpty
     * @Description: 判断Bigdecimal类型是否为空或者0
     * @return
     * @return Integer
     */
    public static boolean isEmptyBigdecimal(BigDecimal decimal) {
        if(!EmptyUtils.isEmpty(decimal)){
            if(decimal.compareTo(new BigDecimal(0))!=0){
                return false;
            }
        }
        return true;
    }

    /**
     * @Title: isEmpty
     * @Description: 判断字符串是否为空，字符串前后空白被截断，截断后长度为0被认为是空字符串
     * @param str
     * @param isTrimed 是否去掉前后空格
     * @return
     * @return Integer
     */
    public static boolean isEmpty(String str, boolean isTrimed) {
        if (isTrimed) {
            return null == str || str.trim().length() == 0;
        } else {
            return  null == str || str.length() == 0;
        }
    }

    /**
     * @Title: isEmpty
     * @Description: 判断列表是否为空，列表没有元素也被认为是空
     * @param collection
     * @return
     * @return Integer
     */
    public static boolean isEmpty(Collection<?> collection) {
        return null == collection || collection.size() == 0;
    }

    /**
     * @Title: isEmpty
     * @Description: 判断数组是否为空
     * @param array
     * @return
     * @return Integer
     */
    public static boolean isEmpty(Object[] array) {
        return null == array || array.length == 0;
    }

    /**
     * @Title: isEmpty
     * @Description: 判断对象是否为空
     * @param obj
     * @return
     * @return Integer
     */
    public static boolean isEmpty(Object obj) {
        return null == obj || "".equals(obj);
    }


    /**
     * 截取字符串中的数字
     * @param str
     * @return
     */
    public static String checkNum(String str) {
        StringBuilder builder=new StringBuilder();
        String regEx="(\\d+(\\.\\d+)?)";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        while (m.find()) {//当符合正则表达式定义的条件时
            builder.append(m.group());
        }
        return builder.toString();
    }

    /**
     * 截取字符串中除了数字以外的字符串
     * @param str
     * @return
     */
    public static String checkNumOhter(String str) {
        StringBuilder builder=new StringBuilder();
        String regEx="[^0-9.]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        while (m.find()) {//当符合正则表达式定义的条件时
            builder.append(m.group());
        }
        return builder.toString();
    }

    /**
     * 拿到字符串数组以逗号拼接成字符串返回
     * @param images
     * @return
     */
    public static String getImage(List<String> images) {
        String image="";
        if(!EmptyUtils.isEmpty(images)){
            for (int i = 0; i < images.size(); i++) {
                if(i==0||images.size()==1){
                    image=images.get(i);
                }else {
                    image+=","+images.get(i);
                }
            }
        }
        return image;
    }

    /**
     * 拿到字符串数组以分号拼接成字符串返回
     * @param images
     * @return
     */
    public static String getFenString(List<String> images) {
        String image="";
        if(!EmptyUtils.isEmpty(images)){
            for (int i = 0; i < images.size(); i++) {
                if(i==0||images.size()==1){
                    image=images.get(i);
                }else {
                    image+=";"+images.get(i);
                }
            }
        }
        return image;
    }


    /**
     * 拿到图片拼接字符串封装成数组对象（以逗号拼接）
     */
    public static List<String> getImages(String image){
        if(!EmptyUtils.isEmpty(image)){
            String[] split = image.split(",");
            return Arrays.asList(split);
        }
        return null;
    }

    /**
     * 拿到图片拼接字符串封装成数组对象（以分号拼接）
     */
    public static List<String> getFenStrings(String image){
        if(!EmptyUtils.isEmpty(image)){
            String[] split = image.split(";");
            return Arrays.asList(split);
        }
        return null;
    }



    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 计算切分次数
     */
    public static Integer countStep(Integer size, int input) {


        return (size + input - 1) / input;
    }

    /**
     * 过滤表情包参数
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {
        if(source != null)
        {
            Pattern emoji = Pattern.compile ("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",Pattern.UNICODE_CASE | Pattern . CASE_INSENSITIVE ) ;
            Matcher emojiMatcher = emoji.matcher(source);
            if ( emojiMatcher.find())
            {
                source = emojiMatcher.replaceAll("*");
                return source ;
            }
            return source;
        }
        return source;
    }
    /**
     * 判断对象是否有值
     * @param object
     * @return
     */

        public static boolean checkFieldAllNull(Object object) throws IllegalAccessException {
            for (Field f : object.getClass().getDeclaredFields()) {
                System.out.println("name:" + f.getName());
                f.setAccessible(true);
                if (Modifier.isFinal(f.getModifiers()) && Modifier.isStatic(f.getModifiers())) {
                    continue;
                }
                if (!isEmptyObject(f.get(object))) {
                    return false;
                }
                f.setAccessible(false);
            }
            //父类public属性
            for (Field f : object.getClass().getFields()) {
                System.out.println("name:" + f.getName());
                f.setAccessible(true);
                if (Modifier.isFinal(f.getModifiers()) && Modifier.isStatic(f.getModifiers())) {
                    continue;
                }
                if (!isEmptyObject(f.get(object))) {
                    return false;
                }
                f.setAccessible(false);
            }
            return true;
        }

    public static boolean isEmptyObject(Object object) {
            if (object == null) {
                return true;
            }
            if (object instanceof String && (object.toString().equals(""))) {
                return true;
            }
            if (object instanceof Collection && ((Collection) object).isEmpty()) {
                return true;
            }
            if (object instanceof Map && ((Map) object).isEmpty()) {
                return true;
            }
            if (object instanceof Object[] && ((Object[]) object).length == 0) {
                return true;
            }
            return false;
        }






}
