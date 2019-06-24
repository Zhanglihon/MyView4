package zlh.com.zlh0510xm1.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zlh.com.zlh0510xm1.R;
import zlh.com.zlh0510xm1.adapter.QuanAdapter;
import zlh.com.zlh0510xm1.bean.QuanBean;
import zlh.com.zlh0510xm1.core.ShowCall;
import zlh.com.zlh0510xm1.p.QuanPresenter;

/**
 * Time:${Data}
 * <p>
 * Author:Lenovo
 * <p>
 * Description:写这个类的作用
 */
public class TwoFragment extends Fragment implements ShowCall<List<QuanBean>>{

    Unbinder unbinder;
    @BindView(R.id.Xrecycle_list)
    XRecyclerView XrecycleList;
    @BindView(R.id.tf_image_fb)
    ImageView tfImageFb;
    private QuanAdapter quanAdapter;
    public int page=1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.twofragment_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        QuanPresenter quanPresenter = new QuanPresenter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        XrecycleList.setLayoutManager(layoutManager);
        //创建适配器
        quanAdapter = new QuanAdapter(getActivity());
        XrecycleList.setAdapter(quanAdapter);
        XrecycleList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                XrecycleList.refreshComplete();
                quanAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLoadMore() {
                page++;
                quanPresenter.requestData(page,"5");
                quanAdapter.notifyDataSetChanged();
                XrecycleList.loadMoreComplete();
            }
        });
        quanPresenter.requestData(page,"5");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void success(List<QuanBean> data) {
        quanAdapter.addAll(data);
        quanAdapter.notifyDataSetChanged();
    }
}
