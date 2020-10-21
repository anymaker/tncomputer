package a2u.tn.utils.computer.calculator;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import org.junit.Test;

import static org.junit.Assert.*;

public class NulableSituations {

  private ObjCalcEngine engine = new ObjCalcEngine();

  public static class TestClass {
    String field;
  }

  @Test
  public void test02() {
    TestClass tc = new TestClass();
    Boolean v = engine.calc(".count(.field in ('AAA','BBB'))", tc, Boolean.class);
    assertEquals(null, v);
  }

}
