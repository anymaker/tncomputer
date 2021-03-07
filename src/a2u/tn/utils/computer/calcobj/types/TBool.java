package a2u.tn.utils.computer.calcobj.types;

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
    converter.addConverter(Boolean.class, int.class, value -> (int) value > 0);
    converter.addConverter(Boolean.class, Long.class, value -> (long) value > 0);
    converter.addConverter(Boolean.class, long.class, value -> (long) value > 0);
    converter.addConverter(Boolean.class, Float.class, value -> (float) value > 0);
    converter.addConverter(Boolean.class, float.class, value -> (float) value > 0);
    converter.addConverter(Boolean.class, Double.class, value -> (double) value > 0);
    converter.addConverter(Boolean.class, double.class, value -> (double) value > 0);
    converter.addConverter(Boolean.class, Character.class, value -> ((int) (char) value) > 0);
    converter.addConverter(Boolean.class, char.class, value -> ((int) (char) value) > 0);
    converter.addConverter(Boolean.class, Boolean.class, value -> (boolean) value);
    converter.addConverter(Boolean.class, boolean.class, value -> (boolean) value);
    converter.addConverter(Boolean.class, String.class, value -> {
      String bVal = String.valueOf(value).trim().toLowerCase();
      switch (bVal) {
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

        case "oui":
          return true;
        case "sì":
          return true;
        case "sí":
          return true;
        case "ja":
          return true;
        case "はい":
          return true;
        case "是的":
          return true;
        case "예":
          return true;

        case "ne":
          return true;
        case "non":
          return true;
        case "ない":
          return true;
        case "不是":
          return true;
        case "아니":
          return true;

        case "null":
          return false;
      }
      return true;
    });
    converter.addConverter(Boolean.class, Converter.AnyClass.class, value -> value != null);

    converter.addConverter(boolean.class, null, value -> false);
    converter.addConverter(boolean.class, Integer.class, value -> (int) value > 0);
    converter.addConverter(boolean.class, int.class, value -> (int) value > 0);
    converter.addConverter(boolean.class, Long.class, value -> (long) value > 0);
    converter.addConverter(boolean.class, long.class, value -> (long) value > 0);
    converter.addConverter(boolean.class, Float.class, value -> (float) value > 0);
    converter.addConverter(boolean.class, float.class, value -> (float) value > 0);
    converter.addConverter(boolean.class, Double.class, value -> (double) value > 0);
    converter.addConverter(boolean.class, double.class, value -> (double) value > 0);
    converter.addConverter(boolean.class, Character.class, value -> ((int) (char) value) > 0);
    converter.addConverter(boolean.class, char.class, value -> ((int) (char) value) > 0);
    converter.addConverter(boolean.class, Boolean.class, value -> (boolean) value);
    converter.addConverter(boolean.class, boolean.class, value -> (boolean) value);
    converter.addConverter(boolean.class, String.class, value -> {
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

        case "oui":
          return true;
        case "sì":
          return true;
        case "sí":
          return true;
        case "ja":
          return true;
        case "はい":
          return true;
        case "是的":
          return true;
        case "예":
          return true;

        case "ne":
          return true;
        case "non":
          return true;
        case "ない":
          return true;
        case "不是":
          return true;
        case "아니":
          return true;

        case "null":
          return false;
      }
      return true;
    });
    converter.addConverter(boolean.class, Converter.AnyClass.class, value -> value != null);

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
