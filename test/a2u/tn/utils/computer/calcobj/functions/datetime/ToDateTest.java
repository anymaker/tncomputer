package a2u.tn.utils.computer.calcobj.functions.datetime;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class ToDateTest {

  @Test
  public void run01() throws ParseException {
    ObjCalcEngine engine = new ObjCalcEngine();
    DateFormat df  = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");

    Date result = engine.calc("todate('2018.11.23-15:43:38', 'yyyy.MM.dd-HH:mm:ss')", Date.class);
    Date sample = df.parse("2018.11.23-15:43:38");
    assertEquals(sample, result);
  }

}