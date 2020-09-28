package a2u.tn.utils.computer.calcobj.functions;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.computer.formula.Formula;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NvlTest {
    
    private ObjCalcEngine engine;

    @Before
    public void prapare() {
        engine = new ObjCalcEngine();
    }

    @Test
    public void withNull() {
        Formula formula = new Formula("'aaa' + nvl(null, ' ' + 'bbb')");
        String str = engine.calc(formula, null, String.class);
        assertEquals("aaa bbb", str);
    }

    @Test
    public void withoutNull() {
        Formula formula = new Formula("'aaa ' + nvl('no null', ' ' + 'bbb')");
        String str = engine.calc(formula, null, String.class);
        assertEquals("aaa no null", str);
    }
}