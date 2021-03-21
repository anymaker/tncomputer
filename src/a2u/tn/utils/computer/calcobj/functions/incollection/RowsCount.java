package a2u.tn.utils.computer.calcobj.functions.incollection;

import a2u.tn.utils.computer.calculator.CalcContext;
import a2u.tn.utils.computer.calculator.CalculatingException;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.List;
import java.util.Map;

/**
 * Return the number of rows in this collection
 */
public class RowsCount extends Function {

  @Override
  public Object run(Calculator calculator, List<FormulaPart> params, Map<String, Object> paramValues, CalcContext ctx) {
    if (ctx.getAllRows() == null) {
      throw new CalculatingException("Function 'rowsCount' can only be used to filtering rows.");
    }
    return ctx.getAllRows().size();
  }
}
