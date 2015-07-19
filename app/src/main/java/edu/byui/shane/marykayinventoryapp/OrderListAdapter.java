package edu.byui.shane.marykayinventoryapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * A super cool class which interfaces product lists and inventory views
 */
public class OrderListAdapter extends ArrayAdapter<ProductInfo> {
    private Context context;
    private int layoutResourceId;
    private List<ProductInfo> data;

    /**
     * Constructor
     *  @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     * @param data  The list of ProductInfo's to keep track of
     */
    public OrderListAdapter(Context context, int resource, List<ProductInfo> data) {
        super(context, resource, data);
        this.context = context;
        this.layoutResourceId = resource;
        this.data = data;
    }

    @Override
    public View getView(int position, View row, ViewGroup parent) {
        if (row == null) { // has it been recycled?
            row = ((Activity) context).getLayoutInflater().inflate(layoutResourceId, parent, false);
        }

        // these should be whatever are in the corresponding xml file
        ProductInfo info = data.get(position);
        ImageView imageView = (ImageView) row.findViewById(R.id.imageView);
        TextView categoryView = (TextView) row.findViewById(R.id.categoryView);
        TextView nameView = (TextView) row.findViewById(R.id.nameView);
        TextView colorView = (TextView) row.findViewById(R.id.colorView);
        TextView amountView = (TextView) row.findViewById(R.id.amountView);

        if (info.getImage() != null) {
            imageView.setImageBitmap(info.getImage());
        }
        categoryView.setText(info.getCategory());
        nameView.setText(info.getName());
        colorView.setText(info.getColor());

        if(info.getNumberToOrder() != 0 && info.getHighestNumberInInventory() != 0) {
            amountView.setText("You will be getting " + info.getNumberToOrder() + " of these items from MaryKay if you choose to continue...");
            amountView.setTextColor(Color.parseColor("#ffff0000")); // red
        } else {
            amountView.setText("You haven't ordered any yet");
            amountView.setTextColor(Color.parseColor("#ff00e812"));//green
        }
        return row;
    }
}