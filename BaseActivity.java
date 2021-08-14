import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tp7_polshu.utiles.AlertHelper;
import com.example.tp7_polshu.utiles.Defaults;
import com.example.tp7_polshu.utiles.CustomLog;

import java.util.Locale;
import java.util.Set;

public class BaseActivity extends AppCompatActivity {

    
    // makes a simple toast, you can modify the length or the gravity if you like
    public void tostada(String msj){
        Toast.makeText(this, msj, Toast.LENGTH_LONG).show();
    }

    //region Replace fragment
    // this functions use a predefined framelayout as the container where all the fragments are replaced
    // you can change the frame layout to the one that you use or pass it in params
    // addBackToStack false, the app will not remember the fragment if you go back or delete it
    public void reemplazarFragment(Fragment frag, boolean addBackToStack){
        FragmentManager manager;
        FragmentTransaction transaction;

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.fraContenedor,frag,null);
        if(addBackToStack){
            transaction.addToBackStack(null);
        }
        transaction.commit();

    }
    public void reemplazarFragment(Fragment frag){
        reemplazarFragment(frag,true);
    }
    //endregion
    
    

    // region Preferences functions
    // to save preferences call this.SavePreferences(key, value).
    // to read preferences call this.ReadPreferences(value, datatype as a string) EG: ReadPreferences("userCount", "int").
    // To clear the xml prefs file call this.clearPreferences()
    // this methods implement the shared prefs object 
    protected void SavePreferences(String key, int value){
        if(key == null){
            CustomLog.log("error. la llave no puede ser nula");
            return;
        }

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key,value);
        editor.apply(); //editor.commit():
    }

    protected void SavePreferences(String key, String value){
        if(key == null){
            CustomLog.log("error. la llave no puede ser nula");
            return;
        }
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key,value);
        editor.apply(); //editor.commit():
    }

    protected void SavePreferences(String key, boolean value){
        if(key == null) {
            CustomLog.log("error. la llave no puede ser nula");
            return;
        }
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key,value);
        editor.apply(); //editor.commit():
    }

    protected void SavePreferences(String key, float value){
        if(key == null){
            CustomLog.log("error. la llave no puede ser nula");
            return;
        }
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat(key,value);
        editor.apply(); //editor.commit():
    }


    protected void SavePreferences(String key, long value){
        if(key == null){
            CustomLog.log("error. la llave no puede ser nula");
            return;
        }
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(key,value);
        editor.apply(); //editor.commit():
    }

    protected void SavePreferences(String key,  Set<String> value){
        if(key == null){
            CustomLog.log("error. la llave no puede ser nula");
            return;
        }
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putStringSet(key,value);
        editor.apply(); //editor.commit():
    }
    //leer de las preferencias
    // * write the primitive {@type type} for the function to work.
    // * if you miss the spelling it returns null and logs
    //
    protected Object ReadPreferences (String key, String type) {
        //needs the datatype of the return value
        if(key == null){
            CustomLog.log("error. la llave no puede ser nula");
            return null;
        }
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        Object result = null;
        switch (type.toLowerCase(Locale.ROOT)){
            case "int":
                result =  (int) sharedPref.getInt(key,Defaults.NUMBER);// can replace default with whatever you want
                break;
            case "float":
                result =  (float) sharedPref.getFloat(key,Defaults.NUMBER);
                break;
            case "long":
                result =  (long) sharedPref.getLong(key,Defaults.NUMBER);
                break;
            case "string":
                result =  (String) sharedPref.getString(key,Defaults.STRING);
                break;
            case "boolean":
                result =  (boolean) sharedPref.getBoolean(key,Defaults.BOOLEAN);
                break;
            case "set":
                result =  (Set<String>) sharedPref.getStringSet(key,Defaults.STRING_SET);
                break;
            default:
                CustomLog.log("no encontre el tipo de dato especificado");
                break;
        }
        return result;
    }

    public void clearPreferences(){
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.apply();
    }
    //endregion



    //region Make a phone call
    /* this functions will try to make a phone call by using the intent ACTION_CALL
    * Notice that if you do not let the app make calls, this will ask for permission. 
    * You can override the request permissions switch block to get the permissions for anything you want
    * This will work just for calling though*/

    private static final int REQUEST_PHONE_CALL = 1;
    
    public void makeCall(String tel){
        if(checkPermission(Manifest.permission.CALL_PHONE)) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + tel));
            this.startActivity(callIntent);
        }
        else requestPermission(Manifest.permission.CALL_PHONE,REQUEST_PHONE_CALL);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PHONE_CALL:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    CustomLog.log("access granted");

                    // main logic
                } else {
                   CustomLog.log("access denied");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(this, permissions[0])
                                != PackageManager.PERMISSION_GRANTED) {
                            AlertHelper.mostrarMensaje(this,"Necesito que me des permisos",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            requestPermission(permissions[0],REQUEST_PHONE_CALL);
                                        }
                                    });
                        }
                    }
                }
                break;
        }
    }


    private boolean checkPermission(String permission) {
        // Permission is not granted
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission(String permission, int code) {

        ActivityCompat.requestPermissions(this,
                new String[]{permission},
                code);
    }
    //endregion

}
