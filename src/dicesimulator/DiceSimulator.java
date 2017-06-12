/*
 * Programmer: Snegirev, Domentyan
 * Chemeketa Community College
 * May 4, 2017
 * Class: CIS234J
 * Assignment: Week 3 Lab - Threaded Dice
 * File Name: DiceSimulator.java
 * Description: This class uses two individual threads to roll die. It counts
 *              and displays the number of times each face is rolled, as well
 *              as each time sevens, elevens or doubles are rolled.
 */
package dicesimulator;

import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import threadeddice.Die;

/**
 *  A dice simulator that simulates the rolling of two die.
 */
public class DiceSimulator extends JFrame
{
   // JLabel and JTextField to display number of 1s
   private JLabel side1JLabel;
   private JTextField output1JTextField;

   // JLabel and JTextField to display number of 2s
   private JLabel side2JLabel; 
   private JTextField output2JTextField;

   // JLabel and JTextField to display number of 3s
   private JLabel side3JLabel;
   private JTextField output3JTextField;

   // JLabel and JTextField to display number of 4s
   private JLabel side4JLabel;
   private JTextField output4JTextField;

   // JLabel and JTextField to display number of 5s
   private JLabel side5JLabel; 
   private JTextField output5JTextField;

   // JLabel and JTextField to display number of 6s
   private JLabel side6JLabel; 
   private JTextField output6JTextField;
   
   // JLabel and JTextField to display number of doubles
   private JLabel doublesJLabel;
   private JTextField doublesJTextField;

   // JLabel and JTextField to display number of 7s
   private JLabel sevensJLabel;
   private JTextField sevensJTextField;

   /// JLabel and JTextField to display number of 11s
   private JLabel elevensJLabel;
   private JTextField elevensJTextField;

   // JLabel and JTextField to display total rolls
   private JLabel totalJLabel; 
   private JTextField totalJTextField;

   // JLabels to display dice
   private JLabel die1JLabel;
   private JLabel die2JLabel;

   // JButton to roll, stop, and reset dice
   private JButton rollJButton;
   private JButton stopJButton;
   private JButton resetJButton;

   // Die face constants
   private final int ONE = 1; 
   private final int TWO = 2;
   private final int THREE = 3;
   private final int FOUR = 4;
   private final int FIVE = 5;
   private final int SIX = 6;
   
   // Constants for 7, 11, and interval count
   private final int SEVEN = 7;
   private final int ELEVEN = 11;
   private final int COUNT = 1;
   
   // File name and directory constants for die images
   private final String FILE_PREFIX = "images/die";
   private final String FILE_SUFFIX = ".png";

   // Set screen dimension
   private final int WIDTH = 350;
   private final int HEIGHT = 375;

   // Variable that stops threads
   private volatile boolean running;

   // Create new die objects
   Die die1 = new Die();
   Die die2 = new Die();

   // Declare new thread for each die object
   Thread dieThread1, dieThread2;

   // Variables for roll speed and count values
   private int rollSpeed, count1, count2, count3, count4, count5, count6,
            countDoubles, countSevens, countElevens;

   // Declare variables for each die face value and sum of both die.
   private int die1Value, die2Value, sumOfDie;
   
   // No-argument constructor
   public DiceSimulator()
   {
      createUserInterface();
   }
   
   // create and position GUI components; register event handlers
   private void createUserInterface()
   {
        // Get content pane for attaching GUI components
        Container contentPane = getContentPane();

        // Enable explicit positioning of GUI components
        contentPane.setLayout(null);

        // Set up side1JLabel
        side1JLabel = new JLabel();
        side1JLabel.setBounds(16, 16, 48, 23);
        side1JLabel.setText("Side 1:");
        contentPane.add(side1JLabel);

        // Set up output1JTextField
        output1JTextField = new JTextField();
        output1JTextField.setBounds(72, 16, 56, 23);
        output1JTextField.setText("0");
        output1JTextField.setEditable(false);
        output1JTextField.setHorizontalAlignment(JTextField.CENTER);
        contentPane.add(output1JTextField);

        // Set up side2JLabel
        side2JLabel = new JLabel();
        side2JLabel.setBounds(16, 48, 48, 23);
        side2JLabel.setText("Side 2:");
        contentPane.add(side2JLabel);

        // Set up output2JTextField
        output2JTextField = new JTextField();
        output2JTextField.setBounds(72, 48, 56, 23);
        output2JTextField.setText("0");
        output2JTextField.setEditable(false);
        output2JTextField.setHorizontalAlignment(JTextField.CENTER);
        contentPane.add(output2JTextField);

        // Set up side3JLabel
        side3JLabel = new JLabel();
        side3JLabel.setBounds(16, 80, 48, 23);
        side3JLabel.setText("Side 3:");
        contentPane.add(side3JLabel);

        // Set up output3JTextField
        output3JTextField = new JTextField();
        output3JTextField.setBounds(72, 80, 56, 23);
        output3JTextField.setText("0");
        output3JTextField.setEditable(false);
        output3JTextField.setHorizontalAlignment(JTextField.CENTER);
        contentPane.add(output3JTextField);

        // Set up side4JLabel
        side4JLabel = new JLabel();
        side4JLabel.setBounds(16, 112, 48, 23);
        side4JLabel.setText("Side 4:");
        contentPane.add(side4JLabel);

        // Set up output4JTextField
        output4JTextField = new JTextField();
        output4JTextField.setBounds(72, 111, 56, 23);
        output4JTextField.setText("0");
        output4JTextField.setEditable(false);
        output4JTextField.setHorizontalAlignment(JTextField.CENTER);
        contentPane.add(output4JTextField);

        // Set up side5JLabel
        side5JLabel = new JLabel();
        side5JLabel.setBounds(16, 144, 48, 23);
        side5JLabel.setText("Side 5:");
        contentPane.add(side5JLabel);

        // Set up output5JTextField
        output5JTextField = new JTextField();
        output5JTextField.setBounds(72, 144, 56, 23);
        output5JTextField.setText("0");
        output5JTextField.setEditable(false);
        output5JTextField.setHorizontalAlignment(JTextField.CENTER);
        contentPane.add(output5JTextField);

        // Set up side6JLabel
        side6JLabel = new JLabel();
        side6JLabel.setBounds(16, 176, 48, 23);
        side6JLabel.setText("Side 6:");
        contentPane.add(side6JLabel);

        // Set up output6JTextField
        output6JTextField = new JTextField();
        output6JTextField.setBounds(72, 176, 56, 23);
        output6JTextField.setText("0");
        output6JTextField.setEditable(false);
        output6JTextField.setHorizontalAlignment(JTextField.CENTER);
        contentPane.add(output6JTextField);

        // Set up doubleJLabel
        doublesJLabel = new JLabel();
        doublesJLabel.setBounds(16, 208, 48, 23);
        doublesJLabel.setText("Double:");
        contentPane.add(doublesJLabel);

        // Set up doubleJTextField
        doublesJTextField = new JTextField();
        doublesJTextField.setBounds(72, 208, 56, 23);
        doublesJTextField.setText("0");
        doublesJTextField.setEditable(false);
        doublesJTextField.setHorizontalAlignment(JTextField.CENTER);
        contentPane.add(doublesJTextField);

        // Set up sevenJLabel
        sevensJLabel = new JLabel();
        sevensJLabel.setBounds(16, 240, 48, 23);
        sevensJLabel.setText("Seven:");
        contentPane.add(sevensJLabel);

        // Set up sevenJTextField
        sevensJTextField = new JTextField();
        sevensJTextField.setBounds(72, 240, 56, 23);
        sevensJTextField.setText("0");
        sevensJTextField.setEditable(false);
        sevensJTextField.setHorizontalAlignment(JTextField.CENTER);
        contentPane.add(sevensJTextField);

        // Set up elevenJLabel
        elevensJLabel = new JLabel();
        elevensJLabel.setBounds(16, 272, 48, 23);
        elevensJLabel.setText("Eleven:");
        contentPane.add(elevensJLabel);

        // Set up elevenJTextField
        elevensJTextField = new JTextField();
        elevensJTextField.setBounds(72, 272, 56, 23);
        elevensJTextField.setText("0");
        elevensJTextField.setEditable(false);
        elevensJTextField.setHorizontalAlignment(JTextField.CENTER);
        contentPane.add(elevensJTextField);

        // Set up die1JLabel
        die1JLabel = new JLabel("");
        die1JLabel.setBounds(152, 32, 72, 64);
        contentPane.add(die1JLabel);

        // Set up die2JLabel
        die2JLabel = new JLabel("");
        die2JLabel.setBounds(208, 96, 72, 64);
        contentPane.add(die2JLabel);

        // Set up rollJButton
        rollJButton = new JButton();
        rollJButton.setBounds(150, 190, 136, 24);
        rollJButton.setText("Roll");
        contentPane.add(rollJButton);

        rollJButton.addActionListener(
            new ActionListener() 
            {
                /**
                 * Anonymous inner class. 
                 * Handles an event when rollJButton is clicked.
                 *
                 * @param event Event handler.
                 */
                public void actionPerformed(ActionEvent event) 
                {
                    rollJButtonActionPerformed(event);
                }
            }
        );

        // Set up stopJButton
        stopJButton = new JButton();
        stopJButton.setBounds(150, 220, 136, 24);
        stopJButton.setText("Stop");
        contentPane.add(stopJButton);

        stopJButton.addActionListener(
            new ActionListener() 
            {
                /**
                 * Anonymous inner class. 
                 * Handles an event when stopJButton is clicked.
                 *
                 * @param event Event handler.
                 */
                public void actionPerformed(ActionEvent event) 
                {
                    stopJButtonActionPerformed(event);
                }
            }
        );

        // Set up resetJButton
        resetJButton = new JButton();
        resetJButton.setBounds(150, 270, 136, 24);
        resetJButton.setText("Reset");
        contentPane.add(resetJButton);

        resetJButton.addActionListener(
            new ActionListener() 
            {
                /**
                 * Anonymous inner class. 
                 * Handles an event when resetJButton is clicked.
                 *
                 * @param event Event handler.
                 */
                public void actionPerformed(ActionEvent event)
                {
                    resetJButtonActionPerformed(event);
                }
            }
        );

        // Set the application's window properties
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setTitle("Dice Simulator");             // set title bar string
        int x = (screen.width - WIDTH) / 2;
        int y = (screen.height - HEIGHT) / 2;
        setBounds(x, y, WIDTH, HEIGHT);
        setSize(WIDTH, HEIGHT);			// set window size
        setVisible(true);       		// display window   
      
   } // end method createUserInterface
   
   /**
    * rollJButtionAction Performed Event Handler method
    * Starts thread for each die to mimic a roll.     *
    * @param event Handles an event when rollJButton is clicked.
    */
    private void rollJButtonActionPerformed(ActionEvent event)
    {
        /**
         * Inner Thread class for die1
         */
        Runnable die1Thread = new Runnable() 
        {
            /**
             * Displays roll animation as the die rolls for die1
             */
            public void run()
            {

                while(running)
                {
                    // Set the rollSpeed and roll the die
                    rollSpeed = ((int) (Math.random() * 200) + 15);
                    die1Value = die1.roll();

                    // Displays dice images
                    displayDie(die1JLabel, die1Value);

                    // Displays die at different speeds every thread interval
                    try 
                    {
                        Thread.sleep(rollSpeed);
                    } 
                    catch (InterruptedException ex)
                    {
                        Logger.getLogger(
                                DiceSimulator.class.getName()).log(
                                Level.SEVERE, null, ex);
                    }
                }
            }
        };

        /**
         * Inner Thread class for die2
         */
        Runnable die2Thread = new Runnable() 
        {
            /**
             * Displays roll animation as the die rolls for die2
             */
            public void run() 
            {

                while (running) 
                {
                    // Set the rollSpeed and roll the die
                    rollSpeed = ((int) (Math.random() * 300) + 25);
                    die2Value = die2.roll();

                    // Displays dice images 
                    displayDie(die2JLabel, die2Value);

                    // Displays die at different speeds every thread interval
                    try 
                    {
                        Thread.sleep(rollSpeed);
                    } 
                    catch (InterruptedException ex) 
                    {
                        Logger.getLogger(
                                DiceSimulator.class.getName()).log(
                                Level.SEVERE, null, ex);
                    }
                }
            }
        };

        // Change running boolean to true for threads
        running = true;

        // Create a new thread for each die
        dieThread1 = new Thread(die1Thread);
        dieThread2 = new Thread(die2Thread);

        // Start each thread
        dieThread1.start();
        dieThread2.start();

    } // end method rollJButtonActionPerformed
    
   /**
    * stopJButtionAction Performed Event Handler method
    * Stops the dice. displays frequency of each side and every special roll     *
    * @param event Handles an event when stopJButton is clicked.
    */
    private void stopJButtonActionPerformed(ActionEvent event)
    {
        // Change running boolean to false for threads
        running = false;
        
        // Add shown values and specials rolls of the die 
        displayFrequency(die1Value);
        displayFrequency(die2Value);
        displaySpecials(die1Value, die2Value);
    } // end method stopJButtonActionPerformed
    
   /**
    * resetJButtionAction Performed Event Handler method
    * Pauses each thread, resets the count values and JLabel/JTextFields.
    * @param event Handles an event when resetJButton is clicked.
    */
    private void resetJButtonActionPerformed(ActionEvent event) 
    {
        // Change running boolean to false for threads
        running = false;

        // Clear die label icons
        die1JLabel.setIcon(null);
        die2JLabel.setIcon(null);

        // Set each count value to 0
        count1 = 0;
        count2 = 0;
        count3 = 0;
        count4 = 0;
        count5 = 0;
        count6 = 0;
        countDoubles = 0;
        countSevens = 0;
        countElevens = 0;

        // Resets each text field
        output1JTextField.setText(String.valueOf(count1));
        output2JTextField.setText(String.valueOf(count2));
        output3JTextField.setText(String.valueOf(count3));
        output4JTextField.setText(String.valueOf(count4));
        output5JTextField.setText(String.valueOf(count5));
        output6JTextField.setText(String.valueOf(count6));
        doublesJTextField.setText(String.valueOf(countDoubles));
        sevensJTextField.setText(String.valueOf(countSevens));
        elevensJTextField.setText(String.valueOf(countElevens));
    } // end method resetJButtonActionPerformed
    
   /**
    * displayDie method
    * Displays the die image for each rolled face value.
    * @param picDieJLabel The JLabel of either die1 or die2.
    * @param face The rolled die face value.
    */
    private void displayDie(JLabel picDieJLabel, int face) 
    {
        ImageIcon image = new ImageIcon(
                Toolkit.getDefaultToolkit().getImage(
                getClass().getResource(FILE_PREFIX + face + FILE_SUFFIX))
        );

        // Displays die images in picDieJLabel
        picDieJLabel.setIcon(image);
    } // end method displayDie
    
   /**
    * displayFrequency method
    * Counts and displays current frequency of every rolled face value.
    * @param face The rolled die face value.
    */
    private void displayFrequency(int face) 
    {
        switch (face) 
        {
            case 1:
                count1 += COUNT;
                output1JTextField.setText(String.valueOf(count1));
                break;
            case 2:
                count2 += COUNT;
                output2JTextField.setText(String.valueOf(count2));
                break;
            case 3:
                count3 += COUNT;
                output3JTextField.setText(String.valueOf(count3));
                break;
            case 4:
                count4 += COUNT;
                output4JTextField.setText(String.valueOf(count4));
                break;
            case 5:
                count5 += COUNT;
                output5JTextField.setText(String.valueOf(count5));
                break;
            case 6:
                count6 += COUNT;
                output6JTextField.setText(String.valueOf(count6));
                break;
            default:
                break;
        }
    } // end method displayFrequency
    
    /**
     * displaySpecials method
     * Counts and displays frequency of every rolled doubles, sevens,
     * and elevens.
     * @param face1 The rolled face value of die1.
     * @param face2 The rolled face value of die2.
     */
    public void displaySpecials(int face1, int face2) 
    {
        sumOfDie = face1 + face2;

        if(face1 == face2)
        {
            countDoubles += COUNT;
            doublesJTextField.setText(String.valueOf(countDoubles));
        }
        if(sumOfDie == SEVEN)
        {
            countSevens += COUNT;
            sevensJTextField.setText(String.valueOf(countSevens));
        } 
        else if(sumOfDie == ELEVEN)
        {
            countElevens += COUNT;
            elevensJTextField.setText(String.valueOf(countElevens));
        }
    } // end method displaySpecials

   /**
    * main method
    * Creates a new DiceSimulator.
    */
    public static void main(String[] args)
    {
        DiceSimulator application = new DiceSimulator();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } // end method main
   
} // end class DiceSimulator
