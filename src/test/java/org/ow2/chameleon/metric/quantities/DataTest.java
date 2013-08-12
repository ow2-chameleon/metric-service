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

/**
 * Test the data units
 */
public class DataTest {

    @Test
    public void testBit() {
        Data data = new Data(10);
        Data data2 = new Data(10, Data.BIT);

        assertThat(data.getUnit().getSymbol()).isEqualTo("bit");
        assertThat(data2.getUnit().getSymbol()).isEqualTo("bit");
        assertThat(data.getUnit().getName()).isEqualTo("bit");
    }

    @Test
    public void testByte() {
        Data data = new Data(10, Data.BYTE);

        assertThat(data.getUnit().getSymbol()).isEqualTo("B");
        assertThat(data.getUnit().getName()).isEqualTo("byte");
    }

    @Test
    public void testByteAndOctet() {
        Quantity<Data> one_byte = new Data(1, Data.BYTE);
        assertThat(one_byte.as(Data.BIT).value().doubleValue()).isEqualTo(8);

        Quantity<Data> one_octet = new Data(1, Data.OCTET);
        assertThat(one_octet.as(Data.BIT).value().doubleValue()).isEqualTo(8);
    }

    @Test
    public void testBitConversions() {
        Data data = new Data(2000);
        Data data2 = new Data(2, Data.KILOBIT);

        assertThat(data.as(Data.KILOBIT).value()).isEqualTo(2d);
        assertThat(data2.as(Data.BIT).value()).isEqualTo(2000d);
        assertThat(data.as(Data.MEGABIT).value()).isEqualTo(0.002d);
        assertThat(data.as(Data.GIGABIT).value().doubleValue()).isEqualTo(0.000002d, Delta.delta(0.1));
        assertThat(data.as(Data.TERABIT).value().doubleValue()).isEqualTo(0.000000002d, Delta.delta(0.1));
    }

    @Test
    public void testByteConversions() {

        Data data = new Data(16000, Data.BIT);
        Data data2 = new Data(2, Data.KILOBYTE);


        assertThat(data.as(Data.KILOBYTE).value()).isEqualTo(2d);
        assertThat(data2.as(Data.BYTE).value()).isEqualTo(2000.0);

        Data d = new Data(1, Data.GIGABIT);
        assertThat(d.as(Data.GIBIBYTE).value().doubleValue()).isEqualTo(0.125, Delta.delta(0.01));

    }
}
