import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import food.*;

public class Order extends JFrame implements ActionListener{
    Product list[] = Main.sampleProduct();
    Cart trolley = new Cart();

    JPanel mainContainer = new JPanel();
    CardLayout cl = new CardLayout();
    
    JPanel menuContainer = new JPanel();
    JPanel productContainer[] = new JPanel[list.length];

    JPanel modContainer = new JPanel();

    JPanel checkoutContainer = new JPanel();
    JPanel cartContainer = new JPanel();

    JLabel productName[] = new JLabel[list.length];
    JLabel productPrice[] = new JLabel[list.length];

    JButton buttons[] = new JButton[list.length];
    JButton pay = new JButton("Proceed to Payment");
    JButton fin = new JButton("Checkout");

    

    Order(){
        mainContainer.setLayout(cl);

        //=============================== Menu Container ======================================
        menuContainer.setLayout(null);
        menuContainer.setBackground(new Color(0xf0f0f0));

        //---------------------- Product List ---------------------------
        for (int i=0; i<list.length; i++){
            productName[i] = new JLabel();
            productName[i].setText(list[i].getName());
            productName[i].setFont(new Font("Arial", Font.BOLD, 22));
            productName[i].setBounds(5, 5, 390, 45);
            productName[i].setVerticalAlignment(JLabel.CENTER);
            productName[i].setHorizontalAlignment(JLabel.LEFT);
            productName[i].setOpaque(true);

            productPrice[i] = new JLabel();
            productPrice[i].setText(Integer.toString(list[i].getPrice()));
            productPrice[i].setFont(new Font("Arial", Font.PLAIN, 16));
            productPrice[i].setBounds(5, 50, 390, 45);
            productPrice[i].setVerticalAlignment(JLabel.CENTER);
            productPrice[i].setHorizontalAlignment(JLabel.LEFT);
            productPrice[i].setOpaque(true);

            buttons[i] = new JButton();
            buttons[i].setText("Add");
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 16));
            buttons[i].addActionListener(this);
            buttons[i].setBounds(400, 5, 85, 90);


            productContainer[i] = new JPanel();
            productContainer[i].setBackground(new Color(0xdff0f0));
            productContainer[i].setBounds(2, 5+(i*105),490, 100);
            productContainer[i].setLayout(null);
            productContainer[i].add(productName[i]);
            productContainer[i].add(productPrice[i]);
            productContainer[i].add(buttons[i]);

            menuContainer.add(productContainer[i]);
        }

        //------------------------------ Checkout Button -------------------------------------------
        pay.setFont(new Font("Arial", Font.PLAIN, 16));
        pay.setBackground(new Color(0x77bd79));
        pay.setForeground(new Color(0xffffff));
        pay.setBounds(5, 425, 475, 30);
        pay.addActionListener(this);

        menuContainer.add(pay);

        mainContainer.add(menuContainer, "menu");



        //=============================== Modifier Container ======================================
        modContainer.setBackground(new Color(0xf0f0f0));
        modContainer.setLayout(null);
        
        mainContainer.add(modContainer, "mod");
        


        //=============================== Checkout Container ======================================
        checkoutContainer.setBackground(new Color(0xdff0f0));
        checkoutContainer.setLayout(null);
        
        //------------------------------- Cart container ------------------------------------------
        cartContainer.setBounds(0, 0, 500, 500);
        cartContainer.setLayout(null);
        cartContainer.setOpaque(false);

        checkoutContainer.add(cartContainer);
        
        //------------------------------- Fin button ----------------------------------------------
        fin.setFont(new Font("Arial", Font.PLAIN, 16));
        fin.setBackground(new Color(0x77bd79));
        fin.setForeground(new Color(0xffffff));
        fin.setBounds(5, 425, 475, 30);
        fin.addActionListener(e -> {
            trolley.clearContent();
            cartContainer.removeAll();
            cl.show(mainContainer, "menu");
        });

        checkoutContainer.add(fin);

        mainContainer.add(checkoutContainer, "co");
        //-----------------------------------------------------------------------------------------

        cl.show(mainContainer, "menu");
        

        //========================================== JFrame ========================================
        this.add(mainContainer);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        //this.setResizable(false);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e){
        


        //================================= Checkout Container Content ======================================
        if (e.getSource() == pay){
            int subtotal = 0;
            
            //----------------------------------------- Cart Content -------------------------------------------
            for (int i=0; i<trolley.getContentSize(); i++){
                JLabel contentName = new JLabel();
                JLabel contentPrice = new JLabel();
                JLabel contentMod = new JLabel();
                JLabel contentNotes = new JLabel();
                
                Item currItem = trolley.getContent(i);

                contentName.setText(Integer.toString(currItem.getQty()) + "x "+ currItem.type.getName());
                contentName.setFont(new Font("Arial", Font.BOLD, 15));
                contentName.setBounds(2, 2, 250, 21);
                contentName.setVerticalAlignment(JLabel.TOP);
                contentName.setHorizontalAlignment(JLabel.LEFT);
                contentName.setOpaque(true);

                contentPrice.setText(Integer.toString(currItem.getTotal()));
                contentPrice.setFont(new Font("Arial", Font.PLAIN, 15));
                contentPrice.setBounds(350, 2, 100, 21);
                contentPrice.setVerticalAlignment(JLabel.CENTER);
                contentPrice.setHorizontalAlignment(JLabel.RIGHT);
                contentPrice.setOpaque(true);
                
                {
                    String temp = "";
                    for (int j=0; j<currItem.type.getModifierSize(); j++){
                        Modifier currModifier = currItem.type.getModifier(j);
                        temp += currModifier.getType() + ": " + currModifier.getVariant(currItem.getMod(j)) + " ";
                    }
                    contentMod.setText(temp);
                }
                contentMod.setFont(new Font("Arial", Font.PLAIN, 11));
                contentMod.setBounds(2, 25, 400, 16);
                contentMod.setVerticalAlignment(JLabel.CENTER);
                contentMod.setHorizontalAlignment(JLabel.LEFT);
                contentMod.setOpaque(true);

                contentNotes.setText(currItem.getNotes());
                contentNotes.setFont(new Font("Arial", Font.PLAIN, 11));
                contentNotes.setBounds(2, 41, 400, 16);
                contentNotes.setVerticalAlignment(JLabel.CENTER);
                contentNotes.setHorizontalAlignment(JLabel.LEFT);
                contentNotes.setOpaque(true);

                JPanel contentContainer = new JPanel();
                contentContainer.setLayout(null);
                contentContainer.setBounds(5, 2+(i*62), 490, 60);
                contentContainer.add(contentName);
                contentContainer.add(contentPrice);
                contentContainer.add(contentMod);
                contentContainer.add(contentNotes);

                cartContainer.add(contentContainer);

                subtotal += currItem.getTotal();
            }

            //------------------------------------ Subtotal --------------------------------------------------
            JLabel subtotalLabel = new JLabel();
            subtotalLabel.setText("Subtotal");
            subtotalLabel.setFont(new Font("Arial", Font.BOLD, 20));
            subtotalLabel.setBounds(15, 390, 290, 30);
            subtotalLabel.setVerticalAlignment(JLabel.CENTER);
            subtotalLabel.setHorizontalAlignment(JLabel.LEFT);
            subtotalLabel.setOpaque(true);

            JLabel subtotalPrice = new JLabel();
            subtotalPrice.setText(Integer.toString(subtotal));
            subtotalPrice.setFont(new Font("Arial", Font.BOLD, 20));
            subtotalPrice.setBounds(305, 390, 165, 30);
            subtotalPrice.setVerticalAlignment(JLabel.CENTER);
            subtotalPrice.setHorizontalAlignment(JLabel.RIGHT);
            subtotalPrice.setOpaque(true);

            cartContainer.add(subtotalLabel);
            cartContainer.add(subtotalPrice);

            cl.show(mainContainer, "co");
        }



        //================================== Modifier Container Content ======================================
        for (int i=0; i<buttons.length; i++){
            if (e.getSource() == buttons[i]){
                Item a = new Item(list[i]);

                //---------------------------------------- Modifier ------------------------------------------------
                int modsize = list[i].getModifierSize();

                JPanel attributeContainer[] = new JPanel[modsize];
                JPanel variantContainer[] = new JPanel[modsize];
                
                JLabel typeLabel[] = new JLabel[modsize];

                JRadioButton variantRadio[][] = new JRadioButton[modsize][];

                ButtonGroup group[] = new ButtonGroup[modsize];

                for (int j=0; j<modsize; j++){
                    Modifier currmod = list[i].getModifier(j);
                    int varsize = currmod.getVariantSize();

                    typeLabel[j] = new JLabel();
                    typeLabel[j].setText(currmod.getType());
                    typeLabel[j].setFont(new Font("Arial", Font.BOLD, 19));
                    typeLabel[j].setBounds(5, 5, 475, 30);
                    typeLabel[j].setVerticalAlignment(JLabel.CENTER);
                    typeLabel[j].setHorizontalAlignment(JLabel.LEFT);
                    typeLabel[j].setOpaque(true);

                    variantContainer[j] = new JPanel();
                    variantContainer[j].setBounds(5, 37, 475, 30);

                    variantRadio[j] = new JRadioButton[varsize];
                    group[j] = new ButtonGroup();

                    for (int k=0; k<varsize; k++){
                        variantRadio[j][k] = new JRadioButton(currmod.getVariant(k));
                        variantRadio[j][k].setFont(new Font("Arial", Font.PLAIN, 15));

                        final int currj = j, currk = k;
                        variantRadio[j][k].addActionListener(f -> a.setMod(currj, currk));

                        group[j].add(variantRadio[j][k]);

                        variantContainer[j].add(variantRadio[j][k]);
                    }  

                    attributeContainer[j] = new JPanel();
                    attributeContainer[j].setBounds(2, 5+(j*75),496, 70);
                    attributeContainer[j].setLayout(null);
                    attributeContainer[j].add(typeLabel[j]);
                    attributeContainer[j].add(variantContainer[j]);

                    modContainer.add(attributeContainer[j]);
                }

                //---------------------------------- Notes ------------------------------------
                JLabel notesLabel = new JLabel();
                notesLabel.setText("Notes");
                notesLabel.setFont(new Font("Arial", Font.BOLD, 18));
                notesLabel.setBounds(5, 5, 400, 30);
                notesLabel.setVerticalAlignment(JLabel.CENTER);
                notesLabel.setHorizontalAlignment(JLabel.LEFT);
                notesLabel.setOpaque(true);

                JTextField notesText = new JTextField();
                notesText.setBounds(5, 37, 455, 30);
                notesText.setPreferredSize(new Dimension(455, 30));
                notesText.setFont(new Font("Arial", Font.PLAIN, 14));
                notesText.setForeground(new Color(0x000000));
                notesText.setBackground(new Color(0xf0f0f0));

                JPanel notesContainer = new JPanel();
                notesContainer.setBounds(5, 320, 470, 75);
                notesContainer.setLayout(null);
                notesContainer.add(notesLabel);
                notesContainer.add(notesText);
                
                modContainer.add(notesContainer);


                //----------------------------------- Qty -------------------------------------
                JLabel qtyLabel = new JLabel();
                qtyLabel.setText(Integer.toString((a.getQty())));
                qtyLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                qtyLabel.setHorizontalAlignment(JLabel.CENTER);
                qtyLabel.setVerticalAlignment(JLabel.CENTER);
                qtyLabel.setBounds(60, 5, 50, 50);
                qtyLabel.setOpaque(true);

                JButton qtyPlus = new JButton("+");
                qtyPlus.setBounds(110, 5, 50, 50);
                qtyPlus.addActionListener(f -> {
                    a.setQty(a.getQty()+1);
                    qtyLabel.setText(Integer.toString((a.getQty())));
                });

                JButton qtyMinus = new JButton("-");
                qtyMinus.setBounds(5, 5, 50, 50);
                qtyMinus.addActionListener(f -> {
                    if (a.getQty() > 1){
                        a.setQty(a.getQty()-1);
                    }
                    qtyLabel.setText(Integer.toString((a.getQty())));
                });
                
                
                JPanel qtyContainer = new JPanel();
                qtyContainer.setLayout(null);
                qtyContainer.setBounds(5, 390, 200, 60);
                qtyContainer.add(qtyMinus);
                qtyContainer.add(qtyLabel);
                qtyContainer.add(qtyPlus);

                modContainer.add(qtyContainer);

                //------------------------------ Add to cart -------------------------------------
                JButton addToCart = new JButton("Add to Cart");
                addToCart.setFont(new Font("Arial", Font.PLAIN, 16));
                addToCart.setBackground(new Color(0x77bd79));
                addToCart.setForeground(new Color(0xffffff));
                addToCart.setBounds(245, 400, 200, 40);
                addToCart.addActionListener(f -> {
                    a.setNotes(notesText.getText());
                    trolley.addContent(a);
                    modContainer.removeAll();
                    cl.show(mainContainer, "menu");
                });

                modContainer.add(addToCart);

                cl.show(mainContainer, "mod");
            }
        }
    }
}
