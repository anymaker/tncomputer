package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.computer.formula.Formula;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IsEmptyTest {

  private ObjCalcEngine engine;

  @Before
  public void prapare() {
    engine = new ObjCalcEngine();
  }

  @Test
  public void run() {
    Formula formula = new Formula("isEmpty('abcdf')");
    boolean isBlank = engine.calc(formula, Boolean.class);
    assertFalse(isBlank);

    formula = new Formula("isEmpty(' ')");
    isBlank = engine.calc(formula, Boolean.class);
    assertFalse(isBlank);

    formula = new Formula("isEmpty('')");
    isBlank = engine.calc(formula, Boolean.class);
    assertTrue(isBlank);

    formula = new Formula("isEmpty(null)");
    isBlank = engine.calc(formula, Boolean.class);
    assertTrue(isBlank);



  }

}