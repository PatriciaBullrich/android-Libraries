
import android.util.Log;

import android.util.Log;

public final class CustomLog {
    public static final String CUSTOM_TAG ="Custom Tag";
    public static void log(String msj){
        Log.d(CUSTOM_TAG, msj);
    }
    public static void logException(Exception e){
        log("errors: "+ e.getMessage());
    }
    public static void logObject(Object o){
        log("object recieved:" + o.toString());
    }
}
