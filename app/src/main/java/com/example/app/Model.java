package com.example.app;

public class Model {

    String cAnswers,uAnswers,Alpha;

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
