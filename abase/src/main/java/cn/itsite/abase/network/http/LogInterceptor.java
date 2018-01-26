package cn.itsite.abase.network.http;

import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Author：leguang on 2016/10/9 0009 15:49
 * Email：langmanleguang@qq.com
 */
public class LogInterceptor implements Interceptor {
    private static final String TAG = LogInterceptor.class.getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long t1 = System.nanoTime();

        Buffer buffer = new Buffer();
        if (request.body() != null) {
            request.body().writeTo(buffer);
        }

        Logger.e(String.format("Sending request %s on %s%n%sRequest Params: %s",
                request.url(), chain.connection(), request.headers(), buffer.clone().readUtf8()));
        buffer.close();

        Response response = chain.proceed(request);
        long t2 = System.nanoTime();

        BufferedSource source = response.body().source();
        source.request(Long.MAX_VALUE);
        buffer = source.buffer().clone();

        try {
            JSONObject jsonObject = new JSONObject(buffer.readUtf8());
            JSONObject jsonOther = jsonObject.optJSONObject("other");
            Logger.e(String.format("Received response for %s%nin %.1fms%n%sResponse Json: %s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers(),
                    jsonObject.toString()));

            Logger.json(jsonObject.toString());
            
            String code = jsonOther.optString("code");
//            if ("123".equals(code)) {
//                EventBus.getDefault().post(new EventLogout());
//            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        buffer.close();
        return response;
    }
}
