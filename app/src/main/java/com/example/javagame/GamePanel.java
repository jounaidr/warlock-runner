package com.example.javagame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread; //initialises main thread
    private Random rand = new Random(); //creates random object
    public static int level = 1; //set level to 1

    private Background bg;
    public static int bgMoveSpeed = -5; //set background to move at speed of -5

    public static final int WIDTH = 1280; //width of game
    public static final int HEIGHT = 720; //height of game
    //increasing height and width will give more game space and a larger view of the background, max resolution is 2k (res of bg)
    private Warlock player; //initialise the player

    private ArrayList<Gargant> gargants; //array to store gargant enemies
    private long gargantStartTime;

    private ArrayList<Dragon> dragons; //array to store dragons
    private long dragonStartTime;
    private int dragonHitCount = 0; //hit counter for dragons

    private ArrayList<Demon> demons; //array to store demons
    private long demonStartTime;
    private int demonHitCount = 0; //hit counter for demons

    private ArrayList<LightningBall> bullets; //stores the players bullets
    private ArrayList<HellFire> demonShots; //stores demon bullets

    private float movX; //stores x pos of finger on touch
    private float movY; //stores y pos of finger on touch

    private float posX; //stores x pos of player on touch
    private float posY; //stores y pos of player on touch

    /**
     * setup game panel
     * @param context
     */
    public GamePanel(Context context) {
        super(context);

        getHolder().addCallback(this); //adds callback to surfaceholder to intercept events

        thread = new MainThread(getHolder(), this); //initialise main thread

        setFocusable(true); //set game panel to focusable
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    /**
     * deletes thread if stopped
     * @param holder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        int count = 0;
        while (retry && (count < 1000)) {
            count++;
            try {
                thread.setRunning(false);
                thread.join();
                retry = false;
                thread = null; //deletes thread if stopped
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * initialise all game objects, get start times, and then start game loop
     * @param holder
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        level = 1; //sets level to 1
        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.highway_bg)); //get bg image
        player = new Warlock(BitmapFactory.decodeResource(getResources(), R.drawable.player_move), 64, 64, 8); //get player image
        gargants = new ArrayList<Gargant>(); // initialises gargants array
        dragons = new ArrayList<Dragon>(); // initialises dragons array
        demons = new ArrayList<Demon>(); // initialises demons array
        bullets = new ArrayList<LightningBall>(); // initialises player bullets array
        demonShots = new ArrayList<HellFire>(); // initialises demon bullets array

        gargantStartTime = System.nanoTime();
        dragonStartTime = System.nanoTime();
        demonStartTime = System.nanoTime();

        //start the game loop
        thread.setRunning(true);
        thread.start();
    }

    /**
     * All player motion is contained in this method. Allows user to control
     * player by moving player object parallel to finger strokes
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(player.getPlaying()) { //if player alive
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: //if finger down...
                    bullets.add(new LightningBall(BitmapFactory.decodeResource(getResources(), R.drawable.firebolt), player.getX(), player.getY(), 32, 32)); // shoot bullet
                    //store x,y of finger
                    movX = event.getX();
                    movY = event.getY();
                    //store x,y of player
                    posX = player.getX();
                    posY = player.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    player.setX(posX + (event.getX() - movX)); //set player x,y based on initial player pos, plus the distance finger moves by
                    player.setY(posY + (event.getY() - movY));

                    if ((player.getX() >= WIDTH - 69)) { //right boundry
                        player.setX(WIDTH - 71);
                    } else if ((player.getX() <= 10)) { //left boundry
                        player.setX(11);
                    }
                    if ((player.getY() >= HEIGHT - 59)) { // bottom boundry
                        player.setY(HEIGHT - 61);
                    } else if ((player.getY() <= 1)) { //top boundry
                        player.setY(3);
                    }
                    break;
            }
        }
        return true;
    }

    /**
     * Method to switch to game over activity, used when player dies
     */
    public void gameOver(){
        Intent intent = new Intent(getContext(), GameOver.class); //set intent to game over activity
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        getContext().startActivity(intent); //goes to game over activity
    }

    /**
     * main update method in the game that controls all game movement
     */
    public void update() {
        if (player.getPlaying()) { //if player alive
            bg.update(); //update background
            player.update(); //update player
            //player collision detection, if player hits any baddies or their bullets is playing sets to false
            for (int i = 0; i < gargants.size(); i++) {
                if (collision(gargants.get(i), player)) {
                    //gargants.remove(i);
                    player.setPlaying(false);

                }
                for (int j = 0; j < demons.size(); j++) {
                    if (collision(demons.get(j), player)) {
                        //demons.remove(j);
                        player.setPlaying(false);

                    }
                    for (int k = 0; k < dragons.size(); k++) {
                        if (collision(dragons.get(k), player)) {
                            //dragons.remove(k);
                            player.setPlaying(false);

                        }
                        for (int l = 0; l < demonShots.size(); l++) {
                            if (collision(demonShots.get(l), player)) {
                                //demonShots.remove(l);
                                player.setPlaying(false);

                            }

                        }
                    }
                }
            }

            for (int i = 0; i < bullets.size(); i++) { //for each bullet
                bullets.get(i).update();  //update them
                if (bullets.get(i).getX() > WIDTH + 10) {
                    bullets.remove(i); //if off screen delete
                }
            }

            for (int i = 0; i < demonShots.size(); i++) { // for each demon bullet
                demonShots.get(i).update(); //update them
                if (demonShots.get(i).getX() < -100) {
                    demonShots.remove(i); //if off screen delete
                }
            }

            long gargantElapseTime = (System.nanoTime() - gargantStartTime) / 1000000;
            if (gargantElapseTime > (1200 - player.getScore() / 3)) { //as score increases, more will spawn
                gargants.add(new Gargant(BitmapFactory.decodeResource(getResources(), R.drawable.gargant), WIDTH + 10, (int) (rand.nextDouble() * (HEIGHT)), 64, 64, 8));
                if (gargants.size() >= 13) { //limit amount of this enemy to 13
                    gargants.remove(gargants.size() - 1);
                }
                gargantStartTime = System.nanoTime();
            }
            for (int i = 0; i < gargants.size(); i++) { // for each gargant
                gargants.get(i).update(); //update them
                for (int j = 0; j < bullets.size(); j++) {
                    if (collision(gargants.get(i), bullets.get(j))) {
                        //when shot remove enemy and bullet
                        gargants.remove(i);
                        bullets.remove(j);
                        player.addScore(10); //10 score for these guys
                        break;
                    }
                }
                if (gargants.get(i).getX() < -100) {
                    gargants.remove(i); //if off screen remove
                }
            }

            if (level >= 3) { //only spawn demon above level 3
                long demonElapseTime = (System.nanoTime() - demonStartTime) / 1000000;
                if (demonElapseTime > (12000 - player.getScore() / 3)) { //as score increases, more will spawn
                    demons.add(new Demon(BitmapFactory.decodeResource(getResources(), R.drawable.demon), WIDTH + 10, (int) (rand.nextDouble() * (HEIGHT)), 64, 64, 8));
                    if (demons.size() >= 10) { //limit amount of this enemy to 10
                        demons.remove(demons.size() - 1);
                    }
                    demonStartTime = System.nanoTime();
                }
                for (int i = 0; i < demons.size(); i++) { //for each demon
                    demons.get(i).update(); //update them
                    int n = rand.nextInt(1001); //random int between 1 and 1000
                    if (n > 980) { //if random int > 980 shoot demon bullet
                        demonShots.add(new HellFire(BitmapFactory.decodeResource(getResources(), R.drawable.hellfire_0), demons.get(i).getX(), demons.get(i).getY(), 64, 64));
                    }
                    for (int j = 0; j < bullets.size(); j++) {
                        if (collision(demons.get(i), bullets.get(j))) {
                            demonHitCount++; //each hit with bullet increases hitcount
                            bullets.remove(j);
                            if (demonHitCount >= 10) { //when hit more than 10 times it dies
                                demonHitCount = 0;
                                demons.remove(i);
                                bullets.remove(j);
                                player.addScore(100); //100 score for these big bois
                            }
                        }
                    }
                    if (demons.get(i).getX() < -100) {
                        demons.remove(i); //if off screen delete
                    }
                }
            }


            if (level >= 2) { //only spawn dragon above level 3
                long dragonElapseTime = (System.nanoTime() - dragonStartTime) / 1000000;
                if (dragonElapseTime > (6000 - player.getScore() / 3)) { //as score increases, more will spawn
                    dragons.add(new Dragon(BitmapFactory.decodeResource(getResources(), R.drawable.dragon), WIDTH + 10, (int) (rand.nextDouble() * (HEIGHT)), 128, 128, 8));
                    if (dragons.size() >= 5) { //dragons limitted to only 5
                        dragons.remove(dragons.size() - 1);
                    }
                    dragonStartTime = System.nanoTime();
                }
                for (int i = 0; i < dragons.size(); i++) {
                    dragons.get(i).update();
                    for (int j = 0; j < bullets.size(); j++) {
                        if (collision(dragons.get(i), bullets.get(j))) {
                            dragonHitCount++; //if hit increase hitcount
                            bullets.remove(j);
                            if (dragonHitCount >= 4) { //when hit 4 times it dies
                                dragonHitCount = 0;
                                dragons.remove(i);
                                bullets.remove(j);
                                player.addScore(25); //25 score for dragons
                            }
                            break;
                        }
                    }
                    if (dragons.get(i).getX() < -100) {
                        dragons.remove(i); //when off screen remove
                    }
                }
            }
        }
    }

    /**
     * checks if two objects collide, if so return true
     * @param a
     * @param b
     * @return
     */
    public boolean collision(Object a, Object b) {
        if (Rect.intersects(a.getRect(), b.getRect())) { //if the two objects hitboxes colide
            return true;
        }
        return false;
    }

    /**
     * Draws all text to screen, and goes to gameover activity once player is dead
     * @param canvas
     */
    public void drawText(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.rgb(240, 230, 140)); //gold color
        paint.setTextSize(30);
        paint.setTypeface(Typeface.SANS_SERIF);

        canvas.drawText("SCORE: " + player.getScore(), WIDTH - 250, HEIGHT - 10, paint); //display player score
        canvas.drawText("LEVEL: " + level, 100, HEIGHT - 10, paint); //display level

        if(!player.getPlaying()){ //if player is dead
            paint.setColor(Color.rgb(102, 0, 0));
            paint.setTextSize(50);
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            canvas.drawText("OH DEAR, YOU ARE DEAD!", WIDTH/2 - 320, HEIGHT/2, paint); //print death message
            gameOver();
        }
    }

    /**
     * Draws everything in sequence
     * @param canvas
     */
    @SuppressLint("MissingSuperCall")
    @Override
    public void draw(Canvas canvas) {
        final float scaleX = getWidth() / (WIDTH * 1.f); //set scale factor of bg image and screen area
        final float scaleY = getHeight() / (HEIGHT * 1.f);
        if (canvas != null) {
            final int savedState = canvas.save();
            canvas.scale(scaleX, scaleY); //scale bg
            //draw order means that demons walk over gargants, demon shots go over all ground troops and under dragon.
            //also means dragons fly over all enemies cus, ya know, they fly
            bg.draw(canvas); //draw bg
            player.draw(canvas); //draw player
            for (LightningBall b : bullets) {
                b.draw(canvas); //draw each player bullet
            }
            for (Gargant g : gargants) {
                g.draw(canvas); //draw each gargant
            }
            for (Demon de : demons) {
                de.draw(canvas); //draw each demon
            }
            for (HellFire ds : demonShots) {
                ds.draw(canvas); //draw each demon shot
            }
            for (Dragon d : dragons) {
                d.draw(canvas); //draw each dragon
            }

            drawText(canvas); //draw the text on screen
            canvas.restoreToCount(savedState);
        }
    }
}

