package tqs.euromillions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import tqs.euromillions.CuponEuromillions;
import tqs.euromillions.Dip;
import tqs.euromillions.EuromillionsDraw;

public class EuromillionsDrawTest {

    private CuponEuromillions sampleCoupon;

    @BeforeEach
    public void setUp()  {
        sampleCoupon = new CuponEuromillions();
        sampleCoupon.appendDip(Dip.generateRandomDip());
        sampleCoupon.appendDip(Dip.generateRandomDip());
        sampleCoupon.appendDip(new Dip(new int[]{1, 2, 3, 48, 49}, new int[]{1, 9}));
    }


    @DisplayName("reports correct matches in a coupon")
    @Test
    public void testCompareBetWithDrawToGetResults() {
        Dip winningDip, matchesFound;

        // test for full match, using the 3rd dip in the coupon as the Draw results
        winningDip = sampleCoupon.getDipByIndex(2);
        EuromillionsDraw testDraw = new EuromillionsDraw(winningDip);
        matchesFound = testDraw.findMatchesFor(sampleCoupon).get(2);

        assertEquals(winningDip, matchesFound, "expected the bet and the matches found to be equal");

        // test for no matches at all
        testDraw = new EuromillionsDraw(new Dip(new int[]{9, 10, 11, 12, 13}, new int[]{2, 3}));
        matchesFound = testDraw.findMatchesFor(sampleCoupon).get(2);
        // compare empty with the matches found
        assertEquals( new Dip(), matchesFound);
    }

    
    @DisplayName("reports correct matches in a dip")
    @Test
    public void testGetDrawResults() {
        Dip winDip = sampleCoupon.getDipByIndex(2);
        EuromillionsDraw testDraw = new EuromillionsDraw(winDip);
        assertEquals(winDip, testDraw.getDrawResults(), "expected the draw results to be the same as the winning dip");
    }

    @DisplayName("reports correct matches in a coupon")
    @Test
    public void testGenerateRandomDraw() {
        EuromillionsDraw testDraw = EuromillionsDraw.generateRandomDraw();
        assertNotNull(testDraw, "no draw generated");
    }

    @DisplayName("Verifica o número correto de dips no cupom")
    @Test
    void testCountDips() {
        CuponEuromillions cupom = new CuponEuromillions();
        
        cupom.appendDip(new Dip(new int[]{4, 8, 15, 16, 23}, new int[]{1, 3}));
        cupom.appendDip(new Dip(new int[]{10, 14, 28, 32, 41}, new int[]{2, 7}));
        cupom.appendDip(new Dip(new int[]{6, 18, 21, 37, 49}, new int[]{5, 9}));

        assertEquals(3, cupom.countDips(), "O número de dips no cupom deve ser 3");
    }

    @DisplayName("Verifica se o cupom gera a string formatada corretamente")
    @Test
    void testCorrectCouponFormat() {
        CuponEuromillions cupom = new CuponEuromillions();
        
        Dip dipA = new Dip(new int[]{1, 5, 12, 18, 33}, new int[]{2, 6});
        Dip dipB = new Dip(new int[]{4, 11, 24, 30, 42}, new int[]{1, 5});
        Dip dipC = new Dip(new int[]{7, 15, 20, 28, 36}, new int[]{3, 4});
        
        cupom.appendDip(dipA);
        cupom.appendDip(dipB);
        cupom.appendDip(dipC);
        
        // Construindo a string esperada com os dips formatados corretamente
        String expectedFormat = String.format("Dip #1:%s\nDip #2:%s\nDip #3:%s\n", 
                                            dipA.format(), dipB.format(), dipC.format());
        
        assertEquals(expectedFormat, cupom.format(), "O formato do cupom não corresponde ao esperado");
    }

    @DisplayName("Verifica se um cupom vazio retorna formato vazio")
    @Test
    void testEmptyCouponFormat() {
        CuponEuromillions cupom = new CuponEuromillions();
        assertEquals("", cupom.format(), "Cupom vazio deve ter formato de string vazia");
    }

    @DisplayName("Verifica se a contagem de dips em cupom vazio é zero")
    @Test
    void testEmptyCouponCount() {
        CuponEuromillions cupom = new CuponEuromillions();
        assertEquals(0, cupom.countDips(), "Cupom sem dips deve ter contagem 0");
    }

    @DisplayName("Verifica comportamento ao adicionar um único dip")
    @Test
    void testSingleDipCoupon() {
        CuponEuromillions cupom = new CuponEuromillions();
        Dip dip = new Dip(new int[]{3, 8, 19, 27, 34}, new int[]{1, 9});
        
        cupom.appendDip(dip);
        
        assertEquals(1, cupom.countDips(), "Cupom deve conter exatamente um dip");
        assertEquals("Dip #1:" + dip.format() + "\n", cupom.format(), "Formato do cupom com um dip não está correto");
    }


}
