package com.mql5.autotests.entities;

public class LstToRequest {
    private RequestData data;

    public RequestData getRequestData() {
        return data;
    }

    public void setRequestData(RequestData requestData) {
        this.data = requestData;
    }

    @Override
    public String toString() {
        return "LstToRequest{" +
                "data=" + data +
                '}';
    }
}
