import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.turri.tp_login_polshu.AsyncTask.AsyncGetBase;
import com.turri.tp_login_polshu.AsyncTask.AsyncTaskBase;
import com.turri.tp_login_polshu.AsyncTask.TaskListener;
import com.turri.tp_login_polshu.Model.Usuario;
import com.turri.tp_login_polshu.Utiles.AlertHelper;
import com.turri.tp_login_polshu.Utiles.CustomLog;
import com.turri.tp_login_polshu.Utiles.ValidacionesHelpers;


public class LoginFragment extends BaseFragment{

	EditText et_userName;
	EditText et_clave;
	Button btn_login;
	MainActivity main;
	View rootLayout;
	
	public void inicializar(){
		if(rootLayout != null){
			main  = (MainActivity) getActivity();
			et_userName = (EditText) rootLayout.findViewById(R.id.et_userName);
			et_clave = (EditText) rootLayout.findViewById(R.id.et_clave);
			btn_login = (Button) rootLayout.findViewById(R.id.btn_login);
		}
	}
	View.OnClickListener btn_login_click = v ->{
		if(esFormularioValido()) {
			login nuevoLogin = new login(et_userName.getText().toString().trim(),et_clave.getText().toString().trim());
			nuevoLogin.setParams("param1", 3);
			nuevoLogin.setParams("param2", "my value");
			nuevoLogin.execute();
		}
	};

	private boolean esFormularioValido() {
		boolean aux = true;
		String errores = "";
		if(!ValidacionesHelpers.esStringValido(et_userName)){
			aux = false;
			errores += "falta llenar el usuario \n";
		}
		if(!ValidacionesHelpers.esStringValido(et_clave)){
			aux = false;
			errores += "falta llenar la contraseña \n";
		}

		if(!aux) AlertHelper.mostrarAlertaError(getContext(), errores);
		return aux;
	}

	public void setearListeners(){
		btn_login.setOnClickListener(btn_login_click);
	}

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
		if(rootLayout == null) rootLayout =  inflater.inflate(R.layout.fragment_login, container, false);
		inicializar();
		setearListeners();
		return rootLayout;
    }
    private final TaskListener myListener = new TaskListener() {
		@Override
		public void onTaskStarted() {
			CustomLog.log("task started");

		}

		@Override
		public void onTaskFinished(String s) {
			if(!s.equals("")){
				Session.setCurrentUser(s);
				main.SavePreferences("saved-user", s);
				main.irAMarcas();
				//informar logeo
			}
			else AlertHelper.mostrarAlertaError(getContext(), "El usuario o contraseña no son correctos");
		}

		@Override
		public void onBadRequest() {

		}

		@Override
		public void onError(Exception ex) {
			CustomLog.logException(ex);
		}
	};

	private class login extends AsyncTaskBase {
		public login(String userName, String password){
			super(RequestMethods.GET,
					String.format("http://api.polshu.com.ar/api/v1/usuarios/login/%s/%s",userName,password),
					myListener);
		}
	}

}
