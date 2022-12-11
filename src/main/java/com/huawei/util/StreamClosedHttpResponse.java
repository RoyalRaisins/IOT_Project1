package com.huawei.util;

import org.apache.http.*;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.util.Locale;

public class StreamClosedHttpResponse implements HttpResponse {

    private final HttpResponse httpResponse;

    private final String content;

    public StreamClosedHttpResponse(HttpResponse httpResponse) throws IOException {
        this.httpResponse = httpResponse;

        HttpEntity entity = httpResponse.getEntity();

        if (entity != null && entity.isStreaming()) {
            String encode = entity.getContentEncoding() != null ? entity.getContentEncoding().getValue() : null;

            content = StreamUtil.inputStream2String(entity.getContent(), encode);
        } else {
            content = null;
        }

    }


    public StatusLine getStatusLine() {
        return httpResponse.getStatusLine();
    }

    public void setStatusLine(StatusLine statusLine) {
        httpResponse.setStatusLine(statusLine);
    }

    public void setStatusLine(ProtocolVersion protocolVersion, int i) {
        httpResponse.setStatusLine(protocolVersion, i);
    }

    public void setStatusLine(ProtocolVersion protocolVersion, int i, String s) {
        httpResponse.setStatusLine(protocolVersion, i, s);
    }

    public void setStatusCode(int i) throws IllegalStateException {
        httpResponse.setStatusCode(i);
    }

    public void setReasonPhrase(String s) throws IllegalStateException {
        httpResponse.setReasonPhrase(s);
    }

    public HttpEntity getEntity() {
        return httpResponse.getEntity();
    }

    public void setEntity(HttpEntity httpEntity) {
        httpResponse.setEntity(httpEntity);
    }

    public Locale getLocale() {
        return httpResponse.getLocale();
    }

    public void setLocale(Locale locale) {
        httpResponse.setLocale(locale);
    }

    public ProtocolVersion getProtocolVersion() {
        return httpResponse.getProtocolVersion();
    }

    public boolean containsHeader(String s) {
        return httpResponse.containsHeader(s);
    }

    public Header[] getHeaders(String s) {
        return httpResponse.getHeaders(s);
    }

    public Header getFirstHeader(String s) {
        return httpResponse.getFirstHeader(s);
    }

    public Header getLastHeader(String s) {
        return httpResponse.getLastHeader(s);
    }

    public Header[] getAllHeaders() {
        return httpResponse.getAllHeaders();
    }

    public void addHeader(Header header) {
        httpResponse.addHeader(header);
    }

    public void addHeader(String s, String s1) {
        httpResponse.addHeader(s, s1);
    }

    public void setHeader(Header header) {
        httpResponse.setHeader(header);
    }

    public void setHeader(String s, String s1) {
        httpResponse.setHeader(s, s1);
    }

    public void setHeaders(Header[] headers) {
        httpResponse.setHeaders(headers);
    }

    public void removeHeader(Header header) {
        httpResponse.removeHeader(header);
    }

    public void removeHeaders(String s) {
        httpResponse.removeHeaders(s);
    }

    public HeaderIterator headerIterator() {
        return httpResponse.headerIterator();
    }

    public HeaderIterator headerIterator(String s) {
        return httpResponse.headerIterator(s);
    }

    public HttpParams getParams() {
        return httpResponse.getParams();
    }

    public void setParams(HttpParams httpParams) {
        httpResponse.setParams(httpParams);
    }

    public String getContent() {
        return content;
    }
}
