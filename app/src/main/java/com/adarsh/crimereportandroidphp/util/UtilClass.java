package com.adarsh.crimereportandroidphp.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class UtilClass {

    public static void intent(Context firstcontext,Class secondclass,int a)
    {
        switch (a)
        {
            case 0 :
                Intent i=new Intent(firstcontext,secondclass);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                firstcontext.startActivity(i);
                break;

            case 1:
                Intent intent=new Intent(firstcontext,secondclass);
                firstcontext.startActivity(intent);
        }

    }
    public static boolean isEmpty(TextInputEditText textInputEditText)
    {
        if(textInputEditText.getText().toString().equals(""))
        {
            textInputEditText.setError("This field is mandatory");
            return true;
        }
        else {
            return false;
        }
    }

    public static void showToast(Context context,String message)
    {
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
    }


}
