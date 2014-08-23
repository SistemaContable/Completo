package Contabilidad;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GUI_Plan_de_Cuentas.java
 *
 * Created on 21-ago-2011, 15:23:41
 */



import Clases_Auxiliares.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Wilfo
 */
public class GUI_Plan_de_Cuentas extends javax.swing.JFrame {

    /** Creates new form GUI_Plan_de_Cuenta2s */
    private Vector<Cuenta2> vecChildren = new Vector<Cuenta2>();
    private int cant_count=0;
    private DefaultTreeModel modelo1 = null;
    private DefaultMutableTreeNode root1 = null;
    private DefaultMutableTreeNode parent1 = null;
    private DefaultMutableTreeNode childnode = null;
    private int posLoad=0;
    
    private Conexion r_con;
    
    
    public GUI_Plan_de_Cuentas(Conexion r) {
        r_con = r;        
        
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        cargarArbol();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        JTreeConta = new javax.swing.JTree();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        JTreeConta.setForeground(new java.awt.Color(255, 255, 255));
        JTreeConta.setModel(modelo1);
        JTreeConta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTreeContaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTreeConta);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 375, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JTreeContaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTreeContaMouseClicked
     
        
      DefaultMutableTreeNode  node = (DefaultMutableTreeNode) JTreeConta.getLastSelectedPathComponent();
      //CLS_ITEM loadmenu;
      Cuenta2 loadcuenta;
        if (node == null) //Nothing is selected.
        {
            return;
        }
        else{JOptionPane.showMessageDialog(null,"holas");}
        if (node.isLeaf()) {
            Object nodeInfo = node.getUserObject();
            if((posLoad==-6)||(posLoad==-7))
            { 
                
                //loadmenu = (CLS_ITEM) nodeInfo;
//               JOptionPane.showMessageDialog(this, loadmenu.getId()+" "+loadmenu.getIdparent());
               //int id=Integer.parseInt(loadmenu.getId());
                //JInternalFrame frm= clsForm.getFormbyId(id);
                
                //if(!(frm==null)){
                 //Main_Conta.JDKConta.add(frm);
                 //frm.toFront();
                 //frm.setVisible(true);
                }
            }
            else
            { 
              //loadcuenta = (Cuenta2) nodeInfo;
          //    JOptionPane.showMessageDialog(this, loadcuenta.getDescrip());
            }

          
        
    }//GEN-LAST:event_JTreeContaMouseClicked

    /**
    * @param args the command line arguments
    */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTree JTreeConta;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables


    public void cargarArbol() {
        int count = 0;
        int aux = 1;
        inicializarArbol();
        Enumeration e = root1.breadthFirstEnumeration();        
        while (e.hasMoreElements()) {
            parent1 = (DefaultMutableTreeNode) e.nextElement();
            if (count >= aux) {
                Cuenta2 objChildren = (Cuenta2) parent1.getUserObject();
                //consigo los hijos de idparent
                vecChildren = getHijos(objChildren.Id);
                for (int k = 0; k < vecChildren.size(); k++) {
                    Cuenta2 hijo = (Cuenta2) vecChildren.get(k);
                    hijo.setDescrip(hijo.getCodCuenta()+"-"+hijo.getDescrip());
                    childnode = new DefaultMutableTreeNode(hijo);
                    modelo1.insertNodeInto(childnode, parent1, parent1.getChildCount());
                }
            }
            //Pregunto si llego al fin.
            if (e.hasMoreElements() == false) {
                int toEle = totalElementos(root1.breadthFirstEnumeration());
                if (cant_count < toEle) {
                    aux = count;
                    count = -2;
                    e = root1.breadthFirstEnumeration();
                    cant_count = toEle;
                } else {
                    break;
                }
            }
            count++;
        }

    }

   private void inicializarArbol() {

        Cuenta2 obj = (Cuenta2) getHijos("0").get(0);
        root1 = new DefaultMutableTreeNode(obj);
        modelo1 = new DefaultTreeModel(root1);
        Enumeration e = root1.breadthFirstEnumeration();
        parent1 = (DefaultMutableTreeNode) e.nextElement();
        Cuenta2 objChildren = (Cuenta2) parent1.getUserObject();
        
        //consigo los hijos de idparent
        vecChildren = getHijos(objChildren.Id);

        for (int k = 0; k < vecChildren.size(); k++) {
            cant_count++;
            Cuenta2 hijo = (Cuenta2) vecChildren.get(k);
            modelo1.insertNodeInto(new DefaultMutableTreeNode(hijo), parent1, parent1.getChildCount());
        }
        //TreeCuenta.setModel(modelo1);
    }

   public int totalElementos(Enumeration e) {
        int cont = 0;
        while (e.hasMoreElements()) {
            e.nextElement();
            cont++;
        }
        return cont;
    }

    /**
     * Return un vector con los hijos.
     * Id,Idparent,Descripcion,Order
     * @param Id
     * @return
     */
    public Vector getHijos (String Id) {
        r_con.Connection();
        Vector<Cuenta2> data = new Vector<Cuenta2>();
        try {            
            ResultSet res = r_con.Consultar("select id_cta,idpadre_cta,nombre_cta,cod_cta,orden_cta from sys_cuenta where idpadre_cta="+Id);

            while (res.next()) {
                data.addElement(new Cuenta2(res.getString(1), res.getString(2), res.getString(3),res.getString(4),res.getInt(5)));
            }
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
            r_con.cierraConexion();
        }
        r_con.cierraConexion();
        return data;
    }
   
  
}
