package a2u.tn.utils.computer.calcobj.functions;

import a2u.tn.utils.computer.calculator.CalculatingException;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.Collection;
import java.util.List;

/**
 * Return true if this row is encountered for the first time
 */
public class Distinct extends Function {

  public Distinct(Calculator calculator) {
    super(calculator);
  }


  @Override
  public Object run(List<FormulaPart> params, Object row, int rowIndex, Collection<Object> allRows) {
    if (allRows == null) {
      throw new CalculatingException("Function 'distinct' is used only for filtering rows.");
    }
    int ix = 0;
    for (Object val : allRows) {
      if (calculator.equalValues(val, row)) {
        break;
      }
      ix++;
    }
    return ix == rowIndex;
  }


}
