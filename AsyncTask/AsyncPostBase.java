import android.os.AsyncTask;


import com.example.ProyectoFinal.loangrounds.Utilidades.CustomLog;
import com.example.ProyectoFinal.loangrounds.Utilidades.OutputStreamHelper;
import com.example.ProyectoFinal.loangrounds.Utilidades.Session;
import com.example.ProyectoFinal.loangrounds.Utilidades.StreamHelper;

import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

//THIS CLASS IS INTENDED TO BE INHERITED
public class AsyncPostBase extends AsyncTask<Void,Void,String> {
    protected enum RequestMethods{
        POST,
        PUT,
        DELETE
    }
    private  String requestMethod;
    private void setRequesMethod(RequestMethods method){
        requestMethod = method.toString();
    }
    protected JSONObject jsonParam = new JSONObject(); //in the child class you fill this value with the body params
    protected String URL;

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
            if (Session.currentUser != null)
                connection.setRequestProperty("ApiKey", Session.currentUser.getApiKey()); // optional api key
            if (jsonParam.length() > 0)
                OutputStreamHelper.writeOutPut(connection.getOutputStream(), jsonParam);

            /*OutputStreamWriter outputStream = new OutputStreamWriter(connection.getOutputStream());
            outputStream.write(jsonParam.toString());
            outputStream.flush();
            outputStream.close();*/
            if (connection.getResponseCode() == 200) {
                response = StreamHelper.returnJsonAsString(connection.getInputStream());
                /*InputStream cuerpoRespuesta = connection.getInputStream();
                InputStreamReader lector = new InputStreamReader(cuerpoRespuesta,"UTF-8");
                BufferedReader r = new BufferedReader(lector);
                StringBuilder total = new StringBuilder();
                for (String line; (line = r.readLine()) != null; ) {
                    total.append(line);
                }
                response = total.toString();
                CustomLog.log(connection.getResponseMessage());
            }*/
            }
            else CustomLog.log("error when connecting to the api");
        } catch (Exception ex) {
            CustomLog.logException(ex);
        }
        CustomLog.log(response);
        return response;

    }



}

