package component2;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import model.Model_Chill;

public class ListChill<E extends Object> extends JList<E> {
    
    private final DefaultListModel model;
    
    public ListChill() {
        model = new DefaultListModel();
        setModel(model);
        setOpaque(false);
    }
    
    @Override
    public ListCellRenderer getCellRenderer() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> jlist, Object o, int index, boolean selected, boolean focus) {
                Model_Chill data;
                if (o instanceof Model_Chill) {
                    data = (Model_Chill) o;
                } else {
                    data = new Model_Chill(new ImageIcon(getClass().getResource("/icon/Chill/1.png")), "Title");
                }
                ItemChill item = new ItemChill(data);
                return item;
            }
        };
    }
    
    public void addItem(Model_Chill data) {
        model.addElement(data);
    }
    
    public void sout() {
        JOptionPane.showMessageDialog(null, "In thành công");
    }
    
}
