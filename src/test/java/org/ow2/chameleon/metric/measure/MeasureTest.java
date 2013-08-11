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
package org.ow2.chameleon.metric.measure;

import org.junit.Test;
import org.ow2.chameleon.metric.DerivedUnitBuilder;
import org.ow2.chameleon.metric.Quantity;
import org.ow2.chameleon.metric.quantities.Angle;
import org.ow2.chameleon.metric.quantities.Illuminance;
import org.ow2.chameleon.metric.systems.SI;

import java.util.Date;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Checks measure behavior.
 */
public class MeasureTest {

    @Test
    public void testBuilder() {

        //Create a new quantity
        Quantity<Illuminance> illuminance = new Quantity<Illuminance>(Illuminance.class,10,
                new DerivedUnitBuilder<Illuminance>()
                        .from(SI.CANDELA).times(Angle.STERADIAN).pow(SI.METRE, -2)
                        .name("lux")
                        .symbol("lx").build());

        Date time = new Date(System.currentTimeMillis());

        //Create the measure
        Measure m = new Measure.Builder("1234")
                .quantity(illuminance)
                .minDeviation(illuminance.getNumber())
                .maxDeviation(illuminance.getNumber())
                .timeStamp(time)
                .build();

        assertThat(m).isNotNull();
        assertThat(m.id()).isEqualTo("1234");
        assertThat(m.quantity()).isEqualTo(illuminance);
        assertThat(m.minDeviation()).isEqualTo(illuminance.getNumber());
        assertThat(m.maxDeviation()).isEqualTo(illuminance.getNumber());
        assertThat(m.timeStamp()).isEqualTo(time);
    }

    @Test
    public void testNotCaptured() {

        Measure notCapture = new Measure.Builder("0000").notCaptured().timeStamp(System.currentTimeMillis()).build();

        assertThat(notCapture.quantity().value().doubleValue()).isEqualTo(0.0);
        assertThat(notCapture.quantity().unit()).isEqualTo(Measure.NOT_CAPTURED_QUANTITY.getUnit());

    }
}
