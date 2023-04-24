
package rc4;
import java.util.Random;
import java.util.Vector;


public class RC4 extends javax.swing.JFrame {
    public void copy(Vector<Integer> v1, Vector<Integer> v2) {
	for (int i = 0; i < v1.size(); i++)
		v2.add(v1.get(i));
}
    public void setS() {
	for (int i = 0; i < 256; i++) {
		S.add(i);
	}
}
    public void setT() {
	for (int i = 0,j=0; i < 256; i++,j++) {
                int x = j%S.size();
                T.add(S.get(x));
	}
}
    public void setKey() {
        Random rand = new Random();
	int size = rand.nextInt(10000) %256;
	for (int i = 0; i < size; i++) {
		int x = rand.nextInt(10000)% 256;
		KEY.add(x);
	}
}
    public void generatS(Vector<Integer> vec1, Vector<Integer>vec2) {
	int j = 0;
	for (int i = 0; i < vec1.size(); i++) {
		j = (j + vec1.get(i) + vec2.get(i)) % vec1.size();
		int swap = vec1.get(i);
		vec1.set(i, vec1.get(j));
		vec1.set(j, swap);
	}
}
    public void setKeyStream(Vector<Integer> vec,int sizeoftext) {
	copy(vec, s);
	int i = 0, j = 0;
	int l = 0;
	while (l < sizeoftext) {
		i = (i + 1) % 256;
		j = (j + s.get(i)) % 256;
		int swap = s.get(i);
                s.set(i, s.get(j));
		s.set(j, swap);
		int t = (s.get(i) + s.get(j)) % 256;
		keyStream.add(S.get(t));
		l++;
	}
	s.clear();
}
    public void print(Vector<Integer>vec) {
	for (int i = 0; i < vec.size(); i++)
		          System.out.println("" + vec.get(i) + " ");
	       System.out.println("\n");
}
    public void getVector(String text, Vector<Integer> vec) {
	for (int i = 0; i < text.length(); i++) {
		int x = (int)text.charAt(i);
		if (x < 0)x += 256;
		vec.add(x);
	}
	
}
    public String getText(Vector<Integer> vec) {
	String text = "";
	for (int i = 0; i < vec.size(); i++){
            int x = vec.get(i);
            text+= (char)x;
        }
		
	return text;
}
    public void Xor(Vector<Integer>vec1,Vector<Integer>vec2,Vector<Integer>result) {
	for (int i = 0; i < vec1.size() && i<vec2.size(); i++)
		result.add(vec1.get(i) ^ vec2.get(i));
}
    public void encryption(String text) {
	P.clear();
	C.clear();
	keyStream.clear();
	getVector(text,P);
	setKeyStream(S, P.size());
	//------
	Xor(keyStream, P, C);
}
    public void decryption(String text) {
	P.clear();
	C.clear();
	keyStream.clear();
	getVector(text, C);
	setKeyStream(S, C.size());
	//------
	Xor(keyStream, C, P);
}
    
    String l="";
    String m="";
    public RC4() {
        for(int i=0;i<256;i++)
            l+='1';
        
        S = new Vector<Integer>();
        s = new Vector<Integer>();
        T = new Vector<Integer>();
        C = new Vector<Integer>();
        P = new Vector<Integer>();
        KEY = new Vector<Integer>();
        keyStream = new Vector<Integer>();
        //-----
        initComponents();
        setKey();
        String key = "";
        key+=getText(KEY);
        keyValue.setText(keyValue.getText() + key);
        
 }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        group = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        textValue = new javax.swing.JLabel();
        value = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        dec = new javax.swing.JRadioButton();
        enc = new javax.swing.JRadioButton();
        jSeparator2 = new javax.swing.JSeparator();
        keyValue = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        textSolution = new javax.swing.JLabel();
        solution = new javax.swing.JLabel();
        clear = new javax.swing.JButton();
        chageKey = new javax.swing.JButton();
        ok = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        textValue.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        textValue.setForeground(new java.awt.Color(255, 255, 255));
        textValue.setText("Plaintext:");

        value.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        value.setForeground(new java.awt.Color(0, 0, 0));

        group.add(dec);
        dec.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        dec.setForeground(new java.awt.Color(255, 255, 255));
        dec.setText("Decryption");

        group.add(enc);
        enc.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        enc.setForeground(new java.awt.Color(255, 255, 255));
        enc.setSelected(true);
        enc.setText("Encryption");

        keyValue.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        keyValue.setForeground(new java.awt.Color(255, 255, 255));
        keyValue.setText("Key: ");

        textSolution.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        textSolution.setForeground(new java.awt.Color(255, 255, 255));
        textSolution.setText("ciphertext:");

        solution.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        solution.setForeground(new java.awt.Color(255, 255, 255));

        clear.setForeground(new java.awt.Color(0, 0, 0));
        clear.setText("Clear");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        chageKey.setForeground(new java.awt.Color(0, 0, 0));
        chageKey.setText("Change Key");
        chageKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chageKeyActionPerformed(evt);
            }
        });

        ok.setForeground(new java.awt.Color(0, 0, 0));
        ok.setText("OK");
        ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(textValue, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(value, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(keyValue, javax.swing.GroupLayout.PREFERRED_SIZE, 849, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(textSolution, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(solution, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(dec, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(231, 231, 231))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(ok, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)
                        .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(129, 129, 129)
                        .addComponent(chageKey, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(159, 159, 159))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(128, 128, 128)
                    .addComponent(enc, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(620, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textValue, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(value, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(dec, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(keyValue, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textSolution, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(solution, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chageKey, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ok, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(149, 149, 149)
                    .addComponent(enc, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(547, Short.MAX_VALUE)))
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

    private void okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okActionPerformed
        if(enc.isSelected() == true){
            textValue.setText("Plaintext: ");
            textSolution.setText("Ciphertext: ");
            S.clear();
            T.clear();
            setS();
            setT();
            generatS(S, T);
            //----
            ciphertext = "";
            String text = value.getText();
            for(int i=0;i<text.length();){
                String str="";
                while(str.length() < 256 && i<text.length()){
                    str+=text.charAt(i);
                    i++;
                }
                encryption(text);
                ciphertext+= getText(C);
            }
            solution.setText(ciphertext);
            
        }
        else if(dec.isSelected() == true){
            textValue.setText("Ciphertext: ");
            textSolution.setText("Plaintext: ");
            plaintext = "";
            value.setText(ciphertext);
            for(int i=0;i<ciphertext.length();){
                String str="";
                while(str.length() < 256 && i<ciphertext.length()){
                    str+=ciphertext.charAt(i);
                    i++;
                }
                decryption(ciphertext);
                plaintext+= getText(P);
            }
            solution.setText(plaintext);
        }
        
        
        
        
    }//GEN-LAST:event_okActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        solution.setText("");
        value.setText("");
    }//GEN-LAST:event_clearActionPerformed

    private void chageKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chageKeyActionPerformed
         KEY.clear();
         RandomFrame r= new RandomFrame("set key", "random key", "enter a key");
         if(r.isRandom() == true && r.isSet() == false){
             setKey();
         }
         else if(r.isRandom() == false && r.isSet() == true){
             Set s = new Set("enter a key","Key: ");
             String str = "";
             str = s.getTextValue();
             
             if(s.isInteger() == false && s.isString() == true){
                 for(int i=0;i<str.length();i++){
                     int num = (int)str.charAt(i);
                     KEY.add(num);
                 }
             }
             else if(s.isInteger() == true && s.isString() == false){
                 str+=' ';
                 String str2="";
                 for(int i=0;i<str.length();){
                     
                     if(str.charAt(i) == ' ' )
                         i++;
                     else{
                         while(str.charAt(i) != ' '){
                             str2+=str.charAt(i);
                             i++;
                         }
                         int num = Integer.parseInt(str2);
                         KEY.add(num);
                         str2="";
                     }
                                          
                 }
                 
             }
         }
         String key="";
         key+=getText(KEY);
         keyValue.setText("Key:  " + key);
        
    }//GEN-LAST:event_chageKeyActionPerformed


    public static void main(String args[]) {
        
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RC4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RC4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RC4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RC4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RC4().setVisible(true);
            }
        });
    }

    
    
String plaintext="",ciphertext="";    
Vector<Integer>S;
Vector<Integer>T;
Vector<Integer>KEY;
Vector<Integer>P;
Vector<Integer>C;
Vector<Integer>keyStream;
Vector<Integer>s;    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chageKey;
    private javax.swing.JButton clear;
    private javax.swing.JRadioButton dec;
    private javax.swing.JRadioButton enc;
    private javax.swing.ButtonGroup group;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel keyValue;
    private javax.swing.JButton ok;
    private javax.swing.JLabel solution;
    private javax.swing.JLabel textSolution;
    private javax.swing.JLabel textValue;
    private javax.swing.JTextField value;
    // End of variables declaration//GEN-END:variables
}
