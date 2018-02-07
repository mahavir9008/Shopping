package product.demo.com.productview.presenters;

import java.util.List;

import product.demo.com.productview.model.ProductType;
import product.demo.com.productview.services.ProductService;
import product.demo.com.productview.view.ProductListViewActivity;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;



public class Presenter {
    private final ProductListViewActivity productListViewActivity;
    private final ProductService productService;

    public Presenter(ProductListViewActivity productListViewActivity, ProductService productService){
        this.productListViewActivity = productListViewActivity;
        this.productService = productService;
    }

    public void loadProductItem(){
        productService.getApi()
                      .getProduct()
                      .subscribeOn(Schedulers.newThread())
                      .observeOn(AndroidSchedulers.mainThread())
                      .subscribe(new Observer<List<ProductType>>() {
                          @Override
                          public void onCompleted() {}

                          @Override
                          public void onError(Throwable e) {}

                          @Override
                          public void onNext(List<ProductType> productTypes) {
                              productListViewActivity.displayProductItem(productTypes);
                          }
                      });
    }

}
