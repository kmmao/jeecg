package cn.com.linkwide.cont.webService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface BidInformationService {
	@WebMethod(action="http://webService.cont.linkwide.com.cn/insertBidInformation")	
	public String insertBidInformation(@WebParam(name="name",targetNamespace="http://webService.cont.linkwide.com.cn/") String xml);
}