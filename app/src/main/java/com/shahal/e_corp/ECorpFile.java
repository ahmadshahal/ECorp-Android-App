package com.shahal.e_corp;


import com.google.gson.annotations.SerializedName;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ECorpFile {

    @SerializedName("url")
    private String url;
    @SerializedName("filename")
    private String fileName;
    @SerializedName("name")
    private String name;
    @SerializedName("extension")
    private String extension;
    @SerializedName("timestamp")
    private Integer timeStamp;
    @SerializedName("size")
    private long size;

    private Integer image;

    public ECorpFile(String url, String fileName, String name, String extension, Integer timeStamp, long size) {
        this.url = url;
        this.fileName = fileName;
        this.name = name;
        this.extension = extension;
        this.timeStamp = timeStamp;
        this.size = size;
        switch (extension) {
            case "pdf":
                image = R.drawable.pdf;
                break;
            case "docx":
                image = R.drawable.docx;
                break;
            case "exe":
                image = R.drawable.exe;
                break;
            case "iso":
                image = R.drawable.iso;
                break;
            case "java":
                image = R.drawable.java;
                break;
            case "jpeg":
                image = R.drawable.jpeg;
                break;
            case "zip":
                image = R.drawable.zip;
                break;
            case "txt":
                image = R.drawable.txt;
                break;
            default:
                image = R.drawable.folder;
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Integer getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Integer timeStamp) {
        this.timeStamp = timeStamp;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getImage() {
        switch (extension) {
            case "pdf":
                image = R.drawable.pdf;
                break;
            case "docx":
                image = R.drawable.docx;
                break;
            case "exe":
                image = R.drawable.exe;
                break;
            case "iso":
                image = R.drawable.iso;
                break;
            case "java":
                image = R.drawable.java;
                break;
            case "jpeg":
                image = R.drawable.jpeg;
                break;
            case "zip":
                image = R.drawable.zip;
                break;
            case "txt":
                image = R.drawable.txt;
                break;
            default:
                image = R.drawable.folder;
        }
        return image;
    }

    public String nameFormatted() {
        String theName = this.fileName;
        if (theName.length() > 31) {
            theName = theName.substring(0, 29);
            theName += "..";
            return theName;
        } else
            return theName;
    }

    public String sizeFormatted() {
        double theSize = (double) this.size / 1000000;
        if (theSize < 0.001)
            return String.format("%d B", (int) (theSize * 1000000));
        else if (theSize < 1)
            return String.format("%.3g KB", theSize * 1000);
        else if (theSize > 1 && theSize < 1000)
            return String.format("%.3g MB", theSize);
        else
            return String.format("%.2g GB", theSize / 1000);
    }

    public String dateFormatted() {
        if (this.timeStamp == null)
            return "--/--/----";
        int epochDay = this.timeStamp / (24 * 3600); // From seconds to days
        LocalDate modificationDate = LocalDate.ofEpochDay(epochDay);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        return modificationDate.format(formatter);
    }
}