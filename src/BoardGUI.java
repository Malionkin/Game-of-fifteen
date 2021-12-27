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
    Logic logic;

    public BoardGUI(){

        rows = 4;
        cols = 4;
        board = new int[rows][cols];
        initGUI();
        logic = new Logic(this);

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





    @Override
    public void actionPerformed(ActionEvent e) {
        String ae = e.getActionCommand().toString();
        int r = Integer.parseInt(ae.split(",")[0]);
        int c = Integer.parseInt(ae.split(",")[1]);

        if (board[r][c] != -1){
            //int val = board[r][c];
            if (r+1 < rows && board[r+1][c] == -1){
                logic.swapRight( r, c);
            }
            else if(r-1 >= 0 && board[r-1][c] == -1){
                logic.swapLeft( r, c);
            }
            else if(c+1<cols&& board[r][c+1] == -1){
                logic.swapUp( r, c);
            }
            else if(c-1>=0&& board[r][c-1] == -1){
                logic.swapDown( r, c);
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
            logic.swapNotControlUp( blank_r,  blank_c);
        }

        if (!e.isControlDown() && e.getKeyCode() == KeyEvent.VK_DOWN && blank_r != 0){
            logic.swapNotControlDown( blank_r,  blank_c);
        }

        if (!e.isControlDown() && e.getKeyCode() == KeyEvent.VK_LEFT && blank_c != 3){
            logic.swapNotControlLeft( blank_r,  blank_c);
        }

        if (!e.isControlDown() && e.getKeyCode() == KeyEvent.VK_RIGHT && blank_c != 0){
            logic.swapNotControlRight( blank_r,  blank_c);
        }

        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_UP && blank_r != 3){
            while(blank_r != 3){
                logic.swapControlUp( blank_r,  blank_c);
                blank_r +=1;
            }
        }

        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_DOWN && blank_r != 0){
            while(blank_r != 0){
                logic.swapControlDown( blank_r,  blank_c);
                blank_r = blank_r - 1;
        }}

        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_LEFT && blank_c != 3){
            while(blank_c != 3){
                logic.swapControlLeft( blank_r,  blank_c);
                blank_c +=1;
        }}

        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_RIGHT && blank_c != 0){
            while(blank_c != 0){
                logic.swapControlRight( blank_r,  blank_c);
                blank_c -= 1;
        }}

        this.updatePanel();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
