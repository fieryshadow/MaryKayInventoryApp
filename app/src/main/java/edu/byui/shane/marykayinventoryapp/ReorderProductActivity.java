package edu.byui.shane.marykayinventoryapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ReorderProductActivity extends ActionBarActivity {
    private List<ProductInfo> productList;
    private float total;
    private static OrderListAdapter orderListAdapter;
    private static TextView productNameView;
    private static TextView productColorView;
    private static SeekBar numberChanger;
    private static EditText numberToOrderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reorder_product);

        final View anchor = findViewById(R.id.anchor);
        final TextView totalCost = (TextView) findViewById(R.id.orderTotal);
        ListView listView = (ListView) findViewById(R.id.orderList);

        productList = InventoryManager.getInstance().getListing();
        orderListAdapter = new OrderListAdapter(this, R.layout.order_list_item, productList);
        listView.setAdapter(orderListAdapter);
        final LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final ProductInfo info = productList.get(position);
                if (info.getNumberToOrder() == 0) {
                    info.setNumberToOrderToDefault();
                    // do the order update and number manipulation stuff here, this is an example
                    TextView amountView = (TextView) view.findViewById(R.id.amountView);
                    // the following doesn't actually save any values, so we need to put some more data into product info...
                    amountView.setText("You will be getting " + info.getNumberToOrder() + " of these items from MaryKay if you choose to continue...");
                    amountView.setTextColor(Color.parseColor("#ffff0000"));// red
                    totalCost.setText("Total Cost: " + updateTotalCost(info));
                } else {
                    View popupView = inflater.inflate(R.layout.popup_reorder_number_editer, null);
                    productNameView = (TextView) popupView.findViewById(R.id.nameView);
                    productColorView = (TextView) popupView.findViewById(R.id.colorView);
                    numberToOrderView = (EditText) popupView.findViewById(R.id.numberToOrderView);
                    numberChanger = (SeekBar) popupView.findViewById(R.id.seekBar);

                    productNameView.setText(info.getName() + " " + info.getCategory());
                    productColorView.setText(info.getColor());
                    numberToOrderView.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            numberToOrderView.setSelection(numberToOrderView.getText().length());
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            try {
                                numberChanger.setProgress(Integer.parseInt(s.toString()));
                            } catch (NumberFormatException ex) {
                                numberChanger.setProgress(0);
                            }
                        }
                    });
                    numberChanger.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            if (Integer.parseInt(numberToOrderView.getText().toString()) < progress) {
                                numberToOrderView.setText(Integer.toString(progress));
                            }
                        }
                    });
                    numberToOrderView.setText(Integer.toString(info.getNumberToOrder()));
                    final PopupWindow popupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

                    Button updater = (Button) popupView.findViewById(R.id.update);
                    updater.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int amount = Integer.parseInt(numberToOrderView.getText().toString());
                            info.setNumberToOrder(amount);
                            orderListAdapter.notifyDataSetChanged();
                            popupWindow.dismiss();
                        }
                    });
                    Button canceler = (Button) popupView.findViewById(R.id.cancel);
                    canceler.setOnClickListener(new Button.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                        }
                    });
                    Log.i(MyApp.LOGGING_TAG, "Showing dropdown in ReorderProductActivity.onCreate.onItemClick");
                    popupWindow.showAsDropDown(anchor, 0, -111);
                    popupWindow.setFocusable(true);
                    popupWindow.update();
                }
            }
        });
        Log.i(MyApp.LOGGING_TAG, "productList Size = " +productList.size());

    }

    /**
     * updateTotalCost takes the products cost * the number to order and divides it by 2 to give the wholesale cost.
     * @param productInfo
     * @return  returns the summation of the previous total and products cost * number to order divided by 2
     */
    public float updateTotalCost(ProductInfo productInfo){
        total += productInfo.getCost()*productInfo.getNumberToOrder()/2;
        return total;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reorder_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void submit(View view) {
        // This will be passed to the new Receipt Activity to display what was ordered.
        List<ProductInfo> receiptList = new ArrayList<ProductInfo>();
        Log.i(MyApp.LOGGING_TAG, "Created receiptList to be passed to ReceiptActivity");

        // Loop through the productList and call processOrder on any object that toOrder isn't 0.
        for(ProductInfo info : productList) {
            if(info.getNumberToOrder() != 0) {
                receiptList.add(info);
                Log.i(MyApp.LOGGING_TAG, "Added ProductInfo object to receiptList");

                InventoryManager.getInstance().processOrders(info.getProductNumber(), info.getCategory(), info.getName(),
                        info.getColor(), info.getCost(), info.getSection(), info.getNumberToOrder(), "");
                Log.i(MyApp.LOGGING_TAG, "Called processOrders on same object.");
            }
        }
        ArrayList<? extends Parcelable> list = new ArrayList<>(receiptList);
        //Now we just need to pass the receiptList to the ReorderReceiptActivity.
        Intent receiptIntent = new Intent(this, ReorderReceiptActivity.class);

        TextView orderTotal = (TextView) findViewById(R.id.orderTotal);

        //Have to create a bundle to pass to the new activity...
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("productInfoList", list);
        receiptIntent.putExtras(bundle);
        receiptIntent.putExtra("orderTotal", orderTotal.getText().toString());
        startActivity(receiptIntent);
        Log.i(MyApp.LOGGING_TAG, "Started receipt activity.");
    }

    /**
     * This function doesn't store any of the information that was entered on the page and it will return the user to the home page.
     * @param view The current user view
     */
    public void cancel(View view) {
        finish();
    }
}
