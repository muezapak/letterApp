package com.example.app;

public class Model {

    String cAnswers,uAnswers,Alpha,id;

    public Model(String cAnswers, String uAnswers, String alpha, String id) {
        this.cAnswers = cAnswers;
        this.uAnswers = uAnswers;
        Alpha = alpha;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Model(String cAnswers, String uAnswers, String alpha) {
        this.cAnswers = cAnswers;
        this.uAnswers = uAnswers;
        Alpha = alpha;
    }

    public String getcAnswers() {
        return cAnswers;
    }

    public void setcAnswers(String cAnswers) {
        this.cAnswers = cAnswers;
    }

    public String getuAnswers() {
        return uAnswers;
    }

    public void setuAnswers(String uAnswers) {
        this.uAnswers = uAnswers;
    }

    public String getAlpha() {
        return Alpha;
    }

    public void setAlpha(String alpha) {
        Alpha = alpha;
    }
}
