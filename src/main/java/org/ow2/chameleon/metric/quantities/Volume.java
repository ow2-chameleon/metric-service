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
import org.ow2.chameleon.metric.Unit;
import org.ow2.chameleon.metric.systems.SI;

/**
 * This class represents the volume quantity. It defines its Unit, symbol name
 * and methods to initialize the quantity.
 *
 * @author jeremy.savonet@gmail.com
 */
public class Volume extends Quantity<Volume> {

    public static final Unit<Volume> CUBIC_METRE = SI.getSI().getUnitBySymbol("\u33A5");
    public static final Unit<Volume> CUBIC_METER = CUBIC_METRE;

    /**
     * @param number
     * @param unit
     */
    public Volume(Number number, Unit<Volume> unit) {
        super(Volume.class, number, unit);
    }

    public Volume(Number number) {
        super(Volume.class, number, CUBIC_METRE);
    }
}

