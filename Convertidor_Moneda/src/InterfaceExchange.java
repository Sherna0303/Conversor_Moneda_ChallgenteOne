import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.Cursor;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class InterfaceExchange extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Variables
	private JPanel contentPane;
	private JTextField txt_Moneda;
	private String moneda = "Pesos Colombianos";
	private int xMouse, yMouse;
	static int status = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceExchange frame = new InterfaceExchange();
					frame.setUndecorated(true);
					frame.setLocationRelativeTo(null);
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
	public InterfaceExchange() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Boton de salida
		JPanel btnExit = new JPanel();
		btnExit.setBackground(Color.WHITE);
		btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExit.setBounds(460, 0, 40, 40);
		contentPane.add(btnExit);
		
		JLabel x = new JLabel("X");
		x.setBackground(Color.WHITE);
		x.setHorizontalAlignment(SwingConstants.CENTER);
		x.setHorizontalTextPosition(SwingConstants.CENTER);
		btnExit.add(x);
		x.setFont(new Font("Roboto", Font.PLAIN, 26));
		
		// Cabecera
		JPanel header = new JPanel();
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 500, 40);
		contentPane.add(header);
		
		// Movimiento de la aplicación
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xMouse = e.getX();
				yMouse = e.getY();
			}
		});
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x-xMouse, y-yMouse);
			}
		});
		
		// Cerrar la aplicación
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnExit.setBackground(Color.red);
				x.setForeground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnExit.setBackground(Color.white);
				x.setForeground(Color.black);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		
		// Titulo
		JLabel txtValor = new JLabel("Ingresar Dolares");
		txtValor.setFont(new Font("Roboto", Font.BOLD, 26));
		txtValor.setBounds(67, 50, 350, 40);
		txtValor.setFocusable(getFocusableWindowState());
		contentPane.add(txtValor);
		
		// Separador del input
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		separator.setBounds(70, 138, 240, 10);
		contentPane.add(separator);
		
		// Input Valor
		txt_Moneda = new JTextField();
		txt_Moneda.setHorizontalAlignment(SwingConstants.LEFT);
		txt_Moneda.setForeground(new Color(169, 169, 169));
		txt_Moneda.setFont(new Font("Roboto", Font.PLAIN, 16));
		txt_Moneda.setText("Ingrese un valor...");
		txt_Moneda.setBounds(70, 110, 240, 26);
		txt_Moneda.setColumns(1);
		txt_Moneda.setBorder(null);
		contentPane.add(txt_Moneda);
		
		// Evento Click
		txt_Moneda.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (txt_Moneda.getText().equals("Ingrese un valor...")) {
					txt_Moneda.setText("");
					txt_Moneda.setForeground(new Color(0, 0, 0));
				}
			}
		});
		
		// Evento al perder el Foco (Efecto PlaceHolder)
		txt_Moneda.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (txt_Moneda.getText().isEmpty()) {
					txt_Moneda.setText("Ingrese un valor...");
					txt_Moneda.setForeground(new Color(169, 169, 169));
				}
			}
		});
		
		// Evento para validar si un valor ingresado es valido
		txt_Moneda.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if (!txt_Moneda.getText().matches("[0-9]+([,]+[0-9]+)*")) {
					txt_Moneda.setForeground(Color.red);
					separator.setForeground(Color.RED);
					separator.setBackground(Color.RED);
				} else {
					txt_Moneda.setForeground(Color.black);
					separator.setForeground(Color.BLACK);
					separator.setBackground(Color.BLACK);
				}
			}
		});
		
		// Boton de Convertir
		JButton btnConvertir = new JButton("Convertir");
		btnConvertir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConvertir.setForeground(Color.WHITE);
		btnConvertir.setBackground(new Color(51, 255, 102));
		btnConvertir.setFont(new Font("Roboto", Font.BOLD, 15));
		btnConvertir.setBounds(100, 220, 170, 50);
		btnConvertir.setBorder(null);
		btnConvertir.setFocusPainted(false);
		contentPane.add(btnConvertir);
		
		// Texto al cual se cambiara el valor ingresado
		JLabel Cambio = new JLabel("Cambio a Pesos Colombianos");
		Cambio.setVerticalAlignment(SwingConstants.CENTER);
		Cambio.setHorizontalAlignment(SwingConstants.CENTER);
		Cambio.setForeground(Color.WHITE);
		Cambio.setFont(new Font("Roboto", Font.BOLD | Font.ITALIC, 20));
		Cambio.setBounds(102, 355, 280, 20);
		contentPane.add(Cambio);
	
		// Selector de monedas
		JComboBox<String> seleccionMoneda = new JComboBox<String>();
		seleccionMoneda.setBackground(Color.WHITE);
		seleccionMoneda.setFont(new Font("Roboto", Font.PLAIN, 15));
		seleccionMoneda.setBounds(80, 160, 210, 30);
		contentPane.add(seleccionMoneda);
		seleccionMoneda.setRequestFocusEnabled(false);
		seleccionMoneda.addItem("Dolar");
		seleccionMoneda.addItem("Euro");
		seleccionMoneda.addItem("Libra Esterlina");
		seleccionMoneda.addItem("Yen Japones");
		seleccionMoneda.addItem("Won sul-coreano");
		seleccionMoneda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Validar Cambios en la interfaz
				if (status == 0) {
					txtValor.setText("Ingresar " + textValor(seleccionMoneda.getSelectedIndex()));
					moneda = "Pesos Colombianos";
				} else {
					moneda = textValor(seleccionMoneda.getSelectedIndex());
					Cambio.setText("Cambio a " + textValor(seleccionMoneda.getSelectedIndex()));
				}
			}
		});
		
		// Boton para cambiar el cambio de moneda
		JButton cambioConversion = new JButton("");
		cambioConversion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cambioConversion.setBounds(360, 130, 60, 60);
		cambioConversion.setBorder(null);
		cambioConversion.setIcon(icono("/img/icono_change.png", 60, 60));
		cambioConversion.setBackground(Color.WHITE);
		cambioConversion.setFocusPainted(false);
		contentPane.add(cambioConversion);
		
		// Animacion al clikear el boton de cambio de moneda
		cambioConversion.addMouseListener(new  MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				cambioConversion.setIcon(icono("/img/icono_change.png", 60, 60));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				cambioConversion.setIcon(icono("/img/icono_change.png", 50, 50));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cambioConversion.setIcon(icono("/img/icono_change.png", 60, 60));
			}
		});
		
		// Evento para cambio visual al cambiar el tipo de moneda
		cambioConversion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (status == 0) {
					txtValor.setText("Ingresar Pesos Colombianos");
					moneda = textValor(seleccionMoneda.getSelectedIndex());
					Cambio.setText("Cambio a " + textValor(seleccionMoneda.getSelectedIndex()));
					status = 1;
				} else {
					txtValor.setText("Ingresar " + textValor(seleccionMoneda.getSelectedIndex()));
					moneda = "Pesos Colombianos";
					Cambio.setText("Cambio a " + moneda);
					status = 0;
				}
				
			}
		});

		// Panel decorativo de color verde
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 255, 102));
		panel_1.setBounds(0, 330, 500, 80);
		contentPane.add(panel_1);
		
		// BackGround
		JPanel bg = new JPanel();
		bg.setBorder(null);
		bg.setBackground(Color.WHITE);
		bg.setBounds(0, 0, 500, 400);
		contentPane.add(bg);
		
		// Modificando visualmente el JOptionPane
		UIManager.put("OptionPane.background", Color.white);
		UIManager.put("OptionPane.messageFont", new Font("Roboto", Font.PLAIN, 14));
		UIManager.put("OptionPane.messageForeground", Color.black);
		UIManager.put("OptionPane.messageButton", btnConvertir);
		UIManager.put("Panel.background", Color.white);
		
		// Boton para el JOptionPane
		JButton btnOptionPane = new JButton("Aceptar");
		btnOptionPane.setBackground(new Color(51, 255, 102));
		btnOptionPane.setForeground(Color.white);
		btnOptionPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOptionPane.setFocusPainted(false);
		btnOptionPane.setBorderPainted(false);
		btnOptionPane.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.getRootFrame().dispose();
			}
		});
		
		// Array de botones
		JButton[] buttons = { btnOptionPane };
		
		//Evento al convertir los datos
		btnConvertir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Validar si los datos son ingresables a la función
				if (txt_Moneda.getText().isEmpty() || !txt_Moneda.getText().matches("[0-9]+([,]+[0-9]+)*")) {
					JOptionPane.showOptionDialog(null, "Ingresa una cantidad valida", "Información Ingresada Erronea", JOptionPane.INFORMATION_MESSAGE, JOptionPane.PLAIN_MESSAGE, icono("/img/alerta.png", 36, 36), buttons, buttons[0]);
				} else {
					// Tomar los datos del Input
					String valorIngresado = txt_Moneda.getText();
					
					// Pasar las comas ingresadas por puntos
					valorIngresado = valorIngresado.replace(",", ".");
					
					// Convertimos el String a Double
					double number = Double.parseDouble(valorIngresado);
					
					// Creamos la instancia de la clase Conversor
					Conversor conversor = new Conversor();
					
					// Ingresamos el Double con el valor ingresado por el usuario
					conversor.setMoneda_1(number);
					
					// Mostramos un Joption con el valor ingresado convertido a la moneda seleccionada
					JOptionPane.showOptionDialog(null, new JLabel(conversor.tipo_Moneda(seleccionMoneda.getSelectedIndex(), status) + " " + moneda, JLabel.LEADING), 
							"Resultado de la Conversión", JOptionPane.INFORMATION_MESSAGE, JOptionPane.PLAIN_MESSAGE, icono("/img/dinero.png", 40, 40), buttons, buttons[0]);
				}
			}
		});
	}
	
	// Validar la opcion selecionada para mostrar en pantalla
	private String textValor(int codigo) {
		//Selector de moneda
			String moneda;
				switch (codigo)
				{
					case 0: {
						//Dolares
						moneda = "Dolares";
						break;
					} 
					case 1: {		
						//Euros
						moneda = "Euros";
						break;
					}
					case 2: {
						//Libras Esterlinas
						moneda = "Libras Esterlinas";
						break;
					}
					case 3: {
						//Yen Japones
						moneda = "Yenes Japoneses";
						break;
					}
					case 4: {
						//Won sul-Coreano
						moneda = "Wones Sul-Coreanos";
						break;
					}	
						default:
							throw new IllegalArgumentException("Unexpected value: " + codigo);
				}
			return moneda;	
	}
	
	// Funcion para crear un icono al tamaño deseado
	private Icon icono(String ruta, int width, int height) {
		Icon icon = new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
		return icon;
	}
}
