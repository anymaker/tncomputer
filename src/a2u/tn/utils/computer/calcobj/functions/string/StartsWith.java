package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calculator.CalcContext;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Tests if string starts with the specified prefix.
 */
public class StartsWith extends Function {

    @Override
    protected List<Parameter> initParameters() {
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter(String.class, "string"));
        parameters.add(new Parameter(String.class, "prefix"));
        return parameters;
    }

    @Override
    public Object run(Calculator calculator, List<FormulaPart> params, Map<String, Object> paramValues, CalcContext ctx) {
        String string = (String) paramValues.get("string");
        String prefix = (String) paramValues.get("prefix");

        if (string == null) {
            string = "";
        }
        if (prefix == null) {
            prefix = "";
        }

        return string.startsWith(prefix);
    }

}
