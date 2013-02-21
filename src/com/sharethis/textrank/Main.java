package com.sharethis.textrank;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Main {

	/* the end user passes a path to the program. 
	 * It should be a folder containing more folders or files
	 * each file is separately passed to textrank program
	 * variable path later stores this user entered path
	 */
	private static String path = "null";
	
	/*
	 * function to accept user input from console
	 * user enters path of a folder
	 * class variable is initialized with the user input
	 */
	public static void getPathFromUser() throws Exception
	{
		System.out.println("Enter path:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		path = br.readLine();
	}
	
	/*
	 * recursively traverses a folder structure
	 * goes to deepest level till it finds files
	 * when a file is found it is sent to sendToTextRank function
	 */
	public static void traverseFileSystem(String currentPath) throws Exception
	{
		//open file or folder represented by current path
		File fileOrFolder=new File(currentPath);
		//check if it is a folder
		if(fileOrFolder.isDirectory())
		{//it is a folder
			
			File folderContents[] = fileOrFolder.listFiles();
			//iterate through contents of folder
			for(int i=0; i<folderContents.length;i++)
			{
				if (folderContents[i].isDirectory())
				{
					//recursive call if another folder found
					System.out.println(folderContents[i].getName());
					traverseFileSystem(currentPath+"/"+folderContents[i].getName());
				}
				else
				{
					//send to textrank algorithm if file found
					System.out.println(folderContents[i].getName());
					sendToTextRank(currentPath,folderContents[i].getName());
				}
				
			}
			
		}
		else
			/*it is not a folder
			 *during recursive call we will never come here
			 *we may come here only if user input is not a folder name but a file name
			 */
			System.out.println("Error: The path is not the name of a directory!");
		
	}
	
	
	
	public static void sendToTextRank(String currentPath,String filename) throws Exception
	{
		String[] argument  = new String[1];
		//if file ends in .txt run the keyword extraction algorithm on it
		   int l = filename.length();
		   String end = filename.substring(l-4);
		   if(end.equalsIgnoreCase(".txt"))
		   {
			   argument[0] = currentPath+"/"+filename;
			   TextRank.main(argument); //calling algo
		   }
	}
	
	
	public static void main(String[] args) throws Exception
	{
		getPathFromUser();
		System.out.println("Path obtained:"+path);
		traverseFileSystem(path);
	}
	
}
