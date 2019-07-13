package cn.com.linkwide.cont.poiImport;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.linkwide.ba.bacustomer.entity.BaCustomerEntity;
import cn.com.linkwide.ba.supplier.entity.LBaSupplierEntity;
import cn.com.linkwide.cont.coninformation.controller.ConInformationController;
import cn.com.linkwide.cont.coninformation.entity.ConInformationEntity;
import cn.com.linkwide.cont.coninformation.service.ConInformationServiceI;



@Controller
@RequestMapping("/poiIImort")
public class PoiIImport { 
	private static final Logger logger = Logger.getLogger(ConInformationController.class);

	@Autowired
	private ConInformationServiceI conInformationService;
	@Autowired
	private SystemService systemService;
	/**
     * POI导出word
     * @throws Exception
     */
    @RequestMapping(params="exportApplyForm")
     public void exportApplyForm(HttpServletRequest request, HttpServletResponse response) throws Exception { 
    	String id=request.getParameter("id");
    	String modelCode=request.getParameter("modelCode");
    	String modeName="";
    	if("01".equals(modelCode)){
    		modeName="YLSB-1";
    	}if("02".equals(modelCode)){
    		modeName="YLSB-2";
    	}
    	if("03".equals(modelCode)){
    		modeName="YLSB-3";
    	}
    	if("04".equals(modelCode)){
    		modeName="RJCG-1";
    	}
    	if("05".equals(modelCode)){
    		modeName="DZSB-1";
    	}
    	if("06".equals(modelCode)){
    		modeName="ZRJRCL-1";
    	}
    	if("07".equals(modelCode)){
    		modeName="ZRJRCL-2";
    	}
    	if("08".equals(modelCode)){
    		modeName="YLQX-1";
    	}
    	if("09".equals(modelCode)){
    		modeName="YLQX-2";
    	}
    	if("10".equals(modelCode)){
    		modeName="YLQX-3";
    	}
    	if("11".equals(modelCode)){
    		modeName="YLQX-4";
    	}
    	if("12".equals(modelCode)){
    		modeName="YLWC-1";
    	}
    	if("13".equals(modelCode)){
    		modeName="YLWC-2";
    	}
    	if("14".equals(modelCode)){
    		modeName="YLSJ-1";
    	}
    	if("15".equals(modelCode)){
    		modeName="YLSJ-2";
    	}
    	if("16".equals(modelCode)){
    		modeName="BGYP-1";
    	}
    	if("17".equals(modelCode)){
    		modeName="BGYP-2";
    	}
    	if("18".equals(modelCode)){
    		modeName="BGYP-3";
    	}
    	if("19".equals(modelCode)){
    		modeName="BGYP-4";
    	}
    	if("20".equals(modelCode)){
    		modeName="BGYP";
    	}
    	if("21".equals(modelCode)){
    		modeName="YLSB-7";
    	}
    	if("22".equals(modelCode)){
    		modeName="YLSB-8";
    	}
    	if("23".equals(modelCode)){
    		modeName="YLSB-9";
    	}
    	if("24".equals(modelCode)){
    		modeName="DZSB-10";
    	}
    	if("25".equals(modelCode)){
    		modeName="RJSB-11";
    	}
    	if("26".equals(modelCode)){
    		modeName="RJSB-12";
    	}
    	if("27".equals(modelCode)){
    		modeName="GZDL-1";
    	}
    	if("28".equals(modelCode)){
    		modeName="HTBG-1";
    	}
    	if("29".equals(modelCode)){
    		modeName="DZHC-29";
    	}
    	if("30".equals(modelCode)){
    		modeName="ZZXY-1";
    	}
    	if("31".equals(modelCode)){
    		modeName="YLSB-XSB";
    	}
    	if("31".equals(modelCode)){
    		modeName="FJWZ-1";
    	}
    	if("32".equals(modelCode)){
    		modeName="BX-1";
    	}
    	if("33".equals(modelCode)){
    		modeName="PJ-1";
    	}
    	if("34".equals(modelCode)){
    		modeName="WX-1";
    	}
    	if("35".equals(modelCode)){
    		modeName="JX-1";
    	}
    	if("36".equals(modelCode)){
    		modeName="SBBQ-1";
    	}
    	if("37".equals(modelCode)){
    		modeName="ZCPG-1";
    	}
    	LBaSupplierEntity ba=null;
    	
    	ConInformationEntity t = conInformationService.get(ConInformationEntity.class,id);
    /*	if(StringUtil.isNotEmpty(t.getOtherCompy())){
    		 ba=systemService.get(LBaSupplierEntity.class, t.getOtherCompy());
    	}*/
		try {
			String sql=" select a.mark_name markName,a.mark_spece markSpece,a.mark_number markNumber,a.mark_price markPrice,b.type_name typeName ,a.mark_money markMoney,a.zczname zczname,a.sczs sczs,a.mark_ms markMs,dept_name deptName,' ' bst  from con_mark a left join  l_ba_material_mater_unit b  on a.MARK_UNIT=b.TYPE_CODE  where a.con_main_id='"+id+"'";
		List<Map<String, Object>> markList=systemService.findForJdbc(sql, null);
  /*	  List<Map<String, Object>> markList = new ArrayList<>();
          for (int i =0;i < 2; i++){
              Map<String,Object> map = new HashMap<>();
              map.put("${markName}", "电脑");
              map.put("${markSpece}", "联想");
              map.put("${markNumber}", "10");
              map.put("${markPrice}", "7000");
              map.put("${markMoney}", "70000");
              markList.add(map);
          }*/
		
		
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
        Map<String, Object> params = new HashMap<String, Object>(); 
        if("22".equals(modelCode)){
        	for (Map<String, Object> map : markList) {
        		 params.put("name",t.getOtherEmp() );  
                 params.put("otherCompy", t.getOtherCompy());
                 params.put("contNmber", t.getConNo().toString());
                 params.put("tel", t.getSpl()==null?" ":t.getSpl());
                 params.put("bidCode", t.getBidNo()==null?" ":t.getBidNo());
                 params.put("signDate",sdf.format(t.getSignedDate())==null?" ":sdf.format(t.getSignedDate()) );
                 params.put("markName", map.get("markName"));
                 params.put("markSpece", map.get("markSpece"));
                 params.put("markNumber", map.get("markNumber"));
                 params.put("sczs", map.get("sczs"));
                 
			}
		}else if("31".equals(modelCode)){
			BaCustomerEntity baCustomerEntity = systemService.findUniqueByProperty(BaCustomerEntity.class, "id", t.getOtherCompy());
			 params.put("otherCompy", baCustomerEntity!=null?baCustomerEntity.getCustomerFullName():"");
			 params.put("yf", baCustomerEntity!=null?baCustomerEntity.getCustomerFullName():"");
		}else{
            params.put("name",t.getOtherEmp() );  
            params.put("otherCompy", t.getOtherCompy());
            params.put("contNmber", t.getConNo().toString());
            params.put("tel", t.getSpl()==null?" ":t.getSpl());
            params.put("bidCode", t.getBidNo()==null?" ":t.getBidNo());
            params.put("signDate",sdf.format(t.getSignedDate())==null?" ":sdf.format(t.getSignedDate()) );
		}
            XwpfTUtil xwpfTUtil = new XwpfTUtil();  
            XWPFDocument doc;  
   /*         String fileNameInResource = "sta.docx";  */
            InputStream is; 
            String tomcatPath = request.getSession().getServletContext().getRealPath("/") ;
            is = new FileInputStream(tomcatPath+"\\webpage\\cn\\com\\linkwide\\cont\\poiImportTom\\"+modeName+".docx");  
           /* is = getClass().getClassLoader().getResourceAsStream(fileNameInResource);      //本身就在编译路径下。。。。*/ 
           doc = new XWPFDocument(is);  
           xwpfTUtil.replaceInPara(doc, params);  
            //替换表格里面的变量  
        if(!"22".equals(modelCode)){
        if(!"28".equals(modelCode)){
        if(!"30".equals(modelCode)){
        	if(!"20".equals(modelCode)){
            xwpfTUtil.export(doc,markList,0);
        	}
        }
        }
        }
        /*  xwpfTUtil.replaceInTable(doc, params);*/
            OutputStream os = response.getOutputStream();  
            response.setContentType("application/vnd.ms-excel");  
            response.setHeader("Content-disposition", "attachment;filename="+new String(t.getConName().getBytes("GBK"),"ISO8859-1")+".docx"); 
          //  response.setHeader("Content-disposition","attachment;filename=YLSB.docx");  
            doc.write(os);  
      
            xwpfTUtil.close(os);  
            xwpfTUtil.close(is);  
	
            os.flush();  
            os.close(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
        }
		
}