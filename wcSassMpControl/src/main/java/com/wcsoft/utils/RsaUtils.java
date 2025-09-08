package com.wcsoft.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * @ClassDescription: 加密解密工具类
 * @Author：李白
 * @Date：2023/3/22 13:05
 */
public class RsaUtils {
    /**
     * 常量字符串
     */
    private static final String RSA = "RSA";
    
    /**
     * 获取密钥对象
     * @param keySize RSA算法模长(个人理解应该是模长越大加密安全性越高，但加密过程可能也越长)
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static List<Key> getRsaObject(int keySize) throws NoSuchAlgorithmException {
        //创建list用来接收公钥对象和私钥对象
        List<Key> keyList = new ArrayList<>();
        //创建RSA密钥生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
        //设置密钥大小，RSA算法的模长=最大加密数据的大小
        keyPairGenerator.initialize(keySize);
        //调用函数生成公钥私钥对象（以对生成密钥）
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //获取公钥放入list
        keyList.add(keyPair.getPublic());
        //获取私钥放入list
        keyList.add(keyPair.getPrivate());
        //返回list
        return keyList;
    }
	/**
	 * 微信支付取到的rsa证书格式是： 公钥格式PKCS#1  ，需要把证书转换成,PKCS#8
	 * 命令 ： openssl rsa -RSAPublicKey_in -in <filename> -pubout
	 * pem 公钥 加载
	 * @return
	 * @throws Exception
	 */
    public static PublicKey GeneratePublicKeyFromPem(String cerFilePath) throws Exception {
     
        try {
        	BufferedReader br = new BufferedReader(new FileReader(cerFilePath));
        	String s = br.readLine();
        	StringBuffer publickey = new StringBuffer();
        	s = br.readLine();
        	while (s.charAt(0) != '-') {
        		publickey.append(s + "\r");
        		s = br.readLine();
        	}
        	System.out.println("publickey="+publickey);
        	byte[] keybyte = org.apache.commons.codec.binary.Base64.decodeBase64(publickey.toString());
        	KeyFactory kf = KeyFactory.getInstance("RSA");
        	X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keybyte);
        	PublicKey publicKey = kf.generatePublic(keySpec);
         
            return publicKey;
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * 微信支付使用的RSA 加密方式
     * @param str
     * @return
     */
    public static String RsaEn(String str){
    	String result=null;
    	try{
    		PublicKey  publicKey=GeneratePublicKeyFromPem(str);  
        	Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-1ANDMGF1PADDING");
        	
        	cipher = Cipher.getInstance("RSA");  
            cipher.init(Cipher.ENCRYPT_MODE, publicKey); //公钥加密  
            byte[] encrypt = cipher.doFinal(str.getBytes());  
            
            	
            
        	result=org.apache.commons.codec.binary.Base64.encodeBase64String(encrypt);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return result;
    }
    /**
     * 生成公钥私钥的字符串
     * @param keySize 模长
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static List<String> getRsaKeyString(int keySize) throws NoSuchAlgorithmException {
        //创建list用来接收公钥对象和私钥对象
        List<String> keyList = new ArrayList<>();
        //创建RSA密钥生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
        //设置密钥大小，RSA算法的模长=最大加密数据的大小
        keyPairGenerator.initialize(keySize);
        //调用函数生成公钥私钥对象（以对生成密钥）
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //将公钥对象转换为字符串通过base64加密
        String publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
        //将私钥对象转换为字符串通过base64加密
        String privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
        //获取公钥放入list
        keyList.add(publicKey);
        //获取私钥放入list
        keyList.add(privateKey);
        //返回list
        return keyList;
    }

    /**
     * 通过公钥字符串生成公钥对象（RSAPublicKey类型）
     * X509EncodeKeySpec方式（字符串公钥转为RSAPublicKey公钥）
     * @param publicKeyStr  公钥字符串
     * @return 返回RSAPublicKey类型的公钥对象
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static RSAPublicKey getRSAPublicKeyByX509(String publicKeyStr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //密钥工厂创建
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        //公钥字符解密为bytes数组
        byte[] keyBytes = Base64.getDecoder().decode(publicKeyStr);
        //公钥字符串转x509
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        //x509转RSAPublicKey
        return (RSAPublicKey) keyFactory.generatePublic(x509EncodedKeySpec);
    }

    /**
     * 通过私钥字符串生成私钥对象（RSAPrivateKey类型）
     * PKCS8EncodedKeySpec方式（字符串私钥转为RSAPrivateKey公钥）
     * @param privateKey 私钥字符串
     * @return 返回RSAPrivateKey类型的私钥对象
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static RSAPrivateKey getRSAPrivateKeyByPKCS8(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //密钥工厂创建
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        //私钥字符串解密为bytes数组
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);
        //私钥字符串转pkcs8
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        //pkcs8转RSAPrivateKey
        return (RSAPrivateKey) keyFactory.generatePrivate(pkcs8EncodedKeySpec);
    }

    /**
     * 公钥加密
     * @param message 需要加密的信息
     * @param rsaPublicKey rsa公钥对象
     * @return 返回信息被加密后的字符串
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws IOException
     */
    public static String encryptByPublicKey(String message, RSAPublicKey rsaPublicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
        //RSA加密实例
        Cipher cipher = Cipher.getInstance(RSA);
        //初始化公钥
        cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
        //模长转为字节数
        int modulusSize = rsaPublicKey.getModulus().bitLength()/8;
        //PKCS PADDING长度为11字节，解密数据是除去这11byte
        int maxSingleSize = modulusSize-11;
        //切分字节数，每段不大于maxSingleSize
        byte[][] dataArray = splitArray(message.getBytes(), maxSingleSize);
        //字节数组输出流
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //分组加密，加密后内容写入输出字节流
        for (byte[] s : dataArray){
            byteArrayOutputStream.write(cipher.doFinal(s));
        }
        //使用base64将字节数组转为string类型
        return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
    }
    
    /**
     * 私钥解密密
     * @param encryptedMessage 信息加密后的字符串
     * @param rsaPrivateKey rsa私钥对象
     * @return 返回解密后的字符串
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws IOException
     */
    public static String decryptByPrivateKey(String encryptedMessage, RSAPrivateKey rsaPrivateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
        //RSA加密实例
        Cipher cipher = Cipher.getInstance(RSA);
        //初始化公钥
        cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
        //加密算法模长
        int modulusSize = rsaPrivateKey.getModulus().bitLength()/8;
        byte[] dataBytes = encryptedMessage.getBytes();
        //加密做了转码，这里也要用base64转回来
        byte[] decodeData = Base64.getDecoder().decode(dataBytes);
        //切分字节数，每段不大于maxSingleSize
        byte[][] dataArray = splitArray(decodeData, modulusSize);
        //字节数组输出流
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //分组解密，解密后内容写入输出字节流
        for (byte[] s : dataArray){
            byteArrayOutputStream.write(cipher.doFinal(s));
        }
        //使用base64将字节数组转为string类型
        return new String(byteArrayOutputStream.toByteArray());
    }
    
    /**
     * 按指定长度切分数组
     * @param byteArrayInfo 需要切分的byte数组
     * @param maxSize 单个字节数组长度
     * @return
     */
    private static byte[][] splitArray(byte[] byteArrayInfo, int maxSize){
        int dataLen = byteArrayInfo.length;
        if(dataLen<=maxSize){
            return new byte[][]{byteArrayInfo};
        }
        byte[][] result = new byte[(dataLen-1)/maxSize+1][];
        int resultLen = result.length;
        for (int i = 0; i < resultLen; i++) {
            if(i==resultLen-1){
                int sLen = dataLen-maxSize*i;
                byte[] single = new byte[sLen];
                System.arraycopy(byteArrayInfo, maxSize*i, single, 0, sLen);
                result[i] = single;
                break;
            }
            byte[] single = new byte[maxSize];
            System.arraycopy(byteArrayInfo, maxSize*i, single, 0, maxSize);
            result[i] = single;
        }
        return result;
    }

}

