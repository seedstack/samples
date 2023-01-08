package org.generated.project.interfaces.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;

import org.seedstack.jaeger.Tracing;

import io.opentracing.Span;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;
import io.opentracing.propagation.Format;
import io.opentracing.propagation.TextMapAdapter;

@Path("/api/v1/scientists")
public class ScientistNameService {

    private final List<String> scientistsNames;
    private Random random;

    @Tracing("scientist-svc")
    private Tracer tracer;

    public ScientistNameService() throws IOException {
        InputStream inputStream = this.getClass().getResourceAsStream("/scientists.txt");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            scientistsNames = reader.lines().collect(Collectors.toList());
        }
        random = new Random();
    }

    @GET
    @Path("/random")
    public String name(@Context HttpHeaders headers) {

        MultivaluedMap<String, String> rawHeaders = headers.getRequestHeaders();
        final Map<String, String> header = new HashMap<>();

        for (Map.Entry<String, List<String>> entry : rawHeaders.entrySet()) {
            header.put(entry.getKey(), entry.getValue().get(0));
        }

        SpanContext parentContext = tracer.extract(Format.Builtin.HTTP_HEADERS, new TextMapAdapter(header));
        Span span = tracer.buildSpan("find-random-scientist-name").asChildOf(parentContext).start();
        span.setTag("scientist", "HeyscientistTag");
        String name = scientistsNames.get(random.nextInt(scientistsNames.size()));
        span.finish();
        return name;
    }
}