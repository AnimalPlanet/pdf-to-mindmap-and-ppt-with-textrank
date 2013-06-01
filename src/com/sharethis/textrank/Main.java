package com.sharethis.textrank;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//import org.apache.poi.hslf.model.Slide;
//import org.apache.poi.hslf.model.TextBox;
//import org.apache.poi.hslf.usermodel.SlideShow;

public class Main {
	//Object for class SlideShow
	static Presentation ppt=new Presentation();
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
	 * class variables initialized with path of input file
	 */
	public static void initialize(String inputPath,String nameOfPpt) throws IOException
	{
		
		output_file = new File(inputPath+".mm");//(path + "\\output.mm");
		writer = new FileWriter(output_file);
		System.out.println("Path obtained:"+inputPath);
		writer.write("<map version=\"0.9.0\">");
		
		writer.write("<node text=\""+inputPath.substring(inputPath.lastIndexOf('/') + 1)+"\">");
		
		
	}
	
	/*
	 * recursively traverses a folder structure
	 * goes to deepest level till it finds files
	 * when a file is found it is sent to sendToTextRank function
	 */
	public static void traverseFileSystem(String currentPath,String name, String nameOfPpt) throws Exception
	{
		

		//open file or folder represented by current path
		File fileOrFolder=new File(currentPath);
		//check if it is a folder
		if(fileOrFolder.isDirectory())
		{//it is a folder
			
			File folderContents[] = fileOrFolder.listFiles();
				
			Arrays.sort(folderContents);
						
			//String position = "right";
			//iterate through contents of folder
			for(int i=0; i<folderContents.length;i++)
			{
				if (folderContents[i].isDirectory())
				{
					//recursive call if another folder found
					String directoryName = folderContents[i].getName();
					directoryName = directoryName.substring(2);
					//create a slide for the given directory name and add title
					//name=name;
					//createSlideShow(directoryName);
					
					System.out.println("directory found: "+directoryName);
					writer.write("<node text=\""+directoryName+"\">"); //position=\""+position+"\">");
					//position = (position.equals("right")?"left":"right");
					
					traverseFileSystem(currentPath+"/"+folderContents[i].getName(),name+" "+directoryName,nameOfPpt);
					writer.write("</node>");
				}
				else
				{
					//send to textrank algorithm if file found
					String fileName = folderContents[i].getName();
					System.out.println("file found: "+fileName);
					sendToTextRank(currentPath,fileName,name,nameOfPpt);//,position);
					//position = (position.equals("right")?"left":"right");
					
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
	
	
	
	public static void sendToTextRank(String currentPath,String filename,String name,String nameOfPpt)//,String position) 
			throws Exception
	{
		//if file ends in .txt run the keyword extraction algorithm on it
		   int l = filename.length();
		   String end = filename.substring(l-4);
		   String nameWithoutExtn=filename.substring(0, l-4);
		  // name=name+":"+nameWithoutExtn;
		   ppt.createSlideShow(name+" "+nameWithoutExtn.substring(2),nameOfPpt);
		   if(end.equalsIgnoreCase(".txt"))
		   {
			   System.out.println("Textfile. Running algorithm on it...");
			   
			   String data_file = currentPath+"/"+filename; 
				writer.write("<node text=\""+filename.substring(2)+"\">");// position=\""+position+"\">");
				
			   TextRank.runTextrank(data_file,writer,nameOfPpt); //calling algo
				writer.write("</node>");
				
			   writer.flush();
			   System.out.println("Algorithm run complete!");
		   }
		   else
		   {
			   System.out.println("Not a text file. Ignored!");
		   }
	}
	/*
	public static void createSlideShow(String toWrite) throws Exception
	{
		
		
	}
	*/
	
	public static void terminate() throws IOException
	{
		writer.write("</node>");
		writer.write("</map>");
		writer.flush();
		writer.close();
	}
	public static void launchFreemind(String pathOfMindmap) throws IOException
	{
		
		Process p = new ProcessBuilder("freemind",pathOfMindmap).start();
		System.out.println("Done launching freemind!");
		
	}
	public static void launchPpt(String pathofppt) throws IOException
	{
		
		Process p = new ProcessBuilder("libreoffice",pathofppt).start();
		System.out.println("Done launching ppt!");
		
	}
	
	public static void main(String[] args) throws Exception
	{
		
		System.out.println("Input a folders path. The code will traverse the folder, going into each subfolder till infinite depth."+
				" It separately runs textrank on each txt file and outputs mindmap style xml maintaining folders tree structure."+
				"\nEnter path:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		path = "/home/lekha/Documents/pdfs/Algorithms in a Nutshell";//br.readLine();
		
		
		runAlgorithm(path);
		
	}
	
	public static void runAlgorithm(String inputPath) throws Exception
	{
		/*
		String [] temp=inputPath.split("/"); // backslash for linux forward slash for windows
		int total=temp.length;
		String nameOfPpt=temp[total-1];
		*/
		String nameOfPpt = inputPath; //its same because apache poi will append a .ppt and create a ppt file at same path as input folder
		initialize(inputPath,nameOfPpt);
		System.out.println("initialization done in main.runalgorithm"+inputPath);
		
		String ip="";
		traverseFileSystem(inputPath,ip,nameOfPpt);
		terminate();
		//launchFreemind(inputPath+".mm");
		
	}
	
}
