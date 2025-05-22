package com.tilldawn.Model;

public class CollisionRect {
    float x, y;
    float width, height;
    public CollisionRect(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void update(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean hasIntersect(CollisionRect collisionRect){
        for (float i = this.x; i < this.x + this.width; i++){
            for (float j = this.y; j < this.y + this.height; j++){
                if(Math.abs(i - collisionRect.x) < collisionRect.width && Math.abs(j - collisionRect.y) < collisionRect.height){
                    return true;
                }
            }
        }
        return false;
    }

}
