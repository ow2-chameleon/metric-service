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
 * This class represents the mass quantity. It defines its Unit, symbol name
 * and methods to initialize the quantity.
 *
 * @author jeremy.savonet@gmail.com
 */
public class Mass extends Quantity<Mass> {

    public static final Unit<Mass> GRAM = SI.GRAM;
    public static final Unit<Mass> KILOGRAM = SI.getSI().getUnitBySymbol("kg");

    /**
     * @param number
     * @param unit
     */
    public Mass(Number number, Unit<Mass> unit) {
        super(Mass.class, number, unit);
    }

    public Mass(Quantity<Mass> q) {
        super(Mass.class, q.value(), q.unit());
    }

    public Mass(Number number) {
        super(Mass.class, number, GRAM);
    }
}
