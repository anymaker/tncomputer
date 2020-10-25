package a2u.tn.utils.computer.calcobj.functions.incollection;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class MaxInRowsTest {

  private static ObjCalcEngine engine = new ObjCalcEngine();

  @Test
  public void run01() {
    List<Object> list = new ArrayList<>();

    Map<String, Object> map = new HashMap<>();
    map.put("name", "AAA");
    map.put("value", 1);
    list.add(map);

    map = new HashMap<>();
    map.put("name", "BBB");
    map.put("value", 2);
    list.add(map);

    map = new HashMap<>();
    map.put("name", "CCC");
    map.put("value", 5);
    list.add(map);

    map = new HashMap<>();
    map.put("name", "DDD");
    map.put("value", 3);
    list.add(map);

    String result = engine.calc("   .(.value = maxInRows(.value)).name   ", list, String.class);
    assertEquals("CCC", result);
  }

}