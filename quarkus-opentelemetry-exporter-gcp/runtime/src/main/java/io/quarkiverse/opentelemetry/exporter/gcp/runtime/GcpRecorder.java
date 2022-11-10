package io.quarkiverse.opentelemetry.exporter.gcp.runtime;

import java.io.IOException;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.spi.CDI;

import com.google.cloud.opentelemetry.trace.TraceExporter;

import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;
import io.quarkus.runtime.annotations.Recorder;

@Recorder
public class GcpRecorder {
    public void installBatchSpanProcessorForGcp() {

        try {
            // Create TraceExporter and install into LateBoundBatchSpanProcessor
            LateBoundBatchSpanProcessor delayedProcessor = CDI.current()
                    .select(LateBoundBatchSpanProcessor.class, Any.Literal.INSTANCE).get();
            TraceExporter traceExporters = TraceExporter.createWithDefaultConfiguration();
            delayedProcessor.setBatchSpanProcessorDelegate(BatchSpanProcessor.builder(traceExporters).build());
        } catch (IllegalArgumentException iae) {
            throw new IllegalStateException("Unable to install GCP Exporter", iae);
        } catch (IOException e) {
            throw new RuntimeException("Unable to install GCP Exporter", e);
        }
        //}
    }
}
