package snakegame;

import javax.swing.*;  
import java.awt.*;
import java.awt.event.*;

public class Board extends JPanel implements ActionListener {  //JPanel is a part of JFrame which divides the frame
    
    private int dots;
    private final int ALL_DOTS =2500; //as width= 500 and height =500 , area=2500
    private final int  DOT_SIZE =10;
    private final int RANDOM_POSITION =29;
    
    private  Timer timer;
    
    private boolean inGame=true  ;
    
    private int apple_x;
    private int apple_y;
    
    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];
    
    private Image apple;  
    private Image dot;
    private Image  head;
    
    private boolean leftDirection = false;
    private boolean rightDirection = true; //initially we are making to move our snake to right direction
    private boolean upDirection = false;
    private boolean downDirection = false;
   
    Board(){
        
        addKeyListener(new TAdapter());
         
        setBackground(Color.BLACK);   //available in java.awt pacakge
        setPreferredSize(new Dimension (500,500));
        setFocusable(true);
        
        
        loadImages();
        initGame();
        
    }
    
    public void loadImages(){
        ImageIcon i1 = new  ImageIcon(ClassLoader.getSystemResource("snakegame/icons/apple.png"));
        apple = i1.getImage();
        
        ImageIcon i2 = new  ImageIcon(ClassLoader.getSystemResource("snakegame/icons/dot.png"));
        dot = i2.getImage();
         
        ImageIcon i3 = new  ImageIcon(ClassLoader.getSystemResource("snakegame/icons/head.png"));
        head = i3.getImage();
        
    }
    
    public void initGame(){
        dots = 3;
        
        for(int i =0; i < dots; i++){
            y[i] =50;
            x[i] = 50-i*DOT_SIZE; //upto  dots =3  first dot will be at 50 , 40,30
        }
        
        locateApple();
        
        
        timer = new Timer(140, this); //after 140 milliseconds the timer calls the actionPerformed method
        timer.start();
        
    }
    
    public void locateApple(){
        
     int r =(int)(Math.random() * RANDOM_POSITION); //type casting  random function generates value from 0 to 1 along with  decimal values
         apple_x = r*DOT_SIZE;
     
         r =(int) (Math.random() *RANDOM_POSITION); //random gives double type we made to cinvert it to "int" type
         apple_y =r*DOT_SIZE;
        
    }
    
    public void paintComponent(Graphics g){  //used to show the images i1,i2,i3 on frames
       super.paintComponent(g);
       
       draw(g);
    }
    
    public void draw(Graphics g){
        
        if(inGame){       // if the player is in game then we should show apples to player 
           
             g.drawImage(apple,apple_x,apple_y, this);
        
        for(int i =0; i<dots ; i++)
        {
            if(i==0){
                g.drawImage(head,x[i],y[i], this);
            }
            else
            {
               g.drawImage(dot,x[i],y[i], this); 
            }
            
        }
        Toolkit.getDefaultToolkit().sync();
        }   
        
        else
        {
             gameOver(g);
        }
    }
    
    public void gameOver(Graphics g){
        String msg = "Game Over !!"; 
        
        
        
        Font font = new Font("SAN SERIF", Font.BOLD , 20);
        FontMetrics metrices = getFontMetrics(font);
        
        
        g.setColor(Color.WHITE);
        g.setFont(font);
        //here we are displaying the message at the centre of frame , so in second parameter we consider length of frame - metrices. width of the string /2
        g.drawString(msg, (500 - metrices.stringWidth(msg)) / 2, 500/2); 
    }
    
    public void move(){
        
        for(int i =dots ; i>0; i--){    //moving the elements
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        
         if (leftDirection) {                       
            x[0] = x[0] - DOT_SIZE;         // as ot is left side of the frame the x value decreases so we made x[0]-DOT_SIZE
        }
         
        if (rightDirection) {
            x[0] = x[0] + DOT_SIZE;
        }
        
        if (upDirection) {                   // when it moves upward of the frame the y axis length decreases so - comes 
            y[0] = y[0] - DOT_SIZE;
        }
        
        if (downDirection) {
            y[0] = y[0] + DOT_SIZE;
        }
         
        //x[0]+=DOT_SIZE;
       // y[0]+=DOT_SIZE; when this line is written along with above line then snake moves in the diagnol direction 
    }
    
    public void checkApple(){
        
        // we are checking the cordinates of the head and apple so that snake get the apple and can increase the length
        
        if(x[0]==apple_x && y[0]==apple_y){ //if the co-ordinates matches then dots will be increased and we need to have another apple , so calling locateApple function  
             dots++;
             locateApple();
        }   
}
    
    public void checkCollision(){
        //when head of the snake hits the body then the game will be over, this is applicable only when the snake size is more than 4 dots
        
        for(int i =dots ; i>0; i--)
        {
            if((i>4) && x[0]==x[i] && y[0]==y[i]){  //remember when snake bites apple then the dots will increases , consider the increased size of dots number
              
                 inGame = false; 
            }
        }
         // if the snake hits the boundaries then also the game gets over 
         
         if (y[0] >= 500)    //here 500 is the height of your frame   //y co-ordinate maximum height and more than the frame height
         {        
             inGame = false;
         }
         
          if (x[0] >= 500)        //x co-ordinate length is 500 , considering complete length and morethan it
         {        
             inGame = false;
         }
          
          
        if (y[0] < 0)             // y co-ordinates minimun height 
         {        
             inGame = false;
         }
           
        if (x[0] < 0)             // x co-ordinate minimum  lenth   
         {        
             inGame = false;
         }
        
        if(!inGame){    // when he/she is not in game then the timer should stop 
            timer.stop();
        }
         
    }
    
    
    public void actionPerformed(ActionEvent ae){
        
        if(inGame){
            
        checkApple();
        checkCollision();
        move();
       
        }   
        repaint(); 
    }
    
    public class TAdapter extends KeyAdapter { //used to capture the movements of the snake
        
        @Override     //this line is written to avoid warnings
        public void keyPressed(KeyEvent e){ //it ia abastract method in the KeyAdapter that implements keyListener
            int key = e.getKeyCode();
            
            //checking the leftDirection
            
            if(key==KeyEvent.VK_LEFT && (!rightDirection))  //when your left direction is true right direction should be made false, then only it can travel to left side
            {
               leftDirection = true; 
               upDirection   = false;
               downDirection = false;
             
            }
            
            // checking the rightDirection
            if(key==KeyEvent.VK_RIGHT && (!leftDirection))  //when your right direction is true left direction should be made false, then only it can travel to right side
            {
               rightDirection = true; 
               upDirection    = false;
               downDirection  = false;
             
            }
            
            // checking the upDirection
            if(key==KeyEvent.VK_UP && (!downDirection))  //when your up direction is true, down direction should be made false, then only it can travel to up side
            {
               upDirection    = true; 
               rightDirection = false;
               leftDirection  = false;
             
            }
            
            
            // checking the downDirection
            if(key==KeyEvent.VK_DOWN && (!upDirection))  //when your down direction is true, up direction should be made false, then only it can travel to down side
            {
               downDirection = true; 
               rightDirection   = false;
               leftDirection = false;
             
            }          
        }   
    
     }
}
