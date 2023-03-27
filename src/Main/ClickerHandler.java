package Main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

public class ClickerHandler implements ActionListener {

    double numberOfCookies = 1095;
    double cookiesPerSecond = 0;
    ArrayList<Item> shopItems = new ArrayList<Item>(); 

    Item cursor = new Item("Cursor", 15, 1.2, 0.1,0,1);
    Item grandma = new Item("Grandma", 100, 1.5, 1,0,2);
    Item farm = new Item("mine", 1_100, 1.7, 8,0,3);
    Item mine = new Item("mine", 12_000, 1.9, 47,0,4);


    JLabel counterLabel, perSecondLabel;
    JButton[] buttons;
    Timer timer, shoptimer;
    DecimalFormat df2 = new DecimalFormat("0.0");

    public ClickerHandler(JLabel counterJLabel, JLabel perSecondLabel, JButton[] buttons){
        this.counterLabel = counterJLabel; 
        this.perSecondLabel = perSecondLabel;
        this.buttons = buttons;
        addShopItem();
        checkNewShopItem();
        setTimer();
        shoptimer.start();
        timer.start();
    }



    @Override
    public void actionPerformed(ActionEvent event) {


        String action = event.getActionCommand();

        switch (action){
            case "Click":
                this.addCookie();
                break;
            case "Cursor":
                if (cursor.price <= this.numberOfCookies){
                    this.buy(cursor);
                }
                break;
            case "Grandma":
                if (grandma.price <= this.numberOfCookies){
                    this.buy(grandma);
                }
                break;
            case "Farm":
                if (farm.price <= this.numberOfCookies){
                    this.buy(farm);
                }
                break;
            case "Mine":
                if (mine.price <= this.numberOfCookies){
                    this.buy(mine);
                }
                break;

        }
        
    }


    private void addCookie(){
        System.out.println(this.numberOfCookies);
        this.numberOfCookies += 1;
        counterLabel.setText("Cookies: " + (int)numberOfCookies);
    }

    private void buy(Item item){
        numberOfCookies -= item.price;
        cookiesPerSecond += item.cookiesPerSecond;
        perSecondLabel.setText("Cookes Per Second: " + df2.format(cookiesPerSecond));
        item.price *= item.priceMultiplier;
        System.out.println(item.numberOwned);
        buttons[item.buttonNumber].setText("Cost: " + item.price);
        counterLabel.setText("Cookies: " + (int)numberOfCookies);
        item.numberOwned++;
    }

    private void setTimer(){
        timer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                numberOfCookies += cookiesPerSecond;
                counterLabel.setText("Cookies: " + (int)numberOfCookies);

            }
            
        });
    }

    private void checkNewShopItem(){
            shoptimer = new Timer(0, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    for (Item item : shopItems){
                        if (numberOfCookies >= item.price){
                            TitledBorder title = BorderFactory.createTitledBorder(item.name + " " +item.numberOwned);
                            title.setTitleColor(Color.WHITE);
                            title.setTitleJustification(TitledBorder.CENTER);
                            buttons[item.buttonNumber].setBorder(title);
                            buttons[item.buttonNumber].setText("Cost: " + item.price);
                        }
                    }

                }
                
            });

    }

    public void addShopItem(){
        shopItems.add(cursor);
        shopItems.add(grandma);
        shopItems.add(farm);
        shopItems.add(mine);
    }



}


