package org.ow2.chameleon.metric;

import org.junit.Test;
import org.ow2.chameleon.metric.quantities.Illuminance;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Check quantity behavior.
 */
public class QuantityTest {


    @Test
    public void testCreation() {
        Quantity<Illuminance> q = new Quantity<Illuminance>(Illuminance.class, 10.0, Illuminance.LUX);
        assertThat(q.getKind()).isEqualTo(Illuminance.class.getName());
        assertThat(q.getNumber()).isEqualTo(10.0);
        assertThat(q.value()).isEqualTo(10.0);
        assertThat(q.getUnit()).isEqualTo(Illuminance.LUX);
        assertThat(q.unit()).isEqualTo(Illuminance.LUX);
    }

}
