package com.emx.platform.utils;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public abstract class EncryptCode {
    public static final String KEY_SHA = "SHA";
    public static final String KEY_MD5 = "MD5";
    public static final String KEY_DES = "DES";

    /**
     * MAC算法可选以下多种算法
     *
     * <pre>
     * HmacMD5
     * HmacSHA1
     * HmacSHA256
     * HmacSHA384
     * HmacSHA512
     * </pre>
     */
    public static final String KEY_MAC = "HmacMD5";

    /*
     *
     */
    public static final String ALGORITHM_DES = "DES/CBC/PKCS5Padding";
    public static final String SALT="BD!@#123";

    /*
     * DES加密
     */
    public static String encryptDES(String data){
        if(data == null) return null;
        try{
            DESKeySpec desKeySpec=new DESKeySpec(SALT.getBytes());
            SecretKeyFactory keyFactory=SecretKeyFactory.getInstance(KEY_DES);
            Key secretKey=keyFactory.generateSecret(desKeySpec);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
            IvParameterSpec iv = new IvParameterSpec("12345678".getBytes());
            AlgorithmParameterSpec parameterSpec=iv;
            cipher.init(Cipher.ENCRYPT_MODE,secretKey,parameterSpec);
            byte[] bytes=cipher.doFinal(data.getBytes());
            return byte2hex(bytes);
        }catch(Exception e){
            LogUtil.error(EncryptCode.class, e.getMessage());
            return data;
        }
    }
    /*
     * DES解密
     */

    public static String decryptDES(String data){
        if(data == null) return null;
        try {
            DESKeySpec desKeySpec=new DESKeySpec(SALT.getBytes());
            SecretKeyFactory keyFactory=SecretKeyFactory.getInstance(KEY_DES);
            Key secretKey=keyFactory.generateSecret(desKeySpec);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
            IvParameterSpec iv = new IvParameterSpec("12345678".getBytes());
            AlgorithmParameterSpec parameterSpec=iv;
            cipher.init(Cipher.ENCRYPT_MODE,secretKey,parameterSpec);
            return new String(cipher.doFinal(hex2byte(data.getBytes())));
        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.error(EncryptCode.class, e.getMessage());
            return data;
        }
    }
    /*
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        Decoder decoder = Base64.getDecoder();
        byte[] buffer = decoder.decode(key);
        return buffer;
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        Encoder encoder = Base64.getEncoder();
        String encode = encoder.encodeToString(key);
        return encode;
    }

    /**
     * MD5加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String encryptMD5(String data) throws Exception {

        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(data.getBytes("UTF-8"));

        byte[] byteArray = md5.digest();
        StringBuffer md5StrBuff = new StringBuffer();

        //将加密后的byte数组转换为十六进制的字符串,否则的话生成的字符串会乱码
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1){
                md5StrBuff.append("0").append(
                        Integer.toHexString(0xFF & byteArray[i]));
            }else{
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }

        return md5StrBuff.toString();

    }

    public static String encryptMD5String(String str) throws UnsupportedEncodingException, Exception{
        try{
            return encryptMD5(str);
        }catch(Exception exception){
            throw exception;
        }
    }

    /**
     * SHA加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encryptSHA(byte[] data) throws Exception {

        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        sha.update(data);

        return sha.digest();

    }

    /**
     * 初始化HMAC密钥
     *
     * @return
     * @throws Exception
     */
    public static String initMacKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);

        SecretKey secretKey = keyGenerator.generateKey();
        return encryptBASE64(secretKey.getEncoded());
    }

    /**
     * HMAC加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptHMAC(byte[] data, String key) throws Exception {

        SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);

        return mac.doFinal(data);

    }

    private static String byte2hex(byte[] bs){
        StringBuilder hs = new StringBuilder();
        String stmp;
        for(int n = 0; bs != null && n < bs.length; n++){
            stmp = Integer.toHexString(bs[n] & 0XFF);
            if(stmp.length() == 1) hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }

    private static byte[] hex2byte(byte[] b){
        if((b.length % 2) != 0) throw new IllegalArgumentException();
        byte[] b2 = new byte[b.length/2];
        for(int n = 0;n < b.length; n+=2){
            String item = new String(b, n, 2);
            b2[n/2]=(byte)Integer.parseInt(item, 16);
        }
        return b2;
    }
}
