package zlh.com.zlh0510xm1.p;

import io.reactivex.Observable;
import zlh.com.zlh0510xm1.core.ShowCall;
import zlh.com.zlh0510xm1.m.BasePresenter;
import zlh.com.zlh0510xm1.m.IRequest;

/**
 * Time:${Data}
 * <p>
 * Author:Lenovo
 * <p>
 * Description:写这个类的作用
 */
public class BannerPresenter extends BasePresenter {
    public BannerPresenter(ShowCall showCall) {
        super(showCall);
    }

    @Override
    protected Observable getModel(IRequest iRequest, Object... args) {
        return iRequest.bannerShow();
    }
}
