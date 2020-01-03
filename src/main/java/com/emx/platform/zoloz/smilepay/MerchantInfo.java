package com.emx.platform.zoloz.smilepay;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bruce on 2018/6/15.
 */

public class MerchantInfo {
    //这里三个值请填写自己真实的值
    //应用的签名私钥
    public final static String appKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCWR7jHceyanA8tkDojHFcpRtZPhw5cFON1od0XIjeFRCT/+GYEJVyngovEWOmKDeIBI2seq3iQ7HaWAvp8xFulMmU4ISe/+iOc+0W3SbdaePDDZenZ4oWs1v/TlOHs/qbqr1MIQ5tGixasuProWEj5wfzhF2QvjZMNE0mOWCUDg8tqtDIVDbGlt9JjscdEPH1W9h2//sjKfdK8UDk9HLoaZyhrpEMwuNjCsp+v/qBX7VqJe49zQvLJ/nhOZpjGXziQuBxlv+sIQ/XlzwlOz6PWCncdiEWcfS8LZnyphM4CcFdWziKhso24Bb5JtWTbuNBLigyZ9M9GrcDyhC00p6X3AgMBAAECggEBAJCkO38LcbVl2135YVuF3YaG/aLR1Mpf+IWx40Bu2I1WB16vUFFRPJqllazH/w+3SP3bfzCnzYV/cqvz9e+8dpDLroyVrNKkCT8Awhslwmo72U/YeXoTAqJW1ShqNgGLIFDlqr9bgsok+RAJgsW6o5JwWdq0TuNOGmkNVVDCGe4Emaa2QY4dfrOoNACzqLHEpEk2yHdBvvPG6h/5G9yS/CuAP09evkLwpSKHg5JZ3Pgh0Qy3zFv0a3NiFrg7ruRBzeMcshaFfY03plkIats5pXN3EXq3XdNeNIfdjTZ4Xmp/FtXlRL8tCfzM0cJdV0eL4bsmWvNuv0Xl0OxfQGxWXoECgYEA+NeybmbZewqbxaKDS66NgRR7P4u5LO//DM1FBK8WfOUCnWw3HJ3Jxpyk1v96HEQW/d+UCNcqaqGnvSDVB7memwyxPJa54mzLcKWI0aR7/GzcKlNifz5dAaNSruHj+OxH8LRyFOCN4p2O/7nk9pp9gmFZAg0N4rrJwJi/awMf8aECgYEAmppHsNCyWN70kgfwMiMK6uXtI7WXDaN8vo1HRXKJeH4gfL74NiS+KG7YCH9OlOdm7YpqVX7EJHt/qAVOltZh4ciWlnvFFviuR9439u0WJjFf9J3Cvv6Y7OBrL7Xk+oKeSS6WWuVgyz0tPDLxoWE5UZJEwcAfAliOAdn9uFEcIJcCgYATNXtVoGTbZac+q1vdRL0xoKYe8qc5u9EBaPsR8H4Y6Ai6pDbg46FGzqK+4fvIIM1xjYe84vcoQhBTviwTq5V9vI4V8kCpJOLTcEPOgxb7FmAseEFbNzIwxS3FyDQz6/FluFgyGl95hdYbEXuGOcjEfsVDLkTLIsYeMuxb02Ls4QKBgGs0koPso892g3eD466QmDB80e0SLbW6aXUlOIfSvbr9EwAN2MaKwgX9nvZ9ePtOXxRXCBdS+SYnMUVMsMtty6AqXSnql6evOAMD+CDVFKfVUymn//Q7TcXsdMc3xZlk2+f5Uy+ioXUtbeGxndZ/OaTz3ZPQ6m/RAmAMTqDyNaPvAoGAGwzMMuMfJPCPYSsC8ISF4jcw8P/Nm4tczSYCO3PFPtK3reAG88fMIIHWyL+nheaKUJvAHxQqtBygJE8dInh/vKwhakaueJnM7F6j9dcRxdP7IimHTnX6h3MO2RNyAy5S9ydICJpweRMYcLUbinCuob6eVVwFbivEZGcrglt1JPM=";
    //商户id
    public final static String partnerId = "TEST";
    //应用的appId
    public final static String appId = "2019121960059136";
    /**
     * mock数据，真实商户请填写真实信息.
     */
    public static Map mockInfo() {
        Map merchantInfo = new HashMap();
        //以下信息请根据真实情况填写
        //商户id
        merchantInfo.put("partnerId", partnerId);
        merchantInfo.put("merchantId", partnerId);
        //开放平台注册的appid
        merchantInfo.put("appId", appId);
        //机具编号，便于关联商家管理的机具
        merchantInfo.put("deviceNum", "TEST_ZOLOZ_TEST");
        //真实店铺号
        merchantInfo.put("storeCode", "TEST");
        //口碑店铺号
        merchantInfo.put("alipayStoreCode", "TEST");
        //品牌，传入拼音或者英文，标示该商家
        merchantInfo.put("brandCode", "TEST");

        merchantInfo.put("areaCode", "TEST");
        merchantInfo.put("geo", "0.000000,0.000000");
        merchantInfo.put("wifiMac", "TEST");
        merchantInfo.put("wifiName", "TEST");
        merchantInfo.put("deviceMac", "TEST");

        return merchantInfo;
    }

    public static void main(String[] args) {
        mockInfo();
    }
}
