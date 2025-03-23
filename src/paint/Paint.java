package paint;

import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.JSlider;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Paint {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Paint window = new Paint();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Paint() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1920, 900);
		frame.setTitle("PAINT");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel herramientas = new JPanel();
		herramientas.setBorder(BorderFactory.createLineBorder(Color.MAGENTA,10));
		herramientas.setBounds(50, 50, 370, 700);
		panel.add(herramientas);
		herramientas.setLayout(null);
		
		JLabel herramienta = new JLabel("Herramientas");
		herramienta.setFont(new Font("Dialog", Font.BOLD, 18));
		herramienta.setBounds(26, 233, 126, 52);
		herramientas.add(herramienta);
		
		JButton pincel = new JButton("     Pincel");
		pincel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pincel.setIcon(new ImageIcon ("img/pincel2.png"));
				pincel.setForeground(Color.MAGENTA);
			}
		});
		pincel.setBackground(new Color(240, 240, 240));
		pincel.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240)));
		pincel.setIcon(new ImageIcon("img/pincel.png"));
		pincel.setHorizontalAlignment(JLabel.LEFT);
		pincel.setFont(new Font("Dialog", Font.PLAIN, 18));
		pincel.setBounds(26, 285, 180, 33);
		herramientas.add(pincel);
		
		JButton borrador = new JButton("     Borrador");
		borrador.setIcon(new ImageIcon("img/borrador.png"));
		borrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrador.setIcon(new ImageIcon ("img/borrador2.png"));
				borrador.setForeground(Color.MAGENTA);
			}
		});
		borrador.setHorizontalAlignment(SwingConstants.LEFT);
		borrador.setFont(new Font("Dialog", Font.PLAIN, 18));
		borrador.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240)));
		borrador.setBackground(new Color(240, 240, 240));
		borrador.setBounds(26, 328, 180, 33);
		herramientas.add(borrador);
		
		JSlider slider = new JSlider();
		slider.setBounds(118, 375, 200, 26);
		herramientas.add(slider);
		
		JLabel grosor = new JLabel("Grosor");
		grosor.setFont(new Font("Dialog", Font.PLAIN, 18));
		grosor.setBounds(36, 359, 97, 52);
		herramientas.add(grosor);
		
		JLabel figuras = new JLabel("Figuras");
		figuras.setFont(new Font("Dialog", Font.BOLD, 18));
		figuras.setBounds(26, 10, 97, 52);
		herramientas.add(figuras);
		
		JButton rectangulo = new JButton("     Rectángulo");
		rectangulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rectangulo.setIcon(new ImageIcon ("img/rectangulo2.png"));
				rectangulo.setForeground(Color.MAGENTA);
			}
		});
		rectangulo.setIcon(new ImageIcon("img/rectangulo.png"));
		rectangulo.setHorizontalAlignment(SwingConstants.LEFT);
		rectangulo.setFont(new Font("Dialog", Font.PLAIN, 18));
		rectangulo.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240)));
		rectangulo.setBackground(new Color(240, 240, 240));
		rectangulo.setBounds(26, 63, 180, 33);
		herramientas.add(rectangulo);
		
		JButton circulo = new JButton("     Círculo");
		circulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				circulo.setIcon(new ImageIcon ("img/circulo2.png"));
				circulo.setForeground(Color.MAGENTA);
			}
		});
		circulo.setIcon(new ImageIcon("img/circulo.png"));
		circulo.setHorizontalAlignment(SwingConstants.LEFT);
		circulo.setFont(new Font("Dialog", Font.PLAIN, 18));
		circulo.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240)));
		circulo.setBackground(new Color(240, 240, 240));
		circulo.setBounds(26, 106, 180, 33);
		herramientas.add(circulo);
		
		JButton tringulo = new JButton("     Triángulo");
		tringulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tringulo.setIcon(new ImageIcon ("img/triangulo2.png"));
				tringulo.setForeground(Color.MAGENTA);
			}
		});
		tringulo.setIcon(new ImageIcon("img/triangulo.png"));
		tringulo.setHorizontalAlignment(SwingConstants.LEFT);
		tringulo.setFont(new Font("Dialog", Font.PLAIN, 18));
		tringulo.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240)));
		tringulo.setBackground(new Color(240, 240, 240));
		tringulo.setBounds(26, 149, 180, 33);
		herramientas.add(tringulo);
		
		JButton linea = new JButton("     Línea");
		linea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				linea.setIcon(new ImageIcon ("img/linea2.png"));
				linea.setForeground(Color.MAGENTA);
			}
		});
		linea.setIcon(new ImageIcon("img/linea.png"));
		linea.setHorizontalAlignment(SwingConstants.LEFT);
		linea.setFont(new Font("Dialog", Font.PLAIN, 18));
		linea.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240)));
		linea.setBackground(new Color(240, 240, 240));
		linea.setBounds(26, 192, 180, 33);
		herramientas.add(linea);
		
		JLabel colores = new JLabel("Colores");
		colores.setFont(new Font("Dialog", Font.BOLD, 18));
		colores.setBounds(26, 421, 126, 52);
		herramientas.add(colores);
		
		JButton blanco = new JButton();
		blanco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				blanco.setBorder(BorderFactory.createLineBorder(Color.MAGENTA,4));
			}
		});
		blanco.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240)));
		blanco.setBackground(new Color(255, 255, 255));
		blanco.setBounds(30, 471, 33, 33);
		herramientas.add(blanco);
		
		JButton negro = new JButton();
		negro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				negro.setBorder(BorderFactory.createLineBorder(Color.MAGENTA,4));
			}
		});
		negro.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240)));
		negro.setBackground(new Color(0, 0, 0));
		negro.setBounds(73, 471, 33, 33);
		herramientas.add(negro);
		
		JButton gris = new JButton();
		gris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gris.setBorder(BorderFactory.createLineBorder(Color.MAGENTA,4));
			}
		});
		gris.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240)));
		gris.setBackground(Color.GRAY);
		gris.setBounds(118, 471, 33, 33);
		herramientas.add(gris);
		
		JButton azul = new JButton();
		azul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				azul.setBorder(BorderFactory.createLineBorder(Color.MAGENTA,4));
			}
		});
		azul.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240)));
		azul.setBackground(Color.BLUE);
		azul.setBounds(161, 471, 33, 33);
		herramientas.add(azul);
		
		JButton rojo = new JButton();
		rojo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rojo.setBorder(BorderFactory.createLineBorder(Color.MAGENTA,4));
			}
		});
		rojo.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240)));
		rojo.setBackground(Color.RED);
		rojo.setBounds(204, 471, 33, 33);
		herramientas.add(rojo);
		
		JButton verde = new JButton();
		verde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verde.setBorder(BorderFactory.createLineBorder(Color.MAGENTA,4));
			}
		});
		verde.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240)));
		verde.setBackground(Color.GREEN);
		verde.setBounds(247, 471, 33, 33);
		herramientas.add(verde);
		
		JButton limpiar = new JButton("LIMPIAR LIENZO");
		limpiar.setHorizontalAlignment(SwingConstants.CENTER);
		limpiar.setFont(new Font("Dialog", Font.BOLD, 18));
		limpiar.setBorder(BorderFactory.createLineBorder(Color.MAGENTA,3));
		limpiar.setBackground(new Color(240, 240, 240));
		limpiar.setBounds(30, 577, 308, 44);
		herramientas.add(limpiar);
		
		JButton guardar = new JButton("GUARDAR IMAGEN");
		guardar.setHorizontalAlignment(SwingConstants.CENTER);
		guardar.setFont(new Font("Dialog", Font.BOLD, 18));
		guardar.setForeground(Color.WHITE);
		guardar.setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
		guardar.setBackground(Color.MAGENTA);
		guardar.setBounds(30, 631, 308, 44);
		herramientas.add(guardar);
		
		JButton lienzo = new JButton();
		lienzo.setIcon(new ImageIcon("img/lienzo.png"));
		lienzo.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
		lienzo.setBackground(Color.WHITE);
		lienzo.setBounds(173, 525, 33, 33);
		herramientas.add(lienzo);
		
		JLabel color_lienzo = new JLabel("Color del Lienzo");
		color_lienzo.setFont(new Font("Dialog", Font.PLAIN, 18));
		color_lienzo.setBounds(26, 514, 137, 52);
		herramientas.add(color_lienzo);
		
		JButton rgb = new JButton();
		rgb.setIcon(new ImageIcon("img/rgb.png"));
		rgb.setFont(new Font("Dialog", Font.PLAIN, 18));
		rgb.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
		rgb.setBackground(Color.WHITE);
		rgb.setBounds(305, 471, 33, 33);
		herramientas.add(rgb);
		
		JPanel dibujo = new JPanel();
		dibujo.setBorder(BorderFactory.createLineBorder(Color.MAGENTA,10));
		dibujo.setLayout(null);
		dibujo.setBounds(466, 50, 1000, 700);
		panel.add(dibujo);
		
		limpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 	{
				dibujo.removeAll();
				dibujo.revalidate();
				dibujo.repaint();
				dibujo.setBackground(new Color(240, 240, 240));
			}
		});
		
		lienzo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color selectedColor = JColorChooser.showDialog(frame, "Selecciona un color", Color.BLACK);
				if (selectedColor != null) {
					dibujo.setBackground(selectedColor);
				}
				
			}
		});
		
		JLabel fondo = new JLabel();
		fondo.setIcon(new ImageIcon("img/fondo.png"));
		fondo.setBounds(0, 0, 1920, 900);
		panel.add(fondo);
	}
}
