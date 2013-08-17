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

import org.fest.assertions.Delta;
import org.junit.Test;
import org.ow2.chameleon.metric.Quantity;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.Fail.fail;

/**
 * Checks angles
 */
public class AngleTest {

    @Test
    public void testNoConversion() {
        Quantity<Angle> angle1 = new Angle(2.0, Angle.RADIAN);
        Quantity<Angle> angle2 = new Angle(2.0, Angle.STERADIAN);

        try {
            angle1.as(Angle.STERADIAN);
            throw fail("Should not have been able to convert radian to steradian");
        } catch (IllegalArgumentException e) {
            // OK.
        }

        try {
            angle2.as(Angle.RADIAN);
            throw fail("Should not have been able to convert steradian to radian");
        } catch (IllegalArgumentException e) {
            // OK.
        }
    }

    @Test
    public void testConversionRadianToDegree() {
        Quantity<Angle> angleInRadian = new Angle(2, Angle.RADIAN);
        assertThat(angleInRadian.as(Angle.DEGREE).value().doubleValue()).isEqualTo(114.6, Delta.delta(0.1));
    }

    @Test
    public void testConversionDegreeToRadian() {
        Quantity<Angle> angleInDegree = new Angle(180, Angle.DEGREE);
        assertThat(angleInDegree.as(Angle.RADIAN).value().doubleValue()).isEqualTo(3.14, Delta.delta(0.01));
    }
}
