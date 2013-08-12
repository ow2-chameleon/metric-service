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
 * Represents represents the luminous flux density per solid angle as measured in a given direction relative to the
 * emitting source. The system unit for this quantity is "cd" (candela).
 */
public class LuminousIntensity extends Quantity<LuminousIntensity> {

    public static final Unit<LuminousIntensity> CANDELA = SI.CANDELA;

    /**
     * @param number
     * @param unit
     */
    public LuminousIntensity(Number number, Unit<LuminousIntensity> unit) {
        super(LuminousIntensity.class, number, unit);
    }

    public LuminousIntensity(Number number) {
        this(number, CANDELA);
    }
}
