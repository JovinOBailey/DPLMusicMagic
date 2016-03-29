package com.tts;

//import com.sun.speech.freetts.*;
import java.beans.PropertyVetoException;
import java.util.Locale;

import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineStateError;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;

import com.sun.speech.freetts.VoiceManager;


public class TextToSpeech {
	//private static final String VOICENAME = "kevin16";
	public static final int G_1  = 196;
    public static final int A_2  = 220;
    public static final int B_2  = 246;
    public static final int C_2  = 260;
    public static final int D_2  = 292;
	
	public static void Speak(String token) throws IllegalArgumentException, EngineException{
	Voice voice;
	SynthesizerModeDesc generalDesc;
	Synthesizer synth;
	System.setProperty("mbrola.base", "C:/Users/Branster/workspace/DPLMusicMagic/mbrola");
	voice = new Voice("alan", Voice.GENDER_FEMALE, Voice.AGE_CHILD, null);
	
		
	   generalDesc = new SynthesizerModeDesc(
               null,           // engine name
               "general",      // mode name
               Locale.US,      // locale
               null,           // running
               null );         // voice

synth = Central.createSynthesizer( generalDesc );

//	voice.allocate();
try {
	synth.allocate();
} catch (EngineException e5) {
	// TODO Auto-generated catch block
	e5.printStackTrace();
} catch (EngineStateError e5) {
	// TODO Auto-generated catch block
	e5.printStackTrace();
}
try {
	synth.getSynthesizerProperties().setVoice(voice);
} catch (PropertyVetoException e4) {
	// TODO Auto-generated catch block
	e4.printStackTrace();
}
try {
	synth.getSynthesizerProperties().setPitchRange(0.0f);
} catch (PropertyVetoException e3) {
	// TODO Auto-generated catch block
	e3.printStackTrace();
}
try {
	synth.resume();
} catch (AudioException | EngineStateError e2) {
	// TODO Auto-generated catch block
	e2.printStackTrace();
}
	
try {
	synth.getSynthesizerProperties().setPitch( C_2 );
} catch (PropertyVetoException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}



	synth.speakPlainText( token, null );


try {
	synth.waitEngineState( Synthesizer.QUEUE_EMPTY );
} catch (IllegalArgumentException | InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}
	

	public static void get_voice(){
		VoiceManager vm = VoiceManager.getInstance();
		for (com.sun.speech.freetts.Voice v : vm.getVoices()) {
			System.out.println(v.getName());
		}
	}
}