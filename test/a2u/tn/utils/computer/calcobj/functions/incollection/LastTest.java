package a2u.tn.utils.computer.calcobj.functions.incollection;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LastTest {

  private static ObjCalcEngine engine = new ObjCalcEngine();

  @Test
  public void run01() {
    List<String> list = new ArrayList<>();
    list.add("one");
    list.add(null);
    list.add("two");
    list.add("three");

    String result = engine.calc(".(last)", list, String.class);
    assertEquals("three", result);
  }

}