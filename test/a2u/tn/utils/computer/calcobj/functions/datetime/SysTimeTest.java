package a2u.tn.utils.computer.calcobj.functions.datetime;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.computer.formula.Formula;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class SysTimeTest {

  private ObjCalcEngine engine;
  private DateFormat df;

  @Before
  public void prapare() {
    engine = new ObjCalcEngine();
    df = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");
  }

  @Test
  public void run() {
    Formula formula = new Formula("sysTime");
    Date date1 = (Date) engine.calc(formula, null);
    Date date2 = new Date();

    String v1 = df.format(date1);
    String v2 = df.format(date2);

    assertEquals(v1, v2);

  }
  
}
