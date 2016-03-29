package com.translate;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

public class translate {
	
	
	public translate(String text_to_translate) 
		{
		
			String translatedText;
			Translate.setClientId("6a94300f-dbcd-4c7b-bd73-7fe7af6c0932");
		    Translate.setClientSecret("9U/TebCeRSnQBzbR3HyMyGML2SaCI9zhiUeWHlkeZ8g=");
		    
		    try {
		    	FileOutputStream fos  = new FileOutputStream("translation.txt");
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
				
				translatedText = Translate.execute(text_to_translate, Language.ENGLISH, Language.SPANISH);
				//System.out.println(translatedText);
				String[] complete = translatedText.split("[,]");
				translatedText  = "";
				for(int num=0; num < complete.length; num++)
				{
				bw.write(complete[num]);
				bw.newLine();
				}
				bw.close();
			} 
		    catch (Exception e)
		    {
				e.printStackTrace();
			}
			
		}

}
