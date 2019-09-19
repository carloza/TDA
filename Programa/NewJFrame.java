package Programa;

import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DebugGraphics;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;

import TDAArbol.EmptyTreeException;
import TDAArbol.Tree;
import TDALista.DoubleLinkedList;
import TDALista.PositionList;
import TDAPila.EmptyStackException;
import TDAPila.Stack;

public final class NewJFrame extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Punto_H ejercicios;
	private JButton jButton1;
	private JButton jButton2;
	private JButton jButton3;
	private JButton jButton4;
	private JButton jButton5;
	private JButton jButton6;
	private JButton jButton7;
	private JButton jButton8;
	private JButton jButton9;
	private JButton jButton10y11;
	private JButton jButton12;
	private JTextPane jTextArea1;
	private JButton btnNewButton;
	

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
				NewJFrame inst = new NewJFrame("Proyecto");
				inst.getContentPane().setLayout(null);
				//inst.setLocationRelativeTo(null);
				inst.setVisible(true);
	}
	
	//Constructor de la clase NewJFrame
	public NewJFrame(String e) {
		super(e);
		initGUI();
	}
	
	//Método privado. Inicializa la interfaz gráfica
	private void initGUI() {
		
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setResizable(false);
			OyenteMouse oy = new OyenteMouse();
				
			ejercicios = new Punto_H();
				
			{
				jButton1 = new JButton();
				getContentPane().add(jButton1);
				jButton1.setText("Cargar un árbol");
				jButton1.setBounds(17, 5, 263, 27);
				jButton1.addMouseListener(oy);
				jButton1.addActionListener(new OyenteEjerc1());
			}
			{
				jButton2 = new JButton();
				getContentPane().add(jButton2);
				jButton2.setText("Agregar nodo");
				jButton2.setBounds(17, 37, 263, 27);
				jButton2.setEnabled(false);
				jButton2.addMouseListener(oy);
				jButton2.addActionListener(new OyenteEjerc2());
			}
			{
				jButton3 = new JButton();
				getContentPane().add(jButton3);
				jButton3.setText("Mostrar rótulos en Preorden");
				jButton3.setBounds(17, 69, 263, 27);
				jButton3.setEnabled(false);
				jButton3.addMouseListener(oy);
				jButton3.addActionListener(new OyenteEjerc3());		
			}
			{
				jButton4 = new JButton();
				getContentPane().add(jButton4);
				jButton4.setText("Mostrar rótulos en Posorden");
				jButton4.setBounds(17, 101, 263, 27);
				jButton4.setEnabled(false);
				jButton4.addMouseListener(oy);
				jButton4.addActionListener(new OyenteEjerc4());	
			}
			{
				jButton5 = new JButton();
				getContentPane().add(jButton5);
				jButton5.setText("Mostrar rótulos por niveles");
				jButton5.setBounds(17, 133, 263, 27);
				jButton5.setEnabled(false);
				jButton5.addMouseListener(oy);
				jButton5.addActionListener(new OyenteEjerc5());	
			}
			{
				jButton6 = new JButton();
				getContentPane().add(jButton6);
				jButton6.setText("Mostrar rótulos en orden descendente");
				jButton6.setBounds(17, 165, 263, 27);
				jButton6.setEnabled(false);
				jButton6.addMouseListener(oy);
				jButton6.addActionListener(new OyenteEjerc6());	
			}
			{
				jButton7 = new JButton();
				getContentPane().add(jButton7);
				jButton7.setText("Eliminar nodos de altura dada");
				jButton7.setBounds(17, 197, 263, 27);
				jButton7.setEnabled(false);
				jButton7.addMouseListener(oy);
				jButton7.addActionListener(new OyenteEjerc7());
			}
			{
				jButton8 = new JButton();
				getContentPane().add(jButton8);
				jButton8.setText("Mostrar camino entre dos rótulos");
				jButton8.setBounds(17, 229, 263, 27);
				jButton8.setEnabled(false);
				jButton8.addMouseListener(oy);
				jButton8.addActionListener(new OyenteEjerc8());
			}
			{
				jButton9 = new JButton();
				getContentPane().add(jButton9);
				jButton9.setText("Árbol espejo");
				jButton9.setBounds(17, 261, 263, 27);
				jButton9.setEnabled(false);
				jButton9.addMouseListener(oy);
				jButton9.addActionListener(new OyenteEjerc9());
			}
			{
				jButton10y11 = new JButton();
				getContentPane().add(jButton10y11);
				jButton10y11.setText("Crear Mapeo y Diccionario");
				jButton10y11.setBounds(17, 293, 263, 27);
				jButton10y11.setEnabled(false);
				jButton10y11.addMouseListener(oy);
				jButton10y11.addActionListener(new OyenteEjer10y11());
			}
			{
				jButton12 = new JButton();
				getContentPane().add(jButton12);
				jButton12.setText("Mostrar profundidad y compañeros");
				jButton12.setBounds(17, 325, 263, 27);
				jButton12.setEnabled(false);
				jButton12.addMouseListener(oy);
				jButton12.addActionListener(new OyenteEjer12());
			}
			{
				jTextArea1 = new JTextPane();
				getContentPane().add(jTextArea1);
				jTextArea1.setBounds(17, 357, 263, 146);
				jTextArea1.setEditable(false);
				jTextArea1.setOpaque(false);
				jTextArea1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			}
			
			//TODO
			btnNewButton = new JButton("CA");
			btnNewButton.setBounds(17, 506, 26, 23);
			getContentPane().add(btnNewButton);
			btnNewButton.setEnabled(true);
			btnNewButton.addMouseListener(oy);
			btnNewButton.addActionListener(new OyenteCA());
			this.setSize(305, 568);
		
	}
	
	//TODO
	/*
	 * OyenteCA
	 */
	class OyenteCA implements ActionListener
	{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			try
			{
				ejercicios.punto1(1);
				ejercicios.punto2(2, 1);
				ejercicios.punto2(3, 1);
				ejercicios.punto2(4, 1);
				ejercicios.punto2(5, 2);
				ejercicios.punto2(6, 2);
				ejercicios.punto2(7, 4);
				JOptionPane.showMessageDialog(null,"El árbol por defecto fue creado correctamente.");
				jButton1.setEnabled(false);
				jButton2.setEnabled(true);
				jButton3.setEnabled(true);
				jButton4.setEnabled(true);
				jButton5.setEnabled(true);
				jButton6.setEnabled(true);
				jButton7.setEnabled(true);
				jButton8.setEnabled(true);
				jButton9.setEnabled(true);
				jButton10y11.setEnabled(true);
				btnNewButton.setEnabled(false);
			} 
			catch (ErrorExcecutionException e1)
			{
			e1.printStackTrace();// el programa anda de lujo!
			}
			
		}
		
	}
	
	/*
	 * OYENTE EJERCICIO 1
	 */
	class OyenteEjerc1 implements ActionListener{

		private JLabel jLabel1;
		private JTextField jTextField1;
		private JButton jButtonAcep;
		private JFrame ventana;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			ventana = new JFrame("Ejercicio 1");
			ventana.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			ventana.setSize(289, 166);
			ventana.getContentPane().setLayout(null);
			ventana.setResizable(false);
			ventana.setVisible(true);
			{
				jLabel1 = new JLabel();
				ventana.getContentPane().add(jLabel1);
				jLabel1.setText("Inserte el rótulo de la raíz a crear:");
				jLabel1.setBounds(12, 26, 259, 15);
			}
			{
				jTextField1 = new JTextField();
				ventana.getContentPane().add(jTextField1);
				jTextField1.setBounds(12, 53, 259, 22);
				jTextField1.setHorizontalAlignment(JTextField.RIGHT);
			}
			{
				jButtonAcep = new JButton();
				ventana.getContentPane().add(jButtonAcep);
				jButtonAcep.setText("Aceptar");
				jButtonAcep.setBounds(77, 93, 129, 22);
				jButtonAcep.addActionListener(new OyenteBotonAceptar());
			}
			
		}
		
		/*
		 * OYENTE BOTON ACEPTAR EJERCICIO 1
		 */
		class OyenteBotonAceptar implements ActionListener{
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					int i = Integer.parseInt(jTextField1.getText());
					ejercicios.punto1(i);
					JOptionPane.showMessageDialog(null,"La raíz fue creada correctamente.");
					jButton1.setEnabled(false);
					jButton2.setEnabled(true);
					btnNewButton.setEnabled(false);//TODO
					ventana.dispose();
					
				}catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null,"El valor insertado no es válido. Por favor, vuelva a intentarlo.");
				} catch (ErrorExcecutionException e) {
					//esto nunca pasa porque cuando el arbol tiene raíz el boton "Cargar árbol" se deshabilita
				}
				
			}
			
		}
		
	}
	
	/*
	 * OYENTE EJERCICIO 2
	 */
	class OyenteEjerc2 implements ActionListener{

		private JFrame ventana;
		private JLabel jLabel1;
		private JTextField jTextField1;
		private JLabel jLabel2;
		private JTextField jTextField2;
		private JButton jButtonAcep;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			ventana = new JFrame("Ejercicio 2");
			ventana.setSize(289, 219);
			ventana.getContentPane().setLayout(null);
			ventana.setResizable(false);
			ventana.setVisible(true);
			{
				jLabel1 = new JLabel();
				ventana.getContentPane().add(jLabel1);
				jLabel1.setText("Inserte el padre del nuevo nodo:");
				jLabel1.setBounds(12, 26, 259, 15);
			}
			{
				jTextField1 = new JTextField();
				ventana.getContentPane().add(jTextField1);
				jTextField1.setBounds(12, 53, 259, 22);
				jTextField1.setDebugGraphicsOptions(DebugGraphics.BUFFERED_OPTION);
				jTextField1.setHorizontalAlignment(JTextField.RIGHT);
			}
			{
				jButtonAcep = new JButton();
				ventana.getContentPane().add(jButtonAcep);
				jButtonAcep.setText("Aceptar");
				jButtonAcep.setBounds(77, 150, 129, 22);
				jButtonAcep.addActionListener(new OyenteBotonAceptar());
			}
			{
				jLabel2 = new JLabel();
				ventana.getContentPane().add(jLabel2);
				jLabel2.setText("Inserte el rótulo del nodo a crear:");
				jLabel2.setBounds(12, 87, 257, 15);
			}
			{
				jTextField2 = new JTextField();
				ventana.getContentPane().add(jTextField2);
				jTextField2.setBounds(12, 114, 257, 22);
				jTextField2.setHorizontalAlignment(JTextField.RIGHT);
			}
			
		}
		
		/*
		 * OYENTE BOTON ACEPTAR EJERCICIO 2
		 */
		class OyenteBotonAceptar implements ActionListener{
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					int p = Integer.parseInt(jTextField1.getText());
					int i = Integer.parseInt(jTextField2.getText());
					ejercicios.punto2(i, p);
					JOptionPane.showMessageDialog(null,"El nuevo nodo fue creado correctamente.");
					jButton3.setEnabled(true);
					jButton4.setEnabled(true);
					jButton5.setEnabled(true);
					jButton6.setEnabled(true);
					jButton7.setEnabled(true);
					jButton8.setEnabled(true);
					jButton9.setEnabled(true);
					jButton10y11.setEnabled(true);
					jButton12.setEnabled(false);
					btnNewButton.setEnabled(false); //TODO
					ventana.dispose();
					
				}
				catch(ErrorExcecutionException e)
				{
						JOptionPane.showMessageDialog(null,"El rótulo del padre ingresado no existe en el árbol. Por Favor, vuelva a intentarlo.");
				} 
				catch (NumberFormatException e) 
				{
					JOptionPane.showMessageDialog(null,"Los datos ingresados no son correctos. Por favor, vuelva a intentarlo.");
				}
				
			}
			
		}
		
	}
	
	/*
	 * OYENTE EJERCICIO 3
	 */
	class OyenteEjerc3 implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			JOptionPane.showMessageDialog(null, "Rótulos en Preorden:\n"+ejercicios.punto3());
		}
	}
	
	/*
	 * OYENTE EJERCICIO 4
	 */
	class OyenteEjerc4 implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			JOptionPane.showMessageDialog(null, "Rótulos en Posorden:\n"+ejercicios.punto4());
		}
		
	}
	
	/*
	 * OYENTE EJERCICIO 5
	 */
	class OyenteEjerc5 implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			JOptionPane.showMessageDialog(null, "Rótulos por niveles:\n"+ejercicios.punto5());
		}
		
	}
	
	/*
	 * OYENTE EJERCICIO 6
	 */
	class OyenteEjerc6 implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			JOptionPane.showMessageDialog(null, "Rótulos en orden descendente segun la cantidad de hijos:\n"+ejercicios.punto6());
		}
		
	}
	
	/*
	 * OYENTE EJERCICIO 7
	 */
	class OyenteEjerc7 implements ActionListener{
		
		private JFrame ventana;
		private JLabel jLabel1; 
		private JButton jButtonAcep;
		private JTextField jTextField2;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			ventana = new JFrame("Ejercicio 7");
			ventana.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			ventana.setSize(291, 161);
			ventana.getContentPane().setLayout(null);
			ventana.setResizable(false);
			ventana.setVisible(true);
			{
				jLabel1 = new JLabel();
				ventana.getContentPane().add(jLabel1);
				jLabel1.setText("Inserte la altura a eliminar:");
				jLabel1.setBounds(12, 26, 259, 15);
			}
			{
				jButtonAcep = new JButton();
				ventana.getContentPane().add(jButtonAcep);
				jButtonAcep.setText("Aceptar");
				jButtonAcep.setBounds(77, 100, 129, 22);
				jButtonAcep.addActionListener(new OyenteBotonAceptar());
			}
			{
				jTextField2 = new JTextField();
				ventana.getContentPane().add(jTextField2);
				jTextField2.setBounds(12, 53, 257, 22);
				jTextField2.setHorizontalAlignment(JTextField.RIGHT);
			}
			
		}
		
		/*
		 * OYENTE BOTON ACEPTAR EJERCICIO 7
		 */
		class OyenteBotonAceptar implements ActionListener{
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					int i = Integer.parseInt(jTextField2.getText());
					Stack<Integer> pila = ejercicios.punto7(i);
					String cadena = "";
					
					if (pila.isEmpty())
					{
						String men = (i==ejercicios.AlturaArbol())? "No se puede eliminar la raíz." : "El nivel ingresado no existe, no se eliminaron elementos";
						JOptionPane.showMessageDialog(null,men);
					}
					else
					{
						while(!pila.isEmpty())
						{
							cadena = cadena+pila.pop()+" - ";
						}
						JOptionPane.showMessageDialog(null,"Se eliminaron los nodos de altura: "+i+"\nLos cuales son:\n"+cadena);
					}
					if(ejercicios.isEmpty())
					{
						JOptionPane.showMessageDialog(null,"El árbol ha quedado vacio.");
						jButton1.setEnabled(true);
						jButton2.setEnabled(false);
						jButton3.setEnabled(false);
						jButton4.setEnabled(false);
						jButton5.setEnabled(false);
						jButton6.setEnabled(false);
						jButton7.setEnabled(false);
						jButton8.setEnabled(false);
						jButton9.setEnabled(false);
						jButton10y11.setEnabled(false);
						btnNewButton.setEnabled(true);//TODO
					}
					jButton12.setEnabled(false);				
					ventana.dispose();
										
				} 
				catch (NumberFormatException e)
				{
					JOptionPane.showMessageDialog(null,"La altura ingresada no es correcta. Por favor, vuelva a intentarlo.");
				} 
				catch (EmptyStackException e)
				{
					//Esto nunca pasa porque estoy verificando antes de que la pila no esta vacia
				}
				
			}
				
		}
		
	}
	
	/*
	 * OYENTE EJERCICIO 8
	 */
	class OyenteEjerc8 implements ActionListener{

		private JFrame ventana;
		private JLabel jLabel1;
		private JTextField jTextField2;
		private JTextField jTextField1;
		private JLabel jLabel2;
		private JButton jButtonAcep;
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			ventana = new JFrame("Ejercicio 8");
			ventana.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			ventana.setSize(291, 230);
			ventana.getContentPane().setLayout(null);
			ventana.setResizable(true);
			ventana.setVisible(true);
			{
				jLabel1 = new JLabel();
				ventana.getContentPane().add(jLabel1);
				jLabel1.setText("Inserte el rótulo del primer nodo:");
				jLabel1.setBounds(12, 26, 259, 15);
			}
			{
				jButtonAcep = new JButton();
				ventana.getContentPane().add(jButtonAcep);
				jButtonAcep.setText("Aceptar");
				jButtonAcep.setBounds(77, 154, 129, 22);
				jButtonAcep.addActionListener(new OyenteBotonAceptar());
			}
			{
				jTextField1 = new JTextField();
				ventana.getContentPane().add(jTextField1);
				jTextField1.setBounds(12, 53, 257, 22);
				jTextField1.setHorizontalAlignment(JTextField.RIGHT);
			}
			{
				jTextField2 = new JTextField();
				ventana.getContentPane().add(jTextField2);
				jTextField2.setBounds(12, 108, 259, 22);
				jTextField2.setHorizontalAlignment(JTextField.RIGHT);
			}
			{
				jLabel2 = new JLabel();
				ventana.getContentPane().add(jLabel2);
				jLabel2.setText("Inserte rótulo del segundo nodo:");
				jLabel2.setBounds(12, 81, 259, 15);
			}
			
		}
		
		/*
		 * OYENTE BOTON ACEPTAR EJERCICIO 8
		 */
		class OyenteBotonAceptar implements ActionListener{
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					int i = Integer.parseInt(jTextField1.getText());
					int j = Integer.parseInt(jTextField2.getText());
					Stack<Integer> pila = ejercicios.punto8(i,j);
					String cadena = "";
					while(!pila.isEmpty()){
							
							cadena = cadena+pila.pop()+" - ";
					}
					JOptionPane.showMessageDialog(null,"El camino de "+i+" a "+j+" es:\n"+cadena);		
					ventana.dispose();
										
				} 
				catch (NumberFormatException e) 
				{
					JOptionPane.showMessageDialog(null,"Datos ingresados incorrectos. Por favor, vuelva a intentarlo.");
				} 
				catch (EmptyStackException e) 
				{
					//Esto nunca pasa porque estoy verificando antesa de que la pila no esta vacia
				} 
				catch (ErrorExcecutionException e) 
				{
					JOptionPane.showMessageDialog(null,"Rótulos ingresados inexistentes. Por favor, vuelva a intentarlo.");
				}
				
			}
				
		}
		
	}
	
	/*
	 * OYENTE EJERCICIO 9
	 */
	class OyenteEjerc9 implements ActionListener{
				
		@Override
		public void actionPerformed(ActionEvent e) {
			
			Tree<Integer> espejo = ejercicios.Punto9();
			String cPos, cPre, cNiv;
			cPos = cPre = cNiv = "";

			try
			{
				if (!espejo.isEmpty())
				{
					cPos = ejercicios.StringPosOrden(espejo, espejo.root());
					cPre = ejercicios.StringPreOrden(espejo);
					cNiv = ejercicios.StringPorNiveles(espejo);
				}
			}
			catch(EmptyTreeException exc)
			{
				//nunca pasa porque verifico antes de que el árbol no este vacio
			}
			
			JOptionPane.showMessageDialog(null,"El árbol espejo se creo correctamente.\nEl árbol espejo en PosOrden: "+cPos+"\nEl árbol espejo en PreOrden: "+cPre+"\nEl árbol espejo por Niveles: "+cNiv);
		}
	}
	
	/*
	 * OYENTE EJERCICIO 1O Y 11
	 */
	class OyenteEjer10y11 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			ejercicios.punto10y11();
			JOptionPane.showMessageDialog(null,"El mapeo y el diccionario se crearon correctamente");
			jButton12.setEnabled(true);
		}
		
	}
	
	/*
	 * OYENTE EJERCICIO 12
	 */
	class OyenteEjer12 implements ActionListener{

		private JLabel jLabel1;
		private JTextField jTextField1;
		private JButton jButtonAcep;
		private JFrame ventana;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			ventana = new JFrame("Ejercicio 12");
			ventana.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			ventana.setSize(357, 166);
			ventana.getContentPane().setLayout(null);
			ventana.setResizable(false);
			ventana.setVisible(true);
			ventana.setPreferredSize(new java.awt.Dimension(357, 166));
			{
				jLabel1 = new JLabel();
				ventana.getContentPane().add(jLabel1);
				jLabel1.setText("Inserte el rótulo del nodo para calcular su profundidad:");
				jLabel1.setBounds(12, 26, 325, 15);
			}
			{
				jTextField1 = new JTextField();
				ventana.getContentPane().add(jTextField1);
				jTextField1.setBounds(12, 53, 325, 22);
				jTextField1.setHorizontalAlignment(JTextField.RIGHT);
			}
			{
				jButtonAcep = new JButton();
				ventana.getContentPane().add(jButtonAcep);
				jButtonAcep.setText("Aceptar");
				jButtonAcep.setBounds(110, 92, 129, 22);
				jButtonAcep.addActionListener(new OyenteBotonAceptar());
			}
			
		}
		
		/*
		 * OYENTE BOTON ACEPTAR EJERCICIO 12
		 */
		class OyenteBotonAceptar implements ActionListener{
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					int i = Integer.parseInt(jTextField1.getText());
					PositionList<Integer> lista = new DoubleLinkedList<Integer>();
					int prof = ejercicios.punto12(i, lista);
					String compas = "";		
					for(Integer n : lista){
						compas = compas + n + " - ";
					}
					
					JOptionPane.showMessageDialog(null,"La profundida de "+i+" es: "+prof+"\nLos nodos con misma profundidad son:\n"+compas);
					ventana.dispose();
					
				}
				catch(NumberFormatException e)
				{
					JOptionPane.showMessageDialog(null,"El valor insertado no es válido. Por favor, vuelva a intentarlo.");
				} 
				catch (ErrorExcecutionException e)
				{
					JOptionPane.showMessageDialog(null,"El rótulo insertado es inexistente. Por favor, vuelva a intentarlo.");
				}
								
			}
			
		}
		
	}
		
		
	
	/*
	 * OYENTE DE MOUSE. MUESTRA LA EXPLICACION DEL EJERCICIO EN EL JLABEL
	 */
	class OyenteMouse implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
			Object boton = e.getSource();
			
			if (boton==jButton1)
			{
				jTextArea1.setText("Ejercicio 1:\n\nEsta opción debe crear el árbol de un solo nodo ofreciendo la posibilidad de especificar el rótulo de la raíz.");
			}
			
			if (boton==jButton2)
			{
				jTextArea1.setText("Ejercicio 2:\n\nEsta opción permite insertar un nodo especificando su rótulo R. El nodo se insertará como ultimo hijo del nodo cuyo rótulo P es también especificado.");
			}
			
			if (boton==jButton3)
			{
				jTextArea1.setText("Ejercicio 3:\n\nMuestra los rótulos de los nodos al recorrer el árbol en preorden.");
			}
			
			if (boton==jButton4)
			{
				jTextArea1.setText("Ejercicio 4:\n\nMuestra los rótulos de los nodos al recorrer el árbol en posorden.");
			}
			
			if (boton==jButton5)
			{
				jTextArea1.setText("Ejercicio 5:\n\nMuestra los rótulos de los nodos al recorrer el árbol por niveles.");
			}
			
			if (boton==jButton6)
			{
				jTextArea1.setText("Ejercicio 6:\n\nMuestra los rótulos de los nodos en orden descendente. Un nodo N1 es menor que otro nodo N2 si la cantidad de hijos de N1 es menor que la cantidad de hijos de N2.");
			}
			
			if (boton==jButton7)
			{
				jTextArea1.setText("Ejercicio 7:\n\nEliminar todos los nodos de una altura dada. La altura se recibe como parámetro. Retorna los rótulos de los nodos eliminados.");
			}
			
			if (boton==jButton8)
			{
				jTextArea1.setText("Ejercicio 8:\n\nDados dos rótulos R1 y R2, retorna y muestra el camino del nodo N1 que tiene como rótulo a R1 al nodo  N2 que tiene como rótulo a R2.");
			}
			
			if (boton==jButton9)
			{
				jTextArea1.setText("Ejercicio 9:\n\nCrea un 'árbol espejo' invirtiendo el orden de los hijos de cada nodo.");
			}
			
			if (boton==jButton10y11)
			{
				jTextArea1.setText("Ejercicio 10 y 11:\n\nDado el árbol cargado con las opciones 1 y 2, obtiene un mapeo M con las entradas (R,p) y obtiene un diccionario D con las entradas (p,R) donde  p la  profundidad del nodo cuyo rótulo es R.");
			}
			
			if (boton==jButton12)
			{
				jTextArea1.setText("Ejercicio 12:\n\nDado un rótulo R, utiliza el mapeo M para mostrar la profundidad p del " +
						"nodo que contiene a R como rótulo. Muestra a continuación todos los rótulos de los nodos " +
						"que tienen profundidad p utilizando el diccionario D");
			}
			
			if (boton==btnNewButton)
			{
				jTextArea1.setText("Carga automaticamente un arbol por defecto.");
			}
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			jTextArea1.setText("");
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
