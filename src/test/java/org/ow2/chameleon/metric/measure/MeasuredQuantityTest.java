package org.ow2.chameleon.metric.measure;

import org.junit.Test;
import org.ow2.chameleon.metric.Quantity;
import org.ow2.chameleon.metric.Unit;
import org.ow2.chameleon.metric.quantities.Force;
import org.ow2.chameleon.metric.quantities.Illuminance;
import org.ow2.chameleon.metric.systems.SI;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Checks the measured quantity behavior.
 */
public class MeasuredQuantityTest {

    @Test
    public void testNotCaptured() throws Exception {
        MeasuredQuantity<Illuminance> illuminance = MeasuredQuantity.<Illuminance>notCaptured();
        MeasuredQuantity<Force> force = MeasuredQuantity.<Force>notCaptured();

        assertThat(illuminance.unit().getName()).isEqualTo("NaM"); // Not a measure
        assertThat(force.value().doubleValue()).isEqualTo(0);

        assertThat(illuminance.isNotCaptured());
        assertThat(force.isNotCaptured());
    }

    @Test
    public void testIsInRange() throws Exception {
        MeasuredQuantity<Illuminance> measure = new MeasuredQuantity<Illuminance>(new Illuminance(10), 0.5);

        assertThat(measure.value().doubleValue()).isEqualTo(10);
        assertThat(measure.unit()).isEqualTo(Illuminance.LUX);

        Quantity<Illuminance> inRange = new Illuminance(10.2);
        assertThat(measure.isInRange(inRange));

        Quantity<Illuminance> notInRange = new Illuminance(9.4);
        assertThat(measure.isInRange(notInRange)).isFalse();

        Unit<Illuminance> centilux = SI.getSI().getUnitByName("centilux");
        Illuminance hasToBeConverted = new Illuminance(110, centilux);

        assertThat(measure.isInRange(hasToBeConverted));
    }
}
