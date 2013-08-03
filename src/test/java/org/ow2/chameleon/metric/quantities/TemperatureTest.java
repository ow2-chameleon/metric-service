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

import static org.fest.assertions.Assertions.assertThat;

/**
 * Test temperature and conversion
 */
public class TemperatureTest {

    @Test
    public void testConversionKtoC() {
        Temperature temperatureInK = Temperature.kelvin(1000);
        assertThat(temperatureInK.as(Temperature.CELSIUS).getNumber()).isEqualTo(726.85);
        assertThat(temperatureInK.as(Temperature.CELSIUS).getUnit().getSymbol()).isEqualTo("°C");

        Temperature temperatureInC = new Temperature(35, Temperature.CELSIUS);
        assertThat(temperatureInC.as(Temperature.KELVIN).getNumber()).isEqualTo(308.15);
        assertThat(temperatureInC.as(Temperature.KELVIN).getUnit().getSymbol()).isEqualTo("K");
    }

    @Test
    public void testConversionKtoF() {
        Temperature temperatureInK = new Temperature(1000, Temperature.KELVIN);
        assertThat(temperatureInK.as(Temperature.FAHRENHEIT).getNumber().doubleValue()).isEqualTo(1340.33, Delta.delta(0.2));
        assertThat(temperatureInK.as(Temperature.FAHRENHEIT).getUnit().getSymbol()).isEqualTo("°F");

        Temperature temperatureInC = Temperature.fahrenheit(35);
        assertThat(temperatureInC.as(Temperature.KELVIN).getNumber().doubleValue()).isEqualTo(274.81,
                Delta.delta(0.2));
        assertThat(temperatureInC.as(Temperature.KELVIN).getUnit().getSymbol()).isEqualTo("K");
    }

    @Test
    public void testConversionCtoF() {
        Temperature temperatureInC = Temperature.celsius(35);
        assertThat(temperatureInC.as(Temperature.FAHRENHEIT).value().doubleValue()).isEqualTo(95.0, Delta.delta(0.2));

        Temperature temperatureInF = new Temperature(95, Temperature.FAHRENHEIT);
        assertThat(temperatureInF.as(Temperature.CELSIUS).value().doubleValue()).isEqualTo(35.0, Delta.delta(0.2));
    }
}
