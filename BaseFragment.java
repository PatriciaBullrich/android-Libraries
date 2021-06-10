package com.example.tpombd;



import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {
    public void ponerTitulo(String t){
        MainActivity main = (MainActivity) getActivity();
        main.setTitle(t);
    }

}
