package a2u.tn.utils.computer.calcobj.functions.auxiliary;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.computer.formula.Formula;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ToMapTest {

  private ObjCalcEngine engine;

  @Before
  public void prapare() {
    engine = new ObjCalcEngine();
  }

  @Test
  public void toMap() {
    Map<String, Object> data = new LinkedHashMap<>();
    data.put("value1", 100);
    data.put("value2", 200);

    Formula formula = new Formula("tomap(('key1', .value1),('key2', .value2))");
    Object result = engine.calc(formula, data);
    assertEquals(LinkedHashMap.class, result.getClass());

    Map map = (Map) result;
    assertEquals(100, map.get("key1"));
    assertEquals(200, map.get("key2"));
  }

  @Test
  public void emptyMap() {
    Formula formula = new Formula("tomap()");
    Object result = engine.calc(formula);
    assertEquals(LinkedHashMap.class, result.getClass());

    Map map = (Map) result;
    assertEquals(true, map.isEmpty());
  }

}