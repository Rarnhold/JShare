package br.arnhold.jshare.server;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.dagostini.jshare.comum.pojos.Arquivo;
import br.dagostini.jshare.comun.Cliente;
import br.dagostini.jshare.comun.IServer;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;

public class TelaServer extends JFrame  implements IServer {

	private JPanel contentPane;
	private JTextField txtIp;
	private JTextField txtPorta;
	private JTextArea txtApresentacao;
	private JButton btnIniciaServico;
	private JButton btnFecharServico;
	private IServer servidor;
	
	//Lista de cliente
	private List<Cliente> listaCliente = new ArrayList<>(); 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaServer frame = new TelaServer();
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
	public TelaServer() {
		setTitle("Servidor Capiroto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblIp = new JLabel("IP:");
		GridBagConstraints gbc_lblIp = new GridBagConstraints();
		gbc_lblIp.anchor = GridBagConstraints.WEST;
		gbc_lblIp.insets = new Insets(0, 0, 5, 5);
		gbc_lblIp.gridx = 0;
		gbc_lblIp.gridy = 0;
		contentPane.add(lblIp, gbc_lblIp);
		
		txtIp = new JTextField();
		txtIp.setText("127.0.0.1");
		GridBagConstraints gbc_txtIp = new GridBagConstraints();
		gbc_txtIp.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtIp.gridwidth = 4;
		gbc_txtIp.insets = new Insets(0, 0, 5, 5);
		gbc_txtIp.gridx = 1;
		gbc_txtIp.gridy = 0;
		contentPane.add(txtIp, gbc_txtIp);
		txtIp.setColumns(10);
		
		JLabel lblPorta = new JLabel("Porta:");
		GridBagConstraints gbc_lblPorta = new GridBagConstraints();
		gbc_lblPorta.anchor = GridBagConstraints.WEST;
		gbc_lblPorta.insets = new Insets(0, 0, 5, 5);
		gbc_lblPorta.gridx = 0;
		gbc_lblPorta.gridy = 1;
		contentPane.add(lblPorta, gbc_lblPorta);
		
		txtPorta = new JTextField();
		txtPorta.setText("1001");
		GridBagConstraints gbc_txtPorta = new GridBagConstraints();
		gbc_txtPorta.gridwidth = 3;
		gbc_txtPorta.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPorta.insets = new Insets(0, 0, 5, 5);
		gbc_txtPorta.gridx = 1;
		gbc_txtPorta.gridy = 1;
		contentPane.add(txtPorta, gbc_txtPorta);
		txtPorta.setColumns(10);
		
		btnIniciaServico = new JButton("Inicia Serviço");
		btnIniciaServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iniciarServidor();
			}
		});
		GridBagConstraints gbc_btnIniciaServico = new GridBagConstraints();
		gbc_btnIniciaServico.insets = new Insets(0, 0, 5, 5);
		gbc_btnIniciaServico.gridx = 4;
		gbc_btnIniciaServico.gridy = 1;
		contentPane.add(btnIniciaServico, gbc_btnIniciaServico);
		
		btnFecharServico = new JButton("Parar Serviço");
		btnFecharServico.setEnabled(false);
		btnFecharServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pararServico();
			}
		});
		GridBagConstraints gbc_btnFecharServico = new GridBagConstraints();
		gbc_btnFecharServico.insets = new Insets(0, 0, 5, 5);
		gbc_btnFecharServico.gridx = 5;
		gbc_btnFecharServico.gridy = 1;
		contentPane.add(btnFecharServico, gbc_btnFecharServico);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 10;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		txtApresentacao = new JTextArea();
		txtApresentacao.setFont(new Font("Dialog", Font.BOLD, 12));
		txtApresentacao.setBackground(Color.BLACK);
		txtApresentacao.setForeground(Color.WHITE);
		txtApresentacao.setEditable(false);
		scrollPane.setViewportView(txtApresentacao);
	}

	protected void pararServico() {
		btnIniciaServico.setEnabled(true);
		// TODO Auto-generated method stubtnFecharServico.setEnabled(false);
		txtIp.setEnabled(true);
		txtPorta.setEnabled(true);
		
		
		
		txtApresentacao.append("Serviço Parado \n");
		
		
	}

	private void iniciarServidor() {
		btnIniciaServico.setEnabled(false);
		btnFecharServico.setEnabled(true);
		txtIp.setEnabled(false);
		txtPorta.setEditable(false);
		
		try {
			servidor = (IServer) UnicastRemoteObject.exportObject(this,0);
			Registry registry = LocateRegistry.createRegistry(Integer.parseInt(txtPorta.getText().trim()));
			registry.rebind(IServer.NOME_SERVICO, servidor);
		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		txtApresentacao.append("Serviço iniciado \n");	
		
		
	}

	
	@Override
	public void registrarCliente(Cliente c) throws RemoteException {
		listaCliente.add(c);
		
		txtApresentacao.append("Cliente "+ c.getNome() +" adicionado com sucesso");
		
	}

	@Override
	public void publicarListaArquivos(Cliente c, List<Arquivo> lista) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<Cliente, List<Arquivo>> procurarArquivo(String nome) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] baixarArquivo(Arquivo arq) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void desconectar(Cliente c) throws RemoteException {
		listaCliente.remove(c);
		
		txtApresentacao.append("Cliente "+ c.getNome() +" removido com sucesso");
		
	}

}
