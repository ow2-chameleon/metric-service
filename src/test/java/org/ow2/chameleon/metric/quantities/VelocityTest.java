package org.ow2.chameleon.metric.quantities;

import org.fest.assertions.Delta;
import org.junit.Test;
import org.ow2.chameleon.metric.Quantity;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Checks velocity units
 */
public class VelocityTest {


    @Test
    public void testConversionFromMeterPerSecondToKilometerPerHour() {
        Quantity<Velocity> speed = new Velocity(1, Velocity.METER_PER_SECOND);
        assertThat(speed.as(Velocity.KILOMETER_PER_HOUR).value().doubleValue()).isEqualTo(3.6);
    }

    @Test
    public void testConversionFromKilometerPerHourToMeterPerSecond() {
        Quantity<Velocity> speed = new Velocity(60, Velocity.KILOMETER_PER_HOUR);
        assertThat(speed.as(Velocity.METER_PER_SECOND).value().doubleValue()).isEqualTo(16.6, Delta.delta(0.1));
        assertThat(speed.as(Velocity.METRE_PER_SECOND).value().doubleValue()).isEqualTo(16.6, Delta.delta(0.1));
    }
}
