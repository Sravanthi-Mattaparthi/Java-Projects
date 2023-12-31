package tictactoe;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*; 

public class TicTacToe implements ActionListener {
    Random random = new Random();                    //comes from java.util.*;
    JFrame frame = new JFrame();                     // comes from swing
    
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    
    boolean player1_turn;    //atleast mentioning player1 is enough if it is true he will play , or false player2 will play

    TicTacToe(){
        
        frame.setLocation(350, 50);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout()); 
        frame.setVisible(true); 
        
        textfield.setBackground(new Color(25,25,25)); 
        textfield.setForeground(new Color(25,255,0)); 
        textfield.setFont(new Font("Times New Roman", Font.BOLD,75)); 
        textfield.setHorizontalAlignment(JLabel.CENTER); 
        textfield.setText("Tic-Tac-Toe ");
        textfield.setOpaque(true); 
        
        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 1000);
        
        button_panel.setLayout(new GridLayout(3,3));
        button_panel .setBackground(new Color(150,150,150 )); 
        
        for(int i =0; i<9 ; i++){
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("Poppins", Font.BOLD,120));
            buttons[i].setFocusable(false);  //we don't want this buttons to be focus able
            buttons[i].addActionListener(this);
        }
        
        title_panel.add(textfield);
        frame.add(title_panel,BorderLayout.NORTH);
        frame.add(button_panel); 
        
        
        firstTurn ();
        
    }
 
    
    public void firstTurn()
    {
       
        try{
           Thread.sleep(2000);   //2seconds of delay
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
      
        if(random.nextInt(2)==0)   //here we are deciding to have either 0 or 1 , so that we can have player1 or player 2 turn
        {
            player1_turn=true;  
            textfield.setText("X turn");
        }
        else
        {
         player1_turn=false; 
         textfield.setText("O turn");   
        }
            
     }
    
    
     @Override
    public void actionPerformed(ActionEvent ae)
    {
        for(int i =0; i<9; i++) {
           if(ae.getSource()==buttons[i]){
               if(player1_turn){
                if( buttons[i].getText()== ""){
                    buttons[i].setForeground(new Color(255,0,0));  //red color
                    buttons[i].setText("X");
                    player1_turn=false;  //after placing X in the box by player1 his turn to be made false so that player 3 turn comes into picture
                    textfield.setText("O turn");
                    check();
                }   
               }
               else{
                 if(buttons[i].getText()== ""){
                    buttons[i].setForeground(new Color(0,0,255));  //blue color
                    buttons[i].setText("O");
                    player1_turn=true;  //after placing O in the box by player2, we make turn of player1 by making true
                    textfield.setText("X turn");
                    check();
                }   
               }
                
            }
        }
    }
    
    
    public void check()
    {
       //xWins condition
        
        if((buttons[0].getText()=="X") &&
           (buttons[1].getText()=="X") &&
           (buttons[2].getText()=="X") ){
            
            xWins(0,1,2);
        }
        
         if((buttons[3].getText()=="X") &&
            (buttons[4].getText()=="X") &&
            (buttons[5].getText()=="X") ){
            
            xWins(3,4,5);
        }

         
         if((buttons[6].getText()=="X") &&
            (buttons[7].getText()=="X") &&
            (buttons[8].getText()=="X") ){
            
            xWins(6,7,8);
        }
         
         if((buttons[0].getText()=="X") &&
            (buttons[3].getText()=="X") &&
            (buttons[6].getText()=="X") ){
            
            xWins(0,3,6);
        }
         
         
         if((buttons[1].getText()=="X") &&
            (buttons[4].getText()=="X") &&
            (buttons[7].getText()=="X") ){
            
            xWins(1,4,7);
        }
         
         
        if( (buttons[2].getText()=="X") &&
            (buttons[5].getText()=="X") &&
            (buttons[8].getText()=="X") ){
            
            xWins(2,5,8 );
        }
         
        if( (buttons[0].getText()=="X") &&
            (buttons[4].getText()=="X") &&
            (buttons[8].getText()=="X") ){
            
            xWins(0,4,8);
        }
        
         if((buttons[2].getText()=="X") &&
            (buttons[4].getText()=="X") &&
            (buttons[6].getText()=="X") ){
            
            xWins(2,4,6);
        }
          
         
        //oWins conditions
        
        if((buttons[0].getText()=="O") &&
           (buttons[1].getText()=="O") &&
           (buttons[2].getText()=="O") ){
            
            oWins(0,1,2);
        }
        
         if((buttons[3].getText()=="O") &&
            (buttons[4].getText()=="O") &&
            (buttons[5].getText()=="O") ){
            
            oWins(3,4,5);
        }

         
         if((buttons[6].getText()=="O") &&
            (buttons[7].getText()=="O") &&
            (buttons[8].getText()=="O") ){
            
            oWins(6,7,8);
        }
         
         if((buttons[0].getText()=="O") &&
            (buttons[3].getText()=="O") &&
            (buttons[6].getText()=="O") ){
            
            oWins(0,3,6);
        }
         
         
         if((buttons[1].getText()=="O") &&
            (buttons[4].getText()=="O") &&
            (buttons[7].getText()=="O") ){
            
            oWins(1,4,7);
        }
         
         
        if((buttons[2].getText()=="O") &&
            (buttons[5].getText()=="O") &&
            (buttons[8].getText()=="O") ){
            
            oWins(2,5,8 );
        }
         
        if((buttons[0].getText()=="O") &&
            (buttons[4].getText()=="O") &&
            (buttons[8].getText()=="O") ){
            
            oWins(0,4,8);
        }
        
         if((buttons[2].getText()=="O") &&
            (buttons[4].getText()=="O") &&
            (buttons[6].getText()=="O") ){
            
            oWins(2,4,6);
        }
         
         
         
    }
    
    public void xWins(int a, int b, int c)
    {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        
        for(int i =0; i<9; i++){
            buttons[i].setEnabled(false);
        }
        
        textfield.setText("X Wins!!");
        
    }
    
    public void oWins(int a, int b, int c)
    {
      
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        
        for(int i =0; i<9; i++){
            buttons[i].setEnabled(false);
        }
        
        textfield.setText("O Wins!!");
    }
   
    
}
