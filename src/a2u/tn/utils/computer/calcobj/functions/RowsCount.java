package a2u.tn.utils.computer.calcobj.functions;

import a2u.tn.utils.computer.calculator.CalculatingException;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Return the number of rows in this collection
 */
public class RowsCount extends Function {

  public RowsCount(Calculator calculator) {
    super(calculator);
  }

  @Override
  public String getName() {
    return "rowsCount";
  }

  @Override
  public Object run(Map<String, FormulaPart> namedParams, List<FormulaPart> otherParams, Object row, int rowIndex, Collection<Object> allRows) {
    if (allRows == null) {
      throw new CalculatingException("Function 'rowsCount' can only be used to filtering rows.");
    }
    return allRows.size();
  }
}
