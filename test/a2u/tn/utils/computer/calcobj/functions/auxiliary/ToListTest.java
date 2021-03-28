package a2u.tn.utils.computer.calcobj.functions.auxiliary;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.computer.formula.Formula;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ToListTest {

  private ObjCalcEngine engine;

  @Before
  public void prapare() {
    engine = new ObjCalcEngine();
  }

  @Test
  public void toList() {
    Map<String, Object> data = new LinkedHashMap<>();
    data.put("value1", 100);
    data.put("value2", 200);

    Formula formula = new Formula("toList(.value1, .value2)");
    Object result = engine.calc(formula, data);
    assertEquals(ArrayList.class, result.getClass());

    List list = (List) result;
    assertEquals(100, list.get(0));
    assertEquals(200, list.get(1));
  }

  @Test
  public void emptyList() {
    Formula formula = new Formula("toList()");
    Object result = engine.calc(formula);
    assertEquals(ArrayList.class, result.getClass());

    List list = (List) result;
    assertEquals(true, list.isEmpty());
  }

}