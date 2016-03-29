package com.phases;

public class Token {
	public String type;
	public String value;
	public String local;
	
	public Token(){}
	
	public String get_type(){
		return this.type;
	}
	public void set_type(String ttype){
		this.type = ttype;
	}
	
	public String get_value(){
		return this.value;
	}
	
	public void set_value(String val){
		this.value = val;
	}
	
	public String get_local(){
		return this.local;
	}
	
	public void set_local(String loc){
		this.local = loc;
	}
	
}
