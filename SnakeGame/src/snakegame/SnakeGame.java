package snakegame;
 
import javax.swing.*;

public class SnakeGame extends JFrame {
    
    SnakeGame(){  //default constructor
        
        super("Snake Game");       //used to give name on the frame 
        add(new Board());               //constructor in board class in board.java when add it applied it treats like component 
        pack();                         //used to refresh the frame whenver changes are made.
        
        //setSize(500,500);    //making the required frame size based on length and height
        setLocationRelativeTo(null); //based on screensize it will analyze and align the frame to centre
        //setLocation(700,200);        //by default the frmae will be at top left , we are trying to align it as per requirement
        setResizable(false);
        //setVisible(true);            //by deafult the frame will be invisible we make it visible by taking it true
        
    }
    
      

    public static void main(String[] args) {
        
        new SnakeGame().setVisible(true); //object creation
       
    }
    
}
