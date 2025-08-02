import java.awt.*;
import java.awt.event.*;
class FDemo extends Frame implements ActionListener
{
	Button b1[] =new Button[9];
	FDemo()
	{
		setTitle("GAME");
		Font f =new Font("Brush Script MT",Font.BOLD,30);
		setFont(f);
		setLayout(null);
		int i,j,k=0;
		int x=100,y=100;
		for(i=1;i<=3;i++)
		{
			for(j=1;j<=3;j++)
			{
				b1[k]=new Button();
				b1[k].setSize(100,100);
				b1[k].setLocation(x,y);
				add(b1[k]);
				b1[k].addActionListener(this);
				x+=100;
				k++;
			}
			y+=100;
			x=100;
		}
		// 👇 Add this to handle window close
		addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e) 
			{
            System.exit(0);
			}
		});
	}
	int c=1;
	public void actionPerformed(ActionEvent e)
	{
		Button b=(Button)e.getSource();
		if(c%2==1)
		{
			b.setLabel("0");
			b.setBackground(Color.RED);
			c=0;
		}
		else
		{
			b.setLabel("X");
			b.setBackground(Color.GREEN);
			c=1;
		}
		b.removeActionListener(this);
		checkWinner(); // 👈 Add this line
	}
	public void paint(Graphics g)
	{
		g.drawString("Developed By :-Divyansh Jayswal",65,450);
		g.drawString("SOFTWAVES TECHNOLOGIES",45,65);
	}
	void checkWinner() 
	{
		String[][] grid = new String[3][3];
		int k = 0;
		// Fill grid with button labels
		for (int i = 0; i < 3; i++) 
		{
			for (int j = 0; j < 3; j++) 
			{
				grid[i][j] = b1[k].getLabel();
				k++;
			}
		}
		// Check rows, columns, and diagonals
		for (int i = 0; i < 3; i++) 
		{
			// Rows
			if (!grid[i][0].equals("") && grid[i][0].equals(grid[i][1]) && grid[i][1].equals(grid[i][2])) 
			{
				showWinner(grid[i][0]);
				return;
			}
			// Columns
			if (!grid[0][i].equals("") && grid[0][i].equals(grid[1][i]) && grid[1][i].equals(grid[2][i])) 
			{
				showWinner(grid[0][i]);
				return;
			}
		}
		// Diagonals
		if (!grid[0][0].equals("") && grid[0][0].equals(grid[1][1]) && grid[1][1].equals(grid[2][2])) 
		{
			showWinner(grid[0][0]);
			return;
		}
		if (!grid[0][2].equals("") && grid[0][2].equals(grid[1][1]) && grid[1][1].equals(grid[2][0])) 
		{
			showWinner(grid[0][2]);
			return;
		}
	}
	void showWinner(String winner) 
	{
		Dialog d = new Dialog(this, "Game Over", true);
		d.setLayout(new FlowLayout());
		d.setSize(200, 100);
		d.add(new Label("Winner is: " + winner));
		Button ok = new Button("OK");
		ok.addActionListener(e -> {
			d.setVisible(false);
			resetGame(); // 👈 Reset the game
        });
		d.add(ok);
		d.setLocationRelativeTo(this);
		d.setVisible(true);
	}
	void resetGame() 
	{
		c = 1; // Reset turn counter
		for (int i = 0; i < 9; i++) 
		{
			b1[i].setLabel("");
			b1[i].setBackground(null);
			b1[i].addActionListener(this); // Re-enable button
		}
	}
}
class tictacgame
{
   public static void main (String ar[])
   {
		FDemo f=new FDemo();
		f.setVisible(true);
		f.setSize(500,500);
		f.setLocation(300,200);
		f.setBackground(Color.CYAN);
   }
}		