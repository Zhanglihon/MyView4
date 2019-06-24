package zlh.com.zlh0510xm1.bean;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

/**
 * Time:${Data}
 * <p>
 * Author:Lenovo
 * <p>
 * Description:写这个类的作用
 */
public class Bannerr extends SimpleBannerInfo {
    public String imageUrl;

    @Override
    public Object getXBannerUrl() {
        return imageUrl;
    }
}
