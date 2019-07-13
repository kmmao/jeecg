package cn.com.linkwide.cont.webService.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.linkwide.cont.desgin.bidinformation.entity.BidInformationEntity;
import cn.com.linkwide.cont.webService.BidInformationService;


@WebService(endpointInterface="cn.com.linkwide.cont.webService.BidInformationService",serviceName="bidInformation")
public class BidInformationServiceImpl implements BidInformationService{
	@Autowired
	private SystemService systemService;
	@Override
	public String insertBidInformation(String xml) {
		Document doc = null;
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			String operation = root.elementText("operation");
			List<Element> dataList = root.element("datas").elements("data");
			List<BidInformationEntity> list =new ArrayList<BidInformationEntity>();
		for (Element element : dataList) {
			element.elementTextTrim("ORG_CODE");
			BidInformationEntity bidInformation = new BidInformationEntity();
			bidInformation.setBidCode(element.elementTextTrim("ORG_CODE"));
			bidInformation.setConBidCode(element.elementTextTrim("ORG_CODE"));
			bidInformation.setVenCode(element.elementTextTrim("ORG_CODE"));
			bidInformation.setVenName(element.elementTextTrim(""));
			bidInformation.setInvCode(element.elementTextTrim(""));
			bidInformation.setInvName(element.elementTextTrim(""));
			bidInformation.setUnitCode(element.elementTextTrim(""));
			bidInformation.setNum(element.elementTextTrim(""));
			bidInformation.setPrice(Double.valueOf(element.elementTextTrim("")));
			bidInformation.setMoney(Double.valueOf(element.elementTextTrim("")));
			bidInformation.setDeptCode("");
			bidInformation.setBudgCode(element.elementTextTrim(""));
			bidInformation.setRegisterCode(element.elementTextTrim(""));
			bidInformation.setManufacturer(element.elementTextTrim(""));
			bidInformation.setRemark(element.elementTextTrim(""));
			bidInformation.setReceiveTime(new Date(System.currentTimeMillis()));
			list.add(bidInformation);
		}
		}catch (DocumentException e) {
			e.printStackTrace();
			return "传输失败！";
		} catch (Exception e) {
			e.printStackTrace();
			return "传输失败！";
		} 
		
		return "成功了！";
	 
		}
	
}
