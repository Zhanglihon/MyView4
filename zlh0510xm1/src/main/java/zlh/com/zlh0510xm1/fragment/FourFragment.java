package zlh.com.zlh0510xm1.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zlh.com.zlh0510xm1.R;
import zlh.com.zlh0510xm1.adapter.FourMyFragmentAdapter;
import zlh.com.zlh0510xm1.fourfragment.FourFiveFragment;
import zlh.com.zlh0510xm1.fourfragment.FourFourFragment;
import zlh.com.zlh0510xm1.fourfragment.FourOneFragment;
import zlh.com.zlh0510xm1.fourfragment.FourThreeFragment;
import zlh.com.zlh0510xm1.fourfragment.FourTwoFragment;

/**
 * Time:${Data}
 * <p>
 * Author:Lenovo
 * <p>
 * Description:写这个类的作用
 */
public class FourFragment extends Fragment {
    List<Fragment> list = new ArrayList<>();

    @BindView(R.id.four_radio_group)
    RadioGroup fourRadioGroup;
    @BindView(R.id.four_view_pager)
    ViewPager fourViewPager;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fourfragment_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(list.size() == 0){
            list.add(new FourOneFragment());
            list.add(new FourTwoFragment());
            list.add(new FourThreeFragment());
            list.add(new FourFourFragment());
            list.add(new FourFiveFragment());
            FourMyFragmentAdapter fourMyFragmentAdapter = new FourMyFragmentAdapter(getFragmentManager(), list);
            fourViewPager.setAdapter(fourMyFragmentAdapter);
            fourRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.four_rb1:
                            fourViewPager.setCurrentItem(0, false);
                            break;
                        case R.id.four_rb2:
                            fourViewPager.setCurrentItem(1, false);
                            break;
                        case R.id.four_rb3:
                            fourViewPager.setCurrentItem(2, false);
                            break;
                        case R.id.four_rb4:
                            fourViewPager.setCurrentItem(3, false);
                            break;
                        case R.id.four_rb5:
                            fourViewPager.setCurrentItem(4, false);
                            break;
                    }
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
