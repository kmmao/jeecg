package cn.com.linkwide.common.util.controller;

/*字符串DESede(3DES)加密*/
//注：百度代码识别BUG，代码中空格属全角。拷贝代码之后请自行修改。


import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DES3 {
	
    /**    
     * BASE64解密   
     * @param key          
     * @return          
     * @throws Exception          
     */              
    public static byte[] decryptBASE64(String key) throws Exception {               
        return (new BASE64Decoder()).decodeBuffer(key);               
    }  
    
    
    /**         
    * BASE64加密   
    * @param key          
    * @return          
    * @throws Exception          
     */              
    public static String encryptBASE64(byte[] key) throws Exception {               
          return (new BASE64Encoder()).encodeBuffer(key);               
    } 
	private static final String Algorithm = "DESede"; // 定义加密算法,可用

	// DES,DESede,Blowfish
	// keybyte为加密密钥，长度为24字节
	// src为被加密的数据缓冲区（源）
	public static byte[] encryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			// 加密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	// keybyte为加密密钥，长度为24字节
	// src为加密后的缓冲区
	public static byte[] decryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			// 解密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	// 转换成十六进制字符串
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
			if (n < b.length - 1) {
				hs = hs + ":";
			}
		}
		return hs.toUpperCase();
	}

	/**将二进制转换成16进制 
	 * @param buf 
	 * @return 
	 */

	public static String parseByte2HexStr(byte buf[]) {  
	        StringBuffer sb = new StringBuffer();  
	        for (int i = 0; i < buf.length; i++) {  
	                String hex = Integer.toHexString(buf[i] & 0xFF);  
	                if (hex.length() == 1) {  
	                        hex = '0' + hex;  
	                }  
	                sb.append(hex.toUpperCase());  
	        }  
	        return sb.toString();  
	}
	
	/**将16进制转换为二进制 
	* @param hexStr 
	* @return 
	 */ 

	public static byte[] parseHexStr2Byte(String hexStr) {  
        if (hexStr.length() < 1)  
                return null;  
        byte[] result = new byte[hexStr.length()/2];  
        for (int i = 0;i< hexStr.length()/2; i++) {  
                int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
                int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
                result[i] = (byte) (high * 16 + low);  
        }  
        return result;  
} 

	
	public static void main(String[] args) {

		// 添加新安全算法,如果用JCE就要把它添加进去
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		final byte[] keyBytes = { 0x11, 0x22, 0x4F, 0x58, (byte) 0x88, 0x10,
				0x40, 0x38, 0x28, 0x25, 0x79, 0x51, (byte) 0xCB, (byte) 0xDD,
				0x55, 0x66, 0x77, 0x29, 0x74, (byte) 0x98, 0x30, 0x40, 0x36,
				(byte) 0xE2 };// 24字节的密钥




		try {
			String szSrc = "nongheC8-1F-66-47-5F-A720151232";
//			System.out.println("加密前的字符串:" + szSrc);
			 
			byte[] encoded = encryptMode(keyBytes, szSrc.getBytes());
//			System.out.println("加密后的字符串:" + new String(encoded));
			 
			String ss = encryptBASE64(encoded);
//			System.out.println("base64加密后:" + ss);
			
			byte[] byteArray = decryptBASE64("OTI1QjZDMDNCQjYwNEU0QUYw");     
//			System.out.println("base64解密后："+new String(byteArray));
			
			byte[] srcBytes = decryptMode(keyBytes, byteArray);
//			System.out.println("解密后的字符串:" + (new String(srcBytes)));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
