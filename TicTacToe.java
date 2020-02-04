import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class TicTacToe extends JFrame {
	private Container pane;
	private String turn;
	private JButton[][] board;
	private boolean hasWinner;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem quit;
	private JMenuItem twoPlayer;
	private JMenuItem singlePlayer;
	private boolean twoPlayerGame;
	private Random rand;

	public TicTacToe() {
		super();
		setTitle("Tic Tac Toe");
		pane = getContentPane();
		pane.setLayout(new GridLayout(3,3));
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		turn = "X";
		board = new JButton[3][3];
		hasWinner = false;
		twoPlayerGame = false;
		rand = new Random();
		setup();
	}
	
	private void setup() {
		for(int i=0; i<3;i++) {
			for(int j=0;j<3;j++){
				JButton btn = new JButton();
				btn.setFont(new Font(Font.SANS_SERIF,Font.BOLD,100));
				board[i][j] = btn;
				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(((JButton) e.getSource()).getText().equals("") && hasWinner == false){
					btn.setText(turn);
					checkWinner();
					
					//dont switch turns if game is over (cpu generated turns will be upset)
					if(hasWinner == false) {
						switchTurn();
					}
				}
					}
				});
				pane.add(btn);
			}
		}
		menuBar = new JMenuBar();
		menu = new JMenu("Game");
		
		singlePlayer = new JMenuItem("Single Player Game");
		singlePlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				twoPlayerGame = false;
				reset();
			}
		});
		
		twoPlayer = new JMenuItem("Two Player Game");
		twoPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				twoPlayerGame = true;
				reset();
			}
		});
		
		quit = new JMenuItem("Quit");
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		menu.add(singlePlayer);
		menu.add(twoPlayer);
		menu.add(quit);
		
		menuBar.add(menu);
		
		setJMenuBar(menuBar);
	
	}
	
	private void reset() {
		turn = "X";
		hasWinner = false;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				board[i][j].setText("");
			}
		}
	}
	
	/**
	 * change the turn from X to O (O to X) (two player)
	 * cpu generated move (single player)
	 */
	private void switchTurn() {
		if(twoPlayerGame) {
			if(turn.equals("X")) {
				turn = "O";
			}
			else {
				turn = "X";
			}
		}//if
		else {
			/*
			 * This will be the cpu generated turn in a single player game.
			 * Will play a random spot on the board.
			 * If selected random spot is not available, recall this method.
			 */
			turn = "O";
			int i = rand.nextInt(3);
			int j = rand.nextInt(3);
			if(board[i][j].getText().equals("")) {
				board[i][j].setText("O");
				checkWinner();
				turn = "X";
			}
			else {
				switchTurn();
			}
			
		}//else
	}
	
	/**
	 * check for a winner
	 * xxx --- --- x-- -x- --x x-- --x
	 * --- xxx --- x-- -x- --x -x- -x-
	 * --- --- xxx x-- -x- --x --x x--
	 */
	private void checkWinner() {
		if(board[0][0].getText().equals(turn) && board[0][1].getText().equals(turn) && board[0][2].getText().equals(turn)) {
			JOptionPane.showMessageDialog(null, "Player " + turn + " Wins!!!");
			hasWinner = true;
		}
		else if(board[1][0].getText().equals(turn) && board[1][1].getText().equals(turn) && board[1][2].getText().equals(turn)) {
			JOptionPane.showMessageDialog(null, "Player " + turn + " Wins!!!");
			hasWinner = true;
		}
		else if(board[2][0].getText().equals(turn) && board[2][1].getText().equals(turn) && board[2][2].getText().equals(turn)) {
			JOptionPane.showMessageDialog(null, "Player " + turn + " Wins!!!");
			hasWinner = true;
		}
		else if(board[0][0].getText().equals(turn) && board[1][0].getText().equals(turn) && board[2][0].getText().equals(turn)) {
			JOptionPane.showMessageDialog(null, "Player " + turn + " Wins!!!");
			hasWinner = true;
		}
		else if(board[0][1].getText().equals(turn) && board[1][1].getText().equals(turn) && board[2][1].getText().equals(turn)) {
			JOptionPane.showMessageDialog(null, "Player " + turn + " Wins!!!");
			hasWinner = true;
		}
		else if(board[0][2].getText().equals(turn) && board[1][2].getText().equals(turn) && board[2][2].getText().equals(turn)) {
			JOptionPane.showMessageDialog(null, "Player " + turn + " Wins!!!");
			hasWinner = true;
		}
		else if(board[0][0].getText().equals(turn) && board[1][1].getText().equals(turn) && board[2][2].getText().equals(turn)) {
			JOptionPane.showMessageDialog(null, "Player " + turn + " Wins!!!");
			hasWinner = true;
		}
		else if(board[0][2].getText().equals(turn) && board[1][1].getText().equals(turn) && board[2][0].getText().equals(turn)) {
			JOptionPane.showMessageDialog(null, "Player " + turn + " Wins!!!");
			hasWinner = true;
		}
	}


}
