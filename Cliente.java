import java.lang.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.rmi.*;

public class Cliente {
  public static void main(String args[]) throws Exception{

    try {

      Scanner valor = new Scanner(System.in);

      Usuario user = new Usuario();
      Corretora core = Corretora.getInstancia();

      double valorLance = 0;
      String status = null;
      String verifica = null;

      System.out.println("Qual o seu nome? ");
      user.setNome(valor.nextLine());

      System.out.println("Qual o seu saldo atual? ");
      user.setSaldo(valor.nextDouble());

      user.geraHash();

      System.out.println("Procurando objeto cadastro no servidor...");

      Interface inter = (Interface) Naming.lookup("rmi:///cadastrojj");
      boolean resposta = inter.registraCliente(user);

      System.out.println("Você deseja dar um lance?");
      verifica = valor.next();

      if (verifica.equals("Sim")) {

        System.out.println("Qual o valor do lance?");
        valorLance = valor.nextDouble();

        Lance l = new Lance(user, valorLance);
        boolean resposta2 = inter.registrarLance(l);

      }
        
      ArrayList<Lance> lances = inter.retornaLancesUsuario(user);
      core.mostraLances(lances);

      // System.out.println("Você deseja comprar ou vender?");
      // user.setStatus(valor.next());

      // System.out.println("Você deseja buscar clientes?");
      // status = valor.next();

      // if(status.3equals("S")){
      //   do {
      //     ArrayList<Usuario> clientes = inter.retornaClientes();

      //     core.mostraClientes(clientes);

      //     System.out.println("Você deseja buscar clientes?");
      //     status = valor.next();

      //   } while (status.equals("S"));
      // }

      // if(resposta == true){
      //   System.out.println("Cliente Cadastrado");
      // }

    } catch (NotBoundException e) {
      Scanner valor2 = new Scanner(System.in);

      Usuario user2 = new Usuario();
      
      System.out.println("Qual o seu nome? ");
      user2.setNome(valor2.nextLine());

      System.out.println("Qual o seu saldo atual? ");
      user2.setSaldo(valor2.nextDouble());

      user2.geraHash();

      System.out.println("Procurando objeto cadastro no servidor...");

      Interface inter = (Interface) Naming.lookup("rmi:///cadastro");
      boolean resposta2 = inter.registraCliente(user2);

    }

  }

}
