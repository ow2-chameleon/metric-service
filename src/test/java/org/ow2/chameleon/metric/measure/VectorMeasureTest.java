package org.ow2.chameleon.metric.measure;

import org.junit.Test;
import org.ow2.chameleon.metric.quantities.Acceleration;

import java.util.Date;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Check vector measures.
 */
public class VectorMeasureTest {

    @Test
    public void testSensorHasMeasuredXYZAtTime() {
         VectorMeasure<Acceleration> measure = new VectorMeasure.VectorMeasureBuilder<Acceleration>()
                 .sensor("gyroscope")
                 .hasMeasured(new Acceleration(1))
                 .hasMeasured(new Acceleration(2), new Acceleration(3))
                 .at(new Date())
                 .build();

        assertThat(measure.measure()).containsExactly(
                new MeasuredQuantity<Acceleration>(new Acceleration(1)),
                new MeasuredQuantity<Acceleration>(new Acceleration(2)),
                new MeasuredQuantity<Acceleration>(new Acceleration(3))
        );
    }

    @Test
    public void testMeasuresTakenByAt() {
        VectorMeasure<Acceleration> measure = new VectorMeasure.VectorMeasureBuilder<Acceleration>()
                .measure(new Acceleration(1))
                .measure(new Acceleration(2), 0.1)
                .measure(new Acceleration(3))
                .takenBy("gyroscope")
                .at(new Date())
                .build();

        assertThat(measure.measure()).containsExactly(
                new MeasuredQuantity<Acceleration>(new Acceleration(1)),
                new MeasuredQuantity<Acceleration>(new Acceleration(2), 0.1),
                new MeasuredQuantity<Acceleration>(new Acceleration(3))
        );
    }

    @Test
    public void testNotCaptured() {
        VectorMeasure<Acceleration> measure = new VectorMeasure.VectorMeasureBuilder<Acceleration>()
                .sensor("gyroscope")
                .hasNotCapturedAValue()
                .hasMeasured(new Acceleration(2))
                .hasMeasured(new Acceleration(3))
                .at(1000)
                .build();

        assertThat(measure.measure()).containsExactly(
                MeasuredQuantity.<Acceleration>notCaptured(),
                new MeasuredQuantity<Acceleration>(new Acceleration(2)),
                new MeasuredQuantity<Acceleration>(new Acceleration(3))
        );
    }
}
