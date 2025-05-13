package cn.epimore.gmv.vo;

import javax.validation.constraints.NotBlank;

public class UserInfo {
    @NotBlank
    private String account;
    @NotBlank
    private String pwd;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
