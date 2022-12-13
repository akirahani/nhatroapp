package com.example.nhatro2.dich_vu;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhatro2.R;

public class DichVuEdit extends AppCompatActivity {
    TextView tacVuThietBi,tieuDeTenThietBi,tieuDeGiaThietBi;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dich_vu_edit);

        float alpha = (float) 0.7;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ((TextView)findViewById(R.id.tacVuThietBi)).setTextColor(Color.argb(alpha, 0, 0, 0));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ((TextView)findViewById(R.id.tieuDeTenThietBi)).setTextColor(Color.argb(alpha, 0, 0, 0));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ((TextView)findViewById(R.id.tieuDeGiaThietBi)).setTextColor(Color.argb(alpha, 0, 0, 0));
        }
    }
}
