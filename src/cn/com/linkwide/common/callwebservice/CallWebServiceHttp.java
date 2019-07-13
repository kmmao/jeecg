package cn.com.linkwide.common.callwebservice;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.util.StringUtil;

/**
 * apache.axis 直接调用webservice接口
 * 李伟华：2018-12-14 改造;改造原因之前没有考虑到多个供应商提供接口没有给接口标识;实现功能spring-mvc中填写接口标识、接口路径、命名空间
 * @author 李伟华
 *
 */
public class CallWebServiceHttp {
	
	/**
	 * 接口发布地址标识模块名称
	 */
	private static Map<String,String> endpointMap = new HashMap<String,String>();
	/**
	 * 接口命令空间标识模块名称
	 */
	private static Map<String,String> namespaceMap = new HashMap<String,String>();
	
	/**
	 * 单个参数请求
	 * @param	identifying	接口标识
	 * @param	methodName	方法名
	 * @param	webService	请求参数实体
	 * @param	returnType	接口返回类型xml返回String
	 * @return
	 */
	public static String singleParameterAccessInterface(String identifying,String methodName,WebServiceModel webService, QName returnType) {
		verifyThatTheRequestIsSuccessful(endpointMap.get(identifying));
		return callAxisPostWeb(endpointMap.get(identifying), namespaceMap.get(identifying), methodName, null, webService, returnType);
	}
	
	/**
	 * 多个参数请求
	 * @param	identifying	接口标识
	 * @param methodName 	方法名
	 * @param list		  	请求参数
	 * @param returnType	接口返回类型xml返回String
	 * @return
	 */
	public static String multipleParameterAccessInterface(String identifying,String methodName,List<WebServiceModel> list, QName returnType) {
		verifyThatTheRequestIsSuccessful(endpointMap.get(identifying));
		return callAxisPostWeb(endpointMap.get(identifying), namespaceMap.get(identifying), methodName, list, null, returnType);
	}
	/**
	 * 单个参数请求返回xml对象
	 * @param	identifying	接口标识
	 * @param	methodName	方法名
	 * @param	webService	请求参数实体
	 * @param	returnType	接口返回类型xml返回String
	 * @return
	 * @throws DocumentException 
	 */
	public static Document singleParameterAccessInterfaceReturnXML(String identifying,String methodName,WebServiceModel webService, QName returnType) throws DocumentException {
		verifyThatTheRequestIsSuccessful(endpointMap.get(identifying));
		return DocumentHelper.parseText(callAxisPostWeb(endpointMap.get(identifying), namespaceMap.get(identifying), methodName, null, webService, returnType));
	}
	
	/**
	 * 多个参数请求返回xml对象
	 * @param	identifying	接口标识
	 * @param methodName 	方法名
	 * @param list		  	请求参数
	 * @param returnType	接口返回类型xml返回String
	 * @return
	 * @throws DocumentException 
	 */
	public static Document multipleParameterAccessInterfaceReturnXML(String identifying,String methodName,List<WebServiceModel> list, QName returnType) throws DocumentException {
		verifyThatTheRequestIsSuccessful(endpointMap.get(identifying));
		return DocumentHelper.parseText(callAxisPostWeb(endpointMap.get(identifying), namespaceMap.get(identifying), methodName, list, null, returnType));
	}
	/**
	 * 单个参数请求   ---默认
	 * @param	methodName	方法名
	 * @param	webService	请求参数实体
	 * @param	returnType	接口返回类型xml返回String
	 * @return
	 */
	public static String singleParameterAccessInterface(String methodName,WebServiceModel webService, QName returnType) {
		verifyThatTheRequestIsSuccessful(endpointMap.get("U8"));
		return callAxisPostWeb(endpointMap.get("U8"), namespaceMap.get("U8"), methodName, null, webService, returnType);
	}
	
	/**
	 * 多个参数请求---默认
	 * @param methodName 	方法名
	 * @param list		  	请求参数
	 * @param returnType	接口返回类型xml返回String
	 * @return
	 */
	public static String multipleParameterAccessInterface(String methodName,List<WebServiceModel> list, QName returnType) {
		verifyThatTheRequestIsSuccessful(endpointMap.get("U8"));
		return callAxisPostWeb(endpointMap.get("U8"), namespaceMap.get("U8"), methodName, list, null, returnType);
	}
	/**
	 * 单个参数请求返回xml对象---默认
	 * @param	methodName	方法名
	 * @param	webService	请求参数实体
	 * @param	returnType	接口返回类型xml返回String
	 * @return
	 * @throws DocumentException 
	 */
	public static Document singleParameterAccessInterfaceReturnXML(String methodName,WebServiceModel webService, QName returnType) throws DocumentException {
		verifyThatTheRequestIsSuccessful(endpointMap.get("U8"));
		return DocumentHelper.parseText(callAxisPostWeb(endpointMap.get("U8"), namespaceMap.get("U8"), methodName, null, webService, returnType));
	}
	
	/**
	 * 多个参数请求返回xml对象---默认
	 * @param methodName 	方法名
	 * @param list		  	请求参数
	 * @param returnType	接口返回类型xml返回String
	 * @return
	 * @throws DocumentException 
	 */
	public static Document multipleParameterAccessInterfaceReturnXML(String methodName,List<WebServiceModel> list, QName returnType) throws DocumentException {
		//verifyThatTheRequestIsSuccessful(endpointMap.get("U8"));
		//return DocumentHelper.parseText(callAxisPostWeb(endpointMap.get("U8"), namespaceMap.get("U8"), methodName, list, null, returnType));
		return DocumentHelper.parseText("<root><entry><success>1</success><message>停止访问U8</<message>></entry></root>");
	}

	/**
	 * 请求webservice接口
	 * 
	 * @param url
	 *            接口发布地址
	 * @param namespace
	 *            命名空间
	 * @param methodName
	 *            方法名
	 * @param list
	 *            传入WebServiceModel list 或者  WebServiceModel
	 * @param returnType
	 *            接口返回的数量类型不填默认String
	 * @return
	 */
	private static String callAxisPostWeb(String url, String namespace, String methodName, List<WebServiceModel> list,
			WebServiceModel webService, QName returnType) {
		Service service = new Service();
		Object[] callParameter = null;
		String result = "";
		Call call;
		try {
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(url);
			call.setUseSOAPAction(true);
			call.setSOAPActionURI(namespace + methodName);
			call.setOperationName(new QName(namespace, methodName));
			// 判断是否多参数
			if(StringUtil.isNotEmpty(list)) {
				callParameter = new Object[list.size()];
				WebServiceModel webServiceModel = null;
				for (int i = 0; i < list.size(); i++) {
					webServiceModel = list.get(i);
					// 声明参数
					call.addParameter(new QName(namespace, webServiceModel.getParameterName()), // 参数名
							webServiceModel.getParameterType(), // 参数类型:String
							webServiceModel.getInputAndOutput()); // 参数模式：'IN' or 'OUT'
					// 数组添加参数值
					callParameter[i]=webServiceModel.getParameterValue();
				}
			}else if(StringUtil.isNotEmpty(webService)) {
				// 添加参数值
				callParameter = new Object[]{webService.getParameterValue()};
				// 声明参数
				call.addParameter(new QName(namespace, webService.getParameterName()), // 参数名
						webService.getParameterType(), // 参数类型:String
						webService.getInputAndOutput()); // 参数模式：'IN' or 'OUT'
			}else {
				return null;//都不满足
			}
			// 设置返回值类型
			call.setReturnType(returnType); // 返回值类型
			result = (String) call.invoke(callParameter);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return result;
	}
	//###Type:---add--- #### reason：----增加校验接口是否可以正常访问业务---- **-李伟华-**#### *-*-开始
	public static void verifyThatTheRequestIsSuccessful(String endpoint) {
		try {
			URL realUrl = new URL(endpoint);
			HttpURLConnection httpUrlConn = (HttpURLConnection) realUrl.openConnection();
            // 设置连接主机超时（单位：毫秒）//设置超时时间为5分钟
            httpUrlConn.setConnectTimeout(300000);
            // 设置从主机读取数据超时（单位：毫秒）
            httpUrlConn.setReadTimeout(300000);
            int statusCode = httpUrlConn.getResponseCode();
            if(statusCode!=200) {
            	throw new BusinessException("无法调用接口,请查询配置接口发布的地址是否正确!");
            }
		}  catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException("调用接口出现错误,请配置接口地址.");
		}
	}
	//###Type:---add--- #### reason：----增加校验接口是否可以正常访问业务---- **-李伟华-**#### *-*-结束

	public static Map<String, String> getEndpointMap() {
		return endpointMap;
	}

	public static Map<String, String> getNamespaceMap() {
		return namespaceMap;
	}

	public static void setEndpointMap(Map<String, String> endpointMap) {
		CallWebServiceHttp.endpointMap = endpointMap;
	}

	public static void setNamespaceMap(Map<String, String> namespaceMap) {
		CallWebServiceHttp.namespaceMap = namespaceMap;
	}

	
	
}
