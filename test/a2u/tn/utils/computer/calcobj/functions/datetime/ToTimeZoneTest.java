package a2u.tn.utils.computer.calcobj.functions.datetime;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ToTimeZoneTest {

  private final ObjCalcEngine engine = new ObjCalcEngine();

  @Test
  public void run() {
    OffsetDateTime odt = OffsetDateTime.now();
    ZoneOffset offset = ZoneOffset.ofTotalSeconds(odt.getOffset().getTotalSeconds() + 2*60*60);
    OffsetDateTime result = engine.calc("toTimeZone(toDate('"+odt.format(DateTimeFormatter.ofPattern("yyyy.MM.dd-HH:mm:ss"))+"', 'yyyy.MM.dd-HH:mm:ss'), '"+ offset.getId()+"')", OffsetDateTime.class);

    assertEquals(odt.plusHours(2L).getHour(), result.getHour());
    assertEquals(offset.getId(), result.getOffset().getId());
  }

  @Test
  public void run02() {
    Map<String, Object> map = new LinkedHashMap<>();
    Map<String, Object> map2 = new LinkedHashMap<>();
    map2.put("zone", "+7");
    map.put("params", map2);

    OffsetDateTime result = engine.calc("toTimeZone(toDate('2020-03-23T12:54:16+03:00', 'yyyy-MM-dd\\'T\\'HH:mm:ssXXX'), .params.zone)", map, OffsetDateTime.class);
    assertEquals("2020-03-23T16:54:16+07:00", result.format(DateTimeFormatter.ISO_DATE_TIME));
  }

  @Test
  public void run03() {
    String result = engine.calc(
      "dateToStr(toTimeZone(toDate('2020.03.23 12:54:16+03:00', 'yyyy.MM.dd HH:mm:ssXXX'), " +
        "                          '+05'" +
        "                         ), " +
        "               'yyyy.MM.dd HH:mm:ssXXX'" +
        "              )", String.class);
    assertEquals("2020.03.23 14:54:16+05:00", result);
  }

}