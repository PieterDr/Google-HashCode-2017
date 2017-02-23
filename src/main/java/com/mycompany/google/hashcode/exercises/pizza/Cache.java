package com.mycompany.google.hashcode.exercises.pizza;

import java.util.List;

public class Cache {
    
    public int maxSize;
    public List<CacheDescription> cacheDescriptionList;

    public Cache(int maxSize, List<CacheDescription> cacheDescriptionList) {
        this.maxSize = maxSize;
        this.cacheDescriptionList = cacheDescriptionList;
    }

    
}
