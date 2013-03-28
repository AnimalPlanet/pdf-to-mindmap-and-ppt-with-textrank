package com.sharethis.textrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PdfToMindmap {

	/**
	 * 
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter absolute path of input pdf file: ");
		String path = "/home/lekha/Documents/files/2.pdf"; //br.readLine();
		
		System.out.println("Path obtained: "+path);
		
		String folder = AccessBookmarks.splitAndExtractPdf(path);
		
		Main.runAlgorithm(folder);
	}

}
