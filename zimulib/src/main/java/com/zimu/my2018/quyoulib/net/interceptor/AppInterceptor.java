package com.zimu.my2018.quyoulib.net.interceptor;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/20
 */

import android.os.Build;
import android.text.TextUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Locale;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * 具体用法：
 * BasicParamsInterceptor basicParamsInterceptor =
 * new BasicParamsInterceptor.Builder()
 * .addHeaderParam("User-Agent", "xxxxxxxxxxx")
 * .addHeaderParam("Accept", "application/json")
 * .addParam("_t", time)
 * .addParam("_tsp", tsp)
 * .build();
 * <p>
 * OkHttpClient.Builder builder = new OkHttpClient.Builder();
 * <p>
 * builder.addInterceptor(basicParamsInterceptor);
 * <p>
 * OkHttpClient okHttpClient = builder.build();
 * mRetrofit = new Retrofit.Builder()
 * .baseUrl(ApiStores.BASE_URL)
 * .addConverterFactory(GsonConverterFactory.create())
 * .client(okHttpClient)
 * .build();
 */
public class AppInterceptor implements Interceptor {

    int version;

    public AppInterceptor(int version) {
        this.version = version;
    }

    private static String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null) {
                copy.writeTo(buffer);
            } else {
                return "";
            }
            return buffer.readUtf8();
        } catch (final IOException e) {
            e.printStackTrace();
            return "did not work";
        }
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        HttpUrl.Builder builder = request.url()
                .newBuilder()
                .setEncodedQueryParameter("Version", "" + version)
                .setEncodedQueryParameter("App-OS", "Android");

        Request.Builder requestBuilder = request.newBuilder();

        requestBuilder.method(request.method(), request.body())
                .url(builder.build());
        // process post body inject

        if (canInjectIntoBody(request)) {
            FormBody.Builder formBodyBuilder = new FormBody.Builder();

            RequestBody formBody = formBodyBuilder.build();
            String postBodyString = bodyToString(request.body());
            postBodyString += ((postBodyString.length() > 0) ? "&" : "") + bodyToString(formBody);
            requestBuilder.post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), postBodyString));
        }
        requestBuilder.addHeader("User-Agent", getUserAgent());
        requestBuilder.addHeader("App-Version", "" + version);
        requestBuilder.addHeader("App-OS", "Android");
        requestBuilder.addHeader("appkey", "2");
        requestBuilder.addHeader("requestSource", "2");
        request = requestBuilder.build();
        return chain.proceed(request);
    }


    private boolean canInjectIntoBody(Request request) {
        if (request == null) {
            return false;
        }
        if (!TextUtils.equals(request.method(), "POST")) {
            return false;
        }
        RequestBody body = request.body();
        if (body == null) {
            return false;
        }
        MediaType mediaType = body.contentType();
        if (mediaType == null) {
            return false;
        }
        return TextUtils.equals(mediaType.subtype(), "x-www-form-urlencoded");
    }

    private String getUserAgent() {

        String webUserAgent = null;
        try {
            Class<?> sysResCls = Class.forName("com.android.internal.R$string");
            Field webUserAgentField = sysResCls.getDeclaredField("web_user_agent");
        } catch (Exception e) {
            // We have nothing to do
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(webUserAgent)) {
            webUserAgent = "Mozilla/5.0 (Linux; U; Android %s) AppleWebKit/533.1 (KHTML, like Gecko) Version/5.0 %sSafari/533.1";
        }

        Locale locale = Locale.getDefault();
        StringBuffer buffer = new StringBuffer();
        // Add version
        final String version = Build.VERSION.RELEASE;
        if (version.length() > 0) {
            buffer.append(version);
        } else {
            // default to "1.0"
            buffer.append("1.0");
        }
        buffer.append("; ");
        final String language = locale.getLanguage();
        if (language != null) {
            buffer.append(language.toLowerCase(locale));
            final String country = locale.getCountry();
            if (!TextUtils.isEmpty(country)) {
                buffer.append("-");
                buffer.append(country.toLowerCase(locale));
            }
        } else {
            // default to "en"
            buffer.append("en");
        }
        // add the model for the release build
        if ("REL".equals(Build.VERSION.CODENAME)) {
            final String model = Build.MODEL;
            if (model.length() > 0) {
                buffer.append("; ");
                buffer.append(model);
            }
        }
        final String id = Build.ID;
        if (id.length() > 0) {
            buffer.append(" Build/");
            buffer.append(id);
        }
        return String.format(webUserAgent, buffer, "Mobile ");

    }
}
