import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
class TextEditor extends JFrame implements ActionListener
{
    /*
    for  creating the text component use this class it provide all
     */
    JTextArea t;
    /*
    This is a another class extends the frame class using to create the text area frame
     and also set the theme an look and feel for the text editor
     */
    JFrame f;
    /*
    This is contain all the working knowledge about the text editor
     */
    TextEditor(){
        f = new JFrame("Editor");
        try{
            //set the look and feel for the text editor like out look
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

            //set the colour for that frame
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        }
        catch (Exception e){

        }
        /*
        Creating the text component like menuBar,Edit
         */
        t = new JTextArea();

        //First need menu to open,new file,save,print

        JMenuBar mb = new JMenuBar();

        JMenu m1 = new JMenu("File");

        JMenuItem mi2 = new JMenuItem("Save");
        JMenuItem mi3 = new JMenuItem("Open");
        JMenuItem mil = new JMenuItem("Print");
        JMenuItem mi1 = new JMenuItem("New");

        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mil.addActionListener(this);
        m1.add(mi1);
        m1.add(mi2);
        m1.add(mi3);
        m1.add(mil);

        /*
        One menu need to cut,copy and paste the texts write
         */
        JMenu m2 = new JMenu("Edit");

        JMenuItem mi4 = new JMenuItem("Copy");
        JMenuItem mi5 = new JMenuItem("Cut");
        JMenuItem mi6 = new JMenuItem("Paste");

        mi4.addActionListener(this);
        mi5.addActionListener(this);
        mi6.addActionListener(this);

        m2.add(mi4);
        m2.add(mi5);
        m2.add(mi6);

        JMenu mc = new JMenu("Close");
        mc.addActionListener(this);

        /*
        Add all the listener to the main menu bar at the top
         */
        mb.add(m1);
        mb.add(m2);
        mb.add(mc);

        /*
        Set the frame content like
        Add the menu bar at the top of the frame.
        add the text area at the bottom of the menu bar.order is important.
        size of the frame is want to enter.
        To view text editor window .show() is used.
         */
        f.setJMenuBar(mb);
        f.add(t);
        f.setSize(500,500);
        f.show();

    }
    /*
    This is help to take the action from the
    Like,
    If the user press any of the menu button take the response from that,
    and the user type the input and take and show it in text box.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("Cut")){
            t.cut();
        } else if (s.equals("Copy")) {
            t.copy();
        } else if (t.equals("Paste")) {
            t.paste();
        } else if (s.equals("Save")) {
            //this is select the path to save the current file in that path inside the JFileChooser
            JFileChooser j = new JFileChooser("f:");

            int showNullDialog = j.showSaveDialog(null);
            if (showNullDialog == JFileChooser.APPROVE_OPTION){
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try{
                    //this will help to write the content in textarea
                    FileWriter wr = new FileWriter(fi,false);
                    //Create the buffereriter to boost the writing ability
                    BufferedWriter w = new BufferedWriter(wr);
                    //it get the input from the user for text area
                    w.write(t.getText());

                    w.flush();
                    w.close();
                }
                catch (Exception evt){
                    //It pop up the dialog box to the user window
                        JOptionPane.showMessageDialog(f,evt.getMessage());
                }
            }
            //If the user is cancel the opertion
            else
                JOptionPane.showMessageDialog(f,"User is cacel the opertion");
        } else if (s.equals("Print")) {
            try{
                //To print the file in text area
                t.print();
            }
            catch (Exception evt){
                JOptionPane.showMessageDialog(f,evt.getMessage());
            }
        } else if (s.equals("Open")) {
            //If the user open the old file select the file from the path
            JFileChooser j = new JFileChooser("f:");

            /*
            It is open the file and save the file in the current path
            Need to Create the FileChoser object.
             */
            int r = j.showOpenDialog(null);

            //if the user is selected the opened file
            if (r == JFileChooser.APPROVE_OPTION){
                //set the text file to save the selected directory
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try{
                    //Get the string as a input is take all type of words will included
                    String s1 =" ",s2 = "";

                    FileReader fr = new FileReader(fi);

                    BufferedReader  br = new BufferedReader(fr);
                    s1 = br.readLine();
                    /*
                    It all used to take the input from previous file that we opened
                     */
                    while ((s1 = br.readLine())!=null){
                        s1 = s1 +"\n" + s1;
                    }
                    //set the input into file
                    t.setText(s1);
                }
                catch(Exception evt){
                    JOptionPane.showMessageDialog(f,evt.getMessage());
                }
            }
            else
                JOptionPane.showMessageDialog(f,"User is cancelled the operation");
        } else if (s.equals("New")) {
            t.setText("");
        } else if (s.equals("Close")) {
            f.setVisible(false);
        }

    }

    public static void main(String[] args) {
        TextEditor e = new TextEditor();
    }
}
