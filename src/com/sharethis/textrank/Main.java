package com.sharethis.textrank;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Main {

	
	public static void main(String[] args) throws Exception{
		 //argument stores path of file later passed to keyword extraction algorithm
		String argument[] = new String[1]; 
		
		//path of folder containing txt files
		String path = "";
		
		//accept path from user
		System.out.println("Enter path:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		path = br.readLine();
		
		String files;
		File folder = new File(path); //open the folder
		File[] listOfFiles = folder.listFiles(); 
		 //iterate through files in the folder
		  for (int i = 0; i < listOfFiles.length; i++) 
		  {
		 
		   if (listOfFiles[i].isFile()) 
		   {
		   files = listOfFiles[i].getName();
		   
		   //if file ends in .txt run the keyword extraction algorithm on it
		   int l = files.length();
		   String end = files.substring(l-4);
		   if(end.equalsIgnoreCase(".txt"))
		   {
			   argument[0] = path+"/"+files;
			   TextRank.main(argument); //calling algo
		   }
		      }
		  }
	}

}
