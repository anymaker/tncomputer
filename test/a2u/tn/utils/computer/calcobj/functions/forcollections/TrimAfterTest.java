package a2u.tn.utils.computer.calcobj.functions.forcollections;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.computer.formula.Formula;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TrimAfterTest {

  private ObjCalcEngine engine;

  @Before
  public void prepare() {
    engine = new ObjCalcEngine();
  }

  @Test
  public void run() {

    assertEquals("[1, 2, 3]",       engine.calc(new Formula("trimAfter((1,2,3,4,5), 3)")).toString());
    assertEquals("[1, 2]",          engine.calc(new Formula("trimAfter((1,2,3,4,5), 3, false)")).toString());

    assertEquals("[]",              engine.calc(new Formula("trimAfter((1,2,3,4,5), 6)")).toString());
    assertEquals("[]",              engine.calc(new Formula("trimAfter((1,2,3,4,5), 6, false)")).toString());

    assertEquals("[1]",             engine.calc(new Formula("trimAfter((1,2,3,4,5), 1)")).toString());
    assertEquals("[]",              engine.calc(new Formula("trimAfter((1,2,3,4,5), 1, false)")).toString());

    assertEquals("[1, 2, 3, 4, 5]", engine.calc(new Formula("trimAfter((1,2,3,4,5), 5)")).toString());
    assertEquals("[1, 2, 3, 4]",    engine.calc(new Formula("trimAfter((1,2,3,4,5), 5, false)")).toString());

    assertEquals("[]",              engine.calc(new Formula("trimAfter((1,2,3,4,5), null)")).toString());
    assertEquals("[]",              engine.calc(new Formula("trimAfter(null, 5)")).toString());

  }

}