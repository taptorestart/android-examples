package com.taptorestart.smarttab;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private static ArrayList<Object> viewItemsByRecent = new ArrayList<>();
    private static ArrayList<Object> viewItemsByName = new ArrayList<>();
    private static Activity activity;
    private static ListAdapter listAdapterByRecent = null;
    private static ListAdapter listAdapterByName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        activity = this;
    }

    private void init(){
        mViewPager = (ViewPager) findViewById(R.id.vp_container);
        FragmentPagerItemAdapter fragmentPagerItemAdapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("Recent", FragmentByRecent.class)
                .add("Name", FragmentCourseByName.class)
                .create());
        mViewPager.setAdapter(fragmentPagerItemAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                Log.d("", "position:" + position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.vp_tab);
        viewPagerTab.setViewPager(mViewPager);

        for(int i = 0; i < 20; i++){
            viewItemsByRecent.add("Recent " + (i + 1));
            viewItemsByName.add("Name " + (i + 1));
        }
        listAdapterByRecent = new ListAdapter(activity, viewItemsByRecent);
        listAdapterByName = new ListAdapter(activity, viewItemsByName);
    }


    public static class FragmentByRecent extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_list, container, false);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(activity);
            RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.recyc_list);
            if (mRecyclerView != null) {
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(listAdapterByRecent);
            }
            return view;
        }
        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
        }
    }

    public static class FragmentCourseByName extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_list, container, false);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(activity);
            RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.recyc_list);
            if (mRecyclerView != null) {
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(listAdapterByName);
            }
            return view;
        }
        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
        }
    }
}