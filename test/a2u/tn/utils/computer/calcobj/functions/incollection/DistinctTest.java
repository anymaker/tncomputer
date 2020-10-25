package a2u.tn.utils.computer.calcobj.functions.incollection;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class DistinctTest {

  private static ObjCalcEngine engine = new ObjCalcEngine();

  @Test
  public void run01() {
    List<String> list = new ArrayList<>();
    list.add("one");
    list.add("one");
    list.add(null);
    list.add("two");
    list.add("two");
    list.add("three");

    List result = engine.calc(".(distinct)", list, List.class);
    assertEquals(4, result.size());
    assertEquals("[one, null, two, three]", result.toString());
  }

  @Test
  public void run02() {
    List<String> list = new ArrayList<>();
    list.add("one");
    list.add("one");
    list.add(null);
    list.add("two");
    list.add("two");
    list.add("three");

    Map<String, Object> map = new HashMap<>();
    map.put("list", list);

    List result = engine.calc(".list(distinct)", map, List.class);
    assertEquals(4, result.size());
    assertEquals("[one, null, two, three]", result.toString());
  }

}