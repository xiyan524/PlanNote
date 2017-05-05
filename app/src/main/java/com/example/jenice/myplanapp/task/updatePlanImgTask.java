package com.example.jenice.myplanapp.task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;

/**
 * Created by Jenice on 2017/4/24.
 */

public class updatePlanImgTask extends AsyncTask<String,Void,Bitmap> {
    private ImageView imageView;

    public updatePlanImgTask(ImageView imageView){
        this.imageView=imageView;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String file_url=params[0];
        String file_name=params[1];

        AVFile file = new AVFile(file_name,file_url,null);
        Bitmap bm = null;
        try {
            byte[] bytes =file.getData();
            bm = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            return bm;
        } catch (AVException e) {
            e.printStackTrace();
        }
        return bm;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imageView.setImageBitmap(bitmap);
    }
}

