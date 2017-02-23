package com.mycompany.google.hashcode.exercises.pizza;

import java.util.List;

public class Cache {
    
    public int maxSize;
    public List<RequestDescription> cacheDescriptionList;

    public Cache(int maxSize, List<RequestDescription> cacheDescriptionList) {
        this.maxSize = maxSize;
        this.cacheDescriptionList = cacheDescriptionList;
    }

    
}
