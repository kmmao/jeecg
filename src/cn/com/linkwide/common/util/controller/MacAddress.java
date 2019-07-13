package cn.com.linkwide.common.util.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;

import org.apache.log4j.Logger;

public class MacAddress {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(MacAddress.class);

    /**
    * 获取当前操作系统名称. return 操作系统名称 例如:windows xp,linux 等.
    */
    public static String getOSName() {
        return System.getProperty("os.name").toLowerCase();
    }

    /**
    * 获取unix网卡的mac地址. 非windows的系统默认调用本方法获取. 如果有特殊系统请继续扩充新的取mac地址方法.
    *
    * @return mac地址
    */
    public static String getUnixMACAddress() {

        String mac = null;
        BufferedReader bufferedReader = null;
        Process process = null;
        try {
            // linux下的命令，一般取eth0作为本地主网卡
            process = Runtime.getRuntime().exec("ifconfig eth0");

            // 显示信息中包含有mac地址信息
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            int index = -1;

            while ((line = bufferedReader.readLine()) != null) {
                // 寻找标示字符串[hwaddr]
                index = line.toLowerCase().indexOf("hwaddr");

                if (index >= 0) {// 找到了
                    // 取出mac地址并去除2边空格
                    mac = line.substring(index + "hwaddr".length() + 1).trim();
                    break;
                }
            }
        } catch (IOException e) {
            logger.error("unix/linux方式未获取到网卡地址");
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            bufferedReader = null;
            process = null;
        }
        return mac;
    }

    /**
    * windows 7 专用 获取MAC地址
    *
    * @return
    * @throws Exception
    */
    public static String getWindows7MACAddress() throws Exception {

        //获取本地IP对象
        InetAddress ia = InetAddress.getLocalHost();

        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < mac.length; i++) {
            if (i != 0) {
                sb.append("-");
            }

            String s = Integer.toHexString(mac[i] & 0xFF);
            sb.append(s.length() == 1 ? 0 + s : s);

        }

        return sb.toString().toUpperCase();
    }

    public String getMACAddress() throws Exception {
        String mac = "";
        String os = getOSName();

        if (os.startsWith("windows")) {
            mac = getWindows7MACAddress();
        } else {
            // 本地是非windows系统 一般就是unix  
            mac = getUnixMACAddress();
        }

        return mac.toUpperCase();
    }

    public static boolean isNull(Object strData) {
        if (strData == null || String.valueOf(strData).trim().equals("")) {
            return true;
        }
        return false;
    }

}
