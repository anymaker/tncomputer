package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.computer.formula.Formula;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LikeRegexpTest {

  private ObjCalcEngine engine;

  @Before
  public void prepare() {
    engine = new ObjCalcEngine();
  }

  @Test
  public void run() {
    assertTrue(engine.calc(new Formula("likeRegexp('abcdfab', '^a.*b$')"), Boolean.class));
    assertTrue(engine.calc(new Formula("likeRegexp('abcdfabghc', '^a.*b.*c$')"), Boolean.class));
  }

}