/*
 * Creation : 9 Jun 2021
 */
package org.generated.project.domain.services;

import java.util.Map;

import io.jaegertracing.internal.metrics.Counter;
import io.jaegertracing.internal.metrics.Gauge;
import io.jaegertracing.internal.metrics.Timer;
import io.jaegertracing.spi.MetricsFactory;

public class MyMetricFactory implements MetricsFactory {

    @Override
    public Counter createCounter(String name, Map<String, String> tags) {
        return new Counter() {

            @Override
            public void inc(long delta) {
            }
        };
    }

    @Override
    public Timer createTimer(final String name, final Map<String, String> tags) {
        return new Timer() {

            @Override
            public void durationMicros(long time) {
            }
        };
    }

    @Override
    public Gauge createGauge(final String name, final Map<String, String> tags) {
        return new Gauge() {

            @Override
            public void update(long amount) {
            }
        };
    }

}