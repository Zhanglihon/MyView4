package zlh.com.zlh0510xm1.m;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import zlh.com.zlh0510xm1.bean.BannerBean;
import zlh.com.zlh0510xm1.bean.GrzlBean;
import zlh.com.zlh0510xm1.bean.GwcBean;
import zlh.com.zlh0510xm1.bean.LoginBean;
import zlh.com.zlh0510xm1.bean.OneFragmentBean;
import zlh.com.zlh0510xm1.bean.PirticBean;
import zlh.com.zlh0510xm1.bean.QuanBean;
import zlh.com.zlh0510xm1.bean.Result;
import zlh.com.zlh0510xm1.bean.SearchBean;
import zlh.com.zlh0510xm1.bean.ShdzBean;
import zlh.com.zlh0510xm1.bean.Shop;
import zlh.com.zlh0510xm1.bean.Shopp;
import zlh.com.zlh0510xm1.bean.WdqbBean;
import zlh.com.zlh0510xm1.bean.WdzjBean;
import zlh.com.zlh0510xm1.bean.XGwcBean;
import zlh.com.zlh0510xm1.bean.XzshdzBean;

/**
 * Time:${Data}
 * <p>
 * Author:Lenovo
 * <p>
 * Description:写这个类的作用
 */
public interface IRequest {
    //注册
    @FormUrlEncoded
    @POST("user/v1/register")
    Observable<Result> register(@Field("phone")String phone,
                                @Field("pwd")String pwd);
    //登录
    @FormUrlEncoded
    @POST("user/v1/login")
    Observable<Result<LoginBean>> login(@Field("phone")String phone,
                                        @Field("pwd")String pwd);
    //banner
    @GET("commodity/v1/bannerShow")
    Observable<Result<List<BannerBean>>> bannerShow();
    //首页展示
    @GET("commodity/v1/commodityList")
    Observable<Result<OneFragmentBean>> commodityList();
    //详情展示
    @GET("commodity/v1/findCommodityDetailsById")
    Observable<Result<PirticBean>> findCommodityDetailsById(@Header("userId")String userId,
                                                            @Header("sessionId")String sessionId,
                                                            @Query("commodityId")String commodityId);
    //根据关键字搜索
    @GET("commodity/v1/findCommodityByKeyword")
    Observable<Result<List<SearchBean>>> findCommodityByKeyword(@Query("keyword")String keyword,
                                                                @Query("page")String page,
                                                                @Query("count")String count);
    //同步购物车
    @GET("order/verify/v1/syncShoppingCart")
    Observable<Result<List<XGwcBean>>> syncShoppingCart(@Query("data")String data);
    //圈子展示
    @GET("circle/v1/findCircleList")
    Observable<Result<List<QuanBean>>> findCircleList(@Query("page")int page,
                                                      @Query("count")String count);

    //购物车展示
    @GET("order/verify/v1/findShoppingCart")
    Observable<Result<List<GwcBean>>> findShoppingCart(@Header("userId") String userId,
                                                       @Header("sessionId") String sessionId);
    //根据用户ID查询用户信息
    @GET("user/verify/v1/getUserById")
    Observable<Result<GrzlBean>> getUserById(@Header("userId")String userId,
                                             @Header("sessionId")String sessionId);
    //我的足迹
    @GET("commodity/verify/v1/browseList")
    Observable<Result<List<WdzjBean>>> browseList(@Header("userId")String userId,
                                                  @Header("sessionId")String sessionId,
                                                  @Query("page")String page,
                                                  @Query("count")String count);
    //查询用户钱包
    @GET("user/verify/v1/findUserWallet")
    Observable<Result<WdqbBean>> findUserWallet(@Header("userId")String userId,
                                                @Header("sessionId")String sessionId,
                                                @Query("page")String page,
                                                @Query("count")String count);
    //收货地址列表
    @GET("user/verify/v1/receiveAddressList")
    Observable<Result<List<ShdzBean>>> receiveAddressList(@Header("userId")String userId,
                                                          @Header("sessionId")String sessionId);
    //新增收货地址
    @FormUrlEncoded
    @POST("user/verify/v1/addReceiveAddress")
    Observable<Result<XzshdzBean>> addReceiveAddress(@Header("userId") String userId,
                                                     @Header("sessionId") String sessionId,
                                                     @Field("realName")String realName,
                                                     @Field("phone")String phone,
                                                     @Field("address")String address,
                                                     @Field("zipCode")String zipCode);
}
