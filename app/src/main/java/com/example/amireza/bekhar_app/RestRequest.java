package com.example.amireza.bekhar_app;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class RestRequest {

    private final URL mUrl;
    private final Object mBody;
    private Listener mListener;

    public RestRequest(String url, Map<String, String> headers, Object body) throws MalformedURLException {
        mUrl = new URL(url);
        mBody = body;
    }

    public void execute(Listener listener) {

    }

    public interface Listener {

    }

}
