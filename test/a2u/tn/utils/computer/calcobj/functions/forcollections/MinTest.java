package a2u.tn.utils.computer.calcobj.functions.forcollections;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MinTest {

  private static ObjCalcEngine engine = new ObjCalcEngine();

  @Test
  public void run01() {
    try {
      int max = (int) engine.calc("min()");
      fail("Expected CalculatingException");
    }
    catch (Exception ex) {
      assertEquals("Error calculate formula:\n" +
        "min()\n" +
        "ERROR: a2u.tn.utils.computer.calculator.CalculatingException: Parameter 'collection' is not specified for function 'min'.\n", ex.getMessage());
    }
  }

  @Test
  public void run02() {
    Object min = engine.calc("min(null)");
    assertEquals(null, min);
  }

  @Test
  public void run03() {
    Object min = engine.calc("min(1)");
    assertEquals(1L, min);
  }

  @Test
  public void run04() {
    Object min = engine.calc("min(1,2)");
    assertEquals(1L, min);
  }

  @Test
  public void run05() {
    Object min = engine.calc("min((1,2))");
    assertEquals(1L, min);
  }

  @Test
  public void run06() {
    Object min = engine.calc("min((2,1))");
    assertEquals(1L, min);
  }

  @Test
  public void run07() {
    List list = new ArrayList();
    Object min = engine.calc("min(.)", list);
    assertEquals(null, min);
  }

  @Test
  public void run08() {
    List<Integer> list = new ArrayList<>();
    list.add(1);
    list.add(2);
    Object min = engine.calc("min(.)", list);
    assertEquals(1, min);
  }

  @Test
  public void run09() {
    List<Object> list = new ArrayList<>();
    list.add(1);
    list.add(2);
    list.add("3");
    Object min = engine.calc("min(.)", list);
    assertEquals(1, min);
  }

  @Test
  public void run10() {
    List<Object> list = new ArrayList<>();
    list.add("aaa");
    list.add("bbb");
    list.add(3);
    Object min = engine.calc("min(.)", list);
    assertEquals(3, min);
  }

}