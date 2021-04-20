package a2u.tn.utils.computer.calcobj;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ObjCalcEngineTest {


  public static class Parent {
    public int field01;
    private String field02;

    public void setField01(int field01) {
      this.field01 = field01;
    }
    public void setField02(String field02) {
      this.field02 = field02;
    }
    public int getField01() {
      return field01;
    }
    public String getField02() {
      return field02;
    }
  }

  public static class Children extends Parent {
    private int field03;

    public int getField03() {
      return field03;
    }
  }

  private ObjCalcEngine engine;

  @Before
  public void prepare() {
    engine = new ObjCalcEngine();
  }


  @Test
  public void test01() {
    Children cl = new Children();
    cl.field01 = 1;
    cl.setField01(2);
    cl.setField02("dss");

    int f01 = engine.calc(".field01", cl, int.class);
    System.out.println(f01);

    int f011 = engine.calc(".field011", cl, int.class);
    System.out.println(f011);
  }


}