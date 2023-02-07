package com.example.nhatro2.firebase;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.nhatro2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Calendar;

public class NotificationFirebase extends FirebaseMessagingService {
    SharedPreferences tokenFirebase;
    @Override


    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        FirebaseMessaging.getInstance().getToken()
        .addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (!task.isSuccessful()) {
                    Log.w("err", "Fetching FCM registration token failed", task.getException());
                    return;
                }

                // Get new FCM registration token
                String token = task.getResult();

                // Log and toast
                @SuppressLint({"StringFormatInvalid", "LocalSuppress"})
                String msg = getString(R.string.msg_token_fmt, token);

                tokenFirebase = getSharedPreferences("fireBaseToken",MODE_PRIVATE);
                Log.d("oktoken", msg);
                Toast.makeText(NotificationFirebase.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
    }


    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
    }
}
