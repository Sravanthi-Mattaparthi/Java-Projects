package quiz;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;



public class Rules extends JFrame  implements ActionListener{
    
    JButton start,back;
    String name;  
    
    Rules(String name){
        this.name = name;
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        
        JLabel heading = new JLabel("Welcome " + name + " to Genius Minds");
        heading.setBounds(50, 20, 700, 30); 
        heading.setFont(new Font("Viner Hand ITC", Font.BOLD, 28));
        heading.setForeground(new Color(30,144,254));
        add(heading);
        
        JLabel rules = new JLabel();
        rules.setBounds(20, 90, 700, 350); 
        rules.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rules.setText(
        
                
            "<html>"+ 
                "1. You can test your Java skills with Genius Minds" + "<br><br>" +
                "2. The test contains 10 questions and there is  time limit of 15 seconds and each question carries 10 points." + "<br><br>" +
                "3. You may not go to previous question ,once you have answered the question." + "<br><br>" +
                "4. You will have a 50-50 lineline , you can use it only once." + "<br><br>" +
                "5. You cannot submit , until you finish answering all questions" + "<br><br>" +
                "6. You will be provided with 4 options , among them one will be correct option for the given question" + "<br><br>" +
                "7. At the end your score will be displayed , you can play aagin if you are not satisfied by your score" + "<br><br>" +
                "8. Answer maximum questions you can, Good Luck" + "<br><br>" +
            "<html>"      
        );
       
        add(rules);
        
        back = new JButton("Back");
        back.setBounds(250, 500, 100, 30);
        back.setBackground(new Color(30, 144, 254));
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);
        
        start = new JButton("Start");
        start.setBounds(400, 500, 100, 30);
        start.setBackground(new Color(30, 144, 254));
        start.setForeground(Color.WHITE);
        start.addActionListener(this);
        add(start);
        
       

        setSize(800,650);
        setLocation(350 ,100);
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==start){
             setVisible(false);
            new Quiz(name); 
        }else if(ae.getSource()==back){
            setVisible(false);
            new Login();
        }
    }
    
    
    public static void main (String args[]){
        new Rules("User");
        
    }
    
}
