package a2u.tn.utils.computer.maplist.types;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Converter;
import a2u.tn.utils.computer.calculator.Type;

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
    converter.addConverter(Date.class, Long.class, value -> new Date((Long) value));
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
