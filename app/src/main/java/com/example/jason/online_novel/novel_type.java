package com.example.jason.online_novel;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;



import java.util.ArrayList;

public class novel_type extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private TabLayout.Tab tabOne;
    private TabLayout.Tab tabTwo;
    private FmPagerAdapter pagerAdapter;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private String[] titles = new String[]{"分類推薦", "搜尋"};
    private Bundle bundle;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        intent = this.getIntent();
        bundle = intent.getExtras();
        init(bundle.getInt("novel_webb_list"));
    }

    private void init(int position) {

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        switch (position){
            case 0:
                fragments.add(new Bestory());
                tabLayout.addTab(tabLayout.newTab());
                fragments.add(new TabFragment());
                tabLayout.addTab(tabLayout.newTab());
                break;
            case 1:
                fragments.add(new ck101_long_novel_fragment());
                tabLayout.addTab(tabLayout.newTab());
                fragments.add(new TabFragment());
                tabLayout.addTab(tabLayout.newTab());

                break;
            default:
                fragments.add(new ck101_long_novel_fragment());
                tabLayout.addTab(tabLayout.newTab());
                fragments.add(new TabFragment());
                tabLayout.addTab(tabLayout.newTab());
        }

        tabLayout.setupWithViewPager(viewPager,false);
        pagerAdapter = new FmPagerAdapter(fragments,getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        for(int i=0;i<titles.length;i++){
            tabLayout.getTabAt(i).setText(titles[i]);
        }
    }
}