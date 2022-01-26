
package dfaequipoa;
/*
* Tecnológico Nacional de Méxi0co campus Pachuca
* Asignatura: Lenguajes y Autómatas I
* Catedrático: Ing. Roberto Hernandéz Pérez
* Equipo: A
*   Lugo Martinez Saul Isui
*   Tomás Hernández Dulce Alejandra
*
* Automa Finto No Determinista con transiciones epsilon (NFA-e) con el lenguaje:
* L = {w | w comienza con 0 y termina con 1}
* M = {Q,Σ,𝛿,q0,F}
* Q = {q0, q1, q2,q3}
* Σ = {0,1}
* F = {q3}
*/
public class NFAe extends javax.swing.JFrame {
    
    final String eClousureq0 = "q0";
    final String eClousureq1a = "q1";
    final String eClousureq1b = "q2";
    final String eClousureq2 = "q2";
    final String eClousureq3 = "q3";
    
    public NFAe() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("NFA-e Equipo A");
    }
    
    //Funcion de transición para Delta
    public String Delta(String q, char a){
        String p = null;
        String t = null;
        String w = jTextField1.getText();
        //Compara cada estado con la entrada que recibe
        if(q.equals("q0") && a == '0'){ 
            p = "q1";
        } else if(q.equals("q0") && a == '1'){
            //La cadena no comienza con 0
            p = "qx";
        } else if(q.equals("q1") && a == '0'){
            p = "q2";
            
        } else if(q.equals("q1") && a == '1'){
            //Si la longitud de la cadena es 2 y termina con 1 entoces es valida
            for (int i = 0; i<w.toCharArray().length;i++){
                if (w.charAt(i) == '1'){
                    //Estado valido
                    p = "q3";
                } else {
                    p = "q2";
                }
            }
        } else if(q.equals("q2") && a == '0'){
            p = "q2";
        } else if(q.equals("q2") && a == '1'){
           //Si el ultimo simbolo es 1 entoces la cadena es valida
            for (int i = 0; i<w.toCharArray().length;i++){
                int lugar = w.length()-1;
                int lugar2 = i+1;
                if (w.charAt(lugar) == '1'){
                    p = "q3";
                } else {
                    p = "q2";
                }
            }
        } else if(q.equals("q3") && a == '0'){
            p = "q3";
        } else if(q.equals("q3") && a == '1'){
            p = "q3";
        }
        //La cadena no comienza con 0, y este bloque de codigo evita que
        //el programa deje de funcionar
        else if(q.equals("qx") && a == '0' || q.equals("qx") && a == '1'){
            p = "qx";
        }
        return p;
    }
    
    //Funcion para Delta eClousure
    public String eClousure(String q, String w){
        String p = "";
        int i = 0;

        while (i<w.length()){
            //Llama al metodo delta para que obtengamos
            //el estado con el simbolo actual de la cadena
            p = Delta(q, w.charAt(i));
            q = p;
            i++;
        }
        return p;
    }
    
    //Valida que el ultimo simbolo de la cadena se encuetre
    //en el estado final
    public boolean Valida(String w){
        boolean val = false;
        String p = eClousure("q0",w);
    //q3 es el estado final
        if (p.equals("q3")){
            val = true;
            jTextField2.setText("Estado final: "+p);
        } else if(p.equals("qx")){
            val = false;
            jTextField2.setText("La cadena no comienza con 0");
        }
        return val;
    }
    
    //Imprime el resultado del método valida
    //Si la cadena es válida o no
    public void Comprueba(String w){
        if (Valida(w)){
            jTextArea1.setText("La cadena : "+w+
                               "\nEs VÁLIDA");
        } else {
            jTextArea1.setText("La cadena : "+w+
                               "\nNo es VÁLIDA");
        }
    }
    
    //Metodo para validar que los simbolos pertenescan al lenguaje
    public void ValidaAlfa(String w) {
        boolean valida = false;
        //variable para que para el simbolo que no pertence al alfabeto
        String p = null;
        for (int i = 0; i < w.length(); i++) {
            char a = w.charAt(i);
            String q = String.valueOf(a);
            //Comparamos cada simbolo
            if (q.equals("0") || q.equals("1")) {
                valida = true;
            } else {
                valida = false;
                p = q;
                //break termina las iteraciones
                //puesto que la cadena no podra ser leida
                //por contener un simbolo que no pertence al alfabeto
                break;
            }
        }
        if (valida) {
            //Si todos los simbolos son aceptados puede entrar al automata
            Comprueba(w);
        } else {
            //La cadena contiene simbolos que no son aceptados para el alfabeto
            //p es el simbolo que no pertenece al alfabeto
            NoPerteneceAlfa(p);
        }
    }
    
    //Método para imprimir el simbolo que no pertenece al alfabeto
    public void NoPerteneceAlfa(String q){
        jTextArea1.setText("Simbolo : "+q+""
                         + "\nNo pertenece al alfabeto");
        jTextField2.setText("");
    }
    
    //Método para limpiar los campos
    public void Limpiar(){
    jTextField1.setText(null);
    jTextArea1.setText(null);
    jTextField2.setText(null);
    }
    
    //Método INGRESA que lee la cadena del campo, escrita por el usuario
    public void Ingresa(){
        String w = null;
        w = jTextField1.getText();
        //Llama al metodo que valida el alfabeto
        ValidaAlfa(w);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jTextField1.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N

        jButton1.setBackground(new java.awt.Color(0, 51, 153));
        jButton1.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("INGRESA");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N

        jButton2.setBackground(new java.awt.Color(0, 51, 153));
        jButton2.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("LIMPIAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Ingresa la cadena");

        jLabel2.setText("M={Q,Σ,delta,q0,F} Σ={0,1}");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Imagen3.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setText("NFA-e EQUIPO A");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/encabezado.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel6)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(55, 55, 55)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Ingresa();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Limpiar();
    }//GEN-LAST:event_jButton2ActionPerformed

 
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NFAe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NFAe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NFAe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NFAe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NFAe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
