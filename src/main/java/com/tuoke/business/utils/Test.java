package com.tuoke.business.utils;

import com.baomidou.mybatisplus.core.toolkit.AES;

/**
 * @program: idea_work
 * @description:
 * @author: ng
 * @create: 2020-06-03 21:58
 **/
public class Test {

    public static void main(String[] args) {
        // 生成 16 位随机 AES 密钥
        String randomKey = AES.generateRandomKey();
        System.out.println("秘钥: "+randomKey);

        // 随机密钥加密
        System.out.println(AES.encrypt("jdbc:h2:mem:test", randomKey));
        System.out.println(AES.encrypt("root", randomKey));
        System.out.println(AES.encrypt("test", randomKey));
    }
}
