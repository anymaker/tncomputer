package a2u.tn.utils.computer.calcobj.types;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Converter;
import a2u.tn.utils.computer.calculator.Type;
import a2u.tn.utils.computer.formula.FPValue;

public class TFPValue extends Type {

  public TFPValue(Calculator calculator) {
    super(calculator);
  }

  @Override
  public void fillConverter(Converter converter) {
    converter.addConverter(FPValue.class, null, value -> null);
    converter.addConverter(FPValue.class, Converter.AnyClass.class, value -> (FPValue)value);
  }

  @Override
  public Class<?> forClass() {
    return FPValue.class;
  }
}
