package a2u.tn.utils.computer.calcobj.functions.auxiliary;

import a2u.tn.utils.computer.calculator.CalcContext;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Return value, if checked value is not null
 */
public class IfNotNull extends Function {

    @Override
    protected List<Parameter<?>> initParameters() {
        List<Parameter<?>> parameters = new ArrayList<>();
        parameters.add(new Parameter<>(Object.class, "expressionForCheck"));
        parameters.add(new Parameter<>(Object.class, "expressionValue"));
        return parameters;
    }

    @Override
    public Object run(Calculator calculator, List<FormulaPart> params, Map<String, Object> paramValues, CalcContext ctx) {
        Object forCheck = paramValues.get("expressionForCheck");
        Object value    = paramValues.get("expressionValue");

        return forCheck != null ? value : null;
    }

}
