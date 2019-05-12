package a2u.tn.utils.computer.calcobj.functions;

import a2u.tn.utils.computer.calculator.CalculatingException;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;

import java.util.Collection;
import java.util.Map;

/**
 * Return true if this row is encountered for the first time
 */
public class Distinct extends Function {

  public Distinct(Calculator calculator) {
    super(calculator);
  }

  @Override
  public String getName() {
    return "distinct";
  }


  @Override
  public Object run(Map<String, Object> paramValues, Object row, int rowIndex, Collection<Object> allRows) {
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
