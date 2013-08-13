package org.ow2.chameleon.metric.measure;

import java.util.Date;

/**
 * Abstract class containing the common code of measure implementation.
 */
public abstract class AbstractMeasure<T> implements Measure<T> {

    /**
     * The origin of the measure.
     */
    private final String origin;

    /**
     * The captured time.
     */
    private final Date captureTime;

    public AbstractMeasure(String origin, long captureTime) {
        this.origin = origin;
        this.captureTime = new Date(captureTime);
    }

    public AbstractMeasure(String origin, Date captureTime) {
        this.origin = origin;
        this.captureTime = captureTime;
    }

    @Override
    public Date getCaptureTime() {
        return captureTime;
    }

    @Override
    public String origin() {
        return origin;
    }

}