
import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;


import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

//THIS CLASS IS INTENDED TO BE INHERITED
public class AsyncTaskBase extends AsyncTask<Void,Void,String> {
    private final TaskListener taskListener;

    protected enum RequestMethods{
        GET,
        POST,
        PUT,
        DELETE
    }

    private final HashMap<String, String> headers = new HashMap<>();
    private  RequestMethods requestMethod;
    private void setRequesMethod(RequestMethods method){
        requestMethod = method;
    }
    private final JSONObject jsonParam = new JSONObject(); //in the child class you fill this value with the body params
    private final String URL;

    public AsyncTaskBase(RequestMethods method, String url,TaskListener listener)  {
        setRequesMethod(method);
        taskListener = listener;
        this.URL = url;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void StartAsyncTaskInParallel(AsyncTask<Void,Void,String> task) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else
            task.execute();
    }


    public void addHeaders(String key, String value){
        headers.put(key,value);
    }

    private void setHeaders(HttpURLConnection con){
        if(headers.isEmpty()) return;
        for (Map.Entry<String,String> set: headers.entrySet()) {
            con.setRequestProperty(set.getKey(), set.getValue());
        }
    }

    public<T>void setParams(String key, T value) {
        try {
            jsonParam.put(key, value);
        } catch (Exception e) {
            //CustomLog.logException(e);
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        addHeaders("Content-Type", "application/json");
        addHeaders("Accept", "application/json");
        taskListener.onTaskStarted();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        taskListener.onTaskFinished();
    }

    @Override
    protected String doInBackground(Void... params) {
        String response = "";
        try {
            URL url = new URL(URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
             if (!requestMethod.equals(RequestMethods.GET))connection.setDoOutput(true);
            connection.setRequestMethod(requestMethod.toString());
            setHeaders(connection); // add the headers
            if (jsonParam.length() > 0)
                OutputStreamHelper.writeOutPut(connection.getOutputStream(), jsonParam);
            if (connection.getResponseCode() == 200) {
                response = StreamHelper.returnJsonAsString(connection.getInputStream());
            }
            else taskListener.onBadRequest();
        } catch (Exception ex) {
            taskListener.onError(ex);
        }
        return response;
    }
}
