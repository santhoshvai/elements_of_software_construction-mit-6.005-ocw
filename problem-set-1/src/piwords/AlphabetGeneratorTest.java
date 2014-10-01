package piwords;

import static org.junit.Assert.*;

import org.junit.Test;

public class AlphabetGeneratorTest {
    @Test
    public void generateFrequencyAlphabetTest() {
        // Expect in the training data that Pr(a) = 2/5, Pr(b) = 2/5,
        // Pr(c) = 1/5.
        String[] trainingData = {"aa", "bbc"};
        // In the output for base 10, they should be in the same proportion.
        char[] expectedOutput = {'a', 'a', 'a', 'a',
                                 'b', 'b', 'b', 'b',
                                 'c', 'c'};
        assertArrayEquals(expectedOutput,
                AlphabetGenerator.generateFrequencyAlphabet(
                        10, trainingData));
        
    }

    // TODO: Write more tests (Problem 5.a)
    @Test
    public void negativeBase(){
    	assertNull(
    			AlphabetGenerator.generateFrequencyAlphabet(
    					-1, new String[]{"test"}));
    }
    @Test
    public void zeroBase(){
    	assertArrayEquals(new char[0],
    			AlphabetGenerator.generateFrequencyAlphabet(
    					0, new String[]{"test"}));
    }
    @Test
    public void noTrainingData(){
    	//test case when base is zero
    	String[] trainingData = {};
    	char[] expectedOutput = {};
    	assertArrayEquals(expectedOutput,
    			AlphabetGenerator.generateFrequencyAlphabet(
    					0, trainingData));
    	char[] expectedOutput2 = {'\0','\0'};
    	assertArrayEquals(expectedOutput2,
    			AlphabetGenerator.generateFrequencyAlphabet(
    					2, trainingData));
    }
}
