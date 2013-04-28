package com.sharethis.textrank;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class Window2 {
	public JPanel createContentPane(){
	JPanel p=new JPanel();
	p.setLayout(null);
	
	
	JPanel topPanel=new JPanel();
	//topPanel.setLayout(null);
	//topPanel.setBackground(Color.red);
	topPanel.setLocation(0,0);
	topPanel.setSize(500,50);
	p.add(topPanel);
	
	JLabel red=new JLabel("KEYWORD EXTRACTION");
	
	//red.setLocation(0,0);
	red.setSize(18,100);
	red.setHorizontalAlignment(0);
	
	topPanel.add(red);
	
	
	
	JPanel middlePanel=new JPanel();
	//middlePanel.setBackground(Color.blue);
	middlePanel.setLocation(0,50);
	middlePanel.setSize(500,50);
	p.add(middlePanel);
	
	final JTextField txtPath = new JTextField();
	txtPath.setBounds(0,10,414,21);
	middlePanel.add(txtPath);	
	txtPath.setColumns(10);
	
	
	
	
	JButton browse= new JButton("Browse");
 	browse.setLocation(10,10);
	browse.setSize(30,40);
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
		 	}
		 });
	
	
	
	JButton ok= new JButton("OK");
	ok.setLocation(10,10);
	ok.setSize(20,40);
	middlePanel.add(ok);


	ok.addActionListener(new ActionListener(){
		 	public void actionPerformed(ActionEvent e){
		 		String path = txtPath.getText();
		 		System.out.println("path obtained: "+path);
		 		try{
		 			
		 		
		 		String folder = AccessBookmarks.splitAndExtractPdf(path);
				Main.runAlgorithm(folder);
		 		
				
		 		}
		 		catch(Exception ex)
		 		{
		 			System.out.println("Something seems to have gone wrong in the execution!");
		 		}
		 	}
		 });

	


	JPanel buttonPanel=new JPanel();
	//buttonPanel.setLayout(null);
	buttonPanel.setLocation(0,100);
	buttonPanel.setSize(500,50);
	p.add(buttonPanel);
	
	
	JButton mindMap= new JButton("MindMap");	
	mindMap.setLocation(10,10);
	mindMap.setSize(50,40);
	buttonPanel.add(mindMap);
	
	JButton ppt= new JButton("Presentation");	
	ppt.setLocation(90,10);
	ppt.setSize(50,40);
	buttonPanel.add(ppt);	
	
p.setOpaque(true);
return p;
}	
	
	
public static void main(String[] args){
	
	SwingUtilities.invokeLater(new Runnable(){
		public void run(){
			createAndShowGUI();
		}
	});
	}

	private static void createAndShowGUI(){
	
	JFrame.setDefaultLookAndFeelDecorated(true);
	JFrame f= new JFrame("Simple GUI");
	Window w= new Window();
	f.setContentPane(w.createContentPane());
	
	
	f.setSize(400,300);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//f.pack();
	//f.setBackground(Color.red);
	//f.setLocation(100,100);
    //f.getContentPane().add(BorderLayout.CENTER, new JTextArea(10,10));
    f.setVisible(true);

}
}

	
