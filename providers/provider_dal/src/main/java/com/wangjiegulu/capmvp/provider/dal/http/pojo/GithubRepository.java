package com.wangjiegulu.capmvp.provider.dal.http.pojo;

import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 23/03/2018.
 */
public class GithubRepository implements Parcelable {
    private Long id;
    private String name;
    @SerializedName("full_name")
    private String fullName;
    @SerializedName("html_url")
    private String htmlUrl;
    private Boolean fork;
    private Integer size;
    @SerializedName("stargazers_count")
    private Integer stargazersCount;
    private String language;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public Boolean getFork() {
        return fork;
    }

    public void setFork(Boolean fork) {
        this.fork = fork;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(Integer stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.fullName);
        dest.writeString(this.htmlUrl);
        dest.writeValue(this.fork);
        dest.writeValue(this.size);
        dest.writeValue(this.stargazersCount);
        dest.writeString(this.language);
    }

    public GithubRepository() {
    }

    protected GithubRepository(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.fullName = in.readString();
        this.htmlUrl = in.readString();
        this.fork = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.size = (Integer) in.readValue(Integer.class.getClassLoader());
        this.stargazersCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.language = in.readString();
    }

    public static final Parcelable.Creator<GithubRepository> CREATOR = new Parcelable.Creator<GithubRepository>() {
        @Override
        public GithubRepository createFromParcel(Parcel source) {
            return new GithubRepository(source);
        }

        @Override
        public GithubRepository[] newArray(int size) {
            return new GithubRepository[size];
        }
    };
}
