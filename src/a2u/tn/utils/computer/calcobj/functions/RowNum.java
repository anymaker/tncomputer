package a2u.tn.utils.computer.calcobj.functions;

import a2u.tn.utils.computer.calculator.CalculatingException;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;

import java.util.Collection;
import java.util.Map;

/**
 * Return number of current row in collection. Starting from 0.
 */
public class RowNum extends Function {

  public RowNum(Calculator calculator) {
    super(calculator);
  }

  @Override
  public String getName() {
    return "rowNum";
  }

  @Override
  public Object run(Map<String, Object> paramValues, Object row, int rowIndex, Collection<Object> allRows) {
    if (allRows == null) {
      throw new CalculatingException("Function 'rowNum' can only be used to filtering rows.");
    }
    return rowIndex;
  }
}
