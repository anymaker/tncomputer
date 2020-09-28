package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.computer.formula.Formula;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RTrimTest {

  private ObjCalcEngine engine;

  @Before
  public void prapare() {
    engine = new ObjCalcEngine();
  }

  @Test
  public void run() {
    Formula formula = new Formula("rTrim('abcdef   ')");
    String result = engine.calc(formula, String.class);
    assertEquals("abcdef", result);

    formula = new Formula("rTrim('abcdef')");
    result = engine.calc(formula, String.class);
    assertEquals("abcdef", result);

    formula = new Formula("rTrim(null)");
    result = engine.calc(formula, String.class);
    assertNull(result);

    formula = new Formula("rTrim(' \t\n ')");
    result = engine.calc(formula, String.class);
    assertEquals("", result);
  }

}