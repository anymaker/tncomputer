package a2u.tn.utils.computer.calcobj.functions.datetime;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.computer.formula.Formula;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class LastDayInYearTest {
  private ObjCalcEngine engine;
  private DateFormat df;

  @Before
  public void prepare() {
    engine = new ObjCalcEngine();
    df = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");
  }

  @Test
  public void run01() {
    Formula formula = new Formula("lastDayInYear(todate('2018.02.01', 'yyyy.MM.dd'))");
    Date date = engine.calc(formula, Date.class);
    String res = df.format(date);
    assertEquals("2018.12.31-00:00:00", res);
  }

  @Test
  public void run02() {
    Formula formula = new Formula("lastDayInYear(todate('2018.02.01-10:35:17', 'yyyy.MM.dd-HH:mm:ss'))");
    Date date = engine.calc(formula, Date.class);
    String res = df.format(date);
    assertEquals("2018.12.31-10:35:17", res);
  }
}
