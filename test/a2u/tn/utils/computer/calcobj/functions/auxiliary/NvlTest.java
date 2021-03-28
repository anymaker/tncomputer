package a2u.tn.utils.computer.calcobj.functions.auxiliary;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.computer.formula.Formula;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class NvlTest {

    private ObjCalcEngine engine;

    @Before
    public void prepare() {
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

  @Test
  public void multiParams() {
    Formula formula = new Formula("nvl(null, null, null, 'NOTNULL')");
    String str = engine.calc(formula, null, String.class);
    assertEquals("NOTNULL", str);
  }

  @Test
  public void allNulls() {
    Formula formula = new Formula("nvl(null, null, null, null)");
    String str = engine.calc(formula, null, String.class);
    assertNull(str);
  }

}