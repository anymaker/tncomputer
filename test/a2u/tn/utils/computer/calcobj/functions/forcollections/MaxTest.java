package a2u.tn.utils.computer.calcobj.functions.forcollections;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MaxTest {

  private static ObjCalcEngine engine = new ObjCalcEngine();

  @Test
  public void run01() {
    try {
      int max = (int) engine.calc("max()");
      fail("Expected CalculatingException");
    }
    catch (Exception ex) {
      assertEquals("Error calculate formula:\n" +
        "max()\n" +
        "ERROR: a2u.tn.utils.computer.calculator.CalculatingException: Parameter 'collection' is not specified for function 'max'.\n", ex.getMessage());
    }
  }

  @Test
  public void run02() {
    Object max = engine.calc("max(null)");
    assertEquals(null, max);
  }

  @Test
  public void run03() {
    Object max = engine.calc("max(1)");
    assertEquals(1L, max);
  }

  @Test
  public void run04() {
    Object max = engine.calc("max(1,2)");
    assertEquals(2L, max);
  }

  @Test
  public void run05() {
    Object max = engine.calc("max((1,2))");
    assertEquals(2L, max);
  }

  @Test
  public void run06() {
    Object max = engine.calc("max((1,2))");
    assertEquals(2L, max);
  }

  @Test
  public void run07() {
    List list = new ArrayList();
    Object max = engine.calc("max(.)", list);
    assertEquals(null, max);
  }

  @Test
  public void run08() {
    List<Integer> list = new ArrayList<>();
    list.add(1);
    list.add(2);
    Object max = engine.calc("max(.)", list);
    assertEquals(2, max);
  }

  @Test
  public void run09() {
    List<Object> list = new ArrayList<>();
    list.add(1);
    list.add(2);
    list.add("3");
    Object max = engine.calc("max(.)", list);
    assertEquals("3", max);
  }

  @Test
  public void run10() {
    List<Object> list = new ArrayList<>();
    list.add("aaa");
    list.add("bbb");
    list.add(3);
    Object max = engine.calc("max(.)", list);
    assertEquals("bbb", max);
  }

}