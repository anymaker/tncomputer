package a2u.tn.utils.computer.calcobj.functions.datetime;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class ToTimeZoneTest {

  private ObjCalcEngine engine = new ObjCalcEngine();

  @Test
  public void run() {
    OffsetDateTime odt = OffsetDateTime.now();
    ZoneOffset offset = ZoneOffset.ofTotalSeconds(odt.getOffset().getTotalSeconds() + 2*60*60);
    OffsetDateTime result = engine.calc("toTimeZone(todate('"+odt.format(DateTimeFormatter.ofPattern("yyyy.MM.dd-HH:mm:ss"))+"', 'yyyy.MM.dd-HH:mm:ss'), '"+ offset.getId()+"')", OffsetDateTime.class);

    assertEquals(odt.plusHours(2L).getHour(), result.getHour());
    assertEquals(offset.getId(), result.getOffset().getId());
  }

}