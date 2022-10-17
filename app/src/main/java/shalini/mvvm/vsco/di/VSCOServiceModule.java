package shalini.mvvm.vsco.di;

import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import shalini.mvvm.vsco.data.VSCOService;

/**
 * This class uses hilt for dependency injection. The retrofit class is initialized
 * this way. Make unit testing easier.
 */

@Module
@InstallIn(ViewModelComponent.class)
public class VSCOServiceModule {

    private final static String BASE_URL = "https://pixabay.com/api/?key=30604832-d383f2bd104bf4a45a3dd004f&q=yellow+flowers&image_type=photo/";
    private static final String API_KEY = "29061306-9cbb474b220e9a07b0efe38a4";

    /**
     * Create in instance of retrofit. Ideally this would be used in conjunction with
     * Interceptors to use auth token. I do have an interceptor with the key in this case.
     *
     * @return
     */
    @Provides
    public static VSCOService provideVSCOService() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getHttpClient())
                .build();
        return retrofit.create(VSCOService.class);
    }

    /**
     * This is the instance of the interceptor. It injects the api key to every call
     *
     * @return OkHttpClient
     */
    public static OkHttpClient getHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Interceptor.Chain chain) throws IOException {
                        Request request = chain.request();
                        HttpUrl url = request.url().newBuilder().addQueryParameter("key", API_KEY).build();
                        request = request.newBuilder().url(url).build();
                        return chain.proceed(request);
                    }
                }).build();

        return client;
    }
}
