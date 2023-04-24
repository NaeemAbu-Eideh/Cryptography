
package cipherfeadback;
import java.util.Random;
import java.util.Vector;
import javax.swing.JOptionPane;
/**
 *
 * @author AB
 */
public class CipherFeadBack extends javax.swing.JFrame {
    private void set(Vector<Integer> vec,int num){
       String binary = "";
       while(num!=0){
           int i = num%2;
           binary+=String.valueOf(i);
           num/=2;
       }
       while(binary.length() < 8)
           binary+='0';
       for(int i=binary.length()-1;i>=0;i--)
           vec.add(Integer.parseInt(String.valueOf(binary.charAt(i))));
    }
    private String inetialVector = "";
    private void setInetialVector(String str){
        inetialVector = str;
    }
    public void set(Vector<Integer> vec) {
        Random rand = new Random();
	for (int i = 0; i < 64; i++) {
		int x = rand.nextInt(1000)% 2;
		vec.add(x);
	}

}
    public void set(Vector<Integer> vec, String text) {
           for(int i=0;i<text.length();i++){
               int x = (int)text.charAt(i);
               if(x < 0) x+=256;
               String binary="";
               while(x!= 0){
                   binary+= String.valueOf(x%2);
                   x/=2;
               }
               while(binary.length() != 8)
                   binary+='0';
               for(int j=binary.length()-1;j>=0;j--)
                   vec.add(Integer.parseInt(String.valueOf(binary.charAt(j))));       
       } 
        int size = vec.size();
        while(size < 64){
            vec.add(0);
            size++;
        }   
    }
    public void get(Vector<Integer> vec) {
	for (int i = 0; i < vec.size(); i++) {
		          System.out.println("" + vec.get(i) + "\n");
	}
	System.out.print("\n");
}
    public void copy(Vector<Integer>vec1, Vector<Integer> vec2) {
	for (int i = 0; i < vec1.size(); i++) {
		vec2.add(vec1.get(i));
	}
}
    public void encryption(Vector<Integer>iv,Vector<Integer>key,Vector<Integer>plain,Vector<Integer>cipher){
        Vector<Integer> p1,p2,p3,p4;
        p1 = new Vector<Integer>();p2 = new Vector<Integer>();p3 = new Vector<Integer>();p4 = new Vector<Integer>();
        for(int i=0;i<16;i++)p1.add(plain.get(i));
        for(int i=16;i<32;i++)p2.add(plain.get(i));
        for(int i=32;i<48;i++)p3.add(plain.get(i));
        for(int i=48;i<64;i++)p4.add(plain.get(i));
        //------
        Vector<Integer>iv2 = new Vector<Integer>();
        copy(iv,iv2);
        //-------
        Vector<Integer>Xor,sBits,C;
        Xor = new Vector<Integer>();sBits = new Vector<Integer>();C = new Vector<Integer>();
        //------
        for(int i=0;i<iv2.size() && i<key.size();i++)
            Xor.add(key.get(i) ^ iv2.get(i));
        for(int i=0;i<16;i++)
            sBits.add(Xor.get(i));
        for(int i=0;i<p1.size();i++)
            C.add(p1.get(i) ^ sBits.get(i));
        for(int i=0;i<C.size();i++)
            cipher.add(C.get(i));
        for(int i=0;i<48;i++)
            iv2.set(i, iv2.get(i+1));
        for(int i=iv2.size()-1;i>=48;i--)
            iv2.remove(i);
        for(int i=0;i<C.size();i++)
            iv2.add(C.get(i));
        sBits.clear();
        Xor.clear();
        C.clear();
        //------
        for(int i=0;i<iv2.size() && i<key.size();i++)
            Xor.add(key.get(i) ^ iv2.get(i));
        for(int i=0;i<16;i++)
            sBits.add(Xor.get(i));
        for(int i=0;i<p2.size();i++)
            C.add(p2.get(i) ^ sBits.get(i));
        for(int i=0;i<C.size();i++)
            cipher.add(C.get(i));
        for(int i=0;i<48;i++)
            iv2.set(i, iv2.get(i+1));
        for(int i=iv2.size()-1;i>=48;i--)
            iv2.remove(i);
        for(int i=0;i<C.size();i++)
            iv2.add(C.get(i));
        sBits.clear();
        Xor.clear();
        C.clear();
        //------
        for(int i=0;i<iv2.size() && i<key.size();i++)
            Xor.add(key.get(i) ^ iv2.get(i));
        for(int i=0;i<16;i++)
            sBits.add(Xor.get(i));
        for(int i=0;i<p3.size();i++)
            C.add(p3.get(i) ^ sBits.get(i));
        for(int i=0;i<C.size();i++)
            cipher.add(C.get(i));
        for(int i=0;i<48;i++)
            iv2.set(i, iv2.get(i+1));
        for(int i=iv2.size()-1;i>=48;i--)
            iv2.remove(i);
        for(int i=0;i<C.size();i++)
            iv2.add(C.get(i));
        sBits.clear();
        Xor.clear();
        C.clear();
        //------
        for(int i=0;i<iv2.size() && i<key.size();i++)
            Xor.add(key.get(i) ^ iv2.get(i));
        for(int i=0;i<16;i++)
            sBits.add(Xor.get(i));
        for(int i=0;i<p4.size();i++)
            C.add(p4.get(i) ^ sBits.get(i));
        for(int i=0;i<C.size();i++)
            cipher.add(C.get(i));
        for(int i=0;i<48;i++)
            iv2.set(i, iv2.get(i+1));
        for(int i=iv2.size()-1;i>=48;i--)
            iv2.remove(i);
        for(int i=0;i<C.size();i++)
            iv2.add(C.get(i));
        sBits.clear();
        Xor.clear();
        C.clear();
        //------
    }
    public void decryption(Vector<Integer>iv,Vector<Integer>key,Vector<Integer>plain,Vector<Integer>cipher){
        Vector<Integer> c1,c2,c3,c4;
        c1 = new Vector<Integer>();c2 = new Vector<Integer>();c3 = new Vector<Integer>();c4 = new Vector<Integer>();
        for(int i=0;i<16;i++)c1.add(cipher.get(i));
        for(int i=16;i<32;i++)c2.add(cipher.get(i));
        for(int i=32;i<48;i++)c3.add(cipher.get(i));
        for(int i=48;i<64;i++)c4.add(cipher.get(i));
        //------
        Vector<Integer>iv2 = new Vector<Integer>();
        copy(iv,iv2);
        //-------
        Vector<Integer>Xor,sBits,P;
        Xor = new Vector<Integer>();sBits = new Vector<Integer>();P = new Vector<Integer>();
        //------
        for(int i=0;i<iv2.size() && i<key.size();i++)
            Xor.add(key.get(i) ^ iv2.get(i));
        for(int i=0;i<16;i++)
            sBits.add(Xor.get(i));
        for(int i=0;i<c1.size();i++)
            P.add(c1.get(i) ^ sBits.get(i));
        for(int i=0;i<P.size();i++)
            plain.add(P.get(i));
        for(int i=0;i<48;i++)
            iv2.set(i, iv2.get(i+1));
        for(int i=iv2.size()-1;i>=48;i--)
            iv2.remove(i);
        for(int i=0;i<P.size();i++)
            iv2.add(P.get(i));
        sBits.clear();
        Xor.clear();
        P.clear();
        //------
        for(int i=0;i<iv2.size() && i<key.size();i++)
            Xor.add(key.get(i) ^ iv2.get(i));
        for(int i=0;i<16;i++)
            sBits.add(Xor.get(i));
        for(int i=0;i<c2.size();i++)
            P.add(c2.get(i) ^ sBits.get(i));
        for(int i=0;i<P.size();i++)
            plain.add(P.get(i));
        for(int i=0;i<48;i++)
            iv2.set(i, iv2.get(i+1));
        for(int i=iv2.size()-1;i>=48;i--)
            iv2.remove(i);
        for(int i=0;i<P.size();i++)
            iv2.add(P.get(i));
        sBits.clear();
        Xor.clear();
        P.clear();
        //------
        for(int i=0;i<iv2.size() && i<key.size();i++)
            Xor.add(key.get(i) ^ iv2.get(i));
        for(int i=0;i<16;i++)
            sBits.add(Xor.get(i));
        for(int i=0;i<c3.size();i++)
            P.add(c3.get(i) ^ sBits.get(i));
        for(int i=0;i<P.size();i++)
            plain.add(P.get(i));
        for(int i=0;i<48;i++)
            iv2.set(i, iv2.get(i+1));
        for(int i=iv2.size()-1;i>=48;i--)
            iv2.remove(i);
        for(int i=0;i<P.size();i++)
            iv2.add(P.get(i));
        sBits.clear();
        Xor.clear();
        P.clear();
        //------
        for(int i=0;i<iv2.size() && i<key.size();i++)
            Xor.add(key.get(i) ^ iv2.get(i));
        for(int i=0;i<16;i++)
            sBits.add(Xor.get(i));
        for(int i=0;i<c4.size();i++)
            P.add(c4.get(i) ^ sBits.get(i));
        for(int i=0;i<P.size();i++)
            plain.add(P.get(i));
        for(int i=0;i<48;i++)
            iv2.set(i, iv2.get(i+1));
        for(int i=iv2.size()-1;i>=48;i--)
            iv2.remove(i);
        for(int i=0;i<P.size();i++)
            iv2.add(P.get(i));
        sBits.clear();
        Xor.clear();
        P.clear();
        //------
    }
    public String getInetialVector(){
        return inetialVector;
    }    
    
    
    public String binaryToString(Vector<Integer>cipher) {
	String n = "";
	double sum = 0;int size = 7;
	for (int i = 0; i < cipher.size();) {
		for (int j = 0; j < 8; j++) {
                        sum += (Double.parseDouble(String.valueOf(cipher.get(i)))) * Math.pow(2, size);
			i++;
			size--;
		}
                
		n += (char)((int)sum);
		sum = 0;
		size = 7;
	}
	return n;
}
    public CipherFeadBack() {
        IV = new Vector<Integer>();
        KEY = new Vector<Integer>();
        plaintext = new Vector<Integer>();
        ciphertext = new Vector<Integer>();
        //------
        
        initComponents();
        set(IV);
        setInetialVector(binaryToString(IV));
        set(KEY);
        String Key = binaryToString(KEY);
        key.setText(Key);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groub = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        value = new javax.swing.JTextField();
        textValue = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        textSolution = new javax.swing.JLabel();
        solution = new javax.swing.JLabel();
        textKey = new javax.swing.JLabel();
        key = new javax.swing.JLabel();
        ok = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        changeKey = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        enc = new javax.swing.JRadioButton();
        dec = new javax.swing.JRadioButton();
        changeIV = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 51));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        textValue.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        textValue.setForeground(new java.awt.Color(255, 255, 255));
        textValue.setText("Plaintext: ");

        textSolution.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        textSolution.setForeground(new java.awt.Color(255, 255, 255));
        textSolution.setText("Ciphertext: ");

        solution.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        solution.setForeground(new java.awt.Color(255, 255, 255));

        textKey.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        textKey.setForeground(new java.awt.Color(255, 255, 255));
        textKey.setText("Key: ");

        key.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        key.setForeground(new java.awt.Color(255, 255, 255));

        ok.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        ok.setForeground(new java.awt.Color(0, 0, 0));
        ok.setText("OK");
        ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okActionPerformed(evt);
            }
        });

        clear.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        clear.setForeground(new java.awt.Color(0, 0, 0));
        clear.setText("Clear");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        changeKey.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        changeKey.setForeground(new java.awt.Color(0, 0, 0));
        changeKey.setText("change key");
        changeKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeKeyActionPerformed(evt);
            }
        });

        groub.add(enc);
        enc.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        enc.setForeground(new java.awt.Color(255, 255, 255));
        enc.setSelected(true);
        enc.setText("Encryption");

        groub.add(dec);
        dec.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        dec.setForeground(new java.awt.Color(255, 255, 255));
        dec.setText("Decryption");

        changeIV.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        changeIV.setText("change IV");
        changeIV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeIVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(textSolution, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(solution, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(textKey, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(key, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(textValue, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(value, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(ok, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68)
                                .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addComponent(changeKey, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(changeIV, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 50, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(enc, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(187, 187, 187)
                .addComponent(dec, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(value, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textValue, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dec, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textKey, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(key, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textSolution, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(solution, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(99, 99, 99)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ok, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changeKey, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changeIV, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        solution.setText("");
        value.setText("");
        enc.setSelected(true);
    }//GEN-LAST:event_clearActionPerformed

    private void changeKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeKeyActionPerformed
        RandomFrame rf = new RandomFrame("change Key", "Random Key", "Enter Key");
        KEY.clear();
        if(rf.isRandom() && !rf.isSet()){
           String Key=""; 
            set(KEY);
            Key+=binaryToString(KEY);
            key.setText(Key);
        }
        else if(!rf.isRandom() && rf.isSet()){
            Set s = new Set("Enter key","key: ");
            String n = s.getTextValue();
            if(s.isString() && !s.isInteger()){
                while(n.length() > 8){
                    JOptionPane.showMessageDialog(this, "the length of key is mor than 64-bit (8 characters), please reenter the key","Error",JOptionPane.OK_OPTION);
                    Set st1 = new Set("Enter Key", "Key: ");
                    n = st1.getTextValue();                    
                }
                System.out.println("n = " + n);
                for(int i=0;i<n.length();i++){
                    int x = (int)n.charAt(i);
                    set(KEY,x);
                   
                }
                set(KEY,"");
                key.setText(n);
            }
            else if(!s.isString() && s.isInteger()){
                n+=' ';
                int count = 0;
                for(int i=0;i<n.length();){
                  if(n.charAt(i) == ' ')i++;
                  else{
                      while(n.charAt(i) != ' ')i++;
                      count++;
                  }
                }
                while(count > 8){
                    JOptionPane.showMessageDialog(this, "the length of key is mor than 64-bit (8 characters), please reenter the key","Error",JOptionPane.OK_OPTION);
                    Set st1 = new Set("Enter the Key","Key: ");
                    n=st1.getTextValue();
                    count=0;
                    for(int i=0;i<n.length();){
                  if(n.charAt(i) == ' ')i++;
                  else{
                      while(n.charAt(i) != ' ')i++;
                      count++;
                  }
                }
                }
                count = 0;
                String str="";
                for(int i=0;i<n.length();){
                  if(n.charAt(i) == ' ')i++;
                  else{
                      while(n.charAt(i) != ' '){
                        str+=n.charAt(i);
                        i++;
                      }
                      int num = Integer.parseInt(str);
                      set(KEY,num);
                      str = "";
                  }
                }
                int num = KEY.size();
                while(num < 64){
                    KEY.add(0);
                    num++;
                }
                str="";
                str+=binaryToString(KEY);
                key.setText(str);
                
                
            }
        }
    }//GEN-LAST:event_changeKeyActionPerformed

    private void okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okActionPerformed
        if(enc.isSelected() == true){
            textValue.setText("Plaintext: ");
            textSolution.setText("Ciphertext: ");
            CT = "";
            //--------
            String str = value.getText(); 
            for(int i=0;i<str.length();){
                String n = "";
                while(n.length() < 8 && i<str.length()){
                    n+=str.charAt(i);
                    i++;
                }
                set(plaintext,n);
                encryption(IV, KEY, plaintext, ciphertext);
                CT += binaryToString(ciphertext);
                ciphertext.clear();
                plaintext.clear();
            }
            solution.setText(CT);
        }
        else if(dec.isSelected() == true){
            ciphertext.clear();
            plaintext.clear();
            textValue.setText("Ciphertext: ");
            textSolution.setText("Plaintext: ");
            value.setText(CT);
            PT = "";
            //--------
            String str = value.getText(); 
            for(int i=0;i<str.length();){
                String n = "";
                while(n.length() < 8 && i<str.length()){
                    n+=str.charAt(i);
                    i++;
                }
                set(ciphertext,n);
                decryption(IV, KEY, plaintext, ciphertext);
                PT += binaryToString(plaintext);
                ciphertext.clear();
                plaintext.clear();
            }
            solution.setText(PT);
        }
        
        
        
        
    }//GEN-LAST:event_okActionPerformed

    private void changeIVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeIVActionPerformed
        RandomFrame rf = new RandomFrame("change IV", "Random IV", "Enter IV");
        IV.clear();
        if(rf.isRandom() && !rf.isSet()){
           String iv=""; 
            set(IV);
            iv+=binaryToString(IV);
            JOptionPane.showMessageDialog(this, "IV in characters = " + iv,"IV",JOptionPane.OK_OPTION);
        }
        else if(!rf.isRandom() && rf.isSet()){
            Set st = new Set("Enter IV","IV = ");
            String n = st.getTextValue();
            if(st.isString() && !st.isInteger()){
                while(n.length() > 8){
                    JOptionPane.showMessageDialog(this, "the length of iv is mor than 64-bit (8 characters), please reenter the iv","Error",JOptionPane.OK_OPTION);
                    Set st1 = new Set("Enter IV", "IV: ");
                    n = st1.getTextValue();                    
                }
                for(int i=0;i<n.length();i++){
                    int x = (int)n.charAt(i);
                    set(IV,x);
                }
                set(IV,"");
                
                JOptionPane.showMessageDialog(this, "IV in characters = " + n,"IV",JOptionPane.OK_OPTION);
            }
            else if(!st.isString() && st.isInteger()){
                n+=' ';
                int count = 0;
                for(int i=0;i<n.length();){
                  if(n.charAt(i) == ' ')i++;
                  else{
                      while(n.charAt(i) != ' ')i++;
                      count++;
                  }
                }
                while(count > 8){
                    JOptionPane.showMessageDialog(this, "the length of iv is mor than 64-bit (8 characters), please reenter the iv","Error",JOptionPane.OK_OPTION);
                    Set st1 = new Set("Enter the IV","IV: ");
                    n=st1.getTextValue();
                    count=0;
                    for(int i=0;i<n.length();){
                  if(n.charAt(i) == ' ')i++;
                  else{
                      while(n.charAt(i) != ' ')i++;
                      count++;
                  }
                }
                }
                count = 0;
                String str="";
                for(int i=0;i<n.length();){
                  if(n.charAt(i) == ' ')i++;
                  else{
                      while(n.charAt(i) != ' '){
                        str+=n.charAt(i);
                        i++;
                      }
                      int num = Integer.parseInt(str);
                      set(IV,num);
                      str = "";
                  }
                }
                int num = IV.size();
                while(num < 64){
                    IV.add(0);
                    num++;
                }
                str="";
                str+=binaryToString(IV);
                
                JOptionPane.showMessageDialog(this, "IV in characters = " + str,"IV",JOptionPane.OK_OPTION);
                
                
            }
        }
        
        
        
        
    }//GEN-LAST:event_changeIVActionPerformed

    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CipherFeadBack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CipherFeadBack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CipherFeadBack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CipherFeadBack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CipherFeadBack CFB = new CipherFeadBack();
                CFB.setVisible(true);
                JOptionPane.showMessageDialog(CFB, "IV in characters = " + CFB.getInetialVector(), "IV", JOptionPane.OK_OPTION);
            }
        });
    }

    
    
    Vector<Integer> IV,plaintext,ciphertext,KEY;
    String PT="",CT="";
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton changeIV;
    private javax.swing.JButton changeKey;
    private javax.swing.JButton clear;
    private javax.swing.JRadioButton dec;
    private javax.swing.JRadioButton enc;
    private javax.swing.ButtonGroup groub;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel key;
    private javax.swing.JButton ok;
    private javax.swing.JLabel solution;
    private javax.swing.JLabel textKey;
    private javax.swing.JLabel textSolution;
    private javax.swing.JLabel textValue;
    private javax.swing.JTextField value;
    // End of variables declaration//GEN-END:variables
}
