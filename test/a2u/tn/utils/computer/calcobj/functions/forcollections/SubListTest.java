package a2u.tn.utils.computer.calcobj.functions.forcollections;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.computer.formula.Formula;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SubListTest {

  private ObjCalcEngine engine;

  @Before
  public void prepare() {
    engine = new ObjCalcEngine();
  }

  @Test
  public void run() {

    assertEquals("[2, 3, 4]",       engine.calc(new Formula("subList((1,2,3,4,5), 1, 3)")).toString());
    assertEquals("[3, 4]",          engine.calc(new Formula("subList((1,2,3,4,5), 1, 3, false)")).toString());
    assertEquals("[2, 3]",          engine.calc(new Formula("subList((1,2,3,4,5), 1, 3, true, false)")).toString());
    assertEquals("[3, 4]",          engine.calc(new Formula("subList((1,2,3,4,5), 1, 3, false, true)")).toString());
    assertEquals("[2, 3, 4]",       engine.calc(new Formula("subList((1,2,3,4,5), 1, 3, true, true)")).toString());

    assertEquals("[1, 2, 3, 4, 5]", engine.calc(new Formula("subList((1,2,3,4,5), 0, 4)")).toString());
    assertEquals("[2, 3, 4, 5]",    engine.calc(new Formula("subList((1,2,3,4,5), 0, 4, false)")).toString());
    assertEquals("[1, 2, 3, 4]",    engine.calc(new Formula("subList((1,2,3,4,5), 0, 4, true, false)")).toString());
    assertEquals("[2, 3, 4, 5]",    engine.calc(new Formula("subList((1,2,3,4,5), 0, 4, false, true)")).toString());
    assertEquals("[1, 2, 3, 4, 5]", engine.calc(new Formula("subList((1,2,3,4,5), 0, 4, true, true)")).toString());

    assertEquals("[5]",             engine.calc(new Formula("subList((1,2,3,4,5), 100, 4)")).toString());
    assertEquals("[]",              engine.calc(new Formula("subList((1,2,3,4,5), 100, 4, false)")).toString());
    assertEquals("[]",              engine.calc(new Formula("subList((1,2,3,4,5), 100, 4, true, false)")).toString());
    assertEquals("[]",              engine.calc(new Formula("subList((1,2,3,4,5), 100, 4, false, true)")).toString());
    assertEquals("[5]",             engine.calc(new Formula("subList((1,2,3,4,5), 100, 4, true, true)")).toString());

    assertEquals("[2, 3, 4, 5]",    engine.calc(new Formula("subList((1,2,3,4,5), -3, 4)")).toString());
    assertEquals("[3, 4, 5]",       engine.calc(new Formula("subList((1,2,3,4,5), -3, 4, false)")).toString());
    assertEquals("[2, 3, 4]",       engine.calc(new Formula("subList((1,2,3,4,5), -3, 4, true, false)")).toString());
    assertEquals("[3, 4, 5]",       engine.calc(new Formula("subList((1,2,3,4,5), -3, 4, false, true)")).toString());
    assertEquals("[2, 3, 4, 5]",    engine.calc(new Formula("subList((1,2,3,4,5), -3, 4, true, true)")).toString());

    assertEquals("[1, 2, 3, 4, 5]", engine.calc(new Formula("subList((1,2,3,4,5), -100, 4)")).toString());
    assertEquals("[2, 3, 4, 5]",    engine.calc(new Formula("subList((1,2,3,4,5), -100, 4, false)")).toString());
    assertEquals("[1, 2, 3, 4]",    engine.calc(new Formula("subList((1,2,3,4,5), -100, 4, true, false)")).toString());
    assertEquals("[2, 3, 4, 5]",    engine.calc(new Formula("subList((1,2,3,4,5), -100, 4, false, true)")).toString());
    assertEquals("[1, 2, 3, 4, 5]", engine.calc(new Formula("subList((1,2,3,4,5), -100, 4, true, true)")).toString());

    assertEquals("[2, 3, 4, 5]",    engine.calc(new Formula("subList((1,2,3,4,5), 1, 40)")).toString());
    assertEquals("[3, 4, 5]",       engine.calc(new Formula("subList((1,2,3,4,5), 1, 40, false)")).toString());
    assertEquals("[2, 3, 4]",       engine.calc(new Formula("subList((1,2,3,4,5), 1, 40, true, false)")).toString());
    assertEquals("[3, 4, 5]",       engine.calc(new Formula("subList((1,2,3,4,5), 1, 40, false, true)")).toString());
    assertEquals("[2, 3, 4, 5]",    engine.calc(new Formula("subList((1,2,3,4,5), 1, 40, true, true)")).toString());

    assertEquals("[1, 2]",          engine.calc(new Formula("subList((1,2,3,4,5), 1, 0)")).toString());
    assertEquals("[2]",             engine.calc(new Formula("subList((1,2,3,4,5), 1, 0, false)")).toString());
    assertEquals("[1]",             engine.calc(new Formula("subList((1,2,3,4,5), 1, 0, true, false)")).toString());
    assertEquals("[2]",             engine.calc(new Formula("subList((1,2,3,4,5), 1, 0, false, true)")).toString());
    assertEquals("[1, 2]",          engine.calc(new Formula("subList((1,2,3,4,5), 1, 0, true, true)")).toString());

    assertEquals("[2, 3, 4]",       engine.calc(new Formula("subList((1,2,3,4,5), 1, -1)")).toString());
    assertEquals("[3, 4]",          engine.calc(new Formula("subList((1,2,3,4,5), 1, -1, false)")).toString());
    assertEquals("[2, 3]",          engine.calc(new Formula("subList((1,2,3,4,5), 1, -1, true, false)")).toString());
    assertEquals("[3, 4]",          engine.calc(new Formula("subList((1,2,3,4,5), 1, -1, false, true)")).toString());
    assertEquals("[2, 3, 4]",       engine.calc(new Formula("subList((1,2,3,4,5), 1, -1, true, true)")).toString());

    assertEquals("[1, 2]",          engine.calc(new Formula("subList((1,2,3,4,5), 1, -100)")).toString());
    assertEquals("[2]",             engine.calc(new Formula("subList((1,2,3,4,5), 1, -100, false)")).toString());
    assertEquals("[1]",             engine.calc(new Formula("subList((1,2,3,4,5), 1, -100, true, false)")).toString());
    assertEquals("[2]",             engine.calc(new Formula("subList((1,2,3,4,5), 1, -100, false, true)")).toString());
    assertEquals("[1, 2]",          engine.calc(new Formula("subList((1,2,3,4,5), 1, -100, true, true)")).toString());


    assertEquals("[]", engine.calc(new Formula("subList(null, 1, 3)")).toString());
    assertEquals("[]", engine.calc(new Formula("subList(toList(), 1, 3)")).toString());
    assertEquals("[]", engine.calc(new Formula("subList((1,2,3,4,5), 100, 100)")).toString());

  }
}