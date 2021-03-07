package a2u.tn.utils.computer.calcobj.types;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TBoolTest {

  private ObjCalcEngine engine;

  @Before
  public void prepare() {
    engine = new ObjCalcEngine();
  }


  @Test
  public void convert() {

    boolean res = engine.toType(boolean.class, null);
    assertEquals(false, res);

    res = engine.toType(Boolean.class, null);
    assertEquals(false, res);


    Boolean resB = engine.toType(boolean.class, null);
    assertEquals(false, resB);

    resB = engine.toType(Boolean.class, null);
    assertEquals(false, resB);


    res = engine.toType(boolean.class, true);
    assertEquals(true, res);

    res = engine.toType(Boolean.class, true);
    assertEquals(true, res);

  }


}