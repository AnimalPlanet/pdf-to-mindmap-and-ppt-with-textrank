package com.sharethis.textrank;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Window {
	
	String folder = null ;
	
	JButton  ppt;
	JButton mindMap;
	public JPanel createContentPane(){
	JPanel p=new JPanel();
	//p.setBackground(Color.CYAN);
	p.setLayout(null);
	
	
	JPanel topPanel=new JPanel();
	topPanel.setLayout(null);
	topPanel.setBackground(Color.white);
	topPanel.setLocation(0,0);
	topPanel.setSize(500,50);
	topPanel.setOpaque(true);
	p.add(topPanel);
	
	JLabel heading=new JLabel("Smart E-Book Assistant");
	heading.setLocation(110,0);
	heading.setSize(400,50);
	heading.setForeground(Color.BLACK);
	heading.setFont(new Font("Book Antiqua", Font.ITALIC, 28));
	topPanel.add(heading);
	
	JPanel middlePanel=new JPanel();
	middlePanel.setLayout(null);
	middlePanel.setBackground(Color.white);
	middlePanel.setLocation(0,50);
	middlePanel.setOpaque(true);
	middlePanel.setSize(600,70);
	p.add(middlePanel);
	
	final JTextField txtPath = new JTextField();
	txtPath.setBounds(40,30,200,25);
	middlePanel.add(txtPath);	
	txtPath.setColumns(10);
	
	
	
	
	JButton browse= new JButton("Browse");
 	browse.setLocation(270,30);
	browse.setSize(80,30);
	middlePanel.add(browse);
	
	browse.addActionListener(new ActionListener(){
		 	public void actionPerformed(ActionEvent e){
		 		JFileChooser fileChooser = new JFileChooser();
		 		
		 		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		 		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		 		fileChooser.setAcceptAllFileFilterUsed(false);
		 		
		 		int rVal=fileChooser.showOpenDialog(null);
		 		if(rVal==fileChooser.APPROVE_OPTION){
		 			
		 			txtPath.setText(fileChooser.getSelectedFile().toString());
		 		}
		 		
		 		//clears the folder variable since we have picked a new input pdf
		 		folder = null;
		 	}
		 });
	
	
	
	JButton ok= new JButton("OK");
	ok.setLocation(380,30);
	ok.setSize(80,30);
	ImageIcon imgicon =new ImageIcon("ofok.jpg");
	ok.setIcon(imgicon);
	middlePanel.add(ok);
	

	ok.addActionListener(new ActionListener(){
	 	public void actionPerformed(ActionEvent e){
	 		String path = txtPath.getText();
	 		System.out.println("path obtained: "+path);
	 		try{
	 			
	 		//disableButtons();
	 		folder = AccessBookmarks.splitAndExtractPdf(path);
	 		System.out.println("folder name:"+folder);
			Main.runAlgorithm(folder);
			//enableButtons();
			
	 		
			
	 		}
	 		catch(Exception ex)
	 		{
	 			System.out.println("Something seems to have gone wrong in the execution!");
	 		}
	 	}
	 });

	

	JPanel buttonPanel=new JPanel();
	buttonPanel.setLayout(null);
	buttonPanel.setBackground(Color.white);
	buttonPanel.setLocation(0,120);
	buttonPanel.setSize(600,65);
	p.add(buttonPanel);
	
	
	mindMap= new JButton("MindMap");	
	mindMap.setLocation(60,10);
	mindMap.setSize(152,50);
	Icon imgicona =new ImageIcon("Freemind1.png");
	mindMap.setIcon(imgicona);
	buttonPanel.add(mindMap);
	
	mindMap.addActionListener(new ActionListener(){
	 	public void actionPerformed(ActionEvent e){
	 		try{
	 			
	 		if(folder != null){
	 		Main.launchFreemind(folder+".mm");
	 		}
	 		else
	 		{
	 			//error
	 		}
			
	 		}
	 		catch(Exception ex)
	 		{
	 			System.out.println("Something seems to have gone wrong in the execution!");
	 		}
	 	}
	 });
	
	ppt= new JButton("Presentation");	
	ppt.setLocation(285,10);
	ppt.setSize(152,50);
	Icon imgiconb =new ImageIcon("ofppt.png");
	ppt.setIcon(imgiconb);
	buttonPanel.add(ppt);	
	
	
	ppt.addActionListener(new ActionListener(){
	 	public void actionPerformed(ActionEvent e){
	 		try{
	 			
	 		if(folder != null){
	 		Main.launchPpt(folder+".ppt");
	 		}
	 		else
	 		{
	 			//error
	 		}
			
	 		}
	 		catch(Exception ex)
	 		{
	 			System.out.println("Something seems to have gone wrong in the execution!");
	 		}
	 	}
	 });
	
	JPanel bottomPanel=new JPanel();
	buttonPanel.setLayout(null);
	bottomPanel.setBackground(Color.white);
	bottomPanel.setLocation(0,185);
	bottomPanel.setSize(500,150);
	p.add(bottomPanel);
	
	
	String help="<html><br><br><br>Click BROWSE. <br>Select an ebook(.pdf format). <br>Click OK. <br>Wait for the execution. <br>Click MINDMAP or PRESENTATION to view your output.</html>"; 
	
	bottomPanel.add(new JLabel (help));
	
	
p.setOpaque(true);
return p;
}	
	
	
	public void disableButtons()
	{
	ppt.setEnabled(false);
	mindMap.setEnabled(false);
	}
	public void enableButtons()
	{
		ppt.setEnabled(true);
		mindMap.setEnabled(true);
	}
	
public static void main(String[] args){
	
	SwingUtilities.invokeLater(new Runnable(){
		public void run(){
			createAndShowGUI();
		}
	});
	}

	private static void createAndShowGUI(){
	
	//JFrame.setDefaultLookAndFeelDecorated(true);
	JFrame f= new JFrame("Smart E-book Assistant");
	Window w= new Window();
	f.setContentPane(w.createContentPane());
	
	
	f.setSize(500,365);
	f.setLocationRelativeTo(null);  
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.setResizable(false);
	f.setVisible(true);

}
}

	