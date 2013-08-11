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
 * This class represents the pressure quantity. It defines its Unit, symbol name
 * and methods to initialize the quantity.
 *
 * @author jeremy.savonet@gmail.com
 */
public class Pressure extends Quantity<Pressure> {

    public static final Unit<Pressure> PASCAL = SI.getSI().getUnitByName("pascal");

    /**
     * @param number
     * @param unit
     */
    public Pressure(Number number, Unit<Pressure> unit) {
        super(Pressure.class, number, unit);
    }

    public Pressure(Number number) {
        super(Pressure.class, number, PASCAL);
    }
}
