
//add gson in your build.gradle
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

// you can add this interface to your class templates to make them work with gson and use
// a generic builder for all classes.

public interface  GsonInterface<T> {
     Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create();

    T fromJson(String s);
    T[] fromJsonToArray(String s);
    ArrayList<T> fromJsonToArrayList(String s);
    String toJson(T element);
    String toJson(T[] array);
    String toJson(ArrayList<T>);
}
