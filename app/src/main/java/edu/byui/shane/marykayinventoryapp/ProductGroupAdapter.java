package edu.byui.shane.marykayinventoryapp;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * A special group adapter for the display of inventory items
 */
public class ProductGroupAdapter extends BaseExpandableListAdapter {
    private Context context;
    private int layoutResourceGroupId;
    private int layoutResourceChildId;
    //private LayoutInflater mInflater;
    private List<ProductGroup> data;

    /**
     * Constructor
     * @param context  The current context.
     * //@param resource The resource ID for a layout file containing a TextView to use when
     * @param data  The list of ProductInfo's to keep track of
     */
    public ProductGroupAdapter(Context context, int layoutResourceGroupId, int layoutResourceChildId, List<ProductGroup> data) {
        this.context = context;
        this.layoutResourceGroupId = layoutResourceGroupId;
        this.layoutResourceChildId = layoutResourceChildId;
        //mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return data.get(groupPosition).getSize();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).getChildren().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) { // has it been recycled?
            row = ((Activity) context).getLayoutInflater().inflate(layoutResourceGroupId, parent, false);
        }

        ProductGroup group = (ProductGroup) getGroup(groupPosition);
        TextView categoryView = (TextView) row.findViewById(R.id.categoryView);
        TextView nameView = (TextView) row.findViewById(R.id.nameView);
        TextView colorsView = (TextView) row.findViewById(R.id.colorsView);

        categoryView.setText(group.getCategory());
        nameView.setText(group.getName());

        String pluralized = " color";
        if (group.getSize() != 1) {
            pluralized += "s";
        }
        colorsView.setText(group.getSize() + pluralized);
        return row;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) { // has it been recycled?
            row = ((Activity) context).getLayoutInflater().inflate(layoutResourceChildId, parent, false);
        }

        ProductInfo info = (ProductInfo) getChild(groupPosition, childPosition);
        ImageView imageView = (ImageView) row.findViewById(R.id.imageView);
        TextView categoryView = (TextView) row.findViewById(R.id.categoryView);
        TextView nameView = (TextView) row.findViewById(R.id.nameView);
        TextView colorView = (TextView) row.findViewById(R.id.colorsView);
        TextView stockStatusView = (TextView) row.findViewById(R.id.stockStatusView);

        if (info.getImage() != null) {
            imageView.setImageBitmap(info.getImage());
        }
        categoryView.setText(info.getCategory());
        nameView.setText(info.getName());
        colorView.setText(info.getColor());

        String stockStatus = "You have " + Integer.toString(info.getNumberInStock()) +
                " at $" + Float.toString(info.getCost()) + " each";
        stockStatusView.setText(stockStatus);
        return row;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
