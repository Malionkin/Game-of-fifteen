import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener {

    BoardGUI gui;
    public MyKeyListener(BoardGUI gui){
        this.gui = gui;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int blank_r = 0;
        int blank_c = 0;
        for (int i = 0; i < gui.rows; i++){
            for (int j = 0; j < gui.cols; j++) {
                if (gui.board[i][j] == -1) {
                    blank_r = i;
                    blank_c = j;
                }
            }
        }
        if (!e.isControlDown() && e.getKeyCode() == KeyEvent.VK_UP && blank_r != 3){
            gui.logic.swapControlUp(blank_r, blank_c);
        }

        if (!e.isControlDown() && e.getKeyCode() == KeyEvent.VK_DOWN && blank_r != 0){
            gui.logic.swapControlDown( blank_r,  blank_c);
        }

        if (!e.isControlDown() && e.getKeyCode() == KeyEvent.VK_LEFT && blank_c != 3){
            gui.logic.swapControlLeft( blank_r,  blank_c);
        }

        if (!e.isControlDown() && e.getKeyCode() == KeyEvent.VK_RIGHT && blank_c != 0){
            gui.logic.swapControlRight( blank_r,  blank_c);
        }

        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_UP && blank_r != 3){
            while(blank_r != 3){
                gui.logic.swapControlUp( blank_r,  blank_c);
                blank_r +=1;
            }
        }

        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_DOWN && blank_r != 0){
            while(blank_r != 0){
                gui.logic.swapControlDown( blank_r,  blank_c);
                blank_r = blank_r - 1;
            }}

        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_LEFT && blank_c != 3){
            while(blank_c != 3){
                gui.logic.swapControlLeft( blank_r,  blank_c);
                blank_c +=1;
            }}

        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_RIGHT && blank_c != 0){
            while(blank_c != 0){
                gui.logic.swapControlRight( blank_r,  blank_c);
                blank_c -= 1;
            }}

        gui.updatePanel();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
