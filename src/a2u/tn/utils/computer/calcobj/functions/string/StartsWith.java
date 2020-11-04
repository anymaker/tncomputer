package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Tests if string starts with the specified prefix.
 */
public class StartsWith extends Function {

    @Override
    protected List<Parameter> initParameters() {
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter(Object.class, "string"));
        parameters.add(new Parameter(Object.class, "prefix"));
        return parameters;
    }

    @Override
    public Object run(Calculator calculator, List<FormulaPart> params, Object row, int rowIndex, Collection<Object> allRows) {
        Object string_  = calculator.calcArgument(params.get(0), row, rowIndex, allRows);
        Object prefix_  = calculator.calcArgument(params.get(1), row, rowIndex, allRows);

        String string = calculator.toType(String.class, string_);
        String prefix = calculator.toType(String.class, prefix_);

        if (string == null) {
            string = "";
        }
        if (prefix == null) {
            prefix = "";
        }

        return string.startsWith(prefix);
    }

}
