package cn.epimore.gmv.cons;

/**
 * API 路径常量
 */
public final class ApiPathConstants {

    // 禁止实例化
    private ApiPathConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    // 直播
    public static final String PLAY_LIVE = "/api/play/live/stream";

    // 回放
    public static final String PLAY_BACK = "/api/play/back/stream";
    public static final String PLAY_BACK_SEEK = "/api/play/back/seek";
    public static final String PLAY_BACK_SPEED = "/api/play/back/speed";

    // 云台控制
    public static final String PTZ = "/api/control/ptz";

    // 下载
    public static final String DOWNLOAD = "/api/download/mp4";
    public static final String TEARDOWN = "/api/download/stop";
    public static final String DOWNING = "/api/downing/info";

    // 文件操作
    public static final String RM_FILE = "/api/rm/file";

    // 截图
    public static final String SNAPSHOT_IMAGE = "/edge/snapshot/image";
}
