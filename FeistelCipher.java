package feistelcipher;

import java.util.Random;
import javax.swing.JOptionPane;


public class FeistelCipher extends javax.swing.JFrame {

    
    public  String getKey(){
    String key = "";
    for(int i=0;i<4;i++){
    Random rand = new Random();
    int randomNumber = (rand.nextInt(100) + 1) %2;
    key += String.valueOf(randomNumber);
    }
    return key;
}
    public void setKeyRounds(String key){
      keyRounds[0] = key;
        for(int i=1;i<16;i++){
             String key2="";
             for(int j=0;j<4;j++){
                 if(key.charAt(j) == '0') key2+='1';
                 else key2+='0';
             }
             keyRounds[i] = key2;
             key = key2;
        }    
    }
    public void getKeys(){
	for (int i = 0; i < 16; i++)
	     System.out.print("" + keyRounds[i] + " ");
        System.out.print("\n");
	
}
    public String cutString(String str, int i, int f) {
	String part = "";
	for (int j = i; j < f; j++)part += str.charAt(j);
	return part;
}
    public String getCiphertext(String str){
        String cipher="";
        for(int i=0;i<str.length();i++){
            int x = (int)str.charAt(i);
            if(x < 0) x+= 256;
            String binary1="",binary2="";
            while(x!=0){
                int a = x%2;
                binary1+=a;
                x/=2;
            }
            int s = binary1.length();
            while(s%8 != 0){
                binary2+=0;
                s++;
            }
            for(int j = binary1.length()-1;j>=0;j--) binary2+=binary1.charAt(j);
            String part1,part2;
            part1 = cutString(binary2, 0, 4);
            part2 = cutString(binary2, 4, 8);
            for(int j = 0;j < keyRounds.length;j++){
                String KEY = keyRounds[i];
                String swap = part2;
                String xor1="",xor2="";
                for(int k = 0;k<4;k++){
                    int a,b,c;
                    a = Integer.parseInt(String.valueOf(part2.charAt(k)));
                    b = Integer.parseInt(String.valueOf(KEY.charAt(k)));
                    c = a^b;
                    xor1+=c;
                }
                for(int k = 0;k<4;k++){
                    int a,b,c;
                    a = Integer.parseInt(String.valueOf(part1.charAt(k)));
                    b = Integer.parseInt(String.valueOf(xor1.charAt(k)));
                    c = a^b;
                    xor2+=c;
                }
                part2 = xor2;
                part1 = swap;
                    
            }
            String text = "";
            text+=part1;
            text+=part2;
            double sum=0,size = 7;
            for(int k = 0;k<text.length();k++){
                double a = Integer.parseInt(String.valueOf(text.charAt(k)));
                a = a * Math.pow(2, size);
                size--;
                sum += a;
            }
            cipher += (char) sum;
            
        }
        System.out.println("ciphertext = " + cipher + "\n");
        
        
        
        return cipher;
    }
    public String getPlaintext(String str){
        String plain="";
        for(int i=0;i<str.length();i++){
            int x = (int)str.charAt(i);
            if(x < 0) x+= 256;
            String binary1="",binary2="";
            while(x!=0){
                int a = x%2;
                binary1+=a;
                x/=2;
            }
            int s = binary1.length();
            while(s%8 != 0){
                binary2+=0;
                s++;
            }
            for(int j = binary1.length()-1;j>=0;j--) binary2+=binary1.charAt(j);
            String part1,part2;
            part1 = cutString(binary2, 0, 4);
            part2 = cutString(binary2, 4, 8);
            for(int j = keyRounds.length-1;j>=0;j--){
                String KEY = keyRounds[i];
                String swap = part1;
                String xor1="",xor2="";
                for(int k = 0;k<4;k++){
                    int a,b,c;
                    a = Integer.parseInt(String.valueOf(part2.charAt(k)));
                    b = Integer.parseInt(String.valueOf(part1.charAt(k)));
                    c = a^b;
                    xor1+=c;
                }
                for(int k = 0;k<4;k++){
                    int a,b,c;
                    a = Integer.parseInt(String.valueOf(KEY.charAt(k)));
                    b = Integer.parseInt(String.valueOf(xor1.charAt(k)));
                    c = a^b;
                    xor2+=c;
                }
                part1 = xor2;
                part2 = swap;
                    
            }
            String text = "";
            text+=part1;
            text+=part2;
            double sum=0,size = 7;
            for(int k = 0;k<text.length();k++){
                double a = Integer.parseInt(String.valueOf(text.charAt(k)));
                a = a * Math.pow(2, size);
                size--;
                sum += a;
            }
            plain += (char) sum;
            
        }
        System.out.println("ciphertext = " + plain + "\n");
        
        
        
        return plain;
    }
    
    public FeistelCipher() {
        super("Feistel Cipher");
    
        initComponents();
        String key = "";
	 key = getKey();
	 setKeyRounds(key); 
         textKey.setText("key: " + keyRounds[0]);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        group = new javax.swing.ButtonGroup();
        jPanel = new javax.swing.JPanel();
        textValue = new javax.swing.JLabel();
        value = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        RB1 = new javax.swing.JRadioButton();
        RB2 = new javax.swing.JRadioButton();
        jSeparator2 = new javax.swing.JSeparator();
        textSolution = new javax.swing.JLabel();
        solution = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        ok = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        changeKey = new javax.swing.JButton();
        textKey = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        jPanel.setBackground(new java.awt.Color(0, 0, 0));

        textValue.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        textValue.setForeground(new java.awt.Color(255, 255, 255));
        textValue.setText("enter a text: ");

        group.add(RB1);
        RB1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        RB1.setForeground(new java.awt.Color(255, 255, 255));
        RB1.setSelected(true);
        RB1.setText("Encryption");
        RB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RB1ActionPerformed(evt);
            }
        });

        group.add(RB2);
        RB2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        RB2.setForeground(new java.awt.Color(255, 255, 255));
        RB2.setText("Decryption");
        RB2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RB2ActionPerformed(evt);
            }
        });

        textSolution.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        textSolution.setForeground(new java.awt.Color(255, 255, 255));

        solution.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        solution.setForeground(new java.awt.Color(255, 255, 255));

        ok.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        ok.setForeground(new java.awt.Color(0, 0, 0));
        ok.setText("OK");
        ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okActionPerformed(evt);
            }
        });

        clear.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        clear.setForeground(new java.awt.Color(0, 0, 0));
        clear.setText("Clear");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        changeKey.setForeground(new java.awt.Color(0, 0, 0));
        changeKey.setText("Change Key");
        changeKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeKeyActionPerformed(evt);
            }
        });

        textKey.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        textKey.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(RB1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RB2)
                .addGap(80, 80, 80))
            .addComponent(jSeparator2)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(textValue, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(value, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textKey, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(textSolution, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                                .addComponent(solution, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addComponent(jSeparator3)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(ok, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(changeKey, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textValue, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(value, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RB1)
                    .addComponent(RB2))
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(textKey, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textSolution, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(solution, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 110, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ok, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(changeKey, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RB1ActionPerformed
       if(RB1.isSelected()) select="Encryption";
    }//GEN-LAST:event_RB1ActionPerformed

    private void RB2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RB2ActionPerformed
        if(RB1.isSelected()) select="Decryption";
    }//GEN-LAST:event_RB2ActionPerformed

    private void okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okActionPerformed
        String str = value.getText();
        if(str.isEmpty()){
            JOptionPane.showMessageDialog(null, "please enter a text to encrypte or decrypte it.", "warning",0);
        }
        else{
            if(RB1.isSelected() == true){
            textSolution.setText("");
            solution.setText("");
            //========
            ciphertext = "";
            ciphertext = getCiphertext(str);
            textSolution.setText("ciphertext: ");
            solution.setText(ciphertext);
       }
        else if(RB2.isSelected() == true){
            textSolution.setText("");
            solution.setText("");
            //========
            plaintext = "";
            plaintext = getPlaintext(ciphertext);
            value.setText(ciphertext);
            textSolution.setText("plaintext: ");
            solution.setText(plaintext);
            
        }
        }
        
            
        
    }//GEN-LAST:event_okActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        value.setText("");
        textSolution.setText("");
        solution.setText("");
    }//GEN-LAST:event_clearActionPerformed

    private void changeKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeKeyActionPerformed
         String key = "";
	 key = getKey();
	 setKeyRounds(key); 
         textKey.setText("key: " + keyRounds[0]);
    }//GEN-LAST:event_changeKeyActionPerformed

    
    
    
    
    
    
    

    public static void main(String args[]) {
        
      
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FeistelCipher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FeistelCipher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FeistelCipher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FeistelCipher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FeistelCipher().setVisible(true);
            }
        });
    }
    String ciphertext="",plaintext="";
    String select="Encryption";
    String []keyRounds = new String[16];
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton RB1;
    private javax.swing.JRadioButton RB2;
    private javax.swing.JButton changeKey;
    private javax.swing.JButton clear;
    private javax.swing.ButtonGroup group;
    private javax.swing.JPanel jPanel;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JButton ok;
    private javax.swing.JLabel solution;
    private javax.swing.JLabel textKey;
    private javax.swing.JLabel textSolution;
    private javax.swing.JLabel textValue;
    private javax.swing.JTextField value;
    // End of variables declaration//GEN-END:variables
}
