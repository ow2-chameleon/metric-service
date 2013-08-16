package org.ow2.chameleon.metric.measure;

import org.ow2.chameleon.metric.Quantity;

import java.util.*;

/**
 * Vector measures store a list of measured quantities.
 */
public class VectorMeasure<Q extends Quantity<Q>> extends AbstractMeasure<List<MeasuredQuantity<Q>>> {

    private final List<MeasuredQuantity<Q>> measures;

    public VectorMeasure(String origin, Date date, List<MeasuredQuantity<Q>> measures) {
        super(origin, date);

        List<MeasuredQuantity<Q>> copy = new ArrayList<MeasuredQuantity<Q>>(measures);
        this.measures = Collections.unmodifiableList(copy);
    }

    @Override
    public List<MeasuredQuantity<Q>> content() {
        return measures;
    }

    /**
     * Builder to create vector measure object.
     * <p></p>
     *<code><pre>
     *      VectorMeasure<Acceleration> measure = new VectorMeasure.VectorMeasureBuilder<Acceleration>()
     *          .sensor("gyroscope")
     *          .hasMeasured(new Acceleration(1))
     *          .hasMeasured(new Acceleration(2), new Acceleration(3))
     *          .at(new Date())
     *          .build();
     *</pre></code>
     * @param <Q> the kind of quantity captured by the measure
     */
    public static class VectorMeasureBuilder<Q extends Quantity<Q>> {

        private String from;
        private List<MeasuredQuantity<Q>> quantities = new ArrayList<MeasuredQuantity<Q>>();
        private Date date;

        public VectorMeasureBuilder<Q> sensor(String origin) {
            this.from = origin;
            return this;
        }

        public VectorMeasureBuilder<Q> hasNotCapturedAValue() {
            this.quantities.add(MeasuredQuantity.<Q>notCaptured());
            return this;
        }

        public VectorMeasureBuilder<Q> takenBy(String origin) {
            return sensor(origin);
        }

        public VectorMeasureBuilder<Q> hasMeasured(Quantity<Q>... q) {
            for (Quantity<Q> quantity : q) {
                if (quantity instanceof MeasuredQuantity) {
                    this.quantities.add((MeasuredQuantity<Q>) quantity);
                } else {
                    this.quantities.add(new MeasuredQuantity<Q>(quantity));
                }
            }
            return this;
        }

        public VectorMeasureBuilder<Q> measure(Quantity<Q>... q) {
            return hasMeasured(q);
        }

        public VectorMeasureBuilder<Q> hasMeasured(Quantity<Q> q, double delta) {
            this.quantities.add(new MeasuredQuantity<Q>(q, delta));
            return this;
        }

        public VectorMeasureBuilder<Q> measure(Quantity<Q> q, double delta) {
            return hasMeasured(q, delta);
        }

        public VectorMeasureBuilder<Q> at(Date time) {
            this.date = time;
            return this;
        }

        public VectorMeasureBuilder<Q> at(long time) {
            this.date = new Date(time);
            return this;
        }

        public VectorMeasure<Q> build() {
            if (date == null) {
                throw new IllegalArgumentException("The measure date must be set");
            }
            if (quantities.isEmpty()) {
                throw new IllegalArgumentException("The measured quantity is empty");
            }
            return new VectorMeasure<Q>(from, date, quantities);
        }
    }
}
