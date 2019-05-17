package com.example.jason.online_novel;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.Arrays;

public class Bestory extends Fragment {
    private String[] nameDatas = new String[]{"魔法異界","仙武異能","言情敘事","時光穿越","科幻太空","靈異軍事","遊戲體育","動漫日清","歷史紀實","名著古典","科普其他","本站原創"};
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
                /*switch (position){
                    case 0:

                        break;
                    case 1:

                        break;
                    default:
                        Toast.makeText(getActivity(),"click view" + position, Toast.LENGTH_SHORT).show();
                }*/
                Toast.makeText(getActivity(),"click view" + position, Toast.LENGTH_SHORT).show();
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
