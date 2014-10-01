package piwords;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

public class PiGeneratorTest {
	// SPOJ test cases http://goo.gl/ucsV05
    @Test
    public void basicPowerModTest() {
        // 5^7 mod 23 = 17
        assertEquals(17, PiGenerator.powerMod(5, 7, 23));
    }
    @Test
    public void basicTwoPowerModTest() {
    	// 7^256 mod 13 = 9
        assertEquals(9, PiGenerator.powerMod(7, 256, 13));
    }
    @Test
    public void basicThreePowerModTest() {
    	// 5^117 mod 19 = 1
        assertEquals(1, PiGenerator.powerMod(5, 117, 19));
    }
    // SPOJ test cases http://goo.gl/ucsV05
    @Test
    public void spojPowerModTest() {

        assertEquals(350431544, PiGenerator.powerMod(54015779, 489100829, 472960975));
        assertEquals(391669493, PiGenerator.powerMod(827371214,966345673,443599139));
//      assertEquals(718185534, PiGenerator.powerMod(29809803, BigInteger.valueOf("47901912849872523461864631327232122"), 1008098565));
    }
    // UVA Test cases http://goo.gl/okBHyZ
    @Test
    public void uvaPowerModTest() {
        assertEquals(13, PiGenerator.powerMod(3, 18132, 17));
        assertEquals(2, PiGenerator.powerMod(17, 1765, 3));
        assertEquals(13195, PiGenerator.powerMod(2374859, 3029382, 36123));
    }
    
    @Test
    public void basicComputePiInHexTest(){
    	assertNull(PiGenerator.computePiInHex(-1));
    	assertArrayEquals(new int[]{2,4,3,15,6},PiGenerator.computePiInHex(5));
    }
}
