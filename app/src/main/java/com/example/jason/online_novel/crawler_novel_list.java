package com.example.jason.online_novel;

import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.ArrayList;

public class crawler_novel_list {
    private String target_url;
    private URL url;
    private Thread th;
    private String te01,te02,te03;
    private StringBuffer novel_list = new StringBuffer();
    private ArrayList<novel_get_set> novelgetsets = new ArrayList<novel_get_set>();

    public crawler_novel_list(String url){
        this.target_url = url;
    }

    public void crawler_title_start(){

        th=new Thread(r0);                //執行緒
        th.start();                    //讓執行緒開始工作
        try {
            th.join();
        }catch (InterruptedException e){
            System.out.println(e);
        }
        System.out.println("執行結束");
    }
    private Runnable r0=new Runnable(){
        public void run(){
            try {

                url=new URL(target_url);
                Document doc =  Jsoup.parse(url, 3000);        //連結該網址
                //Elements title = doc.select("div#post_96251512>table#pid96251512>tbody>tr>td>div>div>div>table>tbody>tr");   //抓取為tr且有class屬性的所有Tag
                Elements title = doc.select("div[class*=l_sPic]");
                //Log.d("fuck", String.valueOf(title.size()));
                for(int i=0;i<title.size();i++){            //用FOR個別抓取選定的Tag內容
                    /*Log.d("fuck", "yoooooooooooouuuuuuuuuuuuuuuuu");*/
                    //Elements title_select=title.get(i).select("td[class=t_f]");//選擇第i個後選取所有為td的Tag
                    Elements title_select=title.get(i).select("a");//選擇第i個後選取所有為td的Tag
                    Elements title_select2=title_select.get(0).select("img");
                    String test = title_select.toString();
                    te01=title_select.attr("title");       //只抓取第 0,2,3 Tag的文字
                    te02=title_select.attr("href");
                    te03=title_select2.attr("src");
                    /*Log.d("<a>", test);
                    Log.d("fuck", te02);
                    Log.d("fuck", te01);
                    Log.d("fuck", te03);*/
                    novel_get_set novelgetset = new novel_get_set();
                    novelgetset.setNovel_titles(te01);
                    novelgetset.setImg_scrs(te03);
                    novelgetset.setNovel_web_sites(te02);

                    novelgetsets.add(novelgetset);
                    //Thread.sleep(100);    //避免執行緒跑太快而UI執行續顯示太慢,覆蓋掉te01~03內容所以設個延遲,也可以使用AsyncTask-異步任務
                }


            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };

    public ArrayList<novel_get_set> getNovelgetsets(){
        return novelgetsets;
    }

}
