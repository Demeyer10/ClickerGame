package Main;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

public class CookieMain {

    JLabel counterLabel, perSecondLabel;
    JPanel shopPanel;
    JButton[] buttons = new JButton[10];
    String[] buttonNames = {"Cursor", "Grandma", "Farm", "Mine", "Factory", "Bank", "Temple"};
    Timer timer;
    


    public static void main(String[] args){

        new CookieMain();

    }

    public CookieMain(){

        createButtons();

        // CREATE UI
        createUI();


        // ADD ACTION LISTEN TO ALL BUTTONS
        ClickerHandler clickerHandler = new ClickerHandler(counterLabel, perSecondLabel, buttons);
        for (int i = 0; i < buttons.length-1; i++){
            buttons[i].addActionListener(clickerHandler);
        }

        


    }


    public void createUI(){
        JFrame window = new JFrame();
        window.setSize(800,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK);
        window.setLayout(null);

        JPanel cookiePanel = new JPanel();
        cookiePanel.setBounds(200,200,200,200);
        cookiePanel.setBackground(Color.BLACK);
        window.add(cookiePanel);

        ImageIcon cookie =  new ImageIcon(getClass().getResource("/res/Cookie.png"));
        buttons[0] = new JButton();
        buttons[0].setBorder(null);
        buttons[0].setIcon(cookie);
        buttons[0].setActionCommand("Click");
        cookiePanel.add(buttons[0]);



        JPanel counterPanel = new JPanel();
        counterPanel.setBounds(100,50,200,100);
        counterPanel.setBackground(Color.BLACK);
        counterPanel.setLayout(new GridLayout(2,1));
        window.add(counterPanel);

        counterLabel = new JLabel();
        perSecondLabel = new JLabel();
        
        counterLabel.setText("Cookies: 0");
        perSecondLabel.setText("Cookies Per Second: ");
        counterLabel.setForeground(Color.WHITE);
        perSecondLabel.setForeground(Color.WHITE);
        counterPanel.add(counterLabel);
        counterPanel.add(perSecondLabel);


        JPanel shopPanel = new JPanel();
        shopPanel.setBounds(600,0,200,600);
        shopPanel.setBackground(Color.BLACK);
        window.add(shopPanel);

        shopPanel.add(buttons[1]);
        shopPanel.add(buttons[2]);
        shopPanel.add(buttons[3]);
        shopPanel.add(buttons[4]);
        shopPanel.add(buttons[5]);
        shopPanel.add(buttons[6]);
        shopPanel.add(buttons[7]);


        window.setVisible(true);
    }

    public void createButtons(){
        int y = 0;
        int i = 1;
        for (String name : buttonNames){
                buttons[i] = new JButton();
                ImageIcon icon =  new ImageIcon(getClass().getResource("/res/" + name + ".png"));
                buttons[i].setIcon(icon);
                buttons[i].setForeground(Color.WHITE);
                buttons[i].setActionCommand(name);
                buttons[i].setPreferredSize(new Dimension(200,70));
                buttons[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
                buttons[i].setBounds(0,y,200, 50);
                if (!name.equalsIgnoreCase("Cursor")){
                    buttons[i].setText("Cost: ???");
                    TitledBorder title = BorderFactory.createTitledBorder("?????");
                    title.setTitleColor(Color.WHITE);
                    title.setTitleJustification(TitledBorder.CENTER);
                    buttons[i].setBorder(title);
                }
                else{
                    buttons[i].setText("Cost: 15");
                    TitledBorder title = BorderFactory.createTitledBorder(name);
                    title.setTitleColor(Color.WHITE);
                    title.setTitleJustification(TitledBorder.CENTER);
                    buttons[i].setBorder(title);
                }
                y += 75; 
                i++;
        }
    }



}
