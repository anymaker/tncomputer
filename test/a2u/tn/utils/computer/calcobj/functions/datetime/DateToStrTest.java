package a2u.tn.utils.computer.calcobj.functions.datetime;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DateToStrTest {

  @Test
  public void run01() throws ParseException {
    ObjCalcEngine engine = new ObjCalcEngine();
    String etalon = "2018.11.23-15:43:38";

    DateFormat df  = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");
    Date date = df.parse(etalon);

    String result = engine.calc("dateToStr(., 'yyyy.MM.dd-HH:mm:ss')", date, String.class);
    assertEquals(etalon, result);

    LocalDateTime ldt = LocalDateTime.parse(etalon, DateTimeFormatter.ofPattern("yyyy.MM.dd-HH:mm:ss"));
    String result2 = engine.calc("dateToStr(., 'yyyy.MM.dd-HH:mm:ss')", ldt, String.class);
    assertEquals(etalon, result2);

    LocalDate ldate = LocalDate.parse(etalon, DateTimeFormatter.ofPattern("yyyy.MM.dd-HH:mm:ss"));
    String result3 = engine.calc("dateToStr(., 'yyyy.MM.dd-HH:mm:ss')", ldate, String.class);
    assertEquals("2018.11.23-00:00:00", result3);
  }

}