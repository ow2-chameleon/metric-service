package org.ow2.chameleon.metric.measure;


import org.junit.Test;
import org.ow2.chameleon.metric.quantities.Angle;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Checks the behavior of the composed measure
 */
public class ComposedMeasureTest {

    @Test
    public void testCreation() {
        ComposedMeasure<Angle> measure = new ComposedMeasure.ComposedMeasureBuilder<Angle>()
                .sensor("compass")
                .hasMeasured("a", new Angle(5, Angle.RADIAN))
                .hasMeasured("b", MeasuredQuantity.<Angle>notCaptured())
                .at(1000)
                .build();

        assertThat(measure.measure().get("a")).isEqualTo(new MeasuredQuantity<Angle>(new Angle(5, Angle.RADIAN)));
        assertThat(measure.measure().get("b")).isEqualTo(MeasuredQuantity.<Angle>notCaptured());
        assertThat(measure.measure().get("c")).isNull();
    }

}
