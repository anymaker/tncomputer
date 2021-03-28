package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.computer.formula.Formula;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PadRightTest {

  private ObjCalcEngine engine;

  @Before
  public void prepare() {
    engine = new ObjCalcEngine();
  }

  @Test
  public void run() {
    Formula formula = new Formula("padRight('AAA', 10, 'B')");
    String result = engine.calc(formula, String.class);
    assertEquals("AAABBBBBBB", result);

    formula = new Formula("padRight('AAA', 2, 'B')");
    result = engine.calc(formula, String.class);
    assertEquals("AAA", result);
  }

}