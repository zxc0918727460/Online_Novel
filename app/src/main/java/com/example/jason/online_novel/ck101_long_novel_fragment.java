package com.example.jason.online_novel;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.Arrays;

public class ck101_long_novel_fragment extends Fragment {
    private String[] nameDatas = new String[]{"全部分類","玄幻奇幻","武俠仙俠","都市言情","歷史軍事","遊戲競技","科幻靈異","其他小說","全本小說"};
    private String[] web_address = new String[]{"https://ck101.com/forum.php?mod=forumdisplay&fid=237",
            "https://ck101.com/forum.php?mod=forumdisplay&fid=237&filter=typeid&typeid=133",
            "https://ck101.com/forum.php?mod=forumdisplay&fid=237&filter=typeid&typeid=134",
            "https://ck101.com/forum.php?mod=forumdisplay&fid=237&filter=typeid&typeid=135",
            "https://ck101.com/forum.php?mod=forumdisplay&fid=237&filter=typeid&typeid=136",
            "https://ck101.com/forum.php?mod=forumdisplay&fid=237&filter=typeid&typeid=137",
            "https://ck101.com/forum.php?mod=forumdisplay&fid=237&filter=typeid&typeid=138",
            "https://ck101.com/forum.php?mod=forumdisplay&fid=237&filter=typeid&typeid=139",
            "https://ck101.com/forum.php?mod=forumdisplay&fid=237"};
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
                intent.setClass(getActivity(), novel_list.class);
                Bundle bundle = new Bundle();
                bundle.putString("novel_website",web_address[position]);
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
