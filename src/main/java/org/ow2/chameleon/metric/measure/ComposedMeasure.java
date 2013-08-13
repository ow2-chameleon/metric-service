package org.ow2.chameleon.metric.measure;

import org.ow2.chameleon.metric.Quantity;

import java.util.*;

/**
 * Vector measures store a set of named measured quantities. You can imagine that each named quantities is a field.
 */
public class ComposedMeasure<Q extends Quantity<Q>> extends AbstractMeasure<Map<String, MeasuredQuantity<Q>>> {

    private final Map<String, MeasuredQuantity<Q>> measures;

    public ComposedMeasure(String origin, Date date, Map<String, MeasuredQuantity<Q>> measures) {
        super(origin, date);

        Map<String, MeasuredQuantity<Q>> copy = new LinkedHashMap<String, MeasuredQuantity<Q>>(measures);
        this.measures = Collections.unmodifiableMap(copy);
    }

    @Override
    public Map<String, MeasuredQuantity<Q>> measure() {
        return measures;
    }

    /**
     * Builder to create composed measure object.
     * @param <Q> the kind of quantity captured by the measure
     */
    public static class ComposedMeasureBuilder<Q extends Quantity<Q>> {

        private String from;
        private Map<String, MeasuredQuantity<Q>> quantities = new LinkedHashMap<String, MeasuredQuantity<Q>>();
        private Date date;

        public ComposedMeasureBuilder<Q> sensor(String origin) {
            this.from = origin;
            return this;
        }

        public ComposedMeasureBuilder<Q> hasMeasured(String name, Quantity<Q> q) {
            this.quantities.put(name, new MeasuredQuantity<Q>(q));
            return this;
        }

        public ComposedMeasureBuilder<Q> hasMeasured(String name, Quantity<Q> q, double delta) {
            this.quantities.put(name, new MeasuredQuantity<Q>(q, delta));
            return this;
        }

        public ComposedMeasureBuilder<Q> at(Date time) {
            this.date = time;
            return this;
        }

        public ComposedMeasureBuilder<Q> at(long time) {
            this.date = new Date(time);
            return this;
        }

        public ComposedMeasure<Q> build() {
            if (date == null) {
                throw new IllegalArgumentException("The measure date must be set");
            }
            if (quantities.isEmpty()) {
                throw new IllegalArgumentException("The measured quantity is not set");
            }
            return new ComposedMeasure<Q>(from, date, quantities);
        }
    }
}
