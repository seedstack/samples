package org.generated.project.domain.services;

import io.opentracing.propagation.TextMap;
import okhttp3.Request;

import java.util.Iterator;
import java.util.Map;

public class RequestBuilderCarrier implements TextMap {
    private final Request.Builder requestBuilder;

    public RequestBuilderCarrier(Request.Builder requestBuilder) {
        this.requestBuilder = requestBuilder;
    }

    @Override
    public Iterator<Map.Entry<String, String>> iterator() {
        throw new UnsupportedOperationException("carrier is writer-only");
    }

    @Override
    public void put(String key, String value) {
        requestBuilder.addHeader(key, value);
    }
}
