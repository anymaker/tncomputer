package a2u.tn.utils.computer.calcobj.functions.datetime;

import a2u.tn.utils.computer.StringUtil;
import a2u.tn.utils.computer.calculator.CalcContext;
import a2u.tn.utils.computer.calculator.Calculator;
import a2u.tn.utils.computer.calculator.Function;
import a2u.tn.utils.computer.formula.FormulaPart;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Convert DateTime to another timezone
 *
 */
public class ToTimeZone extends Function {

  @Override
  protected List<Parameter> initParameters() {
    List<Parameter> parameters = new ArrayList<>();
    parameters.add(new Parameter(OffsetDateTime.class, "time", true, null));
    parameters.add(new Parameter(String.class,         "zone", true, "Z"));
    return parameters;
  }

  @Override
  public Object run(Calculator calculator, List<FormulaPart> params, Map<String, Object> paramValues, CalcContext ctx) {
    OffsetDateTime time = (OffsetDateTime) paramValues.get("time");
    if (time == null) {
      return null;
    }

    String zone = (String) paramValues.get("zone");
    zone = StringUtil.trimToNull(zone);

    ZoneOffset zoneOffset;
    if (zone == null) {
      zoneOffset = OffsetDateTime.now().getOffset();
    }
    else {
      zoneOffset = ZoneOffset.of(zone);
    }

    OffsetDateTime newTime = time.plusSeconds(zoneOffset.getTotalSeconds() - time.getOffset().getTotalSeconds());
    OffsetDateTime result = OffsetDateTime.of(newTime.toLocalDateTime(), zoneOffset);

    return result;
  }

}
