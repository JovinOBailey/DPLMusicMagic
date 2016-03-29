package com.phases;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Syntax {
	boolean startmatch = true;
	boolean endmatch = true;
	boolean bodymatch = true;
	boolean idmatch = true;
	boolean lyricmatch = true;
	boolean metmatch = true;
	boolean wordmatch = true;
	
	public Syntax()
	{
		//Lex lex = new Lex();
		//Rules(lex.getToken());
	}
	
	public void Rules(Token[] tokens){
		
		 for(int i = 0; i < tokens.length; i++)
		 {
			if(tokens[i].get_value().equals("["))
			{
				startmatch = true;
				//System.out.println(" System Broke");
				break;
			}
			else 
			{ 
				startmatch = false;
				//System.out.println(" System Didnt Break");
				
				}
		}
		for(int i = 0; i < tokens.length; i++){
			if(tokens[i].get_value().equals("]")){
				endmatch = true;
				break;
			}else{ 
				
				endmatch = false;
				}
		}
		for(int i = 0; i < tokens.length; i++){
			if(tokens[i].get_type().equals("Identifier") && tokens[i+1].get_type().equals("Identifier")){
				System.out.println("An Identifier is Missing its body; Enter Identifier body or remove Identifer");
				bodymatch = false;
			}else{
				bodymatch = true;
			}
		}
		for(int i = 0; i < tokens.length; i++){
			if(tokens[i].get_type().equals("Identifier")){
				idmatch = true;
				break;
			}else{
			
				idmatch = false;
			}
			}
		for(int i = 0; i < tokens.length; i++){
			if(tokens[i].get_type().equals("Lyric")){
				lyricmatch = true;
				break;
			}else{
				
				lyricmatch = false;
				}
		}
			
		for(int i = 0; i < tokens.length; i++){
			if(tokens[i].get_type().equals("BPM") ||  tokens[i].get_type().equals("metadata")){
				metmatch = true;
				break;
			}else{
				metmatch = false;
			}
		}
		
		System.out.println("Syntax Analysis Starting...");
		for(int i = 0; i < tokens.length; i++){
			if(tokens[i].get_type().equals("Lyric") && !check(tokens[i].get_value())){
				System.out.println("Lyrics contain an unaccepted word:" + " " + tokens[i].get_value());
				wordmatch = false;
				System.exit(0);
				//break;
			}
		}
		
		
		
		if(!startmatch){
			System.out.println("No Starting Point for this song; Please enter a [");
			System.exit(0);
		}
		
		if(!endmatch){
			System.out.println("No ending point for this song; Please enter a ]");
			System.exit(0);
		}
		if(!idmatch){
			System.out.println("Song File does not contain an Identifier; Check that all Choruses and Stanzas are labeled");
			System.exit(0);
		}
		if(!lyricmatch){
			System.out.println("Song does not contain any Lyrics");
			System.exit(0);
		}
		if(!metmatch){
			System.out.println("Song does not contain MetaData; Please enter Metadata");
			System.exit(0);
		}
		}
		
    public static boolean check(String word) {
    	//System.out.println("Checking Dictionary");
    		Scanner scanner = null;
    		boolean exist = false;
    		
    		char[] c = word.toCharArray();
    		String newword = "";
    		    for (int i = 0; i < c.length; i++) {
    		        if (c[i]!= ',') 
    		            newword += c[i];
    		    }
    		//String newword = word.replace(',', ' ');
    		
			try {
				scanner = new Scanner(new File("dict.txt"));
	    		while(scanner.hasNext()) {  
	    			String dict = scanner.next(); 
	    			if(newword.toLowerCase().equals((dict))){
	    				return true;
	    			}
	    			}
	    		scanner.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				exist = false;
			}
			finally{
				scanner.close();
			}
			return exist;
			   			
    }
    
}

