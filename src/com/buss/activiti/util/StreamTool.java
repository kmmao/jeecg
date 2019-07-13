package com.buss.activiti.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamTool {
	/**
	 * inputStream 转 String
	 * @param is
	 * @return
	 */
	public static String convertStreamToString(InputStream is) {      
		 try {  
            ByteArrayOutputStream boa=new ByteArrayOutputStream();  
            int len=0;  
            byte[] buffer=new byte[1024];  
              
            while((len=is.read(buffer))!=-1){  
                boa.write(buffer,0,len);  
            }  
            is.close();  
            boa.close();  
            byte[] result=boa.toByteArray();  
              
            String temp=new String(result);  
              
            //识别编码  
            if(temp.contains("UTF-8")){  
                return new String(result,"utf-8");  
            }else if(temp.contains("gb2312")){  
                return new String(result,"gb2312");  
            }else{  
                return new String(result,"utf-8");  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }   
	}
}
