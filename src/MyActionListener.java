import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyActionListener implements ActionListener {
    BoardGUI gui;
    public MyActionListener(BoardGUI gui){
        this.gui = gui;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String ae = e.getActionCommand().toString();
        int r = Integer.parseInt(ae.split(",")[0]);
        int c = Integer.parseInt(ae.split(",")[1]);

        if (gui.board[r][c] != -1){
            //int val = board[r][c];
            if (r+1 < gui.rows && gui.board[r+1][c] == -1){
                gui.logic.swapRight( r, c);
            }
            else if(r-1 >= 0 && gui.board[r-1][c] == -1){
                gui.logic.swapLeft( r, c);
            }
            else if(c+1<gui.cols&& gui.board[r][c+1] == -1){
                gui.logic.swapUp( r, c);
            }
            else if(c-1>=0&& gui.board[r][c-1] == -1){
                gui.logic.swapDown( r, c);
            }
            gui.updatePanel();
        }
    }
}
