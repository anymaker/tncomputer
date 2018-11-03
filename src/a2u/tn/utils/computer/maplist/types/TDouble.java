package a2u.tn.utils.computer.maplist.types;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Converter;
import a2u.tn.utils.computer.calculator.Type;

import java.util.Date;

/**
 * Double type descriptor
 */
public class TDouble extends Type {

  public TDouble(Calculator calculator) {
    super(calculator);
  }

  @Override
  public void fillConverter(Converter converter) {
    converter.addConverter(Double.class, null, value -> 0d);
    converter.addConverter(Double.class, Integer.class, value -> (double) (int) value);
    converter.addConverter(Double.class, Long.class, value -> (double) (long) value);
    converter.addConverter(Double.class, Float.class, value -> (double) (float) value);
    converter.addConverter(Double.class, Double.class, value -> value);
    converter.addConverter(Double.class, Character.class, value -> (double) (char) value);
    converter.addConverter(Double.class, Boolean.class, value -> ((boolean) value) ? 1d : -1d);
    converter.addConverter(Double.class, Date.class, value -> (double) ((Date) value).getTime());
    converter.addConverter(Double.class, String.class, value -> Double.parseDouble((String) value));
  }

  @Override
  public Class<?> forClass() {
    return Double.class;
  }

  @Override
  public Double mul(Object v1, Object v2) {
    Double value1 = calculator.toType(Double.class, v1);
    Double value2 = calculator.toType(Double.class, v2);
    return value1 * value2;
  }
  @Override
  public Double div(Object v1, Object v2) {
    Double value1 = calculator.toType(Double.class, v1);
    Double value2 = calculator.toType(Double.class, v2);
    return value1 / value2;
  }

  @Override
  public Double plus(Object v1, Object v2) {
    Double value1 = calculator.toType(Double.class, v1);
    Double value2 = calculator.toType(Double.class, v2);
    return value1 + value2;
  }
  @Override
  public Double minus(Object v1, Object v2) {
    Double value1 = calculator.toType(Double.class, v1);
    Double value2 = calculator.toType(Double.class, v2);
    return value1 - value2;
  }

  @Override
  public boolean equal(Object v1, Object v2) {
    Double value1 = calculator.toType(Double.class, v1);
    Double value2 = calculator.toType(Double.class, v2);
    return value1.doubleValue() == value2.doubleValue();
  }
  @Override
  public boolean notequal(Object v1, Object v2) {
    Double value1 = calculator.toType(Double.class, v1);
    Double value2 = calculator.toType(Double.class, v2);
    return value1.doubleValue() != value2.doubleValue();
  }

  @Override
  public boolean great(Object v1, Object v2) {
    Double value1 = calculator.toType(Double.class, v1);
    Double value2 = calculator.toType(Double.class, v2);
    return value1 > value2;
  }
  @Override
  public boolean greatEqual(Object v1, Object v2) {
    Double value1 = calculator.toType(Double.class, v1);
    Double value2 = calculator.toType(Double.class, v2);
    return value1 >= value2;
  }
  @Override
  public boolean less(Object v1, Object v2) {
    Double value1 = calculator.toType(Double.class, v1);
    Double value2 = calculator.toType(Double.class, v2);
    return value1 < value2;
  }
  @Override
  public boolean lessEqual(Object v1, Object v2) {
    Double value1 = calculator.toType(Double.class, v1);
    Double value2 = calculator.toType(Double.class, v2);
    return value1 <= value2;
  }

}
