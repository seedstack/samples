package org.generated.project.interfaces.rest;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.generated.project.domain.services.RequestBuilderCarrier;
import org.seedstack.jaeger.ServiceName;

import io.opentracing.Span;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;
import io.opentracing.propagation.Format;
import io.opentracing.propagation.TextMap;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Path("/api/v1/names")
public class NameGeneratorService {

    OkHttpClient client = new OkHttpClient();

    @ServiceName("name-svc")
    private Tracer tracer;

    @GET
    @Path("/random")
    public String name() throws Exception {

        Span span = tracer.buildSpan("generate-name").start();

        Span scientistSpan = tracer.buildSpan("scientist-name-service").asChildOf(span).start();
        String scientist = makeRequest("http://localhost:8090/api/v1/scientists/random", scientistSpan);
        scientistSpan.finish();

        Span animalSpan = tracer.buildSpan("animal-name-service").asChildOf(span).start();
        String animal = makeRequest("http://localhost:9000/api/v1/animals/random", animalSpan);
        animalSpan.finish();

        String name = scientist + "<-->" + animal;
        span.setTag("name", "HeynameTag");
        span.finish();
        return name;
    }

    private String makeRequest(String url, Span span) throws IOException {
        Request.Builder requestBuilder = new Request.Builder().url(url);

        SpanContext spanContext = span.context();
        Format<TextMap> format = Format.Builtin.HTTP_HEADERS;
        RequestBuilderCarrier requestBuilderCarriernew = new RequestBuilderCarrier(requestBuilder);

        tracer.inject(spanContext, format, requestBuilderCarriernew);

        Request request = requestBuilder.build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

}