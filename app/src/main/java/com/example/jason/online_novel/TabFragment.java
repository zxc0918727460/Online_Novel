package com.example.jason.online_novel;

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

public class TabFragment extends Fragment {
    private String[] nameDatas = new String[]{"智能","红润","日系","自然","艺术黑白","甜美","蜜粉","清新","夏日阳光","唯美","蜜粉",};
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
        adapter = new CommonAdapter<String>(getActivity(),R.layout.item, Arrays.asList(nameDatas)) {
            @Override
            protected void convert(ViewHolder holder, String s, final int position) {
                holder.setText(R.id.tv_title, s);
                holder.setText(R.id.textView, "世界偉人，民族的救星:韓國瑜高雄發大財高雄發大財高雄發大財高雄發大財高雄發大財高雄發大財高雄發大財");
            }
        };
        adapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Toast.makeText(getActivity(),"click view" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
