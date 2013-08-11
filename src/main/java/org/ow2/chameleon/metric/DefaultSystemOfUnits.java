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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Default implementation of a system of units.
 */
public class DefaultSystemOfUnits implements SystemOfUnits {

    private final List<Dimension> dimensions;
    private final String name;
    private final List<Prefix> prefixes;
    private List<Unit<?>> units = new ArrayList<Unit<?>>();

    public DefaultSystemOfUnits(String name, List<Dimension> dimensions, List<Prefix> prefixes) {
        this.name = name;
        if (dimensions != null) {
            this.dimensions = new ArrayList<Dimension>(dimensions);
        } else {
            this.dimensions = Collections.emptyList();
        }
        if (prefixes != null) {
            this.prefixes = new ArrayList<Prefix>(prefixes);
        } else {
            this.prefixes = Collections.emptyList();
        }
    }

    public synchronized <Q extends Quantity<Q>> SystemOfUnits addUnitToSystem(Unit<Q> unit) {
        return  addUnitToSystem(unit, true);
    }

    @Override
    public <Q extends Quantity<Q>> SystemOfUnits addUnitToSystem(Unit<Q> unit, boolean addPrefixedUnits) {
        if (!this.units.contains(unit)) {
            this.units.add(unit);

            // Add the unit for all prefixes.
            if (addPrefixedUnits) {
                for (Prefix prefix : prefixes) {
                    // Add only if the unit does not exist yet
                    final PrefixedUnit prefixed = new PrefixedUnit<Q>(unit, prefix);
                    if (!this.units.contains(prefixed)) {
                        this.units.add(prefixed);
                    }
                }
            }
        }
        return this;
    }

    public synchronized <Q extends Quantity<Q>> SystemOfUnits removeUnitFromSystem(Unit<Q> unit) {
        boolean removed = this.units.remove(unit);

        if (removed) {
            // Remove all transformed units based on the remove unit (this include prefixed unit).
            List<Unit> list = new ArrayList<Unit>(this.units);
            for (Unit u : list) {
                if (u instanceof TransformedUnit && ((TransformedUnit) u).getParent().equals(unit)) {
                    // Remove the converter too
                    this.units.remove(unit);
                    MetricService.getInstance().getConverterRegistry().removeConverter(((TransformedUnit) u)
                            .getConverter());
                }
            }
        }

        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Unit<?>> getUnits() {
        return new ArrayList<Unit<?>>(units);
    }

    @Override
    public synchronized <Q extends Quantity<Q>> Unit<Q> getUnitByName(String name) {
        for (Unit<?> unit : units) {
            if (unit.getName() != null && unit.getName().equals(name)) {
                //noinspection unchecked
                return (Unit<Q>) unit;
            }
        }
        return null;
    }

    @Override
    public <Q extends Quantity<Q>> Unit<Q> getUnitBySymbol(String symbol) {
        for (Unit<?> unit : units) {
            if (unit.getSymbol() != null && unit.getSymbol().equals(symbol)) {
                //noinspection unchecked
                return (Unit<Q>) unit;
            }
        }
        return null;
    }

    @Override
    public List<Dimension> getDimensions() {
        return new ArrayList<Dimension>(dimensions);
    }
}
