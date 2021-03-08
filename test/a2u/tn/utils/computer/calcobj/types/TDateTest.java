package a2u.tn.utils.computer.calcobj.types;

import a2u.tn.utils.computer.calcobj.ObjCalcEngine;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TDateTest {

  private ObjCalcEngine engine;

  @Before
  public void prepare() {
    engine = new ObjCalcEngine();
  }

  @Test
  public void convertToDate() throws Exception {
    Date res = engine.toType(Date.class, null);
    assertEquals(null, res);

    res = engine.toType(Date.class, new Date(1L));
    assertEquals(new Date(1L), res);

    res = engine.toType(Date.class, 1L);
    assertEquals(new Date(1L), res);

    res = engine.toType(Date.class, 1L);
    assertEquals(new Date(1L), res);

    Object obj = 0L;
    res = engine.toType(Date.class, obj);
    assertEquals(new Date(0), res);


    SimpleDateFormat df0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSSXXX");
    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");

    // Long
    res = engine.toType(Date.class, LocalDateTime.of(2021, 2, 25, 13, 51, 15).toInstant(OffsetDateTime.now().getOffset()).toEpochMilli());
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), df0.format(res));

    // LocalDateTime
    res = engine.toType(Date.class, LocalDateTime.of(2021, 2, 25, 13, 51, 15));
    assertEquals("2021-02-25 13:51:15", df1.format(res));

    // LocalDate
    res = engine.toType(Date.class, LocalDate.of(2021, 2, 25));
    assertEquals("2021-02-25", df2.format(res));

    // OffsetDateTime
    res = engine.toType(Date.class, OffsetDateTime.of(2021, 2, 25, 13, 51, 15, 0, OffsetDateTime.now().getOffset()));
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), df0.format(res));

    // ZonedDateTime
    res = engine.toType(Date.class, ZonedDateTime.of(2021, 2, 25, 13, 51, 15, 0, OffsetDateTime.now().getOffset()));
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), df0.format(res));

    // Instant
    res = engine.toType(Date.class, Instant.ofEpochMilli(df0.parse("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset()).getTime()));
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), df0.format(res));

    // Calendar
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(df1.parse("2021-02-25 13:51:15"));
    res = engine.toType(Date.class, calendar);
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), df0.format(res));
  }

  @Test
  public void convertToLong() throws Exception {
    Long res = engine.toType(Long.class, null);
    assertEquals(null, res);

    SimpleDateFormat df0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSSXXX");
    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(df1.parse("2021-02-25 13:51:15"));

    // Date
    res = engine.toType(Long.class, calendar.getTime());
    assertEquals("2021-02-25 13:51:15", df1.format(res));

    // Long
    res = engine.toType(Long.class, LocalDateTime.of(2021, 2, 25, 13, 51, 15).toInstant(OffsetDateTime.now().getOffset()).toEpochMilli());
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), df0.format(res));

    // LocalDateTime
    res = engine.toType(Long.class, LocalDateTime.of(2021, 2, 25, 13, 51, 15));
    assertEquals("2021-02-25 13:51:15", df1.format(res));

    // LocalDate
    res = engine.toType(Long.class, LocalDate.of(2021, 2, 25));
    assertEquals("2021-02-25", df2.format(res));

    // OffsetDateTime
    res = engine.toType(Long.class, OffsetDateTime.of(2021, 2, 25, 13, 51, 15, 0, OffsetDateTime.now().getOffset()));
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), df0.format(res));

    // ZonedDateTime
    res = engine.toType(Long.class, ZonedDateTime.of(2021, 2, 25, 13, 51, 15, 0, OffsetDateTime.now().getOffset()));
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), df0.format(res));

    // Instant
    res = engine.toType(Long.class, Instant.ofEpochMilli(df0.parse("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset()).getTime()));
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), df0.format(res));

    // Calendar
    res = engine.toType(Long.class, calendar);
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), df0.format(res));
  }

  @Test
  public void convertToLLong() throws Exception {
    long res = engine.toType(long.class, null);
    assertEquals(0L, res);

    SimpleDateFormat df0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSSXXX");
    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(df1.parse("2021-02-25 13:51:15"));

    // Date
    res = engine.toType(long.class, calendar.getTime());
    assertEquals("2021-02-25 13:51:15", df1.format(res));

    // Long
    res = engine.toType(Long.class, LocalDateTime.of(2021, 2, 25, 13, 51, 15).toInstant(OffsetDateTime.now().getOffset()).toEpochMilli());
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), df0.format(res));

    // LocalDateTime
    res = engine.toType(long.class, LocalDateTime.of(2021, 2, 25, 13, 51, 15));
    assertEquals("2021-02-25 13:51:15", df1.format(res));

    // LocalDate
    res = engine.toType(long.class, LocalDate.of(2021, 2, 25));
    assertEquals("2021-02-25", df2.format(res));

    // OffsetDateTime
    res = engine.toType(long.class, OffsetDateTime.of(2021, 2, 25, 13, 51, 15, 0, OffsetDateTime.now().getOffset()));
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), df0.format(res));

    // ZonedDateTime
    res = engine.toType(long.class, ZonedDateTime.of(2021, 2, 25, 13, 51, 15, 0, OffsetDateTime.now().getOffset()));
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), df0.format(res));

    // Instant
    res = engine.toType(long.class, Instant.ofEpochMilli(df0.parse("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset()).getTime()));
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), df0.format(res));

    // Calendar
    res = engine.toType(long.class, calendar);
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), df0.format(res));
  }

  @Test
  public void convertToLocalDateTime() throws Exception {
    LocalDateTime res = engine.toType(LocalDateTime.class, null);
    assertEquals(null, res);

    SimpleDateFormat df0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSSXXX");
    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");

    DateTimeFormatter dtf0 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSSXXX");
    DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter dtf10 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone( ZoneId.systemDefault() );

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(df1.parse("2021-02-25 13:51:15"));

    // Date
    res = engine.toType(LocalDateTime.class, calendar.getTime());
    assertEquals("2021-02-25 13:51:15", dtf1.format(res));

    // Long
    res = engine.toType(LocalDateTime.class, LocalDateTime.of(2021, 2, 25, 13, 51, 15).toInstant(OffsetDateTime.now().getOffset()).toEpochMilli());
    assertEquals("2021-02-25 13:51:15", dtf10.format(res));

    // LocalDateTime
    res = engine.toType(LocalDateTime.class, LocalDateTime.of(2021, 2, 25, 13, 51, 15));
    assertEquals("2021-02-25 13:51:15", dtf1.format(res));

    // LocalDate
    res = engine.toType(LocalDateTime.class, LocalDate.of(2021, 2, 25));
    assertEquals("2021-02-25", dtf2.format(res));

    // OffsetDateTime
    res = engine.toType(LocalDateTime.class, OffsetDateTime.of(2021, 2, 25, 13, 51, 15, 0, OffsetDateTime.now().getOffset()));
    assertEquals("2021-02-25 13:51:15", dtf1.format(res));

    // ZonedDateTime
    res = engine.toType(LocalDateTime.class, ZonedDateTime.of(2021, 2, 25, 13, 51, 15, 0, OffsetDateTime.now().getOffset()));
    assertEquals("2021-02-25 13:51:15", dtf1.format(res));

    // Instant
    res = engine.toType(LocalDateTime.class, Instant.ofEpochMilli(df0.parse("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset()).getTime()));
    assertEquals("2021-02-25 13:51:15", dtf1.format(res));

    // Calendar
    res = engine.toType(LocalDateTime.class, calendar);
    assertEquals("2021-02-25 13:51:15", dtf1.format(res));
  }

  @Test
  public void convertToLocalDate() throws Exception {
    LocalDate res = engine.toType(LocalDate.class, null);
    assertEquals(null, res);

    SimpleDateFormat df0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSSXXX");
    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");

    DateTimeFormatter dtf0 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSSXXX");
    DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter dtf20 = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone( ZoneId.systemDefault() );

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(df1.parse("2021-02-25 13:51:15"));

    // Date
    res = engine.toType(LocalDate.class, calendar.getTime());
    assertEquals("2021-02-25", dtf2.format(res));

    // Long
    res = engine.toType(LocalDate.class, LocalDateTime.of(2021, 2, 25, 13, 51, 15).toInstant(OffsetDateTime.now().getOffset()).toEpochMilli());
    assertEquals("2021-02-25", dtf20.format(res));

    // LocalDateTime
    res = engine.toType(LocalDate.class, LocalDateTime.of(2021, 2, 25, 13, 51, 15));
    assertEquals("2021-02-25", dtf2.format(res));

    // LocalDate
    res = engine.toType(LocalDate.class, LocalDate.of(2021, 2, 25));
    assertEquals("2021-02-25", dtf2.format(res));

    // OffsetDateTime
    res = engine.toType(LocalDate.class, OffsetDateTime.of(2021, 2, 25, 13, 51, 15, 0, OffsetDateTime.now().getOffset()));
    assertEquals("2021-02-25", dtf2.format(res));

    // ZonedDateTime
    res = engine.toType(LocalDate.class, ZonedDateTime.of(2021, 2, 25, 13, 51, 15, 0, OffsetDateTime.now().getOffset()));
    assertEquals("2021-02-25", dtf2.format(res));

    // Instant
    res = engine.toType(LocalDate.class, Instant.ofEpochMilli(df0.parse("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset()).getTime()));
    assertEquals("2021-02-25", dtf2.format(res));

    // Calendar
    res = engine.toType(LocalDate.class, calendar);
    assertEquals("2021-02-25", dtf2.format(res));
  }

  @Test
  public void convertToOffsetDateTime() throws Exception {
    OffsetDateTime res = engine.toType(OffsetDateTime.class, null);
    assertEquals(null, res);

    SimpleDateFormat df0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSSXXX");
    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");

    DateTimeFormatter dtf0 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSSXXX");
    DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(df1.parse("2021-02-25 13:51:15"));

    // Date
    res = engine.toType(OffsetDateTime.class, calendar.getTime());
    assertEquals("2021-02-25 13:51:15", dtf1.format(res));

    // Long
    res = engine.toType(OffsetDateTime.class, LocalDateTime.of(2021, 2, 25, 13, 51, 15).toInstant(OffsetDateTime.now().getOffset()).toEpochMilli());
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), dtf0.format(res));

    // LocalDateTime
    res = engine.toType(OffsetDateTime.class, LocalDateTime.of(2021, 2, 25, 13, 51, 15));
    assertEquals("2021-02-25 13:51:15", dtf1.format(res));

    // LocalDate
    res = engine.toType(OffsetDateTime.class, LocalDate.of(2021, 2, 25));
    assertEquals("2021-02-25", dtf2.format(res));

    // OffsetDateTime
    res = engine.toType(OffsetDateTime.class, OffsetDateTime.of(2021, 2, 25, 13, 51, 15, 0, OffsetDateTime.now().getOffset()));
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), dtf0.format(res));

    // ZonedDateTime
    res = engine.toType(OffsetDateTime.class, ZonedDateTime.of(2021, 2, 25, 13, 51, 15, 0, OffsetDateTime.now().getOffset()));
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), dtf0.format(res));

    // Instant
    res = engine.toType(OffsetDateTime.class, Instant.ofEpochMilli(df0.parse("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset()).getTime()));
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), dtf0.format(res));

    // Calendar
    res = engine.toType(OffsetDateTime.class, calendar);
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), dtf0.format(res));
  }

  @Test
  public void convertToZonedDateTime() throws Exception {
    ZonedDateTime res = engine.toType(ZonedDateTime.class, null);
    assertEquals(null, res);

    SimpleDateFormat df0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSSXXX");
    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");

    DateTimeFormatter dtf0 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSSXXX");
    DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(df1.parse("2021-02-25 13:51:15"));

    // Date
    res = engine.toType(ZonedDateTime.class, calendar.getTime());
    assertEquals("2021-02-25 13:51:15", dtf1.format(res));

    // Long
    res = engine.toType(ZonedDateTime.class, LocalDateTime.of(2021, 2, 25, 13, 51, 15).toInstant(OffsetDateTime.now().getOffset()).toEpochMilli());
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), dtf0.format(res));

    // LocalDateTime
    res = engine.toType(ZonedDateTime.class, LocalDateTime.of(2021, 2, 25, 13, 51, 15));
    assertEquals("2021-02-25 13:51:15", dtf1.format(res));

    // LocalDate
    res = engine.toType(ZonedDateTime.class, LocalDate.of(2021, 2, 25));
    assertEquals("2021-02-25", dtf2.format(res));

    // OffsetDateTime
    res = engine.toType(ZonedDateTime.class, OffsetDateTime.of(2021, 2, 25, 13, 51, 15, 0, OffsetDateTime.now().getOffset()));
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), dtf0.format(res));

    // ZonedDateTime
    res = engine.toType(ZonedDateTime.class, ZonedDateTime.of(2021, 2, 25, 13, 51, 15, 0, OffsetDateTime.now().getOffset()));
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), dtf0.format(res));

    // Instant
    res = engine.toType(ZonedDateTime.class, Instant.ofEpochMilli(df0.parse("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset()).getTime()));
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), dtf0.format(res));

    // Calendar
    res = engine.toType(ZonedDateTime.class, calendar);
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), dtf0.format(res));
  }

  @Test
  public void convertToInstant() throws Exception {
    Instant res = engine.toType(Instant.class, null);
    assertEquals(null, res);

    SimpleDateFormat df0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSSXXX");
    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");

    DateTimeFormatter dtf0 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSSXXX");
    DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    DateTimeFormatter dtf10 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSSXXX").withZone( ZoneId.systemDefault() );
    DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(df1.parse("2021-02-25 13:51:15"));

    // Date
    res = engine.toType(Instant.class, calendar.getTime());
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), dtf10.format(res));

    // Long
    res = engine.toType(Instant.class, LocalDateTime.of(2021, 2, 25, 13, 51, 15).toInstant(OffsetDateTime.now().getOffset()).toEpochMilli());
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), dtf10.format(res));

    // LocalDateTime
    res = engine.toType(Instant.class, LocalDateTime.of(2021, 2, 25, 13, 51, 15));
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), dtf10.format(res));

    // LocalDate
    res = engine.toType(Instant.class, LocalDate.of(2021, 2, 25));
    assertEquals("2021-02-25 00:00:00:000"+OffsetDateTime.now().getOffset(), dtf10.format(res));

    // OffsetDateTime
    res = engine.toType(Instant.class, OffsetDateTime.of(2021, 2, 25, 13, 51, 15, 0, OffsetDateTime.now().getOffset()));
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), dtf10.format(res));

    // ZonedDateTime
    res = engine.toType(Instant.class, ZonedDateTime.of(2021, 2, 25, 13, 51, 15, 0, OffsetDateTime.now().getOffset()));
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), dtf10.format(res));

    // Instant
    res = engine.toType(Instant.class, Instant.ofEpochMilli(df0.parse("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset()).getTime()));
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), dtf10.format(res));

    // Calendar
    res = engine.toType(Instant.class, calendar);
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), dtf10.format(res));
  }

  @Test
  public void convertToCalendar() throws Exception {
    Calendar res = engine.toType(Calendar.class, null);
    assertEquals(null, res);

    SimpleDateFormat df0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSSXXX");
    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");

    DateTimeFormatter dtf0 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSSXXX");
    DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(df1.parse("2021-02-25 13:51:15"));

    // Date
    res = engine.toType(Calendar.class, calendar.getTime());
    assertEquals("2021-02-25 13:51:15", df1.format(res.getTime()));

    // Long
    res = engine.toType(Calendar.class, LocalDateTime.of(2021, 2, 25, 13, 51, 15).toInstant(OffsetDateTime.now().getOffset()).toEpochMilli());
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), df0.format(res.getTime()));

    // LocalDateTime
    res = engine.toType(Calendar.class, LocalDateTime.of(2021, 2, 25, 13, 51, 15));
    assertEquals("2021-02-25 13:51:15", df1.format(res.getTime()));

    // LocalDate
    res = engine.toType(Calendar.class, LocalDate.of(2021, 2, 25));
    assertEquals("2021-02-25", df2.format(res.getTime()));

    // OffsetDateTime
    res = engine.toType(Calendar.class, OffsetDateTime.of(2021, 2, 25, 13, 51, 15, 0, OffsetDateTime.now().getOffset()));
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), df0.format(res.getTime()));

    // ZonedDateTime
    res = engine.toType(Calendar.class, ZonedDateTime.of(2021, 2, 25, 13, 51, 15, 0, OffsetDateTime.now().getOffset()));
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), df0.format(res.getTime()));

    // Instant
    res = engine.toType(Calendar.class, Instant.ofEpochMilli(df0.parse("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset()).getTime()));
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), df0.format(res.getTime()));

    // Calendar
    res = engine.toType(Calendar.class, calendar);
    assertEquals("2021-02-25 13:51:15:000"+OffsetDateTime.now().getOffset(), df0.format(res.getTime()));
  }

  @Test
  public void equal() {
    TDate tDate = new TDate(engine);

    OffsetDateTime d1 = OffsetDateTime.of(2021, 2, 25, 13, 51, 15, 0, ZoneOffset.ofHoursMinutes(3, 00));
    OffsetDateTime d2 = OffsetDateTime.of(2021, 2, 25, 13, 51, 15, 0, ZoneOffset.ofHoursMinutes(10, 30));
    OffsetDateTime d3 = OffsetDateTime.of(2021, 2, 25, 13, 51, 15, 0, ZoneOffset.ofHoursMinutes(3, 00));

    assertTrue(tDate.equal(d1, d1));
    assertFalse(tDate.equal(d1, d2));
    assertTrue(tDate.equal(d1, d3));
  }

  @Test
  public void notequal() {
  }

  @Test
  public void great() {
  }

  @Test
  public void greatEqual() {
  }

  @Test
  public void less() {
  }

  @Test
  public void lessEqual() {
  }
}