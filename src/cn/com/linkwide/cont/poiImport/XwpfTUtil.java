package cn.com.linkwide.cont.poiImport;  
  
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.jeecgframework.core.util.StringUtil;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;  
  
public class XwpfTUtil {  
    private String tempLocalPath;
    private XWPFDocument xwpfDocument = null;
    private FileInputStream inputStream = null;
    private OutputStream outputStream = null;
    
    
    /**
     *  设置模板路径
     * @param tempLocalPath
     */
    public void setTempLocalPath(String tempLocalPath) {
        this.tempLocalPath = tempLocalPath;
    }
    /**
     *  初始化
     * @throws IOException
     */
    public void init(InputStream inputStream) throws IOException{
        
        xwpfDocument = new XWPFDocument(inputStream);
    }

    /**
     *  导出方法
     * @param params
     * @param tableIndex
     * @return
     * @throws Exception
     */
    public boolean export(XWPFDocument dc, List<Map<String,Object>> params, int tableIndex) throws Exception{
        this.insertValueToTable(dc,params,tableIndex);
        return true;
    }
  
	 /**
     * 循环填充表格内容
     * @param xwpfDocument
     * @param params
     * @param tableIndex
     * @throws Exception
     */
    private   void insertValueToTable(XWPFDocument xwpfDocument, List<Map<String,Object>> params, int tableIndex) throws Exception {
        List<XWPFTable> tableList = xwpfDocument.getTables();
        if(tableList.size()<=tableIndex){
            throw new Exception("tableIndex对应的表格不存在");
        }
        XWPFTable table = tableList.get(tableIndex);
        List<XWPFTableRow> rows = table.getRows();
        if(rows.size()<2){
            throw new Exception("tableIndex对应表格应该为2行");
        }
        //模板的那一行
        XWPFTableRow tmpRow = rows.get(1);
        //备注哪一行
        XWPFTableRow tmpRows = rows.get(2);
        List<XWPFTableCell> tmpCells = null;
        List<XWPFTableCell> cells = null;
        XWPFTableCell tmpCell = null;
        tmpCells = tmpRow.getTableCells();


        String cellText = null;
        String cellTextKey = null;
        Map<String,Object> totalMap = null;
      
        for (int i = 0, len = params.size(); i < len; i++) {
            Map<String,Object> map = params.get(i);
            Map<String,Object> new_map=new HashMap<String, Object>();
        
            // 创建新的一行
            XWPFTableRow row = table.createRow();
            // 获取模板的行高 设置为新一行的行高
            row.setHeight(tmpRow.getHeight());
            cells = row.getTableCells();
            for (int k = 0, klen = cells.size(); k < klen; k++) {
                tmpCell = tmpCells.get(k);
                XWPFTableCell cell = cells.get(k);
                cellText = tmpCell.getText();
                if (StringUtils.isNotBlank(cellText)) {
                	
                    //转换为mapkey对应的字段
                  //
                
                	 cellTextKey = cellText.replace("$", "").replace("{", "").replace("}", "");
                    if (map.containsKey(cellTextKey)) {
                        // 填充内容 并且复制模板行的属性
                    	//this.replaceInPara(xwpfDocument,map);
                    	if(map.get(cellTextKey)!=null){
                      setCellText(tmpCell,cell,map.get(cellTextKey).toString());
                    	}
                    }
                }
            }

        }
        // 删除模版行
        table.addRow(tmpRows);
        table.removeRow(1);
        table.removeRow(1);
        
    }

    /**
     *  复制模板行的属性
     * @param tmpCell
     * @param cell
     * @param text
     * @throws Exception
     */
    private void setCellText(XWPFTableCell tmpCell, XWPFTableCell cell,String text) throws Exception {

        CTTc cttc2 = tmpCell.getCTTc();
        CTTcPr ctPr2 = cttc2.getTcPr();
        CTTc cttc = cell.getCTTc();
        CTTcPr ctPr = cttc.addNewTcPr();
        if (ctPr2.getTcW() != null) {
            ctPr.addNewTcW().setW(ctPr2.getTcW().getW());
        }
        if (ctPr2.getVAlign() != null) {
            ctPr.addNewVAlign().setVal(ctPr2.getVAlign().getVal());
        }
//        if (cttc2.getPList().size() > 0) {
//            CTP ctp = cttc2.getPList().get(0);
//            if (ctp.getPPr() != null) {
//                if (ctp.getPPr().getJc() != null) {
//                    cttc.getPList().get(0).addNewPPr().addNewJc()
//                            .setVal(ctp.getPPr().getJc().getVal());
//                }
//            }
//        }
        if (ctPr2.getTcBorders() != null) {
            ctPr.setTcBorders(ctPr2.getTcBorders());
        }

        XWPFParagraph tmpP = tmpCell.getParagraphs().get(0);
        XWPFParagraph cellP = cell.getParagraphs().get(0);
        XWPFRun tmpR = null;
        if (tmpP.getRuns() != null && tmpP.getRuns().size() > 0) {
            tmpR = tmpP.getRuns().get(0);
        }
        XWPFRun cellR = cellP.createRun();
        cellR.setText(text);
        // 复制字体信息
        if (tmpR != null) {
                if(!cellR.isBold()){
                    cellR.setBold(tmpR.isBold());
                }
                cellR.setItalic(tmpR.isItalic());
                cellR.setUnderline(tmpR.getUnderline());
                cellR.setColor(tmpR.getColor());
                cellR.setTextPosition(tmpR.getTextPosition());
                if (tmpR.getFontSize() != -1) {
                    cellR.setFontSize(tmpR.getFontSize());
                }
                if (tmpR.getFontFamily() != null) {
                    cellR.setFontFamily(tmpR.getFontFamily());
                }
                if (tmpR.getCTR() != null) {
                    if (tmpR.getCTR().isSetRPr()) {
                        CTRPr tmpRPr = tmpR.getCTR().getRPr();
                        if (tmpRPr.isSetRFonts()) {
                            CTFonts tmpFonts = tmpRPr.getRFonts();
                            CTRPr cellRPr = cellR.getCTR().isSetRPr() ? cellR
                                    .getCTR().getRPr() : cellR.getCTR().addNewRPr();
                            CTFonts cellFonts = cellRPr.isSetRFonts() ? cellRPr
                                    .getRFonts() : cellRPr.addNewRFonts();
                            cellFonts.setAscii(tmpFonts.getAscii());
                            cellFonts.setAsciiTheme(tmpFonts.getAsciiTheme());
                            cellFonts.setCs(tmpFonts.getCs());
                            cellFonts.setCstheme(tmpFonts.getCstheme());
                            cellFonts.setEastAsia(tmpFonts.getEastAsia());
                            cellFonts.setEastAsiaTheme(tmpFonts.getEastAsiaTheme());
                            cellFonts.setHAnsi(tmpFonts.getHAnsi());
                            cellFonts.setHAnsiTheme(tmpFonts.getHAnsiTheme());
                        }
                    }
                }

        }
        // 复制段落信息
        cellP.setAlignment(tmpP.getAlignment());
        cellP.setVerticalAlignment(tmpP.getVerticalAlignment());
        cellP.setBorderBetween(tmpP.getBorderBetween());
        cellP.setBorderBottom(tmpP.getBorderBottom());
        cellP.setBorderLeft(tmpP.getBorderLeft());
        cellP.setBorderRight(tmpP.getBorderRight());
        cellP.setBorderTop(tmpP.getBorderTop());
        cellP.setPageBreak(tmpP.isPageBreak());
        if (tmpP.getCTP() != null) {
            if (tmpP.getCTP().getPPr() != null) {
                CTPPr tmpPPr = tmpP.getCTP().getPPr();
                CTPPr cellPPr = cellP.getCTP().getPPr() != null ? cellP
                        .getCTP().getPPr() : cellP.getCTP().addNewPPr();
                // 复制段落间距信息
                CTSpacing tmpSpacing = tmpPPr.getSpacing();
                if (tmpSpacing != null) {
                    CTSpacing cellSpacing = cellPPr.getSpacing() != null ? cellPPr
                            .getSpacing() : cellPPr.addNewSpacing();
                    if (tmpSpacing.getAfter() != null) {
                        cellSpacing.setAfter(tmpSpacing.getAfter());
                    }
                    if (tmpSpacing.getAfterAutospacing() != null) {
                        cellSpacing.setAfterAutospacing(tmpSpacing
                                .getAfterAutospacing());
                    }
                    if (tmpSpacing.getAfterLines() != null) {
                        cellSpacing.setAfterLines(tmpSpacing.getAfterLines());
                    }
                    if (tmpSpacing.getBefore() != null) {
                        cellSpacing.setBefore(tmpSpacing.getBefore());
                    }
                    if (tmpSpacing.getBeforeAutospacing() != null) {
                        cellSpacing.setBeforeAutospacing(tmpSpacing
                                .getBeforeAutospacing());
                    }
                    if (tmpSpacing.getBeforeLines() != null) {
                        cellSpacing.setBeforeLines(tmpSpacing.getBeforeLines());
                    }
                    if (tmpSpacing.getLine() != null) {
                        cellSpacing.setLine(tmpSpacing.getLine());
                    }
                    if (tmpSpacing.getLineRule() != null) {
                        cellSpacing.setLineRule(tmpSpacing.getLineRule());
                    }
                }
                // 复制段落缩进信息
                CTInd tmpInd = tmpPPr.getInd();
                if (tmpInd != null) {
                    CTInd cellInd = cellPPr.getInd() != null ? cellPPr.getInd()
                            : cellPPr.addNewInd();
                    if (tmpInd.getFirstLine() != null) {
                        cellInd.setFirstLine(tmpInd.getFirstLine());
                    }
                    if (tmpInd.getFirstLineChars() != null) {
                        cellInd.setFirstLineChars(tmpInd.getFirstLineChars());
                    }
                    if (tmpInd.getHanging() != null) {
                        cellInd.setHanging(tmpInd.getHanging());
                    }
                    if (tmpInd.getHangingChars() != null) {
                        cellInd.setHangingChars(tmpInd.getHangingChars());
                    }
                    if (tmpInd.getLeft() != null) {
                        cellInd.setLeft(tmpInd.getLeft());
                    }
                    if (tmpInd.getLeftChars() != null) {
                        cellInd.setLeftChars(tmpInd.getLeftChars());
                    }
                    if (tmpInd.getRight() != null) {
                        cellInd.setRight(tmpInd.getRight());
                    }
                    if (tmpInd.getRightChars() != null) {
                        cellInd.setRightChars(tmpInd.getRightChars());
                    }
                }
            }
        }
    }

    
    
    
    
  
    /** 
     * 替换段落里面的变量 
     * 
     * @param doc    要替换的文档 
     * @param params 参数 
     */  
    public void replaceInPara(XWPFDocument doc, Map<String, Object> params) {  
        Iterator<XWPFParagraph> itPara  = doc.getParagraphsIterator();  
        XWPFParagraph para;  
        while (itPara .hasNext()) {  
//            para = iterator.next();  
//            this.replaceInPara(para, params);  
        	XWPFParagraph paragraph = (XWPFParagraph) itPara.next();
            Set<String> set = params.keySet();
            Iterator<String> iterator = set.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                List<XWPFRun> run=paragraph.getRuns();
                for(int i=0;i<run.size();i++)
                {
                	run.get(i).getText(run.get(i).getTextPosition());
                	if(run.get(i)!=null){
                		if(run.get(i).getText(run.get(i).getTextPosition())  !=null)
                	if(run.get(i).getText(run.get(i).getTextPosition()).contains("${")) {
                		modifiedValue(run,i,"${","");
                	}
                	}
                	if(run.get(i).getText(run.get(i).getTextPosition())  !=null)
                	if(run.get(i).getText(run.get(i).getTextPosition()).contains("}")) {
                		modifiedValue(run,i,"}","");
                	}
                	if(run.get(i).getText(run.get(i).getTextPosition())  !=null)
                    if(run.get(i).getText(run.get(i).getTextPosition())!=null &&
                            run.get(i).getText(run.get(i).getTextPosition()).indexOf(key)!=-1)
                    {
                    	if(StringUtil.isNotEmpty(params.get(key))){
                    	modifiedValue(run,i,key,params.get(key).toString());
                    	}
                    }
                }
            }
        }  
    } 
    
    public void modifiedValue(List<XWPFRun> run,int i,String key,String val) {
    	/**
         * 参数0表示生成的文字是要从哪一个地方开始放置,设置文字从位置0开始
         * 就可以把原来的文字全部替换掉了
         */
        String text = run.get(i).getText(run.get(i).getTextPosition());
        text = text.replace(key,val);
        run.get(i).setText(text,0);
    }
  
    /** 
     * 替换段落里面的变量 
     * 
     * @param para   要替换的段落 
     * @param params 参数 
     */  
    public void replaceInPara(XWPFParagraph para, Map<String, Object> params) {  
        List<XWPFRun> runs;  
        Matcher matcher;  
        if (this.matcher(para.getParagraphText()).find()) {  
            runs = para.getRuns();  
            int start = -1;  
            int end = -1;  
            String str = "";  
            for (int i = 0; i < runs.size(); i++) {  
                XWPFRun run = runs.get(i);  
                String runText = run.toString();  
                if ('$' == runText.charAt(0)&&'{' == runText.charAt(1)) {  
                    start = i;  
                }  
                if ((start != -1)) {  
                    str += runText;  
                }  
                if ('}' == runText.charAt(runText.length() - 1)) {  
                    if (start != -1) {  
                        end = i;  
                        break;  
                    }  
                }  
            }  
  
            for (int i = start; i <= end; i++) {  
                para.removeRun(i); 
                i--;  
                end--;  
            }  
  
            for (String key : params.keySet()) {  
            	if (str.equals(key)) {  
            		if(StringUtil.isNotEmpty(params.get(str))) {
                        	para.createRun().setText((String) params.get(str));  
                        }
            		break;  
            	}  
            } 
  
  
        }  
    }  
  
    /** 
     * 替换表格里面的变量 
     * 
     * @param doc    要替换的文档 
     * @param params 参数 
     */  
    public void replaceInTable(XWPFDocument doc,Map<String, Object> params) {  
        Iterator<XWPFTable> iterator = doc.getTablesIterator();  
        XWPFTable table;  
        List<XWPFTableRow> rows;  
        List<XWPFTableCell> cells;  
        List<XWPFParagraph> paras;  
        while (iterator.hasNext()) {  
            table = iterator.next();  
            rows = table.getRows();  
            for (XWPFTableRow row : rows) {  
                cells = row.getTableCells();  
                for (XWPFTableCell cell : cells) {  
                    paras = cell.getParagraphs();  
                  
                    for (XWPFParagraph para : paras) { 
                    		  this.replaceInPa(para, params);  
                        
                    }  
                }  
            }  
        }  
    }
  
    
    
    
    
    /**
     * 替换段落里面的变量
     *
     * @param para   要替换的段落
     * @param params 参数
     */
    public void replaceInPa(XWPFParagraph para, Map<String, Object> params) {
        List<XWPFRun> runs;
        Matcher matcher;
        if (this.matcher(para.getParagraphText()).find()) {
            runs = para.getRuns();
 
            int start = -1;
            int end = -1;
            String str = "";
            for (int i = 0; i < runs.size(); i++) {
                XWPFRun run = runs.get(i);
                String runText = run.toString();
                System.out.println("------>>>>>>>>>" + runText);
                if ('$' == runText.charAt(0)&&'{' == runText.charAt(1)) {
                    start = i;
                }
                if ((start != -1)) {
                    str += runText;
                }
                if ('}' == runText.charAt(runText.length() - 1)) {
                    if (start != -1) {
                        end = i;
                        break;
                    }
                }
            }
            System.out.println("start--->"+start);
            System.out.println("end--->"+end);
 
            System.out.println("str---->>>" + str);
 
            for (int i = start; i <= end; i++) {
                para.removeRun(i);
                i--;
                end--;
                System.out.println("remove i="+i);
            }
 
            for (String key : params.keySet()) {
                if (str.equals(key)) {
                    para.createRun().setText((String) params.get(key));
                    break;
                }
            }
 
 
        }
    }

    /** 
     * 正则匹配字符串 
     * 
     * @param str 
     * @return 
     */  
    private Matcher matcher(String str) {  
        Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);  
        Matcher matcher = pattern.matcher(str);  
        return matcher;  
    }  
  
    /** 
     * 关闭输入流 
     * 
     * @param is 
     */  
    public void close(InputStream is) {  
        if (is != null) {  
            try {  
                is.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    /** 
     * 关闭输出流 
     * 
     * @param os 
     */  
    public void close(OutputStream os) {  
        if (os != null) {  
            try {  
                os.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
}