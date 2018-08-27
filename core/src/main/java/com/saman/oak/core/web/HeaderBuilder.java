package com.saman.oak.core.web;

import com.saman.oak.core.utils.StringUtils;
import org.springframework.http.HttpHeaders;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class HeaderBuilder {

    HttpHeaders header = new HttpHeaders();
    private String fileExtension = StringUtils.EMPTY;

    public HeaderBuilder() {
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public HeaderBuilder setJson() {
        header.set("Content-Type", "application/json; charset=utf-8");
        return this;
    }

    public HeaderBuilder setPdf() {
        header.set("Content-Type", "application/pdf; charset=utf-8");
        setFileExtension(".pdf");
        return this;
    }

    public HeaderBuilder setImageJpeg() {
        header.set("Content-Type", "image/jpeg; charset=utf-8");
        setFileExtension(".jpeg");
        return this;
    }

    public HeaderBuilder setAttachment(String fileName) throws UnsupportedEncodingException {
        header.set("Content-Disposition", "attachment; filename*='UTF-8" + URLEncoder.encode(fileName, "utf-8") + getFileExtension());
        return this;
    }

    public HeaderBuilder setInline(String fileName) throws UnsupportedEncodingException {
        header.set("Content-Disposition", "inline; filename*='UTF-8" + URLEncoder.encode(fileName, "utf-8") + getFileExtension());
        return this;
    }

    public HttpHeaders build() {
        return header;
    }
}
