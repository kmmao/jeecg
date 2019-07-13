package cn.com.linkwide.common.rsa;



import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Created by WangGH on 2018/7/16.
 * <p>
 * 致到达这里的勇敢的人：你是被上天选中的人，
 * <p>
 * 不眠不休修复大家最棘手的代码，
 * <p>
 * 是英勇的编程骑士。你，真正的救世主，人中之龙，
 * <p>
 * 我要对你说：永远不会放弃你，永远不要让你失望，
 * <p>
 * 永远不会跑掉，抛弃你。
 * <p>
 * 永远不会让你哭泣，永远不说再见。永远不会说谎伤害你。
 */
public class RSAUtil {

    //构建Cipher实例时所传入的的字符串，默认为"RSA/NONE/PKCS1Padding"
    private static String sTransform = "RSA/ECB/PKCS1Padding";

    //进行Base64转码时的flag设置，默认为Base64.DEFAULT
    private static int sBase64Mode = 0;
  //字符串公钥，可以直接保存在客户端  1024bit PKCS#8
    public static final String PUBLIC_KEY_STR = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDPPpaBYfMYZXJ/nfWi2CsFvy8I" +
            "I3EkP3np8hu3kCKUfbEOsT8U8rtX+axCBpJJjbBdYZrzCsxbn+5qxYf63cSJ0RTi" +
            "c1CBVrIW+VigfL/HRuIKTAFqgLbx9fHBD2/DG3tmThh++xLSkGJA03n4cYWta19Z" +
            "2vx8X3/Ch5TMlIw0UQIDAQAB";

    //字符串密钥，通常保存在服务器，这里为了方便演示，直接保存在客户端   1024bit PKCS#8
    public static final String PRIVATE_KEY_STR = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAM8+loFh8xhlcn+d" +
            "9aLYKwW/LwgjcSQ/eenyG7eQIpR9sQ6xPxTyu1f5rEIGkkmNsF1hmvMKzFuf7mrF" +
            "h/rdxInRFOJzUIFWshb5WKB8v8dG4gpMAWqAtvH18cEPb8Mbe2ZOGH77EtKQYkDT" +
            "efhxha1rX1na/Hxff8KHlMyUjDRRAgMBAAECgYEAu+KbIitB77ExL79uDTd7MZeV" +
            "NQSks18U0OKc/DshDowxNAe/D+fLrsN8xtMcVrCs3sv5ZTQ59ys0Q/pXADmDzmbr" +
            "EXfG2z+LvQIokh2mdj9SY7tiKMszLwiTtBDDkLfPXvrxZBfOU+sYtFnXzrXNT99I" +
            "ILZdRSzyTcnjnkwQHP0CQQD6pd6nr6JgVPt0+v5+SCfXjHQnqJQT6DMS3eC6djAW" +
            "myEl03h/KPk9IBU15ZnnTE0LcQ5ulJNl3dP1PjZmYOgXAkEA06t1pyHsn5mWA16/" +
            "X7l7OciJlUpzPffT75mLwCehyIPy9HyCDCLPkYz2dzXKnbvU55XPycZlogAo1S1N" +
            "7fWf1wJBAI0sudE3Er0xgIRuDsOv/ojgulfQEr93rLS2tiowAXvqGmyExLzSpjdw" +
            "h6HY1au6hTapkhhkpPTeUdEzHk7hzDcCQGEX71oA97emDefx2Dsiq+GvmVy/z+kG" +
            "+1KWzkIQa+e8jv+S9Vx3w1rynEPGivCbrmekkrHtrcUH6bMPmwWie/UCQE/Xd11F" +
            "lheXdYw/EUkY4bVQdiG2iYmd+cx1UTbRfRKLiXaj6LZ5gKoVZutyJ1jkTPzKtw0D" +
            "jjaBuO3Od4Nfpwk=";

    //初始化方法，设置参数
    public static void init(String transform,int base64Mode){
        sTransform = transform;
        sBase64Mode = base64Mode;
    }


    /*
        产生密钥对
        @param keyLength
        密钥长度，小于1024长度的密钥已经被证实是不安全的，通常设置为1024或者2048，建议2048
     */
    public static KeyPair generateRSAKeyPair(int keyLength){
        KeyPair keyPair = null;
        try {

            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            //设置密钥长度
            keyPairGenerator.initialize(keyLength);
            //产生密钥对
            keyPair = keyPairGenerator.generateKeyPair();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return keyPair;
    }

    /*
        加密或解密数据的通用方法
        @param srcData
        待处理的数据
        @param key
        公钥或者私钥
        @param mode
        指定是加密还是解密，值为Cipher.ENCRYPT_MODE或者Cipher.DECRYPT_MODE

     */
    private static byte[] processData(byte[] srcData, Key key, int mode){

        //用来保存处理结果
        byte[] resultBytes = null;

        try {

            //获取Cipher实例
            Cipher cipher = Cipher.getInstance(sTransform);
            //初始化Cipher，mode指定是加密还是解密，key为公钥或私钥
            cipher.init(mode,key);
            //处理数据
            resultBytes = cipher.doFinal(srcData);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return resultBytes;
    }


    /*
        使用公钥加密数据，结果用Base64转码
     */
    public static String encryptDataByPublicKey(byte[] srcData ){
    	PublicKey publicKey = RSAUtil.keyStrToPublicKey(PUBLIC_KEY_STR);
        byte[] resultBytes = processData(srcData,publicKey,Cipher.ENCRYPT_MODE);

        return Base64Utils.encode(resultBytes,sBase64Mode);

    }

    /*
        使用私钥解密，返回解码数据
     */
    public static byte[] decryptDataByPrivate(String encryptedData, PrivateKey privateKey){

        byte[] bytes = Base64Utils.decode(encryptedData,sBase64Mode);

        return processData(bytes,privateKey,Cipher.DECRYPT_MODE);
    }

    /*
        使用私钥进行解密，解密数据转换为字符串，使用utf-8编码格式
     */
    public static String decryptedToStrByPrivate(String encryptedData ){
    	 PrivateKey privateKey = RSAUtil.keyStrToPrivate(PRIVATE_KEY_STR);
        return new String(decryptDataByPrivate(encryptedData,privateKey));
    }

    /*
        使用私钥解密，解密数据转换为字符串，并指定字符集
     */
    public static String decryptedToStrByPrivate(String encryptedData, PrivateKey privateKey,String charset){
        try {

            return new String(decryptDataByPrivate(encryptedData,privateKey),charset);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }


    /*
        使用私钥加密，结果用Base64转码
     */

    public static String encryptDataByPrivateKey(byte[] srcData,PrivateKey privateKey){

        byte[] resultBytes = processData(srcData,privateKey,Cipher.ENCRYPT_MODE);

        return new String(Base64Utils.decode(new String(resultBytes),sBase64Mode));
    }

    /*
        使用公钥解密，返回解密数据
     */

    public static byte[] decryptDataByPublicKey(String encryptedData,PublicKey publicKey){

        byte[] bytes = Base64Utils.decode(encryptedData,sBase64Mode);

        return processData(bytes,publicKey,Cipher.DECRYPT_MODE);

    }

    /*
        使用公钥解密，结果转换为字符串，使用默认字符集utf-8
     */
    public static String decryptedToStrByPublicKey(String encryptedData,PublicKey publicKey){
        return new String(decryptDataByPublicKey(encryptedData,publicKey));
    }


    /*
        使用公钥解密，结果转换为字符串，使用指定字符集
     */

    public static String decryptedToStrByPublicKey(String encryptedData,PublicKey publicKey,String charset){
        try {

            return new String(decryptDataByPublicKey(encryptedData,publicKey),charset);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }




    /*
        将字符串形式的公钥转换为公钥对象
     */

    public static PublicKey keyStrToPublicKey(String publicKeyStr){

        PublicKey publicKey = null;

        byte[] keyBytes = Base64Utils.decode(publicKeyStr,sBase64Mode);

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

        try {

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            publicKey = keyFactory.generatePublic(keySpec);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return publicKey;

    }

    /*
        将字符串形式的私钥，转换为私钥对象
     */

    public static PrivateKey keyStrToPrivate(String privateKeyStr){

        PrivateKey privateKey = null;

        byte[] keyBytes = Base64Utils.decode(privateKeyStr,sBase64Mode);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);

        try {

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            privateKey = keyFactory.generatePrivate(keySpec);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return privateKey;

    }
    
  /*  //获取公钥      
    PublicKey publicKey = RSAUtil.keyStrToPublicKey(PUBLIC_KEY_STR);
    //获取私钥
    PrivateKey privateKey = RSAUtil.keyStrToPrivate(PRIVATE_KEY_STR);

    //需要加密的数据
    String clearText01 = "大家好，我是朱志强！";

    //公钥加密结果
    String publicEncryptedResult = RSAUtil.encryptDataByPublicKey(clearText01.getBytes());
    //私钥解密结果
    String privateDecryptedResult = RSAUtil.decryptedToStrByPrivate(publicEncryptedResult);*/
}