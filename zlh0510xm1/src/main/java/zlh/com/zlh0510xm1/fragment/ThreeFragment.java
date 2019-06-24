package zlh.com.zlh0510xm1.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zlh.com.zlh0510xm1.DaoMaster;
import zlh.com.zlh0510xm1.LoginBeanDao;
import zlh.com.zlh0510xm1.R;
import zlh.com.zlh0510xm1.adapter.ThreeFragmentAdapter;
import zlh.com.zlh0510xm1.bean.GwcBean;
import zlh.com.zlh0510xm1.bean.LoginBean;
import zlh.com.zlh0510xm1.core.ShowCall;
import zlh.com.zlh0510xm1.p.GwcPresenter;

/**
 * Time:${Data}
 * <p>
 * Author:Lenovo
 * <p>
 * Description:写这个类的作用
 */
public class ThreeFragment extends Fragment implements ShowCall<List<GwcBean>> {
    @BindView(R.id.tf_xrecycler_view)
    XRecyclerView tfXrecyclerView;
    Unbinder unbinder;
    public ThreeFragmentAdapter threeFragmentAdapter;
    @BindView(R.id.gouwu_quan_checkbox)
    CheckBox gouwuQuanCheckbox;
    @BindView(R.id.gouwu_text_view_zong)
    TextView gouwuTextViewZong;
    @BindView(R.id.shop_go)
    Button shopGo;
    private LoginBean loginBean;
    private GwcPresenter gwcPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.threefragment_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loginBean = DaoMaster.newDevSession(getContext(), LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        gwcPresenter = new GwcPresenter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        tfXrecyclerView.setLayoutManager(layoutManager);
        //创建适配器
        threeFragmentAdapter = new ThreeFragmentAdapter(getActivity());
        tfXrecyclerView.setAdapter(threeFragmentAdapter);
        gwcPresenter.requestData(loginBean.getUserId(), loginBean.getSessionId());


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void success(List<GwcBean> data) {
        threeFragmentAdapter.addAll(data);
        threeFragmentAdapter.notifyDataSetChanged();
    }
}
