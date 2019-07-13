package cn.com.linkwide.common.util.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.Security;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.util.DateUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class ValiLicense {
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

	//校验license有效性
	public static String jmzs(String zsxx) throws Exception {
		String flag = "ok";

		//判断证书文件是否存在
		if (new String(zsxx).trim().equals("")) {
			flag = "License无效，请获取正确的License!";
			return flag;
		}

		//先base解密
		byte[] byteArray = ValiLicense.decryptBASE64(new String(zsxx));

		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		final byte[] keyBytes = { 0x11, 0x22, 0x4F, 0x58, (byte) 0x88, 0x10,
				0x40, 0x38, 0x28, 0x25, 0x79, 0x51, (byte) 0xCB, (byte) 0xDD,
				0x55, 0x66, 0x77, 0x29, 0x74, (byte) 0x98, 0x30, 0x40, 0x36,
				(byte) 0xE2 };// 24字节的密钥

		//DES3解密
		byte[] srcBytes = DES3.decryptMode(keyBytes, byteArray);

		String vlInfo = new String(srcBytes);
		String comFlag = vlInfo.substring(0, 7);

		if (!comFlag.equals("lingwei")) {
			flag = "License无效，请获取正确的License!";
			return flag;
		}

		//判断此KEY的license是否到期
		//license到期时间
		String endDate = vlInfo.substring(24, 32);

		//系统时间
		String sysDate = DateUtils.date2Str(new SimpleDateFormat("yyyyMMdd"));

		//系统时间大于到期时间，license已到期
		if (Integer.parseInt(sysDate) > Integer.parseInt(endDate)) {
			flag = "License已到期，请获取新的License!";
			return flag;
		}

		//mac地址判断
		String mac = vlInfo.substring(7, 24);

		String macAddress = new MacAddress().getMACAddress();

		if (!macAddress.equals(mac)) {
			flag = "服务器没有获得授权!";
		}

		return flag;
	}

	//校验license有效性
	public static String jmzsFromFile(HttpServletRequest request)throws Exception{
		String flag = "ok";
		String realPath = request.getSession().getServletContext().getRealPath("/") + "/upload/ls/sqm.txt";// 文件的硬盘真实路径

		File file = new File(realPath); // 要读取以上路径的input。txt文件  
		
		if (!file.exists()) {
			flag = "License无效，请获取正确的License!";
			return flag;
		}else{
			// 建立一个输入流对象reader  
			InputStreamReader reader = new InputStreamReader(new FileInputStream(realPath)); 
			
			// 建立一个对象，它把文件内容转成计算机能读懂的语言  
			BufferedReader br = new BufferedReader(reader); 
			
			String zsxx = "";
			zsxx = br.readLine();
			
			if(zsxx == null || zsxx.equals("")){
				flag = "License无效，请获取正确的License!";
				return flag;
			}
			
			//先base解密
			byte[] byteArray = ValiLicense.decryptBASE64(new String(zsxx));

			Security.addProvider(new com.sun.crypto.provider.SunJCE());
			final byte[] keyBytes = { 0x11, 0x22, 0x4F, 0x58, (byte) 0x88, 0x10,
					0x40, 0x38, 0x28, 0x25, 0x79, 0x51, (byte) 0xCB, (byte) 0xDD,
					0x55, 0x66, 0x77, 0x29, 0x74, (byte) 0x98, 0x30, 0x40, 0x36,
					(byte) 0xE2 };// 24字节的密钥

			//DES3解密
			byte[] srcBytes = DES3.decryptMode(keyBytes, byteArray);

			String vlInfo = new String(srcBytes);
			String comFlag = vlInfo.substring(0, 7);

			if (!comFlag.equals("lingwei")) {
				flag = "License无效，请获取正确的License!";
				return flag;
			}

			//判断此KEY的license是否到期
			//license到期时间
			String endDate = vlInfo.substring(24, 32);

			//系统时间
			String sysDate = DateUtils.date2Str(new SimpleDateFormat("yyyyMMdd"));

			//系统时间大于到期时间，license已到期
			if (Integer.parseInt(sysDate) > Integer.parseInt(endDate)) {
				flag = "License已到期，请获取新的License!";
				return flag;
			}

			//mac地址判断
			String mac = vlInfo.substring(7, 24);

			String macAddress = new MacAddress().getMACAddress();

			if (!macAddress.equals(mac)) {
				flag = "服务器没有获得授权!";
			}
			
		}


		return flag;
	}
}
