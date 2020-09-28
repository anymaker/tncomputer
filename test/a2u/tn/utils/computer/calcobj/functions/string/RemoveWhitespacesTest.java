package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.computer.formula.Formula;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RemoveWhitespacesTest {

  private ObjCalcEngine engine;

  @Before
  public void prapare() {
    engine = new ObjCalcEngine();
  }

  @Test
  public void run() {
    Formula formula = new Formula("removeWhitespaces('abcdf')");
    String result = engine.calc(formula, String.class);
    assertEquals("abcdf", result);

    formula = new Formula("removeWhitespaces(' a b\nc\td f ')");
    result = engine.calc(formula, String.class);
    assertEquals("abcdf", result);
  }

}