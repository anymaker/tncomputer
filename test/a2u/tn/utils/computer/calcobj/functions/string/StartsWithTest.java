package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.computer.formula.Formula;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StartsWithTest {

  private ObjCalcEngine engine;

  @Before
  public void prepare() {
    engine = new ObjCalcEngine();
  }

  @Test
  public void run() {
    Formula formula = new Formula("startsWith('abcdf', 'ab')");
    boolean isEndWith = engine.calc(formula, Boolean.class);
    assertTrue(isEndWith);

    formula = new Formula("startsWith('abcdf', 'df')");
    isEndWith = engine.calc(formula, Boolean.class);
    assertFalse(isEndWith);
  }

  @Test
  public void runIfNull() {
    Formula formula = new Formula("startsWith(null, 'ab')");
    boolean isEndWith = engine.calc(formula, Boolean.class);
    assertFalse(isEndWith);

    formula = new Formula("startsWith('abcdf', null)");
    isEndWith = engine.calc(formula, Boolean.class);
    assertTrue(isEndWith);

    formula = new Formula("startsWith(null, null)");
    isEndWith = engine.calc(formula, Boolean.class);
    assertTrue(isEndWith);
  }

}