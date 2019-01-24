package com.application.fivnews.data.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewspaperInfo {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("legalName")
    @Expose
    private Object legalName;
    @SerializedName("domain")
    @Expose
    private String domain;
    @SerializedName("domainAliases")
    @Expose
    private List<Object> domainAliases = null;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("foundedYear")
    @Expose
    private Integer foundedYear;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("timeZone")
    @Expose
    private String timeZone;
    @SerializedName("utcOffset")
    @Expose
    private Integer utcOffset;

    @SerializedName("logo")
    @Expose
    private String logo;

    @SerializedName("emailProvider")
    @Expose
    private Boolean emailProvider;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("ticker")
    @Expose
    private Object ticker;
    @SerializedName("phone")
    @Expose
    private Object phone;
    @SerializedName("indexedAt")
    @Expose
    private String indexedAt;
    @SerializedName("tech")
    @Expose
    private List<String> tech = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getLegalName() {
        return legalName;
    }

    public void setLegalName(Object legalName) {
        this.legalName = legalName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public List<Object> getDomainAliases() {
        return domainAliases;
    }

    public void setDomainAliases(List<Object> domainAliases) {
        this.domainAliases = domainAliases;
    }


    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFoundedYear() {
        return foundedYear;
    }

    public void setFoundedYear(Integer foundedYear) {
        this.foundedYear = foundedYear;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Integer getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(Integer utcOffset) {
        this.utcOffset = utcOffset;
    }


    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Boolean getEmailProvider() {
        return emailProvider;
    }

    public void setEmailProvider(Boolean emailProvider) {
        this.emailProvider = emailProvider;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getTicker() {
        return ticker;
    }

    public void setTicker(Object ticker) {
        this.ticker = ticker;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }


    public String getIndexedAt() {
        return indexedAt;
    }

    public void setIndexedAt(String indexedAt) {
        this.indexedAt = indexedAt;
    }

    public List<String> getTech() {
        return tech;
    }

    public void setTech(List<String> tech) {
        this.tech = tech;
    }


}