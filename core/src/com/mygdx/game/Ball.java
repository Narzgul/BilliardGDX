package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class Ball {
    private float radius;
    private Vector2 pos;
    private int xVel = 5, yVel = 3;
    private Color color;

    public Ball(float radius, Vector2 pos, int xVel, int yVel, Color color) {
        this.radius = radius;
        this.pos = pos;
        this.xVel = xVel;
        this.yVel = yVel;
        this.color = color;
    }

    public void update(ShapeDrawer drawer, float width, float height) {
        if(pos.x >= width - 40 || pos.x <= 0 + 40) xVel *= -1;
        if(pos.y >= height - 40 || pos.y <= 0 + 40) yVel *= -1;

        drawer.setColor(color);
        drawer.filledCircle(pos.x, pos.y, 40);
        pos.x += xVel;
        pos.y += yVel;
    }
}
