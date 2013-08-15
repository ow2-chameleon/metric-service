package org.ow2.chameleon.metric.measure;


import org.junit.Test;
import org.ow2.chameleon.metric.quantities.Angle;
import org.ow2.chameleon.metric.quantities.Force;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Checks the behavior of the composed measure
 */
public class ComposedMeasureTest {

    @Test
    public void testCreation() {
        ComposedMeasure measure = new ComposedMeasure.ComposedMeasureBuilder()
                .sensor("compass")
                .hasMeasured("a", new Angle(5, Angle.RADIAN))
                .hasMeasured("b", MeasuredQuantity.<Angle>notCaptured())
                .hasMeasured("c", new Force(10))
                .at(1000)
                .build();

        assertThat(measure.content().get("a")).isEqualTo(new MeasuredQuantity<Angle>(new Angle(5,
                Angle.RADIAN)));
        assertThat(measure.<Angle>get("a")).isEqualTo(new MeasuredQuantity<Angle>(new Angle(5,
                Angle.RADIAN)));
        assertThat(measure.<Angle>get("a", Angle.class)).isEqualTo(new MeasuredQuantity<Angle>(new Angle(5,
                Angle.RADIAN)));

        assertThat(measure.content().get("b")).isEqualTo(MeasuredQuantity.<Angle>notCaptured());
        assertThat(measure.<Angle>get("b").isNotCaptured());
        assertThat(measure.<Angle>get("b", Angle.class)).isEqualTo(MeasuredQuantity.<Angle>notCaptured());

        assertThat(measure.content().get("c")).isEqualTo(new MeasuredQuantity<Force>(new Force(10)));
        assertThat(measure.<Force>get("c")).isEqualTo(new MeasuredQuantity<Force>(new Force(10)));
        assertThat(measure.<Force>get("c", Force.class)).isEqualTo(new MeasuredQuantity<Force>(new Force(10)));

        assertThat(measure.content().get("d")).isNull();
        assertThat(measure.<Force>get("d")).isNull();
        assertThat(measure.get("d", Force.class)).isNull();
    }

}
