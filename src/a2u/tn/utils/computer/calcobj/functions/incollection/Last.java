package a2u.tn.utils.computer.calcobj.functions.incollection;

import a2u.tn.utils.computer.calculator.CalcContext;
import a2u.tn.utils.computer.calculator.CalculatingException;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.List;
import java.util.Map;

/**
 * Return true is current row is last
 *
 * Using:
 * If query      .mailList.author
 * return        {List:['Author01','Author02','Author03']}
 *
 * Then query    .mailList.author(lastRow)
 * will return   {String:'Author03'}
 *
 */
public class Last extends Function {

  @Override
  public Object run(Calculator calculator, List<FormulaPart> params, Map<String, Object> paramValues, CalcContext ctx) {
    if (ctx.getAllRows() == null) {
      throw new CalculatingException("Function 'last' can only be used to filtering rows.");
    }
    return ctx.getRowIndex() == ctx.getAllRows().size()-1;
  }
}
