package com.beatgen;

import java.io.File;
import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class PlayMIDI {

	public PlayMIDI(){}
		
		    public void get_beat() throws InvalidMidiDataException, IOException, MidiUnavailableException{
		    Sequence sequence = MidiSystem.getSequence(new File("MHWGO.mid"));

		    // Create a sequencer for the sequence
		    Sequencer sequencer = MidiSystem.getSequencer();
		    sequencer.open();
		    sequencer.setSequence(sequence);

		    // Start playing
		    sequencer.start();
		    }
		
	}

