package com.example.jason.online_novel;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class novel_view_fragment extends Fragment {

    private TextView tv;
    private Button next,previous;
    private StringBuffer data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_novel_view, container, false);

        init(view);
        return view;
    }

    private void init(View view) {

        Resources res = this.getResources();
        tv=(TextView) view.findViewById(R.id.novel_text_View);    //連結TextView
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());
        tv.setText("test");
    }

    public void setData(StringBuffer novelData){
        data = novelData;
    }
}
