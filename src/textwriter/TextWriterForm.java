package textwriter;
                

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.sql.DriverManager.println;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author plaul1
 */
public class TextWriterForm extends javax.swing.JFrame
{

    List<String> lines = new ArrayList();

    public static class saveThread extends Thread
    {
        List<String> lines = new ArrayList();
        String finalString="";
        public saveThread(List<String> lines)
        {
            this.lines = lines;
        }
        public String arrayListToString(List <String> stringArray)
        {
            for (int i = 0; i < stringArray.size(); i++)
            {
                if (i==0)
                {
                    finalString = stringArray.get(i);
                }
                else
                {
                    finalString = finalString + ", "+stringArray.get(i);
                }
            }
            return finalString;
        }
        public void run()
        {
            while (true)
            {
                arrayListToString(lines);
                FileWriter writer;
                try
                {
                    writer = new FileWriter("backup.txt", false);
                    PrintWriter out = new PrintWriter(writer);
                    //writer.write(finalString);
                    out.println(finalString);
                    out.close();
                }
                catch (IOException ex)
                {
                    Logger.getLogger(TextWriterForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                try
                {
                    saveThread.sleep(15000);
                }
                catch (InterruptedException ex)
                {
                    Logger.getLogger(TextWriterForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    /**
     * Creates new form TextWriterForm
     */
    public TextWriterForm()
    {
        initComponents();
        saveThread t1 = new saveThread(lines);
        t1.start();
        txtNewLine.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                addLine();
            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jScrollPane1 = new javax.swing.JScrollPane();
    allText = new javax.swing.JTextArea();
    txtNewLine = new javax.swing.JTextField();
    btnNewLine = new javax.swing.JButton();
    jLabel1 = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    allText.setColumns(20);
    allText.setRows(5);
    allText.setEnabled(false);
    jScrollPane1.setViewportView(allText);

    txtNewLine.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        txtNewLineActionPerformed(evt);
      }
    });

    btnNewLine.setText("Add Line");
    btnNewLine.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnNewLineActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(btnNewLine)
              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(txtNewLine)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(0, 0, Short.MAX_VALUE))
          .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(txtNewLine, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(btnNewLine)
        .addGap(18, 18, 18)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGap(29, 29, 29))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

    private void addLine()
    {
        lines.add(txtNewLine.getText());
        //saveThread t1 = new saveThread(lines);
        allText.append(String.format(txtNewLine.getText() + "%n"));
        txtNewLine.setText("");
    }

  private void btnNewLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewLineActionPerformed
      addLine();
  }//GEN-LAST:event_btnNewLineActionPerformed

  private void txtNewLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNewLineActionPerformed
      // TODO add your handling code here:
  }//GEN-LAST:event_txtNewLineActionPerformed

    /**
     *
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex)
        {
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new TextWriterForm().setVisible(true);
            }
        });
    }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JTextArea allText;
  private javax.swing.JButton btnNewLine;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTextField txtNewLine;
  // End of variables declaration//GEN-END:variables
}
