import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.ArrayList;


public class Fachkompetenzen extends JFrame implements ActionListener {

    JButton schliessen = new JButton("Schließen");
    JButton hinzufuegen = new JButton("Hinzufügen");
    JButton ausgewaelteEintragEntfernen = new JButton("Ausgewälte Eintrag entfernen");
    JButton letztenEintragEntfernen = new JButton("Letzten Eintrag entfernen");
    JTextField txtEingabe = new JTextField();
    JList<String> listControl;

    DefaultListModel<String> modelList = new DefaultListModel<>();

    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();
    Font font = new Font("Calibri", Font.BOLD, 24);

    public Fachkompetenzen() {
        this.setVisible(true);
        initForm();
    }

    public void initForm() {

        this.setTitle("Meine Fachkompetenzen");
        this.setSize(1000, 1000);
        //this.setBounds(200, 200, 900, 900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setLayout(gbl);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);


        JLabel lbl = new JLabel("Meine Kompetenzen:");
        addComponent(0, 0, 1, 1, lbl, GridBagConstraints.HORIZONTAL);

        //txtEingabe.setPreferredSize(new Dimension(300, 40));
        addComponent(0, 1, 1, 1, txtEingabe, GridBagConstraints.HORIZONTAL);

        hinzufuegen.addActionListener(this);
        addComponent(0, 2, 1, 1, hinzufuegen, GridBagConstraints.HORIZONTAL);

        ausgewaelteEintragEntfernen.addActionListener(this);
        addComponent(0, 3, 1, 1, ausgewaelteEintragEntfernen, GridBagConstraints.HORIZONTAL);
        ausgewaelteEintragEntfernen.setEnabled(false);

        letztenEintragEntfernen.addActionListener(this);
        addComponent(0, 4, 1, 1, letztenEintragEntfernen, GridBagConstraints.HORIZONTAL);
        letztenEintragEntfernen.setEnabled(false);

        schliessen.addActionListener(this);
        addComponent(1, 4, 0, 1, schliessen, GridBagConstraints.HORIZONTAL);


        listControl = new JList<>(modelList);
        listControl.setPreferredSize(new Dimension(300, 200));
        listControl.setFont(new Font("Calibri", Font.PLAIN, 20));


        ListSelectionListener listListener = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (listControl.isSelectionEmpty()){
                    ausgewaelteEintragEntfernen.setEnabled(false);
                }
                else {
                    ausgewaelteEintragEntfernen.setEnabled(true);
                }
            }
        };
        listControl.addListSelectionListener(listListener);


        addComponent(1, 0, 1, -1, listControl, GridBagConstraints.BOTH);

        pack();

    }


    public void addComponent(int x, int y, int width, int height, JComponent component, int fill) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        gbc.fill = fill;
        component.setFont(font);
        gbl.setConstraints(component, gbc);
        add(component);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == schliessen) {
            this.dispose();
        }
        if (e.getSource() == hinzufuegen) {
            if (txtEingabe.getText().length() > 0) {
                int index = this.modelList.getSize();
                this.modelList.add(index, txtEingabe.getText());
                txtEingabe.setText("");
                String txt = txtEingabe.getText();
                letztenEintragEntfernen.setEnabled(true);
            }
        }

        if(listControl.isSelectionEmpty()){
            ausgewaelteEintragEntfernen.setEnabled(false);
        }
        else{
            ausgewaelteEintragEntfernen.setEnabled(true);
        }
        if (e.getSource() == ausgewaelteEintragEntfernen) {
            if (modelList.size() < 2) {
                int selected = listControl.getSelectedIndex();
                modelList.remove(selected);
                ausgewaelteEintragEntfernen.setEnabled(false);
                letztenEintragEntfernen.setEnabled(false);
            } else {
                int selected = listControl.getSelectedIndex();
                modelList.remove(selected);
            }

        }
        if (e.getSource() == letztenEintragEntfernen) {
            if (modelList.size() < 2) {
                int lastElement = modelList.size() - 1;
                modelList.remove(lastElement);
                letztenEintragEntfernen.setEnabled(false);
                ausgewaelteEintragEntfernen.setEnabled(false);
            } else {
                int lastElement = modelList.size() - 1;
                modelList.remove(lastElement);
            }

        }

        /*public void listActionPerformed(ActionEvent e){
            e.
        } */
    }
}

