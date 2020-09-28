package a2u.tn.utils.computer.calcobj.functions;

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

    public IfNotNull(Calculator calculator) {
        super(calculator);
    }

    @Override
    protected List<Parameter> initParameters() {
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter(Object.class, "forCheck"));
        parameters.add(new Parameter(Object.class, "value"));
        return parameters;
    }

    @Override
    public Object run(List<FormulaPart> params, Object row, int rowIndex, Collection<Object> allRows) {
        Object forCheck = calculator.calcArgument(params.get(0), row, rowIndex, allRows);
        Object value    = calculator.calcArgument(params.get(1), row, rowIndex, allRows);

        return forCheck != null ? value : null;
    }

}
