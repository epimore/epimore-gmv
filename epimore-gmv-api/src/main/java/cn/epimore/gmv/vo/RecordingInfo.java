package cn.epimore.gmv.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecordingInfo {
    @JsonProperty("file_name")
    private String fileName;
    @JsonProperty("file_size")
    private Long fileSize;
    private long timestamp;
//    @JsonProperty("bytes_sec")
//    private Integer bytesSec;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

//    public Integer getBytesSec() {
//        return bytesSec;
//    }
//
//    public void setBytesSec(Integer bytesSec) {
//        this.bytesSec = bytesSec;
//    }
}
