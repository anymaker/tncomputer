package a2u.tn.utils.computer.calcobj.functions.forcollections;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.computer.formula.Formula;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TrimToTest {

  private ObjCalcEngine engine;

  @Before
  public void prepare() {
    engine = new ObjCalcEngine();
  }

  @Test
  public void run() {

    assertEquals("[3, 4, 5]", engine.calc(new Formula("trimTo((1,2,3,4,5), 3)")).toString());
    assertEquals("[4, 5]", engine.calc(new Formula("trimTo((1,2,3,4,5), 3, false)")).toString());

    assertEquals("[]", engine.calc(new Formula("trimTo((1,2,3,4,5), 6)")).toString());
    assertEquals("[]", engine.calc(new Formula("trimTo((1,2,3,4,5), 6, false)")).toString());

    assertEquals("[1, 2, 3, 4, 5]", engine.calc(new Formula("trimTo((1,2,3,4,5), 1)")).toString());

    assertEquals("[5]", engine.calc(new Formula("trimTo((1,2,3,4,5), 5)")).toString());
    assertEquals("[]", engine.calc(new Formula("trimTo((1,2,3,4,5), 5, false)")).toString());

    assertEquals("[]", engine.calc(new Formula("trimTo((1,2,3,4,5), null)")).toString());
    assertEquals("[]", engine.calc(new Formula("trimTo(null, 5)")).toString());

  }


}
