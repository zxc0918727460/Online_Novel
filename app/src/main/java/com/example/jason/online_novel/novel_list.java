package com.example.jason.online_novel;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class novel_list extends AppCompatActivity {

    private ViewPager viewPager;
    private crawler_novel_list crawler;
    private FmPagerAdapter pagerAdapter;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private String[] titles = new String[]{"分類推薦", "搜尋"};
    private Bundle bundle;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novel_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        intent = this.getIntent();
        bundle = intent.getExtras();
        init(bundle.getString("novel_website"));
    }

    private void init(String url) {

        crawler_novel_list crawler = new crawler_novel_list(url);
        crawler.crawler_title_start();
        ArrayList<novel_get_set> data= crawler.getNovelgetsets();
        System.out.println("執行123");
        viewPager = (ViewPager) findViewById(R.id.novel_list_viewpager);
        novel_list_fragment item = new novel_list_fragment();
        item.setData(data);
        fragments.add(item);



        pagerAdapter = new FmPagerAdapter(fragments,getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

    }
}
