package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.computer.formula.Formula;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SubstringTest {

  private ObjCalcEngine engine;

  @Before
  public void prapare() {
    engine = new ObjCalcEngine();
  }

  @Test
  public void run() {
    Formula formula = new Formula("substring('abcdefgh', 0, 2)");
    String res = engine.calc(formula, String.class);
    assertEquals("ab", res);

    formula = new Formula("substring('abcdefgh', 2, 5)");
    res = engine.calc(formula, String.class);
    assertEquals("cdefg", res);

    formula = new Formula("substring('abcdefgh', 2, 50)");
    res = engine.calc(formula, String.class);
    assertEquals("cdefgh", res);

    formula = new Formula("substring('abcdefgh', 20, 50)");
    res = engine.calc(formula, String.class);
    assertEquals("", res);

    formula = new Formula("substring('abcdefgh', -5, 2)");
    res = engine.calc(formula, String.class);
    assertEquals("de", res);

    formula = new Formula("substring('abcdefgh', -5, -2)");
    res = engine.calc(formula, String.class);
    assertEquals("bc", res);

    formula = new Formula("substring('abcdefgh', -50, 2)");
    res = engine.calc(formula, String.class);
    assertEquals("", res);

    formula = new Formula("substring('abcdefgh', -5, 20)");
    res = engine.calc(formula, String.class);
    assertEquals("defgh", res);

    formula = new Formula("substring('abcdefgh', 5, -20)");
    res = engine.calc(formula, String.class);
    assertEquals("abcde", res);

  }

  @Test
  public void runIfNull() {
    Formula formula = new Formula("substring(null, 5, 2)");
    String res = engine.calc(formula, String.class);
    assertEquals(null, res);

    formula = new Formula("substring('abcdefgh', null, 2)");
    res = engine.calc(formula, String.class);
    assertEquals("ab", res);

    formula = new Formula("substring('abcdefgh', 5, null)");
    res = engine.calc(formula, String.class);
    assertEquals("", res);
  }

}