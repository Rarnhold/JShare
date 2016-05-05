package br.arnhold.jshare.cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.dagostini.jshare.comun.Cliente;
import br.dagostini.jshare.comun.IServer;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.awt.event.ActionEvent;

public class TelaCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtPortaServidor;
	private JTextField txtIpServidor;
	private JTextField txtNomeCliente;
	private JTextField textField_3;
	private JTable table;
	private Registry registry;
	private IServer servidor;
	private Cliente c = new Cliente();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCliente frame = new TelaCliente();
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
	public TelaCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblNome = new JLabel("Nome:");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 0;
		panel.add(lblNome, gbc_lblNome);

		txtNomeCliente = new JTextField();
		txtNomeCliente.setText("Rafael");
		GridBagConstraints gbc_txtNomeCliente = new GridBagConstraints();
		gbc_txtNomeCliente.gridwidth = 11;
		gbc_txtNomeCliente.insets = new Insets(0, 0, 5, 5);
		gbc_txtNomeCliente.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNomeCliente.gridx = 1;
		gbc_txtNomeCliente.gridy = 0;
		panel.add(txtNomeCliente, gbc_txtNomeCliente);
		txtNomeCliente.setColumns(10);

		JLabel lblIpDoServidor = new JLabel("Ip do servidor:");
		GridBagConstraints gbc_lblIpDoServidor = new GridBagConstraints();
		gbc_lblIpDoServidor.anchor = GridBagConstraints.EAST;
		gbc_lblIpDoServidor.fill = GridBagConstraints.VERTICAL;
		gbc_lblIpDoServidor.insets = new Insets(0, 0, 5, 5);
		gbc_lblIpDoServidor.gridx = 0;
		gbc_lblIpDoServidor.gridy = 1;
		panel.add(lblIpDoServidor, gbc_lblIpDoServidor);

		txtIpServidor = new JTextField();
		txtIpServidor.setText("127.0.0.1");
		GridBagConstraints gbc_txtIpServidor = new GridBagConstraints();
		gbc_txtIpServidor.gridwidth = 7;
		gbc_txtIpServidor.insets = new Insets(0, 0, 5, 5);
		gbc_txtIpServidor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtIpServidor.gridx = 1;
		gbc_txtIpServidor.gridy = 1;
		panel.add(txtIpServidor, gbc_txtIpServidor);
		txtIpServidor.setColumns(10);

		JLabel lblPorta = new JLabel("Porta:");
		GridBagConstraints gbc_lblPorta = new GridBagConstraints();
		gbc_lblPorta.anchor = GridBagConstraints.EAST;
		gbc_lblPorta.insets = new Insets(0, 0, 5, 5);
		gbc_lblPorta.gridx = 0;
		gbc_lblPorta.gridy = 2;
		panel.add(lblPorta, gbc_lblPorta);

		txtPortaServidor = new JTextField();
		txtPortaServidor.setText("1818");
		GridBagConstraints gbc_txtPortaServidor = new GridBagConstraints();
		gbc_txtPortaServidor.fill = GridBagConstraints.BOTH;
		gbc_txtPortaServidor.gridwidth = 5;
		gbc_txtPortaServidor.insets = new Insets(0, 0, 5, 5);
		gbc_txtPortaServidor.gridx = 1;
		gbc_txtPortaServidor.gridy = 2;
		panel.add(txtPortaServidor, gbc_txtPortaServidor);
		txtPortaServidor.setColumns(10);

		JButton btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				conectar();
			}
		});
		GridBagConstraints gbc_btnConectar = new GridBagConstraints();
		gbc_btnConectar.gridwidth = 2;
		gbc_btnConectar.insets = new Insets(0, 0, 5, 5);
		gbc_btnConectar.gridx = 6;
		gbc_btnConectar.gridy = 2;
		panel.add(btnConectar, gbc_btnConectar);

		JButton btnDesconectar = new JButton("Desconectar");
		btnDesconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				desconectar();
			}
		});
		btnDesconectar.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_btnDesconectar = new GridBagConstraints();
		gbc_btnDesconectar.fill = GridBagConstraints.VERTICAL;
		gbc_btnDesconectar.gridwidth = 4;
		gbc_btnDesconectar.insets = new Insets(0, 0, 5, 5);
		gbc_btnDesconectar.gridx = 8;
		gbc_btnDesconectar.gridy = 2;
		panel.add(btnDesconectar, gbc_btnDesconectar);

		JLabel lblProcurar = new JLabel("Procurar");
		GridBagConstraints gbc_lblProcurar = new GridBagConstraints();
		gbc_lblProcurar.anchor = GridBagConstraints.EAST;
		gbc_lblProcurar.insets = new Insets(0, 0, 0, 5);
		gbc_lblProcurar.gridx = 0;
		gbc_lblProcurar.gridy = 3;
		panel.add(lblProcurar, gbc_lblProcurar);

		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.gridwidth = 6;
		gbc_textField_3.insets = new Insets(0, 0, 0, 5);
		gbc_textField_3.fill = GridBagConstraints.BOTH;
		gbc_textField_3.gridx = 2;
		gbc_textField_3.gridy = 3;
		panel.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);

		JButton btnProcurar = new JButton("Procurar");
		GridBagConstraints gbc_btnProcurar = new GridBagConstraints();
		gbc_btnProcurar.gridwidth = 4;
		gbc_btnProcurar.gridx = 8;
		gbc_btnProcurar.gridy = 3;
		panel.add(btnProcurar, gbc_btnProcurar);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);

		table = new JTable();
		panel_1.add(table);
	}

	protected void desconectar() {
		String ip = txtIpServidor.getText().trim();
		String nome = txtNomeCliente.getText().trim();
		Integer porta = Integer.parseInt(txtPortaServidor.getText().trim());

		try {
			registry = LocateRegistry.getRegistry(ip, porta);
			IServer servico = (IServer) registry.lookup(IServer.NOME_SERVICO);
			servico.desconectar(c);

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void conectar() {
		String ip = txtIpServidor.getText().trim();
		String nome = txtNomeCliente.getText().trim();
		Integer porta = Integer.parseInt(txtPortaServidor.getText().trim());

		c.setNome(nome);
		try {
			registry = LocateRegistry.getRegistry(ip, porta);
			IServer servico = (IServer) registry.lookup(IServer.NOME_SERVICO);
			servico.registrarCliente(c);

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
