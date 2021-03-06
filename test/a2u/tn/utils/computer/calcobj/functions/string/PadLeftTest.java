package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.computer.formula.Formula;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PadLeftTest {

  private ObjCalcEngine engine;

  @Before
  public void prepare() {
    engine = new ObjCalcEngine();
  }

  @Test
  public void run() {
    Formula formula = new Formula("padLeft('AAA', 10, 'B')");
    String result = engine.calc(formula, String.class);
    assertEquals("BBBBBBBAAA", result);

    formula = new Formula("padLeft('AAA', 2, 'B')");
    result = engine.calc(formula, String.class);
    assertEquals("AAA", result);
  }

}