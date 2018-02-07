package product.demo.com.productview;


import android.widget.ListView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import butterknife.InjectView;
import product.demo.com.productview.view.ProductListViewActivity;

import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)

public class ProductListViewActivityTest {
    private ProductListViewActivity activity;
    @InjectView(R.id.listViewProduct)
    ListView mListViewProduct;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(ProductListViewActivity.class)
                              .create()
                              .resume()
                              .get();
    }

    @Test
    public void shouldNotBeNull() throws Exception {
        assertNotNull(activity);
    }


    @Test
    public void shouldNotBeNullListView() throws Exception {
        assertNotNull(activity.findViewById(R.id.listViewProduct));
    }
}