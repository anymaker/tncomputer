package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Returns the index within string of the first occurrence of the specified substring.
 */
public class IndexOf extends Function {

    @Override
    protected List<Parameter> initParameters() {
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter(Object.class, "string"));
        parameters.add(new Parameter(Object.class, "substring"));
        return parameters;
    }

    @Override
    public Object run(Calculator calculator, List<FormulaPart> params, Object row, int rowIndex, Collection<Object> allRows) {
        Object string_    = calculator.calcArgument(params.get(0), row, rowIndex, allRows);
        Object substring_ = calculator.calcArgument(params.get(1), row, rowIndex, allRows);

        String string    = calculator.toType(String.class, string_);
        String substring = calculator.toType(String.class, substring_);

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
