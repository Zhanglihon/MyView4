package zlh.com.zlh0510xm1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zlh.com.zlh0510xm1.adapter.SearchAdapter;
import zlh.com.zlh0510xm1.bean.SearchBean;
import zlh.com.zlh0510xm1.core.ShowCall;
import zlh.com.zlh0510xm1.p.KeywordPresenter;

public class SearchActivity extends AppCompatActivity implements ShowCall<List<SearchBean>> {

    @BindView(R.id.search_back)
    ImageView searchBack;
    @BindView(R.id.edit_search)
    EditText editSearch;
    @BindView(R.id.search_btn_search)
    TextView searchBtnSearch;
    @BindView(R.id.xRecyclerView)
    XRecyclerView xRecyclerView;
    @BindView(R.id.image_id)
    ImageView imageId;
    @BindView(R.id.RelativeLayout)
    android.widget.RelativeLayout RelativeLayout;
    private String keyword = "手机";
    private SearchAdapter searchAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        KeywordPresenter keywordPresenter = new KeywordPresenter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        xRecyclerView.setLayoutManager(gridLayoutManager);
        searchAdapter = new SearchAdapter(this);
        xRecyclerView.setAdapter(searchAdapter);
        searchBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editSearch.getText().toString();
                keywordPresenter.requestData(name, "1", "10");
                searchAdapter.clear();
                searchAdapter.notifyDataSetChanged();
            }
        });
        keywordPresenter.requestData(keyword, "1", "10");
    }

    @Override
    public void success(List<SearchBean> data) {
        searchAdapter.addAll(data);
        searchAdapter.notifyDataSetChanged();
        if (data.size()==0) {
            RelativeLayout.setVisibility(View.VISIBLE);
        }else {
            RelativeLayout.setVisibility(View.GONE);
        }
    }
}
