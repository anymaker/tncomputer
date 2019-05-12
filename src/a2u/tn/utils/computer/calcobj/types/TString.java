package a2u.tn.utils.computer.calcobj.types;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Converter;
import a2u.tn.utils.computer.calculator.Type;

/**
 * String type descriptor
 */
public class TString extends Type {

  public TString(Calculator calculator) {
    super(calculator);
  }

  @Override
  public void fillConverter(Converter converter) {
    converter.addConverter(String.class, null, value -> null);
    converter.addConverter(String.class, String.class, value -> value);
    converter.addConverter(String.class, Converter.AnyClass.class, String::valueOf);
  }

  @Override
  public Class<?> forClass() {
    return String.class;
  }



  @Override
  public String plus(Object v1, Object v2) {
    String str1 = calculator.toType(String.class, v1);
    String str2 = calculator.toType(String.class, v2);
    return str1 + str2;
  }


  @Override
  public boolean equal(Object v1, Object v2) {
    String str1 = calculator.toType(String.class, v1);
    String str2 = calculator.toType(String.class, v2);
    return str1.equals(str2);
  }
  @Override
  public boolean notequal(Object v1, Object v2) {
    String str1 = calculator.toType(String.class, v1);
    String str2 = calculator.toType(String.class, v2);
    return !str1.equals(str2);
  }

  @Override
  public boolean great(Object v1, Object v2) {
    String str1 = calculator.toType(String.class, v1);
    String str2 = calculator.toType(String.class, v2);
    return str1.compareTo(str2) > 0;
  }
  @Override
  public boolean greatEqual(Object v1, Object v2) {
    String str1 = calculator.toType(String.class, v1);
    String str2 = calculator.toType(String.class, v2);
    return str1.compareTo(str2) >= 0;
  }
  @Override
  public boolean less(Object v1, Object v2) {
    String str1 = calculator.toType(String.class, v1);
    String str2 = calculator.toType(String.class, v2);
    return str1.compareTo(str2) < 0;
  }
  @Override
  public boolean lessEqual(Object v1, Object v2) {
    String str1 = calculator.toType(String.class, v1);
    String str2 = calculator.toType(String.class, v2);
    return str1.compareTo(str2) <= 0;
  }


}
