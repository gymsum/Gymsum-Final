package com.tugas.hisyam.gymsum;

/**
 * Created by HISYAM on 3/24/2018.
 */
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class adapterkedua extends RecyclerView.Adapter<adapterkedua.holdermn> {
    private Context context;
    private List<menulist2> listmenu2;


    public adapterkedua(Context context, List<menulist2> listmenu2) {
        this.context = context;
        this.listmenu2 = listmenu2;
    }

    @Override

    public holdermn onCreateViewHolder(ViewGroup parent, int viewType) {

        View vw = LayoutInflater.from(context).inflate(R.layout.cardview2, parent, false);
        holdermn holder = new holdermn(vw);
        return holder;
    }

    @Override

    public void onBindViewHolder(holdermn holder, int position) {
        menulist2 dt = listmenu2.get(position);
        holder.nama.setText(dt.getNama());
        holder.detail.setText(dt.getDesc());
        holder.rl.setTag(dt.getGambar());
    }

    @Override

    public int getItemCount() {
        return listmenu2.size();
    }

    class holdermn extends RecyclerView.ViewHolder {

        TextView nama, detail;
        RelativeLayout rl;

        public holdermn(View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.namaLatihan);
            detail = itemView.findViewById(R.id.detailLatihan);
            rl = itemView.findViewById(R.id.rl);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                //ketika cardview di klik
                public void onClick(View view) {

                    Intent a = new Intent(context, DaftarMenu.class);

                    a.putExtra("nama", nama.getText());
                    a.putExtra("detail", detail.getText());
                    a.putExtra("gambar", rl.getTag().toString());

                    context.startActivity(a);
                }
            });
        }
    }
}