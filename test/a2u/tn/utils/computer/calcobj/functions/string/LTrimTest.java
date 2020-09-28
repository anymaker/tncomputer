package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.computer.formula.Formula;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LTrimTest {

  private ObjCalcEngine engine;

  @Before
  public void prapare() {
    engine = new ObjCalcEngine();
  }

  @Test
  public void run() {
    Formula formula = new Formula("lTrim('   abcdef')");
    String result = engine.calc(formula, String.class);
    assertEquals("abcdef", result);

    formula = new Formula("lTrim('abcdef')");
    result = engine.calc(formula, String.class);
    assertEquals("abcdef", result);

    formula = new Formula("lTrim(null)");
    result = engine.calc(formula, String.class);
    assertNull(result);

    formula = new Formula("lTrim(' \t\n ')");
    result = engine.calc(formula, String.class);
    assertEquals("", result);
  }

}