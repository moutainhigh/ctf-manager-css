package com.ctf.utils.vaildator;


import com.lenovo.enums.ResultEnum;
import com.lenovo.exception.Asserts;
import com.lenovo.result.ResultCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.lang.NonNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 校验List中的重复行
 *
 * @author william
 * @description
 * @Date: 2020-07-29 18:20
 */
public class Check4List {
    /**
     * 校验列表中被@NotDuplicate注解标记的字段不为空且不重复
     * 支持校验列表内的列表(开发中)
     *
     * @param list
     */
    public static void checkNullNDuplicate(@NonNull List<?> list) {
        String errorString = getNullOrDuplicateErrorString(list);
        if (!StringUtils.isBlank(errorString)) {
            Asserts.fail(ResultCode.ERROR, Objects.isNull(errorString)?"checkNullNDuplicate internal error":errorString);
        }
    }

    /**
     * 校验列表中被标记的字段不为空且不重复，并返回检出的重复信息
     *
     * @param list
     * @return
     */
    public static String getNullOrDuplicateErrorString(@NonNull List<?> list) {
        if (list.isEmpty()) {
            return "";
        }
        List<String> colNames = new ArrayList<>();
        List<String> listNames = new ArrayList<>();
        Class<?> aClass = list.get(0).getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            Annotation[] declaredAnnotations = declaredField.getDeclaredAnnotations();
            for (Annotation declaredAnnotation : declaredAnnotations) {
                if (declaredAnnotation.annotationType() == NotDuplicate.class && !List.class.isAssignableFrom(declaredField.getType())) {
                    //只记录非列表
                    colNames.add(declaredField.getName());
                }
            }
            if (List.class.isAssignableFrom(declaredField.getType())) {
                //如果元素是一个列表
                listNames.add(declaredField.getName());
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String colName : colNames) {
            doDuplicateCheck(colName, list, sb);
        }
        //遍历递归列表
        for (String listName : listNames) {

            //遍历列表的行看看里面还有没有列表
            Class<?> clz = list.get(0).getClass();
            try {
                Method getMethod = clz.getMethod("get" + listName.substring(0, 1).toUpperCase() + listName.substring(1));
                for (Integer i = 0; i < list.size(); i++) {
                    Object invoke = getMethod.invoke(list.get(i));
                    String rowListErrorString = "";
                    if (null != invoke.getClass() && invoke.getClass() == List.class) {
                        rowListErrorString = getNullOrDuplicateErrorString((List) invoke);

                    }
                    if (null != invoke.getClass() && invoke.getClass() == ValidList.class) {
                        rowListErrorString = getNullOrDuplicateErrorString(((ValidList) invoke).getList());
                    }

                    if (!StringUtils.isBlank(rowListErrorString)) {
                        sb.append(String.format("list %d column %s : %s ", i + 1, listName, rowListErrorString));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return sb.toString();
    }




    /**
     * 查重：非空且不重复
     *
     * @param columnName
     * @param list
     * @param sb
     */
    private static void doDuplicateCheck(String columnName, List<?> list, StringBuilder sb) {
        try {
            Class<?> clz = list.get(0).getClass();
            //获取指定名字的属性的get方法
            Method getMethod = clz.getMethod("get" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1));

            //处理
            List<Object> checkForDuplicate = list.stream().map(e -> {
                Object o = null;
                try {
                    o = getMethod.invoke(e);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return o;
            }).collect(Collectors.toList());

            Map<Object, Object> unitMap = new HashMap<>();
            for (Integer i = 0; i < checkForDuplicate.size(); i++) {
                Object name = checkForDuplicate.get(i);
                if (Objects.isNull(name)) {
                    sb.append(columnName + " in row " + (i + 1) + " should not be null; ");
                } else {
                    if (Objects.isNull(unitMap.get(name))) {
                        unitMap.put(name, i + 1);
                    } else {
                        sb.append(columnName + " values "+ name +" in row " + (i + 1) + " is duplicated with row " + unitMap.get(name) + "; ");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
