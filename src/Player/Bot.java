/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Player;

import java.util.ArrayList;
import java.util.Random;
import javafx.animation.AnimationTimer;

/**
 *
 * @author Jesus
 */
public class Bot extends Player{
    
    public static final int UP = 1;
    public static final int UPLEFT = 5;
    public static final int UPRIGHT = 6;
    public static final int DOWN = 2;
    public static final int DOWNLEFT = 7;
    public static final int DOWNRIGHT = 8;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;
    
    
    private Thread ia;
    private double r;
    private ArrayList<Integer> dirs;
    private Random rand = new Random();
    
    public Bot(String name) {
        super(name);
        dirs = new ArrayList();
        super.boolBot = true;
    }
    
    private void startBasicIA(){
            ia = new Thread(()->{
                this.AI();
            });
        }
    private void AI(){
            int aux = 0;
            int cont = 0;
            do{
                for (int i = 0; i < dirs.size() * 1000; i++) {
                    if(i == 0 || i == 1000 || i == 2000){
                        aux = i/1000;
                        if(dirs.get(aux) != null){
                            switch (dirs.get(aux)) {
                                case UP:
                                    super.setUp();
                                    break;
                                case LEFT:
                                    super.setLeft();
                                    break;
                                case RIGHT:
                                    super.setRight();
                                    break;
                                case UPLEFT:
                                    super.setUpLeft();
                                    break;
                                case UPRIGHT:
                                    super.setUpRight();
                                    break;
                                case DOWN:
                                    super.setDown();
                                    break;
                                case DOWNLEFT:
                                    super.setDownLeft();
                                    break;
                                case DOWNRIGHT:
                                    super.setDownRight();
                                    break;    
                        }
                    System.out.println("Dir: "+aux+" ->"+dirs.get(aux));
                    }        
                }
            }
            cont++;
            System.out.println("end for: "+cont);
            this.initDirs();
        }while(true);
    }
    
    private void start(){
        ia.start();
    }
    private void initDirs(){
        dirs.clear();
        for (int i = 0; i < 3; i++) {
            r = rand.nextDouble() * 100;
            System.out.println("r: "+r);
            if(super.getPosition() == Player.BR){
                if(r >= 0 && r <= 33) dirs.add(UP);
                if(r > 33 && r <= 66) dirs.add(LEFT);
                if(r > 66 && r <= 100) dirs.add(UPLEFT);
            }
            if(super.getPosition() == Player.BL){
                if(r >= 0 && r <= 33) dirs.add(UP);
                if(r > 33 && r <= 66) dirs.add(RIGHT);
                if(r > 66 && r <= 100) dirs.add(UPRIGHT);
            }
            if(super.getPosition() == Player.TL){
                if(r >= 0 && r <= 33) dirs.add(DOWN);
                if(r > 33 && r <= 66) dirs.add(RIGHT);
                if(r > 66 && r <= 100) dirs.add(DOWNRIGHT);
            }
            if(super.getPosition() == Player.TR){
                if(r >= 0 && r <= 33) dirs.add(DOWN);
                if(r > 33 && r <= 66) dirs.add(LEFT);
                if(r > 66 && r <= 100) dirs.add(DOWNLEFT);
            }
        }
    }
    public void initBot(){
        this.initDirs();
        this.startBasicIA();
        this.start();
    }
}
