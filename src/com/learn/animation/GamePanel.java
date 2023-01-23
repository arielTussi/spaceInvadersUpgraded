package com.learn.animation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JFrame implements MouseListener, KeyListener {

    // static vars
    // for the current frame size
    static int WIDTH;
    static int HEIGHT;

    // for the maximum screen size/ resolution of the computer screen
    static int MAX_WIDTH;
    static int MAX_HEIGHT;

    // game objects
    Background background1, background2, background3;
    PlayersPlane playerPlane;
    PlayerBomb playerBomb;
    Explosion exp1 , exp2;

    ArrayList<EnemyPlane> enemyArr = new ArrayList<>();

    ArrayList<EnemyBomb> bombs = new ArrayList<>();
    ArrayList<AnimatedObject> animatedObjects = new ArrayList<>();
    int playerLIfe = 3;
    int counter = 0;
    int level = 1;
    int amountOfPlansInLevel = 3;
    GameLogic gameLogic;
    JLabel scoreLabel;
    JLabel gameOver;

    JPanel panel;



    public GamePanel() {
        setTitle("Game");
        // get current screen resolution from system

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();

        setSize(500, 650);
        setResizable(false);
        setLayout(null);

        // center the frame
        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        scoreLabel = new JLabel("LIFE:"+playerLIfe);
        scoreLabel.setForeground(Color.YELLOW);
        scoreLabel.setFont(scoreLabel.getFont().deriveFont(24.0f));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(scoreLabel);

        gameOver = new JLabel("GAME OVER");
        gameOver.setForeground(Color.RED);
        gameOver.setFont(scoreLabel.getFont().deriveFont(40f));
        gameOver.setHorizontalAlignment(SwingConstants.CENTER);
        add(gameOver);

        addMouseListener(this);
        addKeyListener(this);

        // get frame size - x, y
        Rectangle frameSize = getBounds();
        WIDTH = (int) frameSize.getWidth();
        HEIGHT = (int) frameSize.getHeight();

        // this is the screen panel
        panel = new JPanel();
        panel.setBounds(0, 0, WIDTH, HEIGHT);
        panel.setLayout(null);
        add(panel);


        // and this is for screen size
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        MAX_WIDTH = (int) screen.getWidth();
        MAX_HEIGHT = (int) screen.getHeight();

        /* **********************************************************************
        Game objects creation
*/

        // create player's plane
        ImageIcon playerPicPlane = new ImageIcon("./images/MYF35.png");
        playerPlane = new PlayersPlane(playerPicPlane, 1);
        animatedObjects.add(playerPlane);
        playerPlane.setY(GamePanel.HEIGHT - (playerPlane.height * 2));
        playerPlane.setX(200);

        ImageIcon expPic = new ImageIcon("./images/exp.png");
        exp2 = new Explosion(expPic, 20, 5);
        exp2.animation.setFromSprite(0);
        exp2.animation.setToSprite(20);
        animatedObjects.add(exp2);


        ImageIcon playerBombPic = new ImageIcon("./images/bomb.png");
        playerBomb = new PlayerBomb(playerBombPic, 1);
        animatedObjects.add(playerBomb);
        playerBomb.setY(-100);

        // set player's life text label
        scoreLabel.setBounds(-80,0,300,100);
        panel.add(scoreLabel);

//        if (level==1)
        // first level:
        firstLevel();


        createBackground(level);


        gameLogic = new GameLogic(this);

        /* **********************************************************************
        other objects of the game - game loop, game logics, background music ....
         * **********************************************************************/

        GameLoop gameLoop = new GameLoop(this);
        gameLoop.setRunning(true);
        gameLoop.start();

        setVisible(true);
    }

    public void createBackground(int level)
    {
        // create background objects
        ImageIcon backgroundPic;
        if (level==3)
            backgroundPic = new ImageIcon("./images/nightSky.jpg");
        else
            backgroundPic = new ImageIcon("./images/sky2.jpg");
        background1 = new Background(backgroundPic, 1);
        animatedObjects.add(background1);
        background1.setY(0);

        background2 = new Background(backgroundPic, 1);
        animatedObjects.add(background2);
        background2.setY(-GamePanel.HEIGHT);
    }
    public void deleteBackground() {
        panel.remove(background1);
        panel.remove(background2);
        animatedObjects.remove(background1);
        animatedObjects.remove(background2);
    }

    public void deleteAllEnemies()
    {
        int amount = enemyArr.size();
        for (int i = 0; i < amount; i++) {
            panel.remove(enemyArr.get(i));
            animatedObjects.remove(enemyArr.get(i));
        }
        enemyArr.clear();

    }

    public void createBombs( ImageIcon bombPic , int amountOfPlanes) {
        for (int i = 0; i < amountOfPlanes; i++) {
            bombs.add(new EnemyBomb(bombPic, 1));
            animatedObjects.add(bombs.get(i));
            bombs.get(i).setY(-bombs.get(0).height);
        }
    }

    public void createEnemies( ImageIcon enemyPicPlane , int amountOfPlanes) {
        for (int i = 0; i < amountOfPlanes; i++) {
            enemyArr.add(new EnemyPlane(enemyPicPlane, 1));
            animatedObjects.add(enemyArr.get(i));
            enemyArr.get(i).setX((GamePanel.WIDTH / 3) * i);
        }
    }

    public void firstLevel() {
        final int amountOfPlanes = 3;
        // create enemy's bombs
        ImageIcon bombPic = new ImageIcon("./images/enemyBomb.png");
        createBombs(bombPic,amountOfPlanes);

        // create enemy's planes
        ImageIcon enemyPicPlane = new ImageIcon("./images/F22.png");
        createEnemies(enemyPicPlane,amountOfPlanes);
    }

    public void secondLevel() {
        final int amountOfPlanes = 6;

        deleteBackground();
        // create enemy's bombs
        ImageIcon bombPic = new ImageIcon("./images/enemyBigBomb.png");
        createBombs(bombPic,amountOfPlanes);

        // create enemy's planes
        ImageIcon enemyPicPlane = new ImageIcon("./images/F22.png");
        createEnemies(enemyPicPlane,amountOfPlanes);

        // create background
        createBackground(level);

    }


    public void thirdLevel() {
        final int amountOfPlanes = 9;

        deleteBackground();
        // create enemy's bombs
        ImageIcon bombPic = new ImageIcon("./images/enemyBigBomb.png");
        createBombs(bombPic,amountOfPlanes);


        // create enemy's planes
        ImageIcon enemyPicPlane = new ImageIcon("./images/F22.png");
        createEnemies(enemyPicPlane,amountOfPlanes);

        // create background
        createBackground(level);

    }

    public void shootEnemyBombs()
    {
        //  shoot enemy Bombs :
        for (int i = 0; i < enemyArr.size()  ; i++) {
            if (!bombs.get(i).isShoot()) {
                bombs.get(i).setX(enemyArr.get(i).getX());
                bombs.get(i).setY(enemyArr.get(i).getY());
                bombs.get(i).setShoot(true);
                Random random = new Random();
                bombs.get(i).setSpeed(random.nextInt(20) + 6);
            }
        }
    }

    public void checkCollisionEBombsAndplayer() {
        // collision between enemy Bombs and player Plane:
        for (int i = 0; i < 3; i++) {
            if (gameLogic.collision(bombs.get(i), playerPlane)) {
                exp2.setY(playerPlane.y + 30);
                exp2.setX(playerPlane.x);
                exp2.animation.setPlayedOnce(false);
                playerPlane.setY(-100);

                //dec players life:
                if (counter++ % 9 == 0) {
                    // update score
                    panel.remove(scoreLabel);
                    scoreLabel.setText("LIFE:" + playerLIfe);
                    scoreLabel.setBounds(-80, 0, 300, 100);
                    panel.add(scoreLabel);
                    playerLIfe--;
                }
            } else {
                playerPlane.setY();
            }
        }
    }

    public void checkCollisionPlayerBombAndEnemies() {
        // collision between playerBomb and enemies:
        int found = -1;
        EnemyPlane enemyPlane = null;
        for (int i = 0; i < enemyArr.size(); i++) {
            if (gameLogic.collision(playerBomb, enemyArr.get(i))) {
                exp2.setY(enemyArr.get(i).y + 30);
                exp2.setX(enemyArr.get(i).x);
                exp2.animation.setPlayedOnce(false);
                enemyPlane = enemyArr.get(i);
                found = i;
            }
        }
        // delete enemy plane object from game
        if (found != -1) {
            panel.remove(enemyPlane);
            animatedObjects.remove(enemyPlane);
            enemyArr.remove(found);
        }
    }

    public void update() {
        for (AnimatedObject animatedObject : animatedObjects
        ) {
            animatedObject.update();
        }
        //  shoot enemy Bombs :
        shootEnemyBombs();

        // collision between enemy Bombs and player Plane:
        checkCollisionEBombsAndplayer();

        // collision between playerBomb and enemies:
        checkCollisionPlayerBombAndEnemies();


//        change level:
        if (enemyArr.isEmpty() && level < 4 ) {
            level++;
            if (level==2)
                secondLevel();
            if (level==3)
                thirdLevel();

        }

        // In case the player's life is over
        if (playerLIfe == 0) {
            gameOver.setText("GAME OVER");
            gameOver.setBounds(100, 200, 300, 100);
            panel.add(gameOver);
            panel.remove(scoreLabel);
            deleteAllEnemies();
            level = 5;
            playerLIfe = -1;

        }


        }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);

        //  someoneScored();
        for (AnimatedObject animatedObject : animatedObjects
        ) {
            animatedObject.draw(panel);
        }
    }

    @Override
    public void paint(Graphics g) {
        paintComponents(g);
    }

    public void draw() {

        // this code is a MUST, for refreshing the screen
        panel.setVisible(false);
        panel.setVisible(true);
        panel.revalidate();

        repaint();
    }

    /*
    mouse methods
     */

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            //limit the plane on the screen
            if (playerPlane.x > 0)
                playerPlane.setX(playerPlane.x - 20);

        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            // limit the plane on the screen
            if (playerPlane.x < panel.getWidth() - playerPlane.width - 20 )
                playerPlane.setX(playerPlane.x + 20);

        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            playerBomb.setShoot(true);
            playerBomb.setY(playerPlane.y);
            playerBomb.setX(playerPlane.x);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
