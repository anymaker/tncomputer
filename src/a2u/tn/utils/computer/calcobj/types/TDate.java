package a2u.tn.utils.computer.calcobj.types;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Converter;
import a2u.tn.utils.computer.calculator.Type;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Date type descriptor
 */
public class TDate extends Type {

  public TDate(Calculator calculator) {
    super(calculator);
  }

  @Override
  public void fillConverter(Converter converter) {
    converter.addConverter(Date.class, null, value -> null);
    converter.addConverter(Date.class, Date.class, value -> value);
    converter.addConverter(Date.class, Long.class,           (Object value) -> new Date((Long) value));
    converter.addConverter(Date.class, long.class,           (Object value) -> new Date((Long) value));
    converter.addConverter(Date.class, LocalDateTime.class,  (Object date) -> Date.from(((LocalDateTime)date).atZone(ZoneId.systemDefault()).toInstant()));
    converter.addConverter(Date.class, LocalDate.class,      (Object date) -> java.sql.Date.valueOf((LocalDate)date));
    converter.addConverter(Date.class, OffsetDateTime.class, (Object date) -> java.sql.Date.from(((OffsetDateTime)date).toInstant()));
    converter.addConverter(Date.class, ZonedDateTime.class,  (Object date) -> Date.from(((ZonedDateTime)date).toInstant()));
    converter.addConverter(Date.class, Instant.class,        (Object date) -> Date.from((Instant)date));
    converter.addConverter(Date.class, Calendar.class,       (Object date) -> ((Calendar)date).getTime());

    converter.addConverter(Long.class, Date.class,           (Object date) -> ((Date)date).getTime());
    converter.addConverter(Long.class, LocalDateTime.class,  (Object date) -> Date.from(((LocalDateTime)date).atZone(ZoneId.systemDefault()).toInstant()).getTime());
    converter.addConverter(Long.class, LocalDate.class,      (Object date) -> java.sql.Date.valueOf((LocalDate)date).getTime());
    converter.addConverter(Long.class, OffsetDateTime.class, (Object date) -> java.sql.Date.from(((OffsetDateTime)date).toInstant()).getTime());
    converter.addConverter(Long.class, ZonedDateTime.class,  (Object date) -> Date.from(((ZonedDateTime)date).toInstant()).getTime());
    converter.addConverter(Long.class, Instant.class,        (Object date) -> Date.from((Instant)date).getTime());
    converter.addConverter(Long.class, Calendar.class,       (Object date) -> ((Calendar) date).getTimeInMillis());

    converter.addConverter(long.class, Date.class,           (Object date) -> ((Date)date).getTime());
    converter.addConverter(long.class, LocalDateTime.class,  (Object date) -> Date.from(((LocalDateTime)date).atZone(ZoneId.systemDefault()).toInstant()).getTime());
    converter.addConverter(long.class, LocalDate.class,      (Object date) -> java.sql.Date.valueOf((LocalDate)date).getTime());
    converter.addConverter(long.class, OffsetDateTime.class, (Object date) -> java.sql.Date.from(((OffsetDateTime)date).toInstant()).getTime());
    converter.addConverter(long.class, ZonedDateTime.class,  (Object date) -> Date.from(((ZonedDateTime)date).toInstant()).getTime());
    converter.addConverter(long.class, Instant.class,        (Object date) -> Date.from((Instant)date).getTime());
    converter.addConverter(long.class, Calendar.class,       (Object date) -> ((Calendar) date).getTimeInMillis());

    converter.addConverter(LocalDateTime.class, null, value -> null);
    converter.addConverter(LocalDateTime.class, Date.class,           (Object date) -> ((Date)date).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    converter.addConverter(LocalDateTime.class, Long.class,           (Object date) -> (new Date((long)date)).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    converter.addConverter(LocalDateTime.class, LocalDateTime.class,  (Object date) -> (LocalDateTime)date);
    converter.addConverter(LocalDateTime.class, LocalDate.class,      (Object date) -> LocalDateTime.of((LocalDate)date, LocalTime.of(0,0,0)));
    converter.addConverter(LocalDateTime.class, OffsetDateTime.class, (Object date) -> ((OffsetDateTime)date).toLocalDateTime());
    converter.addConverter(LocalDateTime.class, ZonedDateTime.class,  (Object date) -> ((ZonedDateTime)date).toLocalDateTime());
    converter.addConverter(LocalDateTime.class, Instant.class,        (Object date) -> LocalDateTime.ofInstant((Instant)date, ZoneId.systemDefault()));
    converter.addConverter(LocalDateTime.class, Calendar.class,       (Object date) -> LocalDateTime.ofInstant(((Calendar)date).toInstant(), ZoneId.systemDefault()));

    converter.addConverter(LocalDate.class, null, value -> null);
    converter.addConverter(LocalDate.class, Date.class,           (Object date) -> ((Date)date).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    converter.addConverter(LocalDate.class, Long.class,           (Object date) -> (new Date((long)date)).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    converter.addConverter(LocalDate.class, LocalDateTime.class,  (Object date) -> ((LocalDateTime)date).toLocalDate());
    converter.addConverter(LocalDate.class, LocalDate.class,      (Object date) -> (LocalDate)date);
    converter.addConverter(LocalDate.class, OffsetDateTime.class, (Object date) -> LocalDate.from((OffsetDateTime)date));
    converter.addConverter(LocalDate.class, ZonedDateTime.class,  (Object date) -> LocalDate.from((ZonedDateTime)date));
    converter.addConverter(LocalDate.class, Instant.class,        (Object date) -> LocalDateTime.ofInstant((Instant)date, ZoneId.systemDefault()).toLocalDate());
    converter.addConverter(LocalDate.class, Calendar.class,       (Object date) -> LocalDateTime.ofInstant(((Calendar)date).toInstant(), ZoneId.systemDefault()).toLocalDate());

    converter.addConverter(OffsetDateTime.class, null, value -> null);
    converter.addConverter(OffsetDateTime.class, Date.class,           (Object date) -> ((Date)date).toInstant().atZone(ZoneId.systemDefault()).toOffsetDateTime());
    converter.addConverter(OffsetDateTime.class, Long.class,           (Object date) -> (new Date((long)date)).toInstant().atZone(ZoneId.systemDefault()).toOffsetDateTime());
    converter.addConverter(OffsetDateTime.class, LocalDateTime.class,  (Object date) -> OffsetDateTime.of((LocalDateTime) date, OffsetDateTime.now().getOffset()));
    converter.addConverter(OffsetDateTime.class, LocalDate.class,      (Object date) -> OffsetDateTime.of(LocalDateTime.of((LocalDate) date, LocalTime.of(0,0,0)), OffsetDateTime.now().getOffset()));
    converter.addConverter(OffsetDateTime.class, OffsetDateTime.class, (Object date) -> (OffsetDateTime)date);
    converter.addConverter(OffsetDateTime.class, ZonedDateTime.class,  (Object date) -> OffsetDateTime.from((ZonedDateTime)date));
    converter.addConverter(OffsetDateTime.class, Instant.class,        (Object date) -> OffsetDateTime.ofInstant((Instant)date, ZoneId.systemDefault()));
    converter.addConverter(OffsetDateTime.class, Calendar.class,       (Object date) -> OffsetDateTime.ofInstant(((Calendar)date).toInstant(), ZoneId.systemDefault()));

    converter.addConverter(ZonedDateTime.class, null, value -> null);
    converter.addConverter(ZonedDateTime.class, Date.class,           (Object date) -> ((Date)date).toInstant().atZone(ZoneId.systemDefault()));
    converter.addConverter(ZonedDateTime.class, Long.class,           (Object date) -> (new Date((long)date)).toInstant().atZone(ZoneId.systemDefault()));
    converter.addConverter(ZonedDateTime.class, LocalDateTime.class,  (Object date) -> ZonedDateTime.of((LocalDateTime) date, ZoneId.systemDefault()));
    converter.addConverter(ZonedDateTime.class, LocalDate.class,      (Object date) -> ZonedDateTime.of(LocalDateTime.of((LocalDate) date, LocalTime.of(0,0,0)), ZoneId.systemDefault()));
    converter.addConverter(ZonedDateTime.class, OffsetDateTime.class, (Object date) -> ((OffsetDateTime)date).toZonedDateTime());
    converter.addConverter(ZonedDateTime.class, ZonedDateTime.class,  (Object date) -> (ZonedDateTime)date);
    converter.addConverter(ZonedDateTime.class, Instant.class,        (Object date) -> ((Instant)date).atZone(ZoneId.systemDefault()));
    converter.addConverter(ZonedDateTime.class, Calendar.class,       (Object date) -> ((Calendar)date).toInstant().atZone(ZoneId.systemDefault()));

    converter.addConverter(Instant.class, null, value -> null);
    converter.addConverter(Instant.class, Date.class,           (Object date) -> ((Date)date).toInstant());
    converter.addConverter(Instant.class, Long.class,           (Object date) -> (new Date((long)date)).toInstant());
    converter.addConverter(Instant.class, LocalDateTime.class,  (Object date) -> ((LocalDateTime) date).atZone(ZoneId.systemDefault()).toInstant());
    converter.addConverter(Instant.class, LocalDate.class,      (Object date) -> ((LocalDate)date).atTime(0, 0).atZone(ZoneId.systemDefault()).toInstant());
    converter.addConverter(Instant.class, OffsetDateTime.class, (Object date) -> ((OffsetDateTime)date).toInstant());
    converter.addConverter(Instant.class, ZonedDateTime.class,  (Object date) -> ((ZonedDateTime)date).toInstant());
    converter.addConverter(Instant.class, Instant.class,        (Object date) -> (Instant)date);
    converter.addConverter(Instant.class, Calendar.class,       (Object date) -> ((Calendar)date).toInstant());

    converter.addConverter(Calendar.class, null, value -> null);
    converter.addConverter(Calendar.class, Date.class,           (Object date) -> {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime((Date) date);
      return calendar;
    });
    converter.addConverter(Calendar.class, Long.class,           (Object date) -> {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(new Date((Long) date));
      return calendar;
    });
    converter.addConverter(Calendar.class, LocalDateTime.class,  (Object date) -> GregorianCalendar.from(((LocalDateTime) date).atZone(OffsetDateTime.now().getOffset())));
    converter.addConverter(Calendar.class, LocalDate.class,      (Object date) -> GregorianCalendar.from(((LocalDate)date).atTime(0, 0).atZone(OffsetDateTime.now().getOffset())));
    converter.addConverter(Calendar.class, OffsetDateTime.class, (Object date) -> {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(java.sql.Date.from(((OffsetDateTime)date).toInstant()));
      return calendar;
    });
    converter.addConverter(Calendar.class, ZonedDateTime.class,  (Object date) -> {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(java.sql.Date.from(((ZonedDateTime) date).toInstant()));
      return calendar;
    });
    converter.addConverter(Calendar.class, Instant.class,        (Object date) -> {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(Date.from((Instant) date));
      return calendar;
    });
    converter.addConverter(Calendar.class, Calendar.class,       (Object date) -> (Calendar)date);
  }

  @Override
  public Class<?> forClass() {
    return Date.class;
  }



  @Override
  public boolean equal(Object v1, Object v2) {
    Date date1 = calculator.toType(Date.class, v1);
    Date date2 = calculator.toType(Date.class, v2);
    return date1.equals(date2);
  }
  @Override
  public boolean notequal(Object v1, Object v2) {
    Date date1 = calculator.toType(Date.class, v1);
    Date date2 = calculator.toType(Date.class, v2);
    return !date1.equals(date2);
  }

  @Override
  public boolean great(Object v1, Object v2) {
    Date date1 = calculator.toType(Date.class, v1);
    Date date2 = calculator.toType(Date.class, v2);
    return date1.compareTo(date2) > 0;
  }
  @Override
  public boolean greatEqual(Object v1, Object v2) {
    Date date1 = calculator.toType(Date.class, v1);
    Date date2 = calculator.toType(Date.class, v2);
    return date1.compareTo(date2) >= 0;
  }
  @Override
  public boolean less(Object v1, Object v2) {
    Date date1 = calculator.toType(Date.class, v1);
    Date date2 = calculator.toType(Date.class, v2);
    return date1.compareTo(date2) < 0;
  }
  @Override
  public boolean lessEqual(Object v1, Object v2) {
    Date date1 = calculator.toType(Date.class, v1);
    Date date2 = calculator.toType(Date.class, v2);
    return date1.compareTo(date2) <= 0;
  }
}
