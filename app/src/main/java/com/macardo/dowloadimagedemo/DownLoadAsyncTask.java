package com.macardo.dowloadimagedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownLoadAsyncTask extends AsyncTask<String,Void,byte[]> {
    private ImageView iv;
    public DownLoadAsyncTask(ImageView iv){
        this.iv = iv;
    }

    @Override
    protected byte[] doInBackground(String... strings) {
        //内存流
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            //1.将url网络地址字符串封装成url对象
            URL url = new URL(strings[0]);
            //2.打开url对象的网络连接 获得网络连接对象
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //3.网络连接的设置
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            //4.获取响应状态码
            int responseCode = connection.getResponseCode();
            if (responseCode == 200){
                InputStream inputStream = connection.getInputStream();
                int temp = 0;
                byte[] buff = new byte[1024];
                while ((temp = inputStream.read(buff))!= -1){
                    outputStream.write(buff,0,temp);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }

    //严谨，避免空指针异常
    @Override
    protected void onPostExecute(byte[] bytes) {
        if (bytes!=null && bytes.length != 0){
            //将字节数组转换成bitmap对象展示
            Bitmap bm = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            iv.setImageBitmap(bm);
        }else{
            Log.i("TAG","下载异常！");
        }
    }
}
