package com.ctf.utils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.ApiModelProperty;
import org.apache.http.util.TextUtils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Random;

public class StringUtils {
    /**
     * 获取方法中指定注解的value值返回
     *
     * @param method               方法名
     * @param validationParamValue 注解的类名
     * @return
     */
    public static String getMethodAnnotationOne(Method method, String validationParamValue) {
        String retParam = null;
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            for (int j = 0; j < parameterAnnotations[i].length; j++) {
                String str = parameterAnnotations[i][j].toString();
                if (str.indexOf(validationParamValue) > 0) {
                    retParam = str.substring(str.indexOf("=") + 1, str.indexOf(")"));
                }
            }
        }
        return retParam;
    }

    /**
     * 给获取的企业打码
     *
     * @param companyName
     * @return
     */
    public static String getCompanyName(String companyName) {
        String prefix = null; //前缀
        String suffix = null; //后缀
        companyName = companyName.replaceAll("（", StringUtil.EMPTY_STRING).replaceAll("）", StringUtil.EMPTY_STRING);
        if (org.apache.commons.lang.StringUtils.isNotBlank(companyName)) {
            if (companyName.length() <= 6 && companyName.length() >= 2) {
                suffix = companyName.substring(companyName.length() - 2);
                if (!suffix.equals("公司")) {
                    prefix = companyName.substring(0, 1);
                    suffix = companyName.substring(companyName.length() - 1);
                }
            } else if (companyName.length() > 6) {
                String suffix6 = companyName.substring(companyName.length() - 6);
                String suffix4 = companyName.substring(companyName.length() - 4);
                String suffix2 = companyName.substring(companyName.length() - 2);
                String prefix3 = companyName.substring(0, 3);
                String prefix2 = companyName.substring(0, 2);
                String prefix1 = companyName.substring(0, 1);
                if ("股份有限公司".equals(suffix6)) {
                    suffix = companyName.substring(companyName.length() - 6);
                } else {
                    String substring = companyName.substring(companyName.length() - 4);
                    if ("有限公司".equals(suffix4)) {
                        suffix = substring;
                    } else if ("集团公司".equals(suffix4)) {
                        suffix = substring;
                    } else if ("集团".equals(suffix2)) {
                        suffix = companyName.substring(companyName.length() - 2);
                    } else if ("公司".equals(suffix2)) {
                        suffix = companyName.substring(companyName.length() - 2);
                    } else {
                        suffix = companyName.substring(companyName.length() - 2);
                    }
                }
                //前缀
                if ("中国".equals(prefix2)) {
                    prefix = companyName.substring(0, 3);
                } else if ("省".equals(prefix3.substring(prefix3.length() - 1)) || prefix3.substring(prefix3.length() - 1).equals("市")) {
                    prefix = companyName.substring(0, 4);
                } else if (prefix1.equals("中")) {
                    prefix = companyName.substring(0, 2);
                } else {
                    prefix = companyName.substring(0, 3);
                }
            }
            return prefix + "***" + suffix;
        } else {
            return "";
        }
    }

    /**
     * 用户身份证号码的打码隐藏加星号加*
     * <p>18位和非18位身份证处理均可成功处理</p>
     * <p>参数异常直接返回null</p>
     *
     * @param idCardNum 身份证号码
     * @param front     需要显示前几位
     * @param end       需要显示末几位
     * @return 处理完成的身份证
     */
    public static String idMask(String idCardNum, int front, int end) {
        //身份证不能为空
        if (TextUtils.isEmpty(idCardNum)) {
            return null;
        }
        //需要截取的长度不能大于身份证号长度
        if ((front + end) > idCardNum.length()) {
            return null;
        }
        //需要截取的不能小于0
        if (front < 0 || end < 0) {
            return null;
        }
        //计算*的数量
        int asteriskCount = idCardNum.length() - (front + end);
        StringBuilder asteriskStr = new StringBuilder();
        for (int i = 0; i < asteriskCount; i++) {
            asteriskStr.append("*");
        }
        String regex = "(\\w{" + front + "})(\\w+)(\\w{" + end + "})";
        return idCardNum.replaceAll(regex, "$1" + asteriskStr + "$3");
    }

    public static String generate(){
        char[] letters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'P','Q', 'R', 'S', 'T', 'U', 'V',
                'W', 'X', 'Y', 'Z','0','1','2','3','4','5','6','7','8','9'};
        boolean[] flags = new boolean[letters.length];
        char[] chs = new char[6];
        for (int i = 0; i < chs.length; i++) {
            int index;
            do {
                index = (int) (Math.random() * (letters.length));
            }
            // 判断生成的字符是否重复
            while (flags[index]);
            chs[i] = letters[index];
            flags[index] = true;
        }
        return new StringBuilder().append(chs).toString();
    }

    public static <T> LinkedList<T> getSwagger2Parameter(Class<?> clazz,Class<T> t){
        LinkedList<T> linkedList = new LinkedList<>();
        Field[] declaredFields = clazz.getDeclaredFields();
        for(Field field : declaredFields){
            ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
            if(apiModelProperty==null || apiModelProperty.hidden()){
                continue;
            }
            String name = apiModelProperty.name();
            T t1 = null;
            try {
                t1 = t.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(t1 instanceof LinkedHashMap){
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                linkedHashMap.put(StrUtil.isNotEmpty(name)?name:field.getName(),"text");
                linkedList.add((T) linkedHashMap);
            }else if(t1 instanceof String){
                String str = StrUtil.isNotEmpty(name)?name:field.getName();
                linkedList.add((T) str);
            }
        }
        return linkedList;
    }

    public static void main(String[] args) {

    }

    /**
     * 计算报文有多少行
     *
     * @param message 报文内容
     * @return
     */
    public static Integer messageLine(String message) {
        if (org.apache.commons.lang.StringUtils.isEmpty(message)) {
            return 0;
        }
        message = message.trim();
        String[] array = message.split("'");
        String[] array2 = message.split("\\?'");
        if (array2.length > 0) {
            return array.length - array2.length + 1;
        } else {
            return array.length;
        }
    }

    public static final char UNDERLINE = '_';

    //下划线转驼峰
    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        Boolean flag = false; // "_" 后转大写标志,默认字符前面没有"_"
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                flag = true;
                continue;   //标志设置为true,跳过
            } else {
                if (flag == true) {
                    //表示当前字符前面是"_" ,当前字符转大写
                    sb.append(Character.toUpperCase(param.charAt(i)));
                    flag = false;  //重置标识
                } else {
                    sb.append(Character.toLowerCase(param.charAt(i)));
                }
            }
        }
        return sb.toString();
    }

    /**
     * 转换为驼峰格式/转换为下划线方式
     *
     * @param json 等待转换的方法
     * @param upper 首字母大写或者小写
     * @return 转换后的
     */
    public static JSONObject underlineToCamel(final JSONObject json, boolean upper) {
        JSONObject real = new JSONObject();
        for (String it : json.keySet()) {
            Object objR = json.get(it);
            // 转换为驼峰格式/转换为下划线方式
            String key = it.contains("_") ? StrUtil.toCamelCase(it) : StrUtil.toUnderlineCase(it);
            // 首字母大写或者小写
            key = upper ? StrUtil.upperFirst(key) : StrUtil.lowerFirst(key);
            if (objR instanceof String) {
                real.put(key, objR);
            }
            if (objR instanceof JSONObject) {
                real.put(key, underlineToCamel((JSONObject) objR, upper));
            }
            if (objR instanceof JSONArray) {
                JSONArray jsonA = new JSONArray();
                for (Object objA : (JSONArray) objR) {
                    jsonA.add(underlineToCamel((JSONObject) objA, upper));
                }
                real.put(key, jsonA);
            }
        }
        return real;
    }

    /**
     * 获取异常信息
     * e.printStackTrace();打印是不会显示在日志里面的需要用这个方法获取
     * @param ex
     * @return
     */
    public static String getExceptionInfo(Exception ex) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream pout = new PrintStream(out);
        ex.printStackTrace(pout);
        String ret = new String(out.toByteArray());
        pout.close();
        try {
            out.close();
        } catch (Exception e) {
        }
        return ret;
    }

    /**
     * 生成随机数
     *
     * @param rnd
     * @param start   包含的开始位置
     * @param end     包含的结束位置
     * @param exclude 需要排除的值
     * @return
     */
    public static int getRandomWithExclusion(Random rnd, int start, int end, int... exclude) {
        int random = start + rnd.nextInt(end - start + 1 - exclude.length);
        for (int ex : exclude) {
            if (random < ex) {
                break;
            }
            random++;
        }
        return random;
    }

    public static boolean isEmpty(String str) {
        if ("".equals(str) || str == null) {
            return true;
        } else {
            return false;
        }
    }

}
