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

    double numberOfCookies = 0;
    double cookiesPerSecond = 0;
    ArrayList<Item> shopItems = new ArrayList<Item>(); 

    Item cursor = new Item("Cursor", 15, 1.065, 0.1,0,1);
    Item grandma = new Item("Grandma", 100, 1.065, 1,0,2);
    Item farm = new Item("Farm", 1_100, 1.065, 8,0,3);
    Item mine = new Item("Mine", 12_000, 1.065, 47,0,4);
    Item factory = new Item("Factory", 130_000, 1.065, 260,0,5);
    Item bank = new Item("Bank", 1_400_000, 1.065, 1_400,0,6);
    Item temple = new Item("Temple", 20_000_000, 1.065, 7_800,0,7);


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
            case "Factory":
                if (factory.price <= this.numberOfCookies){
                    this.buy(factory);
                }
                break;
            case "Bank":
                if (bank.price <= this.numberOfCookies){
                    this.buy(bank);
                }
                break;
            case "Temple":
                if (temple.price <= this.numberOfCookies){
                    this.buy(temple);
                }
                break;

        }
        
    }


    private void addCookie(){
        this.numberOfCookies += 1;
        counterLabel.setText("Cookies: " + (int)numberOfCookies);
    }

    private void buy(Item item){
        item.numberOwned++;
        numberOfCookies -= item.price;
        cookiesPerSecond += item.cookiesPerSecond;
        perSecondLabel.setText("Cookes Per Second: " + df2.format(cookiesPerSecond));
        item.price *= item.priceMultiplier;
        buttons[item.buttonNumber].setText("Cost: " + (int)item.price);
        counterLabel.setText("Cookies: " + (int)numberOfCookies);
        TitledBorder title = BorderFactory.createTitledBorder(item.name + " " + item.numberOwned);
        title.setTitleColor(Color.WHITE);
        title.setTitleJustification(TitledBorder.CENTER);
        buttons[item.buttonNumber].setBorder(title);

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
                            TitledBorder title = BorderFactory.createTitledBorder(item.name + " " + item.numberOwned);
                            title.setTitleColor(Color.WHITE);
                            title.setTitleJustification(TitledBorder.CENTER);
                            buttons[item.buttonNumber].setBorder(title);
                            buttons[item.buttonNumber].setText("Cost: " + (int)item.price);
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
        shopItems.add(factory);
        shopItems.add(bank);
        shopItems.add(temple);
    }



}


