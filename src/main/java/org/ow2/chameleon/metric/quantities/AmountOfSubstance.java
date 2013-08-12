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
 * Represents an amount of substance, generally expressed in Mole.
 */
public class AmountOfSubstance extends Quantity<AmountOfSubstance> {

    public static final Unit<AmountOfSubstance> MOLE = SI.MOL;
    public static final Unit<AmountOfSubstance> MOL = MOLE;

    /**
     * @param number
     * @param unit
     */
    public AmountOfSubstance(Number number, Unit<AmountOfSubstance> unit) {
        super(AmountOfSubstance.class, number, unit);
    }

    public AmountOfSubstance(Number number) {
        this(number, MOL);
    }
}
