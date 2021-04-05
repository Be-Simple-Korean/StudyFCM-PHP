package com.example.studyfcm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNotificationChanner();
                String token= FirebaseInstanceId.getInstance().getToken();
                Log.e("토큰 글자수",token.length()+"");
                registerToken(token);
                FirebaseMessaging.getInstance().subscribeToTopic("test message");
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("수행",1+"");
                    fcm();
            }
        });


    }
    private void fcm(){
        new Thread(){
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient();
//                RequestBody body2=new FormBody.Builder()
//                        .build();
                Request request=new Request.Builder()
                        .url("http://whi2020.dothome.co.kr/AndroidEx/StudyFCM/pushNoti.php")
                        .build();
                // .post(body2)
                try{
                    client.newCall(request).execute();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }}.start();

    }
    private void createNotificationChanner(){
        CharSequence name="lemubit";
        String des="Channel for lemubit reminder";
        int importance= NotificationManager.IMPORTANCE_DEFAULT;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel=new NotificationChannel("noti",name,importance);
            channel.setDescription(des);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    private void registerToken(String token) {
        new Thread(){
            @Override
            public void run() {
                Log.e("token",token);
                OkHttpClient client=new OkHttpClient();
                RequestBody body2=new FormBody.Builder()
                        .add("Token",token)
                        .build();
                Request request=new Request.Builder()
                        .url("http://whi2020.dothome.co.kr/AndroidEx/register.php")
                        .post(body2)
                        .build();
                try{
                    client.newCall(request).execute();
                    Log.e("수행","2");
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }}.start();
    }
}