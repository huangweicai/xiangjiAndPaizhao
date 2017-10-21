package com.example.huangweicai.mycamera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import me.nereo.multi_image_selector.MultiImageSelectorFragment;
import me.nereo.multi_image_selector.utils.FileUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void album(View v) {
        CameraManager.getInstance().showAlbumAction(MainActivity.this, false, 1, CameraManager.MODE_SINGLE);
    }

    public void camera(View v) {
        CameraManager.getInstance().showCameraAction(MainActivity.this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CameraManager.REQUEST_CAMERA) {
            //请求拍照
            if (resultCode == Activity.RESULT_OK) {
                if (CameraManager.mTmpFile != null) {
                    Uri o = Uri.fromFile(CameraManager.mTmpFile);
                    CameraManager.mNewPhotoUri = CameraUtil.getOutputMediaFileUri(this, CameraUtil.MEDIA_TYPE_IMAGE);
                    CameraManager.getInstance().cropImage(this, o, CameraManager.mNewPhotoUri, CameraManager.REQUEST_CODE_CROP_PHOTO);

                    Log.d("TAG", "相机 mlocitionFileUrl:" + CameraManager.mNewPhotoUri);
                }
            } else {
                while (CameraManager.mTmpFile != null && CameraManager.mTmpFile.exists()) {
                    boolean success = CameraManager.mTmpFile.delete();
                    if (success) {
                        CameraManager.mTmpFile = null;
                    }
                }
            }
        } else if (requestCode == CameraManager.REQUEST_IMAGE) {
            //请求图片
            if (resultCode == Activity.RESULT_OK) {
                // 获取返回的图片列表
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                Uri o = Uri.fromFile(new File(path.get(0)));
                CameraManager.mNewPhotoUri = CameraUtil.getOutputMediaFileUri(this, CameraUtil.MEDIA_TYPE_IMAGE);
                CameraManager.getInstance().cropImage(this, o, CameraManager.mNewPhotoUri, CameraManager.REQUEST_CODE_CROP_PHOTO);
            }

        } else if (requestCode == CameraManager.REQUEST_CODE_CROP_PHOTO) {
            //裁剪
            if (resultCode == Activity.RESULT_OK) {
                if (CameraManager.mNewPhotoUri != null) {
                    CameraManager.mCurrentFile = new File(CameraManager.mNewPhotoUri.getPath());
                    CameraManager.mlocitionFileUrl = CameraManager.mNewPhotoUri.toString();

                    Log.d("TAG", "mlocitionFileUrl:" + CameraManager.mlocitionFileUrl);
                } else {

                }
            }
        }

    }


}
