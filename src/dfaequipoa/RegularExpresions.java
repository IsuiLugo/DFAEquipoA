package dfaequipoa;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
* Tecnológico Nacional de México campus Pachuca
* Asignatura: Lenguajes y Autómatas I
* Catedrático: Ing. Roberto Hernandéz Pérez
* Equipo: A
*   Lugo Martinez Saul Isui
*   Tomás Hernández Dulce Alejandra
*
* L = {w | w comienza con 0 y termina con 1}
* Expresión regular 0(0+1)*1
* Automa Finto No Determinista con transiciones epsilon (NFA-e) con el lenguaje:
* M = {Q,Σ,𝛿,q0,F}
* Q = {q0, q1, q2,q3}
* Σ = {0,1}
* F = {q3}
*/
public class RegularExpresions extends javax.swing.JFrame {
    //Expresion regular
    final public String RExpresion = "0[01]*1";    
   
    public RegularExpresions() {
        initComponents();
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
            //o a la expresion regular o a ambos
            int option = jComboBox1.getSelectedIndex();
            if(option == 0){
                ImprimeRegular(w);
            } else if(option == 1){
                Comprueba(w);
            }
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
        jTextField2.setText(null);
    }
    
    //Método de la expresion regular
    public boolean RegularExpresion (String w){
        boolean valida = false;
        Pattern expresion = Pattern.compile(RExpresion);
        Matcher evalua = expresion.matcher(w);
        if(evalua.matches()){
            valida = true;
        } else {
            valida = false;
        }
        return valida;
    }
    
    //Imprime expresion regular
    public void ImprimeRegular(String w){
        if (RegularExpresion(w)){
            jTextArea1.setText("La cadena : "+w+
                               "\nEs VÁLIDA");
            jTextField2.setText("RE: "+RExpresion);
        } else {
            jTextField2.setText("La cadena no comienza con 0");
            jTextArea1.setText("La cadena : "+w+
                               "\nNo es VÁLIDA");
        }
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

        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N

        jButton1.setText("INGRESA");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Expresion regular", "NFA-e" }));

        jButton2.setText("LIMPIAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField2.setEditable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("L = {w | w comienza con 0 y termine con 1} Σ={0,1}");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jLabel2.setText("Opciones:");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/encabezado.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("CHECADOR DE CADENA CON ER Y NFA-e EQUIPO A");

        jLabel5.setText("Ingresa la cadena");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                                    .addComponent(jTextField2)
                                    .addComponent(jTextField1)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
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
            java.util.logging.Logger.getLogger(RegularExpresions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegularExpresions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegularExpresions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegularExpresions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegularExpresions().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}