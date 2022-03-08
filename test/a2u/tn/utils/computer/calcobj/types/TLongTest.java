package a2u.tn.utils.computer.calcobj.types;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TLongTest {

  ObjCalcEngine engine;

  @Before
  public void init() {
    engine = new ObjCalcEngine();
  }

  @Test
  public void mul() {
    assertEquals(1L, engine.calc("1 * 1"));
    assertEquals(4L, engine.calc("2 * 2"));
    assertEquals(4L, engine.calc("2 * 2.2"));
    assertEquals(4L, engine.calc("2 * '2'"));
    assertEquals(0L, engine.calc("2 * null"));

    Exception exception = null;
    try {
      engine.calc("'2' * 2");
    }
    catch(Exception ex) {
      exception = ex;
    }
    assertNotNull(exception);
    assertEquals("Operation mul is not allowed for type 'java.lang.String'.", exception.getMessage());

    exception = null;
    try {
      engine.calc(String.valueOf(Long.MAX_VALUE) +" * 2");
    }
    catch(Exception ex) {
      exception = ex;
    }
    assertNotNull(exception);
    assertEquals("Result is very big or small for long. Value1='9223372036854775807'. Value2='2'.", exception.getMessage());
  }

  @Test
  public void div() {
    assertEquals(1L, engine.calc("1 / 1"));
    assertEquals(2L, engine.calc("4 / 2"));
    assertEquals(2L, engine.calc("4 / 2.2"));
    assertEquals(2L, engine.calc("4 / '2'"));

    Exception exception = null;
    try {
      engine.calc("4 / null");
    }
    catch(Exception ex) {
      exception = ex;
    }
    assertNotNull(exception);
    assertEquals("/ by zero", exception.getMessage());

    exception = null;
    try {
      engine.calc("'2' / 2");
    }
    catch(Exception ex) {
      exception = ex;
    }
    assertNotNull(exception);
    assertEquals("Operation div is not allowed for type 'java.lang.String'.", exception.getMessage());
  }

  @Test
  public void remainder() {
    assertEquals(0L, engine.calc("1 % 1"));
    assertEquals(0L, engine.calc("5 % 1"));
    assertEquals(1L, engine.calc("5 % 2"));
    assertEquals(2L, engine.calc("5 % 3"));
    assertEquals(1L, engine.calc("5 % 4"));
    assertEquals(0L, engine.calc("5 % 5"));
    assertEquals(0L, engine.calc("4 % 2.2"));
    assertEquals(0L, engine.calc("4 % '2'"));

    Exception exception = null;
    try {
      engine.calc("4 % null");
    }
    catch(Exception ex) {
      exception = ex;
    }
    assertNotNull(exception);
    assertEquals("/ by zero", exception.getMessage());

    exception = null;
    try {
      engine.calc("'2' % 2");
    }
    catch(Exception ex) {
      exception = ex;
    }
    assertNotNull(exception);
    assertEquals("Operation remainder is not allowed for type 'java.lang.String'.", exception.getMessage());
  }

  @Test
  public void plus() {
    assertEquals(2L, engine.calc("1 + 1"));
    assertEquals(4L, engine.calc("2 + 2"));
    assertEquals(4L, engine.calc("2 + 2.2"));
    assertEquals(4L, engine.calc("2 + '2'"));
    assertEquals(2L, engine.calc("2 + null"));
    assertEquals("22", engine.calc("'2' + 2"));

    Exception exception = null;
    try {
      engine.calc(String.valueOf(Long.MAX_VALUE) +" + 2");
    }
    catch(Exception ex) {
      exception = ex;
    }
    assertNotNull(exception);
    assertEquals("Result is very big or small for long. Value1='9223372036854775807'. Value2='2'.", exception.getMessage());
  }

  @Test
  public void minus() {
    assertEquals(0L, engine.calc("1 - 1"));
    assertEquals(2L, engine.calc("4 - 2"));
    assertEquals(2L, engine.calc("4 - 2.2"));
    assertEquals(2L, engine.calc("4 - '2'"));
    assertEquals(4L, engine.calc("4 - null"));

    Exception exception = null;
    try {
      engine.calc("'2' - 2");
    }
    catch(Exception ex) {
      exception = ex;
    }
    assertNotNull(exception);
    assertEquals("Operation minus is not allowed for type 'java.lang.String'.", exception.getMessage());

    exception = null;
    try {
      engine.calc(String.valueOf(Long.MIN_VALUE) +" - 2");
    }
    catch(Exception ex) {
      exception = ex;
    }
    assertNotNull(exception);
    assertEquals("Result is very big or small for long. Value1='-9223372036854775808'. Value2='2'.", exception.getMessage());
  }

  @Test
  public void equal() {
    assertTrue(engine.calc("1 = 1", boolean.class));
    assertTrue(engine.calc("2 = 2", boolean.class));
    assertTrue(engine.calc("2 = 2.2", boolean.class));
    assertTrue(engine.calc("2 = '2'", boolean.class));
    assertFalse(engine.calc("2 = 3", boolean.class));
    assertFalse(engine.calc("2 = null", boolean.class));
    assertTrue(engine.calc("'2' = 2", boolean.class));
  }

  @Test
  public void notequal() {
    assertFalse(engine.calc("1 != 1", boolean.class));
    assertFalse(engine.calc("2 != 2", boolean.class));
    assertFalse(engine.calc("2 != 2.2", boolean.class));
    assertFalse(engine.calc("2 != '2'", boolean.class));
    assertTrue(engine.calc("2 != 3", boolean.class));
    assertTrue(engine.calc("2 != null", boolean.class));
    assertFalse(engine.calc("'2' != 2", boolean.class));
  }

  @Test
  public void great() {
    assertFalse(engine.calc("1 > 1", boolean.class));
    assertTrue(engine.calc("2 > 1", boolean.class));
    assertFalse(engine.calc("2 > 2", boolean.class));
    assertFalse(engine.calc("2 > 2.2", boolean.class));
    assertFalse(engine.calc("2 > '2'", boolean.class));
    assertFalse(engine.calc("2 > 3", boolean.class));
    assertTrue(engine.calc("2 > null", boolean.class));
    assertFalse(engine.calc("-2 > null", boolean.class));
    assertFalse(engine.calc("'2' > 2", boolean.class));
  }

  @Test
  public void greatEqual() {
    assertTrue(engine.calc("1 >= 1", boolean.class));
    assertTrue(engine.calc("2 >= 1", boolean.class));
    assertTrue(engine.calc("2 >= 2", boolean.class));
    assertTrue(engine.calc("2 >= 2.2", boolean.class));
    assertTrue(engine.calc("2 >= '2'", boolean.class));
    assertFalse(engine.calc("2 >= 3", boolean.class));
    assertTrue(engine.calc("2 >= null", boolean.class));
    assertFalse(engine.calc("-2 >= null", boolean.class));
    assertTrue(engine.calc("'2' >= 2", boolean.class));
  }

  @Test
  public void less() {
    assertFalse(engine.calc("1 < 1", boolean.class));
    assertFalse(engine.calc("2 < 1", boolean.class));
    assertFalse(engine.calc("2 < 2", boolean.class));
    assertFalse(engine.calc("2 < 2.2", boolean.class));
    assertFalse(engine.calc("2 < '2'", boolean.class));
    assertTrue(engine.calc("2 < 3", boolean.class));
    assertFalse(engine.calc("2 < null", boolean.class));
    assertTrue(engine.calc("-2 < null", boolean.class));
    assertFalse(engine.calc("'2' < 2", boolean.class));
  }

  @Test
  public void lessEqual() {
    assertTrue(engine.calc("1 <= 1", boolean.class));
    assertFalse(engine.calc("2 <= 1", boolean.class));
    assertTrue(engine.calc("2 <= 2", boolean.class));
    assertTrue(engine.calc("2 <= 2.2", boolean.class));
    assertTrue(engine.calc("2 <= '2'", boolean.class));
    assertTrue(engine.calc("2 <= 3", boolean.class));
    assertFalse(engine.calc("2 <= null", boolean.class));
    assertTrue(engine.calc("-2 <= null", boolean.class));
    assertTrue(engine.calc("'2' <= 2", boolean.class));
  }
}