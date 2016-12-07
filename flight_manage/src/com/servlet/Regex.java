package com.servlet;

import java.util.regex.*;

public class Regex {
	public Boolean isValid(String s){
		String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";
		Pattern pattern=Pattern.compile(reg,Pattern.CASE_INSENSITIVE);
		if(pattern.matcher(s).find())
			return false;
		return true;
		
	}
}
