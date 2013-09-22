package console;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

class MyPanel extends JPanel {

    private int width = 640;
    private int height = 480;
    public static boolean sellOn;

    public static void main(String[] args) {
	MyPanel p = new MyPanel();
    }

    public MyPanel() {
	PrintStream oldErr = System.err;
	PrintStream newErr = new PrintStream(new ByteArrayOutputStream());
	System.setErr(newErr);
	setBorder(BorderFactory.createLineBorder(Color.black));
	try {
	    addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent me) {
		    if (DrawGame.gridOfOrganisms[(int) me.getX()
			    * DrawGame.gridOfOrganisms.length / width][(int) me
			    .getY()
			    * DrawGame.gridOfOrganisms[0].length
			    / height] == null) {
			sellOn = true;
		    } else {
			sellOn = false;
		    }
		}
	    });
	    addMouseMotionListener(new MouseMotionAdapter() {
		public void mouseDragged(MouseEvent de) {
		    if ((DrawGame.gridOfOrganisms[(int) de.getX()
			    * DrawGame.gridOfOrganisms.length / width][(int) de
			    .getY()
			    * DrawGame.gridOfOrganisms[0].length
			    / height] == null) && (sellOn)) {
			DrawGame.gridOfOrganisms[(int) de.getX()
				* DrawGame.gridOfOrganisms.length / width][(int) de
				.getY()
				* DrawGame.gridOfOrganisms[0].length
				/ height] = new Organism();
		    } else if ((DrawGame.gridOfOrganisms[(int) de.getX()
			    * DrawGame.gridOfOrganisms.length / width][(int) de
			    .getY()
			    * DrawGame.gridOfOrganisms[0].length
			    / height] != null) && (!sellOn)) {
			DrawGame.gridOfOrganisms[(int) de.getX()
				* DrawGame.gridOfOrganisms.length / width][(int) de
				.getY()
				* DrawGame.gridOfOrganisms[0].length
				/ height] = null;
		    }
		    DrawGame.f.revalidate();
		    DrawGame.f.repaint();
		}
	    });
	} catch (Exception e) {
	    //
	}
    }

    public Dimension getPreferredSize() {
	return new Dimension(width, height);
    }

    public void paintComponent(Graphics g) {
	super.paintComponent(g);

	// Draw Text

	int rows = DrawGame.gridOfOrganisms.length;
	int cols = DrawGame.gridOfOrganisms[0].length;
	g.setColor(Color.blue);
	for (int i = 0; i < rows; i++) {
	    for (int j = 0; j < cols; j++) {
		if (DrawGame.gridOfOrganisms[i][j] != null) {
		    g.fillRect(i * width / rows, j * height / cols, width
			    / rows, height / cols);
		}
	    }
	}
	g.setColor(Color.black);
	for (int i = 1; i < rows; i++) {
	    g.drawLine(i * width / rows, 0, i * width / rows, height);
	}
	for (int j = 1; j < cols; j++) {
	    g.drawLine(0, j * height / rows, width, j * height / rows);
	}

    }

}
