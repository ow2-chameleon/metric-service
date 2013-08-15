package org.ow2.chameleon.metric.measure;

import java.util.Date;

/**
 * A measure is a {@link MeasuredQuantity} taken at a specific time by 'something' or 'someone'.
 * It can contains scalar quantities, vector of quantities or be composed by a set of named quantities.
 * For instance, a voltmeter takes scalar measure; a GPS takes a set of quantities (latitude,
 * longitude...); a gyroscope captures a set of 3 quantities (x,y,z).
 *
 * Measure objects are immutable.
 *
 * @param <T> the type of structure stored by the measure
 * @see ScalarMeasure
 * @see ComposedMeasure
 * @see VectorMeasure
 */
public interface Measure<T> {

    /**
     * Gets the capture time.
     * @return the date when the measure is taken.
     */
    public Date date();

    /**
     * The structure of measured quantity.
     * @return a captured data
     */
    public T content();

    /**
     * The origin of the measure (sensor id or whatever enforcing traceability).
     * @return the origin
     */
    public String origin();

}
