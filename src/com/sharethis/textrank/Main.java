package com.sharethis.textrank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	/* the end user passes a path to the program. 
	 * It should be a folder containing more folders or files
	 * each file is separately passed to textrank program
	 * variable path later stores this user entered path
	 */
	private static String path = "null";
	
	/*
	 * program outputs to a single file, its name and a writer object to it are stored in the variables below
	 * */	
	private static File output_file;
	private static FileWriter writer;
	/*
	 * function to accept user input from console
	 * user enters path of a folder
	 * class variable is initialized with the user input
	 */
	public static void initialize(String path) throws IOException
	{
		
		output_file = new File(path + "/output.mm");
		writer = new FileWriter(output_file);
		System.out.println("Path obtained:"+path);
		writer.write("<map version=\"0.9.0\">");
		writer.write("<node text=\""+path.substring(path.lastIndexOf('/') + 1)+"\">");
		
		
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
			String position = "right";
			//iterate through contents of folder
			for(int i=0; i<folderContents.length;i++)
			{
				if (folderContents[i].isDirectory())
				{
					//recursive call if another folder found
					String directoryName = folderContents[i].getName();
					System.out.println("directory found: "+directoryName);
					writer.write("<node text=\""+directoryName+"\" position=\""+position+"\">");
					position = (position.equals("right")?"left":"right");
					traverseFileSystem(currentPath+"/"+folderContents[i].getName());
					writer.write("</node>");
				}
				else
				{
					//send to textrank algorithm if file found
					String fileName = folderContents[i].getName();
					System.out.println("file found: "+fileName);
					sendToTextRank(currentPath,fileName,position);
					position = (position.equals("right")?"left":"right");
					
				}
				
			}
			
		}
		else
			/*
			 * it is not a folder
			 * during recursive call we will never come here
			 * we may come here only if user input is not a folder name but a file name
			 */
			System.out.println("Error: The path is not the name of a directory!");
		
	}
	
	
	
	public static void sendToTextRank(String currentPath,String filename,String position) throws Exception
	{
		//if file ends in .txt run the keyword extraction algorithm on it
		   int l = filename.length();
		   String end = filename.substring(l-4);
		   if(end.equalsIgnoreCase(".txt"))
		   {
			   System.out.println("Textfile. Running algorithm on it...");
			   
			   String data_file = currentPath+"/"+filename; 
				writer.write("<node text=\""+filename+"\" position=\""+position+"\">");
				
			   TextRank.runTextrank(data_file,writer); //calling algo
				writer.write("</node>");
				
			   writer.flush();
			   System.out.println("Algorithm run complete!");
		   }
		   else
		   {
			   System.out.println("Not a text file. Ignored!");
		   }
	}
	
	
	public static void terminateAndLaunchFreemind() throws IOException
	{
		writer.write("</node>");
		writer.write("</map>");
		writer.flush();
		writer.close();
		
		Process p = new ProcessBuilder("freemind",path+"/output.mm").start();
		System.out.println("Done!");
		
	}
	
	public static void main(String[] args) throws Exception
	{
		System.out.println("Input a folders path. The code will traverse the folder, going into each subfolder till infinite depth."+
				" It separately runs textrank on each txt file and outputs mindmap style xml maintaining folders tree structure."+
				"\nEnter path:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		path = "/home/lekha/Documents/files";//br.readLine();
		
		runAlgorithm(path);
		
	}
	
	public static void runAlgorithm(String path) throws Exception
	{
		initialize(path);
		traverseFileSystem(path);
		terminateAndLaunchFreemind();
		
	}
	
}
