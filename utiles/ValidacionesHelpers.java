

import android.widget.EditText;
import android.widget.RadioGroup;
import java.util.Random;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

    
public class ValidacionesHelpers {
    
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

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
    public static boolean esSoloNumeros(String s){
        return !s.equals("") && s.matches("[0-9]+");
    }
        public static int generarRandom(int min, int max){
        Random r = new Random();
        return r.nextInt(max-min) + min;
    }

     public static String convertMayus(EditText edt_txt){
        return edt_txt.getText().toString().trim().toUpperCase();
    }

    public static String convertMinus(EditText edt_txt){
        return edt_txt.getText().toString().trim().toLowerCase();
    }
       public static boolean validarMail(EditText mail) {
        String emailStr = mail.getText().toString().trim();
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static boolean RG_check(RadioGroup rg){
        return rg.getCheckedRadioButtonId() != -1;
    }



}
