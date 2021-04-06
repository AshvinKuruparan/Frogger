// The "StealthGame" class.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
public class StealthGame extends JFrame implements KeyListener
{

    int playerX, playerY;

    public StealthGame ()
    {
	super ("StealthGame");  // Set the frame's name
	setSize (400, 400);     // Set the frame's size
	show ();                // Show the frame
    } // Constructor


    public void paint (Graphics g)
    {
	g.drawRect (playerX, playerY, playerX + 50, playerY + 50);

    } // paint method


    public void keyPressed (KeyEvent e)
    {
	//Checks which key is pressed
	if (e.getKeyCode () == KeyEvent.VK_LEFT)
	{
	    playerX -= 50;
	}
	else if (e.getKeyCode () == KeyEvent.VK_RIGHT)
	{
	    playerX += 50;
	}
	else if (e.getKeyCode () == KeyEvent.VK_UP)
	{
	    playerY -= 50;
	}
	else if (e.getKeyCode () == KeyEvent.VK_DOWN)
	{
	    playerY += 50;
	}
	this.repaint ();
    }


    public static void main (String[] args)
    {
	new StealthGame ();     // Create a StealthGame frame
    } // main method
} // StealthGame class
