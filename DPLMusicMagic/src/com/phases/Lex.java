package com.phases;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Lex {
	
	public Token[] token;
	
	public Token[] getToken() 
	{
		return token;
	}

	public void setToken(Token[] token)
	{
		this.token = token;
	}

	public Lex()
	{
		//this.setToken(this.Tokenize());
		//to_file(this.getToken());
	}
	
	public void to_file(Token[] tok)
	{
		
		try 
		{
			
			FileOutputStream fos  = new FileOutputStream("lex.txt");
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
			
			for(int i= 0; i < tok.length; i++)
			{
			//	System.out.println(tok[i].get_type()+ "      " + tok[i].get_value());
			
				bw.write(tok[i].get_type()+ "      " + tok[i].get_value());
				bw.newLine();
				
			}
			
			bw.close();
			//System.out.println("This is the tokens---------------- \n" + tokens);
			
			
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	public ArrayList<String> Split()
	{
		ArrayList<String> list = new ArrayList<>();
	try 
	{
		Scanner scanner = new Scanner(new File("song.txt"));
		System.out.println("Success");
		
		while(scanner.hasNext())
		{  
			String token = scanner.next();  
			list.add(token); 
		} 
	/*	for(Object token : list) {   
			System.out.println(token);

		}*/
		
		scanner.close();
		}catch(IOException e) {
		System.out.println("File Not Found!");
		
	}
	return list;
	
}
	public Token[] Tokenize(){
		ArrayList<String> val = Split();
		Token[] token = new Token[val.size()];
		
		for(int i=0; i<val.size();i++){
		if(val.get(i).equals("BPM:")){
			token[i] = new Token();
			token[i].set_type("metadata");
			token[i].set_value(val.get(i));		
		}
		else if(isInteger(val.get(i))){
			token[i] = new Token();
			token[i].set_type("BPM");
			token[i].set_value(val.get(i));
		}
		else if(val.get(i).equals("Genre:")){
			token[i] = new Token();
			token[i].set_type("metadata");
			token[i].set_value(val.get(i));		
		}
		else if(val.get(i).equals("Title:")){
			token[i] = new Token();
			token[i].set_type("metadata");
			token[i].set_value(val.get(i));
		}
		else if(val.get(i).equals("[")){
			token[i] = new Token();
			token[i].set_type("StartSong");
			token[i].set_value(val.get(i));
		}
		else if(val.get(i).equals("]")){
			token[i] = new Token();
			token[i].set_type("EndSong");
			token[i].set_value(val.get(i));
		}
		else if(val.get(i).equals("Stanza")){
			token[i] = new Token();
			token[i].set_type("Identifier");
			token[i].set_value(val.get(i));
		}
		else if(val.get(i).equals("Chorus")){
			token[i] = new Token();
			token[i].set_type("Identifier");
			token[i].set_value(val.get(i));
		}
		else if(val.get(i).equals("Soul") || val.get(i).equals("Gospel")){
			token[i] = new Token();
			token[i].set_type("Genre");
			token[i].set_value(val.get(i));
		}
		else if(val.get(i).contains("_")){
			token[i] = new Token();
			token[i].set_type("Title");
			token[i].set_value(val.get(i));
		}
		else{
			token[i] = new Token();
			token[i].set_type("Lyric");
			token[i].set_value(val.get(i));
		}
	}
		
		return token;
}
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
}




