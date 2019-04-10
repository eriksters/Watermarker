package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Component;

import javax.swing.border.EtchedBorder;

import control.FileController;

import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Canvas;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.UIManager;
import javax.swing.SpinnerNumberModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtSource;
	private JTextField txtDest;
	private JButton btnProcess;
	private JList<File> list;
	private JButton btnSource;

	FileController fc;
	private JLabel lblWatermark;
	private JTextField txtWMark;
	private Canvas canvas;
	private JButton btnWatermark;
	private JLabel lblSize;
	private JSpinner spinnerSize;
	private JLabel lblTransparency;
	private JSlider sliderTrans;
	private JSpinner spinnerTrans;
	private JSlider sliderX;
	private JSlider sliderY;
	private JSlider sliderSize;
	private JSpinner spinnerX;
	private JSpinner spinnerY;
	private JScrollPane scrollPane;
	private JTextArea txtInstructions;
	private JLabel lblStatus;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setSize(1300, 500);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("Watermarker. Milu tevi, Annuci :*");
		fc = new FileController();
		
		initComponents();
		addEvents();
	}
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 918, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setForeground(Color.WHITE);
		
		lblStatus = new JLabel("Status");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, 892, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblStatus))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 460, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
					.addComponent(lblStatus))
		);
		
		list = new JList<>(new DefaultListModel<File>());
		list.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		list.setBackground(SystemColor.menu);
		
		txtSource = new JTextField();
		txtSource.setColumns(10);
		
		btnProcess = new JButton("Process");

		
		JLabel lblSource = new JLabel("Source:");
		
		JLabel lblDestination = new JLabel("Destination:");
		
		txtDest = new JTextField();
		txtDest.setColumns(10);
		
		btnSource = new JButton("Update source");
		btnSource.setBackground(Color.GREEN);
		
		lblWatermark = new JLabel("Watermark:");
		
		txtWMark = new JTextField();
		txtWMark.setColumns(10);
		
		btnWatermark = new JButton("Update source");

		btnWatermark.setBackground(Color.GREEN);
		
		sliderX = new JSlider();
		sliderX.setMaximum(100);
		sliderX.setMinimum(-20);

		
		JLabel lblXOffset = new JLabel("x offset:");
		
		JLabel lblYOffset = new JLabel("y offset:");
		
		sliderY = new JSlider();
		sliderY.setMaximum(100);
		sliderY.setMinimum(-20);
		
		spinnerX = new JSpinner();
		
		spinnerX.setModel(new SpinnerNumberModel(new Float(0), new Float(-20), new Float(100), new Float(1)));
		
		spinnerY = new JSpinner();
		spinnerY.setModel(new SpinnerNumberModel(new Float(0), new Float(-20), new Float(100), new Float(1)));
		
		lblSize = new JLabel("Size:");
		
		sliderSize = new JSlider();
		sliderSize.setMaximum(100);
		sliderSize.setMinimum(0);
		
		spinnerSize = new JSpinner();
		spinnerSize.setModel(new SpinnerNumberModel(new Float(0), new Float(0), new Float(100), new Float(1)));
		
		lblTransparency = new JLabel("Transparency:");
		
		sliderTrans = new JSlider();
		sliderTrans.setMaximum(100);
		sliderTrans.setMinimum(0);
		
		spinnerTrans = new JSpinner();
		spinnerTrans.setModel(new SpinnerNumberModel(new Float(0), new Float(0), new Float(100), new Float(1)));
		
		scrollPane = new JScrollPane();
		

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblWatermark, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
								.addComponent(lblDestination, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblSource, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(txtSource, GroupLayout.DEFAULT_SIZE, 6, Short.MAX_VALUE)
										.addComponent(txtWMark, GroupLayout.DEFAULT_SIZE, 6, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnSource, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnWatermark, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
								.addComponent(txtDest, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)))
						.addComponent(list, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSize)
								.addComponent(lblYOffset, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblXOffset)
								.addComponent(lblTransparency))
							.addGap(33)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(sliderX, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
								.addComponent(sliderY, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
								.addComponent(sliderSize, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
								.addComponent(sliderTrans, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE))
							.addGap(10)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(spinnerX, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addComponent(spinnerTrans, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addComponent(spinnerSize, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addComponent(spinnerY, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
							.addGap(52))
						.addComponent(btnProcess, GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE))
					.addGap(0))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSource)
								.addComponent(txtSource, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(9)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblWatermark)
								.addComponent(txtWMark, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnWatermark))
							.addGap(9)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDestination)
								.addComponent(txtDest, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(btnSource)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(5)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblXOffset)
								.addComponent(sliderX, GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)
								.addComponent(spinnerX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
									.addGap(18)
									.addComponent(lblYOffset))
								.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
									.addGap(11)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
											.addComponent(spinnerY, GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED))
										.addComponent(sliderY, 0, 0, Short.MAX_VALUE))))
							.addGap(9)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblSize)
								.addComponent(sliderSize, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(spinnerSize, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(list, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
							.addComponent(lblTransparency))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(spinnerTrans, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(sliderTrans, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnProcess)))
					.addContainerGap())
		);
		
		txtInstructions = new JTextArea();
		scrollPane.setViewportView(txtInstructions);
		
		canvas = new ImageCanvas();
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		setDefaults();
	}
	
	private void addEvents() {
		btnProcess.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				process();
			}
		});
		
		btnSource.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateSource();
			}
		});
		
		btnWatermark.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				updateWatermark();
			}
		});
		
		sliderX.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				spinnerX.setValue(new Float(sliderX.getValue()));
			}
		});
		
		sliderY.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				spinnerY.setValue(new Float(sliderY.getValue()));
			}
		});
		
		sliderSize.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				spinnerSize.setValue(new Float(sliderSize.getValue()));
			}
		});
		
		sliderTrans.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				spinnerTrans.setValue(new Float(sliderTrans.getValue()));
			}
		});
		
		spinnerX.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				sliderX.setValue((int) (float) spinnerX.getValue());
			}
		});
		
		spinnerY.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				sliderY.setValue((int) (float) spinnerY.getValue());
			}
		});
		
		spinnerSize.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				sliderSize.setValue((int) (float) spinnerSize.getValue());
			}
		});
		
		spinnerX.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				sliderX.setValue((int) (float) spinnerX.getValue());
			}
		});
		
		
		
		
//		btnSample.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent arg0) {
//				try {
//					updateCanvas();
//				} catch (IOException e) {
//					JOptionPane.showMessageDialog(null, "Some shit went horribly wrong. Oh no!!!");
//					e.printStackTrace();
//				}
//			}
//		});
	}
	
	private void updateWatermark() {
		String wmSource = txtWMark.getText();
		try {
			fc.setWatermark(wmSource);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	private void updateSource() {
		String source = txtSource.getText();
		System.out.println("Source: " + source);
		try {
			fc.setDirectory(source);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		
		int count = fc.addImagesFromDirectory(fc.getDirectory());
		
		JOptionPane.showMessageDialog(null, "Found " + count + " images");
		
		DefaultListModel<File> lm = (DefaultListModel<File>) list.getModel();
		lm.removeAllElements();
		
		ArrayList<File> images = fc.getImages();
		
		for (File f : images) {
			lm.addElement(f);
		}
	}
	
	private void process() {
		
		String dest = txtDest.getText();
		System.out.println("Destination: " + dest);
		try {
			fc.setDestination(dest);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
		
		try {
			float xOffset = (float) spinnerX.getValue();
			float yOffset = (float) spinnerY.getValue();
			float sizeRatio = (float) spinnerSize.getValue();
			float transparency = (float) spinnerTrans.getValue();

			
			
			fc.processAll(xOffset, yOffset, sizeRatio, transparency);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	//Test case ------------------
	private void setDefaults() {
		txtSource.setText("C:\\Users\\eriks\\Dropbox\\Home\\Kristaps_website\\Bildes");
		txtWMark.setText("C:\\Users\\eriks\\Dropbox\\Home\\Kristaps_website\\Bildes\\wm.png");
		txtDest.setText("C:\\Users\\eriks\\Dropbox\\Home\\Kristaps_website\\Bildes\\testing");
		
		sliderX.setValue(0);
		sliderY.setValue(0);
		sliderSize.setValue(10);
		sliderTrans.setValue(0);
		
		txtInstructions.setText(
				  "1. Ievieto folderi, kurā atrodas tavas editojamās bildes iekš source. Piemēram:\n"
				+ "C:\\Users\\Anna\\Desktop\\bildes\n"
				+ "2. Uzspied pogu, kas atrodas blakus source logam. Atrastās bildes uzrādīsies sarakstā.\n"
				+ "3. Ievieto ūdenszīmi iekš Watermark. Piemēram:\n"
				+ "C:\\Users\\Anna\\Desktop\\watermark.png\n"
				+ "4. Uzspied pogu, kas ir blakus Watermark logam. Man ir slinkums rakties pa kodu un \npievienot ziņas, tāpēc, ja nekādi errori nav, tad jau gan jau viss ok.\n"
				+ "5. Ievieto folderi, kurā rakstīt jaunās bildes iekš output. Piemēram: \n"
				+ "C:\\Users\\Anna\\Desktop\\Output\n"
				+ "6. Uzspied Process un ceri uz to labāko\n"
				+ "*Iesaku pamēģināt ar vienu bildi vispirms, lai zinātu, kāda konfigurācija vislabākā. \n(Prieview shall be added later!)\n"
				+ "7. LOVE ME FOREVER AND EVER!!!"
		);
		
	}
	
	class ImageCanvas extends Canvas{
		
		@Override
		public void paint(Graphics g) {
			Toolkit t = Toolkit.getDefaultToolkit();
			Image i = t.getImage("C:\\Users\\eriks\\Dropbox\\Home\\Kristaps_website\\Bildes\\minion.jpg");
			
			
			
			g.drawImage(i, 0, 0, 100, 100, this);
		}
	}
}
