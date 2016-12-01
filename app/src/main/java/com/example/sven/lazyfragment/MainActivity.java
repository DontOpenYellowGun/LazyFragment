package com.example.sven.lazyfragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tab_viewpager)
    SmartTabLayout tabViewpager;
    @Bind(R.id.viewpager)
    ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViewPager();
    }

    private void initViewPager() {
        Bundle oneBundle = new Bundle();
        oneBundle.putString("data", "data1");
        Bundle twoBundle = new Bundle();
        twoBundle.putString("data", "data2");
        Bundle thirdBundle = new Bundle();
        twoBundle.putString("data", "data3");
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("one", OneBlankFragment.class, oneBundle)
                .add("two", TwoBlankFragment.class, twoBundle)
                .add("third", ThirdBlankFragment.class, thirdBundle)
                .create());
        viewpager.setAdapter(adapter);
        viewpager.setOffscreenPageLimit(3);
        tabViewpager.setViewPager(viewpager);
    }
}
