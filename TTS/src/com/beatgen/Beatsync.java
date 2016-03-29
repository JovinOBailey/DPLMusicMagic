package com.beatgen;
import com.phases.*;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Scanner;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiChannel;


public class Beatsync {
	static int channel =9;
	static int  channel1=0;// 0 is a piano, 9 is percussion, other channels are for other instruments
	static int channel2=3;
	static int volume = 80; // between 0 et 127
	static int duration = 200; // in milliseconds
	static int BPM;
	  static Token[] beats;

	  public Beatsync()
	  {
		  //this.beat();
	  }

	  public void beat()
	  {
		  
		  try {
				Synthesizer synth = MidiSystem.getSynthesizer();
				synth.open();
				MidiChannel[] channels = synth.getChannels();
			

			Lex lex = new Lex();
			Token[] beat = lex.Tokenize();
			int length = beat.length;
		
				
			for(int i = 0; i < length; i++){
				if(beat[i].get_type().equals("BPM")){
					BPM = Integer.parseInt(beat[i].get_value());
				}
			}
			
			int b = BPM/4;
			
			for(int i = 0; i < b; i++){
				channels[channel].noteOn( 50, volume ); 
				Thread.sleep( 600 );
				channels[channel1].noteOn(76,volume);
				Thread.sleep( 600 );
				channels[channel1].noteOn(68,volume);
				Thread.sleep( 600 );
				channels[channel].noteOff( 50);
				channels[channel1].noteOff( 76);
				channels[channel2].noteOff(68);
			}

			} catch (MidiUnavailableException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{}
	  }
	
	/*public static void main(String[] args) throws MidiUnavailableException, InterruptedException {
		
		try {
			Synthesizer synth = MidiSystem.getSynthesizer();
			synth.open();
			MidiChannel[] channels = synth.getChannels();
		

		Lex lex = new Lex();
		Token[] beat = lex.Tokenize();
		int length = beat.length;
	
			
		for(int i = 0; i < length; i++){
			if(beat[i].get_type().equals("BPM")){
				BPM = Integer.parseInt(beat[i].get_value());
			}
		}
		
		int b = BPM/4;
		
		for(int i = 0; i < b; i++){
			channels[channel].noteOn( 50, volume ); 
			Thread.sleep( 600 );
			channels[channel1].noteOn(76,volume);
			Thread.sleep( 600 );
			channels[channel1].noteOn(68,volume);
			Thread.sleep( 600 );
			channels[channel].noteOff( 50);
			channels[channel1].noteOff( 76);
			channels[channel2].noteOff(68);
		}

		}finally{}}
		*/
}

