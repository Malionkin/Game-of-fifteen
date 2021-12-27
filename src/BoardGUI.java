import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class BoardGUI  {
    JFrame frame, frame_1;
    JPanel mainPanel;
    JButton[][] button;
    int cols;
    int rows;
    JLabel[][] label;
    int[][] board;
    Logic logic;

    public BoardGUI() {

        rows = 4;
        cols = 4;
        board = new int[rows][cols];
        initGUI();
        logic = new Logic(this);

    }

    public void initGUI() {
        frame = new JFrame();
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.white);
        mainPanel.setLayout(new GridLayout(4, 4));
        button = new JButton[rows][cols];
        label = new JLabel[rows][cols];

        this.shuffleBoard();


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                button[i][j] = new JButton();
                String text = i + "," + j;
                button[i][j].setText(text);
                button[i][j].setFont(new Font("TimesRoman", Font.PLAIN, 0));
                button[i][j].addActionListener(new MyActionListener(this));
                button[i][j].addKeyListener(new MyKeyListener(this));
                String filename;
                int val = board[i][j];
                if (val != -1) {
                    filename = "pics/" + val + ".png";
                    label[i][j] = new JLabel(new ImageIcon(filename), JLabel.CENTER);
                    //val = 16;
                } else {
                    label[i][j] = new JLabel(new ImageIcon("pics/16.png"));
                }


                button[i][j].add(label[i][j]);
                button[i][j].setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
                button[i][j].setBackground(Color.CYAN);
                mainPanel.add(button[i][j]);
            }
        }


        frame.add(mainPanel);

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void shuffleBoard() {
        Random rand = new Random();
        int[] array = new int[16];
        for (int i = 0; i < 16; i++) {
            array[i] = i + 1;
        }
        array[15] = -1;
        for (int i = 0; i < 16; i++) {
            int index = rand.nextInt(16);
            int temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }

        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = array[count];
                count = count + 1;
                System.out.print(board[i][j] + "\t");
            }
            System.out.println(" ");
        }

    }


    public void updatePanel() {

        int counter = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == counter) {
                    button[i][j].setBackground(Color.green);
                    counter += 1;
                    if (counter == 16) {
                        winPanel();
                    }
                } else {
                    button[i][j].setBackground(Color.CYAN);
                    counter += 1;
                }

            }
        }
    }


    public void winPanel() {
        frame_1 = new JFrame();
        frame_1.setSize(500, 500);
        frame_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame_1.setLocationRelativeTo(null);
        frame_1.setTitle("YEEEEEEEEEEEES!");
        JLabel lab = new JLabel();
        lab.setIcon(new ImageIcon("pics/16.png"));
        frame_1.add(lab);
        frame_1.setVisible(true);
    }
}





