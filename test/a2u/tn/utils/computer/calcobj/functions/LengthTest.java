package a2u.tn.utils.computer.calcobj.functions;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.computer.formula.Formula;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class LengthTest {

  private ObjCalcEngine engine;

  @Before
  public void prapare() {
    engine = new ObjCalcEngine();
  }

  @Test
  public void run() {
    Formula formula = new Formula("length(null)");
    int len = engine.calc(formula, Integer.class);
    assertEquals(0, len);

    formula = new Formula("length('abcdf')");
    len = engine.calc(formula, Integer.class);
    assertEquals(5, len);

    formula = new Formula("length(1)");
    len = engine.calc(formula, Integer.class);
    assertEquals(1, len);

    formula = new Formula("length(123)");
    len = engine.calc(formula, Integer.class);
    assertEquals(3, len);

    formula = new Formula("length((1,2,3,4,5))");
    len = engine.calc(formula, Integer.class);
    assertEquals(5, len);

    Map<String, Integer> map = new HashMap<>();
    map.put("1", 1);
    map.put("2", 2);
    Map<String, Object> data = new HashMap<>();
    data.put("map", map);
    formula = new Formula("length(.map)");
    len = engine.calc(formula, data, Integer.class);
    assertEquals(2, len);
  }

}