package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class BilliardGDX extends ApplicationAdapter {
	SpriteBatch batch;
	Texture texture;
	ShapeDrawer drawer;
	OrthographicCamera camera;
	int xVel = 5;
	int xPos = 50;
	int yVel = 3;
	int yPos = 50;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		camera = new OrthographicCamera();
		camera.setToOrtho(true);
		System.out.println(camera.viewportWidth);

		Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.drawPixel(0, 0);
		texture = new Texture(pixmap); //remember to dispose of later
		pixmap.dispose();
		TextureRegion region = new TextureRegion(texture, 0, 0, 1, 1);

		drawer = new ShapeDrawer(batch, region);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 1, 0, 1);

		camera.update();

		batch.begin();

		if(xPos >= camera.viewportWidth - 40 || xPos <= 0 + 40) {
			xVel *= -1;
			System.out.println("Bounce!");
		}
		if(yPos >= camera.viewportHeight - 40 || yPos <= 0 + 40) {
			yVel *= -1;
			System.out.println("Bounce!");
		}

		drawer.filledCircle(xPos, yPos, 40);
		xPos += xVel;
		yPos += yVel;

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		texture.dispose();
	}
}
