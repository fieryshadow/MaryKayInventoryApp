package edu.byui.shane.marykayinventoryapp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        // has it been recycled?
        if (row == null) {
            //row = LayoutInflater.from(context).inflate(layoutResourceId, parent, false);
            row = ((Activity) context).getLayoutInflater().inflate(layoutResourceId, parent, false);
        }

        ProductInfo info = data.get(position);
        //((ImageView) row.findViewById(R.id.imageView)).setImageResource(info.getImage());
        ((TextView) row.findViewById(R.id.categoryView)).setText(info.getGroup());
        ((TextView) row.findViewById(R.id.nameView)).setText(" - " + info.getName());
        ((TextView) row.findViewById(R.id.priceView)).setText("Price: $" + Float.toString(info.getCost()));
        ((TextView) row.findViewById(R.id.colorView)).setText(info.getColor());
        ((TextView) row.findViewById(R.id.amountView)).setText("# in Stock: " + Integer.toString(info.getNumberInStock()));
        ((TextView) row.findViewById(R.id.worthView)).setText("Asset Worth: " + Float.toString(info.getInventoryValue()));
        return row;
    }
}