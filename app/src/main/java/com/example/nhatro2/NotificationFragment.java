package com.example.nhatro2;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.nhatro2.api.ApiQH;
import com.example.nhatro2.kha_bien.KhaBien;
import com.example.nhatro2.kha_bien.KhaBienAdapter;
import com.example.nhatro2.kha_bien.KhaBienModel;
import com.example.nhatro2.thanhvien.ThanhVienModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        LinearLayout dangXuatTaiKhoan,infoUser, changePassd;
        SharedPreferences shp = getContext().getSharedPreferences("user", MODE_PRIVATE);
        String nameUser = shp.getString("tenThanhVien","");
        String phoneUser = shp.getString("dienthoaiThanhVien","");
        TextView nameUserText, phoneUserText;
        nameUserText = itemView.findViewById(R.id.nameUser);
        phoneUserText = itemView.findViewById(R.id.phoneUser);
        nameUserText.setText(nameUser);
        phoneUserText.setText(phoneUser);

        dangXuatTaiKhoan = itemView.findViewById(R.id.dangXuatTaiKhoan);
        dangXuatTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(Html.fromHtml("<font color='#71a6d5'>Thông báo!</font>")).setMessage(Html.fromHtml("<font color='#71a6d5'>Bạn có thực sự muốn thoát ?</font>"));
                builder.setCancelable(true);
                builder.setIcon(R.drawable.alert_bottom);
                //check
                builder.setPositiveButton(Html.fromHtml("<font color='#71a6d5'>Có</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getContext(),"Thoát", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                        SharedPreferences shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                        shp.edit().clear().commit();
//                        view.getContext();
                    }
                });
                // NO
                builder.setNegativeButton(Html.fromHtml("<font color='#71a6d5'>Không</font>"), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getContext(),"Ở lại", Toast.LENGTH_SHORT).show();
                        //  Cancel
                        dialog.cancel();
                    }
                });
                // show alert
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        changePassd = itemView.findViewById(R.id.changePassdRow);
        changePassd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView changePassdClick,changePassdClose;
                Dialog changePassdForm = new Dialog(getContext());
                changePassdForm.requestWindowFeature(Window.FEATURE_NO_TITLE);
                changePassdForm.setContentView(R.layout.layout_dialog_change_passd);

                Window window = changePassdForm.getWindow();
                if (window == null) {
                    return;
                }

                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                WindowManager.LayoutParams windowAttr = window.getAttributes();
                windowAttr.gravity = Gravity.CENTER;
                window.setAttributes(windowAttr);

                changePassdClick = changePassdForm.findViewById(R.id.changePassdClick);
                changePassdClose = changePassdForm.findViewById(R.id.changePassdClose);

                changePassdClick.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText passdOld, passdNew;
                        passdOld = changePassdForm.findViewById(R.id.passdOld);
                        passdNew = changePassdForm.findViewById(R.id.passdNew);

                        String passdOldText = passdOld.getText().toString();
                        String passdNewText = passdNew.getText().toString();
                        SharedPreferences shp = getContext().getSharedPreferences("user", MODE_PRIVATE);
                        shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                        int idThanhVienQuanLy = shp.getInt("idThanhVien",0);


                        ApiQH.apiQH.changePassd(idThanhVienQuanLy,passdOldText,passdNewText).enqueue(new Callback<ThanhVienModel>() {
                            @Override
                            public void onResponse(Call<ThanhVienModel> call, Response<ThanhVienModel> response) {
                                Toast.makeText(getContext(),"Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                                changePassdForm.dismiss();
                            }

                            @Override
                            public void onFailure(Call<ThanhVienModel> call, Throwable t) {
                                Log.d("loi nay dm",""+t.toString());
                                Toast.makeText(getContext(),"Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });

                changePassdClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        changePassdForm.dismiss();
                    }
                });

                changePassdForm.show();
            }
        });

        infoUser = itemView.findViewById(R.id.infoUserRow);
        infoUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog infoUserForm = new Dialog(getContext());
                infoUserForm.requestWindowFeature(Window.FEATURE_NO_TITLE);
                infoUserForm.setContentView(R.layout.layout_dialog_info_user);

                Window window = infoUserForm.getWindow();
                if (window == null) {
                    return;
                }

                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                WindowManager.LayoutParams windowAttr = window.getAttributes();
                windowAttr.gravity = Gravity.CENTER;
                window.setAttributes(windowAttr);

                TextView tenTaiKhoan, tenDayDu,sdtTaiKhoan;
                tenTaiKhoan = infoUserForm.findViewById(R.id.tenTaiKhoan);
                tenDayDu = infoUserForm.findViewById(R.id.tenDayDu);
                sdtTaiKhoan = infoUserForm.findViewById(R.id.sdtTaiKhoan);

                SharedPreferences shp = getContext().getSharedPreferences("user", MODE_PRIVATE);
                shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                int idThanhVienQuanLy = shp.getInt("idThanhVien",0);
                String tenThanhVienGet = shp.getString("tenThanhVien","");
                String dienThoaiThanhVien = shp.getString("dienthoaiThanhVien", "");
                String userNameThanhVien = shp.getString("usernameThanhVien", "");

                tenTaiKhoan.setText("Tên tài khoản: "+userNameThanhVien);
                sdtTaiKhoan.setText("Số điện thoại: "+dienThoaiThanhVien);
                tenDayDu.setText("Tên đầy đủ: "+tenThanhVienGet);

                infoUserForm.show();
            }
        });
        return itemView;
    }
}
