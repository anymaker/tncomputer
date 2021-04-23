package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.computer.formula.Formula;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LikeTest {

  private ObjCalcEngine engine;

  @Before
  public void prepare() {
    engine = new ObjCalcEngine();
  }

  @Test
  public void run() {
    assertTrue(engine.calc(new Formula("like('abcdfab', 'abcdfab')"), Boolean.class));
    assertFalse(engine.calc(new Formula("like(null, 'abcdfab')"), Boolean.class));

    assertTrue(engine.calc(new Formula("like('abcdfab', 'a%b')"), Boolean.class));
    assertTrue(engine.calc(new Formula("like('abcdfabghc', 'a%b%c')"), Boolean.class));

    assertTrue( engine.calc(new Formula("like('abcabc', '%abc')"), Boolean.class));
    assertTrue( engine.calc(new Formula("like('aababc', '%abc')"), Boolean.class));
    assertFalse(engine.calc(new Formula("like('aababcc', '%abc')"), Boolean.class));

    assertFalse(engine.calc(new Formula("like('abcdfab', 'ab')"), Boolean.class));

    assertTrue(engine.calc(new Formula("like('abcdfab', '%ab%')"), Boolean.class));

    assertTrue(engine.calc(new Formula("like('abcdfab', '%cd%')"), Boolean.class));

    assertFalse(engine.calc(new Formula("like('abcdfab', '%ab%bab')"), Boolean.class));


  }

  @Test
  public void additional() {
    assertTrue( like("abcdfab",    "a%b"));
    assertTrue( like("abcdfabghc", "a%b%c"));
    assertTrue( like("abcabc",     "%abc"));
    assertTrue( like("aababc",     "%abc"));
    assertFalse(like("aababcc",    "%abc"));
    assertFalse(like("abcdfab",    "ab"));
    assertTrue( like("abcdfab",    "%ab%"));
    assertTrue( like("abcdfab",    "%cd%"));
    assertFalse(like("abcdfab",    "%ab%bab"));


    assertTrue( like("abc_d", "abc\\_d"));
    assertTrue( like("abc%d", "abc\\%%d"));
    assertFalse(like("abcd", "abc\\_d"));

    String source = "1abcd";
    assertEquals(true,  like(source, "_%d"));
    assertEquals(false, like(source, "%%a"));
    assertEquals(false, like(source, "1"));
    assertEquals(true,  like(source, "%d"));
    assertEquals(true,  like(source, "%%%%"));
    assertEquals(true,  like(source, "1%_"));
    assertEquals(false, like(source, "1%_2"));
    assertEquals(false, like(source, "1abcdef"));
    assertEquals(true,  like(source, "1abcd"));
    assertEquals(false, like(source, "1abcde"));

    assertEquals(true,  like(source, "_%_"));
    assertEquals(true,  like(source, "_%____"));
    assertEquals(true,  like(source, "_____"));
    assertEquals(false, like(source, "___"));
    assertEquals(false, like(source, "__%____"));
    assertEquals(false, like(source, "1"));

    assertEquals(false, like(source, "a_%b"));
    assertEquals(true,  like(source, "1%"));
    assertEquals(false, like(source, "d%"));
    assertEquals(true,  like(source, "_%"));
    assertEquals(true,  like(source, "_abc%"));
    assertEquals(true,  like(source, "%d"));
    assertEquals(true,  like(source, "%abc%"));
    assertEquals(false, like(source, "ab_%"));

    assertEquals(true,  like(source, "1ab__"));
    assertEquals(true,  like(source, "1ab__%"));
    assertEquals(false, like(source, "1ab___"));
    assertEquals(true,  like(source, "%"));

    assertEquals(false, like(null, "1ab___"));
    assertEquals(false, like(source, null));
    assertEquals(false, like(source, ""));
  }

  private boolean like(String source, String template) {
    return engine.calc(new Formula("like('"+source+"', '"+template+"')"), Boolean.class);
  }

}