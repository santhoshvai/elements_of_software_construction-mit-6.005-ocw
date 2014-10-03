package piano;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.sound.midi.MidiUnavailableException;

import midi.Midi;
import music.Pitch;

import org.junit.Test;

public class PianoMachineTest {
	
	PianoMachine pm = new PianoMachine();
	
    @Test
    public void singleNoteTest() throws MidiUnavailableException {
        String expected0 = "on(61,PIANO) wait(100) off(61,PIANO)";
        
    	Midi midi = Midi.getInstance();

    	midi.clearHistory();
    	
        pm.beginNote(new Pitch(1));
		Midi.wait(100);
		pm.endNote(new Pitch(1));

        System.out.println(midi.history());
        assertEquals(expected0,midi.history());
    }
    
    @Test
    public void changeInstrumentTest() throws MidiUnavailableException{
    	String expected0 = "on(61,BRIGHT_PIANO) wait(100) off(61,BRIGHT_PIANO)";
    	
    	Midi midi = Midi.getInstance();
    	
    	midi.clearHistory();
    	pm.changeInstrument();
    	
    	pm.beginNote(new Pitch(1));
    	Midi.wait(100);
    	pm.endNote(new Pitch(1));
    	
    	System.out.println(midi.history());
    	assertEquals(expected0,midi.history());
    }
    
    @Test
    public void shiftUpTest() throws MidiUnavailableException{
    	String expected0 = "on(85,PIANO) wait(100) off(85,PIANO)";
    	
    	Midi midi = Midi.getInstance();
    	midi.clearHistory();
    	
    	for(int i=0; i<7; i++) //shift up seven octaves 
    		// but 7*12 should not be up, just 2*12 max
    		pm.shiftUp(); // 61 + 24 = 85 
    	
    	pm.beginNote(new Pitch(1));
    	Midi.wait(100);
    	pm.endNote(new Pitch(1));
    	
    	System.out.println(midi.history());
    	assertEquals(expected0,midi.history());
    }
    
    @Test
    public void shiftDownTest() throws MidiUnavailableException{
    	String expected0 = "on(37,PIANO) wait(100) off(37,PIANO)";
    	
    	Midi midi = Midi.getInstance();
    	midi.clearHistory();
    	
    	for(int i=0; i<7; i++) //shift down seven octaves 
    		// but 7*12 should not be down, just 2*12 max
    		pm.shiftDown();; // 61 - 24 = 37 
    	
    	pm.beginNote(new Pitch(1));
    	Midi.wait(100);
    	pm.endNote(new Pitch(1));
    	
    	System.out.println(midi.history());
    	assertEquals(expected0,midi.history());
    }
    
    @Test
    public void recordSingleNoteTest() throws MidiUnavailableException {
    	String expected0 = "on(61,PIANO) wait(100) off(61,PIANO)";
    	
    	Midi midi = Midi.getInstance();
    	midi.clearHistory();
    	
    	pm.toggleRecording();
    	pm.beginNote(new Pitch(1));
    	Midi.wait(100);
    	pm.endNote(new Pitch(1));
    	
    	//output must be equal
    	System.out.println(midi.history());
    	assertEquals(expected0,midi.history());
    	
    	//recording must be the same too
    	pm.playback();
    	assertEquals(expected0, midi.history());
    }

}
