
package dfaequipoa;
/*
* Tecnológico Nacional de México campus Pachuca
* Asignatura: Lenguajes y Autómatas I
* Catedrático: Ing. Roberto Hernandéz Pérez
* Equipo: A
*   Lugo Martinez Saul Isui
*   Tomás Hernández Dulce Alejandra
*
* Automa Finto No Determinista (NFA) con el lenguaje
* L = {w | w contenga pares de 0's o pares de 1's}
* M = {Q,Σ,𝛿,q0,F}
* Q = {q0, q1, q2,q3,q4}
* Σ = {0,1}
* F = {q2,q4}
*/
public class NFA extends javax.swing.JFrame {


    public NFA() {
        initComponents();
    }

    //Funcion de transición iterativa para Delta
    public String Delta(String q, char a){
        String p = null;
        String t = null;
        String w = jTextField1.getText();
        //Compara cada estado con la entrada que recibe
        if(q.equals("q0") && a == '0'){ 
            //Pregunta si el siguiente simbolo es igual al actual = 0
            for (int i = 1; i<w.toCharArray().length;i++){
                if (w.charAt(i) == '0'){
                    p = "q3";
                } else {
                    p = "q0";
                }
            }
        } else if(q.equals("q0") && a == '1'){
            //Pregunta si el siguiente simbolo es igual al actual = 1
            for (int i = 1; i<w.toCharArray().length;i++){
                if (w.charAt(i) == '1'){
                    p = "q1";
                } else {
                    p = "q0";
                }
            }
        } else if(q.equals("q1") && a == '0'){
            p = "q1";
        } else if(q.equals("q1") && a == '1'){
            p = "q2";
        } else if(q.equals("q2") && a == '0'){
            p = "q2";
        } else if(q.equals("q2") && a == '1'){
            p = "q2";
        } else if(q.equals("q3") && a == '0'){
            p = "q4";
        } else if(q.equals("q3") && a == '1'){
            p = "q3";
        } else if(q.equals("q4") && a == '0'){
            p = "q4";
        } else if(q.equals("q4") && a == '1'){
            p = "q4";
        }
        return p;
    }
    
    //Funcion iterativa para Delta
    public String deltaIterativa(String q, String w){
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
        String p = deltaIterativa("q0",w);
    //q3 es el estado final
        if (p.equals("q2") ||  p.equals("q4")){
            val = true;
            jTextField2.setText("Estado final: "+p);
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
    }
    
    //Método para lipiar los campos
    public void Limpiar(){
    jTextField1.setText(null);
    jTextArea1.setText(null);
    jTextField2.setText(null);
    }
    
    //Método que lee la cadena del campo, escrita por el usuario
    public void Ingresa(){
        String w = null;
        w = jTextField1.getText();
        //Llama al metodo que valida el alfabeto
        ValidaAlfa(w);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);
        jTextArea1.getAccessibleContext().setAccessibleName("Resultados");
        jTextArea1.getAccessibleContext().setAccessibleDescription("");

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 560, 230, 110));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 530, 230, -1));

        jButton1.setText("INGRESA");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 530, 114, 40));

        jButton2.setText("LIMPIAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 530, 83, 40));

        jLabel1.setText("Ingresa la cadena");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 510, 220, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("M={Q,Σ,delta,q0,F} Σ={0,1}");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 460, -1, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("L = {w | w contenga pares de 0's o pares de 1's}");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, -1, 33));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Diagrama NFA.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, -1, 206));

        jTextField2.setEditable(false);
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 580, 200, 42));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel5.setText("NFA EQUIPO A");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 328, 66));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/encabezado.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 410, 110));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Limpiar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Ingresa();
    }//GEN-LAST:event_jButton1ActionPerformed


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
            java.util.logging.Logger.getLogger(NFA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NFA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NFA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NFA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NFA().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
