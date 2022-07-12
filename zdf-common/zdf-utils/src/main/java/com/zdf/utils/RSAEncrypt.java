package com.zdf.utils;

import lombok.SneakyThrows;
import org.apache.commons.net.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RSAEncrypt {

    @SneakyThrows
    public static Map<Integer,String> getGenKeyPair(){
        Map<Integer,String> map = new HashMap<>();
        KeyPairGenerator rsa = KeyPairGenerator.getInstance("RSA");
        rsa.initialize(1024,new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = rsa.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
        // 将公钥和私钥保存到Map
        map.put(0,publicKeyString);  //0表示公钥
        map.put(1,privateKeyString);  //1表示私钥
        return map;
    }

    /**
     * RSA公钥加密
     *
     * @param str
     *            加密字符串
     * @param publicKey
     *            公钥
     * @return 密文
     * @throws Exception
     *             加密过程中的异常信息
     */
    public static String encrypt( String str, String publicKey ) throws Exception{
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeBase64String(getMaxResultEncrypt(str,cipher));
        return outStr;
    }

    /**
     * RSA私钥解密
     *
     * @param str
     *            加密字符串
     * @param privateKey
     *            私钥
     * @return 铭文
     * @throws Exception
     *             解密过程中的异常信息
     */
    public static String decrypt(String str, String privateKey) throws Exception{
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(getMaxResultDecrypt(str,cipher));
        return outStr;
    }

    private static byte[] getMaxResultDecrypt(String str, Cipher cipher) throws IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        byte[] inputArray = Base64.decodeBase64(str.getBytes("UTF-8"));
        int inputLength = inputArray.length;
        // 最大解密字节数，超出最大字节数需要分组加密
        int MAX_ENCRYPT_BLOCK = 128;
        // 标识
        int offSet = 0;
        byte[] resultBytes = {};
        byte[] cache = {};
        while (inputLength - offSet > 0) {
            if (inputLength - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(inputArray, offSet, MAX_ENCRYPT_BLOCK);
                offSet += MAX_ENCRYPT_BLOCK;
            } else {
                cache = cipher.doFinal(inputArray, offSet, inputLength - offSet);
                offSet = inputLength;
            }
            resultBytes = Arrays.copyOf(resultBytes, resultBytes.length + cache.length);
            System.arraycopy(cache, 0, resultBytes, resultBytes.length - cache.length, cache.length);
        }
        return resultBytes;
    }

    private static byte[] getMaxResultEncrypt(String str, Cipher cipher) throws IllegalBlockSizeException, BadPaddingException {
        byte[] inputArray = str.getBytes();
        int inputLength = inputArray.length;
        // 最大加密字节数，超出最大字节数需要分组加密
        int MAX_ENCRYPT_BLOCK = 117;
        // 标识
        int offSet = 0;
        byte[] resultBytes = {};
        byte[] cache = {};
        while (inputLength - offSet > 0) {
            if (inputLength - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(inputArray, offSet, MAX_ENCRYPT_BLOCK);
                offSet += MAX_ENCRYPT_BLOCK;
            } else {
                cache = cipher.doFinal(inputArray, offSet, inputLength - offSet);
                offSet = inputLength;
            }
            resultBytes = Arrays.copyOf(resultBytes, resultBytes.length + cache.length);
            System.arraycopy(cache, 0, resultBytes, resultBytes.length - cache.length, cache.length);
        }
        return resultBytes;
    }

    @SneakyThrows
    public static void main(String[] args) {
        String message = "{\"asnNo\":\"194356014\",\"asnType\":\"0\",\"shiptoId\":\"\",\"shiptoName\":\"\",\"uniqueId\":\"e3405b02-a647-478e-ab36-c168337c0165\",\"forwardCode\":\"\",\"hawb\":\"\",\"shipfromId\":\"\",\"shipfromName\":\"\",\"totalVolume\":\"0.0\",\"totalWeight\":\"0.0\",\"totalCarton\":\"0.0\",\"createDate\":\"2021-06-2\",\"createTime\":\"16:32:49\",\"timezone\":\"UTC\",\"remark\":\"\",\"doaType\":\"\",\"supplierCode\":\"1000340\",\"supplierName\":\"LCFC (HeFei) Electronics Technology\",\"transportType\":\"Truck\",\"incoterm\":\"EXW\",\"pickCity\":\"合肥\",\"vendorType\":\"ODM/OEM\",\"details\":[{\"partNo\":\"5B21B36539\",\"partName\":\"主板 L 81WC WIN I310110U_UMA_4G\",\"asnQuantity\":\"4519.00000\",\"asnItemNo\":\"1\",\"isIqc\":\"1\",\"location\":\"1001\",\"plant\":\"H021\",\"isDg\":\"N\",\"bol\":\"LCFC2021060201\",\"commdityCode\":\"PL\",\"referenceOrder\":\"4501639401\",\"referenceOrderItemNo\":\"10\"}]}\n";
        Map<Integer, String> genKeyPair = getGenKeyPair();
        String pub = genKeyPair.get(0);
//        String pub = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCRWXPqg7YjVREbCr01km0QN+NFi01mNMIVh6aEkkRPFC0KJmHUxNMYm3EMDbrwJ5qolgZTuC/3QJkIXtVhIiq9UxhzlSdsUcUArY20GbcAfIHIKKC9aZ6637FKqnFa4o8JyHItuq+e6ToJMyyrSMS7MbwZVMxcxCZra2NxhlMYnQIDAQAB";
        String pri = genKeyPair.get(1);
//        String pri = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJFZc+qDtiNVERsKvTWSbRA340WLTWY0whWHpoSSRE8ULQomYdTE0xibcQwNuvAnmqiWBlO4L/dAmQhe1WEiKr1TGHOVJ2xRxQCtjbQZtwB8gcgooL1pnrrfsUqqcVrijwnIci26r57pOgkzLKtIxLsxvBlUzFzEJmtrY3GGUxidAgMBAAECgYAe+V8YZMUVtnUUvC8iEXgocqB+G3d9BlUt3WCA8+KWr4gBl7hVbTRHFCj9Fpf1wZrVvlLO2HtwR1XVUNBMCMnuhddD/fI3hy3VWSk9ZkaEa1d3F699EDz0SCI6Bx9CJYXSaWozdoqPOAGcf93E5Ux4C/8UXrLMD7kPi1ZHi7aciQJBAPdgZBsmd9glHursb1V7VUeMce1jrBXQDRu6bVIs7kZOMN+Hul353Uk3STvaNq6miqPkcHaaiFZVpLUOS2XCewsCQQCWapA0ti1iGaiD20FUT2qA9SVEwnYcq3BEFVOWMyurnVfE3gsWEJr5e9SBasi33HguGe9evdJ591tAzWps/8P3AkAYKUj9wd8YtF91sIwbPEWTDGugNQj8xGpTV/0PdqX7Je3uFzKjtSDg4s57Uw/eNRP7d/doDiXc0NaafH/uu3ULAkBW5NDR4rxtA62aHruq3QYF4ZjaFAzvmwqMClXzWzRLO+ZMUInPAWDF54doDCxQKwkyeMET1k0VMJSC5ygoN8kZAkAsOMFQ7gwNJI1xNorpbgEqKCG9bZ9HTMy6BVfbfKCHzfWepTqQKoUbbZnsihaq/5cZn7kN/aA3RmPgMcvH/sDv";
        System.out.println("随机生成的公钥为:" + pub);
        System.out.println("随机生成的私钥为:" + pri);
        String messageEn = encrypt(message,pub);
        System.out.println(message + "\t加密后的字符串为:" + messageEn);
        String messageDe = decrypt("MOblI4PDo5LYmZPqohaaZKA/qgPkMtonX8XmOEmczxf9Jk+qFVPZZTcB9BQ8YfKYoULKWFY3cbxN7KvSeXVlQ+kJEwWPJSFM7ZWeHmLG64EVzHB+3IIjgKS9ppxt97Xe/tSDQxfBAeWm09QJOSr8VJKauP4PdQJjV+PkI9F4No9rWA7tbU2r4RZoiIHDXnieILV+RVQ8A0mDUWpIpuEtZQJd0Z24g39lHUfEIb/i/OE7a9E0JG8gscTw3dtLaE1gigmC4is6IE48GB8gD74c07bgvuPqZgIVwxkf0W3oXlMCm+QGQxE/3o+D0WEAMqtHAoEu238ac20pdQK+U4mtHTiAjWZVMJ4IUE0kmkTpp6gyWAiIVUN4ZEssbL2imkupCQlFyaIZFyJIVqIWhFuo4qrNETLRo0EzFD2Rrnf6Un8pUMgFWz/UCGZ1fUJSMB4/jiHXsD8Der/ovh7YSpyD+Y8JJQNesljOfLdbIzkYxBrtrBx3IW/Eui39rY+L3JU4I7nvH/RfFMRLzeAvhIwG27OUUc/8XlbNzPX6t/kYOlE8gGRyCjQ1iABBpsewD3yh7lGqd9TSRofl5gVabydYD3r8V/cS+MTDIDWZtCcNLagHpFv2RSuJxPL590yAW//6Pxw7MP4y8HOIwrq31kzW7YH6aFGrxEcFKX1U/z4DtPSGXVw6VnsBPReP9MzceqHbHuoIqQcmTlJJURcRPouAAzOVrGGaVnUpuQmqfKZh7ydhkcr32nYSdjvRm+ldhTEchpfJdZ3aVJSuG/O4AaDcLPumEwd/GpHNDERM0h1Hlt0gKROt8NI6Rd3joEWICyr9pZC8/DlYrdrN4mfO3zZBmQoBsIAV2f1oYk5Cn3fJo3ALg4Mn+/ZmB6lz/LvcZUPuy0Cs8BX/1G/Bwh4NoBKnofzklIbbNzqY/9LWf8gQ6IixoY4Xx4yFSWZ8SzivnTHjWoVWjDjIWT0ctplK9l277iqSTwWRBz1SFNsBFEJCZMPMVms0cQWwwcvKx5wCZei+Jubss7B21BR54YXhBGHmmrWdLOaD8D1lmfBGvbbWetgu0f0MjnPK0+uQBF0NFeZ/+5x4tNvLwaZh7qbv00Xq4aATlGPypQfdxVummdsoc2s2v9AMC0VLB6wbKNKUC2hORoGfz7uGVO/fyLqaBLNcAGdzcyowD/JFS2zOSv9l/QZL+pAVC3jXqzrenBOwbcIjbN9jlOzXO+vWk8KlQJvKT6sLNbzAsXnPLcWiC3mfrHtFST5hW7bHD+yK3umPOXEwdU34MPuKOPxkNWiLw6J23tpIqjv1iXhBqM8AckAPLSLmG6SEo0wiqnskbGdz7Q0uVsRaso90TaO13UgGwvBGFDynJYtYG+eP5YYTeBWG1FsJM5a4mCKglhxilY9019Vee1u8S4Af7bPtCw41mRhuBnGv6AbvLPS6u7iMcdiklcbCKRavPUGRriR7jR9q30CldIiizZZi06kleIQspt/ZzT8zYceSrRxj0Liqv5R59gnRTrWcxx6odfXPYMOiPcTqJnSUF/MpGgUO1kBrGCn9oIS1jImTbmFeNvK3rXZKmR9ZMTRZ7ELzLdn2WQvwuwllnA1KeYxtI1n6OvoMEYepOLs45Q5I3cjlysqB3D6bMr1xPaFQROWXYSsiC0VSZCxwGa0FM432+nWSsJXbQPgHP15/Qoy0XToA7sU0reOc/cQ=",pri);
        System.out.println("还原后的字符串为:" + messageDe);
    }
}
