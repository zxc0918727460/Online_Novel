package com.example.jason.online_novel;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.view.View;

import java.util.ArrayList;

public class novel_view extends AppCompatActivity {

    private StringBuffer novel = new StringBuffer();
    private Bundle bundle;
    private Intent intent;
    private TextView text;
    private String novel_id,novel_url;
    private String[] url_split;
    private int page = 1;
    private AppCompatButton previousButton,nextButton;
    private AppCompatImageButton set;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novel_view);

        previousButton = (AppCompatButton)findViewById(R.id.previousButton);
        set = (AppCompatImageButton)findViewById(R.id.set);
        nextButton = (AppCompatButton)findViewById(R.id.nextButton);
        scrollView = (ScrollView)findViewById(R.id.scrollView);
        text = this.findViewById(R.id.novel_text_View);
        intent = this.getIntent();
        bundle = intent.getExtras();
        novel_url = bundle.getString("novel_website");
        url_split = novel_url.split("-");
        novel_id = url_split[1];
        System.out.println(novel_id);
        init(moveToPage(novel_id,page));

        if(page == 1){
            previousButton.setEnabled(false);
        }else{
            previousButton.setEnabled(true);
        }
        previousButton.setOnClickListener(new AppCompatButton.OnClickListener(){

            @Override

            public void onClick(View v) {

                // TODO Auto-generated method stub


                page--;
                if(page == 1){
                    previousButton.setEnabled(false);
                }
                scrollView.smoothScrollTo(0,20);
                scrollView.setFocusable(true);
                text.setText("sex"+page);
                movepage(page);

            }

        });

        set.setOnClickListener(new AppCompatImageButton.OnClickListener(){

            @Override

            public void onClick(View v) {

                // TODO Auto-generated method stub

                text.setText("fuck "+page);

            }

        });

        nextButton.setOnClickListener(new AppCompatButton.OnClickListener(){

            @Override

            public void onClick(View v) {

                // TODO Auto-generated method stub
                page++;
                scrollView.smoothScrollTo(0,20);
                scrollView.setFocusable(true);
                text.setText("shit"+page);

                movepage(page);
                previousButton.setEnabled(true);
            }

        });
    }

    private void init(String url) {


        crawler_novel crawler = new crawler_novel(url);
        crawler.crawler_novel_start();
        novel= crawler.getNovelContent();
        text.setText(novel);
    }

    private String moveToPage(String id,int page){
        return "https://ck101.com/forum.php?mod=viewthread&tid="+id+"&extra=page%3D1&page="+page;
    }

    private void movepage(int page) {

        String url = "https://ck101.com/forum.php?mod=viewthread&tid="+novel_id+"&extra=page%3D1&page="+page;
        novel = new StringBuffer();
        crawler_novel crawler = new crawler_novel(url);
        crawler.crawler_novel_start();
        novel= crawler.getNovelContent();
        text.setText(novel);
    }
}
