package com.example.jason.online_novel;

import android.text.Html;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.ArrayList;

import static android.text.Html.FROM_HTML_MODE_LEGACY;

public class crawler_novel {
    private String target_url;
    private URL url;
    private Thread th;
    private String te01,te02,te03;
    private StringBuffer novel = new StringBuffer();
    private ArrayList<novel_get_set> novelgetsets = new ArrayList<novel_get_set>();
    private CharSequence charSequence;

    public crawler_novel(String url){
        this.target_url = url;
    }

    public void crawler_novel_start(){

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
                Elements title = doc.select("div[class*=t_fsz]");
                //Log.d("fuck", String.valueOf(title.size()));
                for(int i=1;i<title.size();i++){            //用FOR個別抓取選定的Tag內容
                    //Log.d("fuck", "yoooooooooooouuuuuuuuuuuuuuuuu");
                    //Elements title_select=title.get(i).select("td[class=t_f]");//選擇第i個後選取所有為td的Tag
                    Elements title_select=title.get(i).select("td[class*=t_f]");//選擇第i個後選取所有為td的Tag

                    te01 = title_select.toString();
                    charSequence = Html.fromHtml(te01,FROM_HTML_MODE_LEGACY);
                    novel.append(charSequence);

                }


            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };

    public StringBuffer getNovelContent(){
        return novel;
    }
}
