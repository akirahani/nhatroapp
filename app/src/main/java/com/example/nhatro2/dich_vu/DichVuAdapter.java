package com.example.nhatro2.dich_vu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;
import com.example.nhatro2.api.Api;

import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DichVuAdapter extends RecyclerView.Adapter<DichVuAdapter.DichVuViewHolder> {
    Context context;
    List<DichVuModel> dichVu;
    ImageView editDichVu, xoaDichVu;

    public DichVuAdapter(Context context, List<DichVuModel> dichVu) {
        this.context = context;
        this.dichVu = dichVu;
    }

    @NonNull
    @Override
    public DichVuAdapter.DichVuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dich_vu_item,parent,false);
        editDichVu = view.findViewById(R.id.suaDichVu);
        xoaDichVu = view.findViewById(R.id.xoaDichVu);
        return new DichVuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DichVuAdapter.DichVuViewHolder holder, int position) {
        DichVuModel dataDV = dichVu.get(position);
        int id = dataDV.getId();
        String ten = dataDV.getTen();
        int loai = dataDV.getLoai();
        int gia = dataDV.getGia();

        //Lấy Id dịch vụ để sửa
        editDichVu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(),DichVuEdit.class);
                intent.putExtra("idDichVu",id);
                context.startActivity(intent);

            }

        });

        //Xóa dịch vụ
        xoaDichVu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder  = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Confirm").setMessage("Bạn có thực sự muốn xóa ?");
                builder.setCancelable(true);
                builder.setIcon(R.drawable.alert_bottom);
                // Yes
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        id = dataDV.getId();
                        Api.api.delDichVu(id).enqueue(new Callback<DichVuModel>() {
                            @Override
                            public void onResponse(Call <DichVuModel> call, Response <DichVuModel> response){
                                DichVuModel dichVu = response.body();
                                context.startActivity(new Intent(context.getApplicationContext(), DichVu.class));
                                Toast.makeText(view.getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure (Call < DichVuModel > call, Throwable t){
                                Toast.makeText(view.getContext(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                // No
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        Toast.makeText(view.getContext(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
                // show alert
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String giaFormat = formatter.format(gia);
        // holder.idDichVu.setText(id+"");
        holder.tenDichVu.setText(ten);
        // holder.loaiDichVu.setText(loai+"");
        holder.giaDichVu.setText(giaFormat+"đ");

    }

    @Override
    public int getItemCount() {
        return dichVu.size();
    }

    public class DichVuViewHolder extends RecyclerView.ViewHolder {
        TextView idDichVu, tenDichVu, loaiDichVu, giaDichVu;
        ImageView suaDichVu, xoaDichVu;
        public DichVuViewHolder(@NonNull View itemView) {
            super(itemView);
//            idDichVu = itemView.findViewById(R.id.idDichVu);
            tenDichVu = itemView.findViewById(R.id.tenDichVu);
//            loaiDichVu = itemView.findViewById(R.id.loaiDichVu);
            giaDichVu = itemView.findViewById(R.id.giaDichVu);
            suaDichVu = itemView.findViewById(R.id.suaDichVu);
            xoaDichVu = itemView.findViewById(R.id.xoaDichVu);
        }
    }
}
