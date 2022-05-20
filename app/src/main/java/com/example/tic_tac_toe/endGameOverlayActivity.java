package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class endGameOverlayActivity extends AppCompatActivity {
    private Button screenShot_btn;
    private static final int STORAGE_PERMISSION_CODE = 101;
    Bitmap screenShotOfPreviousView;

    private TextView endGameMessageTextView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game_overlay);

        endGameMessageTextView = (TextView) findViewById(R.id.endGameMessageTextView);

        Bundle extras = getIntent().getExtras();
        byte[] byteArray = extras.getByteArray("picture");
        String endGameMessage = extras.getString("endGameMessage");

        endGameMessageTextView.setText(endGameMessage);

        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);


        screenShot_btn = (Button)findViewById(R.id.button_screen);

        screenShot_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);
                saveScreenshot(bmp);
            }
        });

    }


    private void saveScreenshot(Bitmap bitmap) {

        Date now = new Date();
        DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file

            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + "Pictures" + "/" + "Screenshots" + "/" + now + ".jpg";
            System.out.println(mPath);
            String folderPath = Environment.getExternalStorageDirectory().toString() + "/" + "Pictures" + "/" + "Screenshots";

            File directory = new File(folderPath);
            if (! directory.exists()){
                directory.mkdir();
                // If you require it to make the entire directory path including parents,
                // use directory.mkdirs(); here instead.
            }


            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            //openScreenshot(imageFile);
        } catch (Throwable e) {
            // Several error may come out with file handling or DOM
            e.printStackTrace();
        }
    }

    private void openScreenshot(File imageFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = FileProvider.getUriForFile(endGameOverlayActivity.this, BuildConfig.APPLICATION_ID + ".provider",imageFile);
        intent.setDataAndType(uri, "image/*");
        startActivity(intent);
    }

    public void checkPermission(String permission, int requestCode)
    {
        // Checking if permission is not granted
        if (ContextCompat.checkSelfPermission(endGameOverlayActivity.this, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(endGameOverlayActivity.this, new String[] { permission }, requestCode);
        }
        else {
            //Toast.makeText(endGameOverlayActivity.this, "Permission already granted", Toast.LENGTH_SHORT).show();
        }
    }

}