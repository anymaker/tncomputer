package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calculator.CalcContext;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Returns the index within string of the first occurrence of the specified substring.
 */
public class IndexOf extends Function {

    @Override
    protected List<Parameter> initParameters() {
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter(String.class, "string"));
        parameters.add(new Parameter(String.class, "substring"));
        return parameters;
    }

    @Override
    public Object run(Calculator calculator, List<FormulaPart> params, Map<String, Object> paramValues, CalcContext ctx) {
        String string    = (String) paramValues.get("string");
        String substring = (String) paramValues.get("substring");

        if (string == null && substring == null) {
            return 0;
        }
        if (string != null && substring == null) {
            return -1;
        }

        if (string == null) {
            string = "";
        }

        int ix = string.indexOf(substring);
        return ix;
    }

}
