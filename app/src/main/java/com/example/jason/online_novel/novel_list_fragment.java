package com.example.jason.online_novel;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;

public class novel_list_fragment extends Fragment {

    private ArrayList<novel_get_set> ngs;
    private String[] titleDatas;
    private RecyclerView recyclerView;
    private CommonAdapter<String> adapter;
    private Bitmap[] bitmap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        titleDatas = new String[ngs.size()];
        bitmap = new Bitmap[ngs.size()];
        for(int i=0;i<ngs.size();i++){

            titleDatas[i] = ngs.get(i).getNovel_titles();
        }
        init(view);
        return view;
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        Resources res = this.getResources();


        adapter = new CommonAdapter<String>(getActivity(),R.layout.novel_list_item, Arrays.asList(titleDatas)) {
            @Override
            protected void convert(ViewHolder holder, String s, final int position) {

                holder.setText(R.id.novel_list_item_title, s);
                //holder.setImageBitmap(R.id.novel_list_cover,GetImage(ngs.get(position).getImg_scrs()));
                if(ngs.get(position).getImg_scrs().equals("static/image/common/nothumb.jpg")){
                    holder.setImageResource(R.id.novel_list_cover,R.mipmap.face);
                }else{

                     Runnable r0 = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            bitmap[position] = GetImage(ngs.get(position).getImg_scrs());

                        }
                    });
                    ((Thread) r0).start();
                     try{
                         ((Thread) r0).join();
                     }catch (Exception e){
                         System.out.println(e);
                     }
                    holder.setImageBitmap(R.id.novel_list_cover,bitmap[position]);
                    /*try{
                        URL url = new URL(ngs.get(position).getImg_scrs());
                        System.out.println(url.toString());
                        holder.setImageBitmap(R.id.novel_list_cover,BitmapFactory.decodeStream(url.openStream()));
                    }catch (Exception e){

                    }*/

                }

                //holder.setBackgroundColor(R.id.weblist_item_background_top,0xFFE91E1E);
            }
        };
        adapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

                Intent intent = new Intent();
                intent.setClass(getActivity(), novel_view.class);
                Bundle bundle = new Bundle();
                bundle.putString("novel_website",ngs.get(position).getNovel_web_sites());
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

    public void setData(ArrayList<novel_get_set> novel){
        ngs = novel;
        System.out.println("儲存資料");
    }
    public Bitmap GetImage(String url){  //秀出網路上圖片的function
        Bitmap bitmap;
        try {
            URL url2 = new URL(url);
            URLConnection conn = url2.openConnection();
            HttpURLConnection httpConn = (HttpURLConnection)conn;
            httpConn.setRequestMethod("GET");
            httpConn.connect();

            if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = httpConn.getInputStream();

                bitmap = BitmapFactory.decodeStream(inputStream);
                inputStream.close();

                return bitmap;

            }
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
