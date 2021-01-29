package homeinventory;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Date;

public class Home_Inventory_Manager extends JFrame {
	
    JToolBar toolbar = new JToolBar();
    
    JButton newButton = new JButton(new ImageIcon("C:\\Users\\Rohit\\Desktop\\Become Java Devloper\\FreeIconWebsite\\new.png"));
    JButton deleteButton = new JButton(new ImageIcon("C:\\Users\\Rohit\\Desktop\\Become Java Devloper\\FreeIconWebsite\\delete.png"));
    JButton saveButton = new JButton(new ImageIcon("C:\\Users\\Rohit\\Desktop\\Become Java Devloper\\FreeIconWebsite\\save.png"));
    JButton previousButton = new JButton(new ImageIcon("C:\\Users\\Rohit\\Desktop\\Become Java Devloper\\FreeIconWebsite\\previous.png"));
    JButton nextButton = new JButton(new ImageIcon("C:\\Users\\Rohit\\Desktop\\Become Java Devloper\\FreeIconWebsite\\next.png"));
    JButton printButton = new JButton(new ImageIcon("C:\\Users\\Rohit\\Desktop\\Become Java Devloper\\FreeIconWebsite\\print.png"));
    JButton exitButton = new JButton(new ImageIcon("C:\\Users\\Rohit\\Desktop\\Become Java Devloper\\FreeIconWebsite\\exit.png"));
    JButton photoButton = new JButton();
    JButton[] searchButton = new JButton[26];
    
    JLabel itemLabel = new JLabel();
    JLabel locationLabel = new JLabel();
    JLabel serialLabel = new JLabel();
    JLabel priceLabel = new JLabel();
    JLabel dateLabel = new JLabel();
    JLabel storeLabel = new JLabel();
    JLabel noteLabel = new JLabel();
    JLabel photoLabel = new JLabel();
    
    JTextField itemTextField = new JTextField();
    JTextField serialTextField = new JTextField();  
    JTextField priceTextField = new JTextField();
    JTextField storeTextField = new JTextField();   
    JTextField noteTextField = new JTextField();
    
    JComboBox locationComboBox = new JComboBox();
    JCheckBox markedCheckBox = new JCheckBox();
    JDateChooser dateDateChooser = new JDateChooser();
   
    JPanel searchPanel = new JPanel();
    PhotoPanel photoPanel = new PhotoPanel();
    
    int currentEntry;
    static int numberEntries;
    static int lastPage; 
    static final int entriesPerPage = 2;
    static final int maximumEntries = 300;
    static JTextArea photoTextArea = new JTextArea();
    static InventoryItem[] myInventory = new InventoryItem[maximumEntries];
 
    public static void main(String args[])
    {
        new Home_Inventory_Manager().show();// create frame
    }
    
    public Home_Inventory_Manager()

    {
// frame constructor
        setTitle("Home Inventory Manager");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent evt)
            {
                exitForm(evt);
            }
        });
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        toolbar.setFloatable(false);
        toolbar.setBackground(Color.BLUE);
        toolbar.setOrientation(SwingConstants.VERTICAL);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 8;
        gbc.fill = GridBagConstraints.VERTICAL;
        getContentPane().add(toolbar, gbc);
        
        toolbar.addSeparator();
        //new button
        Dimension buttonSize = new Dimension(70, 50);
        newButton.setText("New");
        sizeButton(newButton, buttonSize);
        newButton.setToolTipText("Add New Item");
        newButton.setHorizontalTextPosition(SwingConstants.CENTER);
        newButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        newButton.setFocusable(false);
        toolbar.add(newButton);
        newButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                newButtonActionPerformed(e);
            }
        });
        //delete button
        deleteButton.setText("Delete");
        sizeButton(deleteButton, buttonSize);
        deleteButton.setToolTipText("Delete Current Item");
        deleteButton.setHorizontalTextPosition(SwingConstants.CENTER);
        deleteButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        deleteButton.setFocusable(false);
        toolbar.add(deleteButton);
        deleteButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                deleteButtonActionPerformed(e);
            }
        });
        //save button
        saveButton.setText("Save");
        sizeButton(saveButton, buttonSize);
        saveButton.setToolTipText("Save Current Item");
        saveButton.setHorizontalTextPosition(SwingConstants.CENTER);
        saveButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        saveButton.setFocusable(false);
        toolbar.add(saveButton);
        saveButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                saveButtonActionPerformed(e);
            }
        });
        
        toolbar.addSeparator();
        //previous button
        previousButton.setText("Previous");
        sizeButton(previousButton, buttonSize);
        previousButton.setToolTipText("Display Previous Item");
        previousButton.setHorizontalTextPosition(SwingConstants.CENTER);
        previousButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        previousButton.setFocusable(false);
        toolbar.add(previousButton);
        previousButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                previousButtonActionPerformed(e);
            }
        });
        //next button
        nextButton.setText("Next");
        sizeButton(nextButton, buttonSize);
        nextButton.setToolTipText("Display Next Item");
        nextButton.setHorizontalTextPosition(SwingConstants.CENTER);
        nextButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        nextButton.setFocusable(false);
        toolbar.add(nextButton);
        nextButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                nextButtonActionPerformed(e);
            }
        });
        
        toolbar.addSeparator();
        //print button
        printButton.setText("Print");
        sizeButton(printButton, buttonSize);
        printButton.setToolTipText("Print Inventory List");
        printButton.setHorizontalTextPosition(SwingConstants.CENTER);
        printButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        printButton.setFocusable(false);
        toolbar.add(printButton);
        printButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                printButtonActionPerformed(e);
            }
        });
        //exit button
        exitButton.setText("Exit");
        sizeButton(exitButton, buttonSize);
        exitButton.setToolTipText("Exit Program");
        exitButton.setFocusable(false);
        toolbar.add(exitButton);
        exitButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                exitButtonActionPerformed(e);
            }
        });
 /****************************toolbar buttons end************************/
        
        //inventory item label
        itemLabel.setText("Inventory Item");       
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 0, 10);
        gbc.anchor = GridBagConstraints.EAST;
        getContentPane().add(itemLabel, gbc);
        
        itemTextField.setPreferredSize(new Dimension(400, 25));       
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.insets = new Insets(10, 0, 0, 10);
        gbc.anchor = GridBagConstraints.WEST;
        getContentPane().add(itemTextField, gbc);
        itemTextField.addActionListener(new ActionListener ()
        {
            public void actionPerformed(ActionEvent e)
            {
                itemTextFieldActionPerformed(e);
            }
        });
       //locationlabel 
        locationLabel.setText("Location");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 0, 10);
        gbc.anchor = GridBagConstraints.EAST;
        getContentPane().add(locationLabel, gbc);
        
        locationComboBox.setPreferredSize(new Dimension(270, 25));
        locationComboBox.setFont(new Font("Arial", Font.PLAIN, 12));
        locationComboBox.setEditable(true);
        locationComboBox.setBackground(Color.WHITE);        
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 0, 0, 10);
        gbc.anchor = GridBagConstraints.WEST;
        getContentPane().add(locationComboBox, gbc);
        locationComboBox.addActionListener(new ActionListener ()
        {
            public void actionPerformed(ActionEvent e)
            {
                locationComboBoxActionPerformed(e);
            }
        });
        //marked label
        markedCheckBox.setText("Marked?");
        markedCheckBox.setFocusable(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        getContentPane().add(markedCheckBox, gbc);
        
        //serial number label
        serialLabel.setText("Serial Number");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 10, 0, 10);
        gbc.anchor = GridBagConstraints.EAST;
        getContentPane().add(serialLabel, gbc);
        
        serialTextField.setPreferredSize(new Dimension(270, 25));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 0, 0, 10);
        gbc.anchor = GridBagConstraints.WEST;
        getContentPane().add(serialTextField, gbc);
        serialTextField.addActionListener(new ActionListener ()
        {
            public void actionPerformed(ActionEvent e)
            {
                serialTextFieldActionPerformed(e);
            }
        });
        //purchase-price label
        priceLabel.setText("Purchase Price");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 10, 0, 10);
        gbc.anchor = GridBagConstraints.EAST;
        getContentPane().add(priceLabel, gbc);
        
        priceTextField.setPreferredSize(new Dimension(160, 25));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 0, 10);
        gbc.anchor = GridBagConstraints.WEST;
        getContentPane().add(priceTextField, gbc);
        priceTextField.addActionListener(new ActionListener ()
        {
            public void actionPerformed(ActionEvent e)
            {
                priceTextFieldActionPerformed(e);
            }
        });
        //datepurchase label
        dateLabel.setText("Date Purchased");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 10, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        getContentPane().add(dateLabel, gbc);
        
        dateDateChooser.setPreferredSize(new Dimension(120, 25));
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 0, 10);
        gbc.anchor = GridBagConstraints.WEST;
        getContentPane().add(dateDateChooser, gbc);
        dateDateChooser.addPropertyChangeListener(new PropertyChangeListener()
        {
            public void propertyChange(PropertyChangeEvent e)
            {
                dateDateChooserPropertyChange(e);
            }
        });
        //store label
        storeLabel.setText("Store/Website");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.insets = new Insets(10, 10, 0, 10);
        gbc.anchor = GridBagConstraints.EAST;
        getContentPane().add(storeLabel, gbc);
        
        storeTextField.setPreferredSize(new Dimension(400, 25));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 5;
        gbc.insets = new Insets(10, 0, 0, 10);
        gbc.anchor = GridBagConstraints.WEST;
        getContentPane().add(storeTextField, gbc);
        storeTextField.addActionListener(new ActionListener ()
        {
            public void actionPerformed(ActionEvent e)
            {
                storeTextFieldActionPerformed(e);
            }
        });
        //note label
        noteLabel.setText("Note");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.insets = new Insets(10, 10, 0, 10);
        gbc.anchor = GridBagConstraints.EAST;
        getContentPane().add(noteLabel, gbc);
        
        noteTextField.setPreferredSize(new Dimension(400, 25));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.gridwidth = 5;
        gbc.insets = new Insets(10, 0, 0, 10);
        gbc.anchor = GridBagConstraints.WEST;
        getContentPane().add(noteTextField, gbc);
        noteTextField.addActionListener(new ActionListener ()
        {
            public void actionPerformed(ActionEvent e)
            {
                noteTextFieldActionPerformed(e);
            }
        });
        //photolabel
        photoLabel.setText("Photo");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.insets = new Insets(10, 10, 0, 10);
        gbc.anchor = GridBagConstraints.EAST;
        getContentPane().add(photoLabel, gbc);
        
        photoTextArea.setPreferredSize(new Dimension(350, 35));
        photoTextArea.setFont(new Font("Arial", Font.PLAIN, 12));
        photoTextArea.setEditable(false);
        photoTextArea.setLineWrap(true);
        photoTextArea.setWrapStyleWord(true);
        photoTextArea.setBackground(new Color(255, 255, 192));
        photoTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        photoTextArea.setFocusable(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(10, 0, 0, 10);
        gbc.anchor = GridBagConstraints.WEST;
        getContentPane().add(photoTextArea, gbc);
        
        //photo button 
        photoButton.setText("...");
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 6;
        gbc.insets = new Insets(10, 0, 0, 10);
        gbc.anchor = GridBagConstraints.WEST;
        getContentPane().add(photoButton, gbc);
        photoButton.addActionListener(new ActionListener ()
        {
            public void actionPerformed(ActionEvent e)
            {
                photoButtonActionPerformed(e);
            }
        });
        //searchpanel
        searchPanel.setPreferredSize(new Dimension(240, 160));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Item Search"));
        searchPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        getContentPane().add(searchPanel, gbc);
        
        int x = 0, y = 0;
// create and position 26 buttons
        for (int i = 0; i < 26; i++)
        {
// create new button
            searchButton[i] = new JButton();
// set text property
            searchButton[i].setText(String.valueOf((char) (65 + i)));
            searchButton[i].setFont(new Font("Arial", Font.BOLD, 12));
            searchButton[i].setMargin(new Insets(-10, -10, -10, -10));
            sizeButton(searchButton[i], new Dimension(37, 27));
            searchButton[i].setBackground(Color.YELLOW);
            searchButton[i].setFocusable(false);
            gbc = new GridBagConstraints();
            gbc.gridx = x;
            gbc.gridy = y;
            searchPanel.add(searchButton[i], gbc);
// add method
            searchButton[i].addActionListener(new ActionListener ()
            {
                public void actionPerformed(ActionEvent e)
                {
                    searchButtonActionPerformed(e);
                }
            });
            x++;
// six buttons per row
            if (x % 6 == 0)
            {
                x = 0;
                y++;
            }
        }
        //photopanel
        photoPanel.setPreferredSize(new Dimension(240, 160));
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 7;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 0, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        getContentPane().add(photoPanel, gbc);
/* ***************Frame label,textField,comboBox end**************************** */
        pack();
        Dimension screenSize =
                Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width - getWidth())), (int) (0.5 * (screenSize.height -
                getHeight())), getWidth(), getHeight());
        int n;
// open file for entries
        try
        {
            BufferedReader inputFile = new BufferedReader(new FileReader("inventory.txt"));
            numberEntries =
                    Integer.valueOf(inputFile.readLine()).intValue();
            if (numberEntries != 0)
            {
                for (int i = 0; i < numberEntries; i++)
                {
                    myInventory[i] = new InventoryItem();
                    myInventory[i].description = inputFile.readLine();
                    myInventory[i].location = inputFile.readLine();
                    myInventory[i].serialNumber = inputFile.readLine();
                    myInventory[i].marked =
                            Boolean.valueOf(inputFile.readLine()).booleanValue();
                    myInventory[i].purchasePrice =
                    inputFile.readLine();
                    myInventory[i].purchaseDate = inputFile.readLine();
                    myInventory[i].purchaseLocation = inputFile.readLine();
                    myInventory[i].note = inputFile.readLine();
                    myInventory[i].photoFile = inputFile.readLine();
                }
            }
// read in combo box elements
            n = Integer.valueOf(inputFile.readLine()).intValue();
            if (n != 0)
            {
                for (int i = 0; i < n; i++)
                {
                    locationComboBox.addItem(inputFile.readLine());
                }
            }
            inputFile.close();
            currentEntry = 1;
            showEntry(currentEntry);
        }
        catch (Exception ex)
        {
            numberEntries = 0;
            currentEntry = 0;
        }
        if (numberEntries == 0)
        {
            newButton.setEnabled(false);
            deleteButton.setEnabled(false);
            nextButton.setEnabled(false);
            previousButton.setEnabled(false);
            printButton.setEnabled(false);
        }
    }//end of constructor
  
    /*********************** Buttons functionality ***************************/
    
    //exitWindow message
    private void exitForm(WindowEvent evt)
    {
        if (JOptionPane.showConfirmDialog(null, "Any unsaved changes will be lost.\nAre you sure you want to exit?", "Exit Program", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION)
            return;
// write entries back to file
        try
        {
            PrintWriter outputFile = new PrintWriter(new BufferedWriter(new
                    FileWriter("inventory.txt")));
            outputFile.println(numberEntries);
            if (numberEntries != 0)
            {
                for (int i = 0; i < numberEntries; i++)
                {
                    outputFile.println(myInventory[i].description);
                    outputFile.println(myInventory[i].location);
                    outputFile.println(myInventory[i].serialNumber);
                    outputFile.println(myInventory[i].marked);
                    outputFile.println(myInventory[i].purchasePrice);
                    outputFile.println(myInventory[i].purchaseDate);
                    outputFile.println(myInventory[i].purchaseLocation);
                    outputFile.println(myInventory[i].note);
                    outputFile.println(myInventory[i].photoFile);
                }
            }
// write combo box entries
            outputFile.println(locationComboBox.getItemCount());
            if (locationComboBox.getItemCount() != 0)
            {
                for (int i = 0; i < locationComboBox.getItemCount(); i++)
                    outputFile.println(locationComboBox.getItemAt(i));
            }
            outputFile.close();
        }
catch (Exception ex)
        {
        }
        System.exit(0);
    }
    //newButtonFunctionality
    private void newButtonActionPerformed(ActionEvent e)
    {
        checkSave();
        blankValues();
    }
    //deleteButtonFunctionality
    private void deleteButtonActionPerformed(ActionEvent e)
    {
        if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?",
                "Delete Inventory Item", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION)
            return;
        deleteEntry(currentEntry);
        if (numberEntries == 0)
        {
            currentEntry = 0;
            blankValues();
        }
        else
        {
            currentEntry--;
            if (currentEntry == 0)
                currentEntry = 1;
            showEntry(currentEntry);
        }
    }
    //saveButtonFunctionality
    private void saveButtonActionPerformed(ActionEvent e)
    {
// check for description
        itemTextField.setText(itemTextField.getText().trim());
        if (itemTextField.getText().equals(""))
        {
            JOptionPane.showConfirmDialog(null, "Must have item description.", "Error",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            itemTextField.requestFocus();
            return;
        }
        if (newButton.isEnabled())
        {
// delete edit entry then resave
            deleteEntry(currentEntry);
        }
// capitalize first letter
        String s = itemTextField.getText();
        itemTextField.setText(s.substring(0, 1).toUpperCase() + s.substring(1));
        numberEntries++;
// determine new current entry location based on description
        currentEntry = 1;
        if (numberEntries != 1)
        {
            do
            {
                if
                (itemTextField.getText().compareTo(myInventory[currentEntry - 1].description) < 0)
                    break;
                currentEntry++;
            }
            while (currentEntry < numberEntries);
        }
// move all entries below new value down one position unless at end
        if (currentEntry != numberEntries)
        {
            for (int i = numberEntries; i >= currentEntry + 1; i--)
            {
                myInventory[i - 1] = myInventory[i - 2];
                myInventory[i - 2] = new InventoryItem();
            }
        }
        myInventory[currentEntry - 1] = new InventoryItem();
        myInventory[currentEntry - 1].description = itemTextField.getText();
        myInventory[currentEntry - 1].location =
                locationComboBox.getSelectedItem().toString();
        myInventory[currentEntry - 1].marked = markedCheckBox.isSelected();
        myInventory[currentEntry - 1].serialNumber = serialTextField.getText();
        myInventory[currentEntry - 1].purchasePrice = priceTextField.getText();
        myInventory[currentEntry - 1].purchaseDate =
                dateToString(dateDateChooser.getDate());
        myInventory[currentEntry - 1].purchaseLocation = storeTextField.getText();
        myInventory[currentEntry - 1].photoFile = photoTextArea.getText();
        myInventory[currentEntry - 1].note = noteTextField.getText();
        showEntry(currentEntry);
        if (numberEntries < maximumEntries)
            newButton.setEnabled(true);
        else
            newButton.setEnabled(false);
        deleteButton.setEnabled(true);
        printButton.setEnabled(true);
    }
    //previousButtonFunctionality
    private void previousButtonActionPerformed(ActionEvent e)
    {
        checkSave();
        currentEntry--;
        showEntry(currentEntry);
    }
    //nextButtonFunctionality
    private void nextButtonActionPerformed(ActionEvent e)
    {
        checkSave();
        currentEntry++;
        showEntry(currentEntry);
    }
    //printButtonFunctionality
    private void printButtonActionPerformed(ActionEvent e)
    {
        lastPage = (int) (1 + (numberEntries - 1) / entriesPerPage);
        PrinterJob inventoryPrinterJob = PrinterJob.getPrinterJob();
        inventoryPrinterJob.setPrintable(new InventoryDocument());
        if (inventoryPrinterJob.printDialog())
        {
            try
            {
                inventoryPrinterJob.print();
            }
            catch (PrinterException ex)
            {
                JOptionPane.showConfirmDialog(null, ex.getMessage(), "Print Error",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    //exitButtonFunctionality
    private void exitButtonActionPerformed(ActionEvent e)
    {
        exitForm(null);
    }
    //photoButtonFunctionality
    private void photoButtonActionPerformed(ActionEvent e)
    {
        JFileChooser openChooser = new JFileChooser();
        openChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        openChooser.setDialogTitle("Open Photo File");
        openChooser.addChoosableFileFilter(new FileNameExtensionFilter("Photo Files",
                "jpg"));
        if (openChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            showPhoto(openChooser.getSelectedFile().toString());
    }
    //searchButtonFunctionality
    private void searchButtonActionPerformed(ActionEvent e)
    {
        int i;
        if (numberEntries == 0)
            return;
// search for item letter
        String letterClicked = e.getActionCommand();
        i = 0;
        do
        {
            if (myInventory[i].description.substring(0, 1).equals(letterClicked))
            {
                currentEntry = i + 1;
                showEntry(currentEntry);
                return;
            }
            i++;
        }
        while (i < numberEntries);
        JOptionPane.showConfirmDialog(null, "No " + letterClicked + " inventory items.",
                "None Found", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
    }
    //locationComboBoxFunctionality
    private void locationComboBoxActionPerformed(ActionEvent e)
    {
// If in list - exit method
        if (locationComboBox.getItemCount() != 0)
        {
            for (int i = 0; i < locationComboBox.getItemCount(); i++)
            {
                if (locationComboBox.getSelectedItem().toString().equals(locationComboBox.getItemAt(i).toString()))
                {
                    serialTextField.requestFocus();
                    return;
                }
            }
        }
// If not found, add to list box
        locationComboBox.addItem(locationComboBox.getSelectedItem());
        serialTextField.requestFocus();
    }
   
   /************************** focus Request *******************************/
    private void itemTextFieldActionPerformed(ActionEvent e)
    {
        locationComboBox.requestFocus();
    }
    private void serialTextFieldActionPerformed(ActionEvent e)
    {
        priceTextField.requestFocus();
    }
    private void priceTextFieldActionPerformed(ActionEvent e)
    {
        dateDateChooser.requestFocus();
    }
    private void dateDateChooserPropertyChange(PropertyChangeEvent e)
    {
        storeTextField.requestFocus();
    }
    private void storeTextFieldActionPerformed(ActionEvent e)
    {
        noteTextField.requestFocus();
    }
    private void noteTextFieldActionPerformed(ActionEvent e)
    {
        photoButton.requestFocus();
    }
    private void sizeButton(JButton b, Dimension d)
    {
        b.setPreferredSize(d);
        b.setMinimumSize(d);
        b.setMaximumSize(d);
    }
    private void showEntry(int j)
    {
// display entry j (1 to numberEntries)
        itemTextField.setText(myInventory[j - 1].description);
        locationComboBox.setSelectedItem(myInventory[j - 1].location);
        markedCheckBox.setSelected(myInventory[j - 1].marked);
        serialTextField.setText(myInventory[j - 1].serialNumber);
        priceTextField.setText(myInventory[j - 1].purchasePrice);
        dateDateChooser.setDate(stringToDate(myInventory[j - 1].purchaseDate));
        storeTextField.setText(myInventory[j - 1].purchaseLocation);
        noteTextField.setText(myInventory[j - 1].note);
        showPhoto(myInventory[j - 1].photoFile);
        nextButton.setEnabled(true);
        previousButton.setEnabled(true);
        if (j == 1)
            previousButton.setEnabled(false);
        if (j == numberEntries)
            nextButton.setEnabled(false);
        itemTextField.requestFocus();
    }
    
    /****************** oops concept *****************/
	private Date stringToDate(String s)
    {
        int month = Integer.valueOf(s.substring(0, 2)).intValue() - 1;
        int day = Integer.valueOf(s.substring(3, 5)).intValue();
        int year = Integer.valueOf(s.substring(6)).intValue() - 1900;
        return(new Date(year, month, day));
    }
    private String dateToString(Date date)
    {
        String yString = String.valueOf(date.getYear() + 1900);
        int month = date.getMonth() + 1;
        String mString = new DecimalFormat("00").format(month);
        int day = date.getDate();
        String dString = new DecimalFormat("00").format(day);
        return(mString + "/" + dString + "/" + yString);
    }
    private void showPhoto(String photoFile)
    {
        if (!photoFile.equals(""))
        {
            try
            {
                photoTextArea.setText(photoFile);
            }
            catch (Exception ex)
            {
                photoTextArea.setText("");
            }
        }
        else
        {
            photoTextArea.setText("");
        }
        photoPanel.repaint();
    }
    private void blankValues()
    {
// blank input screen
        newButton.setEnabled(false);
        deleteButton.setEnabled(false);
        saveButton.setEnabled(true);
        previousButton.setEnabled(false);
        nextButton.setEnabled(false);
        printButton.setEnabled(false);
        itemTextField.setText("");
        locationComboBox.setSelectedItem("");
        markedCheckBox.setSelected(false);
        serialTextField.setText("");
        priceTextField.setText("");
        dateDateChooser.setDate(new Date());
        storeTextField.setText("");
        noteTextField.setText("");
        photoTextArea.setText("");
        photoPanel.repaint();
        itemTextField.requestFocus();
    }
    private void deleteEntry(int j)
    {
// delete entry j
        if (j != numberEntries)
        {
// move all entries under j up one level
            for (int i = j; i < numberEntries; i++)
            {
                myInventory[i - 1] = new InventoryItem();
                myInventory[i - 1] = myInventory[i];
            }
        }
        numberEntries--;
    }
    private void checkSave()
    {
        boolean edited = false;
        if (!myInventory[currentEntry - 1].description.equals(itemTextField.getText()))
            edited = true;
        else if (!myInventory[currentEntry -
        1].location.equals(locationComboBox.getSelectedItem().toString()))
        edited = true;
    else if (myInventory[currentEntry - 1].marked != markedCheckBox.isSelected())
        edited = true;
    else if (!myInventory[currentEntry - 1].serialNumber.equals(serialTextField.getText()))
        edited = true;
    else if (!myInventory[currentEntry - 1].purchasePrice.equals(priceTextField.getText()))
        edited = true;
    else if (!myInventory[currentEntry -
            1].purchaseDate.equals(dateToString(dateDateChooser.getDate())))
        edited = true;
    else if (!myInventory[currentEntry -
            1].purchaseLocation.equals(storeTextField.getText()))
        edited = true;
    else if (!myInventory[currentEntry - 1].note.equals(noteTextField.getText()))
        edited = true;
    else if (!myInventory[currentEntry - 1].photoFile.equals(photoTextArea.getText()))
        edited = true;
        if (edited)
        {
            if (JOptionPane.showConfirmDialog(null, "You have edited this item. Do you want to save the changes?", "Save Item", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
                saveButton.doClick();
        }
    }
}



