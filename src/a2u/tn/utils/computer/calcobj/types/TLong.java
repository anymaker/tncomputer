package a2u.tn.utils.computer.calcobj.types;

import a2u.tn.utils.computer.ValueOverflowException;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Converter;
import a2u.tn.utils.computer.calculator.Type;
import java.util.Date;

/**
 * Long type descriptor
 */
public class TLong extends Type {

  public TLong(Calculator calculator) {
    super(calculator);
  }

  @Override
  public void fillConverter(Converter converter) {
    converter.addConverter(Long.class, null, value -> 0L);
    converter.addConverter(Long.class, Integer.class, value -> (long) (int) value);
    converter.addConverter(Long.class, int.class, value -> (long) (int) value);
    converter.addConverter(Long.class, Long.class, value -> value);
    converter.addConverter(Long.class, Float.class, value -> (long) (float) value);
    converter.addConverter(Long.class, Double.class, value -> (long) (double) value);
    converter.addConverter(Long.class, Character.class, value -> (long) (char) value);
    converter.addConverter(Long.class, Boolean.class, value -> ((boolean) value) ? 1L : 0L);
    converter.addConverter(Long.class, Date.class, value -> ((Date) value).getTime());
    converter.addConverter(Long.class, String.class, value -> Long.parseLong((String) value));

    converter.addConverter(long.class, null, value -> 0L);
    converter.addConverter(long.class, Integer.class, value -> (long) (int) value);
    converter.addConverter(long.class, int.class, value -> (long) (int) value);
    converter.addConverter(long.class, Long.class, value -> value);
    converter.addConverter(long.class, Float.class, value -> (long) (float) value);
    converter.addConverter(long.class, Double.class, value -> (long) (double) value);
    converter.addConverter(long.class, Character.class, value -> (long) (char) value);
    converter.addConverter(long.class, Boolean.class, value -> ((boolean) value) ? 1L : 0L);
    converter.addConverter(long.class, Date.class, value -> ((Date) value).getTime());
    converter.addConverter(long.class, String.class, value -> Long.parseLong((String) value));
    converter.addConverter(long.class, int.class, value -> (long) (int) value);

  }

  @Override
  public Class<?> forClass() {
    return Long.class;
  }

  @Override
  public Long mul(Object v1, Object v2) {
    Long value1 = calculator.toType(Long.class, v1);
    Long value2 = calculator.toType(Long.class, v2);
    try {
      return Math.multiplyExact(value1, value2);
    }
    catch (ArithmeticException ex) {
      throw new ValueOverflowException("Result is very big or small for long. Value1='"+ value1 +"'. Value2='"+ value2 +"'.");
    }
  }
  @Override
  public Long div(Object v1, Object v2) {
    Long value1 = calculator.toType(Long.class, v1);
    Long value2 = calculator.toType(Long.class, v2);
    return value1 / value2;
  }
  @Override
  public Long remainder(Object v1, Object v2) {
    Long value1 = calculator.toType(Long.class, v1);
    Long value2 = calculator.toType(Long.class, v2);
    return value1 % value2;
  }

  @Override
  public Long plus(Object v1, Object v2) {
    Long value1 = calculator.toType(Long.class, v1);
    Long value2 = calculator.toType(Long.class, v2);
    try {
      return Math.addExact(value1, value2);
    }
    catch (ArithmeticException ex) {
      throw new ValueOverflowException("Result is very big or small for long. Value1='"+ value1 +"'. Value2='"+ value2 +"'.");
    }
  }
  @Override
  public Long minus(Object v1, Object v2) {
    Long value1 = calculator.toType(Long.class, v1);
    Long value2 = calculator.toType(Long.class, v2);
    try {
      return Math.subtractExact(value1, value2);
    }
    catch (ArithmeticException ex) {
      throw new ValueOverflowException("Result is very big or small for long. Value1='"+ value1 +"'. Value2='"+ value2 +"'.");
    }
  }

  @Override
  public boolean equal(Object v1, Object v2) {
    Long value1 = calculator.toType(Long.class, v1);
    Long value2 = calculator.toType(Long.class, v2);
    return value1.longValue() == value2.longValue();
  }
  @Override
  public boolean notequal(Object v1, Object v2) {
    Long value1 = calculator.toType(Long.class, v1);
    Long value2 = calculator.toType(Long.class, v2);
    return value1.longValue() != value2.longValue();
  }

  @Override
  public boolean great(Object v1, Object v2) {
    Long value1 = calculator.toType(Long.class, v1);
    Long value2 = calculator.toType(Long.class, v2);
    return value1 > value2;
  }
  @Override
  public boolean greatEqual(Object v1, Object v2) {
    Long value1 = calculator.toType(Long.class, v1);
    Long value2 = calculator.toType(Long.class, v2);
    return value1 >= value2;
  }
  @Override
  public boolean less(Object v1, Object v2) {
    Long value1 = calculator.toType(Long.class, v1);
    Long value2 = calculator.toType(Long.class, v2);
    return value1 < value2;
  }
  @Override
  public boolean lessEqual(Object v1, Object v2) {
    Long value1 = calculator.toType(Long.class, v1);
    Long value2 = calculator.toType(Long.class, v2);
    return value1 <= value2;
  }
}
