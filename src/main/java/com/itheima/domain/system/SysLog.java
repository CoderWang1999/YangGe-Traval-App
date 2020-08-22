package com.itheima.domain.system;

import java.io.Serializable;
import java.util.Date;

public class SysLog  implements Serializable {

    private String id;  // 日志id
    private Date visitTime; // 访问的时间
    private String visitTimeStr;
    private String username;   // 访问的用户名称
    private String ip;     //访问的ip地址
    private String url;   //访问的资源路径
    private Long executionTime;  //运行时间
    private String method;   //方法名称

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getVisitTimeStr() {
        return visitTimeStr;
    }

    public void setVisitTimeStr(String visitTimeStr) {
        this.visitTimeStr = visitTimeStr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Long executionTime) {
        this.executionTime = executionTime;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "SysLog{" +
                "id='" + id + '\'' +
                ", visitTime=" + visitTime +
                ", visitTimeStr='" + visitTimeStr + '\'' +
                ", username='" + username + '\'' +
                ", ip='" + ip + '\'' +
                ", url='" + url + '\'' +
                ", executionTime=" + executionTime +
                ", method='" + method + '\'' +
                '}';
    }
}
