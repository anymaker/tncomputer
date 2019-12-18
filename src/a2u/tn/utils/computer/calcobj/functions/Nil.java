package a2u.tn.utils.computer.calcobj.functions;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * Return null value such as Null
 * Use it when you can't save "null" in interface
 */
public class Nil extends Function {

  public Nil(Calculator calculator) {
    super(calculator);
  }

  @Override
  public String getName() {
    return "nil";
  }

  @Override
  public Object run(Map<String, FormulaPart> namedParams, List<FormulaPart> otherParams, Object row, int rowIndex, Collection<Object> allRows) {
    return null;
  }
}
