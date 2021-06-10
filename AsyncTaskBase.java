//Generic Async task to extend for GET requests

import com.example.tpombd.utiles.StreamHelper;
import android.os.AsyncTask;
public class AsyncTaskBase extends AsyncTask<Void, Void ,String> {

    private String Busqueda;

    public AsyncTaskBase(String b) {
        this.Busqueda = b;
    }
    @Override
    protected String doInBackground(Void... voids) {
        return StreamHelper.returnJsonAsString(Busqueda);
    }
}
