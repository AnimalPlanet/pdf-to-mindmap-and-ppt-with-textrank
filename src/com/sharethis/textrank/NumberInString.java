package com.sharethis.textrank;

public class NumberInString {
	
	public static String increment(String input) throws Exception
	{
		int num = Integer.parseInt(input);
		num++;
		String output = Integer.toString(num);
		if (num<10)
			output = "0"+output;
		if(num>99)
			throw new Exception();
		return output;
	}
	
	public static String removeCounter(String input) throws Exception
	{
		return input.substring(2);
	}
	
	public static void main(String args[]) throws Exception
	{
		System.out.println(removeCounter("93hello"));
	}

}
