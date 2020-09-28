package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Tests if string ends with the specified suffix.
 */
public class EndWith extends Function {

    public EndWith(Calculator calculator) {
        super(calculator);
    }

    @Override
    protected List<Parameter> initParameters() {
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter(Object.class, "string"));
        parameters.add(new Parameter(Object.class, "suffix"));
        return parameters;
    }

    @Override
    public Object run(List<FormulaPart> params, Object row, int rowIndex, Collection<Object> allRows) {
        Object string_  = calculator.calcArgument(params.get(0), row, rowIndex, allRows);
        Object suffix_  = calculator.calcArgument(params.get(1), row, rowIndex, allRows);

        String string = calculator.toType(String.class, string_);
        String suffix = calculator.toType(String.class, suffix_);

        if (string == null) {
            string = "";
        }
        if (suffix == null) {
            suffix = "";
        }

        return string.endsWith(suffix);
    }

}
