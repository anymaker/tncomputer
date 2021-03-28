package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.computer.formula.Formula;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EndsWithTest {

    private ObjCalcEngine engine;

    @Before
    public void prepare() {
        engine = new ObjCalcEngine();
    }

    @Test
    public void run() {
        Formula formula = new Formula("endsWith('abcdf', 'df')");
        boolean isEndWith = engine.calc(formula, Boolean.class);
        assertTrue(isEndWith);

        formula = new Formula("endsWith('abcdf', 'ab')");
        isEndWith = engine.calc(formula, Boolean.class);
        assertFalse(isEndWith);
    }

    @Test
    public void runIfNull() {
        Formula formula = new Formula("endsWith(null, 'df')");
        boolean isEndsWith = engine.calc(formula, Boolean.class);
        assertFalse(isEndsWith);

        formula = new Formula("endsWith('abcdf', null)");
        isEndsWith = engine.calc(formula, Boolean.class);
        assertTrue(isEndsWith);

        formula = new Formula("endsWith(null, null)");
        isEndsWith = engine.calc(formula, Boolean.class);
        assertTrue(isEndsWith);
    }

}