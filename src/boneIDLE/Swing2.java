package boneIDLE;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.Charset;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import bonereader.UIHandler;

/*
 * The GUI design and handling for boneIDLE
 * Very inefficient. Should probably have split some of the sections into other classes.
 */
public class Swing2 implements ActionListener {
	
	private JTextArea code;
	private JTextArea vars;
	private final JFileChooser fc = new JFileChooser();
	private JFrame frame;
	private JTextField repIn;
	
	public void HighlightSyntax()
	{
		
	}
	
	private void increaseFont()
	{
		Font font = code.getFont();
		float size = font.getSize() + 5;
		code.setFont(font.deriveFont(size));
		vars.setFont(font.deriveFont(size));
	}
	
	private void decreaseFont()
	{
		Font font = code.getFont();
		float size = font.getSize() - 5;
		code.setFont(font.deriveFont(size));
		vars.setFont(font.deriveFont(size));
	}
//	
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * A rather clustered function that handles all possible GUI inputs
	 */
	public void actionPerformed(ActionEvent e)
	{
		//Respond to button presses.
		//Makes use of the bonereader interpreter.
		//System.out.println(e);
		
		if(e.getActionCommand().equals("compile"))
		{
			bonereader.Controller.clear();
			code.getHighlighter().removeAllHighlights();
			//if(code.getText() != null) bonereader.Reader.readBigFile(code.getText());
			if(code.getText() != null) TextSender.sendText(code.getText());
		}
		else if(e.getActionCommand().equals("run"))
		{
			bonereader.Controller.fullRun();
			code.getHighlighter().removeAllHighlights();
		}
		else if(e.getActionCommand().equals("next"))
		{
			bonereader.Controller.next();
			//this.HighlightSection();
		}
		else if(e.getActionCommand().equals("load"))
		{
			//This just opens up the file chooser window
			int returnval = fc.showOpenDialog(this.frame);
			if(returnval == JFileChooser.APPROVE_OPTION)
			{
				//The user has picked something
				//Argh, this is nested horribly
				code.setText(FileHandler.ReadTest(fc.getSelectedFile(), Charset.defaultCharset()));
			}
			//I don't really care if the user cancels
		}
		else if(e.getActionCommand().equals("save"))
		{
			int returnval = fc.showSaveDialog(this.frame);
			if(returnval == JFileChooser.APPROVE_OPTION)
				FileHandler.WriteTest(code.getText(), fc.getSelectedFile());
		}
		else if(e.getActionCommand().equals("evaluate"))
		{
			//DISCLAIMER: This no longer works. Instruction have to be compiled before they are run.
			//bonereader.Controller.handleSimpleInstruction(repIn.getText());
			//Do this properly somewhere
		}
		else if(e.getActionCommand().equals("clear"))
		{
			repIn.setText("");
		}
		else if(e.getActionCommand().equals("increaseFont"))
		{
			increaseFont();
		}
		else if(e.getActionCommand().equals("decreaseFont"))
		{
			decreaseFont();
		}
		
		vars.setText(bonereader.Variables.GetAllVars());
		
	}
	
	//Create and display the GUI
	//This is very clustered. I'm sorry.
	public void BuildAndShow()
	{
		frame = new JFrame("BoneIDLE");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel pane = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel lblCode = new JLabel("Code:");
		c.gridx = 0;
		c.gridy = 0;
		pane.add(lblCode, c);
		
		JLabel lblVars = new JLabel("Variables");
		c.gridx = 3;
		pane.add(lblVars, c);
		
		code = new JTextArea();
		code.setColumns(20);
		code.setRows(20);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 5;
		
		UIHandler.setCodeText(code);
		
		JScrollPane scroll1 = new JScrollPane(code);
		scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		pane.add(scroll1, c);
		
		vars = new JTextArea();
		vars.setColumns(20);
		vars.setRows(20);
		vars.setEditable(false);
		vars.setForeground(Color.BLACK);
		
		JScrollPane scroll2 = new JScrollPane(vars);
		scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		c.gridx = 3;
		pane.add(scroll2, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		
		JButton b = new JButton("Compile");
		b.addActionListener(this);
		b.setActionCommand("compile");
		c.weighty = 0.2;
		c.gridx = 2;
		c.gridy = 2;
		c.gridheight = 1;
		pane.add(b, c);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(this);
		btnNext.setActionCommand("next");
		c.gridx = 2;
		c.gridy = 3;
		pane.add(btnNext, c);
		
		JButton btnRun = new JButton("Run");
		btnRun.addActionListener(this);
		btnRun.setActionCommand("run");
		c.gridx = 2;
		c.gridy = 4;
		pane.add(btnRun, c);
		
		//These are the REPL components.
//		JLabel lblRepl = new JLabel("Evaluate Single Commands");
//		c.gridx = 0;
//		c.gridy = 6;
//		pane.add(lblRepl, c);
//		
//		//This should respond on the enter key.
//		repIn = new JTextField();
//		repIn.addActionListener(this);
//		repIn.setActionCommand("evaluate");
//		c.gridx = 0;
//		c.gridy = 7;
//		c.gridwidth = 3;
//		pane.add(repIn, c);
//		
//		JButton btnEval = new JButton("Evaluate");
//		btnEval.addActionListener(this);
//		btnEval.setActionCommand("evaluate");
//		c.gridx = 0;
//		c.gridy = 8;
//		c.weightx = 1;
//		c.weighty = 0;
//		c.gridwidth = 1;
//		c.gridheight = 1;
//		pane.add(btnEval, c);
//		
//		JButton btnClear = new JButton("Clear");
//		btnClear.addActionListener(this);
//		btnClear.setActionCommand("clear");
//		c.gridx = 1;
//		c.gridwidth = 2;
//		c.weightx = 1;
//		pane.add(btnClear, c);
		
		JButton btnBig = new JButton("Increase Font Size");
		btnBig.addActionListener(this);
		btnBig.setActionCommand("increaseFont");
		c.gridx = 3;
		c.gridy = 7;
		pane.add(btnBig, c);
		
		JButton btnSmall = new JButton("Decrease Font Size");
		btnSmall.addActionListener(this);
		btnSmall.setActionCommand("decreaseFont");
		c.gridy = 8;
		pane.add(btnSmall, c);
		
		//Now the top menu.
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		menuBar.add(menu);
		
		//The items of that menu.
		JMenuItem saveItem = new JMenuItem("Save...");
		saveItem.addActionListener(this);
		saveItem.setActionCommand("save");
		menu.add(saveItem);
		
		JMenuItem loadItem = new JMenuItem("Load...");
		loadItem.addActionListener(this);
		loadItem.setActionCommand("load");
		menu.add(loadItem);
		
		frame.setJMenuBar(menuBar);
		
		//frame.getContentPane().add(pane, BorderLayout.CENTER);
		frame.setContentPane(pane);
		
		frame.pack();
		frame.setVisible(true);
		
	}

}
