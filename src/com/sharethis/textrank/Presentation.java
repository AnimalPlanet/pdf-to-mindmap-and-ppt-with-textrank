package com.sharethis.textrank;

import org.apache.poi.hslf.model.HeadersFooters;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.usermodel.RichTextRun;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.hslf.model.TextBox;

import java.io.*;

public class Presentation {
	
	static SlideShow slideShow = new SlideShow();  
	
 
	public void createSlideShow(String toWrite,String nameOfPpt) throws Exception
	{ 
		/*
		 * This function takes the folder name/.txt name as the input and creates a
		 *  new blank slide with that name as the title 
		 *  First argument takes the title for each slide and the second argument gets
		 *  the name of the Presentation 
		 */
		
		
		nameOfPpt=nameOfPpt+".ppt";
		Slide slide = slideShow.createSlide(); //creates a new blank slide
		TextBox title = slide.addTitle();
		title.setText(toWrite);
		title.setMarginTop(200);
		HeadersFooters hdd = slideShow.getSlideHeadersFooters();  
		hdd.setSlideNumberVisible(true);   
		
		
		//the following are the 'Ctrl+S' steps for the presentation file 
		FileOutputStream out = new FileOutputStream(nameOfPpt);   
		   slideShow.write(out);
		   out.close();
		
	}

	
	/*
	 * This is the function which takes as input 10 keywords and adds them as bullet points in a slide
	 * first argument is the 10 keywords and 2nd argument is the name for the ppt file
	 */
	
	public void addBulletPoints(String bullets,String nameOfPpt) throws Exception
	{
	/*
		String [] bullet= bullets.split(":");	//input is a string of ten keywords separated by a : 
	*/
	Slide slide1 = slideShow.createSlide();
	 
	nameOfPpt=nameOfPpt+".ppt";
	  
	  TextBox shape = new TextBox();
	  RichTextRun rt = shape.getTextRun().getRichTextRuns()[0];
	  
	  /*
	  String str = "";
	  for (int i = 1 ; i < bullet.length ; i++)
	  {
		  str = str + bullet[i] + "\r";
	  }
	  System.out.println(str);
	  */
	  System.out.println(bullets);
	  shape.setText(bullets);
	  rt.setFontSize(42);
	  rt.setBullet(true);
	  rt.setBulletOffset(0);  //bullet offset
	  rt.setTextOffset(50);   //text offset (should be greater than bullet offset)
	  rt.setBulletChar('\u2022'); //bullet character
	  slide1.addShape(shape);

	  shape.setAnchor(new java.awt.Rectangle(50, 50, 500, 300));  //position of the text box in the slide
	  slide1.addShape(shape);
	
	HeadersFooters hdd = slideShow.getSlideHeadersFooters();
	hdd.setSlideNumberVisible(true);	

	//the following are the 'Ctrl+S' steps for the presentation file named slideshow.ppt

	FileOutputStream out = new FileOutputStream(nameOfPpt);   
	   slideShow.write(out);
	   out.close();
	}


		

}
