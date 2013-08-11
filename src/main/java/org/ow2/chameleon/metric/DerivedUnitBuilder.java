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

import org.ow2.chameleon.metric.quantities.Force;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Builder to create derived units. This class can also be used to create base units, but its now its primary purpose.
 *
 * <code>
 *     final Unit<Force> newton = new DerivedUnitBuilder<Force>().from(GRAM).times(SI.METRE)
 *                                      .pow(SI.SECOND, -2).name("newton").symbol("N").build();
 * </code>
 */
public class DerivedUnitBuilder<Q extends Quantity<Q>> {

    private Map<Unit<?>, Integer> products = new LinkedHashMap<Unit<?>, Integer>();
    private String name;
    private String symbol;
    private String system;

    public DerivedUnitBuilder<Q> from(Unit<?> unit) {
        products.put(unit, 1);
        return this;
    }

    public DerivedUnitBuilder<Q> symbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public DerivedUnitBuilder<Q> name(String name) {
        this.name = name;
        return this;
    }

    public DerivedUnitBuilder<Q> times(Unit<?> unit) {
        return pow(unit, 1);
    }

    public DerivedUnitBuilder<Q> dividedBy(Unit<?> unit) {
        return dividedBy(unit, 1);
    }

    public DerivedUnitBuilder<Q> dividedBy(Unit<?> unit, int power) {
        if (products.containsKey(unit)) {
            products.put(unit, products.get(unit) - power);
        } else {
            products.put(unit, - power);
        }
        return this;
    }

    public DerivedUnitBuilder<Q> pow(Unit<?> unit, int n) {
        if (products.containsKey(unit)) {
            products.put(unit, products.get(unit) + n);
        } else {
            products.put(unit, n);
        }
        return this;
    }

    public DerivedUnitBuilder<Q> addToSystem(String system) {
        this.system = system;
        return this;
    }

    public Unit<Q> build() {
        // Enforcement
        if (symbol == null) {
            if (products == null  || products.isEmpty()) {
                throw new IllegalArgumentException("Cannot build an unit without symbol");
            } else {
                // Use the unit product as symbol
                products = UnitProduct.consolidate(products);
                symbol = UnitProduct.toString(products);
            }
        }

        Unit<Q> unit;
        if (products.isEmpty()) {
            unit = new Unit<Q>(symbol, name);
        } else {
            products = UnitProduct.consolidate(products);
            unit = new DerivedUnit<Q>(symbol, name, products);
        }

        if (system != null) {
            SystemOfUnits systemOfUnits = MetricService.getInstance().getSystemOfUnits(system);
            if (systemOfUnits == null) {
                throw new IllegalArgumentException("Cannot register the unit to " + system + " - unknown system of " +
                        "units");
            }
            systemOfUnits.addUnitToSystem(unit);
        }

        return unit;
    }


}
