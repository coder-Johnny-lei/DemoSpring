package com.sdjz.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取项目相关配置
 * 
 * @author ruoyi
 */
@Component
@ConfigurationProperties(prefix = "dms")
public class DMSConfig
{
    /** 项目名称 */
    private String name;

    /** 版本 */
    private String version;

    /** 版权年份 */
    private String copyrightYear;

    /** 实例演示开关 */
    private boolean demoEnabled;

    /** 上传路径 */
    private static String profile;

    /** 获取地址开关 */
    private static boolean addressEnabled;

    /** 验证码类型 */
    private static String captchaType;

    /** 临时解压文件*/
    private  String tempUnzipFile;

    /** sql备份路径*/
    private  String sqlBackupPath;

    /** 文件备份路径*/
    private  String fileBackupPath;

    /** ES备份路径*/
    private String esBacupPath;

    /**封存文件根路径*/
    private static String sealRootPath;

    public void setSealRootPath(String sealRootPath){
        DMSConfig.sealRootPath = sealRootPath;
    }
    public static String getSealRootPath(){
        return sealRootPath;
    }

    public String getEsBacupPath() {
        return esBacupPath;
    }

    public void setEsBacupPath(String esBacupPath) {
        this.esBacupPath = esBacupPath;
    }

    public String getTempUnzipFile() {
        return tempUnzipFile;
    }

    public void setTempUnzipFile(String tempUnzipFile) {
        this.tempUnzipFile = tempUnzipFile;
    }

    public String getSqlBackupPath() {
        return sqlBackupPath;
    }

    public void setSqlBackupPath(String sqlBackupPath) {
        this.sqlBackupPath = sqlBackupPath;
    }

    public String getFileBackupPath() {
        return fileBackupPath;
    }

    public void setFileBackupPath(String fileBackupPath) {
        this.fileBackupPath = fileBackupPath;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public String getCopyrightYear()
    {
        return copyrightYear;
    }

    public void setCopyrightYear(String copyrightYear)
    {
        this.copyrightYear = copyrightYear;
    }

    public boolean isDemoEnabled()
    {
        return demoEnabled;
    }

    public void setDemoEnabled(boolean demoEnabled)
    {
        this.demoEnabled = demoEnabled;
    }

    public static String getProfile()
    {
        return profile;
    }

    public void setProfile(String profile)
    {
        DMSConfig.profile = profile;
    }

    public static boolean isAddressEnabled()
    {
        return addressEnabled;
    }

    public void setAddressEnabled(boolean addressEnabled)
    {
        DMSConfig.addressEnabled = addressEnabled;
    }

    public static String getCaptchaType() {
        return captchaType;
    }

    public void setCaptchaType(String captchaType) {
        DMSConfig.captchaType = captchaType;
    }

    /**
     * 获取导入上传路径
     */
    public static String getImportPath()
    {
        return getProfile() + "/import";
    }

    /**
     * 获取头像上传路径
     */
    public static String getAvatarPath()
    {
        return getProfile() + "/avatar";
    }

    /**
     * 获取下载路径
     */
    public static String getDownloadPath()
    {
        return getProfile() + "/download/";
    }

    /**
     * 获取上传路径
     */
    public static String getUploadPath()
    {
        return getProfile() + "/upload/";
    }

    /**
     * 获取素材上传路径
     */
    public static String getMaterialPath(){
        return getProfile() + "\\material\\";
    }

    /**
     * 获取模板上传路径
     */
    public static String getTemplatePath(){ return getProfile() + "\\template\\"; }

    /**
     * 获取文档路径
     */
    public static String getDocPath(){  return getProfile() + "\\doc\\"; }

    /**
     * 获取文档编辑路径
     */
    public static String getDocEditPath(){
        return getProfile() + "\\docEdit\\";
    }

    /**
     * 获取封存文档临时根目录
     */
    public static String getSealRootTempPath(){return getSealRootPath() + "\\temp\\";}

    /**
     * 获取封存文档zip根目录
     */
    public static String getSealRootZipPath(){return getSealRootPath() + "\\zip\\";}
}
