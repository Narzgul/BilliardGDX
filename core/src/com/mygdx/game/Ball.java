package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class Ball {
    private final float radius;
    private final Vector2 pos;
    private int xVel, yVel;
    private final Color color;

    public Ball(float radius, Vector2 pos, int xVel, int yVel, Color color) {
        this.radius = radius;
        this.pos = pos;
        this.xVel = xVel;
        this.yVel = yVel;
        this.color = color;
    }

    public void update(ShapeDrawer drawer, float width, float height) {
        if(pos.x >= width - radius || pos.x <= 0 + radius) xVel *= -1;
        if(pos.y >= height - radius || pos.y <= 0 + radius) yVel *= -1;

        drawer.setColor(color);
        drawer.filledCircle(pos.x, pos.y, radius);
        pos.x += xVel;
        pos.y += yVel;
    }

    public float getRadius() {
        return radius;
    }

    public int getXVel() {
        return xVel;
    }

    public int getYVel() {
        return yVel;
    }

    public Vector2 getPos() {
        return pos;
    }

    public void setXVel(int xVel) {
        this.xVel = xVel;
    }

    public void setYVel(int yVel) {
        this.yVel = yVel;
    }
}
