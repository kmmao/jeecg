package com.buss.activiti.entity;

import java.util.List;
import java.util.Map;
/**
 * 流程定义动态单据 属性对象
 * @author Administrator
 *
 */
public class FormAttr {
	String id;  
    String name;  
    String type;  
    Object defaultValue;
    boolean required;//默认是fasle  
    boolean readable;//默认是true  
    boolean writable;//默认是true  
    List<Node> selects;  
    public FormAttr(){}  
    public FormAttr(Node formProperty){  
        Map<String,String> attrMap = formProperty.getAttrMap();  
        id = attrMap.get("id");  
        name = attrMap.get("name");  
        type = attrMap.get("type");  
        defaultValue = attrMap.get("default");
        //attrMap没找到，即结果为null说明是默认值  
        required = "true".equals(attrMap.get("required")) ? true : false;  
        readable = "false".equals(attrMap.get("readable")) ? false : true;  
        writable = "false".equals(attrMap.get("writable")) ? false : true;  
        if("enum".equals(type)){  
            selects = formProperty.getChildrenList();  
        }
        
    }  
    public String toRequiredStr(){  
        return required ? " required='true' " : "";  
    }  
    public String toReadableStr(){  
        return readable ? "" : " display:none; ";  
    }  
    public String toWritableStr(){  
        return writable ? " name='"+id+"' " : " readonly='readonly' ";  
    }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public boolean isReadable() {
		return readable;
	}
	public void setReadable(boolean readable) {
		this.readable = readable;
	}
	public boolean isWritable() {
		return writable;
	}
	public void setWritable(boolean writable) {
		this.writable = writable;
	}
	public List<Node> getSelects() {
		return selects;
	}
	public void setSelects(List<Node> selects) {
		this.selects = selects;
	}
	public Object getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}
	 
    
}
