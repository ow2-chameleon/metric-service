package org.ow2.chameleon.metric.measure;

import org.ow2.chameleon.metric.Quantity;
import org.ow2.chameleon.metric.Unit;

/**
 * Measured quantities are quantities with min and max derivation. The exact value is in the range [value -
 * minDerivation, value + maxDerivation].
 *
 * Measured values are immutable.
 */
public class MeasuredQuantity<Q extends Quantity<Q>> extends Quantity<Q> {

    /**
     * A specific quantity used for measure that are invalid (because of a device failure, or issue in the response).
     * This quantify uses a specific unit 'NaM' (not a measure) and has a zero-value.
     */
    @SuppressWarnings("unchecked")
    public static MeasuredQuantity NOT_CAPTURED_QUANTITY = new MeasuredQuantity("", 0.0, new Unit("\u2205", "NaM"), 0, 0);

    /**
     * Gets a not captured quantity.
     * @param <Q> the kind
     * @return a not captured quantity
     */
    public static <Q extends Quantity<Q>> MeasuredQuantity<Q> notCaptured() {
        //noinspection unchecked
        return NOT_CAPTURED_QUANTITY;
    }

    /**
     * The min derivation.
     */
    public final double minDerivation;

    /**
     * The max derivation.
     */
    public final double maxDerivation;


    /**
     * Creates a measured value.
     * @param kind the quantity kind
     * @param value the value
     * @param unit the unit
     * @param minDerivation the min derivation
     * @param maxDerivation the max derivation
     */
    public MeasuredQuantity(String kind, Number value, Unit<Q> unit, double minDerivation, double maxDerivation) {
        super(kind, value, unit);
        this.minDerivation = minDerivation;
        this.maxDerivation = maxDerivation;
    }

    /**
     * Creates a measured value.
     * @param kind the quantity kind
     * @param value the value
     * @param unit the unit
     * @param minDerivation the min derivation
     * @param maxDerivation the max derivation
     */
    public MeasuredQuantity(Class<Q> kind, Number value, Unit<Q> unit, double minDerivation, double maxDerivation) {
        this(kind.getName(), value, unit, minDerivation, maxDerivation);
    }

    /**
     * Creates a measured value from an existing quantity
     * @param quantity the quantity
     * @param minDerivation the min derivation
     * @param maxDerivation the max derivation
     */
    public MeasuredQuantity(Quantity<Q> quantity, double minDerivation, double maxDerivation) {
        this(quantity.getKind(), quantity.value(), quantity.unit(), minDerivation, maxDerivation);
    }

    /**
     * Creates a measured value when derivations are 0.0 (the measure is exact).
     * @param value the quantity
     */
    public MeasuredQuantity(Quantity<Q> value) {
        this(value, 0, 0);
    }

    /**
     * Creates a measured value with the min derivation = max derivation = delta.
     * @param value the quantity
     * @param delta the delta
     */
    public MeasuredQuantity(Quantity<Q> value, double delta) {
        this(value, delta, delta);
    }


    /**
     * Checks whether the given value is in the range of the measure.
     * @param v the value to test
     * @return {@code true} if the value is in the range, {@code false} otherwise.
     */
    public boolean isInRange(double v) {
        return v >= value().doubleValue() - minDerivation  && v <= value().doubleValue() + maxDerivation;
    }

    /**
     * Checks whether the given quantity is in the range of the measure.
     * The quantity is converted to the current quantity unit before applying the test.
     * @param q the quantity
     * @return {@code true} if the value of the given quantity converted to the current quantity's unit is in the
     * range, {@code false} otherwise.
     */
    public boolean isInRange(Quantity<Q> q) {
        // convert q to the measured unit
        Quantity<Q> that = q.as(unit());
        return isInRange(that.value().doubleValue());
    }

    /**
     * Checks whether the measured value has an actual value or if the stored quantity is a mock representing a not
     * captured value.
     * @return {@code true} if the measured value reflects a not captured value
     */
    public boolean isNotCaptured() {
        return this == NOT_CAPTURED_QUANTITY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MeasuredQuantity that = (MeasuredQuantity) o;

        if (Double.compare(that.maxDerivation, maxDerivation) != 0) return false;
        if (Double.compare(that.minDerivation, minDerivation) != 0) return false;

        if (Double.compare(that.value().doubleValue(), value().doubleValue()) != 0) return false;
        if (! that.unit().equals(unit())) return false;

        return true;
    }

    @Override
    public String toString() {
        return "MeasuredQuantity{" +
                "value=" + value() + " " + unit().getSymbol() +
                ", minDerivation=" + minDerivation +
                ", maxDerivation=" + maxDerivation +
                '}';
    }

    /**
     * Checks whether the measured quantity is exact (i.e. minDerivation = maxDerivation = 0).
     * @return {@code true} is the measure is exact
     */
    public boolean isExact() {
        return ! isNotCaptured()  && minDerivation == 0  && maxDerivation == 0;
    }

    public double getMinDerivation() {
        return minDerivation;
    }

    public double getMaxDerivation() {
        return maxDerivation;
    }
}
