package product.demo.com.productview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.TreeSet;

import product.demo.com.productview.R;
import product.demo.com.productview.model.Product;
import product.demo.com.productview.utility.Constant;

public class ProductListAdapter extends BaseAdapter {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;

    private final ArrayList<Product> mData = new ArrayList<>();
    private final TreeSet<Integer> sectionHeader = new TreeSet<>();

    private final LayoutInflater mInflater;
    private final Context context;

    public ProductListAdapter(Context context) {
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    public void addItem(final Product item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void addSectionHeaderItem(final Product item) {
        mData.add(item);
        sectionHeader.add(mData.size() - 1);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return sectionHeader.contains(position) ? TYPE_SEPARATOR : TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Product getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        int rowType = getItemViewType(position);

        if (convertView == null) {
            holder = new ViewHolder();
            switch (rowType) {
                case TYPE_ITEM:
                    convertView = mInflater.inflate(R.layout.list_row, null);
                    holder.textView = convertView.findViewById(R.id.title);
                    holder.imageView = convertView.findViewById(R.id.thumbnail);
                    break;
                case TYPE_SEPARATOR:
                    convertView = mInflater.inflate(R.layout.list_row, null);
                    holder.textView = convertView.findViewById(R.id.title);
                    holder.textView.setClickable(false);
                    break;
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (rowType == 0) {
            holder.imageView.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(Constant.PRODUCT_SERVER_URL + mData.get(position).getUrl())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.imagenotfound)
                    .into(holder.imageView);
        }
        holder.textView.setText(mData.get(position).getName());
        return convertView;
    }

    public static class ViewHolder {
        public TextView textView;
        public ImageView imageView;
    }

}


