package a2u.tn.utils.computer.calcobj.types;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Converter;
import a2u.tn.utils.computer.calculator.Type;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Date type descriptor
 */
public class TDate extends Type {

  public TDate(Calculator calculator) {
    super(calculator);
  }

  @Override
  public void fillConverter(Converter converter) {
    converter.addConverter(Date.class, null, value -> new Date(0));
    converter.addConverter(Date.class, Date.class, value -> value);
    converter.addConverter(Date.class, Long.class, (Object value) -> new Date((Long) value));
    converter.addConverter(Date.class, LocalDateTime.class, (Object date) -> Date.from(((LocalDateTime)date).atZone(ZoneId.systemDefault()).toInstant()));
    converter.addConverter(Date.class, LocalDate.class,     (Object date) -> java.sql.Date.valueOf((LocalDate)date));

    converter.addConverter(LocalDate.class,     Date.class, (Object date) -> ((Date)date).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    converter.addConverter(LocalDateTime.class, Date.class, (Object date) -> ((Date)date).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    converter.addConverter(Long.class,          Date.class, (Object date) -> ((Date)date).getTime());

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
