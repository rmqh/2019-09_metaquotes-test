package com.mql5.autotests.entities;

public class LstToResponse {
    private ResponseData data;

    public ResponseData getData() {
        return data;
    }

    public void setData(ResponseData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LstToResponse{" +
                "data=" + data +
                '}';
    }
}
