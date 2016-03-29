package com.client;

import com.main.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
 
public class Client {
	private JFrame frame;
	private JFileChooser fc = new JFileChooser();
	private File file;
	private JTextArea textArea;
	private Socket socket;
	public Main main;
    
	public Client() throws UnknownHostException, IOException, InterruptedException{
		initialize();
		//receiveFile();
	//	main = new Main();
	}
	
    private void initialize() throws UnknownHostException, IOException {
    	 socket = new Socket("127.0.0.1", 4444);
    	//ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
    	//ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
    	
		frame = new JFrame("DPL Music Magic");
		frame.setResizable(false);
		frame.setBounds(100, 100, 720, 810);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setRows(25);
		textArea.setColumns(25);
		textArea.setBounds(10, 11, 584, 760);
		frame.getContentPane().add(textArea);
		
		JRadioButton rdbtnViewLexical = new JRadioButton("Lexical");
		rdbtnViewLexical.setBounds(604, 66, 109, 23);
		//bGroup.add(rdbtnViewLexical);
		frame.getContentPane().add(rdbtnViewLexical);
		
		JRadioButton rdbtnViewSemantic = new JRadioButton("Semantic");
		rdbtnViewSemantic.setBounds(604, 92, 109, 23);
		//bGroup.add(rdbtnViewSemantic);
		frame.getContentPane().add(rdbtnViewSemantic);
		
		JRadioButton rdbtnOptimize = new JRadioButton("Optimize");
		rdbtnOptimize.setBounds(604, 118, 109, 23);
		//bGroup.add(rdbtnOptimize);
		frame.getContentPane().add(rdbtnOptimize);
		
		JRadioButton rdbtnIR = new JRadioButton("Get IR");
		rdbtnIR.setBounds(604, 144, 109, 23);
		//bGroup.add(rdbtnGetAudio);
		frame.getContentPane().add(rdbtnIR);
		
		JRadioButton rdbtnViewTranslation = new JRadioButton("Translation");
		rdbtnViewTranslation.setBounds(604, 170, 109, 23);
		//bGroup.add(rdbtnViewTranslation);
		frame.getContentPane().add(rdbtnViewTranslation);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = "";
				
				if(rdbtnViewLexical.isSelected()){
					fileName = "lex.txt";
				}
				else if(rdbtnOptimize.isSelected()){
					fileName = "optimize.txt";
				}
				else if(rdbtnViewSemantic.isSelected()){
					fileName = "AST.txt";
				}
				else if(rdbtnViewTranslation.isSelected()){
					fileName = "Translation.txt";
				}
				else if(rdbtnIR.isSelected()){
					fileName = "ASCII FILE.txt";
				}
				
				System.out.println(fileName);
				textArea.setText("");
				loadFileToTextBox(fileName);
				
			}
		});
		btnSubmit.setBounds(604, 200, 89, 23);
		frame.getContentPane().add(btnSubmit);
		
		ButtonGroup operations = new ButtonGroup();
		operations.add(rdbtnViewLexical);
		operations.add(rdbtnOptimize);
		operations.add(rdbtnViewSemantic);
		operations.add(rdbtnViewTranslation);
		operations.add(rdbtnIR);
		
		//sp = new JScrollPane(textArea);
		//sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JButton btnPlay = new JButton("Play English");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Main main = new Main();
				main.beat_spoken();
			}
		});
		
		btnPlay.setBounds(604, 567, 100, 35);
		frame.getContentPane().add(btnPlay);
		
		JButton btnAttach = new JButton("Open File");
		
		btnAttach.setBounds(158, 363, 89, 23);
		frame.getContentPane().add(btnAttach);
		
		btnAttach = new JButton("Open File");
		btnAttach.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				int result = fc.showOpenDialog(frame);
				if (result==JFileChooser.APPROVE_OPTION){
					file = fc.getSelectedFile();
					String filename = file.getAbsolutePath();
					loadFileToTextBox(filename);
					System.out.println(filename);
					try {
						sendFile(file);
						
					} catch (IOException | InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				}
			});
		
			btnAttach.setBounds(604, 12, 89, 23);
			frame.getContentPane().add(btnAttach);
			
			JButton btnNewButton = new JButton("Play Spanish");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				
					main.beat_spoken_spanish();
					
				}
			});
			btnNewButton.setBounds(604, 613, 100, 35);
			frame.getContentPane().add(btnNewButton);
			
			JButton btnEnglishMidi = new JButton("Eng w/B");
			btnEnglishMidi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					main.beat_speak();
				}
			});
			btnEnglishMidi.setBounds(604, 659, 100, 35);
			frame.getContentPane().add(btnEnglishMidi);
			
			JButton btnCompile = new JButton("Compile");
			btnCompile.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					main= new Main();
				}
			});
			btnCompile.setBounds(604,  248,  89,  23);
			frame.getContentPane().add(btnCompile);
		
	}
    
    public void loadFileToTextBox(String filename){
		try{
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			textArea.read(br, null);
		}catch(IOException ex){
			JOptionPane.showMessageDialog(null, ex);
		}
	}
    
    public void sendFile(File file) throws IOException, InterruptedException{
		File myFile = new File(file.getAbsolutePath());
        byte[] mybytearray = new byte[(int) myFile.length()];
        
        FileInputStream fis = new FileInputStream(myFile);
        BufferedInputStream bis = new BufferedInputStream(fis);
        bis.read(mybytearray, 0, mybytearray.length);
         
        OutputStream os = socket.getOutputStream();
         
        os.write(mybytearray, 0, mybytearray.length);
         
        os.flush();
         
        
        bis.close();
        JOptionPane.showMessageDialog(frame, "File Sent. Server Working...");
    }
	
    public void receiveFile () throws IOException, InterruptedException {   
        
        int bytesRead;
        //int current = 0;
     
        //serverSocket = new ServerSocket(4444);
           
        while(true) {
            //Socket clientSocket = null;
            //socket = serverSocket.accept();
             
            InputStream in = socket.getInputStream();
             
            // Writing the file to disk
            // Instantiating a new output stream object
            OutputStream output = new FileOutputStream("received.txt");
               
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
            // Closing the FileOutputStream handle
            output.close();
            System.out.println("File transfer sucessful!");
        }
      }
    
    public static void main(String[] args) throws IOException {
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
}