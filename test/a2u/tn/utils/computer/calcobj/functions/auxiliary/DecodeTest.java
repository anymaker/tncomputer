package a2u.tn.utils.computer.calcobj.functions.auxiliary;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.computer.formula.Formula;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class DecodeTest {

    private ObjCalcEngine engine;
    private Map<String, Object> testValues;


    @Before
    public void prapare() {
        engine = new ObjCalcEngine();

        testValues = new LinkedHashMap<>();
        testValues.put("a", 1);
        testValues.put("b", 2);
        testValues.put("c", 3);
        testValues.put("d", 10);

    }

    @Test
    public void run01() {
        Formula formula = new Formula("decode(.a,  1, 111,  2, 222,  3, 333,  5, 555)");
        int res = engine.calc(formula, testValues, Integer.class);
        assertEquals(111, res);
    }

    @Test
    public void run02() {
        Formula formula = new Formula("decode(.b,  1, 111,  2, 222,  3, 333,  5, 555)");
        int res = engine.calc(formula, testValues, Integer.class);
        assertEquals(222, res);
    }

    @Test
    public void run03() {
        Formula formula = new Formula("decode(.c,  1, 111,  2, 222,  3, 333,  5, 555)");
        int res = engine.calc(formula, testValues, Integer.class);
        assertEquals(333, res);
    }

    @Test
    public void run05() {
        Formula formula = new Formula("decode(.d,  1, 111,  2, 222,  3, 333,  555)");
        int res = engine.calc(formula, testValues, Integer.class);
        assertEquals(555, res);
    }

    @Test
    public void run06() {
        Formula formula = new Formula("decode(.d,  1, 111,  2, 222,  3, 333,  5, 555)");
        int res = engine.calc(formula, testValues, Integer.class);
        assertEquals(10, res);
    }

    @Test
    public void run07() {
        Formula formula = new Formula("'aaa' + decode(null, '', ' ' + 'bbb')");
        String str = engine.calc(formula, null, String.class);
        assertEquals("aaa", str);
    }
}