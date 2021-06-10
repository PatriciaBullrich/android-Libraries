import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public final class StreamHelper {
    //Receives a String URL and returns the json reponse into a string
    //Used for Async task with apis
    public static String returnJsonAsString(String url){
        String response ="";
        try{
            URL request = new URL(url);
            CustomLog.logObject(request);
            HttpURLConnection con = (HttpURLConnection) request.openConnection();
            CustomLog.log("connecting");
            if (con.getResponseCode() == 200) {
                CustomLog.log("Connection OK");
                InputStream cuerpoRespuesta = con.getInputStream();
                InputStreamReader lector = new InputStreamReader(cuerpoRespuesta,"UTF-8");
                BufferedReader r = new BufferedReader(lector);
                StringBuilder total = new StringBuilder();
                for (String line; (line = r.readLine()) != null; ) {
                    total.append(line);
                }
                response = total.toString();
                CustomLog.log(response);
            }
            else {
                CustomLog.log("error when connecting to the api");
            }
            con.disconnect();
        }
        catch(Exception ex){
            CustomLog.logException(ex);
        }
        return response;

    }
}
