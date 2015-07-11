package edu.byui.shane.marykayinventoryapp;

import java.util.List;
import java.util.Locale;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class InventoryListActivity extends ActionBarActivity {
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_list);

        Log.i(MainActivity.TAG_FOR_APP, "Loading fragments in InventoryListActivity.onCreate");

        // Create the adapter that will return a fragment for each of the two primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        Log.i(MainActivity.TAG_FOR_APP, "Finished loading page in InventoryListActivity.onCreate");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inventory_list, menu);
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


    /** Manager of the different lists of products */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        /** Adhering to the fragment API */
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            return SectionFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 2; // Show 2 total pages.
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1);
                case 1:
                    return getString(R.string.title_section2);
            }
            return null;
        }
    }

    /** A fragment showing a section of the inventory contents */
    public static class SectionFragment extends Fragment {
        private static final String ARG_SECTION = "which section";

        /**
         * Creates the appropriate fragment based on which page the user is looking at
         * @param sectionNumber The page number
         * @return Returns the fragment
         */
        public static SectionFragment newInstance(int sectionNumber) {
            SectionFragment fragment = new SectionFragment();
            Bundle args = new Bundle();
            if (sectionNumber == 1) {
                args.putString(ARG_SECTION, InventoryManager.section1);
            } else {
                args.putString(ARG_SECTION, InventoryManager.section2);
            }
            fragment.setArguments(args);
            return fragment;
        }

        /** Adhering to the fragment API */
        public SectionFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Log.v(MainActivity.TAG_FOR_APP, "Inflating layout in SectionFragment.onCreateView");
            View fragmentView = inflater.inflate(R.layout.fragment_inventory_list, container, false);
            Log.v(MainActivity.TAG_FOR_APP, "Retrieving section in SectionFragment.onCreateView");
            String section = getArguments().getString(ARG_SECTION);

            Log.v(MainActivity.TAG_FOR_APP, "Finding the list in SectionFragment.onCreateView");
            ListView listView = (ListView) fragmentView.findViewById(R.id.fragmentListView);
            List<ProductInfo> list = InventoryManager.getInstance().getSectionListing(section);
            Log.v(MainActivity.TAG_FOR_APP, "Displaying the list view in SectionFragment.onCreateView");
            ProductListAdapter productsView = new ProductListAdapter(getActivity(), R.layout.inventory_list_item, list);
            listView.setAdapter(productsView);

            Log.v(MainActivity.TAG_FOR_APP, "Finished creation in SectionFragment.onCreateView");
            return fragmentView;
        }
    }
}
