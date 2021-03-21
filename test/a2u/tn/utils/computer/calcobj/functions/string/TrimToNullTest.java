package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.computer.formula.Formula;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TrimToNullTest {

  private ObjCalcEngine engine;

  @Before
  public void prepare() {
    engine = new ObjCalcEngine();
  }

  @Test
  public void run() {
    Formula formula = new Formula("trimToNull('   abcdef')");
    String result = engine.calc(formula, String.class);
    assertEquals("abcdef", result);

    formula = new Formula("trimToNull('abcdef   ')");
    result = engine.calc(formula, String.class);
    assertEquals("abcdef", result);

    formula = new Formula("trimToNull('   abcdef   ')");
    result = engine.calc(formula, String.class);
    assertEquals("abcdef", result);

    formula = new Formula("trimToNull('abcdef')");
    result = engine.calc(formula, String.class);
    assertEquals("abcdef", result);

    formula = new Formula("trimToNull(null)");
    result = engine.calc(formula, String.class);
    assertNull(result);

    formula = new Formula("trimToNull(' \t\n ')");
    result = engine.calc(formula, String.class);
    assertEquals(null, result);

    formula = new Formula("trimToNull('    ')");
    result = engine.calc(formula, String.class);
    assertEquals(null, result);
  }

}