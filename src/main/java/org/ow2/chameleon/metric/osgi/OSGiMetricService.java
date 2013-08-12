package org.ow2.chameleon.metric.osgi;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.ow2.chameleon.metric.MetricService;
import org.ow2.chameleon.metric.SystemOfUnits;
import org.ow2.chameleon.metric.converters.ConverterRegistry;

import java.util.List;

/**
 * Component exposing the metric service as OSGi service.
 */
@Component(immediate=true)
@Provides(specifications=MetricService.class)
@Instantiate
public class OSGiMetricService extends MetricService {

    private MetricService delegate = MetricService.getInstance();

    @Override
    public List<SystemOfUnits> getSystemsOfUnits() {
        return delegate.getSystemsOfUnits();
    }

    @Override
    public SystemOfUnits getSystemOfUnits(String name) {
        return delegate.getSystemOfUnits(name);
    }

    @Override
    public void addSystemOfUnits(SystemOfUnits system) {
        delegate.addSystemOfUnits(system);
    }

    @Override
    public void removeSystemOfUnits(SystemOfUnits system) {
        delegate.removeSystemOfUnits(system);
    }

    @Override
    public ConverterRegistry getConverterRegistry() {
        return delegate.getConverterRegistry();
    }
}
