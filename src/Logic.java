import javax.swing.*;

public class Logic {
    BoardGUI gui;
    public Logic(BoardGUI gui){
        this.gui = gui;
    }

    public void swapRight(int r, int c){
        gui.label[r][c].setIcon(new ImageIcon(""));
        String filename = "pics/" + gui.board[r][c] + ".png";
        gui.label[r+1][c].setIcon(new ImageIcon(filename));
        int temp = gui.board[r][c];
        gui.board[r][c] = gui.board[r+1][c];
        gui.board[r+1][c] = temp;
    }
    public void swapLeft(int r, int c){
        gui.label[r][c].setIcon(new ImageIcon(""));
        String filename = "pics/" + gui.board[r][c] + ".png";
        gui.label[r-1][c].setIcon(new ImageIcon(filename));
        int temp = gui.board[r][c];
        gui.board[r][c] = gui.board[r-1][c];
        gui.board[r-1][c] = temp;
    }
    public void swapUp(int r, int c){
        gui.label[r][c].setIcon(new ImageIcon(""));
        String filename = "pics/" + gui.board[r][c] + ".png";
        gui.label[r][c+1].setIcon(new ImageIcon(filename));
        int temp = gui.board[r][c];
        gui.board[r][c] = gui.board[r][c+1];
        gui.board[r][c+1] = temp;
    }
    public void swapDown(int r, int c){
        gui.label[r][c].setIcon(new ImageIcon(""));
        String filename = "pics/" + gui.board[r][c] + ".png";
        gui.label[r][c-1].setIcon(new ImageIcon(filename));
        int temp = gui.board[r][c];
        gui.board[r][c] = gui.board[r][c-1];
        gui.board[r][c- 1] = temp;
    }

    public void swapNotControlUp(int blank_r, int blank_c){
        gui.label[blank_r+1][blank_c].setIcon(new ImageIcon(""));
        String filename = "pics/" + gui.board[blank_r+1][blank_c] + ".png";
        gui.label[blank_r][blank_c].setIcon(new ImageIcon(filename));
        int temp = gui.board[blank_r][blank_c];
        gui.board[blank_r][blank_c] = gui.board[blank_r+1][blank_c];
        gui.board[blank_r+1][blank_c] = temp;
    }
    public void swapNotControlDown(int blank_r, int blank_c){
        gui.label[blank_r-1][blank_c].setIcon(new ImageIcon(""));
        String filename = "pics/" + gui.board[blank_r-1][blank_c] + ".png";
        gui.label[blank_r][blank_c].setIcon(new ImageIcon(filename));
        int temp = gui.board[blank_r][blank_c];
        gui.board[blank_r][blank_c] = gui.board[blank_r - 1][blank_c];
        gui.board[blank_r - 1][blank_c] = temp;
    }
    public void swapNotControlLeft(int blank_r, int blank_c){
        gui.label[blank_r][blank_c+1].setIcon(new ImageIcon(""));
        String filename = "pics/" + gui.board[blank_r][blank_c+1] + ".png";
        gui.label[blank_r][blank_c].setIcon(new ImageIcon(filename));
        int temp = gui.board[blank_r][blank_c];
        gui.board[blank_r][blank_c] = gui.board[blank_r][blank_c+1];
        gui.board[blank_r][blank_c+1] = temp;
    }
    public void swapNotControlRight(int blank_r, int blank_c){
        gui.label[blank_r][blank_c-1].setIcon(new ImageIcon(""));
        String filename = "pics/" + gui.board[blank_r][blank_c-1] + ".png";
        gui.label[blank_r][blank_c].setIcon(new ImageIcon(filename));
        int temp = gui.board[blank_r][blank_c];
        gui.board[blank_r][blank_c] = gui.board[blank_r][blank_c-1];
        gui.board[blank_r][blank_c-1] = temp;
    }

    public void swapControlUp(int blank_r, int blank_c){
        gui.label[blank_r+1][blank_c].setIcon(new ImageIcon(""));
        String filename = "pics/" + gui.board[blank_r+1][blank_c] + ".png";
        gui.label[blank_r][blank_c].setIcon(new ImageIcon(filename));
        int temp = gui.board[blank_r][blank_c];
        gui.board[blank_r][blank_c] = gui.board[blank_r+1][blank_c];
        gui.board[blank_r+1][blank_c] = temp;

    }

    public void swapControlDown(int blank_r, int blank_c){
        gui.label[blank_r-1][blank_c].setIcon(new ImageIcon(""));
        String filename = "pics/" + gui.board[blank_r-1][blank_c] + ".png";
        gui.label[blank_r][blank_c].setIcon(new ImageIcon(filename));
        int temp =gui. board[blank_r][blank_c];
        gui.board[blank_r][blank_c] = gui.board[blank_r - 1][blank_c];
        gui.board[blank_r - 1][blank_c] = temp;

    }

    public void swapControlLeft(int blank_r, int blank_c){
        gui.label[blank_r][blank_c+1].setIcon(new ImageIcon(""));
        String filename = "pics/" + gui.board[blank_r][blank_c+1] + ".png";
        gui.label[blank_r][blank_c].setIcon(new ImageIcon(filename));
        int temp = gui.board[blank_r][blank_c];
        gui.board[blank_r][blank_c] = gui.board[blank_r][blank_c+1];
        gui.board[blank_r][blank_c+1] = temp;

    }

    public void swapControlRight(int blank_r, int blank_c){
        gui.label[blank_r][blank_c-1].setIcon(new ImageIcon(""));
        String filename = "pics/" + gui.board[blank_r][blank_c-1] + ".png";
        gui.label[blank_r][blank_c].setIcon(new ImageIcon(filename));
        int temp = gui.board[blank_r][blank_c];
        gui.board[blank_r][blank_c] = gui.board[blank_r][blank_c-1];
        gui.board[blank_r][blank_c-1] = temp;

    }

}
