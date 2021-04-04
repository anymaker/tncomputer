package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calculator.CalcContext;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Return one character from position
 * The first position has index 0
 */
public class CharAt extends Function {

    @Override
    protected List<Parameter<?>> initParameters() {
        List<Parameter<?>> parameters = new ArrayList<>();
        parameters.add(new Parameter<>(String.class, "string"));
        parameters.add(new Parameter<>(Integer.class, "position"));
        return parameters;
    }

    @Override
    public Object run(Calculator calculator, List<FormulaPart> params, Map<String, Object> paramValues, CalcContext ctx) {
      String string = (String) paramValues.get("string");
      Integer position = (Integer) paramValues.get("position");

      if (string == null) {
        return null;
      }

      if (string.length() < position) {
        return null;
      }

      char c = string.charAt(position);

      return String.valueOf(c);
    }

}
