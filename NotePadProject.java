import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;
import java.util.Objects;

public class NotePadProject extends JFrame implements ActionListener {
    JFrame frame;
    JTextArea text;
    JMenuBar bar;
    public NotePadProject(){
        //frame
        frame = new JFrame("Notepad");
        frame.setSize(800,450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //menu bar
        bar = new JMenuBar();
        frame.setJMenuBar(bar);
        //menu
        JMenu menu1 = new JMenu("File");
        JMenu menu2 = new JMenu("Edit");
        bar.add(menu1);
        bar.add(menu2);
        //menu items
        JMenuItem item1 = new JMenuItem("Open");
        JMenuItem item2 = new JMenuItem("Save");
        JMenuItem item3 = new JMenuItem("Print");
        JMenuItem item4 = new JMenuItem("New");

        JMenuItem item5 = new JMenuItem("Cut");
        JMenuItem item6 = new JMenuItem("Copy");
        JMenuItem item7 = new JMenuItem("Paste");
        //JMenuItem item8 = new JMenuItem("Delete");

        menu1.add(item1);
        menu1.add(item2);
        menu1.add(item3);
        menu1.add(item4);

        menu2.add(item5);
        menu2.add(item6);
        menu2.add(item7);
        //menu2.add(item8);
        //text area
        text = new JTextArea();
        frame.add(text);

        //menu item action listener
        item5.addActionListener(this);
        item6.addActionListener(this);
        item7.addActionListener(this);

        item1.addActionListener(this);
        item2.addActionListener(this);
        item3.addActionListener(this);
        item4.addActionListener(this);
        //setting color
        //bar.setBackground(Color.PINK);
        //text.setBackground(Color.ORANGE);
        //setting frame visible
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        NotePadProject np = new NotePadProject();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if(Objects.equals(s, "Cut")){
            text.cut();
        }
        else if (Objects.equals(s, "Copy")){
            text.copy();
        }
        else if(Objects.equals(s, "Paste")){
            text.paste();
        }
        else if(Objects.equals(s, "Save")){
            JFileChooser fileChooser = new JFileChooser("C:");
            int ans = fileChooser.showOpenDialog(null);
            if(ans==JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new FileWriter(file,false));
                    writer.write(text.getText());
                    writer.flush();writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        else if(Objects.equals(s, "Open")){
            JFileChooser fileChooser = new JFileChooser("C:");
            int ans = fileChooser.showOpenDialog(null);
            if(ans == JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String s1 = "",s2="";
                    while((s1=br.readLine())!=null) {
                        s2 += s1 + "\n";
                    }
                    text.setText(s2);
                    br.close();
                } catch (IOException ex){
                    throw new RuntimeException(ex);
                }
            }
        }
        else if(Objects.equals(s, "New")){
            text.setText("");
        }
        else if(Objects.equals(s, "Print")){
            try {
                text.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
