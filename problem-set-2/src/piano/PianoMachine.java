package piano;

import java.util.ArrayList;

import javax.sound.midi.MidiUnavailableException;

import midi.Instrument;
import midi.Midi;
import music.Pitch;

public class PianoMachine {
	
	private Midi midi;
	private static Instrument instrument = Midi.DEFAULT_INSTRUMENT;
	private static int semiTonesShift = 0;
	private static boolean recording = false;
	/**
	 * constructor for PianoMachine.
	 * 
	 * initialize midi device and any other state that we're storing.
	 */
    public PianoMachine() {
    	try {
            midi = Midi.getInstance();
        } catch (MidiUnavailableException e1) {
            System.err.println("Could not initialize midi device");
            e1.printStackTrace();
            return;
        }
    }
    
    /*
     * When one of these keys is pressed, a note should begin if it isn't already sounding
     * @param rawPitch Pitch that sound using the current instrument
     */
    public void beginNote(Pitch rawPitch) {
    	midi.beginNote(rawPitch.transpose(semiTonesShift).toMidiFrequency(), instrument);
    }
    
    /*
     * When key is released, a note should end if it is currently sounding
     * @param rawPitch Pitch that must end using the current instrument
     */
    public void endNote(Pitch rawPitch) {
    	midi.endNote(rawPitch.transpose(semiTonesShift).toMidiFrequency(), instrument);
    }   
    
    /* 
     * The 'I' key should switch our instrument mode to the next instrument in a list, 
     * or back to the start if we're at the end.
     * 
     * Strategy: go to the next instrument in the Instrument enum
     */
    public void changeInstrument() {
    	instrument = instrument.next(); 
    }
    
    /*
     * Shift up by 2 octaves maximum
     * Each time one octave (12 semitones) is shifted up 
     * 
     */
    public void shiftUp() {
    	if(semiTonesShift != (2*Pitch.OCTAVE)) semiTonesShift += Pitch.OCTAVE;
    }
    
    /*
     * Shift down by 2 octaves maximum
     * Each time one octave (12 semitones) is shifted down
     */
    public void shiftDown() {
    	if(semiTonesShift != -(2*Pitch.OCTAVE)) semiTonesShift -= Pitch.OCTAVE;
    }
    
    /**
    * Toggles the recording of the midi device
    * @return true if recording is turned on, otherwise returns false
    */
    public boolean toggleRecording() {
    	recording = !recording;
		if (recording) {
    		//starting to record, clear evenHistory
			midi.clearHistory();
    	}
    	return recording;
    }
    
    /**
     * Plays back each note that has been played since recording
     *  was switched on via toggleRecording()
     *  Used code from: http://goo.gl/DPoB9w, but this code also tracks change of instruments
     *  TIP: check regx at http://www.javarepl.com/console.html
     */
    protected void playback() {	
    	if (recording) {
    		String history = midi.history();
    		midi.clearHistory();
    		if (history.length() == 0) return;
    		String[] token = history.split("\\s+");
    		for (String str : token) {
    			String[] split = str.split("[(),]");
    			if (split[0].equals("on")) beginNote( new Pitch(Integer.valueOf(split[1]) - 60));
    			else if (split[0].equals("wait")) Midi.wait(Integer.valueOf(split[1]));
    			else if (split[0].equals("off")) endNote( new Pitch(Integer.valueOf(split[1]) - 60));
    		}
    	}
    }
}
