package cn.com.linkwide.common.enums;
/**
 * 一个月天数的枚举
 * @author chencp
 *
 */
public enum MonthEnum {
ONE("1","1"), 
TWO("2","2"),
THREE("3","3"),
FOUR("4","4"),
FIVE("5","5"),
SIX("6","6"),
SEVEN("7","7"),
EIGHT("8","8"),
NINE("9","9"),
TEN("10","10"),
ELEVEN("11","11"), 
TWELVE ("12","12"),
THIRTEEN ("13","13"),
FOURTEEN ("14","14"),
FIFTEEN ("15","15"),
SIXTEEN("16","16"), 
SEVENTEEN("17","17"),  
EIGHTEEN("18","18"), 
NINETEEN("19","19"), 
TWENTY("20","20"), 
TWENTY_ONE("21","21"),  
TWENTY_TWO("22","22"), 
TWENTY_THREE("23","23"),
TWENTY_FOUR("24","24"), 
TWENTY_FIVE("25","25"), 
TWENTY_SIX("26","26"),
TWENTY_SEVEN("27","27"),
TWENTY_EIGHT("28","28"), 
TWENTY_NINE("29","29"),
THIRTY("30","30"), 
THIRTY_ONE("31","31");

private String value;
private String name;

MonthEnum(String value,String name){
	this.value=value;
	this.name=name;
}

public String getValue() {
	return value;
}

public void setValue(String value) {
	this.value = value;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public static MonthEnum getName(String value){
	for(MonthEnum month:values()){
		if(month.getValue().equals(value)){
			return month;
		}
	}
	return null;
}
}
