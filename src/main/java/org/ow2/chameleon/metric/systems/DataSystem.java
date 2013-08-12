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
package org.ow2.chameleon.metric.systems;

import org.ow2.chameleon.metric.*;
import org.ow2.chameleon.metric.quantities.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents definition of SI Units
 */
public class DataSystem extends DefaultSystemOfUnits {

    public static Unit<Data> BIT = new Unit<Data>("bit", "bit", Dimension.NONE);
    public static Unit SECOND = SI.SECOND;

    public DataSystem() {
        super("SI", Arrays.asList(
                Dimension.TIME,
                Dimension.NONE), null);
//                getITPrefixMapping());

        // Base units.
        this.addUnitToSystem(BIT)
                .addUnitToSystem(SI.SECOND);

    }

    public static List<Prefix> getSIPrefixMapping() {
        List<Prefix> prefixes = new ArrayList<Prefix>();
        prefixes.add(new Prefix("tera", "T", 1000000000000l));
        prefixes.add(new Prefix("giga", "G", 1000000000l));
        prefixes.add(new Prefix("mega", "M", 1000000));
        prefixes.add(new Prefix("giga", "k", 1000));
        prefixes.add(new Prefix("hecto", "h", 100));
        prefixes.add(new Prefix("deci", "d", 0.1));
        prefixes.add(new Prefix("centi", "c", 0.01));
        prefixes.add(new Prefix("milli", "m", 0.001));
        prefixes.add(new Prefix("micro", "\u00B5", 0.000001));
        prefixes.add(new Prefix("nano", "n", 0.000000001));
        prefixes.add(new Prefix("pico", "p", 0.000000000001));
        return prefixes;
    }
}
