package com.example.jason.online_novel;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.Arrays;

public class weblist extends Fragment {
    private String[] nameDatas = new String[]{"精品文學網","卡提諾長篇小說","卡提諾短篇小說","卡提諾原創言情","卡提諾出版言情","卡提諾耽美小說","飄天文學","黃金屋中文網"};
    private RecyclerView recyclerView;
    private CommonAdapter<String> adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        Resources res = this.getResources();


        adapter = new CommonAdapter<String>(getActivity(),R.layout.weblist_item, Arrays.asList(nameDatas)) {
            @Override
            protected void convert(ViewHolder holder, String s, final int position) {
                holder.setText(R.id.weblist_item_title, s);
                //holder.setBackgroundColor(R.id.weblist_item_background_top,0xFFE91E1E);
            }
        };
        adapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

                Intent intent = new Intent();
                intent.setClass(getActivity(), novel_type.class);
                Bundle bundle = new Bundle();
                bundle.putInt("novel_webb_list",position);
                intent.putExtras(bundle);
                startActivity(intent);

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                //holder.itemView.setBackgroundColor(0xFF03E0F4);
                return false;
            }

        });

        recyclerView.setAdapter(adapter);
    }
}
