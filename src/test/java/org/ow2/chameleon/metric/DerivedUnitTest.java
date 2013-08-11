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
package org.ow2.chameleon.metric;

import org.ow2.chameleon.metric.quantities.*;
import org.ow2.chameleon.metric.systems.SI;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Check derived units
 */
public class DerivedUnitTest {

    @Test
    public void testNewton() {
        Unit<Force> newton = new DerivedUnitBuilder<Force>().name("newton").symbol("N").from(SI.GRAM).times(SI.METRE).pow(SI
                .SECOND,
                -2).build();

        assertThat(newton.getName()).isEqualTo("newton");
        assertThat(newton.getSymbol()).isEqualTo("N");
        assertThat(newton.getDimension()).isEqualTo(new DimensionBuilder().from(Dimension.MASS).times(Dimension
                .LENGTH).pow(Dimension.TIME, -2).build());
        assertThat(newton.toString()).isEqualTo("N");
        assertThat(((DerivedUnit)newton).getProductAsString()).isEqualTo("g.m.s^-2");
    }

    @Test
    public void testJouleAndPascal() {
        Unit<Force> newton = new DerivedUnitBuilder<Force>().name("newton").symbol("N").from(SI.GRAM).times(SI.METRE).pow(SI
                .SECOND,
                -2).build();

        Unit<Energy> joule = new DerivedUnitBuilder<Energy>().name("joule").symbol("J").from(newton).times(SI.METRE).build();

        assertThat(joule.getDimension()).isEqualTo(new DimensionBuilder().from(Dimension.MASS).times(Dimension
                .LENGTH).pow(Dimension.TIME, -2).times(Dimension.LENGTH).build());

        //N.m-2, J.m-3

        Unit<Pressure> pascal = new DerivedUnitBuilder<Pressure>().name("pascal").symbol("Pa").from(newton).pow(SI.METRE,
                -2).build();
        Unit<Pressure> pascal_alt = new DerivedUnitBuilder<Pressure>().name("pascal").symbol("Pa").from(joule).pow(SI.METRE,
                -3).build();

        assertThat(pascal.getDimension()).isEqualTo(pascal_alt.getDimension());

    }

    @Test
    public void testLux() {
        Unit<Illuminance> LUX = new DerivedUnitBuilder<Illuminance>().symbol("lx").from(SI.CANDELA).times(Angle.STERADIAN).pow
                (SI.METRE,
                -2).build();

        assertThat(LUX.getDimension()).isEqualTo(new DimensionBuilder().from(Dimension.LUMINOUS_INTENSITY).pow
                (Dimension.LENGTH, -2).build());
    }
}
