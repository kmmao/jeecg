package cn.com.linkwide.common.bdyx;

import com.pkuhit.iihip.sdk.message.Client;

public class ClientHolder {
	private static Client client;
	
	public static Client getClient() {
		if(client == null) {
			init();
		}
		return client;
	}
	
	public static void init() {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Client c = client = Client.SimpleClient("172.16.36.29", // 服务器地址
							8999, // 服务器端口号
							"S018", // 系统ID
							"ylzS018", // 账号
							"123456" // 密码
							);
					client = c;
				} catch (Exception e) {
					// 初始化客户端失败
					e.printStackTrace();
				}		
			}
		});
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("客户端初始化完毕");
	}
	
}

