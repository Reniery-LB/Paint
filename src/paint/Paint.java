package paint;

import java.awt.EventQueue;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JSlider;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class Paint implements MouseListener, MouseMotionListener {

    private JFrame frame;
    private DrawingPanel drawingPanel;
    private Point lastPoint;
    
    private Color colorActual = Color.BLACK;
    private int grosorTrazo = 3;
    
    private List<Trazo> listaDeTrazos = new ArrayList<>();
    private List<Point> puntosActuales = new ArrayList<>();
    private List<Figura> listaDeFiguras = new ArrayList<>();
    
    // 1: pincel, 2: rectángulo, 3: círculo, 4: triángulo
    private int herramientaActual = 1;

    class Trazo {
        List<Point> puntos;
        Color color;
        int grosor;
        
        public Trazo(List<Point> puntos, Color color, int grosor) {
            this.puntos = new ArrayList<>(puntos);
            this.color = color;
            this.grosor = grosor;
        }
    }

    class Figura {
        Color color;
        int grosor;
        int tipo; // 1: rectángulo, 2: círculo, 3: triángulo
        Point posicion;
        int ancho = 100; // Tamaño predeterminado
        int alto = 100;  // Tamaño predeterminado
        
        public Figura(Color color, int grosor, int tipo, Point posicion) {
            this.color = color;
            this.grosor = grosor;
            this.tipo = tipo;
            this.posicion = posicion;
        }
    }

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
                herramientaActual = 1;
                pincel.setIcon(new ImageIcon("img/pincel2.png"));
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
                borrador.setIcon(new ImageIcon("img/borrador2.png"));
                borrador.setForeground(Color.MAGENTA);
                colorActual = drawingPanel.getBackground();
            }
        });
        borrador.setHorizontalAlignment(SwingConstants.LEFT);
        borrador.setFont(new Font("Dialog", Font.PLAIN, 18));
        borrador.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240)));
        borrador.setBackground(new Color(240, 240, 240));
        borrador.setBounds(26, 328, 180, 33);
        herramientas.add(borrador);
        
        JSlider slider = new JSlider();
        slider.setMinimum(1);
        slider.setMaximum(50);
        slider.setValue(grosorTrazo);
        slider.setBounds(118, 375, 200, 26);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                grosorTrazo = slider.getValue();
            }
        });
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
                herramientaActual = 2;
                rectangulo.setIcon(new ImageIcon("img/rectangulo2.png"));
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
                herramientaActual = 3;
                circulo.setIcon(new ImageIcon("img/circulo2.png"));
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
                herramientaActual = 4;
                tringulo.setIcon(new ImageIcon("img/triangulo2.png"));
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
                linea.setIcon(new ImageIcon("img/linea2.png"));
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
                colorActual = Color.WHITE;
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
                colorActual = Color.BLACK;
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
                colorActual = Color.GRAY;
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
                colorActual = Color.BLUE;
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
                colorActual = Color.RED;
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
                colorActual = Color.GREEN;
                verde.setBorder(BorderFactory.createLineBorder(Color.MAGENTA,4));
            }
        });
        verde.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240)));
        verde.setBackground(Color.GREEN);
        verde.setBounds(247, 471, 33, 33);
        herramientas.add(verde);
        
        JButton limpiar = new JButton("LIMPIAR LIENZO");
        limpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listaDeTrazos.clear();
                listaDeFiguras.clear();
                puntosActuales.clear();
                drawingPanel.repaint();
                drawingPanel.setBackground(Color.WHITE);
            }
        });
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
        lienzo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color selectedColor = JColorChooser.showDialog(frame, "Selecciona un color", drawingPanel.getBackground());
                if (selectedColor != null) {
                    drawingPanel.setBackground(selectedColor);
                }
            }
        });
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
        rgb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color selectedColor = JColorChooser.showDialog(frame, "Selecciona un color", colorActual);
                if (selectedColor != null) {
                    colorActual = selectedColor;
                    rgb.setBorder(BorderFactory.createLineBorder(Color.MAGENTA,4));
                }
            }
        });
        rgb.setIcon(new ImageIcon("img/rgb.png"));
        rgb.setFont(new Font("Dialog", Font.PLAIN, 18));
        rgb.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        rgb.setBackground(Color.WHITE);
        rgb.setBounds(305, 471, 33, 33);
        herramientas.add(rgb);
        
        drawingPanel = new DrawingPanel();
        drawingPanel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA,10));
        drawingPanel.setBounds(466, 50, 1000, 700);
        panel.add(drawingPanel);
        
        drawingPanel.addMouseListener(this);
        drawingPanel.addMouseMotionListener(this);
        
        JLabel fondo = new JLabel();
        fondo.setIcon(new ImageIcon("img/fondo.png"));
        fondo.setBounds(0, 0, 1920, 900);
        panel.add(fondo);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(herramientaActual == 2) { // Rectángulo
            listaDeFiguras.add(new Figura(colorActual, grosorTrazo, 1, e.getPoint()));
        } 
        else if(herramientaActual == 3) { // Círculo
            listaDeFiguras.add(new Figura(colorActual, grosorTrazo, 2, e.getPoint()));
        }
        else if(herramientaActual == 4) { // Triángulo
            listaDeFiguras.add(new Figura(colorActual, grosorTrazo, 3, e.getPoint()));
        }
        drawingPanel.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(herramientaActual == 1) { // Solo para pincel
            lastPoint = e.getPoint();
            puntosActuales.add(lastPoint);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(herramientaActual == 1 && !puntosActuales.isEmpty()) {
            listaDeTrazos.add(new Trazo(puntosActuales, colorActual, grosorTrazo));
            puntosActuales = new ArrayList<>();
            drawingPanel.repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        if(herramientaActual == 1) { // Solo para pincel
            Point newPoint = e.getPoint();
            puntosActuales.add(newPoint);
            lastPoint = newPoint;
            drawingPanel.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    class DrawingPanel extends JPanel {
        public DrawingPanel() {
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            
            // Dibujar trazos (pincel)
            for (Trazo trazo : listaDeTrazos) {
                g2d.setColor(trazo.color);
                g2d.setStroke(new BasicStroke(trazo.grosor));
                
                if (trazo.puntos.size() > 1) {
                    for (int i = 1; i < trazo.puntos.size(); i++) {
                        Point p1 = trazo.puntos.get(i - 1);
                        Point p2 = trazo.puntos.get(i);
                        g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
                    }
                }
            }
            
            // Dibujar figuras
            for (Figura figura : listaDeFiguras) {
                g2d.setColor(figura.color);
                g2d.setStroke(new BasicStroke(figura.grosor));
                
                switch(figura.tipo) {
                    case 1: // Rectángulo
                        g2d.drawRect(figura.posicion.x, figura.posicion.y, figura.ancho, figura.alto);
                        break;
                    case 2: // Círculo
                        g2d.drawOval(figura.posicion.x, figura.posicion.y, figura.ancho, figura.alto);
                        break;
                    case 3: // Triángulo
                        int[] xPoints = {
                            figura.posicion.x + figura.ancho/2, 
                            figura.posicion.x, 
                            figura.posicion.x + figura.ancho
                        };
                        int[] yPoints = {
                            figura.posicion.y, 
                            figura.posicion.y + figura.alto, 
                            figura.posicion.y + figura.alto
                        };
                        g2d.drawPolygon(xPoints, yPoints, 3);
                        break;
                }
            }
            
            // Dibujar trazo actual (pincel)
            if (puntosActuales.size() > 1) {
                g2d.setColor(colorActual);
                g2d.setStroke(new BasicStroke(grosorTrazo));
                
                for (int i = 1; i < puntosActuales.size(); i++) {
                    Point p1 = puntosActuales.get(i - 1);
                    Point p2 = puntosActuales.get(i);
                    g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
                }
            }
        }
    }
}