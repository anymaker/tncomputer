package a2u.tn.utils.computer.maplist.types;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Converter;

/**
 * Integer type descriptor
 */
public class TInt extends TLong {

  public TInt(Calculator calculator) {
    super(calculator);
  }

  @Override
  public void fillConverter(Converter converter) {
    converter.addConverter(Integer.class, null, value -> 0);
    converter.addConverter(Integer.class, Integer.class, value -> value);
    converter.addConverter(Integer.class, Long.class, value -> Math.toIntExact((long) value));
    converter.addConverter(Integer.class, Float.class, value -> Math.round((float) value));
    converter.addConverter(Integer.class, Double.class, value -> Math.toIntExact((long) (double) value));
    converter.addConverter(Integer.class, Character.class, value -> (int) (char) value);
    converter.addConverter(Integer.class, Boolean.class, value -> ((boolean) value) ? 1 : 0);
    converter.addConverter(Integer.class, String.class, value -> Integer.parseInt((String) value));
  }

  @Override
  public Class<?> forClass() {
    return Integer.class;
  }


}
