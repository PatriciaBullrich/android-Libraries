//Generic Async task to extend for GET requests

package com.example.ProyectoFinal.loangrounds.AsyncTask;

import android.os.AsyncTask;

import com.example.ProyectoFinal.loangrounds.Utilidades.CustomLog;
import com.example.ProyectoFinal.loangrounds.Utilidades.OutputStreamHelper;
import com.example.ProyectoFinal.loangrounds.Utilidades.StreamHelper;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class AsyncTaskBase extends AsyncTask<Void, Void ,String> {

    private String url;
    protected JSONObject jsonParam = new JSONObject(); //in the child class you fill this value with the body params

    public AsyncTaskBase(String url) {
        this.url = url;
    }
    
     @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void StartAsyncTaskInParallel(AsyncTask<Void, Void, String> task) {
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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
           // you can add headers with  con.setRequestProperty("authorization", "123");
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
