package a2u.tn.utils.computer.calcobj.functions.auxiliary;

import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Return value, if checked value is not null
 */
public class IfNotNull extends Function {

    @Override
    protected List<Parameter> initParameters() {
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter(Object.class, "expressionForCheck"));
        parameters.add(new Parameter(Object.class, "expressionValue"));
        return parameters;
    }

    @Override
    public Object run(Calculator calculator, List<FormulaPart> params, Object row, int rowIndex, Collection<Object> allRows) {
        Object forCheck = calculator.calcArgument(params.get(0), row, rowIndex, allRows);
        Object value    = calculator.calcArgument(params.get(1), row, rowIndex, allRows);

        return forCheck != null ? value : null;
    }

}
