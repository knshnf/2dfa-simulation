import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;


public class View {
    private JFrame mainFrame;

    public View() {
        this.mainFrame = new JFrame("2DFA");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLayout(null);
        this.mainFrame.setResizable(false);
        this.mainFrame.setSize(500,500);

        JPanel mainPanel = new JPanel();

        //Build the menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem menuItem = new JMenuItem("Open...");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
                fileChooser.setFileFilter(filter);
                int returnValue = fileChooser.showOpenDialog(mainFrame);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    // Process the selected file here
                    // For example, you can display its path or load its contents
                    System.out.println("Selected file: " + selectedFile.getAbsolutePath());

                    // Validate Machine Definition
                    if(!validateTextFile(selectedFile)) {

                    }

                }
            }
        });

        menuBar.add(menu);
        menu.add(menuItem);

        this.mainFrame.setJMenuBar(menuBar);
        this.mainFrame.show();
    }

    public boolean validateTextFile(File file) {
        return true;
    }
}

