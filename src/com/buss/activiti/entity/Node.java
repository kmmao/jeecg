package com.buss.activiti.entity;

import java.util.List;
import java.util.Map;
/**
 * 流程资源xml文件活动节点对象
 * @author Administrator
 *
 */
public class Node {
	String name;  
    String text;  
    Map<String, String> attrMap;  
    List<Node> childrenList;
    public Node getSubNodeByName(String name){
    	for (Node node : childrenList) {
    		if(node.getName().equals(name))
        		return node;
		}
    	return null;
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Map<String, String> getAttrMap() {
		return attrMap;
	}
	public void setAttrMap(Map<String, String> attrMap) {
		this.attrMap = attrMap;
	}
	public List<Node> getChildrenList() {
		return childrenList;
	}
	public void setChildrenList(List<Node> childrenList) {
		this.childrenList = childrenList;
	}  
    
}
