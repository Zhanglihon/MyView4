package zlh.com.zlh0510xm1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zlh.com.zlh0510xm1.R;
import zlh.com.zlh0510xm1.bean.ShdzBean;

/**
 * Time:${Data}
 * <p>
 * Author:Lenovo
 * <p>
 * Description:写这个类的作用
 */
public class ShdzAdapter extends RecyclerView.Adapter<ShdzAdapter.holder> {
    Context context;
    List<ShdzBean>list;

    public ShdzAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.shdz_item,null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.shdz_name.setText(list.get(position).realName);
        holder.shdz_phone.setText(list.get(position).phone);
        holder.shdz_address.setText(list.get(position).address);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAll(List<ShdzBean> data) {
        list.addAll(data);
    }

    public class holder extends RecyclerView.ViewHolder{

        private final TextView shdz_name;
        private final TextView shdz_address;
        private final TextView shdz_delete;
        private final TextView shdz_phone;
        private final RadioButton shdz_radius;
        private final TextView shdz_update;

        public holder(View itemView) {
            super(itemView);
            shdz_name = itemView.findViewById(R.id.shdz_name);
            shdz_address = itemView.findViewById(R.id.shdz_address);
            shdz_delete = itemView.findViewById(R.id.shdz_delete);
            shdz_phone = itemView.findViewById(R.id.shdz_phone);
            shdz_radius = itemView.findViewById(R.id.shdz_radius);
            shdz_update = itemView.findViewById(R.id.shdz_update);
        }
    }
}
