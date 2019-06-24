package zlh.com.zlh0510xm1.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Time:${Data}
 * <p>
 * Author:Lenovo
 * <p>
 * Description:写这个类的作用
 */
@Entity
public class LoginBean {
    @Id
    public String userId;
    public String sessionId;
    public String nickName;
    public String phone;
    public String headPic;
    public String sex;
    @Generated(hash = 1932088658)
    public LoginBean(String userId, String sessionId, String nickName, String phone,
            String headPic, String sex) {
        this.userId = userId;
        this.sessionId = sessionId;
        this.nickName = nickName;
        this.phone = phone;
        this.headPic = headPic;
        this.sex = sex;
    }
    @Generated(hash = 1112702939)
    public LoginBean() {
    }
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getSessionId() {
        return this.sessionId;
    }
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    public String getNickName() {
        return this.nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getHeadPic() {
        return this.headPic;
    }
    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
}
