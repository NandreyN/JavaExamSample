import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class MainFrame extends JFrame {
    private JMenuBar menuBar;
    private JMenuItem openItem;
    private JMenu fileMenu;

    private JSplitPane splitPane;
    private JTable table;
    private GuardTableModel tableModel;
    private JScrollPane scrollPane;

    private JLabel outLabel;
    private JPanel outputPanel;

    public MainFrame() {
        setLayout(new BorderLayout());
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        openItem = new JMenuItem("Open");

        fileMenu.add(openItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        tableModel = new GuardTableModel(new ArrayList<Employee>());
        table = new JTable(tableModel);

        TableRowSorter<javax.swing.table.TableModel> rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);
        java.util.List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        /*sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(2, SortOrder.DESCENDING));*/
        rowSorter.setSortable(3,false);
        rowSorter.setSortable(4,false);
        rowSorter.setComparator(2, new Comparator<String>() {

            @Override
            public int compare(String name1, String name2) {
                return new Integer(Integer.parseInt(name1)).compareTo(new Integer(Integer.parseInt(name2)));
            }
        });
        rowSorter.setSortKeys(sortKeys);
        rowSorter.sort();

        scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Data"));
        outputPanel = new JPanel(new BorderLayout());
        outLabel = new JLabel();
        outputPanel.add(outLabel, BorderLayout.CENTER);

        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(scrollPane);
        splitPane.setBottomComponent(outputPanel);
        splitPane.setDividerLocation(0.1);
        add(splitPane, BorderLayout.CENTER);

        openItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                int result = fileChooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    if (!file.getName().endsWith(".xml"))
                    {
                        JOptionPane.showMessageDialog(null, "Choose xml file , please");
                        return;
                    }

                    XMLInputHandler handler = new XMLInputHandler();
                    SAXParserFactory factory = SAXParserFactory.newInstance();
                    try {
                        SAXParser parser = factory.newSAXParser();
                        parser.parse(file, handler);
                        tableModel.update(handler.getGuards());
                        splitPane.setDividerLocation(0.5);

                        outLabel.setText("<html>"+tableModel.getJobs().toString() + "<br>" +
                                tableModel.getMinSalaryInfo().toString() + "</html>");
                    } catch (ParserConfigurationException | SAXException | IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }

        });
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setBounds(30, 30, 600, 600);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
