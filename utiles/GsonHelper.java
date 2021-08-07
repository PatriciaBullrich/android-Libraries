
//add json in your build.gradle
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class GsonHelper<T>{
    private T element;
    private final Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                        .create();
    //you can choose any date format that you want


    public T fromJson(String json){
        Type typeOfT = new TypeToken<T>(){}.getType();
        return gson.fromJson(json, typeOfT);
    }

    public T[] fromJsonToArray(String json){
        Type typeOfT = new TypeToken<T[]>(){}.getType();
        return gson.fromJson(json, typeOfT);
    }

    public ArrayList<T> fromJsonToArrayList(String json){
        Type typeOfT = new TypeToken<ArrayList<T>>(){}.getType();
        return gson.fromJson(json, typeOfT);
    }

    public String toJson(T element){
        return gson.toJson(element);
    }

    public String toJson(T[] element){
        return gson.toJson(element);
    }

    public String toJson(ArrayList<T> element){
        return gson.toJson(element);
    }
}
