package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.computer.formula.Formula;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NormalizeSpacesTest {

  private ObjCalcEngine engine;

  @Before
  public void prapare() {
    engine = new ObjCalcEngine();
  }

  @Test
  public void run() {
    Formula formula = new Formula("normalizeSpaces('a b   cd  f')");
    String result = engine.calc(formula, String.class);
    assertEquals("a b cd f", result);

    formula = new Formula("normalizeSpaces(' a b c d\nf ')");
    result = engine.calc(formula, String.class);
    assertEquals(" a b c d\nf ", result);

    formula = new Formula("normalizeSpaces('abcdf')");
    result = engine.calc(formula, String.class);
    assertEquals("abcdf", result);
  }

}