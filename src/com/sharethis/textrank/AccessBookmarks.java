package com.sharethis.textrank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.apache.pdfbox.util.PDFTextStripper;

public class AccessBookmarks {

	private static PDDocument doc;
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter absolute path of input pdf(code might malfunction if you give relative path!): ");
		String inputPdf = "/home/lekha/Documents/pdfs/ecom.pdf";//br.readLine();
		splitAndExtractPdf(inputPdf);
		
	}
	 public static String splitAndExtractPdf(String inputPdf) throws Exception
	 {
		 
		 String path = inputPdf.substring(0,inputPdf.lastIndexOf('/'));
		
		 doc = PDDocument.load(inputPdf);
			
			String name = doc.getDocumentInformation().getTitle();
			
			if(name.equals(" "))
				name="New Folders";
			System.out.println("name of the book: "+name);
			
			
			PDDocumentOutline root = doc.getDocumentCatalog().getDocumentOutline();
			path = path+"/"+name;
			File dir = new File(path);
			dir.mkdir();
			
			PDOutlineItem item = root.getFirstChild();
			String count = "00";
			while(item !=null)
			{
			handleItemMODIFIED(item,path,count);
			item = item.getNextSibling();
			count = NumberInString.increment(count);
			}
			handleLastLeaf(count);
			System.out.println("-------------Done!-------------");
		
			return (path);
			
	 }
	
	 public static void handleLastLeaf(String count) throws IOException
	 {
		 	PDFTextStripper stripper = new PDFTextStripper();
		 	FileWriter writer = new FileWriter(new File(previousPath + "/"+count + start.getTitle() +".txt"));
			stripper.setStartBookmark(start);
			stripper.setEndBookmark(null);
			//System.out.println("***"+start.getTitle()+"  to  *THE END*  --->  "+previousPath);
			stripper.writeText(doc, writer);
			writer.flush();
			writer.close();
			
	 }
	public static void handleItem(PDOutlineItem item, String path) throws IOException
	{
		if(item == null)
		{
			System.out.println("Error: The item is null!");
			return;
		}
		if(item.getFirstChild() == null)
		{
			//it is a leaf
			System.out.println("Leaf: "+item.getTitle());
			
			PDFTextStripper stripper = new PDFTextStripper();
			String outputFilename = item.getTitle()+".txt";
			
			System.out.println(outputFilename);
			
			//replace below code to remove all illegal characters from filename (if any)
			
			outputFilename = outputFilename.replace(',', ' ');
			outputFilename = outputFilename.replace('/', ' ');
			outputFilename = outputFilename.replace('-', ' ');
			outputFilename = outputFilename.replace(':', ' ');
			outputFilename = outputFilename.replace(';', ' ');
			outputFilename = outputFilename.replace('"', ' ');
			outputFilename = outputFilename.replace('\'', ' ');
			
			
			//code to be replaced ends here
			
			
			FileWriter outputStream = new FileWriter(new File(path+"/"+outputFilename));
			stripper.setStartBookmark(item);
			//the problem below is that for a node with no next sibling we get text starting from the node to end of book whereas we just want from the node to next node
			stripper.setEndBookmark(item.getNextSibling());
			stripper.writeText(doc, outputStream);
			outputStream.flush();
			outputStream.close();
			
		}
		else
		{// internal node : iterate through children and recurse
			path = path+"/"+item.getTitle();
			File dir = new File(path);
			dir.mkdir();
			PDOutlineItem child = item.getFirstChild();
			while(child != null)
			{
				handleItem(child,path);
				child = child.getNextSibling();
			}
		}
		
		
	}
	
	static PDOutlineItem start = null;
	static String previousPath = null;
	static String savedCount = null;
	public static void handleItemMODIFIED(PDOutlineItem item, String path,String count) throws Exception
	{
		System.out.println("Item found: "+item.getTitle());
		System.out.println("count: "+count);
		if(item == null)
		{
			System.out.println("Error: The item is null!");
			return;
		}
		if(item.getFirstChild() == null)
		{
			//leaf
			PDOutlineItem end = item;
			
			if(start != null)
			{
			PDFTextStripper stripper = new PDFTextStripper();
			FileWriter writer = new FileWriter(new File(previousPath + "/" + savedCount +start.getTitle()+".txt"));
			stripper.setStartBookmark(start);
			stripper.setEndBookmark(end);
			//System.out.println("***"+start.getTitle()+"  to  "+end.getTitle()+"  --->  "+previousPath);
			stripper.writeText(doc, writer);
			writer.flush();
			writer.close();
			}
			
			start = end;
			previousPath = path;
			savedCount = count;
			/*
			
			//it is a leaf
			System.out.println("Leaf: "+item.getTitle());
			
			PDFTextStripper stripper = new PDFTextStripper();
			String outputFilename = item.getTitle()+".txt";
			
			System.out.println(outputFilename);
			
			//replace below code to remove all illegal characters from filename (if any)
			outputFilename = outputFilename.replace(',', ' ');
			outputFilename = outputFilename.replace('/', ' ');
			
			//code to be replaced ends here
			
			
			FileWriter outputStream = new FileWriter(new File(path+"/"+outputFilename));
			stripper.setStartBookmark(item);
			//the problem below is that for a node with no next sibling we get text starting from the node to end of book whereas we just want from the node to next node
			stripper.setEndBookmark(item.getNextSibling());
			stripper.writeText(doc, outputStream);
			outputStream.flush();
			outputStream.close();
			
			*/
		}
		else
		{// internal node : iterate through children and recurse
			
			path = path+"/"+count+item.getTitle();
			File dir = new File(path);
			dir.mkdir();
			PDOutlineItem child = item.getFirstChild();
			String childCount = "00";
			while(child != null)
			{
				handleItemMODIFIED(child,path,childCount);
				child = child.getNextSibling();
				childCount = NumberInString.increment(childCount);
			}
		}
		
		
	}
	
	public static void main2(String args[]) throws IOException
	{
			//take user input of path of input pdf
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter path of a pdf to split: ");
			String inputPdf = "/home/lekha/Documents/files/java.pdf";//br.readLine();
			
			//create a pdfdocument object for the input pdf
			PDDocument doc = PDDocument.load(inputPdf);
			
			//get root node of the pdf
			PDDocumentOutline root = doc.getDocumentCatalog().getDocumentOutline();
			//get first child of the root
			PDOutlineItem item = root.getFirstChild();
			PDFTextStripper stripper = new PDFTextStripper();
		        
			while( item != null )
		      {
				
				
		          System.out.println( "Item:" + item.getTitle());
		          
		          PDOutlineItem child = item.getFirstChild();
		          
		          while( child != null )
		          {
		        	  int count = 1;
		              System.out.println( "    Child l1:" + child.getTitle() );
		              PDOutlineItem child2 = child.getFirstChild();
			          while( child2 != null )
			          {
			              System.out.println( "\t    Child l2:" + child2.getTitle() );
			              PDOutlineItem child3 = child2.getFirstChild();
				          while( child3 != null )
				          {
				              System.out.println( "\t\t    Child l3:" + child3.getTitle() );
				              
				              child3 = child3.getNextSibling();
				          }
				          count++;
				          if(count == 3)
				          {
				          FileWriter outputStream = new FileWriter(new File("dummyOutput.txt"));
				          stripper.setStartBookmark(child);

			              stripper.setEndBookmark(child.getNextSibling());
			              stripper.writeText(doc,outputStream);
			              outputStream.flush();
			              outputStream.close();
			              }

				          child2 = child2.getNextSibling();
			          }
			          			          child = child.getNextSibling();
		          }
		          
		          item = item.getNextSibling();
		      }
		
		      
			
	}

}
