package com.braysoft.unedintercampus.entities;

public class Subject 
{
	private String _code;
	private String _name;
	
	public Subject(String code, String name)
	{
		_code = code;
		_name = name;
	}
	
	public Subject()
	{
		
	}

	public String getCode() {
		return _code;
	}

	public void setCode(String code) {
		_code = code;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}
	
	

}
