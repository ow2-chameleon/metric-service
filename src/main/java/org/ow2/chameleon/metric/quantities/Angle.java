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

import org.ow2.chameleon.metric.MetricService;
import org.ow2.chameleon.metric.Quantity;
import org.ow2.chameleon.metric.Unit;

/**
 * Represents an angle
 *
 */
public class Angle extends Quantity<Angle> {

    public static Unit<Angle> RADIAN = MetricService.getInstance().getSystemOfUnits("SI").getUnitByName("radian");

    public static Unit<Angle> STERADIAN = MetricService.getInstance().getSystemOfUnits("SI")
            .getUnitByName("steradian");

    /**
     * @param number
     * @param unit
     */
    public Angle(Number number, Unit<Angle> unit) {
        super(number, unit);
    }
}
