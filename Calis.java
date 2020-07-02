import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class Calis extends JFrame implements ActionListener{

	/*Declaracion de objetos*/
		JPanel panel;
		JLabel lblTexto1;
		JButton btnBoton1;

	public Calis()
	{
		/*Creacion de objetos y valores iniciales*/
		panel = new JPanel();
		panel.setLayout(null);

		/*Agregando objetos al panel*/
		lblTexto1 = new JLabel("MI TEXTO");
		lblTexto1.setBounds(50,150,200,50);
		btnBoton1 = new JButton("OK");
		btnBoton1.setBounds(50,70,100,30);

		/*Agregando objetos al panel*/
		panel.add(lblTexto1);
		panel.add(btnBoton1);

		/*Agregando panel a la ventajna y valores iniciales de la ventana*/
		this.add(panel);
		this.setTitle("MI PRIMER VENTANA");
		this.setVisible(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setSize(600,400);

		btnBoton1.addActionListener(this);
	}

	public void setBtnBoton1(JButton btnBoton1)
	{
		this.btnBoton1 = btnBoton1;
		this.btnBoton1.addActionListener(this);
	}

	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource() == this.btnBoton1)
		{
			System.out.println("LE DIERON CLICK AL BOTON");
		}
	}
}