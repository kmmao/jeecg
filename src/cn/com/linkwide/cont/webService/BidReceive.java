package cn.com.linkwide.cont.webService;
import java.io.IOException;
import java.io.StringWriter;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.encoding.XMLType;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

public class BidReceive {
	
 
	public static String queryList(){ 
		String endpoint = "http://127.0.0.1:8080/hrp/webservice/bidInformation?wsdl";
		String result = "call failed!"; 
		String namespace = "http://webService.cont.linkwide.com.cn/";
		String methodName = "insertBidInformation";
		String soapActionURL = "http://webService.cont.linkwide.com.cn/insertBidInformation";
		Service service = new Service();
		Call call;
		try {
			call = (Call) service.createCall();  
			call.setTargetEndpointAddress(endpoint); 
			call.setUseSOAPAction(true);
			call.setSOAPActionURI(soapActionURL);
			call.setOperationName(new QName(namespace, methodName));
			// 设置参数名  
			call.addParameter(new QName(namespace,"name"),   // 参数名  
					XMLType.XSD_STRING, // 参数类型:String  
					ParameterMode.IN);  // 参数模式：'IN' or 'OUT'   
			// 设置返回值类型  
			call.setReturnType(XMLType.XSD_STRING); // 返回值类型：String  
			//String ctype  = "<Request><TranCode>11001</TranCode></Request>";  
			String ctype  = "<?xml version='1.0' encoding='UTF-8'?><hrpdata><operation>update</operation><datas><data><ORG_CODE>1001</ORG_CODE><DEPARTNAME>消化内科</DEPARTNAME><PARENTDEPARTID>4028889b67c93ebf0167c9480098000a</PARENTDEPARTID><IFLATER>1</IFLATER></data><data><ORG_CODE>1002</ORG_CODE><DEPARTNAME>呼吸内科</DEPARTNAME><PARENTDEPARTID>4028889b67c93ebf0167c9480098000a</PARENTDEPARTID><IFLATER>1</IFLATER></data></datas></hrpdata>";  
			result = (String) call.invoke(new Object[] { ctype});
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String queryList2(){//172.17.195.130 
        String xmlInput  = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><hrpdata><operation>update</operation><datas><data><ORG_CODE>1001</ORG_CODE><DEPARTNAME>消化内科</DEPARTNAME><PARENTDEPARTID>4028889b67c93ebf0167c9480098000a</PARENTDEPARTID><IFLATER>1</IFLATER></data><data><ORG_CODE>1002</ORG_CODE><DEPARTNAME>呼吸内科</DEPARTNAME><PARENTDEPARTID>4028889b67c93ebf0167c9480098000a</PARENTDEPARTID><IFLATER>1</IFLATER></data></datas></hrpdata>";
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        String wsUrl = "http://127.0.0.1:8080/hrp/webservice/bidInformation?wsdl";
        String method = "insertBidInformation";//webservice的方法名

        Client client = dcf.createClient(wsUrl);
        Object[] res = null;
        try {
            res = client.invoke(method, xmlInput);//调用webservice
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "";
	 
}

	public static void main(String[] args) {
		System.out.println(BidReceive.queryList());
//		List<WebServiceModel> modeList = new ArrayList<WebServiceModel>();
//		WebServiceModel mode = new WebServiceModel();
//		mode = new WebServiceModel();
//		mode.setParameterName("name");
//		mode.setParameterValue("zhangsan");
//		mode.setParameterType(XMLType.XSD_STRING);
//		mode.setInputAndOutput(ParameterMode.IN);
//		modeList.add(mode);
		
		//CallHisWebServiceHttp.multipleParameterAccessInterface("queryFmBillUseList", modeList, XMLType.XSD_STRING);
		
//		CallWebServiceHttp.multipleParameterAccessInterface("HelloWorld", modeList, XMLType.XSD_STRING);
		//String str = TTT.queryList();
		//System.out.println(TTT.queryList());
		/*String str = "<?xml version=\"1.0\" encoding=\"utf-16\"?><Response><TransCode>91001</TransCode><ResultCode>1</ResultCode><ErrorMsg>ORA-00923: 未找到要求的 FROM 关键字</ErrorMsg></Response>";
		try {
			Document xml = DocumentHelper.parseText(str);
			Element root=xml.getRootElement(); 
			Element code=root.element("ResultCode");
			
			System.out.println(code.getText());
			if("1".equals(code.getText())) {
				Element errorMsg=root.element("ErrorMsg");
				throw new BusinessException("接收失败!"+errorMsg.getText());
			}else{
				List<Element> items = root.element("Items").elements("Item");
				for(Element item:items){
					FmBillEntity b = new FmBillEntity(); 
					item.element("billTypeCode").getText();
					item.element("billTypeName").getText();
					item.element("billCodePrefix").getText();
					item.element("billCode").getText();
					item.element("billApplyUser").getText();
					item.element("usePerson").getText();
					item.element("useMoney").getText();
					item.element("useDate").getText();
					item.element("billStatus").getText();
					item.element("destoryDate").getText();
					
					b.setBillCode(item.element("billCode").getText());
					b.setBillTypeCode(item.element("billTypeCode").getText());
					b.setBillTypeName(item.element("billTypeName").getText());
					b.setBillCodePrefix(item.element("billCodePrefix").getText());
					b.setUseName(item.element("billApplyUser").getText());// 领用人,即申请人
					b.setExpendUse(item.element("usePerson").getText());
					b.setBillMoney(item.element("useMoney").getText());
					try {
						b.setExpendDate(new SimpleDateFormat("yyyy-mm-dd").parse(item.element("useMoney").getText()));
						b.setDestoryDate(new SimpleDateFormat("yyyy-mm-dd").parse(item.element("destoryDate").getText()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					b.setBillStatus(item.element("billStatus").getText());
					
					String sql = "update fm_bill set expend_use='"+b.getExpendUse()+"',bill_money='"+b.getBillMoney()+"'   where bill_type_code='"+b.getBillTypeCode()+"' and to_char(create_date,'yyyy') = to_char(sysdate,'yyyy')";
					FmBillMainEntity t = fmBillMainService.get(FmBillMainEntity.class, fmBillMain.getId());
					MyBeanUtils.copyBeanNotNull2Bean(b, t);
					fmBillMainService.saveOrUpdate(t);
					
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}*/
		/* SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		 //System.out.println(sdf.format(new Date()));
		 //System.out.println(DateUtils.calculationDate(sdf,new ,null,null,null));
		//System.out.println(DateUtils.calculationDate(new SimpleDateFormat("yyyyMMddHHmmss"),"2018-12-18 12:21:11", null, null, null));
		 Calendar cl = Calendar.getInstance();
		 cl.setTime(new Date());
		 cl.add(Calendar.MINUTE,-5);
		 sdf.format(cl.getTime());
		 System.out.println(DateUtils.yyyymmddhhmmss.format(cl.getTime()));*/
	}
	
	public static class MessageKit {
	    public static String map2xml(Map<String, String> map) throws IOException {
	        Document d = DocumentHelper.createDocument();
	        Element root = d.addElement("response");
	        Set<String> keys = map.keySet();
	        for(String key:keys) {
	            root.addElement(key).addText(map.get(key));
	        }
	        StringWriter sw = new StringWriter();
	        XMLWriter xw = new XMLWriter(sw);
	        xw.setEscapeText(false);
	        xw.write(d);
	        return sw.toString();
	    }
	}



    @Test
    public void testMsg() {
       /* try {
            Map<String,String> maps = new HashMap<String, String>();
            maps.put("123", "abc");
            maps.put("bcd", "222");
            maps.put("bcd", "<abc>ddd</abc>");
            System.out.println(MessageKit.map2xml(maps));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    	String aa = "00001";
    	String bb = "000100";
    	//System.out.println(Integer.parseInt(bb)-Integer.parseInt(aa)+1);
    	//result=String.format("%0"+3+"d",1);
    	
    	String aa1 = String.format("%0"+10+"d",122);
    	System.out.println(aa1);
    	//System.out.println(String.format("%0"+3+"d","122"));
    	//System.out.println(haoAddOne("11"));
    }
    private static final String STR_FORMAT = "000000000000";
	public static String haoAddOne(String liuShuiHao){
		Integer intHao = Integer.parseInt(liuShuiHao);
		intHao++;
		DecimalFormat df = new DecimalFormat(STR_FORMAT);
		return df.format(intHao);
	}
}
