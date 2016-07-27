package com.example.a1216qdf.exasynctask;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private final String IMAGE_PATH = "http://developer.android.com/images/home/kk-hero.jpg";
//    private final String IMAGE_PATH2 = "http://ww2.sinaimg.cn/mw690/69c7e018jw1e6hd0vm3pej20fa0a674c.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.imageView);

    }

    //按下"測試一"按鈕，7秒之後，TextView文字會改變
    public void btn1_onclick(View v) {
        new btn1Task().execute();
    }

    //按下"測試二"按鈕，n秒之後(傳入整數)，TextVIew文字會改變
    public void btn2_onclick(View v){
        new btn2Task().execute(3);
    }

    //按下"測試三"按鈕，n秒之後，TextView文字隨著倒數改變
    public void btn3_onclick(View v){
        new btn3Task().execute(5);
    }

    //按下"下載一"按鈕，開始下載圖片(圓圈)
    public void btn4_onclick(View v){
       new btn4Task().execute(IMAGE_PATH);
    }

    //按下"下載二"按鈕，開始下載圖片(進度條)
    public void btn5_onclick(View v){
        new btn5Task().execute(IMAGE_PATH);
    }

    class btn1Task extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            TextView textView = (TextView) findViewById(R.id.infor);
            textView.setText("嗨，測試一成功!");
        }
    }

    class btn2Task extends AsyncTask<Integer,Void,Void>{

        @Override
        protected Void doInBackground(Integer... params) {
            try{
                Thread.sleep(params[0]*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            TextView textView = (TextView)findViewById(R.id.infor);
            textView.setText("嗨，測試二成功");
        }
    }

    class btn3Task extends AsyncTask<Integer,Integer,Void>{

        @Override
        protected Void doInBackground(Integer... params) {
            for (int i = params[0];i>0;i--){
                publishProgress(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            TextView textView = (TextView)findViewById(R.id.infor);
            textView.setText(String.valueOf(values[0]));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            TextView textView = (TextView)findViewById(R.id.infor);
            textView.setText("嗨，測試三成功!");
        }
    }

    class btn4Task extends AsyncTask<String,Integer,byte[]>{

        private ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new  ProgressDialog(MainActivity.this);
            progressDialog.setTitle("提示信息");
            progressDialog.setMessage("正在下載中，請稍後。。。");
            progressDialog.setCancelable(false);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(byte[] bytes) {
            super.onPostExecute(bytes);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            imageView.setImageBitmap(bitmap);
            progressDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected byte[] doInBackground(String... params) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(params[0]);
            byte[] image = new byte[]{};

            try {
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                if (httpEntity != null &&
                        httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
                {
                    image = EntityUtils.toByteArray(httpEntity);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                httpClient.getConnectionManager().shutdown();
            }
            return image;
        }
    }

    class btn5Task extends AsyncTask<String,Integer,byte[]>{

        public ProgressDialog progressDialog;

        @Override
        protected byte[] doInBackground(String... params) {

            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(params[0]);
            byte[] image = new byte[]{};


            try {
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                InputStream inputStream = null;
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                if (httpEntity != null &&
                        httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                    long file_length = httpEntity.getContentLength();
                    long total_length = 0;
                    int length = 0;
                    byte[] data = new byte[256];
                    inputStream = httpEntity.getContent();

                    while (-1 != (length = inputStream.read(data))){
                        total_length += length;
                        byteArrayOutputStream.write(data, 0, length);
                        int progress = ((int)(total_length/(float)file_length)*100);
                        publishProgress(progress);
                    }
                }
                image = byteArrayOutputStream.toByteArray();
                inputStream.close();
                byteArrayOutputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                httpClient.getConnectionManager().shutdown();
            }

            return image;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("提示訊息二");
            progressDialog.setMessage("正在下載中");
            progressDialog.setCancelable(false);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(byte[] bytes) {
            super.onPostExecute(bytes);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            imageView.setImageBitmap(bitmap);
            progressDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }
    }
}

