package cn.epimore.gmv.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "AlarmInfo", description = "告警信息")
public class AlarmInfo {
    @ApiModelProperty("告警等级")
    private int priority;
    @ApiModelProperty("告警方式")
    private int method;
    private String methodStr;
    @ApiModelProperty("告警类型")
    private int alarmType;
    private String alarmTypeStr;
    @ApiModelProperty("告警时间")
    private String timeStr;
    @ApiModelProperty("告警设备")
    private String deviceId;
    @ApiModelProperty("告警通道")
    private String channelId;

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public int getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(int alarmType) {
        this.alarmType = alarmType;
    }

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    //,取值1为电话报警,2为设备报警,3为短信报警,4为GPS报警,5为
//视频报警,6为设备故障报警,7其他报警
    public String getMethodStr() {
        String methodStr = "未知报警";
        switch (method) {
            case 1:
                methodStr = "电话报警";
                break;
            case 2:
                methodStr = "设备报警";
                break;
            case 3:
                methodStr = "短信报警";
                break;
            case 4:
                methodStr = "GPS报警";
                break;
            case 5:
                methodStr = "视频报警";
                break;
            case 6:
                methodStr = "设备故障报警";
                break;
            case 7:
                methodStr = "其他报警";
                break;
        }
        return methodStr;
    }

    //报警方式为2时,不携带AlarmType为默认的报警设备报警,携
//带AlarmType取值及对应报警类型如下:1-视频丢失报警;2-设备防拆报警;3-存储
//设备磁盘满报警;4-设备高温报警;5-设备低温报警。报警方式为5时,取值如下:1
//人工视频报警;2-运动目标检测报警;3-遗留物检测报警;4-物体移除检测报警;5-绊
    //线检测报警;6-入侵检测报警;7-逆行检测报警;8-徘徊检测报警;9-流量统计报警;
// 10-密度检测报警;11-视频异常检测报警;12-快速移动报警。报警方式为6时,取值
//如下:1-存储设备磁盘故障报警;2-存储设备风扇故障报警。
    public String getAlarmTypeStr() {
        String alarmTypeStr = "未知报警";
        if (method == 2) {
            switch (alarmType) {
                case 1:
                    alarmTypeStr = "视频丢失报警";
                    break;
                case 2:
                    alarmTypeStr = "设备防拆报警";
                    break;
                case 3:
                    alarmTypeStr = "存储设备磁盘满报警";
                    break;
                case 4:
                    alarmTypeStr = "设备高温报警";
                    break;
                case 5:
                    alarmTypeStr = "设备低温报警";
                    break;
                default:
                    alarmTypeStr = "设备告警";
                    break;
            }
        } else if (method == 5) {
            switch (alarmType) {
                case 1:
                    alarmTypeStr = "人工视频报警";
                    break;
                case 2:
                    alarmTypeStr = "运动目标检测报警";
                    break;
                case 3:
                    alarmTypeStr = "遗留物检测报警";
                    break;
                case 4:
                    alarmTypeStr = "物体移除检测报警";
                    break;
                case 5:
                    alarmTypeStr = "绊线检测报警";
                    break;
                case 6:
                    alarmTypeStr = "入侵检测报警";
                    break;
                case 7:
                    alarmTypeStr = "逆行检测报警";
                    break;
                case 8:
                    alarmTypeStr = "徘徊检测报警";
                    break;
                case 9:
                    alarmTypeStr = "流量统计报警";
                    break;
                case 10:
                    alarmTypeStr = "密度检测报警";
                    break;
                case 11:
                    alarmTypeStr = "视频异常检测报警";
                    break;
                case 12:
                    alarmTypeStr = "快速移动报警";
                    break;
            }
        } else if (method == 6) {            //报警方式为6时,取值如下:1-存储设备磁盘故障报警;2-存储设备风扇故障报警。
            switch (alarmType) {
                case 1:
                    alarmTypeStr = "存储设备磁盘故障报警";
                    break;
                case 2:
                    alarmTypeStr = "存储设备风扇故障报警";
                    break;
            }
        }
        return alarmTypeStr;
    }
}