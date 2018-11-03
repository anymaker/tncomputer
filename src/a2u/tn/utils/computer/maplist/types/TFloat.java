package a2u.tn.utils.computer.maplist.types;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Converter;
import a2u.tn.utils.computer.calculator.Type;

/**
 * Float type descriptor
 */
public class TFloat extends Type {

  public TFloat(Calculator calculator) {
    super(calculator);
  }

  @Override
  public void fillConverter(Converter converter) {
    converter.addConverter(Float.class, null, value -> 0f);
    converter.addConverter(Float.class, Integer.class, value -> (float) (int) value);
    converter.addConverter(Float.class, Long.class, value -> (float) Math.toIntExact((long) value));
    converter.addConverter(Float.class, Float.class, value -> value);
    converter.addConverter(Float.class, Double.class, value -> (float) (double) value);
    converter.addConverter(Float.class, Character.class, value -> (float) (char) value);
    converter.addConverter(Float.class, Boolean.class, value -> ((boolean) value) ? 1f : -1f);
    converter.addConverter(Float.class, String.class, value -> Float.parseFloat((String) value));
  }

  @Override
  public Class<?> forClass() {
    return Float.class;
  }

  @Override
  public Float mul(Object v1, Object v2) {
    Float value1 = calculator.toType(Float.class, v1);
    Float value2 = calculator.toType(Float.class, v2);
    return value1 * value2;
  }
  @Override
  public Float div(Object v1, Object v2) {
    Float value1 = calculator.toType(Float.class, v1);
    Float value2 = calculator.toType(Float.class, v2);
    return value1 / value2;
  }

  @Override
  public Float plus(Object v1, Object v2) {
    Float value1 = calculator.toType(Float.class, v1);
    Float value2 = calculator.toType(Float.class, v2);
    return value1 + value2;
  }
  @Override
  public Float minus(Object v1, Object v2) {
    Float value1 = calculator.toType(Float.class, v1);
    Float value2 = calculator.toType(Float.class, v2);
    return value1 - value2;
  }

  @Override
  public boolean equal(Object v1, Object v2) {
    Float value1 = calculator.toType(Float.class, v1);
    Float value2 = calculator.toType(Float.class, v2);
    return value1.floatValue() == value2.floatValue();
  }
  @Override
  public boolean notequal(Object v1, Object v2) {
    Float value1 = calculator.toType(Float.class, v1);
    Float value2 = calculator.toType(Float.class, v2);
    return value1.floatValue() != value2.floatValue();
  }

  @Override
  public boolean great(Object v1, Object v2) {
    Float value1 = calculator.toType(Float.class, v1);
    Float value2 = calculator.toType(Float.class, v2);
    return value1 > value2;
  }
  @Override
  public boolean greatEqual(Object v1, Object v2) {
    Float value1 = calculator.toType(Float.class, v1);
    Float value2 = calculator.toType(Float.class, v2);
    return value1 >= value2;
  }
  @Override
  public boolean less(Object v1, Object v2) {
    Float value1 = calculator.toType(Float.class, v1);
    Float value2 = calculator.toType(Float.class, v2);
    return value1 < value2;
  }
  @Override
  public boolean lessEqual(Object v1, Object v2) {
    Float value1 = calculator.toType(Float.class, v1);
    Float value2 = calculator.toType(Float.class, v2);
    return value1 <= value2;
  }
}
