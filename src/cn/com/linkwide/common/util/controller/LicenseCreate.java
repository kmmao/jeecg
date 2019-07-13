package cn.com.linkwide.common.util.controller;

import java.security.Security;



public class LicenseCreate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String zsxx = new LicenseCreate().getNewLicense();

		try {
			String flag;

			flag = ValiLicense.jmzs(zsxx);

			System.out.println(flag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getNewLicense() {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		final byte[] keyBytes = { 0x11, 0x22, 0x4F, 0x58, (byte) 0x88, 0x10,
				0x40, 0x38, 0x28, 0x25, 0x79, 0x51, (byte) 0xCB, (byte) 0xDD,
				0x55, 0x66, 0x77, 0x29, 0x74, (byte) 0x98, 0x30, 0x40, 0x36,
				(byte) 0xE2 };

		String newLicense = "";

		//加密前数据 前缀、mac、有效期
		String szSrc = "lingwei70-4D-7B-64-36-8920180926";

		//3DES加密
		byte[] encoded = DES3.encryptMode(keyBytes, szSrc.getBytes());
		String base64Result = "";

		try {
			//BASE64加密
			base64Result = DES3.encryptBASE64(encoded);
			newLicense = base64Result;
			System.out.println(base64Result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return newLicense;
	}
}
