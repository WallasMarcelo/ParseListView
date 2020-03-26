package com.studio.parselistview;

public class Paises {

    private String name;
    private String capital;
    private String region;
    private String flag;

    public Paises(){

    }

    public Paises(String name, String capital, String region, String flag) {
        this.name = name;
        this.capital = capital;
        this.region = region;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
