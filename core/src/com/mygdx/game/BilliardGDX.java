package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class BilliardGDX extends ApplicationAdapter {
	SpriteBatch batch;
	Texture texture;
	ShapeDrawer drawer;
	OrthographicCamera camera;
	Ball ball;
	
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

		ball = new Ball(40, new Vector2(50, 50), 5, 3, Color.WHITE);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 1, 0, 1);

		camera.update();

		batch.begin();

		ball.update(drawer, camera.viewportWidth, camera.viewportHeight);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		texture.dispose();
	}
}
