package cn.com.linkwide.common.enums;
/**
 * 一个周对的枚举
 * @author chencp
 *
 */
public enum WeekEnum {
	MONDAY("monday","星期一"),
	TUESDAY("tuesday","星期二"), 
	WEDNESDAY("wednesday","星期三"), 
	THURSDAY("thursday","星期四"), 
	FRIDAYS("fridays","星期五"), 
	SATURDAY("saturday","星期六"), 
	SUNDAY("sunday","星期日");
	private String code;

	private String name;

	private WeekEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static WeekEnum toEnum(String code){
		for (WeekEnum week : values()) {
			if(week.getCode().equals(code)){
				return week;
			}
		}
		return null;
	}
	
}
