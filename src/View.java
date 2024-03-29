/**
 *  STALGCM Term 3 AY 2022 - 2023
 *  DIGNO, Kenneth Clark
 *  FETALVERO, Kenshin
 *  LIOBING, Aldwin
 *  S14
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.List;


/**
 * The View class is repsonsible for displaying GUI of the application.
 */
public class View {
    private JFrame mainFrame;
    private JMenuBar menuBar;
    private JMenu menuFile, menuInput;
    private JMenuItem menuItemOpen, menuItemInputStep, menuItemInputFast;
    private JTabbedPane tabbedPane;
    private JScrollPane scrollPane;
    private JLabel stepCurrState, stepCurrInput;
    private JTextArea textArea;
    private File file;
    private Machine machine;
    private int stepIndex;
    private List<StateAndIndexPair> stepList;

    /**
     * Creates a new instance of View. This constructor initializes the GUI.
     */
    public View() {
        // Initialize JFrame
        this.mainFrame = new JFrame("2DFA");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLayout(null);
        this.mainFrame.setResizable(false);
        this.mainFrame.setSize(500,500);
        this.mainFrame.setLocationRelativeTo(null);

        //Build the menu
        this.menuBar = new JMenuBar();
        this.menuFile = new JMenu("File");
        this.menuItemOpen = new JMenuItem("Open...");
        this.menuItemOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
                fileChooser.setFileFilter(filter);
                int returnValue = fileChooser.showOpenDialog(mainFrame);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    file = selectedFile;
                    // Process the selected file here
                    // For example, you can display its path or load its contents
                    System.out.println("Selected file: " + selectedFile.getAbsolutePath());

                    try{
                        machine = new Machine(selectedFile);
                        
//                        System.out.println(machine.toString());
                    }
                    catch (Exception ex) {
                        System.out.println(ex);
                    }
                    displayMachineDefinition();

                }
            }
        });

        this.menuInput = new JMenu("Input");
        this.menuInput.setVisible(false);
        this.menuItemInputStep = new JMenuItem("Step with Closure...");

        this.menuItemInputStep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a text field
                JTextField textField = new JTextField(20);

                // Create a panel to hold the text field and label
                JPanel panel = new JPanel();
                panel.add(new JLabel("Input"));
                panel.add(textField);

                // Show the input dialog prompt for input
                int result = JOptionPane.showOptionDialog(null,
                        panel,
                        "Input",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        null,
                        null);

                // Check the result to see if OK button was clicked
                if (result == JOptionPane.OK_OPTION) {
                    String inputText = textField.getText();
                    StringBuilder sb = new StringBuilder();
                    sb.append("<");
                    sb.append(inputText);
                    sb.append(">");

                    stepIndex = 0;
                    stepList = machine.step(inputText);

                    // Initialize the new tab that will show the current state and current input being read
                    JPanel newPanel = new JPanel(null);
                    newPanel.setBackground(Color.white);

                    JButton prevBtn = new JButton("Prev");
                    prevBtn.setBounds(105, 10, 90, 25);

                    JButton nextBtn = new JButton("Next");
                    nextBtn.setBounds(205, 10, 90, 25);

                    JButton resetBtn = new JButton("Reset");
                    resetBtn.setBounds(305, 10, 90, 25);

                    JLabel currState = new JLabel("Current State ");
                    currState.setBounds(200, 100, 100, 30);

                    stepCurrState = new JLabel("", SwingConstants.CENTER);
                    stepCurrState.setBounds(0, 130, 477, 30);

                    JLabel currInput = new JLabel("Current Input");
                    currInput.setBounds(200, 200, 100, 30);
                    stepCurrInput = new JLabel("", SwingConstants.CENTER);
                    stepCurrInput.setBounds(0, 230, 477, 30);

                    newPanel.add(prevBtn);
                    newPanel.add(nextBtn);
                    newPanel.add(resetBtn);
                    newPanel.add(currState);
                    newPanel.add(currInput);

                    stepCurrState.setText(stepList.get(0).getStateName());
                    stepCurrInput.setText(formatStringAtIndex(sb.toString(), stepList.get(stepIndex).getIndex()));

                    newPanel.add(stepCurrState);
                    newPanel.add(stepCurrInput);

                    // Add actions listener to the prev, next, and reset button
                    prevBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(stepIndex == 0) {
                                return;
                            }
                            stepIndex--;
                            stepCurrState.setText(stepList.get(stepIndex).getStateName());
                            stepCurrInput.setText(formatStringAtIndex(sb.toString(), stepList.get(stepIndex).getIndex()));
                            mainFrame.repaint();
                            mainFrame.revalidate();
                        }
                    });

                    nextBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(stepIndex == stepList.size() - 1) {
                                if(machine.fastRun(inputText)){
                                    JOptionPane.showMessageDialog(mainFrame,
                                            "The input was accepted");
                                }
                                else{
                                    JOptionPane.showMessageDialog(mainFrame,
                                            "The input was rejected");
                                }


                                return;
                            }

                            stepIndex++;
                            stepCurrState.setText(stepList.get(stepIndex).getStateName());
                            stepCurrInput.setText(formatStringAtIndex(sb.toString(), stepList.get(stepIndex).getIndex()));
                            System.out.print(stepList.get(stepIndex).getIndex());

                            mainFrame.repaint();
                            mainFrame.revalidate();
                        }
                    });

                    resetBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            stepIndex = 0;
                            stepCurrState.setText(stepList.get(stepIndex).getStateName());
                            stepCurrInput.setText(formatStringAtIndex(sb.toString(), stepList.get(stepIndex).getIndex()));
                            mainFrame.repaint();
                            mainFrame.revalidate();
                        }
                    });

                    int tabCount = tabbedPane.getTabCount();

                    if (tabCount > 1) {
                        // Remove the second tab (index 1) if there are multiple tabs
                        tabbedPane.remove(1);
                    }

                    tabbedPane.add("Simulate: " + inputText, newPanel);
                    tabbedPane.setSelectedIndex(1);

                    mainFrame.repaint();
                    mainFrame.revalidate();
                }
            }
        });

        // Initialize the menu item and action for fast run option
        this.menuItemInputFast = new JMenuItem("Fast Run...");

        this.menuItemInputFast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a text field
                JTextField textField = new JTextField(20);

                // Create a panel to hold the text field and label
                JPanel panel = new JPanel();
                panel.add(new JLabel("Input"));
                panel.add(textField);

                // Show the input dialog with "OK" and "Cancel" buttons, and custom title
                int result = JOptionPane.showOptionDialog(null,
                        panel,
                        "Input",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        null,
                        null);

                // Check the result to see if "OK" was clicked
                if (result == JOptionPane.OK_OPTION) {
                    String inputText = textField.getText();
                    if(machine.fastRun(inputText)){
                        JOptionPane.showMessageDialog(mainFrame,
                                "The input was accepted");
                    }
                    else{
                        JOptionPane.showMessageDialog(mainFrame,
                                "The input was rejected");
                    }

                    System.out.println("You entered: " + inputText);
                }
            }
        });

        this.menuInput.add(this.menuItemInputStep);
        this.menuInput.add(this.menuItemInputFast);

        // Create a JTextArea
        this.textArea = new JTextArea();
        this.textArea.setEditable(false);
        this.textArea.setLineWrap(true);
        this.textArea.setWrapStyleWord(true);
        this.textArea.setMargin(new Insets(20,20,20,20));

        // Create a JScrollPane and add the JTextArea to it
        this.scrollPane = new JScrollPane(this.textArea);

        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setBounds(0, 0, 484, 438);
        this.tabbedPane.add("Machine Definition", scrollPane);
        this.mainFrame.add(this.tabbedPane);
        this.menuBar.add(this.menuFile);
        this.menuBar.add(this.menuInput);
        this.menuFile.add(this.menuItemOpen);

        this.mainFrame.setJMenuBar(this.menuBar);
        this.mainFrame.setVisible(true);
    }

    /**
     * Displays the machine definition one a valid file is entered
     */
    public void displayMachineDefinition() {
        this.mainFrame.setTitle("2DFA - " + file.getName());
        this.textArea.setText(machine.toString());
        this.menuInput.setVisible(true);
        this.mainFrame.repaint();
        this.mainFrame.revalidate();

    }

    /**
     * Formats the current input string based on what symbol is currently being read
     * @param inputString the string being formatted
     * @param index the current position of the read head
     * @return the formatted string in HTML format
     */
    private String formatStringAtIndex(String inputString, int index) {
        if (index < 0 || index >= inputString.length()) {
            throw new IllegalArgumentException("Invalid index");
        }

        String formattedString = "<html>";

        for (int i = 0; i < inputString.length(); i++) {
            if (i == 0 && index == 0) {
                formattedString += "<u>" + "&#60" + "</u>";
            }
            else if (i == 0){
                formattedString +=  "&#60";
            }
            else if (i == index) {
                formattedString += "<u>" + inputString.charAt(i) + "</u>";
            } else {
                formattedString += inputString.charAt(i);
            }
        }

        formattedString += "</html>";
        return formattedString;
    }
}

