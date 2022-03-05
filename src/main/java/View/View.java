package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame{

    private JLabel l = new JLabel("Queues Simulator");
    private JLabel l_nr_n = new JLabel("Introduce the number of clients: ");
    private JTextField tf_n = new JTextField(4);
    private JLabel l_nr_q = new JLabel("Introduce the number of queues: ");
    private JTextField tf_q = new JTextField(3);
    private JLabel l_sim = new JLabel("Simulation interval: ");
    private JTextField tf_sim = new JTextField(4);
    private JLabel l_arrival = new JLabel("Arrival time bounds: ");
    private JTextField tf_min_arrival = new JTextField(4);
    private JTextField tf_max_arrival = new JTextField(4);
    private JLabel l_service = new JLabel("Service time bounds: ");
    private JTextField tf_min_service = new JTextField(4);
    private JTextField tf_max_service = new JTextField(4);
    private JButton btn_start = new JButton("Start");
    private JButton btn_exit = new JButton("Exit");
    private JTextArea ta_real_time = new JTextArea();

    public View(){

        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(3, 3));
        p1.add(l_nr_n);
        p1.add(Box.createRigidArea(new Dimension(15, 0)));
        p1.add(tf_n);
        p1.add(l_nr_q);
        p1.add(Box.createRigidArea(new Dimension(15, 0)));
        p1.add(tf_q);
        p1.add(l_sim);
        p1.add(Box.createRigidArea(new Dimension(15, 0)));
        p1.add(tf_sim);

        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(2,3));
        p2.add(l_arrival);
        //p2.add(Box.createRigidArea(new Dimension(0, 5)));
        p2.add(tf_min_arrival);
        p2.add(tf_max_arrival);
        p2.add(l_service);
        //p2.add(Box.createRigidArea(new Dimension(0, 5)));
        p2.add(tf_min_service);
        p2.add(tf_max_service);

        JPanel p3 = new JPanel();
        p3.setLayout(new GridLayout(1,3));
        p3.add(btn_start);
        btn_start.setBackground(Color.GREEN);
        p3.add(btn_exit);
        p3.add(Box.createRigidArea(new Dimension(15, 0)));

        JPanel p4 = new JPanel();
        p4.setLayout(new BoxLayout(p4, BoxLayout.Y_AXIS));
        p4.add(Box.createRigidArea(new Dimension(0, 15)));
        p4.add(l);
        l.setAlignmentX(CENTER_ALIGNMENT);
        l.setFont(new Font("Sheriff", Font.BOLD, 19));
        p4.add(Box.createRigidArea(new Dimension(0, 20)));
        p4.add(p1);
        p4.add(Box.createRigidArea(new Dimension(0, 15)));
        p4.add(p2);
        p4.add(Box.createRigidArea(new Dimension(0, 15)));
        p4.add(p3);
        p4.add(Box.createRigidArea(new Dimension(0, 15)));
        p4.add(ta_real_time);
        ta_real_time.setEditable(false);
        p4.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.X_AXIS));
        content.add(Box.createRigidArea(new Dimension(15, 0)));
        content.add(p4);
        content.add(Box.createRigidArea(new Dimension(15, 0)));

        this.setContentPane(content);
        this.pack();
        this.setTitle("Queues simulator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addStartListener(ActionListener al) {
        btn_start.addActionListener(al);
    }

    public void addExitListener(ActionListener al) {
        btn_exit.addActionListener(al);
    }

    public String getNumberOfClients() {
        return tf_n.getText();
    }

    public String getNumberOfQueues() {
        return tf_q.getText();
    }

    public String getSimulationTime() {
        return tf_sim.getText();
    }

    public String getMinArrivalTime() {
        return tf_min_arrival.getText();
    }

    public String getMaxArrivalTime() {
        return tf_max_arrival.getText();
    }

    public String getMinProcessingTime() {
        return tf_min_service.getText();
    }

    public String getMaxProcessingTime() {
        return tf_max_service.getText();
    }
    public void setStartBtnGreen() {
        btn_start.setBackground(Color.GREEN);
    }
    public void setStartBtnRed() {
        btn_start.setBackground(Color.RED);
    }

    public void setTextArea(String string){
        ta_real_time.setText(string);
    }

    public void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }

}