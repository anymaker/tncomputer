package a2u.tn.utils.computer.calcobj.functions.auxiliary;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.computer.formula.Formula;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IfNotNullTest {

    private ObjCalcEngine engine;

    @Before
    public void prepare() {
        engine = new ObjCalcEngine();
    }

    @Test
    public void runIfNull() {
        Formula formula = new Formula("'aaa' + ifNotNull(null, ' ' + 'bbb')");
        String str = engine.calc(formula, null, String.class);
        assertEquals("aaa", str);
    }

    @Test
    public void runIfNotNull() {
        Formula formula = new Formula("'aaa' + ifNotNull('not null', ' ' + 'bbb')");
        String str = engine.calc(formula, null, String.class);
        assertEquals("aaa bbb", str);
    }
}