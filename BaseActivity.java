

import android.view.Gravity;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    public void tostada(String msj){
        Toast.makeText(this, msj, Toast.LENGTH_LONG).show();
    }

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



}
