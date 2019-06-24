package zlh.com.zlh0510xm1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zlh.com.zlh0510xm1.bean.GwcBean;
import zlh.com.zlh0510xm1.bean.LoginBean;
import zlh.com.zlh0510xm1.bean.PirticBean;
import zlh.com.zlh0510xm1.bean.XGwcBean;
import zlh.com.zlh0510xm1.core.ShowCall;
import zlh.com.zlh0510xm1.p.GwcPresenter;
import zlh.com.zlh0510xm1.p.PirticPresenter;
import zlh.com.zlh0510xm1.p.XGwcPrexenter;

public class PirticActicity1 extends AppCompatActivity implements ShowCall<PirticBean>{

    @BindView(R.id.detail_back)
    ImageView detailBack;
    @BindView(R.id.detail_goods)
    RadioButton detailGoods;
    @BindView(R.id.detail_detail)
    RadioButton detailDetail;
    @BindView(R.id.detail_context)
    RadioButton detailContext;
    @BindView(R.id.detail_group)
    RadioGroup detailGroup;
    @BindView(R.id.title)
    LinearLayout title;
    @BindView(R.id.detail_banner)
    Banner detailBanner;
    @BindView(R.id.detail_price)
    TextView detailPrice;
    @BindView(R.id.detail_num)
    TextView detailNum;
    @BindView(R.id.detail_name)
    TextView detailName;
    @BindView(R.id.detail_web)
    WebView detailWeb;
    @BindView(R.id.sync_gwc)
    ImageView syncGwc;
    @BindView(R.id.shopingcar)
    ImageView shopingcar;
    List<String>list=new ArrayList<>();
    List<XGwcBean> slist;
    XGwcPrexenter xGwcPrexenter;
    private GwcPresenter gwcPresenter;
    private LoginBean loginBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pirtic_acticity1);
        ButterKnife.bind(this);
        PirticPresenter pirticPresenter=new PirticPresenter(this);
        Intent intent=getIntent();
        gwcPresenter=new GwcPresenter(new GwcCx());
        String id=intent.getStringExtra("str");
        pirticPresenter.requestData("906","1559023617184906",id);

        WebSettings webSettings=detailWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);  //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true);//缩放至屏幕大小
        webSettings.setSupportZoom(true);  //支持缩放，默认为true。是下面那个的前提。
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        webSettings.setDefaultFontSize(40);//设置 WebView 字体的大小，默认大小为 16
        webSettings.setMinimumFontSize(35);//设置 WebView 支持的最小字体大小，默认为 8
        webSettings.setDefaultTextEncodingName("UTF-8");//设置默认为utf-8

        detailWeb.setWebViewClient(new WebViewClient());
        detailWeb.setWebChromeClient(new WebChromeClient());
        //同步购物车
        syncGwc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    //查询购物车
     class GwcCx implements ShowCall<List<GwcBean>> {
        @Override
        public void success(List<GwcBean> data) {
            for (int i = 0; i < data.size(); i++) {
                slist.add(new XGwcBean(data.get(i).count,data.get(i).commodityId));
            }
            Gson gson=new Gson();
            String s=gson.toJson(slist);
            xGwcPrexenter.requestData(s);
        }
    }

    @Override
    public void success(PirticBean data) {
        detailPrice.setText(data.price+"");
        detailName.setText(data.commodityName);
        detailNum.setText("已售"+data.saleNum+"件");
        detailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String picture=data.picture;
        String[] icon=picture.split(",");
        for (int i = 0; i < icon.length; i++) {
            list.add(icon[i]);
        }
        detailBanner.setImages(list).isAutoPlay(true).setDelayTime(2000).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        }).start();
        String js="<script type=\"text/javascript\">" +
                "var imgs = document.getElementsByTagName('img');" + // 找到img标签
                "for(var i = 0; i<imgs.length; i++){" +  // 逐个改变
                "imgs[i].style.width = '100%';" +  // 宽度改为100%
                "imgs[i].style.height = 'auto';" +
                "}" +
                "</script>";
        detailWeb.loadDataWithBaseURL(null,data.details+js,"text/html","utf-8",null);
    }
}
