package a2u.tn.utils.computer.calcobj.functions.datetime;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.computer.formula.Formula;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class AddDaysTest {

  private ObjCalcEngine engine;
  private DateFormat df;

  @Before
  public void prapare() {
    engine = new ObjCalcEngine();
    df = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");
  }

  @Test
  public void testPlus() {
    Formula formula = new Formula("adddays(todate('2018.01.01', 'yyyy.MM.dd'), 5)");
    Date date = engine.calc(formula, Date.class);
    String res = df.format(date);

    assertEquals("2018.01.06-00:00:00", res);
  }

  @Test
  public void testMinus() {
    Formula formula = new Formula("adddays(todate('2018.01.01', 'yyyy.MM.dd'), -5)");
    Date date = engine.calc(formula, Date.class);
    String res = df.format(date);

    assertEquals("2017.12.27-00:00:00", res);
  }

}
