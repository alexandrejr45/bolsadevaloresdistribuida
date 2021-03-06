import java.lang.*;
import java.io.*;
import java.util.*;
import java.lang.String;
import javax.swing.*;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;
import java.rmi.registry.Registry;

public class Servidor2 extends UnicastRemoteObject implements Interface {
  public Servidor2() throws RemoteException {
    super();
  };

  private Corretora corretora = Corretora.getInstancia();

  public boolean registraCliente(Usuario cliente) throws RemoteException {
    System.out.println("Servidor recebeu uma chamada para registrar um Cliente");

    try {

      if (Corretora.adicionarCliente(cliente)) {
        System.out.println("Cliente cadastrado");

      }

      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public ArrayList retornaClientes() throws RemoteException {
    System.out.println("Servidor recebeu uma chamada para listar os clientes");

    try {
      return corretora.retornaLista();

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }

  public boolean registrarLance(Lance lance) throws RemoteException {
    System.out.println("Servidor recebeu uma chamada para registrar um Lance");

    try {

      if (corretora.adicionarLance(lance)) {
        System.out.println("Lance cadastrado");
        return true;
      } else {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public boolean comprarLance(Lance lance, int indiceLance) throws RemoteException {
    System.out.println("Servidor recebeu uma chamada para comprar um Lance");

    try {
      if (corretora.comprarLance(lance, indiceLance)) {
        System.out.println("Lance comprado com sucesso");
        return true;
      } else {
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public ArrayList<Lance> retornaLancesUsuario(Usuario user) throws RemoteException {
    System.out.println("Servidor recebeu uma chamada para listar os lances ");
    ArrayList<Lance> lista = new ArrayList<>();

    try {
      lista = corretora.retornaLancesSaldo(user);

      return lista;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static void main(String args[]) {

    try {
      Registry registry2 = Registro.criaRegistro(2230);

      System.out.println("Criando o objeto servidor...");
      Servidor2 servidor2 = new Servidor2(); // cria um objeto
      System.out.println("Conectando o objeto cadastro no Registry...");
      registry2.rebind("cadastro2", servidor2); // registra o objeto forn como "produto"

    } catch (Exception e) {
      System.out.println("Servidor.main: " + e);
    }

    System.out.println("Pronto para receber chamadas RMI...");

  }
}
