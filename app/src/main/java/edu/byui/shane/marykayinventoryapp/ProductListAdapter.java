package edu.byui.shane.marykayinventoryapp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * The super cool class which interfaces lists and views
 */
public class ProductListAdapter extends ArrayAdapter<ProductInfo> {
    private Context context;
    private int layoutResourceId;
    private List<ProductInfo> data;

    /**
     * Constructor
     *  @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     * @param data  The list of ProductInfo's to keep track of
     */
    public ProductListAdapter(Context context, int resource, List<ProductInfo> data) {
        super(context, resource, data);
        this.context = context;
        this.layoutResourceId = resource;
        this.data = data;
    }

    @Override
    public View getView(int position, View row, ViewGroup parent) {
        if (row == null) { // has it been recycled?
            //row = LayoutInflater.from(context).inflate(layoutResourceId, parent, false);
            row = ((Activity) context).getLayoutInflater().inflate(layoutResourceId, parent, false);
        }

        ProductInfo info = data.get(position);
        ImageView imageView = (ImageView) row.findViewById(R.id.imageView);
        TextView categoryView = (TextView) row.findViewById(R.id.categoryView);
        TextView nameView = (TextView) row.findViewById(R.id.nameView);
        TextView priceView = (TextView) row.findViewById(R.id.priceView);
        TextView colorView = (TextView) row.findViewById(R.id.colorView);
        TextView amountView = (TextView) row.findViewById(R.id.amountView);
        TextView worthView = (TextView) row.findViewById(R.id.worthView);

        if (info.getImage() != null) {
            imageView.setImageBitmap(info.getImage());
        }
        categoryView.setText(info.getGroup());
        nameView.setText(info.getName());
        priceView.setText("$" + Float.toString(info.getCost()));
        colorView.setText(info.getColor());
        amountView.setText(Integer.toString(info.getNumberInStock()) + " at");
        worthView.setText("Asset Worth: $" + Float.toString(info.getInventoryValue()));
        return row;
    }
}