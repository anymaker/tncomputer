package a2u.tn.utils.computer.calcobj.functions.incollection;

import a2u.tn.utils.computer.calculator.CalculatingException;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.Collection;
import java.util.List;

/**
 * Return true is current row is first
 *
 * Using:
 * If query      .mailList.autor
 * return        {List:['Autor01','Autor02','Autor03']}
 *
 * Then query    .mailList.autor(firstRow)
 * will return   {String:'Autor01'}
 */
public class First extends Function {

  @Override
  public Object run(Calculator calculator, List<FormulaPart> params, Object row, int rowIndex, Collection<Object> allRows) {
    if (allRows == null) {
      throw new CalculatingException("Function 'first' can only be used to filtering rows.");
    }
    return rowIndex == 0;
  }
}
