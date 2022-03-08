package a2u.tn.utils.computer.calculator;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConverterTest {

  public enum IsOneTo {
    session,
    query,
    items,
    every
  }

  private final ObjCalcEngine engine = new ObjCalcEngine();


  @Test
  public void toTypeEnum() {
    IsOneTo v = engine.toType(IsOneTo.class, "session");
    assertEquals(IsOneTo.session, v);

    Exception exception = null;
    try {
      v = engine.toType(IsOneTo.class, "session2");
    }
    catch (Exception ex) {
      exception = ex;
    }
    assertNotNull(exception);
    assertEquals(CalculatingException.class, exception.getClass());
    assertEquals("Enum a2u.tn.utils.computer.calculator.ConverterTest$IsOneTo is not contain value session2.", exception.getMessage());
  }


}