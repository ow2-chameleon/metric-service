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

import java.util.List;

/**
 * A system of units is a set of units which are chosen as the reference scales for some set of quantity kinds
 * together with the definitions of each unit. Units may be defined by experimental observation or by proportion to
 * another unit not included in the system.
 *
 * <strong>Important</strong> : not all units are part of a system.
 */
public interface SystemOfUnits {

    /**
     * @return the system name.
     */
    public String getName();

    /**
     * @return the current list of units defined in the system.
     */
    public List<Unit<?>> getUnits();

    /**
     * Searches for an unit in the system by its name.
     * @param name the unit name
     * @return the unit object or {@code null} if not found
     */
    public <Q extends Quantity<Q>> Unit<Q> getUnitByName(String name);

    /**
     * Searches for an unit in the system by its symbol.
     * @param symbol the unit symbol
     * @return the unit object or {@code null} if not found
     */
    public <Q extends Quantity<Q>> Unit<Q> getUnitBySymbol(String symbol);

    /**
     * Gets the base dimensions used by the system.
     * @return the set of dimension if defined
     */
    public List<Dimension> getDimensions();

    /**
     * Adds the given unit to the system of units.
     * It also creates and add all the prefixed unit computed from the prefixes of the system.
     * @param unit the unit
     * @param <Q> the kind of quantity
     * @return the current system of units
     */
    public <Q extends Quantity<Q>> SystemOfUnits addUnitToSystem(Unit<Q> unit);

    /**
     * Adds the given unit to the system of units.
     * @param unit the unit
     * @param addPrefixedUnits enable or disable the creation of the prefixed units
     * @param <Q> the kind of quantity
     * @return the current system of units
     */
    public <Q extends Quantity<Q>> SystemOfUnits addUnitToSystem(Unit<Q> unit, boolean addPrefixedUnits);

    /**
     * Removes the given unit from the system of units.
     * @param unit the unit to remove
     * @param <Q> the kind of quantity
     * @return the current system of units
     */
    public <Q extends Quantity<Q>> SystemOfUnits removeUnitFromSystem(Unit<Q> unit);

}
