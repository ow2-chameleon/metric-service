package org.ow2.chameleon.metric.quantities;

import org.fest.assertions.Delta;
import org.junit.Test;
import org.ow2.chameleon.metric.Quantity;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Checks time behavior.
 */
public class TimeTest {

    @Test
    public void testConversions() {
        Quantity<Time> t1 = new Time(30, Time.SECOND);

        assertThat(t1.as(Time.MINUTE).getNumber().doubleValue()).isEqualTo(0.5);

        Quantity<Time> t2 = t1.times(2);
        assertThat(t2.as(Time.MINUTE).getNumber().doubleValue()).isEqualTo(1);

        Quantity<Time> t3 = t2.times(2);
        assertThat(t3.as(Time.MINUTE).getNumber().doubleValue()).isEqualTo(2);

        assertThat(t1.as(Time.MILLISECOND).value().doubleValue()).isEqualTo(30000);
        assertThat(t1.as(Time.NANOSECOND).value().doubleValue()).isEqualTo(30000000000d, Delta.delta(0.1));

        Quantity<Time> halfHour = new Time(30, Time.MINUTE);

        assertThat(halfHour.as(Time.SECOND).value().doubleValue()).isEqualTo(30 * 60);
        assertThat(halfHour.as(Time.HOUR).value().doubleValue()).isEqualTo(0.5);

        Quantity<Time> halfDay = new Time(12, Time.HOUR);

        assertThat(halfDay.as(Time.DAY).value().doubleValue()).isEqualTo(0.5);

    }

}
