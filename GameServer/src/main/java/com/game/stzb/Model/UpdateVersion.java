package com.game.stzb.Model;

public class UpdateVersion {
    private String softflag;
    private String softname;
    private String versionname;
    private int versioncode;
    private String versioninfo;
    private String adminname;
    private String adminpwd;
    private String downloadurl;
    private int isforce;

    public String getSoftflag() {
        return softflag;
    }

    public UpdateVersion setSoftflag(String mSoftflag) {
        softflag = mSoftflag;
        return this;
    }

    public String getSoftname() {
        return softname;
    }

    public UpdateVersion setSoftname(String mSoftname) {
        softname = mSoftname;
        return this;
    }

    public String getVersionname() {
        return versionname;
    }

    public UpdateVersion setVersionname(String mVersionname) {
        versionname = mVersionname;
        return this;
    }

    public int getVersioncode() {
        return versioncode;
    }

    public UpdateVersion setVersioncode(int mVersioncode) {
        versioncode = mVersioncode;
        return this;
    }

    public String getVersioninfo() {
        return versioninfo;
    }

    public UpdateVersion setVersioninfo(String mVersioninfo) {
        versioninfo = mVersioninfo;
        return this;
    }

    public String getAdminname() {
        return adminname;
    }

    public UpdateVersion setAdminname(String mAdminname) {
        adminname = mAdminname;
        return this;
    }

    public String getAdminpwd() {
        return adminpwd;
    }

    public UpdateVersion setAdminpwd(String mAdminpwd) {
        adminpwd = mAdminpwd;
        return this;
    }

    public String getDownloadurl() {
        return downloadurl;
    }

    public UpdateVersion setDownloadurl(String mDownloadurl) {
        downloadurl = mDownloadurl;
        return this;
    }

    public int getIsforce() {
        return isforce;
    }

    public UpdateVersion setIsforce(int mIsforce) {
        isforce = mIsforce;
        return this;
    }
}
