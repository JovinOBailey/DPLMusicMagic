package com.server;
import java.net.*;
import java.io.*;
import com.phases.*;
   
public class Server {
	private ServerSocket serverSocket;
	private Socket clientSocket;
	
   public Server() throws IOException, InterruptedException{
	   receiveFile();
	   
   }
   
public void receiveFile () throws IOException, InterruptedException {   
     
    int bytesRead;
    //int current = 0;
 
    serverSocket = new ServerSocket(4444);
       
    while(true) {
        //Socket clientSocket = null;
        clientSocket = serverSocket.accept();
         
        InputStream in = clientSocket.getInputStream();
         
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

public void sendFile() throws IOException{
	File myFile = new File("lex.txt");
    byte[] mybytearray = new byte[(int) myFile.length()];
    
    FileInputStream fis = new FileInputStream(myFile);
    BufferedInputStream bis = new BufferedInputStream(fis);
    bis.read(mybytearray, 0, mybytearray.length);
     
    OutputStream os = clientSocket.getOutputStream();
     
    os.write(mybytearray, 0, mybytearray.length);
    os.flush();
     
    clientSocket.close();
    bis.close();
}

/*private static void copyFileUsingStream(File source, File dest) throws IOException {
    InputStream is = null;
    OutputStream os = null;
    try {
        is = new FileInputStream(source);
        os = new FileOutputStream(dest);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
    } finally {
        is.close();
        os.close();
    }
}*/
	
	public static void main(String[]args){
		try {
			new Server();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}