package com.adarsh.crimereportandroidphp.util;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.adarsh.crimereportandroidphp.ui.MainActivity;
import com.adarsh.crimereportandroidphp.R;
import com.adarsh.crimereportandroidphp.ui.citizen.SignUpActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

import okhttp3.RequestBody;

public class AadharScannerActivity extends AppCompatActivity {

   Storage storage;
   String uid, name, gender, yearOfBirth, careOf, villageTehsil, street, postOffice, district, subdistrict, state, house, postCode;
   String Json;
    RequestBody requestBody = null;
    private static final int MY_CAMERA_REQUEST_CODE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aadhar_scanner);
         storage=new Storage(this);
        permission();
    }
    private void permission(){
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED)
            {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA
                    },
                    MY_CAMERA_REQUEST_CODE);
             }
        else {
            IntentIntegrator integrator = new IntentIntegrator(this);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
            integrator.setPrompt("Scan your Aadharcard QR Code");
            integrator.setCameraId(0);  // Use a specific camera of the device
            integrator.initiateScan();
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result
        super.onActivityResult(requestCode, resultCode, intent);
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (scanningResult != null) {
            //we have a result
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();

            // process received data
            if (scanContent != null && !scanContent.isEmpty()) {
                processScannedData(scanContent);


            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Scan Cancelled", Toast.LENGTH_SHORT);
                toast.show();
                Intent i=new Intent(AadharScannerActivity.this, MainActivity.class);
                startActivity(i);
            }

        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
            Intent i=new Intent(AadharScannerActivity.this,MainActivity.class);
            startActivity(i);
        }
    }

    /**
     * process xml string received from aadhaar card QR code
     *
     * @param scanData
     */
    protected void processScannedData(String scanData) {
        Log.d("Rajdeol", scanData);
        XmlPullParserFactory pullParserFactory;

        try {
            // init the parserfactory
            pullParserFactory = XmlPullParserFactory.newInstance();
            // get the parser
            XmlPullParser parser = pullParserFactory.newPullParser();

            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(new StringReader(scanData));

            // parse the XML
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_DOCUMENT) {
                    Log.d("Rajdeol", "Start document");
                } else if (eventType == XmlPullParser.START_TAG && DataAttributes.AADHAAR_DATA_TAG.equals(parser.getName())) {
                    // extract data from tag
                    //uid
                    uid = parser.getAttributeValue(null, DataAttributes.AADHAR_UID_ATTR);
                    //name
                    name = parser.getAttributeValue(null, DataAttributes.AADHAR_NAME_ATTR);
                    //gender
                    gender = parser.getAttributeValue(null, DataAttributes.AADHAR_GENDER_ATTR);
                    // year of birth
                    yearOfBirth = parser.getAttributeValue(null, DataAttributes.AADHAR_YOB_ATTR);
                    // care of
                    careOf = parser.getAttributeValue(null, DataAttributes.AADHAR_CO_ATTR);

                    house = parser.getAttributeValue(null, DataAttributes.AADHAR_HOUSE_ATTR);

                    street = parser.getAttributeValue(null, DataAttributes.AADHAR_STREET_ATTR);
                    // village Tehsil
                    villageTehsil = parser.getAttributeValue(null, DataAttributes.AADHAR_VTC_ATTR);
                    // Post Office
                    postOffice = parser.getAttributeValue(null, DataAttributes.AADHAR_PO_ATTR);
                    // district
                    district = parser.getAttributeValue(null, DataAttributes.AADHAR_DIST_ATTR);

                    subdistrict = parser.getAttributeValue(null, DataAttributes.AADHAR_SUBDIST_ATTR);
                    // state
                    state = parser.getAttributeValue(null, DataAttributes.AADHAR_STATE_ATTR);
                    // Post Code
                    postCode = parser.getAttributeValue(null, DataAttributes.AADHAR_PC_ATTR);

                    storeScannedData();



                } else if (eventType == XmlPullParser.END_TAG) {
                    Log.d("Rajdeol", "End tag " + parser.getName());

                } else if (eventType == XmlPullParser.TEXT) {
                    Log.d("Rajdeol", "Text " + parser.getText());

                }
                // update eventType
                eventType = parser.next();
            }

            // display the data on screen
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void storeScannedData() {

        Toast.makeText(this,name, Toast.LENGTH_SHORT).show();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("key1", uid);
        editor.putString("key2", name);
        editor.putString("key3", gender);
        editor.putString("key4", yearOfBirth);
        editor.putString("key5", careOf);
        editor.putString("key6", house);
        editor.putString("key7", villageTehsil);
        editor.putString("key8", street);
        editor.putString("key9", district);
        editor.putString("key10", state);
        editor.putString("key11", postCode);
        editor.putString("key12", subdistrict);
        editor.apply();

       Intent i=new Intent(AadharScannerActivity.this, SignUpActivity.class);
       startActivity(i);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case MY_CAMERA_REQUEST_CODE:
                IntentIntegrator integrator = new IntentIntegrator(this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan your Aadharcard QR Code");
                integrator.setCameraId(0);  // Use a specific camera of the device
                integrator.initiateScan();
                break;
        }
    }
}