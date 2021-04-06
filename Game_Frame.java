// The "Game_Frame" class.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Game_Frame extends JFrame implements KeyListener //, ActionListener
{
    //Declaring Global variables
    //Declaring all the pictures
    ImageIcon road, water, player, log, log2, log3, log4, log5, grass1, grass2, grass3, grass4, fly, fly2, car, car2, car3, car4, car5, car6;
    //player postion, frequency of the timers. Score, life and fly counnter
    int playerX = 600, playerY = 850, freq = 1000, freq2 = 200, score = 0, lives = 3, flyCount = 0, fly2Count = 0;
    //Log x values
    int x = 1030, x2 = 500, x3 = 1030, x4 = 500, x5 = 1030;
    //Car x values
    int carX = 0, carX2 = 400, carX3 = 800, carX4 = 1130, carX5 = 800, carX6 = 400;
    //Declraing Timers
    Timer logTimer, carTimer, flyTimer;
    //Stores players name
    String nam;

    public Game_Frame ()
    {
	super ("Game_Frame");   // Set the frame's name
	Toolkit dir = Toolkit.getDefaultToolkit ();
	//Assigning images to each icon
	road = new ImageIcon (dir.getImage ("RoadArt.jpg"));
	water = new ImageIcon (dir.getImage ("WaterArt3.jpg"));
	player = new ImageIcon (dir.getImage ("PlayerFrog.png"));

	//Fly Sprites
	fly = new ImageIcon (dir.getImage ("pixelFly.png"));
	fly2 = new ImageIcon (dir.getImage ("pixelFly.png"));

	//Log Sprites
	log = new ImageIcon (dir.getImage ("LogPixel.png"));
	log2 = new ImageIcon (dir.getImage ("LogPixel.png"));
	log3 = new ImageIcon (dir.getImage ("LogPixel.png"));
	log4 = new ImageIcon (dir.getImage ("LogPixel.png"));
	log5 = new ImageIcon (dir.getImage ("LogPixel.png"));

	//Grass Sprites
	grass1 = new ImageIcon (dir.getImage ("PixelGrass.jpg"));
	grass2 = new ImageIcon (dir.getImage ("PixelGrass2.png"));
	grass3 = new ImageIcon (dir.getImage ("PixelGrass2.png"));
	grass4 = new ImageIcon (dir.getImage ("PixelGrass2.png"));


	//Car Sprites
	car = new ImageIcon (dir.getImage ("carArt.png"));
	car2 = new ImageIcon (dir.getImage ("carArt.png"));
	car3 = new ImageIcon (dir.getImage ("carArt.png"));
	car4 = new ImageIcon (dir.getImage ("carArt2.png"));
	car5 = new ImageIcon (dir.getImage ("carArt2.png"));
	car6 = new ImageIcon (dir.getImage ("carArt2.png"));



	// Timer moves log across the screenn
	logTimer = new Timer (frequency (freq, score), new ActionListener ()
	{
	    public void actionPerformed (ActionEvent evt)
	    {

		//Changes the x value of the logs to move accross the screen
		x -= 50;
		x2 -= 50;
		x3 -= 50;
		x4 -= 50;
		x5 -= 75;
		//Redraws all of the icons
		repaint ();
		//Checks if the logs go past the screen
		//If logs pass the edge of screen, resets position
		if (x <= 0)
		{
		    x = 1030;
		}
		else if (x2 <= 0)
		{
		    x2 = 1030;
		}
		else if (x3 <= 0)
		{
		    x3 = 1030;
		}

		else if (x4 <= 0)
		{
		    x4 = 1030;
		}

		else if (x5 <= 0)
		{
		    x5 = 1030;
		}
	    }
	}
	);

	logTimer.start ();

	//Timer moves car accross screen
	carTimer = new Timer (frequency2 (freq2, score), new ActionListener ()
	{
	    public void actionPerformed (ActionEvent e)
	    {
		//Changes cars x value to move it across the screen
		carX += 5;
		carX2 += 5;
		carX3 += 5;

		carX4 -= 5;
		carX5 -= 5;
		carX6 -= 5;

		//Checks if the car passes the screen
		//if the car does pass the screen reset its position
		if (carX >= 1130)
		{
		    carX = 0;
		}
		else if (carX2 >= 1130)
		{
		    carX2 = 0;
		}
		else if (carX3 >= 1130)
		{
		    carX3 = 0;
		}
		else if (carX4 <= 0)
		{
		    carX4 = 1130;
		}
		else if (carX5 <= 0)
		{
		    carX5 = 1130;
		}
		else if (carX6 <= 0)
		{
		    carX6 = 1130;
		}
	    }

	}
	);
	carTimer.start ();

	//Ask the player for their name
	nam = JOptionPane.showInputDialog ("What is your name?");
	JOptionPane.showMessageDialog ("Dodge cars and logs. Swim and collect as many flys as possible");

	addKeyListener (this);

	setSize (1280, 900);     // Set the frame's size
	show ();                // Show the frame
    } // Constructor


    //-----------------------------------------------------------------------------------------
    public void paint (Graphics g)
    {
	road.paintIcon (this, g, 0, 450);
	water.paintIcon (this, g, 0, 0);

	//Painting cars
	car.paintIcon (this, g, carX, 750);
	car2.paintIcon (this, g, carX2, 750);
	car3.paintIcon (this, g, carX3, 750);
	car4.paintIcon (this, g, carX4, 600);
	car5.paintIcon (this, g, carX5, 600);
	car6.paintIcon (this, g, carX6, 600);

	//Painting grass
	grass1.paintIcon (this, g, 0, 0);
	grass2.paintIcon (this, g, 0, 50);
	grass3.paintIcon (this, g, 500, 50);
	grass4.paintIcon (this, g, 1030, 50);

	//Painting logs
	log.paintIcon (this, g, x, 500);
	log2.paintIcon (this, g, x2, 500);
	log3.paintIcon (this, g, x3, 350);
	log4.paintIcon (this, g, x4, 350);
	log5.paintIcon (this, g, x5, 200);

	//If the player has not touched a fly then paint the fly
	if (flyCount == 0)
	{
	    fly.paintIcon (this, g, 330, 150);
	}

	if (fly2Count == 0)
	{
	    fly2.paintIcon (this, g, 800, 150);
	}

	//Paint player last so it does not get covered by other images
	player.paintIcon (this, g, playerX, playerY);

	//If the player touches a fly then send player back to start and add 50 points
	if (flyCollision (playerX, playerY) == true)
	{
	    playerX = 600;
	    playerY = 850;
	    score += 50;
	    flyCount += 1;
	}

	if (flyCollision2 (playerX, playerY) == true)
	{
	    playerX = 600;
	    playerY = 850;
	    score += 50;
	    fly2Count += 1;
	}

	//Calls the collision methods
	if (logCollision (playerX, playerY, x, x2, x3, x4, x5) == true)
	{
	    //if there is collision send player back to start
	    playerX = 600;
	    playerY = 850;

	    lives -= 1;
	}

	if (carCollision (playerX, playerY, carX, carX2, carX3, carX4, carX5, carX6) == true)
	{
	    //if there is collision send player back to start
	    playerX = 600;
	    playerY = 850;
	    lives -= 1;
	}


	//if lives = 0 stop timers and print out highscore
	if (lives == 0)
	{
	    logTimer.stop ();
	    carTimer.stop ();
	    highscore (score, nam);
	}

	Font gfont = new Font ("TimesNewRoman", 1, 25);
	g.setFont (gfont);
	g.setColor (Color.white);
	g.drawString ("Lives: " + lives, 10, 75);

	g.drawString ("Score: " + score, 1130, 75);

	if (flyCount >= 1 && fly2Count >= 1)
	{
	    flyCount = 0;
	    fly2Count = 0;



	}
    } // paint method


    //-----------------------------------------------------------------------------------------

    //Player movement
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


    //-----------------------------------------------------------------------------------------

    //Checks if player collieds with the logs
    public static boolean logCollision (int playerX, int playerY, int x, int x2, int x3, int x4, int x5)
    {
	//Creates the player hit box
	Rectangle rectPlayer = new Rectangle (playerX, playerY, playerX + 10, playerY + 10);
	//Creates log hit boxs
	Rectangle rectLog = new Rectangle (x, 500, x + 250, 480);
	Rectangle rectLog2 = new Rectangle (x2, 500, x2 + 250, 480);
	Rectangle rectLog3 = new Rectangle (x3, 350, x3 + 250, 330);
	Rectangle rectLog4 = new Rectangle (x4, 350, x4 + 250, 330);
	Rectangle rectLog5 = new Rectangle (x5, 200, x5 + 250, 180);
	//Puts all rectangles, x values and y values in an array
	Rectangle rectArray[] = {rectLog, rectLog2, rectLog3, rectLog4, rectLog5};
	int posXLog[] = {x, x2, x3, x4, x5};
	int posYLog[] = {500, 500, 350, 350, 200};

	//Checks if the rectangles intersect
	for (int i = 0 ; i < 5 ; i++)
	{
	    Rectangle rectColli = rectArray [i].intersection (rectPlayer);
	    if (playerX <= posXLog [i] + 250 && playerX > posXLog [i])
		if (playerY <= posYLog [i] + 20 && playerY > posYLog [i] - 20)
		{
		    return true;
		}
	}
	return false;
    }






    //-----------------------------------------------------------------------------------------

    public static boolean carCollision (int playerX, int playerY, int carX, int carX2, int carX3, int carX4, int carX5, int carX6)
    {
	//Creates the player hit box
	Rectangle rectPlayer = new Rectangle (playerX, playerY, playerX + 10, playerY + 10);
	//Creates log hit boxs
	Rectangle rectCar = new Rectangle (carX, 750, carX + 150, 715);
	Rectangle rectCar2 = new Rectangle (carX2, 750, carX2 + 150, 715);
	Rectangle rectCar3 = new Rectangle (carX3, 750, carX3 + 150, 715);
	Rectangle rectCar4 = new Rectangle (carX4, 600, carX4 + 150, 565);
	Rectangle rectCar5 = new Rectangle (carX5, 600, carX5 + 150, 565);
	Rectangle rectCar6 = new Rectangle (carX6, 600, carX6 + 150, 565);
	//Puts all rectangles, x values and y values in an array
	Rectangle rectArray[] = {rectCar, rectCar2, rectCar3, rectCar4, rectCar5, rectCar6};
	int posXLog[] = {carX, carX2, carX3, carX4, carX5, carX6};
	int posYLog[] = {750, 750, 750, 600, 600, 600};

	//Checks if the rectangles intersect
	for (int i = 0 ; i < 6 ; i++)
	{
	    Rectangle rectColli = rectArray [i].intersection (rectPlayer);
	    if (rectColli.width > posXLog [i] && rectColli.height >= posYLog [i] - 35)
	    {
		if (rectColli.width < posXLog [i] + 150 && rectColli.height <= posYLog [i])
		{
		    return true;
		}
	    }
	}


	return false;
    }


    //-----------------------------------------------------------------------------------------

    public static boolean flyCollision (int playerX, int playerY)
    {
	//Creates the hit boxes
	Rectangle rectPlayer = new Rectangle (playerX, playerY, playerX + 5, playerY + 5);
	Rectangle rectFly = new Rectangle (330, 150, 325, 145);

	Rectangle rectColli = rectFly.intersection (rectPlayer);

	//Checks if the boxes intersects
	if (rectColli.width <= 309 && rectColli.width >= 275)
	{
	    if (rectColli.height <= 150 && rectColli.height >= 145)
	    {
		return true;
	    }

	}


	return false;
    }


    //-----------------------------------------------------------------------------------------
    public static boolean flyCollision2 (int playerX, int playerY)
    {
	//Creates the hit boxes
	Rectangle rectPlayer = new Rectangle (playerX, playerY, playerX + 5, playerY + 5);
	Rectangle rectFly = new Rectangle (800, 150, 795, 145);

	Rectangle rectColli = rectFly.intersection (rectPlayer);

	//Checks if the boxes intersects
	if (rectColli.width <= 800 && rectColli.width >= 795)
	{
	    if (rectColli.height <= 150 && rectColli.height >= 145)
	    {
		return true;
	    }

	}


	return false;
    }


    //-----------------------------------------------------------------------------------------

    public static int frequency (int freq, int score)
    {
	//The higher score the player has the lower the frequency
	freq = freq - score;
	return freq;
    }


    public static int frequency2 (int freq2, int score)
    {
	freq2 = freq2 - (score / 2);
	return freq2;
    }


    //-----------------------------------------------------------------------------------------

    public static void highscore (int score, String nam)
    {
	try
	{
	    //Creates file reader
	    FileReader file = new FileReader ("highscore.txt");
	    BufferedReader input = new BufferedReader (file);

	    //Creates the arrays
	    String names[];
	    int allScores[];

	    names = new String [10];
	    allScores = new int [10];

	    //Reads from file and stores it in an array
	    for (int i = 0 ; i < names.length ; i++)
	    {
		names [i] = input.readLine ();
		allScores [i] = Integer.parseInt (input.readLine ());
	    }

	    //Sorts the names and score
	    for (int k = 0 ; k < names.length ; k++)
	    {
		for (int j = 0 ; j < names.length - 1 ; j++)
		{

		    if (allScores [j] > allScores [j + 1])
		    {
			int tempScore = allScores [j];
			allScores [j] = allScores [j + 1];
			allScores [j + 1] = tempScore;
			String tempName = names [j];
			names [j] = names [j + 1];
			names [j + 1] = tempName;
		    }
		}
	    }


	    //Checks if the player's score is in the top ten
	    for (int v = names.length - 1 ; v >= 0 ; v--)
	    {
		if (score > allScores [v])
		{
		    allScores [v] = score;
		    names [v] = nam;
		    v = -1;
		}

	    }

	    //Closes file
	    input.close ();

	    //Creates file writer
	    FileWriter outFile = new FileWriter ("highscore.txt");
	    PrintWriter output = new PrintWriter (outFile);

	    System.out.println ("Highscore");

	    //Writes scores to file and prints out highscore
	    for (int z = names.length - 1 ; z >= 0 ; z--)
	    {
		System.out.println (names [z] + " , " + allScores [z]);
		output.println (names [z]);
		output.println (allScores [z]);
	    }

	    output.close ();
	    outFile.close ();

	    //Searches to see if players are in the top ten
	    int count = 0;
	    for (int c = 0 ; c < names.length ; c++)
	    {
		if (nam.equals (names [c]))
		{
		    System.out.println ("\n" + "You are ranked: " + (names.length - c) + "\n" + "Good Job!");
		    c = names.length;
		    count = 1;
		}
	    }

	    if (count == 0)
	    {
		System.out.println ("\n" + "Better luck next time");
	    }

	}


	catch (Exception e)
	{
	}




    }


    //-----------------------------------------------------------------------------------------
    public void keyReleased (KeyEvent e)
    {

    }


    public void keyTyped (KeyEvent e)
    {

    }


    //-----------------------------------------------------------------------------------------

    public static void main (String[] args)
    {
	new Game_Frame ();      // Create a Game_Frame frame
    } // main method
} // Game_Frame class


