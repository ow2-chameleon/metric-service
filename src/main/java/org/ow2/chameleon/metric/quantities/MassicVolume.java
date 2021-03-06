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

import org.ow2.chameleon.metric.DerivedUnitBuilder;
import org.ow2.chameleon.metric.Quantity;
import org.ow2.chameleon.metric.Unit;
import org.ow2.chameleon.metric.systems.SI;

/**
 * This class represents the massic volume quantity. It defines its Unit, symbol name
 * and methods to initialize the quantity.
 *
 * @author jeremy.savonet@gmail.com
 */
public class MassicVolume extends Quantity<MassicVolume> {

    public static final Unit<MassicVolume> MASSIC_VOLUME_UNIT = new DerivedUnitBuilder<MassicVolume>()
            .from(SI.GRAM).pow(SI.METRE, -3)
            .name("massic volume")
            .symbol("g/m3")
            .addToSystem("SI")
            .build();

    public static final Unit<MassicVolume> MASSIC_VOLUME_UNIT_FOR_GAZ = new DerivedUnitBuilder<MassicVolume>()
            .from(SI.getSI().<Mass>getUnitBySymbol("µg")).pow(SI.METRE, -3)
            .name("massic volume")
            .symbol("µg/m3")
            .addToSystem("SI")
            .build();

    /**
     * @param number
     * @param unit
     */
    public MassicVolume(Number number, Unit<MassicVolume> unit) {
        super(MassicVolume.class, number, unit);
    }

    public MassicVolume(Number number) {
        super(MassicVolume.class, number, MASSIC_VOLUME_UNIT);
    }
}
