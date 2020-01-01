package infinitemonkey;

//JavaFX Imports
import java.awt.*;
import java.io.PrintStream;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Main{

	static DNA_Pool dna_pool;

	public static void main(String[] args) {
		JFrame startFrame = new JFrame();

		// String Entry
		JButton submitBtn = new JButton("Submit");
		JTextField strField = new JTextField();

		// Menu Bar
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		JMenuItem importFileItem = new JMenuItem("Import File");

		// Layouts
		JPanel bottomLayout = new JPanel(new BorderLayout());
		JPanel topLayout = new JPanel(new BorderLayout());

		// Layout Padding
		topLayout.setBorder(new EmptyBorder(10, 10, 5, 10));
		bottomLayout.setBorder(new EmptyBorder(5, 10, 10, 10));

		// Text area to display algorithmic details
		JTextArea details = new JTextArea(1000, 10);
		PrintStream ps = new PrintStream(new CstmOutStrm(details));
		System.setOut(ps);

		details.setFont(new Font(Font.DIALOG, Font.PLAIN, 10));

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.add(details);

		details.setEditable(false);
		topLayout.add(scrollPane, BorderLayout.CENTER);

		// Text field and submit button for entering strings
		strField.setPreferredSize(new Dimension(500, 20));

		submitBtn.addActionListener(e -> {
			dna_pool = new DNA_Pool(100, strField.getText());
			dna_pool.loopUntilTargetMet();
		});

		bottomLayout.add(strField, BorderLayout.WEST);
		bottomLayout.add(submitBtn, BorderLayout.EAST);

		// Initialising main frame
		startFrame.setTitle("Infinite Monkeys");
		startFrame.setSize(800, 700);
		startFrame.setResizable(false);
		startFrame.setLayout(new BorderLayout());
		startFrame.setDefaultCloseOperation(startFrame.EXIT_ON_CLOSE);

		// Setting up menu bar
		exitMenuItem.addActionListener(e -> System.exit(0));
		fileMenu.add(importFileItem);
		fileMenu.add(new JSeparator(SwingConstants.HORIZONTAL));
		fileMenu.add(exitMenuItem);
		menuBar.add(fileMenu);

		// Adding menu bar and layout to main frame
		startFrame.setJMenuBar(menuBar);
		startFrame.add(topLayout, BorderLayout.CENTER);
		startFrame.add(bottomLayout, BorderLayout.SOUTH);

		startFrame.setVisible(true);

	}
}
