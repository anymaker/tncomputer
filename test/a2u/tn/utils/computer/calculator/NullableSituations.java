package a2u.tn.utils.computer.calculator;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class NullableSituations {

  private final ObjCalcEngine engine = new ObjCalcEngine();

  public static class TestClass {
    String field;
  }

  @Test
  public void test01() {
    String str = engine.calc("null", String.class);
    assertNull(str);

    Date date = engine.calc("null", Date.class);
    assertNull(date);
  }

  @Test(expected = CalculatingException.class)
  public void test02() {
    TestClass tc = new TestClass();
    Boolean v = engine.calc(".count(.field in ('AAA','BBB'))", tc, Boolean.class);
    //assertEquals(null, v);  I need to think about it
  }

}
