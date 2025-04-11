/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tqs.euromillions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import tqs.euromillions.Dip;

/**
 * @author ico0
 */
public class DipTest {

    private Dip sampleInstance;


    @BeforeEach
    public void setUp() {
        sampleInstance = new Dip(new int[]{10, 20, 30, 40, 50}, new int[]{1, 2});
    }

    @AfterEach
    public void tearDown() {
        sampleInstance = null;
    }

    @DisplayName("format as string show all elements")
    @Test
    public void testFormat() {
        String result = sampleInstance.format();
        assertEquals("N[ 10 20 30 40 50] S[  1  2]", result, "format as string: formatted string not as expected. ");
    }

    @DisplayName("new Dip rejects wrong size ou negatives")
    @Test
    public void testConstructorFromBadArrays() {

        // insufficient args
        assertThrows(IllegalArgumentException.class,
                () -> new Dip( new int[]{10, 11}, new int[]{} ) );

        //negative numbers
        assertThrows(IllegalArgumentException.class,
                () -> new Dip( new int[]{10, 11, 12, 13, -1}, new int[]{1, 2} ) );

        // this test will reveal that the code was not yet checking ranges


    }

    @DisplayName("new Dip rejects out of range elements")
    @Test
    public void testConstructorFromBadRanges() {
        // creating Dip with numbers or starts outside the expected range
        // expects an exception
        assertThrows(IllegalArgumentException.class,
                () -> new Dip( new int[]{10, 11, 12, 13, Dip.NUMBERS_RANGE_MAX * 2}, new int[]{1,2} ) );
        assertThrows(IllegalArgumentException.class,
                () -> new Dip( new int[]{11, 12, 13, 14, 15}, new int[]{ Dip.STARS_RANGE_MAX*2 ,1} ) );

    }

    @DisplayName("Test equality between different Dip instances")
    @Test
    void testEqualityBetweenDips() {
        Dip dip1 = new Dip(new int[]{5, 15, 25, 35, 45}, new int[]{3, 7});
        Dip dip2 = new Dip(new int[]{5, 15, 25, 35, 45}, new int[]{3, 7});

        assertEquals(dip1, dip2, "Dips with identical numbers and stars should be equal");
    }

    @DisplayName("Test inequality due to different main numbers")
    @Test
    void testInequalityDifferentMainNumbers() {
        Dip dip1 = new Dip(new int[]{1, 2, 3, 4, 5}, new int[]{6, 7});
        Dip dip2 = new Dip(new int[]{10, 20, 30, 40, 50}, new int[]{6, 7});

        assertNotEquals(dip1, dip2, "Dips with different main numbers should not be equal");
    }

    @DisplayName("Test inequality due to different star numbers")
    @Test
    void testInequalityDifferentStars() {
        Dip dip1 = new Dip(new int[]{10, 20, 30, 40, 50}, new int[]{1, 2});
        Dip dip2 = new Dip(new int[]{10, 20, 30, 40, 50}, new int[]{3, 4});

        assertNotEquals(dip1, dip2, "Dips with different star numbers should not be equal");
    }

    @DisplayName("Test hashCode consistency")
    @Test
    void testHashCodeConsistency() {
        Dip dip1 = new Dip(new int[]{5, 10, 15, 20, 25}, new int[]{2, 9});
        Dip dip2 = new Dip(new int[]{5, 10, 15, 20, 25}, new int[]{2, 9});

        assertEquals(dip1.hashCode(), dip2.hashCode(), "Dips with identical values should have the same hashCode");
    }

    @DisplayName("Test inequality with null and different object types")
    @Test
    void testNotEqualsWithDifferentObjects() {
        Dip dip = new Dip(new int[]{3, 6, 9, 12, 15}, new int[]{1, 4});
        assertNotEquals(dip, null, "Dip should not be equal to null");
        assertNotEquals(dip, "randomString", "Dip should not be equal to an object of different type");
    }




}
