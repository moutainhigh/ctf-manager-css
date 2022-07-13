package com.ctf.utils;

import java.util.List;
import java.util.regex.Pattern;

public class UrlFilterUtil {

    private static String getRegPath(String path){
        char[] chars = path.toCharArray();
        int len = chars.length;
        StringBuilder sb = new StringBuilder();
        boolean preX = false;
        for (int i = 0; i <len ; i++) {
            if (chars[i] == '*'){
                if (preX){
                    sb.append(".*");
                    preX = false;
                }else if (i + 1 == len){
                    sb.append("[^/]*");
                }else {
                    preX = true;
                    continue;
                }
            }else{
                if (preX){
                    sb.append("[^/]*");
                    preX = false;
                }
                if (chars[i] == '?'){
                    sb.append('.');
                }else {
                    sb.append(chars[i]);
                }
            }
        }
        return sb.toString();
    }


    private static boolean filterUrls(String excludePath,String reqUrl){
        String regPath = getRegPath(excludePath);
        return Pattern.compile(regPath).matcher(reqUrl).matches();
    }


    public static boolean checkWhiteList(List<String> excludeUrls, String reqUrl){
        for (String url : excludeUrls){
            if (filterUrls(url,reqUrl)){
                return true;
            }
        }
        return false;
    }

}
