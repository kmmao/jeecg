package cn.com.linkwide.common.callwebservice;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

/**
 * webservice 请求模型
 * @author asus
 *
 */
public class WebServiceModel {
	/**
	 * 请求参数名
	 */
	private String parameterName;
	
	/**
	 * 请求参数值
	 */
	private Object parameterValue;
	
	/**
	 * 参数类型		参照org.apache.axis.encoding.XMLType
	 * 示例：
	 * 		string	XMLType.XSD_STRING
	 * 		boolean	XMLType.XSD_BOOLEAN
	 * 		double	XMLType.double
	 * 		float	XMLType.float
	 * 		int	XMLType.int
	 * 		integer	XMLType.integer
	 * 		long	XMLType.long
	 * 		short	XMLType.short
	 * 		byte	XMLType.byte
	 * 		decimal	XMLType.decimal
	 */
	private QName parameterType;
	
	/**
	 * 输出输入		参照javax.xml.rpc.ParameterMode
	 * 示例:
	 * 		in	ParameterMode.IN	输入
	 * 		out	ParameterMode.OUT	输出
	 */
	private ParameterMode inputAndOutput;

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public QName getParameterType() {
		return parameterType;
	}

	public void setParameterType(QName parameterType) {
		this.parameterType = parameterType;
	}

	public ParameterMode getInputAndOutput() {
		return inputAndOutput;
	}

	public void setInputAndOutput(ParameterMode inputAndOutput) {
		this.inputAndOutput = inputAndOutput;
	}

	public Object getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(Object parameterValue) {
		this.parameterValue = parameterValue;
	}
	
	
	
}
