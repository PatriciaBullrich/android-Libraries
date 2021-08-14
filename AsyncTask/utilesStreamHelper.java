import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class StreamHelper {
    //Receives a String URL and returns the json reponse into a string
    //Used for Async task with apis
    public static String returnJsonAsString(InputStream cuerpoRespuesta){
        String response ="";
        try{
                InputStreamReader lector = new InputStreamReader(cuerpoRespuesta,"UTF-8");
                BufferedReader r = new BufferedReader(lector);
                StringBuilder total = new StringBuilder();
                for (String line; (line = r.readLine()) != null; ) {
                    total.append(line);
                }
                response = total.toString();
                CustomLog.log(response);
        }
        catch(Exception ex){
            CustomLog.logException(ex);
        }
        return response;
    }
}
