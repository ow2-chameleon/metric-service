package org.ow2.chameleon.metric.quantities;

import org.ow2.chameleon.metric.Quantity;
import org.ow2.chameleon.metric.Unit;
import org.ow2.chameleon.metric.systems.SI;

/**
 * Represents an amount of electric potential, generally in volt.
 */
public class ElectricPotential extends Quantity<ElectricPotential> {

    public static final Unit<ElectricPotential> VOLT = SI.getSI().getUnitByName("volt");

    public ElectricPotential(Number number, Unit<ElectricPotential> unit) {
        super(ElectricPotential.class, number, unit);
    }
}
