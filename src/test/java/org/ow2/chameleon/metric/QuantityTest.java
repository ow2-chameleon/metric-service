package org.ow2.chameleon.metric;

import org.junit.Test;
import org.ow2.chameleon.metric.quantities.Angle;
import org.ow2.chameleon.metric.quantities.Force;
import org.ow2.chameleon.metric.quantities.Illuminance;
import org.ow2.chameleon.metric.quantities.Length;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.Fail.fail;

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

    @Test(expected=IllegalArgumentException.class)
    public void testImpossibleAdditionOfTwoQuantitiesWithTheSameKind() {
        // Cannot add rad and srad.
        Quantity<Angle> rad = new Angle(10.0, Angle.RADIAN);
        Quantity<Angle> srad = new Angle(10.0, Angle.STERADIAN);

        rad.add(srad);
        throw fail("Should not have been able to add radians and steradians");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testImpossibleAdditionOfTwoQuantitiesWithDifferentKind() {
        // Cannot add rad and srad.
        Quantity rad = new Angle(10.0, Angle.RADIAN);
        Quantity newton = new Force(10.0, Force.NEWTON);

        rad.add(newton);
        throw fail("Should not have been able to add radians and newtons");
    }

    @Test
    public void testComparisonOfUnitsFromTheSameKinds() {
        Quantity<Length> l1000m = new Length(1000, Length.METER);
        Quantity<Length> l1km = new Length(1000, Length.KILOMETER);
        Quantity<Length> l900m = new Length(900, Length.METER);
        Quantity<Length> l901m = new Length(901, Length.METER);

        assertThat(l1000m.isEqualTo(l1km));
        assertThat(l1000m.isMoreThan(l900m));
        assertThat(l900m.isLessThan(l1km));
        assertThat(l900m.isLessThan(l1000m));

        assertThat(l900m.isEqualTo(l901m, 5));
        assertThat(l901m.isEqualTo(l900m, 5));
    }




}
