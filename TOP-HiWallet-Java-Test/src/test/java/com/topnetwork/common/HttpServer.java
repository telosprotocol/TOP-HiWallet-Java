package com.topnetwork.common;

import java.util.Map;

import com.gitee.magic.framework.base.rest.RestfulTemplate;
import com.gitee.magic.framework.base.rest.RequestMethodEnum;
import com.gitee.magic.core.json.JsonObject;

public class HttpServer {

    private final String SERVERURL = "http://127.0.0.1:8080";
//    private final String SERVERURL = "https://www.hiwallet.org/hiwallet";
//    private final String SERVERURL = "http://18.140.219.191:8080";

    public void postRequest(String action, String accessid, String accesskey, Map<String, Object> content)
            throws Exception {
        postRequest(action, accessid, accesskey, content, new Result() {

            @Override
            public void success(JsonObject json) {
                System.out.println(action + ":" + json);
            }

        });
    }

    public void postRequest(String action, String accessid, String accesskey, Map<String, Object> content, Result result) {
        RestfulTemplate restful = new RestfulTemplate(accessid, accesskey);
        String data = restful.requestBodyForMap(SERVERURL + action, content,RequestMethodEnum.POST);
        try {
            JsonObject json = new JsonObject(data);
            int code = json.getInt("code");
            if (code == 200) {
                result.success(json);
            } else {
                System.out.println(action + ":" + json);
            }
        } catch (Exception e) {
            System.out.println(action + ":" + data);
            e.printStackTrace();
        }
    }

    public static abstract class Result {
        public abstract void success(JsonObject json);
    }

}
