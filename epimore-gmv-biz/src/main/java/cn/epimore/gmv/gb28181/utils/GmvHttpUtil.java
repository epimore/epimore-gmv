package cn.epimore.gmv.gb28181.utils;

import cn.epimore.gmv.vo.GmvSessionResult;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class GmvHttpUtil {
    private final static Logger logger = LoggerFactory.getLogger(GmvHttpUtil.class);
    private static final MediaType JSON_TYPE
            = MediaType.parse("application/json; charset=utf-8");

    public static <T> GmvSessionResult<T> post(String url, Object obj, Class<T> clazz) {
        try {
            OkHttpClient client = new OkHttpClient().newBuilder().readTimeout(16, TimeUnit.SECONDS).build();
            RequestBody body = RequestBody.create(JSON_TYPE, JSON.toJSONString(obj));
            String u_id = CurrentUserHelper.getSystemUser().getId();
            Request request = new Request.Builder()
                    .addHeader("gmv-token", u_id)
                    .url(url)
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful() && response.body() != null) {
                    String bodyStr = response.body().string();
                    logger.info("response:{}", bodyStr);
                    if (StringUtils.isNotEmpty(bodyStr)) {
                        return JSONObject.parseObject(bodyStr, new TypeReference<GmvSessionResult<T>>(clazz) {
                        });
                    }
                } else {
                    if (response.body()!=null){
                        logger.error("post异常:code:{},message:{},payload:{}", response.code(), response.message(), response.body().string());
                    }else {
                        logger.error("post异常:code:{},message:{}", response.code(), response.message());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("post异常", e);
        }
        return null;
    }
}
