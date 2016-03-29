package  com.main;

import com.phases.*;
import com.translate.translate;
import com.tts.TextToSpeech;
import com.beatgen.*;

import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.speech.EngineException;

public class Main {
	static String stanza = " ";
	public static void main(String[] args) throws IllegalArgumentException, EngineException, MidiUnavailableException, InterruptedException {
		// TODO Auto-generated method stub
		
		Lex lex = new Lex();
		lex.setToken(lex.Tokenize());
		lex.to_file(lex.getToken());
		
		Syntax syn = new Syntax();
		//syn.Rules(lex.getToken());
		
		Semantic sem = new Semantic();
		sem.SemRules(lex.getToken());
		sem.AST(lex.getToken());
		
		try{Optimize opt = new Optimize();}catch(IOException e){e.printStackTrace();}
		
		//translate tran = new translate(sem.getAST());
		stanza = sem.getAST();
		
		Token[] tok = lex.Tokenize();
		//syn.Rules(tok);
		//sem.SemRules(tok);
		
	
		
	Main main = new Main();
	//Lex.main(args);
	//Semantic.main(args);
	//main.beat_speak();
	main.beat_spoken();
		
		TextToSpeech.get_voice();

	}
	public void beat_spoken()
	{
		(new Thread(new thread_2())).start();
        (new Thread(new thread_3())).start();
	}
	  public void beat_speak() {
	        (new Thread(new thread_1())).start();
	        (new Thread(new thread_2())).start();
	       
	    }
	  
	  public class thread_1 implements Runnable
	  {
		  Beatsync beat = new Beatsync();
		@Override
		public void run()
		{
			beat.beat();
			
			
		}
		  
	  }
	  public class thread_2 implements Runnable
	  {

		@Override
		public void run()
		{
			try {
				TextToSpeech.Speak(stanza);
				Thread.sleep(1);
			} catch (IllegalArgumentException | EngineException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// TODO Auto-generated method stub
			
		}
		  
	  }
	  public class thread_3 implements Runnable
	  {
		 PlayMIDI midi = new PlayMIDI();
		@Override
		public void run()
		{
			
			try {
				midi.get_beat();
			} catch (InvalidMidiDataException | IOException | MidiUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		  
	  }


}
