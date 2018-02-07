package product.demo.com.productview.services;

import java.util.List;

import product.demo.com.productview.model.ProductType;
import product.demo.com.productview.utility.Constant;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.GET;
import rx.Observable;



public class ProductService {
    private final ProductApi productApi;

    public ProductService() {

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Accept", "application/json");
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constant.PRODUCT_SERVER_URL)
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        productApi = restAdapter.create(ProductApi.class);

    }

    public ProductApi getApi() {
        return productApi;}

    public interface ProductApi {
        @GET("/")
        Observable<List<ProductType>>
        getProduct();}
}
