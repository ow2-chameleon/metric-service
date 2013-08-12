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
import org.ow2.chameleon.metric.TransformedUnit;
import org.ow2.chameleon.metric.TransformedUnitBuilder;
import org.ow2.chameleon.metric.Unit;
import org.ow2.chameleon.metric.systems.SI;

/**
 * This class represents the time quantity. It defines its Unit, symbol name
 * and methods to initialize the quantity.
 */
public class Time extends Quantity<Time> {

    public static final Unit<Time> SECOND = SI.SECOND;

    public static final Unit<Time> MILLISECOND = SI.getSI().getUnitBySymbol("ms");

    public static final Unit<Time> NANOSECOND = SI.getSI().getUnitBySymbol("ns");

    public static final Unit<Time> MINUTE = new TransformedUnitBuilder<Time>(SECOND).times(60).symbol("min").name
            ("minute").withConverter().build();

    public static final Unit<Time> HOUR = new TransformedUnitBuilder<Time>(MINUTE).times(60).symbol("h").name
            ("hour").withConverter().build();

    public static final Unit<Time> DAY = new TransformedUnitBuilder<Time>(HOUR).times(24).symbol("day").name
            ("day").withConverter().build();

    /**
     * @param number
     * @param unit
     */
    public Time(Number number, Unit<Time> unit) {
        super(Time.class, number, unit);
    }
}