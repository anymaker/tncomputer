package a2u.tn.utils.computer.calculator;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import org.junit.Test;

public class NullableSituations {

  private ObjCalcEngine engine = new ObjCalcEngine();

  public static class TestClass {
    String field;
  }

  @Test(expected = CalculatingException.class)
  public void test02() {
    TestClass tc = new TestClass();
    Boolean v = engine.calc(".count(.field in ('AAA','BBB'))", tc, Boolean.class);
    //assertEquals(null, v);  I need to think about it
  }

}
