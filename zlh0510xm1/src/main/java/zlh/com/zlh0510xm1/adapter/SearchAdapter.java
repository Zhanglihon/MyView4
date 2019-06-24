package zlh.com.zlh0510xm1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import zlh.com.zlh0510xm1.R;
import zlh.com.zlh0510xm1.bean.SearchBean;

/**
 * Time:${Data}
 * <p>
 * Author:Lenovo
 * <p>
 * Description:写这个类的作用
 */
public class SearchAdapter extends XRecyclerView.Adapter<SearchAdapter.holder> {
    Context context;
    List<SearchBean>list;

    public SearchAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.search_item_layout,null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.search_text_title.setText(list.get(position).commodityName);
        holder.search_text_price.setText(list.get(position).price);
        Glide.with(context).load(list.get(position).masterPic).into(holder.search_image_view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAll(List<SearchBean> data) {
        list.addAll(data);
    }

    public void clear() {
        list.clear();
    }

    public class holder extends XRecyclerView.ViewHolder{

        private final ImageView search_image_view;
        private final TextView search_text_title;
        private final TextView search_text_price;

        public holder(View itemView) {
            super(itemView);
            search_image_view = itemView.findViewById(R.id.search_image_view);
            search_text_title = itemView.findViewById(R.id.search_text_title);
            search_text_price = itemView.findViewById(R.id.search_text_price);
        }
    }
}
