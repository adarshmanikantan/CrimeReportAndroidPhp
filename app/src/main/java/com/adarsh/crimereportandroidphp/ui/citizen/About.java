package com.adarsh.crimereportandroidphp.ui.citizen;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.adarsh.crimereportandroidphp.R;

import java.util.Locale;


public class About extends Fragment {

    Locale myLocale;
    String currentLanguage = "en", currentLang;
    private TextView lang;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_about, container, false);
        initView(root);

        Bundle bundle=getArguments();
        if(bundle != null) {
            currentLanguage = bundle.getString(currentLang);

        }
        if(currentLanguage.equals("en"))
        {
            Typeface custom_typerface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/dyuthi.ttf");
            lang.setTypeface(custom_typerface);
            lang.setText("മലയാളം");

        }
        else
        {
            lang.setText("English");
        }
        lang.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(currentLanguage.equals("ml")) {
                     setLocale("en");
                     Typeface custom_typerface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/dyuthi.ttf");
                     lang.setTypeface(custom_typerface);
                     lang.setText("മലയാളം");

                 }
                 else
                 {
                     setLocale("ml");
                     lang.setText("English");
                 }
             }
         });
        return root;
    }
    public void setLocale(String localeName) {
//        if (!localeName.equals(currentLanguage)) {
            myLocale = new Locale(localeName);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);

            Fragment currentFragment = new About();
            Bundle bundle=new Bundle();
            bundle.putString(currentLang,localeName);
            currentFragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.nav_host_fragmentc,About.class,bundle);
            fragmentTransaction.commit();

//        } else {
//            Toast.makeText(getContext(), "Language already selected!", Toast.LENGTH_SHORT).show();
//        }
    }

    private void initView(View root) {
        lang = root.findViewById(R.id.lang);
    }
}