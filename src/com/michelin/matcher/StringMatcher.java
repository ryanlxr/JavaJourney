package com.michelin.matcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Title: StringMatcher.java 
 * Description: 
 * 
 * @author lin.xr 2013-7-24 下午1:36:11
 * @version 1.0
 */
public class StringMatcher {
	
	public static void main(String[] args) {
		String number0 = "13410011006";
		String number1 = "8613410011009";
		String number2 = "+8613410011009";
		String number3 = "08613410011009";
		String number4 = "+08613410011009";
		String number5 = "008613410011009";
		String number6 = "+008613410011009";

		System.out.println(String.format("before %s, after %s", number0, checkPhoneNum(number0)));
		System.out.println(String.format("before %s, after %s", number1, checkPhoneNum(number1)));
		System.out.println(String.format("before %s, after %s", number2, checkPhoneNum(number2)));
		System.out.println(String.format("before %s, after %s", number3, checkPhoneNum(number3)));
		System.out.println(String.format("before %s, after %s", number4, checkPhoneNum(number4)));
		System.out.println(String.format("before %s, after %s", number5, checkPhoneNum(number5)));
		System.out.println(String.format("before %s, after %s", number6, checkPhoneNum(number6)));
	
		System.out.println(getEmailPrefix("text@qq.com"));
	}

	public static String getEmailPrefix(String strEmail) {
		int index = strEmail.indexOf("@");
		if (index == -1) {
			return strEmail;
		}
		return strEmail.substring(0, index);
	}
	
	/**
	 * user java reg to check phone number and replace 86 or +86 only check
	 * start with "+86" or "86" ex +8615911119999 13100009999 replace +86 or 86
	 * with ""
	 * 
	 * @param phoneNum
	 * @return
	 * @throws Exception
	 */
	private static String checkPhoneNum(String phoneNum) {

		Pattern p1 = Pattern.compile("^((\\+{0,1}(0)*86){0,1})1[0-9]{10}");
		Matcher m1 = p1.matcher(phoneNum);
		if (m1.matches()) {
			Pattern p2 = Pattern.compile("^((\\+{0,1}(0)*86){0,1})");
			Matcher m2 = p2.matcher(phoneNum);
			StringBuffer sb = new StringBuffer();
			while (m2.find()) {
				m2.appendReplacement(sb, "");
			}
			m2.appendTail(sb);
			return sb.toString();

		} else {
			return phoneNum;
		}
	}
}
