/*
 * Copyright 2013 OW2 Chameleon
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.ow2.chameleon.metric.quantities;

import org.ow2.chameleon.metric.DerivedUnit;
import org.ow2.chameleon.metric.DerivedUnitBuilder;
import org.ow2.chameleon.metric.Quantity;
import org.junit.Test;
import org.ow2.chameleon.metric.Unit;
import org.ow2.chameleon.metric.systems.SI;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Checks the force quantities.
 */
public class ForceTest {

    @Test
    public void testForce() {
        Quantity<Force> force1 = new Force(10);

        Force force2 = new Force(10, Force.NEWTON);

        assertThat(force1.getUnit().isCompatible(force2.getUnit()));
        assertThat(force1.getUnit().getDimension()).isEqualTo(force2.getUnit().getDimension());

        Quantity<Force> result = force1.add(force2);
        assertThat(result.getNumber()).isEqualTo(20.0);
        assertThat(result.getUnit()).isEqualTo(Force.NEWTON);
    }

    /**
     * Check that 1 N = 1 kg·m/s2
     */
    @Test
    public void testNewton() {
        Quantity<Force> N = new Force(1, Force.NEWTON);

        Unit<Force> composed = new DerivedUnitBuilder<Force>().from(SI.getSI().<Force>getUnitByName("kilogram")).times
                (Length.METER).dividedBy(SI.SECOND, 2).build();

        Quantity<Force> test = Quantity.valueOf(1, composed);

        assertThat(N.getUnit().isCompatible(test.getUnit()));
        assertThat(test.as(Force.NEWTON).value().doubleValue()).isEqualTo(1.0);
        assertThat(N.as(composed).value().doubleValue()).isEqualTo(1.0);


    }
}
