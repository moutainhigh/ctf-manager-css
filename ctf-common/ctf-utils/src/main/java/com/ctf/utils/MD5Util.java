package com.ctf.utils;


import cn.hutool.core.codec.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * MD5 加密
 */
public class MD5Util {

    public static String md5(String value) {
        try {
            byte[] md5s = MessageDigest.getInstance("MD5").digest(value.getBytes("UTF-8"));
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5s.length; i++) {
                int val = ((int) md5s[i]) & 0xff;
                if (val < 16)
                    hexValue.append("0");
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Should not happen", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Should not happen", e);
        }
    }


    public static String md5UpperCase(String value) {
        return md5(value).toUpperCase();
    }

    public static String createLinkString(Map<String, Object> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = String.valueOf(params.get(key));

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }

    public static String md5NoSystem(String str){
        try {
            byte[] md5s = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
            return Base64.encode(md5s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String s = md5UpperCase("123456");
        System.out.println(s);
    }

}
