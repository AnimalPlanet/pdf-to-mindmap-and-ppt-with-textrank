package demos;
//import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.model.HeadersFooters;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.usermodel.RichTextRun;
import org.apache.poi.hslf.usermodel.SlideShow;
//import org.apache.poi.sl.usermodel.TextBox;
import org.apache.poi.hslf.model.TextBox;

import java.io.*;
public class FirstSteps
  {
	static SlideShow slideShow = new SlideShow();
	 
  public void createSlide(String toWrite) throws Exception
  {
  
 
 // SlideShow s2=new SlideShow();
  Slide slide = slideShow.createSlide();
  TextBox title = slide.addTitle();
  title.setText(toWrite);
  
 
  //FileOutputStream out = new FileOutputStream("slideshow.ppt");
 // slideShow.write(out);
 // out.close();
  
  HeadersFooters hdd = slideShow.getSlideHeadersFooters();
  hdd.setSlideNumberVisible(true);
  hdd.setFootersText("Tushar Kale");
  /*
     Slide slide1 = slideShow.createSlide();
   
  
  TextBox shape = new TextBox();
  RichTextRun rt = shape.getTextRun().getRichTextRuns()[0];
  shape.setText(
          "Prongs\r" +
          "Padfoot\r" +
          "Moony\r" +
          "Wormtail");
  rt.setFontSize(42);
  rt.setBullet(true);
  rt.setBulletOffset(0);  //bullet offset
  rt.setTextOffset(50);   //text offset (should be greater than bullet offset)
  rt.setBulletChar('\u223B'); //bullet character
  slide1.addShape(shape);

  shape.setAnchor(new java.awt.Rectangle(50, 50, 500, 300));  //position of the text box in the slide
  slide1.addShape(shape);
*/
  FileOutputStream out = new FileOutputStream("slideshow.ppt");
 // FileOutputStream out1 = new FileOutputStream("slideshowdemo.ppt");
  slideShow.write(out);
  
  out.close();
//  out1.close();
  System.out.println("in try");
  
  System.out.println("outside try");
  }

public void addBullet() throws Exception
{
	Slide slide1 = slideShow.createSlide();
	  TextBox shape = new TextBox();
	  RichTextRun rt = shape.getTextRun().getRichTextRuns()[0];
	  shape.setText(
	          "Prongs\r" +
	          "Padfoot\r" +
	          "Moony\r" +
	          "Wormtail");
	  rt.setFontSize(42);
	  rt.setBullet(true);
	  rt.setBulletOffset(0);  //bullet offset
	  rt.setTextOffset(50);   //text offset (should be greater than bullet offset)
	  rt.setBulletChar('\u223B'); //bullet character
	  slide1.addShape(shape);

	  shape.setAnchor(new java.awt.Rectangle(50, 50, 500, 300));  //position of the text box in the slide
	  slide1.addShape(shape);

	  FileOutputStream out = new FileOutputStream("slideshow.ppt");
	  // FileOutputStream out1 = new FileOutputStream("slideshowdemo.ppt");
	   slideShow.write(out);
	   
	   out.close();
}
 

  }