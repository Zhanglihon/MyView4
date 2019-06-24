package zlh.com.zlh0510xm1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import zlh.com.zlh0510xm1.R;
import zlh.com.zlh0510xm1.bean.WdqbBean;
import zlh.com.zlh0510xm1.bean.xfmx;

/**
 * Time:${Data}
 * <p>
 * Author:Lenovo
 * <p>
 * Description:写这个类的作用
 */
public class WdqbAdapter extends RecyclerView.Adapter<WdqbAdapter.holder> {
    Context context;
    List<xfmx>list;

    public WdqbAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.wdqb_item,null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        double je=list.get(position).amount;
        holder.xfmx_xfje.setText(je+"");
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat();
        String data=simpleDateFormat.format(new Date(list.get(position).createTime));
        holder.xfmx_xfsj.setText(data);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void add(WdqbBean data) {
        List<xfmx>wd=data.detailList;
        list.addAll(wd);
    }

    public class holder extends RecyclerView.ViewHolder{

        private final TextView xfmx_xfje;
        private final TextView xfmx_xfsj;

        public holder(View itemView) {
            super(itemView);
            xfmx_xfje = itemView.findViewById(R.id.xfmx_xfje);
            xfmx_xfsj = itemView.findViewById(R.id.xfmx_xfsj);
        }
    }
}
