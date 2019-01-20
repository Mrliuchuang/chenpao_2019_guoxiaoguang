package com.efan.chenpao_2019_guoxiaoguang;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_camera;
    private Button sendimage;
    private ImageView imageView;
    private TextView response1;
    private static int CAMERA_REQUEST_CODE = 1;
    private Bitmap bitmap;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        btn_camera = (Button) findViewById(R.id.btn_carema);
        sendimage = (Button) findViewById(R.id.sendimage);
        response1 = (TextView) findViewById(R.id.response);
        sendimage.setOnClickListener(this);
        btn_camera.setOnClickListener(this);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (data == null) {
                return;
            } else {
                Bundle extras = data.getExtras();
                if (extras != null) {
                    bitmap = extras.getParcelable("data");
                    imageView.setImageBitmap(bitmap);
                }

            }
        }
    }

    public void Okhttp(String img) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Gson gson = new Gson();
        Map<String, String> map = new HashMap<>();
        map.put("image_type", "BASE64");
        map.put("image", img);
        map.put("group_id_list", "student");
        String param = gson.toJson(map);
//       String param="{\n" +
//               "            \"image\": \"39.106.34.160/liuchuang/liu.jpg\",\n" +
//               "                \"image_type\": \"URL\",\n" +
//               "                \"group_id_list\": \"student\"\n" +
//               "        }" ;
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search?access_token=24.a770c71385b2e22bda4a8581a9cac272.2592000.1550579891.282335-15455209";
        RequestBody requestBody = RequestBody.create(JSON, param);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        Jsonbean jsonbean = gson.fromJson(json, Jsonbean.class);
                        Double score = jsonbean.getResult().getUser_list().get(0).getScore();
                        if (score > 85) {
                            Toast.makeText(MainActivity.this, "人脸识别成功", Toast.LENGTH_SHORT);
                            damessage();

                        } else {
                            Toast.makeText(MainActivity.this, "打卡失败", Toast.LENGTH_SHORT).show();
                        }

                    }

                    private void damessage() {

                    }
                });
            }
        });
    }




    public void sendimage(Bitmap bm) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 60, stream);
        byte[] bytes = stream.toByteArray();
        String img = new String(Base64.encodeToString(bytes, Base64.DEFAULT));
        Okhttp(img);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_carema:
                Log.e("dda", "摄像头");
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST_CODE);
                break;
            case R.id.sendimage:
                Log.e("dd", "ddd");
                sendimage(bitmap);
            default:
                break;
        }
    }
}
