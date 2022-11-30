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

import java.util.ArrayList;

import space.earlygrey.shapedrawer.ShapeDrawer;

public class BilliardGDX extends ApplicationAdapter {
	SpriteBatch batch;
	Texture texture;
	ShapeDrawer drawer;
	OrthographicCamera camera;
	ArrayList<Ball> balls;
	
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

		balls = new ArrayList<>();
		Ball whiteBall = new Ball(40, new Vector2(50, 50), 5, 3, Color.WHITE);
		Ball blackBall = new Ball(40, new Vector2(50, camera.viewportHeight - 50), 5, 3, Color.BLACK);
		Ball blueBall = new Ball(40, new Vector2(camera.viewportWidth - 50, 50), 5, 3, Color.BLUE);
		Ball redBall = new Ball(40, new Vector2(camera.viewportWidth - 50, camera.viewportHeight - 50), 5, 3, Color.RED);

		balls.add(whiteBall);
		balls.add(blackBall);
		balls.add(blueBall);
		balls.add(redBall);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 1, 0, 1);

		camera.update();

		batch.begin();

		for(Ball ball : balls) {
			for (Ball compBall : balls) {
				if (compBall != ball  && getDistance(ball, compBall) <= 0) {
					ball.setXVel(ball.getXVel() + compBall.getXVel());
					ball.setYVel(ball.getYVel() + compBall.getYVel());
				}
			}
			ball.update(drawer, camera.viewportWidth, camera.viewportHeight);
		}

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		texture.dispose();
	}

	private double getDistance(Ball ball1, Ball ball2) {
		return Math.sqrt(
					Math.pow(ball1.getPos().x - ball2.getPos().x, 2)
					+ Math.pow(ball1.getPos().y - ball2.getPos().y, 2)
				)
				- (ball1.getRadius() + ball2.getRadius());
	}
}
