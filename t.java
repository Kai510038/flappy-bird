import javax.swing.*;
import java.awt.*;import java.awt.event.*;
public class t{
	public static void main(String[]args){
		ButtonDemo myButtonGUI=new ButtonDemo();
		myButtonGUI.setVisible(true);
	}
}
class ButtonDemo extends JFrame implements ActionListener{
	public int wins=0, losses=0, draws=0;  // game count by user
	public int rows[][]={{0,2},{3,5},{6,8},{0,6},{1,7},{2,8},{0,8},{2,6}};
	static final char BLANK=' ', O='O', X='X';
	public char pos[]={  // Board pos (BLANK, O, or X)
    BLANK, BLANK, BLANK,
    BLANK, BLANK, BLANK,
    BLANK, BLANK, BLANK};
	
	public static final int Width=500;
	public static final int Height=500;
	
	Button button[]=new Button[10];
	ButtonDemo(){
		setSize(Width,Height); 
		Container p=getContentPane();
		p.setBackground(Color.BLUE);
		p.setLayout(new GridLayout(3,3));
		
		for (int i = 1; i <= 9; i++)
		{
			button[i] = new Button(Integer.toString(i-1));
			button[i].setBackground(Color.white);
			button[i].setForeground(Color.white);
			button[i].setSize(50,50);
			button[i].setFont(new Font("Arial", Font.PLAIN, 40));
			button[i].addActionListener(this);
			p.add(button[i]);
		}
	}
	
	static int j=0;
	public void actionPerformed(ActionEvent e){
		String s=e.getActionCommand();
	//	System.out.println(s);
		if(s!="X"&&s!="O"){
			int n=Integer.valueOf(s);
			j++;
			Button button = (Button) e.getSource();
			if(j%2==0){
				button.setLabel("X");
				button.setForeground(Color.black);
				pos[n]=X;
			}
			if(j%2==1){
				button.setLabel("O");
				button.setForeground(Color.black);
				pos[n]=O;
			}	
			putX();  
		}
	}	
	
	public void putX() {
      // Check if game is over
      if (won(O))
        newGame(O);
      else if (won(X))
        newGame(X);
      else if (filled())
	    newGame(BLANK);
    }
	
	// Return true if player has won
    boolean won(char player) {
      for (int i=0; i<8; ++i)
        if (testRow(player, rows[i][0], rows[i][1]))
          return true;
      return false;
    }
	
	 // Has player won in the row from pos[a] to pos[b]?
    boolean testRow(char player, int a, int b) {
      return pos[a]==player && pos[b]==player 
          && pos[(a+b)/2]==player;
    }
	
	// Are all 9 spots filled?
    boolean filled() {
      for (int i=0; i<9; ++i)
        if (pos[i]==BLANK)
          return false;
      return true;
    }
	
	// Start a new game
    void newGame(char winner) {
      // Announce result of last game.  Ask user to play again.
      String letter; // x or o;
      if (winner==O) {
        ++wins;
        letter = "O Wins!";
      }
      else if (winner==X) {
        ++losses;
        letter = "X Wins!";
      }
      else {
        letter = "Tie";
        ++draws;
      }
      if (JOptionPane.showConfirmDialog(null, 
		  letter+
          "\nO Wins: "+wins+ " , X Wins: "+losses+" , "+draws+" draws\n"
          +"Play again?", letter, JOptionPane.YES_NO_OPTION)
          !=JOptionPane.YES_OPTION) {
        System.exit(0);
      }

      // Clear the board to start a new game
      for (int j=0; j<9; ++j)
        pos[j]=BLANK;
	
	  for (int i = 1; i <= 9; i++){
		button[i].setLabel(Integer.toString(i-1)); 
		button[i].setForeground(Color.white);
	  }
    }
}