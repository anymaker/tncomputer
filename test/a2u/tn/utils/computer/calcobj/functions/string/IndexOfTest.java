package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.computer.formula.Formula;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IndexOfTest {

    private ObjCalcEngine engine;

    @Before
    public void prepare() {
        engine = new ObjCalcEngine();
    }

    @Test
    public void run() {
        Formula formula = new Formula("indexOf('abcdf', 'aaa')");
        int pos = engine.calc(formula, Integer.class);
        assertEquals(-1, pos);

        formula = new Formula("indexOf('abcdf', 'ab')");
        pos = engine.calc(formula, Integer.class);
        assertEquals(0, pos);

        formula = new Formula("indexOf('abcdf', 'bc')");
        pos = engine.calc(formula, Integer.class);
        assertEquals(1, pos);
    }

    @Test
    public void runIfNull() {
        Formula formula = new Formula("indexOf(null, 'aaa')");
        int pos = engine.calc(formula, Integer.class);
        assertEquals(-1, pos);

        formula = new Formula("indexOf('abcdf', null)");
        pos = engine.calc(formula, Integer.class);
        assertEquals(-1, pos);

        formula = new Formula("indexOf(null, null)");
        pos = engine.calc(formula, Integer.class);
        assertEquals(0, pos);
    }

}