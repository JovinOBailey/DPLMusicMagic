package com.phases;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class Semantic {
	boolean genmatch = false;
	boolean idmatch = false;
	public String AST = "";
	
	public String getAST()
	{
		return AST;
	}

	public void setAST(String aST)
	{
		AST = aST;
	}
	
	public Semantic()
	{
	//	Lex lex = new Lex();
		//this.SemRules(lex.getToken());
		//this.AST(lex.getToken());
	}
	
	

	public void SemRules(Token[] token){
		for(int i = 0; i < token.length; i++){
			if(token[i].get_type().equals("Genre") && ! token[i].get_value().equals("Soul")){
				System.out.println("Unaccepted Genre");
				System.exit(0);
			}
				
			if(token[i].get_type().equals("Identifier") && token[i+1].get_type().equals("Identifier")){
				System.out.println("An Identifier is missing its body");
				System.exit(0);
			}
				
		}
	}
	
	public void AST(Token[] token)
	{
		String AST = "";
		try 
		{
			FileOutputStream fos  = new FileOutputStream("AST.txt");
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
			
			for(int i = 0; i < token.length; i++)
			{	
				if(token[i].get_type().equals("Identifier"))
				{
					System.out.println();
					System.out.println();
					System.out.print(token[i].get_value());
				}
					else
				{
						if(token[i].get_type().equals("Lyric"))
						{	
							boolean hasUppercase = !token[i].get_value().equals(token[i].get_value().toLowerCase());
							
							if(hasUppercase)
							{
								AST = AST + "\n";
								AST = AST + token[i].get_value() + " ";
								
								bw.newLine();
								bw.write(token[i].get_value() + " ");
								
								System.out.println();
								System.out.print(token[i].get_value() + " ");
							}
							else
							{
								AST = AST + token[i].get_value() + " ";
								
								bw.write(token[i].get_value() + " ");
								
								System.out.print(token[i].get_value() + " ");
								
							}
								
						}
				}
			}
			System.out.println("AST IS ACUTALLY--------------" + AST);
			this.setAST(AST);
			bw.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	

}
