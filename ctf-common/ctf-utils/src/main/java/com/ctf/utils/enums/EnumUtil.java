package com.ctf.utils.enums;

import cn.hutool.core.util.StrUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnumUtil {

    /**
     *
     * @param clazz 操作枚举
     * @param name  操作列
     * @return
     */
    public static List<String> getNameList(Class<Enum> clazz,String name){
        List<String> list = new ArrayList<>();
        Enum[] enumConstants = clazz.getEnumConstants();
        for(int i = 0 ;i<enumConstants.length;i++){
            Enum enumConstant = enumConstants[i];
            Class<? extends Enum> aClass = enumConstant.getClass();
            try {
                Method method = aClass.getMethod("get"+name.substring(0, 1).toUpperCase() + name.substring(1), null);
                Object invoke = method.invoke(enumConstant);
                list.add(invoke.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(enumConstant.name());
        }
        return list;
    }


    /**
     *
     * @param clazz
     * @param name1
     * @param name2
     * @param byName 可以为空
     * @return
     */
    public static List<Map<String,Object>> getToMap(Class<Enum> clazz,String name1,String name2,String byName){
        List<Map<String,Object>> list = new ArrayList<>();
        Enum[] enumConstants = clazz.getEnumConstants();
        for(int i = 0 ;i<enumConstants.length;i++){
            Map<String,Object> map = new HashMap<>();
            Enum enumConstant = enumConstants[i];
            Class<? extends Enum> aClass = enumConstant.getClass();
            try {
                Method nameMethod1 = aClass.getDeclaredMethod("get" + name1.substring(0, 1).toUpperCase() + name1.substring(1));
                Method nameMethod2 = aClass.getDeclaredMethod("get" + name2.substring(0, 1).toUpperCase() + name2.substring(1));
                String s = nameMethod1.invoke(enumConstant).toString();
                if(StrUtil.isNotEmpty(byName) && !s.equals(byName)){
                    continue;
                }
                map.put("name",nameMethod2.invoke(enumConstant));
                map.put("value",nameMethod2.invoke(enumConstant));
                list.add(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<Map<String,Object>> toMapByName(Class<Enum> clazz,String name1,String name2,String byName){
        List<Map<String,Object>> list = new ArrayList<>();
        Enum[] enumConstants = clazz.getEnumConstants();
        for(int i = 0 ;i<enumConstants.length;i++){
            Map<String,Object> map = new HashMap<>();
            Enum enumConstant = enumConstants[i];
            Class<? extends Enum> aClass = enumConstant.getClass();
            try {
                Method nameMethod1 = aClass.getDeclaredMethod("get" + name1.substring(0, 1).toUpperCase() + name1.substring(1));
                Method nameMethod2 = aClass.getDeclaredMethod("get" + name2.substring(0, 1).toUpperCase() + name2.substring(1));
                map.put(nameMethod1.invoke(enumConstant).toString(),nameMethod2.invoke(enumConstant));
                list.add(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static void main(String[] args) {

    }
}
