package edu.byui.shane.marykayinventoryapp;

import java.util.List;
import java.util.Locale;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
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

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
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


    /** Manager of the different list of products */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            if (position == 1) {
                return SectionTwoFragment.newInstance(position + 1);
            }
            return SectionOneFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
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

    /** A fragment showing the inventory contents */
    public static class SectionOneFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public static SectionOneFragment newInstance(int sectionNumber) {
            SectionOneFragment fragment = new SectionOneFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public SectionOneFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_inventory_list, container, false);

            ListView listView = (ListView) view.findViewById(R.id.listView);
            List<ProductInfo> list = InventoryManager.getInstance().getSectionListing(InventoryManager.section2);
            ProductListAdapter products = new ProductListAdapter(getActivity(), R.layout.inventory_list_item, list);
            listView.setAdapter(products);

            return view;
        }
    }

    /** A fragment showing the MaryKay stock */
    public static class SectionTwoFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public static SectionTwoFragment newInstance(int sectionNumber) {
            SectionTwoFragment fragment = new SectionTwoFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public SectionTwoFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_inventory_list, container, false);

            ListView listView = (ListView) view.findViewById(R.id.listView);
            List<ProductInfo> list = InventoryManager.getInstance().getSectionListing(InventoryManager.section2);
            ProductListAdapter products = new ProductListAdapter(getActivity(), R.layout.inventory_list_item, list);
            listView.setAdapter(products);

            return view;
        }
    }


    public void sortList(int sortKind) {

    }
}
