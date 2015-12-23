package com.lin.studytest.spring.food;

public class Rice implements Food {
    
    String name;
    
    public Rice(String name) {
        this.name = name;
    }
    
    public String getFoodName() {
        return name;
    }
}
