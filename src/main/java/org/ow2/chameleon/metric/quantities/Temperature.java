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
import org.ow2.chameleon.metric.units.SI;

/**
 * This class represents the temperature quantity. It defines its Unit, symbol name
 * and methods to initialize the quantity.
 *
 * @author jeremy.savonet@gmail.com
 */
public class Temperature extends Quantity<Temperature> {

    public static Unit<Temperature> KELVIN = SI.KELVIN;
    public static Unit<Temperature> CELSIUS =
            new TransformedUnitBuilder<Temperature>(KELVIN)
                    .symbol("°C")
                    .name("Celsius")
                    .add(273.15)
                    .registerConverter()
                    .build();

    public static Unit<Temperature> FAHRENHEIT =
            new TransformedUnitBuilder<Temperature>(KELVIN)
                    .symbol("°F")
                    .name("Fahrenheit")
                    .times(5d / 9d)
                    .add(+459.67)
                    .registerConverter()
                    .build();

    /**
     * @param number
     * @param unit
     */
    public Temperature(Number number, Unit<Temperature> unit) {
        super(Temperature.class, number, unit);
    }

}

