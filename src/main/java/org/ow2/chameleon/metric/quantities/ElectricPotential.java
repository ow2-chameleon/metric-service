package org.ow2.chameleon.metric.quantities;

import org.ow2.chameleon.metric.Quantity;
import org.ow2.chameleon.metric.Unit;
import org.ow2.chameleon.metric.UnitBuilder;
import org.ow2.chameleon.metric.units.SI;

/**
 * Represents an amount of electric potential, generally in volt.
 */
public class ElectricPotential extends Quantity<ElectricPotential> {

    public static final Unit VOLT = new UnitBuilder<ElectricPotential>()
            .name("volt")
            .symbol("V")
            .from(SI.GRAM)
            .pow(SI.METER, 2)
            .pow(SI.AMPERE, -1)
            .pow(SI.SECOND, -3)
            .build();

    public ElectricPotential(Number number, Unit<ElectricPotential> unit) {
        super(number, unit);
    }
}
