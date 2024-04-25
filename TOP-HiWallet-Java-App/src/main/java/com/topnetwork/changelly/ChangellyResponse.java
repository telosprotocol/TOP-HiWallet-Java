package com.topnetwork.changelly;

import java.io.Serializable;

/**
 * @ClassName ChangellyResponse
 * @Description
 * @Author bran
 * @Date 2020/6/11 14:34
 */
public class ChangellyResponse implements Serializable {
    private static final long serialVersionUID = 43948582629964283L;
    private String jsonrpc;
    private String id;
    private ChangellyError error;

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ChangellyError getError() {
        return error;
    }

    public void setError(ChangellyError error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ChangellyResponse{" +
                "jsonrpc='" + jsonrpc + '\'' +
                ", id='" + id + '\'' +
                ", error=" + error +
                '}';
    }
}
