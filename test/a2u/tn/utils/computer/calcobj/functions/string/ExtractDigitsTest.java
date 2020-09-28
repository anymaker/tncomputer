package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.computer.formula.Formula;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExtractDigitsTest {

  private ObjCalcEngine engine;

  @Before
  public void prapare() {
    engine = new ObjCalcEngine();
  }

  @Test
  public void run() {
    Formula formula = new Formula("extractDigits('1a2b3c-45(6)d')");
    String result = engine.calc(formula, String.class);
    assertEquals("123456", result);

    formula = new Formula("extractDigits('abcdef')");
    result = engine.calc(formula, String.class);
    assertEquals("", result);

    formula = new Formula("extractDigits('')");
    result = engine.calc(formula, String.class);
    assertEquals("", result);

    formula = new Formula("extractDigits(null)");
    result = engine.calc(formula, String.class);
    assertEquals(null, result);

  }

}