package console;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawGame {
    public static ConsoleGrid grid = new ConsoleGrid(40, 40);
    public static Organism[][] gridOfOrganisms = grid.getGrid();
    public static JFrame f;
    static Container c;
    private static boolean isPaused = true;
    private static JPanel room = new JPanel();
    private static JPanel mainMenu = new JPanel();
    
    public static void randomizeGrid() {
	for (int i = 0; i < gridOfOrganisms.length; i++) {
	    for (int j = 0; j < gridOfOrganisms[0].length; j++) {
		if ((int) (Math.random() * 2) == 0) {
		    gridOfOrganisms[i][j] = new Organism();
		}
	    }
	}
    }
    
    public static void clearGrid() {
	for (int i = 0; i < gridOfOrganisms.length; i++) {
	    for (int j = 0; j < gridOfOrganisms[0].length; j++) {
		gridOfOrganisms[i][j] = null;
	    }
	}
    }

    public static void go() {
	if (!isPaused) {
	    grid.act();
	    f.revalidate();
	    f.repaint();
	}
	try {
	    Thread.sleep(150);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	go();

    }

    public static void main(String[] args) throws InterruptedException {
	grid.setGrid(gridOfOrganisms);
	createAndShowGUI();
	go();

    }

    public static void createAndShowGUI() {
	f = new JFrame("Conway's Game of Life");
	f.setLayout(new FlowLayout());
	c = f.getContentPane();
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	room.setLayout(new FlowLayout());
	room.add(new MyPanel());
	JPanel subMenu = new JPanel();
	subMenu.setLayout(new GridLayout(2, 4));
	mainMenu.setSize(room.getPreferredSize());
	mainMenu.setLayout(new BoxLayout(mainMenu, BoxLayout.PAGE_AXIS));

	JButton toGrid = new JButton("Play now!");
	final JCheckBox b0 = new JCheckBox("Born: 0");
	final JCheckBox b1 = new JCheckBox("Born: 1");
	final JCheckBox b2 = new JCheckBox("Born: 2");
	final JCheckBox b3 = new JCheckBox("Born: 3");
	final JCheckBox b4 = new JCheckBox("Born: 4");
	final JCheckBox b5 = new JCheckBox("Born: 5");
	final JCheckBox b6 = new JCheckBox("Born: 6");
	final JCheckBox b7 = new JCheckBox("Born: 7");
	final JCheckBox b8 = new JCheckBox("Born: 8");
	final JCheckBox s0 = new JCheckBox("Survives: 0");
	final JCheckBox s1 = new JCheckBox("Survives: 1");
	final JCheckBox s2 = new JCheckBox("Survives: 2");
	final JCheckBox s3 = new JCheckBox("Survives: 3");
	final JCheckBox s4 = new JCheckBox("Survives: 4");
	final JCheckBox s5 = new JCheckBox("Survives: 5");
	final JCheckBox s6 = new JCheckBox("Survives: 6");
	final JCheckBox s7 = new JCheckBox("Survives: 7");
	final JCheckBox s8 = new JCheckBox("Survives: 8");
	
	//Default settings
	b3.doClick(); s2.doClick(); s3.doClick();
	
	subMenu.add(b0);
	subMenu.add(b1);
	subMenu.add(b2);
	subMenu.add(b3);
	subMenu.add(b4);
	subMenu.add(b5);
	subMenu.add(b6);
	subMenu.add(b7);
	subMenu.add(b8);
	subMenu.add(s0);
	subMenu.add(s1);
	subMenu.add(s2);
	subMenu.add(s3);
	subMenu.add(s4);
	subMenu.add(s5);
	subMenu.add(s6);
	subMenu.add(s7);
	subMenu.add(s8);
	mainMenu.add(subMenu);
	mainMenu.add(toGrid);

	toGrid.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		Organism.numToBeBorn[0] = b0.isSelected();
		Organism.numToBeBorn[1] = b1.isSelected();
		Organism.numToBeBorn[2] = b2.isSelected();
		Organism.numToBeBorn[3] = b3.isSelected();
		Organism.numToBeBorn[4] = b4.isSelected();
		Organism.numToBeBorn[5] = b5.isSelected();
		Organism.numToBeBorn[6] = b6.isSelected();
		Organism.numToBeBorn[7] = b7.isSelected();
		Organism.numToBeBorn[8] = b8.isSelected();
		Organism.numsToSurvive[0] = s0.isSelected();
		Organism.numsToSurvive[1] = s1.isSelected();
		Organism.numsToSurvive[2] = s2.isSelected();
		Organism.numsToSurvive[3] = s3.isSelected();
		Organism.numsToSurvive[4] = s4.isSelected();
		Organism.numsToSurvive[5] = s5.isSelected();
		Organism.numsToSurvive[6] = s6.isSelected();
		Organism.numsToSurvive[7] = s7.isSelected();
		Organism.numsToSurvive[8] = s8.isSelected();
		f.remove(mainMenu);
		f.revalidate();
		f.repaint();
		f.add(room);
		f.setSize(800, 550);
		f.revalidate();
		f.repaint();
	    }
	});

	JPanel infoPanel = new JPanel();
	infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
	JButton pause = new JButton("Play/Stop");
	infoPanel.add(pause);
	pause.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		if (isPaused) {
		    isPaused = false;
		} else {
		    isPaused = true;
		}
	    }
	});

	JButton randomize = new JButton("Randomize");
	infoPanel.add(randomize);
	randomize.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		isPaused = false;
		randomizeGrid();
		f.revalidate();
		f.repaint();
	    }
	});
	
	JButton clear = new JButton("Clear");
	infoPanel.add(clear);
	clear.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		isPaused = false;
		clearGrid();
		f.revalidate();
		f.repaint();
		
	    }
	});

	JButton menu = new JButton("Back to Menu");
	infoPanel.add(menu);
	menu.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		isPaused = true;
		f.remove(room);
		f.revalidate();
		f.repaint();
		f.add(mainMenu);
		f.setSize(820, 130);
		f.revalidate();
		f.repaint();
	    }
	});

	room.add(infoPanel);
	f.add(mainMenu);
	f.pack();
	f.setLocationRelativeTo(null);
	f.setVisible(true);
    }
}