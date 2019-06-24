package zlh.com.zlh0510xm1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zlh.com.zlh0510xm1.PirticActicity1;
import zlh.com.zlh0510xm1.R;
import zlh.com.zlh0510xm1.SearchActivity;
import zlh.com.zlh0510xm1.adapter.OneFragmentAdapter1;
import zlh.com.zlh0510xm1.adapter.OneFragmentAdapter2;
import zlh.com.zlh0510xm1.adapter.OneFragmentAdapter3;
import zlh.com.zlh0510xm1.bean.BannerBean;
import zlh.com.zlh0510xm1.bean.Bannerr;
import zlh.com.zlh0510xm1.bean.OneFragmentBean;
import zlh.com.zlh0510xm1.bean.Shop;
import zlh.com.zlh0510xm1.core.ShowCall;
import zlh.com.zlh0510xm1.p.BannerPresenter;
import zlh.com.zlh0510xm1.p.OneFragmentPresenter;

/**
 * Time:${Data}
 * <p>
 * Author:Lenovo
 * <p>
 * Description:写这个类的作用
 */
public class OneFragment extends Fragment implements ShowCall<List<BannerBean>> {
    @BindView(R.id.indexBanner)
    XBanner indexBanner;
    Unbinder unbinder;
    @BindView(R.id.btn_search)
    ImageView btnSearch;
    @BindView(R.id.btn_cl)
    ImageView btnCl;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.recycler_view2)
    RecyclerView recyclerView2;
    @BindView(R.id.recycler_view3)
    RecyclerView recyclerView3;
    List<Bannerr> list = new ArrayList<>();

    private OneFragmentAdapter1 oneFragmentAdapter1;
    private OneFragmentPresenter oneFragmentPresenter;
    private OneFragmentAdapter2 oneFragmentAdapter2;
    private OneFragmentAdapter3 oneFragmentAdapter3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.onefragment_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BannerPresenter bannerPresenter = new BannerPresenter(this);
        bannerPresenter.requestData();
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SearchActivity.class);
                startActivity(intent);
            }
        });
        //热销商品
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        //创建适配器
        oneFragmentAdapter1 = new OneFragmentAdapter1(getActivity());
        recyclerView.setAdapter(oneFragmentAdapter1);
        oneFragmentPresenter = new OneFragmentPresenter(new One());
        oneFragmentPresenter.requestData();
        //魔力时尚
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity());
        layoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView2.setLayoutManager(layoutManager1);
        //创建适配器
        oneFragmentAdapter2 = new OneFragmentAdapter2(getActivity());
        recyclerView2.setAdapter(oneFragmentAdapter2);
        oneFragmentPresenter=new OneFragmentPresenter(new Two());
        oneFragmentPresenter.requestData();
        //品质生活
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),2);
        recyclerView3.setLayoutManager(gridLayoutManager);
        //创建适配器
        oneFragmentAdapter3 = new OneFragmentAdapter3(getActivity());
        recyclerView3.setAdapter(oneFragmentAdapter3);
        oneFragmentPresenter=new OneFragmentPresenter(new Three());
        oneFragmentPresenter.requestData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void success(List<BannerBean> data) {
        if (list.size() == 0) {//如果集合为空
            //则添加
            for (int i = 0; i < data.size(); i++) {
                Bannerr bannerr = new Bannerr();
                bannerr.imageUrl = data.get(i).imageUrl;
                list.add(bannerr);
            }
        }
        // 为XBanner绑定数据
        indexBanner.setBannerData(list);
        // XBanner适配数据
        indexBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(getContext()).load(((Bannerr) model).getXBannerUrl()).into((ImageView) view);
            }
        });
    }

    public class One implements ShowCall<OneFragmentBean>{

        @Override
        public void success(OneFragmentBean data) {
            oneFragmentAdapter1.add(data);
            oneFragmentAdapter1.notifyDataSetChanged();
        }
    }
    public class Two implements ShowCall<OneFragmentBean>{

        @Override
        public void success(OneFragmentBean data) {
            oneFragmentAdapter2.add(data);
            oneFragmentAdapter2.notifyDataSetChanged();
        }
    }
    public class Three implements ShowCall<OneFragmentBean>{

        @Override
        public void success(OneFragmentBean data) {
            oneFragmentAdapter3.add(data);
            oneFragmentAdapter3.notifyDataSetChanged();
        }
    }
}
