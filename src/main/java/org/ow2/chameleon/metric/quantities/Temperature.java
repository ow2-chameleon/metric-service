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

import org.ow2.chameleon.metric.Quantity;
import org.ow2.chameleon.metric.TransformedUnitBuilder;
import org.ow2.chameleon.metric.Unit;
import org.ow2.chameleon.metric.systems.SI;

/**
 * This class represents the temperature quantity. It defines its Unit, symbol name
 * and methods to initialize the quantity.
 *
 * @author jeremy.savonet@gmail.com
 */
public class Temperature extends Quantity<Temperature> {

    /**
     * The Kelvin unit (copy of {@link SI#KELVIN}.
     */
    public static Unit<Temperature> KELVIN = SI.KELVIN;
    /**
     * The celsius unit.
     */
    public static Unit<Temperature> CELSIUS =
            new TransformedUnitBuilder<Temperature>(KELVIN)
                    .symbol("\u2103")
                    .name("Celsius")
                    .add(273.15)
                    .withConverter()
                    .build();
    /**
     * The fahrenheit unit.
     */
    public static Unit<Temperature> FAHRENHEIT =
            new TransformedUnitBuilder<Temperature>(KELVIN)
                    .symbol("\u2109")
                    .name("Fahrenheit")
                    .times(5d / 9d)
                    .add(+459.67)
                    .withConverter()
                    .build();

    /**
     * Creates a new temperature
     *
     * @param number the value
     * @param unit   the unit
     */
    public Temperature(Number number, Unit<Temperature> unit) {
        super(Temperature.class, number, unit);
    }

    /**
     * Creates a new temperature in celsius.
     *
     * @param temp the value
     * @return a new temperature object
     */
    public static Temperature celsius(Number temp) {
        return new Temperature(temp, CELSIUS);
    }

    /**
     * Creates a new temperature in kelvin.
     *
     * @param temp the value
     * @return a new temperature object
     */
    public static Temperature kelvin(Number temp) {
        return new Temperature(temp, KELVIN);
    }

    /**
     * Creates a new temperature in fahrenheit.
     *
     * @param temp the value
     * @return a new temperature object
     */
    public static Temperature fahrenheit(Number temp) {
        return new Temperature(temp, FAHRENHEIT);
    }

}

