package com.example.path.model;

import java.time.LocalTime;

public abstract class Transport {
    protected String type;
    protected String name;
    protected String source;
    protected String dest;

    public Transport(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public abstract String getName();

    public abstract String getDest() ;

    public abstract String getSource() ;


    public abstract void setName(String name);

    public abstract void setSource(String source) ;

    public abstract void setDest(String dest) ;

    public abstract int getDuration();
}
