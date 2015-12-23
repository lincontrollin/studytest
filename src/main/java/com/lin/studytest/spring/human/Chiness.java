package com.lin.studytest.spring.human;

import com.lin.studytest.spring.food.Food;

public class Chiness implements Human{
    
    Food food;
    
    public void speak() {
        System.out.println("中国人");
    }
    
    public void eat() {
        System.out.println("中国人吃"+food.getFoodName());
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
    
    

}
