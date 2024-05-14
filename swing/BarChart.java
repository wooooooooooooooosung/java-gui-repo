import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Bar Chart<br>
 * 
 * Date : 2024-05-14
 * 
 * @see 
 * @version 1.0.0
 * @author Woosung Jo
 * @category swing
 * 
 * Timeout Limit : n ms
 * Memory Limit  : n MB
 */
public class BarChart extends JFrame {
	
	ChartPanel chartPanel = new ChartPanel();
	JPanel panel = new JPanel();
	
	JTextField[] input = new JTextField[5];
	JButton button = new JButton("입력");
	
	public static void main(String[] args) {
		new BarChart();
	}

	public BarChart() {
		this.setTitle("Bar Chart");
		this.setSize(600, 400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	 	this.add(chartPanel, BorderLayout.CENTER);
	 	this.add(panel, BorderLayout.SOUTH);
	 	
	 	for (int i = 0; i < input.length; i++) {
	 		panel.add(new JLabel(String.format("%d번 값", i + 1), JLabel.CENTER));
	 		panel.add(input[i] = new JTextField(3));
	 		input[i].setText("100");
		}
	 	panel.add(button);
	 	
	 	// button click action
	 	button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					chartPanel.setValue(
							new int[] { 
								Integer.parseInt(input[0].getText()), 
								Integer.parseInt(input[1].getText()), 
								Integer.parseInt(input[2].getText()), 
								Integer.parseInt(input[3].getText()), 
								Integer.parseInt(input[4].getText()) 
							});
					// call paint()
					chartPanel.repaint();
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
	 	button.doClick();
	 	this.setVisible(true);
	}

}

class ChartPanel extends JPanel {
	
	int[] val = { 0, 0, 0, 0, 0 };
	float max = 0;
	
	final int HEIGHT   = 280;
	final int WIDTH    = 30;
	final int DISTANCE = 110;
	
	final Color[] CHART_COLOR = { Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE };
	
	
	// input value
	public void setValue(int[] val) {
		this.val = val;
		max = Math.max(val[0], Math.max(val[1], Math.max(val[2], Math.max(val[3], val[4]))));
	}
	
	// draw chart
	// repaint()
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 600, 400);
		float point = HEIGHT / max;
		
		for (int i = 0; i < 5; i++) {
			g.setColor(CHART_COLOR[i]);
			g.fillRect(50 + i * DISTANCE, HEIGHT + 20 - (int) (val[i] * point), WIDTH, (int) (val[i] * point));
			g.setColor(Color.BLACK);
			g.drawRect(50 + i * DISTANCE, HEIGHT + 20 - (int) (val[i] * point), WIDTH, (int) (val[i] * point));
		}
	}
	
}
