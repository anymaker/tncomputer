package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.computer.formula.Formula;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RemoveSpacesTest {

  private ObjCalcEngine engine;

  @Before
  public void prapare() {
    engine = new ObjCalcEngine();
  }

  @Test
  public void run() {
    Formula formula = new Formula("removeSpaces('abcdf')");
    String result = engine.calc(formula, String.class);
    assertEquals("abcdf", result);

    formula = new Formula("removeSpaces(' a b c d f ')");
    result = engine.calc(formula, String.class);
    assertEquals("abcdf", result);
  }

}