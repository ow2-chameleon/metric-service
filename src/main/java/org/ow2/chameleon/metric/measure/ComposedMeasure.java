package org.ow2.chameleon.metric.measure;

import org.ow2.chameleon.metric.Quantity;

import java.util.*;

/**
 * Composed measures store a set of named measured quantities.
 * You can imagine that each named quantities is a field.
 */
public class ComposedMeasure extends AbstractMeasure<Map<String, MeasuredQuantity>> {

    private final Map<String, MeasuredQuantity> measures;

    public ComposedMeasure(String origin, Date date, Map<String, MeasuredQuantity> measures) {
        super(origin, date);

        Map<String, MeasuredQuantity> copy = new LinkedHashMap<String, MeasuredQuantity>(measures);
        this.measures = Collections.unmodifiableMap(copy);
    }

    @Override
    public Map<String, MeasuredQuantity> content() {
        return measures;
    }

    /**
     * Retrieves a content measured quantity from its name. The given kind must be the same as the measured
     * quantity kind.
     * @param name the name
     * @param kind the kind
     * @return the measured quantity, {@code null} if not found.
     * @throws IllegalArgumentException if the given kind is incompatible with the measured quantity kind
     */
    public <K extends Quantity<K>> MeasuredQuantity<K> get(String name, Class<K> kind) {
        MeasuredQuantity q =  content().get(name);
        if (q == null) {
            return null;
        }

        if (q.getUnit().equals(MeasuredQuantity.NOT_CAPTURED_QUANTITY.unit()) || q.getKind().equals(kind.getName())) {
            //noinspection unchecked
            return q;
        } else {
            throw new IllegalArgumentException("Incompatible quantity kind between the measured quantity " + q + " " +
                    "and " + kind.getName());
        }

    }

    /**
     * Retrieves a content measured from its name. Be care about the kind you use,
     * this method does not enforce the kind.
     * @param name the name
     * @param <K> the kind
     * @return the measured quantity, or {@code null} if not found.
     */
    public <K extends Quantity<K>> MeasuredQuantity<K> get(String name) {
        //noinspection unchecked
        return content().get(name);
    }

    /**
     * Builder to create composed measure object.
     */
    public static class ComposedMeasureBuilder {

        private String from;
        private Map<String, MeasuredQuantity> quantities = new LinkedHashMap<String, MeasuredQuantity>();
        private Date date;

        public ComposedMeasureBuilder sensor(String origin) {
            this.from = origin;
            return this;
        }

        public ComposedMeasureBuilder hasMeasured(String name, Quantity q) {
            this.quantities.put(name, new MeasuredQuantity(q));
            return this;
        }

        public ComposedMeasureBuilder hasMeasured(String name, Quantity q, double delta) {
            this.quantities.put(name, new MeasuredQuantity(q, delta));
            return this;
        }

        public ComposedMeasureBuilder at(Date time) {
            this.date = time;
            return this;
        }

        public ComposedMeasureBuilder at(long time) {
            this.date = new Date(time);
            return this;
        }

        public ComposedMeasure build() {
            if (date == null) {
                throw new IllegalArgumentException("The measure date must be set");
            }
            if (quantities.isEmpty()) {
                throw new IllegalArgumentException("The measured quantity is not set");
            }
            return new ComposedMeasure(from, date, quantities);
        }
    }
}
