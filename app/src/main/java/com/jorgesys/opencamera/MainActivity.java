package com.jorgesys.opencamera;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "CameraActivity";
    private Camera mCameraFront = null;
    private Camera mCameraBack = null;
    private CameraView mCameraViewFront = null;
    private CameraView mCameraViewBack  = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkCameraPermission();

         Log.d(TAG, "Number of Cameras: " + Camera.getNumberOfCameras());

        if (Camera.getNumberOfCameras() >= 2) {
            try{
              mCameraBack = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
                Log.i(TAG, "opening CAMERA_FACING_BACK");
            } catch (Exception e){
                Log.e(TAG, "Failed to get camera CAMERA_FACING_BACK : " + e.getMessage());
            }

            try{
                mCameraFront = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
                Log.i(TAG, "opening CAMERA_FACING_FRONT");
            } catch (Exception e){
                Log.e(TAG, "Failed to get camera CAMERA_FACING_FRONT : " + e.getMessage());
            }


        }
        if(mCameraFront != null) {
            mCameraViewFront = new CameraView(this, mCameraFront);
            FrameLayout camera_view = (FrameLayout)findViewById(R.id.camera_one);
            camera_view.addView(mCameraViewFront);//agrega la vista CameraView()
        }

        if(mCameraBack != null) {
            mCameraViewBack = new CameraView(this, mCameraBack);
            FrameLayout camera_view2 = (FrameLayout)findViewById(R.id.camera_two);
            camera_view2.addView(mCameraViewBack);//agrega la vista CameraView()*/
        }

        //Button for closing the app.
        ImageButton imgClose = (ImageButton)findViewById(R.id.imgClose);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    private void checkCameraPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.e("Permission", "Permission required to use the camera!.");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 225);
        } else {
            Log.i("Permission", "Permission to use camera is already set.");
        }
    }
}
