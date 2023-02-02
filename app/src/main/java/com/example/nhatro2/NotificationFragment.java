package com.example.nhatro2;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NotificationFragment extends Fragment {
    public NotificationFragment() {
        // Required empty public constructor
    }

    public static NotificationFragment newInstance() {
        NotificationFragment fragment = new NotificationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup itemView = (ViewGroup) inflater.inflate(R.layout.notification_fragment,container,false);

        SharedPreferences shp = getContext().getSharedPreferences("user", MODE_PRIVATE);
        String nameUser = shp.getString("tenThanhVien","");
        String phoneUser = shp.getString("dienthoaiThanhVien","");
        TextView nameUserText, phoneUserText;
        nameUserText = itemView.findViewById(R.id.nameUser);
        phoneUserText = itemView.findViewById(R.id.phoneUser);

        nameUserText.setText(nameUser);
        phoneUserText.setText(phoneUser);
        return itemView;
    }
}
