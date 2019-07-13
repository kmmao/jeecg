package com.buss.activiti.util;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

import com.buss.activiti.entity.Node;
public class XMLTools {
	 /*** 
     * 将dom4j的Element格式化为自己定义的结点Node格式 
     * @param Node 
     * @return 
     */  
    public static Node getNodes(Element ele){    
        Node node = new Node();  
        Map<String, String> attrMap = new HashMap<String, String>();  
        List<Node> childrenList = new ArrayList<Node>();  
        node.setName(ele.getName());//当前节点名称    
        node.setText(ele.getTextTrim());//当前节点文本内容  
        node.setAttrMap(attrMap);//当前节点所有属性的list    
        node.setChildrenList(childrenList);//当前节点所有子节点    
          
        List<Attribute> listAttr = ele.attributes();//当前节点的所有属性的list    
        for(Attribute attr:listAttr){//遍历当前节点的所有属性    
            attrMap.put(attr.getName(), attr.getValue());  
        }    
        //递归遍历当前节点所有的子节点    
        List<Element> listElement = ele.elements();//所有一级子节点的list    
        for(Element e:listElement){//遍历所有一级子节点    
            Node mapChildren = getNodes(e);//递归    
            childrenList.add(mapChildren);  
        }    
        return node;  
    }    
    public static Node Dom2Map(String xml){    
        Document doc = null;  
        Element root= null;  
        try {  
            doc = DocumentHelper.parseText(xml);  
            root = doc.getRootElement();//获取根节点    
        } catch (DocumentException e) {  
            e.printStackTrace();  
        }  
        return getNodes(root);    
    }
    
    /**
     * map 转换为xml
     * @param map 
     * @param rootName xml的根节点
     * @return xml内容
     * @throws IOException
     */
	public static String map2xml(Map<String, String> map,String rootName) throws IOException {
        Document d = DocumentHelper.createDocument();
        Element root = d.addElement(rootName);
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
