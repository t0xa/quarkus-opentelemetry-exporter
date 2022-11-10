package io.quarkiverse.opentelemetry.exporter.gcp.runtime;

import io.quarkus.arc.DefaultBean;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@Singleton
public class GcpExporterProvider {
    @Produces
    @Singleton
    @DefaultBean
    public LateBoundBatchSpanProcessor spanProcessorForGCP() {
        return new LateBoundBatchSpanProcessor();
    }
}
