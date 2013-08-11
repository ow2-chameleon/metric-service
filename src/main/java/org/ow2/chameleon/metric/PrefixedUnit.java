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

package org.ow2.chameleon.metric;

import org.ow2.chameleon.metric.converters.Multiplication;

/**
 * Represent an unit prefixed by a `prefix`.
 * Km is a prefixed unit, cm too.
 */
public class PrefixedUnit<Q extends Quantity<Q>> extends TransformedUnit<Q> {

    /**
     * The prefix.
     */
    private final Prefix prefix;

    /**
     * Creates a prefixed unit.
     *
     * @param unit   the parent unit
     * @param prefix the prefix
     */
    public PrefixedUnit(Unit<Q> unit, Prefix prefix) {
        super(
                prefix.symbol + unit.getSymbol(),
                prefix.text + unit.getName(),
                unit,
                new Multiplication(prefix.factor),
                unit.getDimension()
        );
        MetricService.getInstance().getConverterRegistry().addConverter(getConverter());
        this.prefix = prefix;
    }

    public Prefix getPrefix() {
        return prefix;
    }
}
