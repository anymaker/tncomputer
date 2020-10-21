package a2u.tn.utils.computer.calculator;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CalcEnums {

  public enum TestEnum {
    FIALD_A,
    FIELD_B,
    FILED_C
  }

  public static class TestClass {
    TestEnum field;
  }

  private ObjCalcEngine engine = new ObjCalcEngine();

  @Test
  public void test01() {
    TestClass tc = new TestClass();
    tc.field = TestEnum.FIALD_A;

    boolean v = engine.calc(".field in ('AAA','BBB')", tc, Boolean.class);
    assertFalse(v);

    v = engine.calc(".field in ('FIALD_A','BBB')", tc, Boolean.class);
    assertTrue(v);
  }



}