package com.channelsoft.omsp.utils;

public class FTPInfo {
    /** ftp �� ip*/
    private String ip;
    /** ftp �� �˿ں�port*/
    private int port;
    /** ����ftp �� �û���*/
    private String userName;
    /** ����ftp �� ����*/
    private String password;
    /** ��Ӧƽ̨��Ӣ����д*/
    private String plateformEnName;
    /** ��Ӧƽ̨����������*/
    private String plateformName;
    /** �ļ���ftp�ϵ����·��*/
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
