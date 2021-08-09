

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.tp7_polshu.utiles.AlertHelper;
import com.example.tp7_polshu.utiles.CustomLog;

public class FlashlightHelper {

    /*   <uses-permission android:name="android.permission.CAMERA"/>
    <uses-permission android:name="android.permission.FLASHLIGHT"/>
    Add in manifest
     */
    private static  CameraManager manager;
    private static String cameraId;
    public static boolean estaEncendida = false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    private static void inicializarFlashlight(Context context){
        boolean estaDisponible = context.getPackageManager().
                hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        if(!estaDisponible) CustomLog.log("el flash no esta disponible"); //show an error
        else{
            manager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
            try {
                cameraId = manager.getCameraIdList()[0];
                manager.registerTorchCallback(statusCallBakck,null);
            } catch (Exception e){
                AlertHelper.mostrarAlertaError(context.getApplicationContext(), "parece que hubo un error con tu camara");
                CustomLog.logException(e);
            }
        }

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private static final CameraManager.TorchCallback statusCallBakck = new CameraManager.TorchCallback() {
        @Override
        public void onTorchModeChanged(@NonNull String cameraId, boolean enabled) {
            super.onTorchModeChanged(cameraId, enabled);
            estaEncendida = enabled;

        }
    };

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void switchFlashLight(Context c,boolean btnStatus) {
        try {
            if(manager == null) inicializarFlashlight(c);
            manager.setTorchMode(cameraId, btnStatus);
        } catch (Exception e) {
            CustomLog.logException(e);
        }
    }


}
