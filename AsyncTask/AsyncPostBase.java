import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;

import com.turri.tp_login_polshu.Session;

import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.turri.tp_login_polshu.Utiles.CustomLog;
//THIS CLASS IS INTENDED TO BE INHERITED
public class AsyncPostBase extends AsyncTask<Void,Void,String> {

    protected enum RequestMethods{
        POST,
        PUT,
        DELETE
    }

    private final HashMap<String, String> headers = new HashMap<>();
    private  String requestMethod;
    private void setRequesMethod(RequestMethods method){
        requestMethod = method.toString();
    }
    private JSONObject jsonParam = new JSONObject(); //in the child class you fill this value with the body params
    private final String URL;

    public AsyncPostBase(RequestMethods method,String url) {
        setRequesMethod(method);
        this.URL = url;
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
    protected String doInBackground(Void... params) {
        String response = "";
        try {
            URL url = new URL(URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod(requestMethod);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            setHeaders(connection); // add the headers
            if (jsonParam.length() > 0)
                OutputStreamHelper.writeOutPut(connection.getOutputStream(), jsonParam);
            if (connection.getResponseCode() == 200) {
                response = StreamHelper.returnJsonAsString(connection.getInputStream());
            }
            else CustomLog.log("error when connecting to the api");
        } catch (Exception ex) {
            CustomLog.logException(ex);
        }
        CustomLog.log(response);
        return response;

    }

}
