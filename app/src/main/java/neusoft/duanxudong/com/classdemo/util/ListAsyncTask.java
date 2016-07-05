package neusoft.duanxudong.com.classdemo.util;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.List;

import neusoft.duanxudong.com.classdemo.model.FileResource;

/**
 * Created by lisheng on 2016/3/21.
 */
public class ListAsyncTask<T> extends AsyncTask {
    private List<T> list;
    private String mUrl;
    public ListAsyncTask(String url){
        this.mUrl=url;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getmUrl() {
        return mUrl;
    }

    @Override
    protected List<T> doInBackground(Object[] params) {
        try {
            URL url=new URL(mUrl);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(3000);
            conn.setDoInput(true);

            InputStream is=conn.getInputStream();

//            byte[] data=StreamTool.read(is);
//            String json=new String(data);

            BufferedReader reader=new BufferedReader(new InputStreamReader(is));
            StringBuffer sb=new StringBuffer();
            String str;
            while((str=reader.readLine())!=null){
                sb.append(str);
            }
            System.out.println("+++"+sb);
            org.json.JSONObject jsonObject=new org.json.JSONObject(sb.toString());

            org.json.JSONArray array=jsonObject.getJSONArray("list");

            for(int i=0;i<array.length();i++){
                org.json.JSONObject object=array.getJSONObject(i);
                FileResource fileResource=new FileResource();
                fileResource.setFid((Integer) object.get("fid"));
                fileResource.setFile_add_time((Timestamp) object.get("file_add_time"));

            }

            setList(list);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
