package product.demo.com.productview;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.UiThreadTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import product.demo.com.productview.adapter.ProductListAdapter;
import product.demo.com.productview.presenters.Presenter;
import product.demo.com.productview.services.ProductService;
import product.demo.com.productview.utility.InternetConnection;
import product.demo.com.productview.view.ProductListViewActivity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {


    Presenter presenter;
    ProductService productService;
    @Rule
    public final ActivityTestRule<ProductListViewActivity> rule = new ActivityTestRule<>(ProductListViewActivity.class);

    @Rule
    public UiThreadTestRule uiThreadTestRule = new UiThreadTestRule();

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("product.demo.com.productview", appContext.getPackageName());
    }


    @UiThreadTest
    public void testInternetConnectivity() {
        final ProductListViewActivity activity = rule.getActivity();
        assertFalse(InternetConnection.checkConnection(activity));
    }

    @Test
    public void testService() {
        try {
            java.lang.reflect.Field listAdapter =
                    ProductListViewActivity.class.getDeclaredField("productListAdapter");
            listAdapter.setAccessible(true);

            final ProductListViewActivity activity = rule.getActivity();
            ProductListAdapter productListAdapter = new ProductListAdapter(activity);
            listAdapter.set(activity, productListAdapter);
            productService = new ProductService();
            presenter = new Presenter(activity, productService);
            presenter.loadProductItem();
            Assert.assertEquals(productListAdapter.getCount(), 25);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void ensureListViewIsPresent() throws Exception {
        ProductListViewActivity activity = rule.getActivity();
        View viewById = activity.findViewById(R.id.listViewProduct);
        Assert.assertNotNull(viewById);
        ListView listView = (ListView) viewById;
        ListAdapter adapter = listView.getAdapter();
        Assert.assertEquals(adapter.getCount(), 5);
    }
}
