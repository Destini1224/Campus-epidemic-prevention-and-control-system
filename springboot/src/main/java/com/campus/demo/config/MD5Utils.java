package com.campus.demo.config;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Description：MD5加密
 * Param：
 * return：
 * Author：Dengshiting
 * Date：2023/3/28
 */

public class MD5Utils {
    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    private static final String salt = "1a2b3c4d";

    public static String inputPassToFormPass(String inputPass) {
        String str = ""+salt.charAt(0)+salt.charAt(2) + inputPass +salt.charAt(5) + salt.charAt(4);
        System.out.println(str);
        return md5(str);
    }

    public static String formPassToDBPass(String formPass, String salt) {
        String str = ""+salt.charAt(0)+salt.charAt(2) + formPass +salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDbPass(String inputPass, String saltDB) {
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDBPass(formPass, saltDB);
        return dbPass;
    }
//    //编写主类进行测试
//    public static void main(String args[]) {
//        String s = "Kodak";
//        System.out.println("原始：" + s);
//
//        System.out.println("加密后："+MD5Utils.inputPassToFormPass(s));
//
//    }
}
