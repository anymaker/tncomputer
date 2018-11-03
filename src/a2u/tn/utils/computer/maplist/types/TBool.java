package a2u.tn.utils.computer.maplist.types;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Converter;
import a2u.tn.utils.computer.calculator.Type;

/**
 * Boolean type descriptor
 */
public class TBool extends Type {

  public TBool(Calculator calculator) {
    super(calculator);
  }

  @Override
  public void fillConverter(Converter converter) {
    converter.addConverter(Boolean.class, null, value -> false);
    converter.addConverter(Boolean.class, Integer.class, value -> (int) value > 0);
    converter.addConverter(Boolean.class, Long.class, value -> (long) value > 0);
    converter.addConverter(Boolean.class, Float.class, value -> (float) value > 0);
    converter.addConverter(Boolean.class, Double.class, value -> (double) value > 0);
    converter.addConverter(Boolean.class, Character.class, value -> ((int) (char) value) > 0);
    converter.addConverter(Boolean.class, Boolean.class, value -> (boolean) value);
    converter.addConverter(Boolean.class, String.class, value -> {
      String bval = String.valueOf(value).trim().toLowerCase();
      switch (bval) {
        case "true":
          return true;
        case "false":
          return false;
        case "да":
          return true;
        case "нет":
          return false;
        case "yes":
          return true;
        case "no":
          return false;
        case "on":
          return true;
        case "off":
          return false;
        case "null":
          return false;
      }
      return true;
    });
    converter.addConverter(Boolean.class, Converter.AnyClass.class, value -> value != null);
  }

  @Override
  public Class<?> forClass() {
    return Boolean.class;
  }



  @Override
  public boolean equal(Object v1, Object v2) {
    Boolean bool1 = calculator.toType(Boolean.class, v1);
    Boolean bool2 = calculator.toType(Boolean.class, v2);
    return bool1.booleanValue() == bool2.booleanValue();
  }

  @Override
  public boolean notequal(Object v1, Object v2) {
    Boolean bool1 = calculator.toType(Boolean.class, v1);
    Boolean bool2 = calculator.toType(Boolean.class, v2);
    return bool1.booleanValue() != bool2.booleanValue();
  }


}
