package com.phases;

import java.awt.Menu;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
//import com.memetix.mst.language.Language;
//import com.memetix.mst.translate.Translate;

@SuppressWarnings("serial")
public class Optimize extends FileNotFoundException
{
	int level = 9;
	
	FileOutputStream fos = new FileOutputStream("optimize.txt");
	FileOutputStream fos_ASCII = new FileOutputStream("ASCII FILE.txt");
	FileInputStream fis = new FileInputStream("song.txt");
	
	BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
	BufferedWriter bw_ascii = new BufferedWriter(new OutputStreamWriter(fos_ASCII, "UTF-8"));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
	
	Deflater deflate = new Deflater();
	Inflater inflate = new Inflater();
	
	public byte[] input;
	public byte[] output = new byte[1024];
	public int data_length;
	public String text;
	
	
	public Optimize() throws IOException
	{
		this.text = this.encode();
		this.compression();
		this.IR_generation(this.text);
		
		
	}

	

	public void IR_generation(String text)
	{
		
		char[] character = text.toCharArray();
		int count = character.length;
		int[] ascii = {0};
		
		for(int new_val = 0; new_val < count; ++new_val)
		{
			System.out.println((int)character[new_val]);
			try {
				fos_ASCII.write((int)character[new_val]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	ascii[new_val] = (int) character[new_val];
		}
		System.out.println("ASCII helo");
		System.out.println(ascii);
	}



	public String encode() throws NullPointerException, IOException
	{
		Scanner scanner = new Scanner(new File("song.txt"));
		String inputString = "";
		
		while(scanner.hasNext())
		{  
			inputString =  inputString + scanner.next() + "\n";   
		} 
		
		
		System.out.println(inputString);
		
		byte[] input = inputString.getBytes("UTF-8");
		this.input = input;	
		
		return inputString;
		
	}
	
	public void compression() throws IOException
	{
		deflate.setLevel(9);
		deflate.setInput(this.input);
		deflate.finish();
		
		while (!deflate.finished())
		{
			this.data_length = deflate.deflate(this.output);
			fos.write(this.output, 0, this.data_length);
		}

		deflate.end();

		fos.close();

	}
}


