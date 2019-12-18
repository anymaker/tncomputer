package a2u.tn.utils.computer.calcobj.functions;

import a2u.tn.utils.computer.calculator.CalculatingException;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Return true is current row is last
 *
 * Using:
 * If query      .mailList.autor
 * return        {List:['Autor01','Autor02','Autor03']}
 *
 * Then query    .mailList.autor(lastRow)
 * will return   {String:'Autor03'}
 *
 */
public class Last extends Function {

  public Last(Calculator calculator) {
    super(calculator);
  }

  @Override
  public String getName() {
    return "last";
  }

  @Override
  public Object run(Map<String, FormulaPart> namedParams, List<FormulaPart> otherParams, Object row, int rowIndex, Collection<Object> allRows) {
    if (allRows == null) {
      throw new CalculatingException("Function 'last' can only be used to filtering rows.");
    }
    return rowIndex == allRows.size()-1;
  }
}
