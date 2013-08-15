package org.ow2.chameleon.metric.measure;

import org.ow2.chameleon.metric.Quantity;

import java.util.Date;

/**
 * A scalar measure is a measure storing a `simple` quantity.
 */
public class ScalarMeasure<Q extends Quantity<Q>> extends AbstractMeasure<MeasuredQuantity<Q>> {

    /**
     * The stored quantity.
     */
    private final MeasuredQuantity<Q> quantity;

    public ScalarMeasure(String origin, Date date, MeasuredQuantity<Q> quantity) {
        super(origin, date);
        this.quantity = quantity;
    }

    @Override
    public MeasuredQuantity<Q> content() {
    return quantity;
    }


    /**
     * Builder to create scalar measure object.
     *
     * <p><strong>Examples:</strong></p>
     * <code><pre>
     *     ScalarMeasure<Temperature> measure = new ScalarMeasure.ScalarMeasureBuilder<Temperature>()
     *          .sensor("thermometer")
     *          .hasMeasured(new Temperature(10, Temperature.CELSIUS))
     *          .at(new Date())
     *          .build();
     *
     *     ScalarMeasure<Temperature> measure2 = new ScalarMeasure.ScalarMeasureBuilder<Temperature>()
     *          .measure(new Temperature(10, Temperature.CELSIUS))
     *          .takenBy("thermometer")
     *          .at(new Date())
     *          .build();
     * </pre></code>
     *
     * @param <Q> the kind of quantity captured by the measure
     */
    public static class ScalarMeasureBuilder<Q extends Quantity<Q>> {

        private String from;
        private Quantity<Q> quantity;
        private double minDeviation;
        private double maxDeviation;
        private Date date;
        private boolean notCaptured;

        public ScalarMeasureBuilder<Q> sensor(String origin) {
            this.from = origin;
            return this;
        }

        public ScalarMeasureBuilder<Q> hasNotCapturedAValueAt(long time) {
            return hasNotCapturedAValueAt(new Date(time));
        }

        public ScalarMeasureBuilder<Q> hasNotCapturedAValueAt(Date time) {
            this.notCaptured = true;
            this.date = time;
            return this;
        }

        public ScalarMeasureBuilder<Q> hasMeasured(Quantity<Q> q) {
            this.quantity = q;
            return this;
        }

        public ScalarMeasureBuilder<Q> takenBy(String origin) {
            return  sensor(origin);
        }

        public ScalarMeasureBuilder<Q> measure(Quantity<Q> q) {
            return hasMeasured(q);
        }

        public ScalarMeasureBuilder<Q> hasMeasured(Quantity<Q> q, double delta) {
            this.quantity = q;
            this.minDeviation = delta;
            this.maxDeviation = delta;
            return this;
        }

        public ScalarMeasureBuilder<Q> measure(Quantity<Q> q, double delta) {
            return hasMeasured(q, delta);
        }

        public ScalarMeasureBuilder<Q> at(Date time) {
            this.date = time;
            return this;
        }

        public ScalarMeasureBuilder<Q> at(long time) {
            this.date = new Date(time);
            return this;
        }

        public ScalarMeasure<Q> build() {
            if (date == null) {
                throw new IllegalArgumentException("The measure date must be set");
            }
            if (quantity == null  && ! notCaptured) {
                throw new IllegalArgumentException("The measured quantity is not set");
            }
            if (! notCaptured) {
                return new ScalarMeasure<Q>(from, date, new MeasuredQuantity<Q>(quantity, minDeviation, maxDeviation));
            } else {
                return new ScalarMeasure<Q>(from, date, MeasuredQuantity.<Q>notCaptured());
            }
        }
    }
}
