package com.dima.test.dut;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    @JsonProperty("full_name")
    private String name;
    @JsonProperty("description")
    private String desc;
    @JsonProperty("language")
    private String mainLang;
    @JsonProperty("stargazers_count")
    private String stars;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getMainLang() {
        return mainLang;
    }

    public void setMainLang(String mainLang) {
        this.mainLang = mainLang;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }
}