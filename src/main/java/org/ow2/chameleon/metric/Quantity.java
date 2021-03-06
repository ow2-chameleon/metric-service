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

import org.ow2.chameleon.metric.converters.QuantityConverter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * This class defines what is a quantity. It offers methods to construct a quantity and
 * methods to build make arithmetical operations on quantities.
 *
 * @author Clement Escoffier, clement.escoffier@gamil.com
 * @param <Q> the kind of quantity.
 */
public class Quantity<Q extends Quantity<Q>> {

    /**
     * The UNKNOWN quantity kind.
     */
    public static final String UNKNOWN_NAME = "unknown";

    protected final Number number;
    protected final Unit<Q> unit;
    protected final String kind;

    /**
     * Creates a quantity.
     * @param kind the quantity's kind
     * @param number amount
     * @param unit unit
     */
    public Quantity(Class<Q> kind, Number number, Unit<Q> unit) {
        this.number = number;
        this.unit = unit;
        this.kind = kind.getName();
    }

    /**
     * Creates a quantity.
     * The kind is given as String. This constructor is only used internally.
     * @param kind the quantity's kind
     * @param number amount
     * @param unit unit
     */
    public Quantity(String kind, Number number, Unit<Q> unit) {
        this.number = number;
        this.unit = unit;
        this.kind = kind;
    }

    /**
     * Returns the amount corresponding to the specified value and unit.
     * The quantity's kind is inferred from the unit class.
     *
     * @param value the value stated in the specified unit.
     * @param unit  the unit in which the value is stated.
     * @return the corresponding amount.
     */
    public static <Q extends Quantity<Q>> Quantity<Q> valueOf(Class<Q> kind, Number value,
                                                       Unit<Q> unit) {
        return new Quantity<Q>(kind, value, unit);
    }

    public Number value() {
        return getNumber();
    }

    public Number getNumber() {
        return number;
    }

    public Unit<Q> unit() {
        return getUnit();
    }

    public Unit<Q> getUnit() {
        return unit;
    }

    /**
     * Gets the quantity 's kind.
     *
     * The result depends on how the quantity is built:
     * <ul>
     *     <li>if the `kind` parameter is set, returns the simple name of this class</li>
     *     <li>if the `kind` parameter is not set, infer it by reflection</li>
     *     <li>classes extending quantity mechanism that can't rely on the previous options must override this
     *     method.</li>
     * </ul>
     *
     * @return the quantity name, "unknown" if it can't be inferred.
     */
    public String getKind() {
        return kind;
    }

    /**
     * Adds the current quantity with the given one.
     * Before being added, the given quantity is converted to the current unit.
     * @param that the quantity to add
     * @return a new quantity adding the two quantities. The quantity's unit is the current unit.
     */
    public Quantity<Q> add(Quantity<Q> that) {
        Quantity<Q> that2 = that.as(unit);
        return new Quantity<Q>(kind, that2.getNumber().doubleValue() + this.getNumber().doubleValue(), unit);
    }

    public Quantity<Q> sub(Quantity<Q> that) {
        return add(that.times(-1));
    }

    public Quantity<Q> times(Number number) {
        return new Quantity<Q>(kind, getNumber().doubleValue() * number.doubleValue(), getUnit());
    }

    public boolean isMoreThan(Quantity<Q> that) {
        Quantity<Q> that2 = that.as(unit);
        return this.number.doubleValue() > that2.number.doubleValue();
    }

    public boolean isLessThan(Quantity<Q> that) {
        Quantity<Q> that2 = that.as(unit);
        return this.number.doubleValue() < that2.number.doubleValue();
    }

    public boolean isEqualTo(Quantity<Q> that, double delta) {
        Quantity<Q> that2 = that.as(unit);
        return Math.abs(that2.number.doubleValue() - number.doubleValue()) <= delta;
    }

    public boolean isEqualTo(Quantity<Q> that) {
        return isEqualTo(that, 0);
    }

    public Quantity<Q> getNormalizedQuantity() {
        if (unit instanceof TransformedUnit) {
            TransformedUnit<Q> transformedUnit = (TransformedUnit<Q>) getUnit();
            Quantity<Q> converted = transformedUnit.getConverter().convert(this);
            return converted.getNormalizedQuantity();
        } else {
            return this;
        }
    }

    public String toString() {
        return value() + unit().toString();
    }

    public Quantity<Q> as(Unit<Q> unit) {
        // Retrieve the normalized quantity
        Quantity<Q> normalized = getNormalizedQuantity();


        // For base and derived unit we apply dimensional analysis to determine if the units are compatible.
        if (!(this.unit instanceof TransformedUnit) && !(unit instanceof  TransformedUnit)) {
            Dimension dimension1 = this.unit.getDimension();
            Dimension dimension2 = unit.getDimension();
            if (! dimension1.equals(Dimension.NONE)  && ! dimension2.equals(Dimension.NONE)  && dimension1.equals
                    (dimension2)) {
                // this.unit and unit are actually the same.
                return new Quantity<Q>(kind, value(), unit);
            }
        }

        // Fall back to the conversion chain.

        QuantityConverter<Q> converter = MetricService.getInstance().getConverterRegistry()
                .findConverter(normalized.unit, unit);

        if (converter == null) {
            throw new IllegalArgumentException("Cannot convert " + this + " to " + unit + " - no converter in the " +
                    "registry");
        } else {
            return converter.convert(normalized);
        }
    }

}
