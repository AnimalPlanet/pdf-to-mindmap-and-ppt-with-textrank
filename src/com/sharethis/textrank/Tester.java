package com.sharethis.textrank;

import org.apache.commons.lang3.StringEscapeUtils;


public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = StringEscapeUtils.escapeXml("hello lekha exclaim! less< more> quotes'\"");
		System.out.println(s);
	}

}
