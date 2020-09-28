package a2u.tn.utils.computer.calcobj.functions.datetime;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import a2u.tn.utils.computer.formula.Formula;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.junit.Assert.*;

public class SysDateTest {

  private ObjCalcEngine engine;
  private DateFormat df;

  @Before
  public void prapare() {
    engine = new ObjCalcEngine();
    df = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");
  }

  @Test
  public void run() {
    Formula formula = new Formula("sysDate");
    Date date1 = engine.calc(formula, Date.class);

    LocalDateTime date2 = LocalDateTime.now();
    date2 = date2.truncatedTo(ChronoUnit.DAYS);

    String v1 = df.format(date1);
    String v2 = date2.format(DateTimeFormatter.ofPattern("yyyy.MM.dd-HH:mm:ss"));

    assertEquals(v1, v2);

  }
  
}
