package a2u.tn.utils.computer.calcobj.types;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Converter;
import a2u.tn.utils.computer.calculator.Type;

public class TNull extends Type {
  
  public static final class Null {
  
  }
  
  public TNull(Calculator calculator) {
    super(calculator);
  }
  
  public static Null getNull() {
    return new Null();
  }
  
  
  @Override
  public void fillConverter(Converter converter) {
    converter.addConverter(Null.class, null, value -> new Null());
    converter.addConverter(Null.class, Null.class, value -> value);
  }
  
  @Override
  public Class<?> forClass() {
    return Null.class;
  }
  
  public boolean equal(Object v1, Object v2) {
    return v2 == null;
  }
  
  public boolean notequal(Object v1, Object v2) {
    return !equal(v1, v2);
  }
  
}
