package zlh.com.zlh0510xm1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zlh.com.zlh0510xm1.adapter.WdzjAdapter;
import zlh.com.zlh0510xm1.bean.LoginBean;
import zlh.com.zlh0510xm1.bean.WdzjBean;
import zlh.com.zlh0510xm1.core.ShowCall;
import zlh.com.zlh0510xm1.p.WdzjPresenter;

public class wdzjActivity extends AppCompatActivity implements ShowCall<List<WdzjBean>> {

    @BindView(R.id.wdzj_recycler_view)
    RecyclerView wdzjRecyclerView;
    LoginBean loginBean;
    private WdzjAdapter wdzjAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBean=DaoMaster.newDevSession(this,LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        setContentView(R.layout.activity_wdzj);
        ButterKnife.bind(this);
        WdzjPresenter wdzjPresenter=new WdzjPresenter(this);
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        wdzjRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        //创建适配器
        wdzjAdapter = new WdzjAdapter(this);
        wdzjRecyclerView.setAdapter(wdzjAdapter);
        wdzjPresenter.requestData(loginBean.getUserId(),loginBean.getSessionId(),"1","11");
    }

    @Override
    public void success(List<WdzjBean> data) {
        wdzjAdapter.addAll(data);
        wdzjAdapter.notifyDataSetChanged();
    }
}
