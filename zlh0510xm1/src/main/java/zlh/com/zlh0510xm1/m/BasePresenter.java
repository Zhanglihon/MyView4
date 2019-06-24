package zlh.com.zlh0510xm1.m;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import zlh.com.zlh0510xm1.bean.Result;
import zlh.com.zlh0510xm1.core.ShowCall;
import zlh.com.zlh0510xm1.m.IRequest;
import zlh.com.zlh0510xm1.util.OkHttpUtil;

/**
 * Time:${Data}
 * <p>
 * Author:Lenovo
 * <p>
 * Description:写这个类的作用
 */
public abstract class BasePresenter {
    ShowCall showCall;

    public BasePresenter(ShowCall showCall) {
        this.showCall = showCall;
    }
    public void requestData(Object...args){
        IRequest iRequest=OkHttpUtil.getIntance().getRetrofit().create(IRequest.class);
        getModel(iRequest,args)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Result>() {
                    @Override
                    public void accept(Result o) throws Exception {
                        if (o.status.equals("0000")){
                            showCall.success(o.result);
                        }
                    }
                });
    }
    protected abstract Observable getModel(IRequest iRequest,Object...args);
}
