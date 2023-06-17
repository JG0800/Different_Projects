package main;

import entity.Player;
import objects.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //Screen settings
    final int originalTileSize = 32;
    final int scale = 2;
    public final int tileSize = originalTileSize*scale;
    public int maxScreenCol = 16;
    public int maxScreenRow = 12;
    public int screenWidth = tileSize*maxScreenCol;
    public int screenHeight =  tileSize*maxScreenRow;

    //World Settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread; //Threads??
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];
    TileManager tileM = new TileManager(this);



    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH); //keyListener wird von der Klasse KeyHandler hinzugefügt
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObject();
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null){

            long currentTime = System.nanoTime();

                    //updates information like Player position
            player.update();
            tileM.tileUpdate();
            //repaints the screen
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;


                if (remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
                nextDrawTime += drawInterval;
            }

        }
    }

    public void paintComponent(Graphics g){ //Graphics g??
        super.paintComponent(g); //??
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        for( int i = 0; i < obj.length; i++){
            if(obj[i]!= null){
                obj[i].draw(g2, this);
            }
        }
        player.draw(g2);
    }
}
