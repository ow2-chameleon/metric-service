package org.ow2.chameleon.metric.measure;

import org.junit.Test;
import org.ow2.chameleon.metric.quantities.Temperature;

import java.util.Date;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Check the scalar measures.
 */
public class ScalarMeasureTest {

    @Test
    public void testBuilderSensorHasMeasuredVAtT_NoDelta() {
        ScalarMeasure<Temperature> measure = new ScalarMeasure.ScalarMeasureBuilder<Temperature>()
                .sensor("thermometer")
                .hasMeasured(new Temperature(10, Temperature.CELSIUS))
                .at(new Date())
                .build();

        assertThat(measure.origin()).isEqualTo("thermometer");
        assertThat(measure.content().value().doubleValue()).isEqualTo(10);
        assertThat(measure.content().unit()).isEqualTo(Temperature.CELSIUS);
        assertThat(measure.content().isExact());
    }

    @Test
    public void testBuilderSensorHasMeasuredVAtT_WithDelta() {
        ScalarMeasure<Temperature> measure = new ScalarMeasure.ScalarMeasureBuilder<Temperature>()
                .sensor("thermometer")
                .hasMeasured(new Temperature(10, Temperature.CELSIUS), 0.5)
                .at(new Date())
                .build();

        assertThat(measure.origin()).isEqualTo("thermometer");
        assertThat(measure.content().value().doubleValue()).isEqualTo(10);
        assertThat(measure.content().unit()).isEqualTo(Temperature.CELSIUS);
        assertThat(measure.content().isExact()).isFalse();
        assertThat(measure.content().minDerivation).isEqualTo(0.5);
        assertThat(measure.content().maxDerivation).isEqualTo(0.5);
    }

    @Test
    public void testBuilderMeasureTakenByAt() {
        ScalarMeasure<Temperature> measure = new ScalarMeasure.ScalarMeasureBuilder<Temperature>()
                .measure(new Temperature(10, Temperature.CELSIUS))
                .takenBy("thermometer")
                .at(new Date())
                .build();

        assertThat(measure.origin()).isEqualTo("thermometer");
        assertThat(measure.content().value().doubleValue()).isEqualTo(10);
        assertThat(measure.content().unit()).isEqualTo(Temperature.CELSIUS);
        assertThat(measure.content().isExact());
    }

    @Test
    public void testNotCaptured() {
        ScalarMeasure<Temperature> measure = new ScalarMeasure.ScalarMeasureBuilder<Temperature>()
                .sensor("thermometer").hasNotCapturedAValueAt(1000).build();

        assertThat(measure.origin()).isEqualTo("thermometer");
        assertThat(measure.content().isNotCaptured());
        assertThat(measure.content().isExact()).isFalse();
    }
}
