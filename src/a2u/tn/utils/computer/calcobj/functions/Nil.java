package a2u.tn.utils.computer.calcobj.functions;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.Collection;
import java.util.List;


/**
 * Return null value such as Null
 * Use it when you can't save "null" in interface
 */
public class Nil extends Function {

  public Nil(Calculator calculator) {
    super(calculator);
  }

  @Override
  public Object run(List<FormulaPart> params, Object row, int rowIndex, Collection<Object> allRows) {
    return null;
  }
}
