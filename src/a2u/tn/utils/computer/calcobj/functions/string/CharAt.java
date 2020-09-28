package a2u.tn.utils.computer.calcobj.functions.string;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Return one character from position
 * The first position has index 0
 */
public class CharAt extends Function {

    public CharAt(Calculator calculator) {
        super(calculator);
    }

    @Override
    protected List<Parameter> initParameters() {
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter(Object.class, "string"));
        parameters.add(new Parameter(Object.class, "position"));
        return parameters;
    }

    @Override
    public Object run(List<FormulaPart> params, Object row, int rowIndex, Collection<Object> allRows) {
        Object string   = calculator.calcArgument(params.get(0), row, rowIndex, allRows);
        Object position = calculator.calcArgument(params.get(1), row, rowIndex, allRows);

        String str = calculator.toType(String.class, string);
        int pos = calculator.toType(Integer.class, position);

        if (str == null) {
            return null;
        }

        if (str.length() < pos) {
            return null;
        }

        char c = str.charAt(pos);

        return String.valueOf(c);
    }
    
}
