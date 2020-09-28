package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.computer.formula.Formula;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReplaceTest {

  private ObjCalcEngine engine;

  @Before
  public void prapare() {
    engine = new ObjCalcEngine();
  }

  @Test
  public void run() {
    Formula formula = new Formula("replace('abcdefbcgh', 'bc', 'BC')");
    String res = engine.calc(formula, String.class);
    assertEquals("aBCdefBCgh", res);

    formula = new Formula("replace('aaaaa', 'a', '')");
    res = engine.calc(formula, String.class);
    assertEquals("", res);

    formula = new Formula("replace('aaaaa', 'a', 'b')");
    res = engine.calc(formula, String.class);
    assertEquals("bbbbb", res);

    formula = new Formula("replace('aaaaa', 'c', 'b')");
    res = engine.calc(formula, String.class);
    assertEquals("aaaaa", res);

    formula = new Formula("replace('', 'c', 'b')");
    res = engine.calc(formula, String.class);
    assertEquals("", res);

    formula = new Formula("replace(null, 'c', 'b')");
    res = engine.calc(formula, String.class);
    assertEquals(null, res);

    formula = new Formula("replace('aaaaa', '', 'b')");
    res = engine.calc(formula, String.class);
    assertEquals("aaaaa", res);
  }

}