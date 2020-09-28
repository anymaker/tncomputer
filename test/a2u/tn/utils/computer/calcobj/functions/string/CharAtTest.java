package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.computer.formula.Formula;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CharAtTest {

    private ObjCalcEngine engine;

    @Before
    public void prapare() {
        engine = new ObjCalcEngine();
    }

    @Test
    public void run() {
        Formula formula = new Formula("charAt('abcdf', 0)");
        String str = engine.calc(formula, String.class);
        assertEquals("a", str);

        formula = new Formula("charAt('abcdf', 1)");
        str = engine.calc(formula, String.class);
        assertEquals("b", str);

        formula = new Formula("charAt('abcdf', 2)");
        str = engine.calc(formula, String.class);
        assertEquals("c", str);

        formula = new Formula("charAt('abcdf', 3)");
        str = engine.calc(formula, String.class);
        assertEquals("d", str);
    }

    @Test
    public void runIfNull() {
        Formula formula = new Formula("charAt(null, 1)");
        String str = engine.calc(formula, null, String.class);
        assertNull(str);

        formula = new Formula("charAt('abcdf', null)");
        str = engine.calc(formula, null, String.class);
        assertEquals("a", str);
    }
    
}