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

import org.junit.Test;
import org.ow2.chameleon.metric.Quantity;
import org.ow2.chameleon.metric.systems.SI;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: jeremy
 * Date: 15/07/13
 * Time: 14:48
 * To change this template use File | Settings | File Templates.
 */
public class MassTest {

    @Test
    public void testMassUsingSameUnit() {
        Mass l1 = new Mass(10);
        Mass l2 = new Mass(10, SI.GRAM);
        Mass l3 = new Mass(10, Mass.GRAM);

        assertThat(l1.value()).isEqualTo(10);
        assertThat(l2.value()).isEqualTo(10);
        assertThat(l3.value()).isEqualTo(10);

        assertThat(l1.getNumber()).isEqualTo(10);
        assertThat(l2.getNumber()).isEqualTo(10);
        assertThat(l3.getNumber()).isEqualTo(10);

        assertThat(l1.getNormalizedQuantity().getNumber()).isEqualTo(10);
        assertThat(l2.getNormalizedQuantity().getNumber()).isEqualTo(10);
        assertThat(l3.getNormalizedQuantity().getNumber()).isEqualTo(10);

        assertThat(l1.getNormalizedQuantity().getUnit()).isEqualTo(SI.GRAM);
        assertThat(l2.getNormalizedQuantity().getUnit()).isEqualTo(SI.GRAM);
        assertThat(l3.getNormalizedQuantity().getUnit()).isEqualTo(SI.GRAM);

        // Additions

        Quantity<Mass> l12 = l1.add(l2);
        Quantity<Mass> l21 = l2.add(l1);

        assertThat(l12.getNumber()).isEqualTo(20.0);
        assertThat(l21.getNumber()).isEqualTo(20.0);
        assertThat(l12.getUnit()).isEqualTo(SI.GRAM);
        assertThat(l21.getUnit()).isEqualTo(SI.GRAM);

        // Subtractions

        Quantity<Mass> l_12 = l1.sub(l2);
        Quantity<Mass> l_21 = l2.sub(l1);

        assertThat(l_12.getNumber()).isEqualTo(0.0);
        assertThat(l_21.getNumber()).isEqualTo(0.0);
        assertThat(l_12.getUnit()).isEqualTo(SI.GRAM);
        assertThat(l_21.getUnit()).isEqualTo(SI.GRAM);
    }

    @Test
    public void testConversion() {
        Quantity<Mass> mass1 = new Quantity<Mass>(Mass.class, 1000, Mass.GRAM);
        Quantity<Mass> mass2 = new Quantity<Mass>(Mass.class, 1, Mass.KILOGRAM);

        assertThat(mass1.as(Mass.KILOGRAM).value().doubleValue()).isEqualTo(1.0);
        assertThat(mass2.as(Mass.GRAM).value().doubleValue()).isEqualTo(1000.0);

        assertThat(mass1.as(Mass.GRAM).value().doubleValue()).isEqualTo(1000.0);
    }

    @Test
    public void testMassUsingDifferentUnit() {
        Quantity<Mass> mass1 = new Quantity<Mass>(Mass.class, 1000, Mass.GRAM);
        Quantity<Mass> mass2 = new Quantity<Mass>(Mass.class, 1, Mass.KILOGRAM);

        Quantity<Mass> added = mass1.add(mass2);
        assertThat(added.value().doubleValue()).isEqualTo(2000.0);
        assertThat(added.as(Mass.KILOGRAM).value().doubleValue()).isEqualTo(2.0);

        // Revert the quantities.
        added = mass2.add(mass1);
        assertThat(added.value().doubleValue()).isEqualTo(2.0);
        assertThat(added.as(Mass.KILOGRAM).value().doubleValue()).isEqualTo(2.0);
        assertThat(added.as(Mass.GRAM).value().doubleValue()).isEqualTo(2000.0);
    }
}
