package cn.itsite.abase.network.http;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @version v0.0.0
 * @Author leguang
 * @E-mail langmanleguang@qq.com
 * @Blog https://github.com/leguang
 * @Time 2018/3/8 0008 11:24
 * @Description
 */
public class HttpHeaderInterceptor implements Interceptor {



    @Override
    public Response intercept(Chain chain) throws IOException {
        //  配置请求头
        Request request = chain.request().newBuilder()
                .header("token", "appId")
                .header("fromPoint", "123")
                .header("Content-Type", "application/json")
                .build();

//        HttpUrl httpUrl = request.url()
//                .newBuilder()
//                .addEncodedQueryParameter("version", "VersionName")
//                .build();
//        request = request.newBuilder().url(httpUrl).build();

        return chain.proceed(request);
    }
}

