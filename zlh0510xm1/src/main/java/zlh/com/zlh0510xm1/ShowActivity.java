package zlh.com.zlh0510xm1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zlh.com.zlh0510xm1.adapter.MyFragmentAdapter;
import zlh.com.zlh0510xm1.fragment.FiveFragment;
import zlh.com.zlh0510xm1.fragment.FourFragment;
import zlh.com.zlh0510xm1.fragment.OneFragment;
import zlh.com.zlh0510xm1.fragment.ThreeFragment;
import zlh.com.zlh0510xm1.fragment.TwoFragment;

public class ShowActivity extends AppCompatActivity {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rb3)
    RadioButton rb3;
    @BindView(R.id.rb4)
    RadioButton rb4;
    @BindView(R.id.rb5)
    RadioButton rb5;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    List<Fragment>list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        list.add(new OneFragment());
        list.add(new TwoFragment());
        list.add(new ThreeFragment());
        list.add(new FourFragment());
        list.add(new FiveFragment());
        MyFragmentAdapter myFragmentAdapter=new MyFragmentAdapter(getSupportFragmentManager(),list);
        viewPager.setAdapter(myFragmentAdapter);
        //解决滑动冲突
        viewPager.setOffscreenPageLimit(list.size());
        //点击切换页面
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb1:
                        viewPager.setCurrentItem(0,false);
                        break;
                    case R.id.rb2:
                        viewPager.setCurrentItem(1,false);
                        break;
                    case R.id.rb3:
                        viewPager.setCurrentItem(2,false);
                        break;
                    case R.id.rb4:
                        viewPager.setCurrentItem(3,false);
                        break;
                    case R.id.rb5:
                        viewPager.setCurrentItem(4,false);
                        break;
                }
            }
        });
        //滑动切换页面
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                radioGroup.check(radioGroup.getChildAt(i).getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
