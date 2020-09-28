package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.computer.formula.Formula;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IsBlankTest {

  private ObjCalcEngine engine;

  @Before
  public void prapare() {
    engine = new ObjCalcEngine();
  }

  @Test
  public void run() {
    Formula formula = new Formula("isBlank('abcdf')");
    boolean isBlank = engine.calc(formula, Boolean.class);
    assertFalse(isBlank);

    formula = new Formula("isBlank(null)");
    isBlank = engine.calc(formula, Boolean.class);
    assertTrue(isBlank);

    formula = new Formula("isBlank(' \t\n ')");
    isBlank = engine.calc(formula, Boolean.class);
    assertTrue(isBlank);
  }

}