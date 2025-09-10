import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.util.Scanner;
public class Overcooked_Three extends JPanel implements KeyListener, Runnable, MouseListener {
    // Game States:
    // G.S 0 - Home State/ menu screen
    // G.S 1 - INFO state
    // G.S 2 - high scores
    // G.S 3 - In-game
    // G.S 4 - Final Score

    // Numbers for each ingredient:
    // 0 -> rice
    // 1 -> sashimi
    // 2 -> yellow tail
    // 3 -> seaweed
    // 4 -> matcha

    // 1) 0 + 1
    // 2) 0 + 2
    // 3) 0 + 1 + 3
    // 4) 4
    // 5) 0 + 2 + 3
    public static int gameState = 0;
    public static int[] highScores = new int[3];
    // image importation:
    public static BufferedImage img0;
    public static BufferedImage[] img2 = new BufferedImage[2];
    public static BufferedImage img1;
    public static BufferedImage img3;
    public static BufferedImage img4;
    public static BufferedImage rice;
    public static BufferedImage rice2;
    public static BufferedImage rice3;
    public static BufferedImage sashimi;
    public static BufferedImage sashimi2;
    public static BufferedImage sashimi3;
    public static BufferedImage seaweed;
    public static BufferedImage seaweed2;
    public static BufferedImage seaweed3;
    public static BufferedImage yellowtail;
    public static BufferedImage yellowtail2;
    public static BufferedImage yellowtail3;
    public static BufferedImage sashNoSeaMenu;
    public static BufferedImage sashSeaMenu;
    public static BufferedImage yellSeaMenu;
    public static BufferedImage yellNoSeaMenu;
    public static BufferedImage riceMenu;
    public static int bobNum = 0;
    public static int bobDirection = 2; // 0 = back, 1 = left, 2 = front, 3 = right
    public static BufferedImage[] bob = new BufferedImage[16];
    public static BufferedImage[] bob2 = new BufferedImage[2];
    public static BufferedImage[] bob3 = new BufferedImage[2];
    public static BufferedImage[] bob4 = new BufferedImage[3]; // him sleeping
    public static int frameCountergs = 0;
    public static int spriteNogs = 0;
    public static int frcb = 0;
    public static int spcb = 0;
    public static int frameCS = 0;
    public static int sprS2 = 0;
    public static int frc2 = 0;
    public static int sprS3 = 0;
    public static int frc3 = 0;
    public static int spriteS = 0;
    public static BufferedImage[] sas = new BufferedImage[2];
    public static BufferedImage[] sas2 = new BufferedImage[2];
    public static BufferedImage[] sas3 = new BufferedImage[2];
    public static int spriteNoBob = 0;
    public static int spriteNoBob2 = 0;
    public static int spriteNoBob3 = 0;
    public static int frameCounterBob = 0;
    public static int frameCounterBob2 = 0;
    public static int frameCounterBob3 = 0;

    // pressed key stuff:
    public static int posxBob = 450;
    public static int posyBob = 70;
    public static boolean sashBool = false;
    public static boolean sashBool2 = false;
    public static boolean sashBool3 = false;
    public static boolean yellBool = false;
    public static boolean yellBool2 = false;
    public static boolean yellBool3 = false;
    public static boolean riceBool = false;
    public static boolean riceBool2 = false;
    public static boolean riceBool3 = false;
    public static boolean seaBool = false;
    public static boolean seaBool2 = false;
    public static boolean seaBool3 = false;

    public static int sashSpot;
    public static int sashSpot2;
    public static int sashSpot3;
    public static int riceSpot;
    public static int riceSpot2;
    public static int riceSpot3;
    public static int seaSpot;
    public static int seaSpot2;
    public static int seaSpot3;
    public static int yellowSpot;
    public static int yellowSpot2;
    public static int yellowSpot3;
    public static boolean wPressed = false;
    public static boolean  aPressed = false;
    public static boolean  sPressed = false;
    public static boolean  dPressed = false;
    public static boolean xPressed = false;
    public static boolean spacePressed = false;
    public static int randMenu; // menu 0 = rice, 1 = sash + rice, 2 = yellow + rice, 3 = sash + rice + sea
                        // 4 = yellow = rice = sea, 5 = matcha

    public static boolean menuOn = false;
    public static int frameCounterFirst = 1000;
    public static int timer = 3000; // 1 min
    public static int counter;
    public static int[] sashimiOne = {0,1};
    public static int[] yellowTailOne = {0,2};
    public static int[] sashimiTwo = {0,1,3};
    public static int[] yellowTailTwo = {0,2,3};
    public static int[] ric = {0};
    public static int[] plate = new int[0];
    public static int score = 0;
    public static int p1;
    public static int p2;
    public static int p3;

    // Default Settings and Image importations
    public Overcooked_Three() {
        setPreferredSize(new Dimension(1280, 720));

        // add keylistener
        this.setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);

        // add timer/thread
        Thread thread = new Thread(this);
        thread.start(); //

        // image importation
        try {
            img0 = ImageIO.read(new File("bg0-01.png")); // gs0 changed originally is 0
            img1 = ImageIO.read(new File("BROO-01.png"));
            img2[0] = ImageIO.read(new File("hs1-01.png"));
            img2[1] = ImageIO.read(new File("hs2-01.png"));
            img3 = ImageIO.read(new File("bra-01.png"));
            img4 = ImageIO.read(new File("b4v4-01.png"));
            rice = ImageIO.read(new File("RICE.png"));
            rice2 = ImageIO.read(new File("RICE.png"));
            rice3 = ImageIO.read(new File("RICE.png"));
            sashimi = ImageIO.read(new File("SASHIMI.png"));
            sashimi2 = ImageIO.read(new File("SASHIMI.png"));
            sashimi3 = ImageIO.read(new File("SASHIMI.png"));
            seaweed = ImageIO.read(new File("SEAWEED.png"));
            seaweed2 = ImageIO.read(new File("SEAWEED.png"));
            seaweed3 = ImageIO.read(new File("SEAWEED.png"));
            yellowtail = ImageIO.read(new File("YELLOWTAIL.png"));
            yellowtail2 = ImageIO.read(new File("YELLOWTAIL.png"));
            yellowtail3 = ImageIO.read(new File("YELLOWTAIL.png"));
            yellNoSeaMenu = ImageIO.read(new File("bpng.png"));
            sashNoSeaMenu = ImageIO.read(new File("fpng.png"));
            sashSeaMenu = ImageIO.read(new File("sfpng.png"));
            yellSeaMenu = ImageIO.read(new File("sf2.png"));
            sas[0] = ImageIO.read(new File("sa1.png"));
            sas[1] = ImageIO.read(new File("sa2.png"));
            sas2[0] = ImageIO.read(new File("b1.png"));
            sas2[1] = ImageIO.read(new File("b2.png"));
            sas3[0] = ImageIO.read(new File("halo.png"));
            sas3[1] = ImageIO.read(new File("halo2.png"));
            riceMenu = ImageIO.read(new File("rfpng.png"));
            bob[0] = ImageIO.read(new File("mrchow1-1.png.png")); // front idle
            bob[1] = ImageIO.read(new File("mrchow1-2.png.png")); // front idle
            bob[2] = ImageIO.read(new File("mrchow2-1.png.png")); // front walk left foot (s)
            bob[3] = ImageIO.read(new File("mrchow2-2.png.png")); // front walk right foot (s)
            bob[4] = ImageIO.read(new File("mrchow3-1.png.png")); // back walk right (w)
            bob[5] = ImageIO.read(new File("mrchow3-2.png.png")); // back walk left (w)
            bob[6] = ImageIO.read(new File("mrchow4-1.png.png")); // back idle
            bob[7] = ImageIO.read(new File("mrchow4-2.png.png")); // back idle
            bob[8] = ImageIO.read(new File("mrchow5-1.png.png")); // left walk (a)
            bob[9] = ImageIO.read(new File("mrchow5-2.png.png")); // left walk (a)
            bob[10] = ImageIO.read(new File("mrchow6-1.png.png")); // left idle
            bob[11] = ImageIO.read(new File("mrchow6-2.png.png")); // left idle
            bob[12] = ImageIO.read(new File("mrchow7-1.png.png")); // right idle
            bob[13] = ImageIO.read(new File("mrchow7-2.png.png")); // right idle
            bob[14] = ImageIO.read(new File("mrchow8-1.png.png")); // right walk (d)
            bob[15] = ImageIO.read(new File("mrchow8-2.png.png")); // right walk (d)
            bob2[0] = ImageIO.read(new File("mrchow1-1.png.png")); // draw him
            bob2[1] = ImageIO.read(new File("mrchow1-2.png.png"));
            bob3[0] = ImageIO.read(new File("mrchow1-1.png.png")); // draw him
            bob3[1] = ImageIO.read(new File("mrchow1-2.png.png"));
            bob4[0] = ImageIO.read(new File("zzz2.png.png")); // draw him
            bob4[1] = ImageIO.read(new File("zzzpng.png"));


        } catch (Exception e) {
        }
    }
    public static void gameEnds() throws IOException{
        PrintWriter outputFile = new PrintWriter(new File("WTF.txt"));
        for(int i = 0; i < 3; i++){
            outputFile.println(highScores[i]);
        }
        outputFile.close();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameState == 0) {
            g.drawImage(img0, 0, 0, null);
            frameCounterBob2++;
            frameCS++;
            frc2++;
            frc3++;
            if(frameCounterBob2 == 15){
                spriteNoBob2 = (spriteNoBob2 + 1) % 2;
                frameCounterBob2 = 0;
            }
            if(frameCS == 15){
                spriteS = (spriteS + 1)%2;
                frameCS = 0;
            }
            if(frc2 == 13){
                sprS2 = (sprS2 + 1)%2;
                frc2 = 0;
            }
            if(frc3 == 13){
                sprS3 = (sprS3 + 1)%2;
                frc3 = 0;
            }
            g.drawImage(bob2[spriteNoBob2], 820, 230, null);
            g.drawImage(sas[spriteS], 50, 160, null);
            g.drawImage(sas2[sprS2], 920, 210, null);
            g.drawImage(sas3[sprS3], 50, 320, null);

            clear(false);
            posyBob = 420;
            posxBob = 420;
            score = 0;
            timer = 3000;
            frameCounterBob = 0;
            spriteNoBob = 0;
            bobDirection = 2;
            bobNum = 0;
            wPressed = false;
            aPressed = false;
            sPressed = false;
            dPressed = false;

        } else if (gameState == 1) {
            g.drawImage(img1, 0, 0, null);
            frameCounterBob3++;
            if(frameCounterBob3 == 10){
                spriteNoBob3 = ((spriteNoBob3+1)%2);
                frameCounterBob3 = 0;
            }
            g.drawImage(bob3[spriteNoBob3],330,45,null);

        } else if (gameState == 2) {
            frameCountergs++;
            if(frameCountergs == 40){
                spriteNogs = ((spriteNogs + 1) % 2);
                frameCountergs = 0;
            }
            g.drawImage(img2[spriteNogs],0,0,null);

            g.setFont(new Font("Lemon Milk", Font.PLAIN,100));
            g.setColor(new Color(216,14,14));
            g.drawString("" + highScores[0],625,250);
            g.setFont(new Font("Lemon Milk", Font.PLAIN,100));
            g.setColor(new Color(216,14,14));
            g.drawString("" + highScores[1],625,400);
            g.setFont(new Font("Lemon Milk", Font.PLAIN,100));
            g.setColor(new Color(216,14,14));
            g.drawString("" + highScores[2],625,550);

        }
        else if (gameState == 3) {
            g.drawImage(img3, 0,0, null);
            timer -= 1;
            if(timer == 0 || xPressed){
                if(xPressed){
                    score = 15;
                }
                if(score > highScores[0]){
                    highScores[2] = highScores[1];
                    highScores[1] = highScores[0];
                    highScores[0] = score;
                }
                else if(score > highScores[1]){
                    highScores[2] = highScores[1];
                    highScores[1] = score;
                }
                else if(score > highScores[2]){
                    highScores[2] = score;
                }
                else{
                    highScores[0] = p1;
                    highScores[1] = p2;
                    highScores[2] = p3;
                }
                try{
                    gameEnds();
                } catch(IOException e){
                    throw new RuntimeException(e);
                }
                gameState = 4;
            }
            g.setFont(new Font("Lemon Milk", Font.PLAIN, 50));
            g.setColor(new Color(0,0,0));
            g.drawString("" + score, 1050, 220);

            g.setFont(new Font("Lemon Milk", Font.PLAIN, 50));
            g.setColor(new Color(0,0,0));
            g.drawString("" + (timer/50+1), 1125, 685);

            if(sashBool){
                g.drawImage(sashimi, checkNumX(sashSpot),60,null);
            }
            if(sashBool2){
                g.drawImage(sashimi2, checkNumX(sashSpot2),60,null);
            }
            if(sashBool3){
                g.drawImage(sashimi3, checkNumX(sashSpot3),60,null);
            }
            if(riceBool){
                g.drawImage(rice, checkNumX(riceSpot),60,null);
            }
            if(riceBool2){
                g.drawImage(rice2, checkNumX(riceSpot2),60,null);
            }
            if(riceBool3){
                g.drawImage(rice3, checkNumX(riceSpot3),60,null);
            }
            if(yellBool){
                g.drawImage(yellowtail, checkNumX(yellowSpot),60,null);
            }
            if(yellBool2){
                g.drawImage(yellowtail2, checkNumX(yellowSpot2),60,null);
            }
            if(yellBool3){
                g.drawImage(yellowtail3, checkNumX(yellowSpot3),60,null);
            }
            if(seaBool){
                g.drawImage(seaweed, checkNumX(seaSpot),60,null);
            }
            if(seaBool2){
                g.drawImage(seaweed2, checkNumX(seaSpot2),60,null);
            }
            if(seaBool3){
                g.drawImage(seaweed3, checkNumX(seaSpot3),60,null);
            }

            if(wPressed){ // use direction as last direction
                bobDirection = 0;
                if(!(posyBob + 50 < 330)){
                    bobNum = 4;
                    posyBob -= 10;
                }
                else{
                    bobNum = 6;
                }
            }
            else if(aPressed){
                bobDirection = 1;
                if(!(posxBob < 240)){
                    bobNum = 8;
                    posxBob -= 10;
                }
                else{
                    bobNum = 10;
                }
            }
            else if(sPressed){
                bobDirection = 2;
                if(!(posyBob + 90 > 600)){
                    bobNum = 2;
                    posyBob += 10;
                }
                else{
                    bobNum = 0;
                }
            }
            else if(dPressed){
                bobDirection = 3;
                if(!(posxBob + 65 > 1010)){
                    bobNum = 14;
                    posxBob += 10;
                }
                else{
                    bobNum = 12;
                }
            }
            else{
                if(bobDirection == 0){
                    bobNum = 6;
                }
                else if (bobDirection == 1){
                    bobNum = 10;
                }
                else if(bobDirection == 2){
                    bobNum = 0;
                }
                else if (bobDirection == 3){
                    bobNum = 12;
                }
            }

            if(spacePressed){
                // Sashimi
                if((bobDirection == 1 && posxBob >= 230 && posxBob <= 270 && posyBob >= 300 && posyBob <= 350) && plate.length < 3){
                    counter++;
                    plate = extendArray(plate, 1);
                    if(counter == 1){
                        sashBool = true;
                        sashSpot = plate.length;
                    }
                    else if (counter == 2){
                        sashBool2 = true;
                        sashSpot2 = plate.length;
                    }
                    else if(counter == 3) {
                        sashBool3 = true;
                        sashSpot3 = plate.length;
                    }
                }
                //yellow tail
                if(bobDirection == 1 && posxBob >= 230 && posxBob <= 270 && posyBob >= 400 && posyBob <= 445 && plate.length < 3){
                    counter++;
                    plate = extendArray(plate,2);
                    if(counter == 1){
                        yellBool = true;
                        yellowSpot = plate.length;
                    }
                    else if (counter == 2){
                        yellBool2 = true;
                        yellowSpot2 = plate.length;
                    }
                    else if(counter == 3) {
                        yellBool3 = true;
                        yellowSpot3 = plate.length;
                    }
                }
                // seaweed
                if(bobDirection == 1 && posxBob >= 230 && posxBob <= 270 && posyBob >= 490 && posyBob <= 600 && plate.length < 3){
                    counter++;
                    plate = extendArray(plate,3);
                    if(counter == 1){
                        seaBool = true;
                        seaSpot = plate.length;
                    }
                    else if (counter == 2){
                        seaBool2 = true;
                        seaSpot2 = plate.length;
                    }
                    else if(counter == 3) {
                        seaBool3 = true;
                        seaSpot3 = plate.length;
                    }
                }
                // rice
                if(bobDirection == 0 && posxBob >= 400 && posxBob <= 575 && posyBob <= 280 && plate.length < 3){
                    counter++;
                    plate = extendArray(plate,0);
                    if(counter == 1){
                        riceBool = true;
                        riceSpot = plate.length;
                    }
                    else if (counter == 2){
                        riceBool2 = true;
                        riceSpot2 = plate.length;
                    }
                    else if(counter == 3) {
                        riceBool3 = true;
                        riceSpot3 = plate.length;
                    }
                }
                // serve
                if(bobDirection == 3 && posxBob >= 945 && posyBob >= 400 && posyBob <= 600){
                    checkMatch(plate);
                }
                // garbage
                if(bobDirection == 2 && posxBob >= 480 && posxBob <= 560 && posyBob >= 520 && posyBob <= 545){
                    clear(true);
                }
                spacePressed = false;
            }
            // 0 = back, 1 = left, 2 = front, 3 = right

            if(xPressed){
                gameState = 4;
                xPressed = false;
                score = 15;
            }

            frameCounterBob++;
            if(frameCounterBob == 10){ // mini delay (fix later lolz)
                spriteCounter();
            }
            if(!menuOn){
                randMenu = (int)(Math.random()*5);
                menuOn = true;
            }
            if(menuOn){
                frameCounterFirst -= 1;
            }
            if(frameCounterFirst == 0){
                menuOn = false;
                timeMenu();
            }
            if(randMenu == 0){
                g.drawImage(sashNoSeaMenu, 50,0,null);
            }
            else if(randMenu == 1){
                g.drawImage(yellNoSeaMenu,50,0,null);
            }
            else if(randMenu == 2){
                g.drawImage(sashSeaMenu, 50, 0, null);
            }
            else if(randMenu == 3){
                g.drawImage(yellSeaMenu,50,0, null);
            }
            else if(randMenu == 4){
                g.drawImage(riceMenu,50,0, null);
            }
            g.drawImage(bob[spriteNoBob],posxBob,posyBob,null);

            g.setFont(new Font("Lemon Milk", Font.PLAIN, 20));
            g.setColor(new Color(250,100,0));
            g.drawString("" + (frameCounterFirst/50+1), 270, 115);

        } else if (gameState == 4) {
            g.drawImage(img4, 0, 0, null);
            g.setFont(new Font("Lemon Milk", Font.PLAIN, 150));
            g.setColor(new Color(0,0,0));
            g.drawString("" + score, 585, 390);
            frcb++;
            if(frcb == 13){
                spcb = (spcb + 1)%2;
                frcb = 0;
            }
            g.drawImage(bob4[spcb], 525, 420, null);

        }
    }
    public static void main(String[] args)throws IOException{
        JFrame frame = new JFrame("Overcooked");
        Overcooked_Three panel = new Overcooked_Three();
        frame.add(panel);
        frame.setVisible(true);
        frame.pack();

        Scanner inputFile = new Scanner(new File("WTF.txt"));
        p1 = Integer.parseInt(inputFile.nextLine());
        p2= Integer.parseInt(inputFile.nextLine());
        p3 = Integer.parseInt(inputFile.nextLine());
        inputFile.close();

        highScores[0] = p1;
        highScores[1] = p2;
        highScores[2] = p3;

    }
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        if(gameState == 3){
            if (key == 'w') {
                wPressed = true;
            }
            if(key == 'a'){
                aPressed = true;
            }
            if(key == 's'){
                sPressed = true;
            }
            if(key == 'd'){
                dPressed = true;
            }
            if(key == 'x'){
                xPressed = true;
            }
            if (key == ' ') {
                spacePressed = true;
            }

        }
    }
    public void keyReleased(KeyEvent e) {
        char key = e.getKeyChar();
        if(gameState == 3){
            if(key == 'w'){
                wPressed = false;
            }
            if(key == 'a'){
                aPressed = false;
            }
            if(key == 's'){
                sPressed = false;
            }
            if(key == 'd'){
                dPressed = false;
            }
        }
    }
    public void mousePressed(MouseEvent e){
        if(gameState == 0){
            if(e.getButton() == 1 && e.getX() >= 50 && e.getX() <= 300 && e.getY() >= 540 && e.getY() <= 680){
                gameState = 1;
            }
            if(e.getButton() == 1 && e.getX() >= 515 && e.getX() <= 765 && e.getY() >= 540 && e.getY() <= 680) {
                gameState = 3;
            }
            if(e.getButton() == 1 && e.getX() >= 980 && e.getX() <= 1230 && e.getY() >= 540 && e.getY() <= 680) {
                gameState = 2;
            }
        }
        else if(gameState == 1){
            if(e.getButton() == 1 && e.getX() >= 50 && e.getX() <= 300 && e.getY() >= 540 && e.getY() <= 680){
                gameState = 0;
            }
        }
        else if(gameState == 2){
            if(e.getButton() == 1 && e.getX() >= 50 && e.getX() <= 300 && e.getY() >= 540 && e.getY() <= 680){
                gameState = 0;
            }
        }
        else if(gameState == 4){
            if(e.getButton() == 1 && e.getX() >= 50 && e.getX() <= 300 && e.getY() >= 540 && e.getY() <= 680){
                gameState = 0;
            }
            if(e.getButton() == 1 && e.getX() >= 980 && e.getX() <= 1230 && e.getY() >= 540 && e.getY() <= 680){
                gameState = 2;
            }
        }
    }
    public void mouseReleased(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void keyTyped(KeyEvent e) {}
    public void run() {
        // two commnds to control frame rate and to redraw
        while (true) {
            try {
                repaint();
                Thread.sleep(20); // 50fps (game state act gets cleared out and redraw)
            } catch (Exception e) {
            }
        }
    }
    public void spriteCounter(){
        spriteNoBob = ((spriteNoBob + 1) % 2) + bobNum;
        frameCounterBob = 0;
        bobNum = 0;
    }
    public static int[] extendArray(int[] a, int n){
        if(a.length >= 3){
            return a;
        }
        int[] newArr = new int[a.length + 1];
        for(int i = 0; i < a.length; i++){
            newArr[i] = a[i];
        }
        newArr[a.length] = n;
        return newArr;
    }
    public static int checkNumX(int n){
        if(n == 1){
            return 700;
        }
        else if(n == 2){
            return 900;
        }
        else{
            return 1100;
        }
    }
    public static void checkMatch(int[] a){
        if(randMenu == 0 && sameArr(a,sashimiOne)){
            score++;
            clear(false);
        }
        else if(randMenu == 1 && sameArr(a,yellowTailOne)){
            score++;
            clear(false);
        }
        else if(randMenu == 2 && sameArr(a,sashimiTwo)){
            score++;
            clear(false);
        }
        else if(randMenu == 3 && sameArr(a, yellowTailTwo)){
            score++;
            clear(false);
        }
        else if (randMenu == 4 && sameArr(a, ric)){
            score++;
            clear(false);
        }
    }
    public static boolean sameArr(int[] a, int[] b){
        if(a.length != b.length){
            return false;
        }
        for(int i = 0; i < a.length; i++){
            if(a[i] != b[i]){
                return false;
            }
        }
        return true;
   }
    public static void clear(boolean n) {
        plate = new int[0];
        riceBool = false;
        yellBool = false;
        sashBool = false;
        sashBool2 = false;
        sashBool3 = false;
        seaBool = false;
        riceBool2 = false;
        riceBool3 = false;
        yellBool2 = false;
        yellBool3 = false;
        seaBool2 = false;
        seaBool3 = false;
        if(!n){
            menuOn = false;
            timeMenu();
        }
        counter = 0;
    }
    public static void timeMenu(){
        if(timer/50 <= 60 && timer/50 >= 50){
            frameCounterFirst = 1000;
            if(score > 2){
                frameCounterFirst = 750;
            }
        }
        else if(timer/50 < 50 && timer/50 >= 45){
            frameCounterFirst = 750;
            if(score > 6){
                frameCounterFirst = 500;
            }
        }
        else if(timer/50 < 45 && timer/50 >= 35){
            if(score > 9){
                frameCounterFirst = 400;
            }
            frameCounterFirst = 500;
        }
        else if(timer/50 < 35 && timer/50 >= 25){
            frameCounterFirst = 400;
        }
        else if(timer/50 < 25 && timer/50 >= 15){
            frameCounterFirst = 350;
        }
        else{
            frameCounterFirst = 250;
        }
    }
}