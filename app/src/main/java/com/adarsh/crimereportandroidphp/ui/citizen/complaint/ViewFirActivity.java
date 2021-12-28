package com.adarsh.crimereportandroidphp.ui.citizen.complaint;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.adarsh.crimereportandroidphp.R;
import com.adarsh.crimereportandroidphp.retrofit.models.ViewFirModel;
import com.adarsh.crimereportandroidphp.retrofit.network.Api;
import com.adarsh.crimereportandroidphp.retrofit.network.ApiClient;
import com.adarsh.crimereportandroidphp.ui.police.PoliceHome;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewFirActivity extends AppCompatActivity {
    String firId;
    private static final int MY_STORAGE_PERMISSION_CODE = 100;
    private ImageView imgview;
    private TextView fir;
    private TextView section;
    private TextView districtTitle;
    private TextView districtTxtview;
    private TextView pstitle;
    private TextView pstextview;
    private TextView firnotitle;
    private TextView firnotextview;
    private TextView yeartitle;
    private TextView dateandtime;
    private TextView firdatetextview;
    private TextView firTimeEdt;
    private TextView yeartextview;
    private TableLayout table;
    private TextView actstextview;
    private TextView sectionstextview;
    private TextView occurence;
    private TextView day;
    private TextView dayEdt;
    private TextView datefrom;
    private TextView dateFromEdt;
    private TextView dateto;
    private TextView dateToEdt;
    private TextView timefrom;
    private TextView timeFromEdt;
    private TextView timeto;
    private TextView timeToEdt;
    private TextView gdr;
    private TextView diaryNumber;
    private TextView diaryNumberEdt;
    private TextView diaryTime;
    private TextView diaryTimeEdt;
    private TextView typeofinfo;
    private TextView typeofinfoEdit;
    private TextView placeofoccurence;
    private TextView distance;
    private TextView distanceEdt;
    private TextView beat;
    private TextView beatEdt;
    private TextView address;
    private TextView addressEdt;
    private RelativeLayout relativeLayout;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_fir);
        initView();
        if(null != getIntent().getStringExtra("firIdIntent")) {
             firId = getIntent().getStringExtra("firIdIntent");
            Api api= ApiClient.Citizen().create(Api.class);
            api.VIEW_FIR_MODEL_CALL(firId).enqueue(new Callback<ViewFirModel>() {
                @Override
                public void onResponse(Call<ViewFirModel> call, Response<ViewFirModel> response) {
                    ViewFirModel viewFirModel=response.body();
                    if(viewFirModel.getStatus().equalsIgnoreCase("Success"))
                    {
                        placeofoccurence.setText(viewFirModel.getFir_details().getPlace_of_occurance());
                        districtTxtview.setText(viewFirModel.getFir_details().getDistrict());
                        pstextview.setText(viewFirModel.getFir_details().getPolice_station());
                        firnotextview.setText(viewFirModel.getFir_details().getFir_number());
                        yeartextview.setText("2021");
                        firdatetextview.setText(viewFirModel.getFir_details().getFir_date());
                        firTimeEdt.setText(viewFirModel.getFir_details().getFir_time());
                        actstextview.setText(viewFirModel.getFir_details().getIpc_act());
                        sectionstextview.setText(viewFirModel.getFir_details().getIpc_section());
                        dayEdt.setText(viewFirModel.getFir_details().getOccure_day());
                        dateFromEdt.setText(viewFirModel.getFir_details().getOccure_datefrom());
                        dateToEdt.setText(viewFirModel.getFir_details().getOccure_dateto());
                        timeFromEdt.setText(viewFirModel.getFir_details().getOccure_timefrom());
                        timeToEdt.setText(viewFirModel.getFir_details().getOccure_timeto());
                        diaryNumberEdt.setText(viewFirModel.getFir_details().getDiary_entry());
                        diaryTimeEdt.setText( viewFirModel.getFir_details().getTime());
                        typeofinfoEdit.setText(viewFirModel.getFir_details().getInform_type());
                        distanceEdt.setText(viewFirModel.getFir_details().getDistance());
                        beatEdt.setText(viewFirModel.getFir_details().getBeat_number());
                        addressEdt.setText(viewFirModel.getFir_details().getAddress_citizen());
                    }
                    else
                    {
                        Toast.makeText(ViewFirActivity.this, "FIR not found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ViewFirModel> call, Throwable t) {
                    Toast.makeText(ViewFirActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }
        else
        {
            Toast.makeText(this, "Invalid FIR", Toast.LENGTH_SHORT).show();
        }

    }

    private void initView() {
        relativeLayout=findViewById(R.id.relative);
        imgview = findViewById(R.id.imgview);
        fir = findViewById(R.id.fir);
        section = findViewById(R.id.section);
        districtTitle = findViewById(R.id.district_title);
        districtTxtview = findViewById(R.id.district_txtview);
        pstitle = findViewById(R.id.pstitle);
        pstextview = findViewById(R.id.pstextview);
        firnotitle = findViewById(R.id.firnotitle);
        firnotextview = findViewById(R.id.firnotextview);
        yeartitle = findViewById(R.id.yeartitle);
        dateandtime = findViewById(R.id.dateandtime);
        firdatetextview = findViewById(R.id.firdatetextview);
        firTimeEdt = findViewById(R.id.fir_time_edt);
        yeartextview = findViewById(R.id.yeartextview);
        table = findViewById(R.id.table);
        actstextview = findViewById(R.id.actstextview);
        sectionstextview = findViewById(R.id.sectionstextview);
        occurence = findViewById(R.id.occurence);
        day = findViewById(R.id.day);
        dayEdt = findViewById(R.id.day_edt);
        datefrom = findViewById(R.id.datefrom);
        dateFromEdt = findViewById(R.id.date_from_edt);
        dateto = findViewById(R.id.dateto);
        dateToEdt = findViewById(R.id.date_to_edt);
        timefrom = findViewById(R.id.timefrom);
        timeFromEdt = findViewById(R.id.time_from_edt);
        timeto = findViewById(R.id.timeto);
        timeToEdt = findViewById(R.id.time_to_edt);
        gdr = findViewById(R.id.gdr);
        diaryNumber = findViewById(R.id.diary_number);
        diaryNumberEdt = findViewById(R.id.diary_number_edt);
        diaryTime = findViewById(R.id.diary_time);
        diaryTimeEdt = findViewById(R.id.diary_time_edt);
        typeofinfo = findViewById(R.id.typeofinfo);
        typeofinfoEdit = findViewById(R.id.typeofinfo_edit);
        placeofoccurence = findViewById(R.id.placeofoccurence);
        distance = findViewById(R.id.distance);
        distanceEdt = findViewById(R.id.distance_edt);
        beat = findViewById(R.id.beat);
        beatEdt = findViewById(R.id.beat_edt);
        address = findViewById(R.id.address);
        addressEdt = findViewById(R.id.address_edt);
    }

    public void savePdf(View view) {
        if (getApplication().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_STORAGE_PERMISSION_CODE);
        }
        else {
            bitmap = loadBitmapFromView(relativeLayout, relativeLayout.getWidth(), relativeLayout.getHeight());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                createPdf();
            }
        }
    }
    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);

        return b;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void createPdf() {

        try {


            android.graphics.pdf.PdfDocument document = new android.graphics.pdf.PdfDocument();
            android.graphics.pdf.PdfDocument.PageInfo pageInfo = new android.graphics.pdf.PdfDocument.PageInfo.Builder(relativeLayout.getWidth(), relativeLayout.getHeight(),1).create();
            android.graphics.pdf.PdfDocument.Page page = document.startPage(pageInfo);

            Canvas canvas = page.getCanvas();
            Paint paint = new Paint();
            canvas.drawPaint(paint);
            relativeLayout.draw(canvas);
            document.finishPage(page);
            String targetPdf = "/sdcard/pdffromlayout.pdf";
            File filePath;
            filePath = new File(targetPdf);
            try {
                document.writeTo(new FileOutputStream(filePath));

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
            }

            // close the document
            document.close();
            Toast.makeText(this, "PDF is created!!!", Toast.LENGTH_SHORT).show();
//            Intent intent=new Intent(getApplicationContext(), PoliceHome.class);
//            startActivity(intent);
            openGeneratedPDF();


        }catch (Exception e){

            //Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }


    private void openGeneratedPDF() {
        File file = new File("/sdcard/pdffromlayout.pdf");
        if (file.exists()) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(file);
            intent.setDataAndType(uri, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(ViewFirActivity.this, "No Application available to view pdf", Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_STORAGE_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(getApplicationContext(), "storage permission granted", Toast.LENGTH_LONG).show();
                bitmap = loadBitmapFromView(relativeLayout, relativeLayout.getWidth(), relativeLayout.getHeight());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    createPdf();
                }
            }
            else
            {
                Toast.makeText(getApplicationContext(), "storage permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }
}
