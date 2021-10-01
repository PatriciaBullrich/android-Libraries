//Generic Async task to extend for GET requests

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;


import org.json.JSONObject;

import com.turri.tp_login_polshu.Session;
import com.turri.tp_login_polshu.Utiles.CustomLog;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AsyncGetBase extends AsyncTask<Void, Void ,String> {

    private final String url;
    private JSONObject jsonParam = new JSONObject(); //in the child class you fill this value with the body params
    private  final HashMap<String, String> headers = new HashMap<>();


    public AsyncGetBase(String url) {
        this.url = url;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void StartAsyncTaskInParallel(AsyncTask<Void,Void,String> task) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else
            task.execute();
    }

    protected void addHeaders(String key, String value){
        headers.put(key,value);
    }

    private void setHeaders(HttpURLConnection con){
        if(headers.isEmpty()) return;
        for (Map.Entry<String,String> set: headers.entrySet()) {
            con.setRequestProperty(set.getKey(), set.getValue());
        }
    }




    public void setParams(String key, String value) {
        try {
            jsonParam.put(key, value);
        } catch (Exception e) {
            //CustomLog.logException(e);
        }
    }

    public void setParams(String key, int value) {
        try {
            jsonParam.put(key, value);
        } catch (Exception e) {
            //CustomLog.logException(e);
        }
    }

    public void setParams(String key){
        try {
            jsonParam.put(key, null);
        } catch (Exception e) {
            //CustomLog.logException(e);
        }
    }

    public void setParams(String key, Date value) {
        try {
            jsonParam.put(key, value);
        } catch (Exception e) {
            //CustomLog.logException(e);
        }
    }

    public void setParams(String key, float value) {
        try {
            jsonParam.put(key, value);
        } catch (Exception e) {
            //CustomLog.logException(e);
        }
    }

    public void setParams(String key, double value) {
        try {
            jsonParam.put(key, value);
        } catch (Exception e) {
            //CustomLog.logException(e);
        }
    }

    public void setParams(String key, boolean value) {
        try {
            jsonParam.put(key, value);
        } catch (Exception e) {
            //CustomLog.logException(e);
        }
    }

    @Override
    protected String doInBackground(Void... voids) {
        String response = "";
        try {
            URL request = new URL(url);
            CustomLog.logObject(request);
            HttpURLConnection con = (HttpURLConnection) request.openConnection();
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            if(Session.getCurrentUser()!= null) addHeaders("tokenkey", Session.getCurrentUser().getTokenKey());
            setHeaders(con); // setting the headers
            CustomLog.log("connecting");
            if (con.getResponseCode() == 200) {
                CustomLog.log("Connection OK");
                if (jsonParam.length() >0) OutputStreamHelper.writeOutPut(con.getOutputStream(),jsonParam);
                response = StreamHelper.returnJsonAsString(con.getInputStream());
            } else {
                CustomLog.log("error when connecting to the api");
            }
            con.disconnect();
        } catch (Exception ex) {
            CustomLog.logException(ex);
        }
        return response;
    }
}
