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
 * This class represents the length quantity. It defines its Unit, symbol name
 * and methods to initialize the quantity.
 *
 * @author jeremy.savonet@gmail.com
 */
public class Length extends Quantity<Length> {

    public static final Unit<Length> METER = SI.METRE;
    public static final Unit<Length> METRE = SI.METRE;
    public static final Unit<Length> INCH = new TransformedUnitBuilder<Length>(SI.getSI().<Length>getUnitBySymbol("cm"))
            .times(2.54)
            .symbol("in")
            .withConverter()
            .build();

    public static final Unit<Length> FOOT = new TransformedUnitBuilder<Length>(INCH)
            .times(12)
            .symbol("ft")
            .withConverter()
            .build();
    public static final Unit<Length> CENTIMETER = SI.getSI().getUnitBySymbol("cm");
    public static final Unit<Length> MILLIMETER = SI.getSI().getUnitBySymbol("mm");
    public static final Unit<Length> KILOMETER = SI.getSI().getUnitBySymbol("km");

    /**
     * @param number
     * @param unit
     */
    public Length(Number number, Unit<Length> unit) {
        super(Length.class, number, unit);
    }

    public Length(Quantity<Length> q) {
        super(Length.class, q.value(), q.unit());
    }

    public Length(Number number) {
        super(Length.class, number, METER);
    }
}
