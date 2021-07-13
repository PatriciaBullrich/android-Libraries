

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
//THIS CLASS IS INTENDED TO BE INHERITED
    public class AsyncPostBase extends AsyncTask<Void,Void,String> {
        protected JSONObject jsonParam = new JSONObject(); //in the child class you fill this value with the body params
        protected String url;
        public AsyncPostBase(String url){
            this.url= url;
        }

        public void setParams(String key,String value) {
            try {
                jsonParam.put(key, value);
            } catch (Exception e) {
                //CustomLog.logException(e);
            }
        }

        public void setParams(String key,int value) {
            try {
                jsonParam.put(key, value);
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

        public void setParams(String key,float value) {
            try {
                jsonParam.put(key, value);
            } catch (Exception e) {
                //CustomLog.logException(e);
            }
        }

        public void setParams(String key,double value) {
            try {
                jsonParam.put(key, value);
            } catch (Exception e) {
                //CustomLog.logException(e);
            }
        }

        public void setParams(String key,boolean value) {
            try {
                jsonParam.put(key, value);
            } catch (Exception e) {
                //CustomLog.logException(e);
            }
        }
        @Override
        protected String doInBackground(Void... params) {
            String response ="";
            try{
                URL url = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
               // if(!Session.currentUser.ApiKey.equals("")) connection.setRequestProperty("ApiKey",Session.currentUser.ApiKey); // optional api key
                OutputStreamWriter outputStream = new OutputStreamWriter(connection.getOutputStream());
                outputStream.write(jsonParam.toString());
                outputStream.flush();
                outputStream.close();

                InputStream cuerpoRespuesta = connection.getInputStream();
                InputStreamReader lector = new InputStreamReader(cuerpoRespuesta,"UTF-8");
                BufferedReader r = new BufferedReader(lector);
                StringBuilder total = new StringBuilder();
                for (String line; (line = r.readLine()) != null; ) {
                    total.append(line);
                }
                response = total.toString();
                //CustomLog.log(connection.getResponseMessage());
            }
            catch (Exception ex){
                //CustomLog.logException(e);
            }
            return response;
        }
    }