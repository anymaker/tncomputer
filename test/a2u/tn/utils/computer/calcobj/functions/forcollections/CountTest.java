package a2u.tn.utils.computer.calcobj.functions.forcollections;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class CountTest {

  private static ObjCalcEngine engine = new ObjCalcEngine();

  @Test
  public void run01() {
    try {
      int cnt = (int) engine.calc("count()");
      fail("Expected CalculatingException");
    }
    catch (Exception ex) {
      assertEquals("Error calculate formula:\n" +
        "count()\n" +
        "ERROR: a2u.tn.utils.computer.calculator.CalculatingException: Parameter 'collection' is not specified for function 'count'.\n", ex.getMessage());
    }
  }

  @Test
  public void run02() {
    int cnt = (int) engine.calc("count(null)");
    assertEquals(0, cnt);
  }

  @Test
  public void run03() {
    int cnt = (int) engine.calc("count(1)");
    assertEquals(1, cnt);
  }

  @Test
  public void run04() {
    int cnt = (int) engine.calc("count(1,2,3)");
    assertEquals(1, cnt);
  }

  @Test
  public void run05() {
    int cnt = (int) engine.calc("count(null,2,3)");
    assertEquals(0, cnt);
  }

  @Test
  public void run06() {
    int cnt = (int) engine.calc("count((1,2,3))");
    assertEquals(3, cnt);
  }

  @Test
  public void run07() {
    int cnt = (int) engine.calc("count((1,2,3), 4,5,6)");
    assertEquals(3, cnt);
  }

  @Test
  public void run08() {
    int cnt = (int) engine.calc("count((1,2,3), (4,5,6))");
    assertEquals(3, cnt);
  }

  @Test
  public void run09() {
    int cnt = (int) engine.calc("count((1,2,3), (4,5,6), 7,8)");
    assertEquals(3, cnt);
  }

  @Test
  public void run10() {
    List<String> list = new ArrayList<>();
    list.add("one");
    list.add("two");
    list.add("three");

    Map<String, Object> map = new HashMap<>();
    map.put("data", list);

    int cnt = (int) engine.calc("count(.data)", map);
    assertEquals(3, cnt);
  }

  @Test
  public void run11() {
    List<String> list = new ArrayList<>();
    list.add("one");
    list.add(null);
    list.add("two");
    list.add("three");

    int cnt = (int) engine.calc("count(.)", list);
    assertEquals(3, cnt);
  }

}