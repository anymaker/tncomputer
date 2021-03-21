package a2u.tn.utils.computer.calcobj.functions.incollection;

import a2u.tn.utils.computer.calculator.CalcContext;
import a2u.tn.utils.computer.calculator.CalculatingException;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.List;
import java.util.Map;

/**
 * Return true if this row is encountered for the first time
 */
public class Distinct extends Function {


  @Override
  public Object run(Calculator calculator, List<FormulaPart> params, Map<String, Object> paramValues, CalcContext ctx) {
    if (ctx.getAllRows() == null) {
      throw new CalculatingException("Function 'distinct' is used only for filtering rows.");
    }
    int ix = 0;
    for (Object val : ctx.getAllRows()) {
      if (calculator.equalValues(val, ctx.getRowData())) {
        break;
      }
      ix++;
    }
    return ix == ctx.getRowIndex();
  }


}
