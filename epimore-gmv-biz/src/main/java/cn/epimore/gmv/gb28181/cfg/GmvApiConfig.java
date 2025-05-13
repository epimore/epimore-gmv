package cn.epimore.gmv.gb28181.cfg;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "gmv.api")
public class GmvApiConfig {
    private String host;
    private String playLive;
    private String playBack;
    private String playBackSeek;
    private String playBackSpeed;
    private String ptz;
    private String download;
    private String teardown;
    private String downing;
    private String rmFile;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPlayLive() {
        return playLive;
    }

    public void setPlayLive(String playLive) {
        this.playLive = playLive;
    }

    public String getPlayBack() {
        return playBack;
    }

    public void setPlayBack(String playBack) {
        this.playBack = playBack;
    }

    public String getPlayBackSeek() {
        return playBackSeek;
    }

    public void setPlayBackSeek(String playBackSeek) {
        this.playBackSeek = playBackSeek;
    }

    public String getPlayBackSpeed() {
        return playBackSpeed;
    }

    public void setPlayBackSpeed(String playBackSpeed) {
        this.playBackSpeed = playBackSpeed;
    }

    public String getPtz() {
        return ptz;
    }

    public void setPtz(String ptz) {
        this.ptz = ptz;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public String getTeardown() {
        return teardown;
    }

    public void setTeardown(String teardown) {
        this.teardown = teardown;
    }

    public String getDowning() {
        return downing;
    }

    public void setDowning(String downing) {
        this.downing = downing;
    }

    public String getRmFile() {
        return rmFile;
    }

    public void setRmFile(String rmFile) {
        this.rmFile = rmFile;
    }
}
