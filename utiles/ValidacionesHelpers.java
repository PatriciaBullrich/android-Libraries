

import android.widget.EditText;

import java.util.Random;

public class ValidacionesHelpers {

    public  static final int maxCaracteres = 20000000;
    public static boolean esStringValido(String texto, int min, int max){
        boolean aux = false;
        if(texto.length()!=0 && texto.length()>=min && texto.length()<= max){
            aux = true;
        }
        return aux;
    }
    public static boolean esStringValido(String texto, int min){
        return esStringValido(texto,min,maxCaracteres);
    }

    public static boolean esStringValido(String texto){
        return esStringValido(texto,0);
    }

    public static boolean esStringValido(EditText edt_txt){
        String aux = edt_txt.getText().toString().trim();
        return esStringValido(aux);
    }
    
        public static int generarRandom(int min, int max){
        Random r = new Random();
        return r.nextInt(max-min) + min;
    }

    public static String filtrarAminus(EditText e){
        return e.getText().toString().trim().toLowerCase();
    }



}
