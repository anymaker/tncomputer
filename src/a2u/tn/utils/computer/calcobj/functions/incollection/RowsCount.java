package a2u.tn.utils.computer.calcobj.functions.incollection;

import a2u.tn.utils.computer.calculator.CalculatingException;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.Collection;
import java.util.List;

/**
 * Return the number of rows in this collection
 */
public class RowsCount extends Function {

  @Override
  public Object run(Calculator calculator, List<FormulaPart> params, Object row, int rowIndex, Collection<Object> allRows) {
    if (allRows == null) {
      throw new CalculatingException("Function 'rowsCount' can only be used to filtering rows.");
    }
    return allRows.size();
  }
}
