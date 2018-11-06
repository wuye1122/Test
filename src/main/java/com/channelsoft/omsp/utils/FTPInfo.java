package com.channelsoft.omsp.utils;

public class FTPInfo {
    /** ftp 的 ip*/
    private String ip;
    /** ftp 的 端口号port*/
    private int port;
    /** 连接ftp 的 用户名*/
    private String userName;
    /** 连接ftp 的 密码*/
    private String password;
    /** 对应平台的英文缩写*/
    private String plateformEnName;
    /** 对应平台的中文名称*/
    private String plateformName;
    /** 文件在ftp上的相对路径*/
    private String remotePath;

    public String getRemotePath() {
        return remotePath;
    }
    public void setRemotePath(String remotePath) {
        this.remotePath = remotePath;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPlateformEnName() {
        return plateformEnName;
    }
    public void setPlateformEnName(String plateformEnName) {
        this.plateformEnName = plateformEnName;
    }
    public String getPlateformName() {
        return plateformName;
    }
    public void setPlateformName(String plateformName) {
        this.plateformName = plateformName;
    }

}
