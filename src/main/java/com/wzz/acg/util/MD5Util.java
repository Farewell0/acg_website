package com.wzz.acg.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    private static String byteArrayToHexString(byte[] b){
        StringBuffer resultSb =new StringBuffer();
        for (byte aB : b) {
            resultSb.append(byteToHexString(aB));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b){
        int n = b;
        if(n < 0)   n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    private static String MD5Encode(String origin, String charsetName){
        String resultString = null;
        try {
            resultString = origin;
            MessageDigest md =MessageDigest.getInstance("MD5");
            if(charsetName == null || "".equals(charsetName)){
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            }else{
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetName)));
            }
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return resultString.toUpperCase();
    }

    public static String MD5EncodeUtf8(String origin){
        origin = origin + PropertiesUtil.getProperty("password.salt", "farewell8yt834y89yer8y.;");
        return MD5Encode(origin, "utf-8");
    }

//    public static void main(String[] args) {
//        System.out.println(MD5Util.MD5EncodeUtf8("test04"));
//    }
}
