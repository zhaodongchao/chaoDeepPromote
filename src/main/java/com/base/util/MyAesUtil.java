package com.base.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Description: AES加解密辅助类,键值长度16位否则截取或补充
 */
public class MyAesUtil {
    private static final Log logger = LogFactory.getLog(MyAesUtil.class);
    private final static String SPECMODE = "AES";
    private final static String CHARSE = "UTF-8";
    private static final String BLOCKPADSTR = "ABCDEFGHIJKLMNOP";//QRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
    private final static String VALIDCODEKEY = "NEW1TOU2CH003ABC4";
    private final static String VALIDCODEIV = "abc1def2ghi3jkl4";
    /**
     * 私有构造函数，阻止实例化
     */
    private MyAesUtil(){
    }

    private static String padBlock(String key){
        int nameLength = key.length();
        if (nameLength > 16) {
            return key.substring(0, 16);
        }
        String result = key + BLOCKPADSTR.substring(nameLength);

        return result;
    }

    /**
    * Description 根据键值进行加密
    * @param data
    * @param key  加密键字符串,长度16位否则截取或补充
    * @return
    * @throws Exception
    */
    public static String encrypt(String data, String key) throws Exception {
        key = MyAesUtil.padBlock(key);

        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(CHARSE), SPECMODE);
        Cipher cipher = Cipher.getInstance(SPECMODE);
        cipher.init(1, skeySpec);

        return byte2hex(cipher.doFinal(data.getBytes(CHARSE))).toLowerCase();
    }
    private static String byte2hex(byte[] bytes) {
        String hex = ""; String tmp = "";

        for (int n = 0; n < bytes.length; ++ n) {
            tmp = Integer.toHexString(bytes[n] & 0xFF);
            if (tmp.length() == 1)
                hex = hex + "0" + tmp;
            else
                hex = hex + tmp;
        }

        return hex.toUpperCase();
    }

    /**
     * Description 根据键值进行解密
     * @param data
     * @param key  加密键字符串,长度16位否则截取或补充
     * @return
     * @throws Exception
     */
    public static String decrypt(String data, String key) throws Exception  {
        key = MyAesUtil.padBlock(key);

        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(CHARSE), SPECMODE);
        Cipher cipher = Cipher.getInstance(SPECMODE);
        cipher.init(2, skeySpec);

        byte[] encrypted = MyAesUtil.hex2byte(data);

        return new String(cipher.doFinal(encrypted), CHARSE);
    }
    private static byte[] hex2byte(String hex) {
        if (hex == null) {
            return null;
        }
        int l = hex.length();
        if (l % 2 == 1) {
            return null;
        }
        byte[] b = new byte[l / 2];
        for (int i = 0; i != l / 2; ++i) {
            b[i] = (byte)Integer.parseInt(hex.substring(i * 2, i * 2 + 2), 16);
        }
        return b;
    }

    /**
     * 生成AES加密串，使用AES算法，CBC模式
     * @param text 需加密文本
     * @param key 加密密钥文本,定长为16字符（128位）
     * @param iv 增强向量文本
     * @return
     * @throws Exception
     */
    public static String encodeAesByCBC(String text, String key, String iv) throws Exception {
        if (key == null){
            logger.info("key is null.");
            return "";
        }
        if (key.length() != 16){
            logger.info("key length is not eq 16.");
            return "";
        }

        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(CHARSE), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(iv.getBytes(CHARSE)));

        byte[] encrypted = cipher.doFinal(text.getBytes(CHARSE));

        return byte2hex(encrypted).toLowerCase();
    }

    public static String decodeAesByCBC(String data, String key, String iv) throws Exception{
        if (key == null){
            logger.info("key is null.");
            return "";
        }
        if (key.length() != 16){
            logger.info("key length is not eq 16.");
            return "";
        }
        

        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(CHARSE), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(iv.getBytes(CHARSE)));

        byte[] encrypted = MyAesUtil.hex2byte(data);

        return new String(cipher.doFinal(encrypted), CHARSE);
    }

    public static String encodeValidCode(String code) throws Exception{
        String result = "";
        if (code == null || code.length() == 0){
            return "";
        }
        result = encodeAesByCBC(code, VALIDCODEKEY, VALIDCODEIV);
        code = decodeAesByCBC(code,"deng123456789012","1234560405060708");

        return result;
    }
    public static String decodeValidCode(String code) throws Exception{
        String result = "";
        if (code == null || code.length() == 0){
            return "";
        }

        result = decodeAesByCBC(code, VALIDCODEKEY, VALIDCODEIV);

        return result;
    }
    ////////////////////////////////////////////////////////////////////////////////
    /*
    public static String mYencoe(String content, String key) throws Exception{
        byte[] input = content.getBytes(CHARSE);

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] thedigest = md.digest(key.getBytes(CHARSE));
        SecretKeySpec skc = new SecretKeySpec(thedigest, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec("1234560405060708".getBytes(CHARSE));
        cipher.init(Cipher.ENCRYPT_MODE, skc, iv);

        byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
        int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
        ctLength += cipher.doFinal(cipherText, ctLength);

        return parseByte2HexStr(cipherText);
    }
    private static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    // 加密
    public static String encode(String sSrc, String sKey) throws Exception {
        String key = sKey;
        if (sKey == null) {
            key = "";
        }
        byte[] enCodeFormat;
            KeyGenerator kgen = KeyGenerator.getInstance("AES");//实例化AES密钥生成器
            kgen.init(128, new SecureRandom(key.getBytes(CHARSE)));//根据密钥明文初始化128位密钥生成器
            //kgen.init(new ECGenParameterSpec("ECB"), new SecureRandom(key.getBytes(CHARSE)));//根据密钥明文初始化128位密钥生成器
//            kgen.init(key.length(), new SecureRandom(key.getBytes(CHARSE)));//根据密钥明文初始化128位密钥生成器
            SecretKey secretKey = kgen.generateKey();//生成密钥
            enCodeFormat = secretKey.getEncoded();//获取密钥字节信息
        SecretKeySpec skeySpec = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"
        IvParameterSpec iv = new IvParameterSpec("1234560405060708".getBytes(CHARSE));//使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes(CHARSE));

        return byte2hex(encrypted).toLowerCase();
    }
    public static String encode2(String sSrc, String sKey) throws Exception {
        String key = sKey;
        if (sKey == null)  key = "";
        byte[] enCodeFormat;
        KeyGenerator kgen = KeyGenerator.getInstance("AES");//实例化AES密钥生成器
        kgen.init(new SecureRandom(key.getBytes(CHARSE)));//根据密钥明文初始化128位密钥生成器
        SecretKey secretKey = kgen.generateKey();//生成密钥
        enCodeFormat = secretKey.getEncoded();//获取密钥字节信息
        SecretKeySpec skeySpec = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"
        IvParameterSpec iv = new IvParameterSpec("1234560405060708".getBytes(CHARSE));//使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes(CHARSE));

        return byte2hex(encrypted).toLowerCase();//此处使用BAES64做转码功能，同时能起到2次加密的作用。
    }
    public static String encode3(String sSrc, String sKey) throws Exception {
        String key = sKey;
        if (sKey == null)  key = "";
        byte[] enCodeFormat;
        KeyGenerator kgen = KeyGenerator.getInstance("AES");//实例化AES密钥生成器
        kgen.init(new SecureRandom(key.getBytes(CHARSE)));//根据密钥明文初始化128位密钥生成器
        SecretKey secretKey = kgen.generateKey();//生成密钥
        enCodeFormat = secretKey.getEncoded();//获取密钥字节信息
        SecretKeySpec skeySpec = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES/CFB/PKCS5Padding");//"算法/模式/补码方式"
        IvParameterSpec iv = new IvParameterSpec("1234560405060708".getBytes(CHARSE));//使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes(CHARSE));

        return byte2hex(encrypted).toLowerCase();//此处使用BAES64做转码功能，同时能起到2次加密的作用。
    }
    private static String PkcsPad(String data) throws Exception{
        String result = data;

        int blockSize = 4;
        int blockSizeBytes = blockSize * 4;

        int nPaddingBytes = blockSizeBytes - data.length() % blockSizeBytes;

        int paddingWord = (nPaddingBytes << 24) | (nPaddingBytes << 16) | (nPaddingBytes << 8) | nPaddingBytes;

        for (int i = 0; i < nPaddingBytes; i += 4) {
            result = result + Integer.toHexString(paddingWord);
        }

        return result;
    }*/

    public static void main(String[] args) {
    	String suername = "c583918397d1f7b4706ea81ff51ed4bd";
    	String key = "9899904930976290" ;
    	String vi = "0258268808958441" ;
    	try {
    		System.out.println(MyAesUtil.encodeAesByCBC("赵东朝", key, vi));
			System.out.println(MyAesUtil.decodeAesByCBC(suername, key, vi));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
