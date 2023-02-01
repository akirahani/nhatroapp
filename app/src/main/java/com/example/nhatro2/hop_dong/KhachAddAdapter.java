package com.example.nhatro2.hop_dong;

import android.content.Context;

import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;
import com.example.nhatro2.thanhvien.ThanhVienModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class KhachAddAdapter extends RecyclerView.Adapter<KhachAddAdapter.KhachTroAddViewHolder> {
    Context context;
    List<ListKhachChonModel> khachTro;

    public KhachAddAdapter(Context context, List<ListKhachChonModel> khachTro) {
        this.context = context;
        this.khachTro = khachTro;
    }

    @NonNull
    @Override
    public KhachAddAdapter.KhachTroAddViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.khach_hop_dong_add_item, parent, false);
        return new KhachTroAddViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhachAddAdapter.KhachTroAddViewHolder holder, int position) {
        ListKhachChonModel khachThueItem = khachTro.get(position);
        int id = khachThueItem.getId();
        String tenKhach = khachThueItem.getFullname();
        String canCuoc = khachThueItem.getCancuoc();
        String ngayCap = khachThueItem.getNgaycap();
        String ngaySinh = khachThueItem.getNgaysinh();
        String dienThoai = khachThueItem.getDienthoai();
        String noicap = khachThueItem.getNoicap();

        holder.tenKhachAdd.setText(tenKhach);
//        holder.canCuocKhachAdd.setText(canCuoc);
//        holder.ngayCapKhachAdd.setText(ngayCap);
//        holder.ngaySinhHopDongAddText.setText(ngaySinh);
        holder.sdtKhachAdd.setText(dienThoai);
//        holder.noiCapKhachAdd.setText(noicap);

        position++;

        SharedPreferences shpKhachThem = context.getSharedPreferences("thongTinKhach", Context.MODE_PRIVATE);
        SharedPreferences.Editor khachEdit = shpKhachThem.edit();
        String tenKhachLuu = shpKhachThem.getString("itemTenKhach", "");
        String sdtKhachLuu = shpKhachThem.getString("itemDienThoai", "");

        List<String> thanhVienPhong = new ArrayList<>();

//            tenKhachText = findViewById(R.id.tenKhachAdd);
//            sdtKhachText = findViewById(R.id.sdtKhachAdd);
//            String nameCustomer = tenKhachText.getText().toString();
//            String phone = sdtKhachText.getText().toString();
//            thanhVienPhong.add(nameCustomer);
//            String convertStringTenKhach = null;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                convertStringTenKhach = thanhVienPhong.stream().map(String::valueOf).collect(Collectors.joining(","));
//            }
//
//            khachEdit.putString("itemTenKhach",convertStringTenKhach);
//            khachEdit.commit();

//            Log.d("tenKhach1",""+convertStringTenKhach);


        if (position <= khachTro.size() - 1) {
            holder.tenKhachAdd.setText(khachTro.get(position).getFullname());
//            holder.canCuocKhachAdd.setText(khachTro.get(position).getCancuoc());
//            holder.ngayCapKhachAdd.setText(khachTro.get(position).getNgaycap());
//            holder.ngaySinhHopDongAddText.setText(khachTro.get(position).getNgaysinh());
            holder.sdtKhachAdd.setText(khachTro.get(position).getDienthoai());
//            holder.noiCapKhachAdd.setText(khachTro.get(position).getNoicap());

        }
        holder.thuTuKhachTro.setText("Khách trọ " + position);

    }

    @Override
    public int getItemCount() {
        return khachTro.size();
    }

    public class KhachTroAddViewHolder extends RecyclerView.ViewHolder {
        EditText tenKhachAdd, canCuocKhachAdd, noiCapKhachAdd, sdtKhachAdd;
        TextView ngayCapKhachAdd, ngaySinhHopDongAddText, thuTuKhachTro;

        public KhachTroAddViewHolder(@NonNull View itemView) {
            super(itemView);
            tenKhachAdd = itemView.findViewById(R.id.tenKhachAdd);
//            canCuocKhachAdd = itemView.findViewById(R.id.canCuocKhachAdd);
//            noiCapKhachAdd = itemView.findViewById(R.id.noiCapKhachAdd);
            sdtKhachAdd = itemView.findViewById(R.id.sdtKhachAdd);
//            ngayCapKhachAdd = itemView.findViewById(R.id.ngayCapKhachAdd);
//            ngaySinhHopDongAddText = itemView.findViewById(R.id.ngaySinhHopDongAddText);
            thuTuKhachTro = itemView.findViewById(R.id.thuTuKhachTro);


        }
    }
}
