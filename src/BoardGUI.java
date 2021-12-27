import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class BoardGUI implements ActionListener, KeyListener {
    JFrame frame, frame_1;
    JPanel mainPanel;
    JButton [][] button;
    int cols;
    int rows;
    JLabel [][] label;
    int [][] board;

    public BoardGUI(){

        rows = 4;
        cols = 4;
        board = new int[rows][cols];
        initGUI();

    }

    public void initGUI(){
        frame = new JFrame();
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.white);
        mainPanel.setLayout(new GridLayout(4,4));
        button = new JButton[rows][cols];
        label = new JLabel[rows][cols];

        this.shuffleBoard();


        for (int i = 0; i < rows; i++){
            for(int j = 0; j< cols; j++){
                button[i][j] = new JButton();
                String text = i+","+j;
                button[i][j].setText(text);
                button[i][j].setFont(new Font("TimesRoman", Font.PLAIN, 0));
                button[i][j].addActionListener(this);
                button[i][j].addKeyListener(this);
                String filename;
                int val = board[i][j];
                if (val!= -1){
                    filename = "pics/" + val  + ".png";
                    label[i][j] = new JLabel(new ImageIcon(filename), JLabel.CENTER);
                    //val = 16;
                }
                else{
                    label[i][j] = new JLabel(new ImageIcon("pics/16.png"));
                }



                button[i][j].add(label[i][j]);
                button[i][j].setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
                button[i][j].setBackground(Color.CYAN);
                mainPanel.add(button[i][j]);
            }
        }


        frame.add(mainPanel);

        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void shuffleBoard(){
        Random rand = new Random();
        int [] array = new int[16];
        for (int i = 0; i < 16; i++){
            array[i] = i+1;
        }
        array[15] = -1;
        for (int i = 0; i < 16; i++){
            int index = rand.nextInt(16);
            int temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }

        int count = 0;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                board[i][j] = array[count];
                count = count + 1;
                System.out.print(board[i][j] + "\t");
            }
            System.out.println(" ");
        }

    }



    public void updatePanel(){

        int counter = 1;
        for (int i = 0; i< rows; i++){
            for (int j = 0; j < cols; j++){
                if (board[i][j] == counter ){
                    button[i][j].setBackground(Color.green);
                    counter += 1;
                    if (counter == 16){
                        winPanel();
                    }
                }
                else {
                    button[i][j].setBackground(Color.CYAN);
                    counter += 1;
                }

            }
        }
    }


    public void winPanel(){
        frame_1 = new JFrame();
        frame_1.setSize(500,500);
        frame_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame_1.setLocationRelativeTo(null);
        frame_1.setTitle("YEEEEEEEEEEEES!");
        JLabel lab = new JLabel();
        lab.setIcon(new ImageIcon("pics/16.png"));
        frame_1.add(lab);
        frame_1.setVisible(true);
    }



    public void swapRight(int r, int c){
        label[r][c].setIcon(new ImageIcon(""));
        String filename = "pics/" + board[r][c] + ".png";
        label[r+1][c].setIcon(new ImageIcon(filename));
        int temp = board[r][c];
        board[r][c] = board[r+1][c];
        board[r+1][c] = temp;
    }
    public void swapLeft(int r, int c){
        label[r][c].setIcon(new ImageIcon(""));
        String filename = "pics/" + board[r][c] + ".png";
        label[r-1][c].setIcon(new ImageIcon(filename));
        int temp = board[r][c];
        board[r][c] = board[r-1][c];
        board[r-1][c] = temp;
    }
    public void swapUp(int r, int c){
        label[r][c].setIcon(new ImageIcon(""));
        String filename = "pics/" + board[r][c] + ".png";
        label[r][c+1].setIcon(new ImageIcon(filename));
        int temp = board[r][c];
        board[r][c] = board[r][c+1];
        board[r][c+1] = temp;
    }
    public void swapDown(int r, int c){
        label[r][c].setIcon(new ImageIcon(""));
        String filename = "pics/" + board[r][c] + ".png";
        label[r][c-1].setIcon(new ImageIcon(filename));
        int temp = board[r][c];
        board[r][c] = board[r][c-1];
        board[r][c- 1] = temp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String ae = e.getActionCommand().toString();
        int r = Integer.parseInt(ae.split(",")[0]);
        int c = Integer.parseInt(ae.split(",")[1]);

        if (board[r][c] != -1){
            //int val = board[r][c];
            if (r+1 < rows && board[r+1][c] == -1){
                swapRight( r, c);
            }
            else if(r-1 >= 0 && board[r-1][c] == -1){
                swapLeft( r, c);
            }
            else if(c+1<cols&& board[r][c+1] == -1){
                swapUp( r, c);
            }
            else if(c-1>=0&& board[r][c-1] == -1){
                swapDown( r, c);
            }
            this.updatePanel();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        int blank_r = 0;
        int blank_c = 0;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == -1) {
                    blank_r = i;
                    blank_c = j;
                }
            }
        }
        if (!e.isControlDown() && e.getKeyCode() == KeyEvent.VK_UP && blank_r != 3){
            label[blank_r+1][blank_c].setIcon(new ImageIcon(""));
            String filename = "pics/" + board[blank_r+1][blank_c] + ".png";
            label[blank_r][blank_c].setIcon(new ImageIcon(filename));
            int temp = board[blank_r][blank_c];
            board[blank_r][blank_c] = board[blank_r+1][blank_c];
            board[blank_r+1][blank_c] = temp;
        }

        if (!e.isControlDown() && e.getKeyCode() == KeyEvent.VK_DOWN && blank_r != 0){
            label[blank_r-1][blank_c].setIcon(new ImageIcon(""));
            String filename = "pics/" + board[blank_r-1][blank_c] + ".png";
            label[blank_r][blank_c].setIcon(new ImageIcon(filename));
            int temp = board[blank_r][blank_c];
            board[blank_r][blank_c] = board[blank_r - 1][blank_c];
            board[blank_r - 1][blank_c] = temp;
        }

        if (!e.isControlDown() && e.getKeyCode() == KeyEvent.VK_LEFT && blank_c != 3){
            label[blank_r][blank_c+1].setIcon(new ImageIcon(""));
            String filename = "pics/" + board[blank_r][blank_c+1] + ".png";
            label[blank_r][blank_c].setIcon(new ImageIcon(filename));
            int temp = board[blank_r][blank_c];
            board[blank_r][blank_c] = board[blank_r][blank_c+1];
            board[blank_r][blank_c+1] = temp;
        }

        if (!e.isControlDown() && e.getKeyCode() == KeyEvent.VK_RIGHT && blank_c != 0){
            label[blank_r][blank_c-1].setIcon(new ImageIcon(""));
            String filename = "pics/" + board[blank_r][blank_c-1] + ".png";
            label[blank_r][blank_c].setIcon(new ImageIcon(filename));
            int temp = board[blank_r][blank_c];
            board[blank_r][blank_c] = board[blank_r][blank_c-1];
            board[blank_r][blank_c-1] = temp;
        }

        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_UP && blank_r != 3){
            while(blank_r != 3){
                label[blank_r+1][blank_c].setIcon(new ImageIcon(""));
                String filename = "pics/" + board[blank_r+1][blank_c] + ".png";
                label[blank_r][blank_c].setIcon(new ImageIcon(filename));
                int temp = board[blank_r][blank_c];
                board[blank_r][blank_c] = board[blank_r+1][blank_c];
                board[blank_r+1][blank_c] = temp;
                blank_r +=1;
            }
        }

        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_DOWN && blank_r != 0){
            while(blank_r != 0){
            label[blank_r-1][blank_c].setIcon(new ImageIcon(""));
            String filename = "pics/" + board[blank_r-1][blank_c] + ".png";
            label[blank_r][blank_c].setIcon(new ImageIcon(filename));
            int temp = board[blank_r][blank_c];
            board[blank_r][blank_c] = board[blank_r - 1][blank_c];
            board[blank_r - 1][blank_c] = temp;
            blank_r = blank_r - 1;
        }}

        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_LEFT && blank_c != 3){
            while(blank_c != 3){
            label[blank_r][blank_c+1].setIcon(new ImageIcon(""));
            String filename = "pics/" + board[blank_r][blank_c+1] + ".png";
            label[blank_r][blank_c].setIcon(new ImageIcon(filename));
            int temp = board[blank_r][blank_c];
            board[blank_r][blank_c] = board[blank_r][blank_c+1];
            board[blank_r][blank_c+1] = temp;
            blank_c +=1;
        }}

        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_RIGHT && blank_c != 0){
            while(blank_c != 0){
            label[blank_r][blank_c-1].setIcon(new ImageIcon(""));
            String filename = "pics/" + board[blank_r][blank_c-1] + ".png";
            label[blank_r][blank_c].setIcon(new ImageIcon(filename));
            int temp = board[blank_r][blank_c];
            board[blank_r][blank_c] = board[blank_r][blank_c-1];
            board[blank_r][blank_c-1] = temp;
            blank_c -= 1;
        }}

        this.updatePanel();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
