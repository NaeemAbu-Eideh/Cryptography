
package cipherfeadback;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;







class Set extends JDialog implements ActionListener{
    
    private String textValue="";
    
    private void setTextValue(String str){
        textValue = str;
    }
    private boolean checkInteger = false,checkString = false;
    private void setCheckInteger(boolean check){
        checkInteger = check;
    }
    private void setCheckString(boolean check){
        checkString = check;
    }
    private JLabel text;
    private JTextField textField;
    private JButton ok;
    private JRadioButton string,integer;
    private ButtonGroup group;
    public boolean isInteger(){
        return checkInteger;
    }
    public boolean isString(){
        return checkString;
    }
    public String getTextValue(){
        return textValue;
    }
    public Set(String title,String label){
        super();
        setTitle(title);
        text = new JLabel(label);
        ok = new JButton("OK");
        textField = new JTextField(30);
        string = new JRadioButton("String");
        integer = new JRadioButton("Integer");
        group = new ButtonGroup();
        group.add(string);
        group.add(integer);
        string.setSelected(true);
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(integer.isSelected()){
                    if(!(e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyChar() == ' ')){
                        e.consume();
                    }
                }
                else{
                    
                }
                
            }
        });
        JPanel n,s,c;
        n = new JPanel();s = new JPanel();c = new JPanel();
        n.add(text);
        n.add(textField);
        s.add(ok);
        c.add(integer);
        c.add(string);
        add(n,BorderLayout.NORTH);
        add(c,BorderLayout.CENTER);
        add(s,BorderLayout.SOUTH);
        ok.addActionListener(this);
        setSize(400,200);
        setLocation(700,300);
        setModal(true);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public JRadioButton getIntegerAddress(){
        return integer;
    }
    public JRadioButton getStringAddress(){
        return string;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object t = e.getSource();
        
        if(t == ok){
            String str = textField.getText();
            setTextValue(str);
            if(integer.isSelected()){setCheckInteger(true);setCheckString(false);}
            else if(string.isSelected()){setCheckInteger(false);setCheckString(true);}
            dispose();
        }
        else if(t == textField){
            ok.doClick();
        }
    }
}


class RandomFrame extends JDialog implements ActionListener{
       private JButton random = null,set = null,cancel = null;
       private boolean chackRandom = false,checkSet = false;
       private void setRandom(boolean check){
           chackRandom = check;
       }
       private void setSet(boolean check){
           checkSet = check;
       }
       
       public boolean isRandom(){
           return chackRandom;
       }
       public boolean isSet(){
           return checkSet;
       }
       
       public RandomFrame(String title, String randomButtonName, String setButtonName){
           super();
           
           setTitle(title);
           random = new JButton(randomButtonName);
           set = new JButton(setButtonName);
           cancel = new JButton("Cancel");
           JPanel c = new JPanel();
           c.add(random);
           c.add(set);
           c.add(cancel);
           add(c,BorderLayout.CENTER);
           random.addActionListener(this);
           set.addActionListener(this);
           cancel.addActionListener(this);
           setSize(400,100);
           setLocation(800,400);
           setModal(true);
           setVisible(true);
           setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object t = e.getSource();
        if(t == random){
            setRandom(true);
            setSet(false);
            dispose();
        }
        else if(t == set){
            setRandom(false);
            setSet(true);
            dispose();
        }
        else if (t == cancel){
            setRandom(false);
            setSet(false);
            dispose();
        }
    }
}
