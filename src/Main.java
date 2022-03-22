import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    ArrayList<Servico> pedidos = new ArrayList<Servico>();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        int escolha;
        Calendar hoje = Calendar.getInstance();
        int horaAtual = hoje.get(Calendar.HOUR_OF_DAY);
        int minAtual = hoje.get(Calendar.MINUTE);
        int tempAtual = minAtual + (horaAtual * 60);
        ArrayList<Estabelecimento> estabelecimentos = new ArrayList<Estabelecimento>();
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        boolean controlador = true;

        System.out.println("Bem-Vindo ao Comidas Ocasionais");
        while (controlador) {
            System.out.println("""
                    =========================================
                    1-Login
                    2-Cadastro de Usuario
                    3-Cadastro de Restaurante
                    =========================================
                    """);
            System.out.println("Escolha as opções:");
            escolha = scan.nextInt();

            switch (escolha) {
                case 1: // TODO: Matheus
                    System.out.print("Digite seu login: ");
                    String entradaLogin = scan.next();
                    System.out.print("Digite sua senha: ");
                    String entradaSenha = scan.next();

                    for (Cliente cliente : clientes) {
                        Cadastro cadastro = cliente.getCadastro();
                        if (
                            cadastro.getLogin().equals(entradaLogin)
                            &&
                            cadastro.getSenha().equals(entradaSenha)
                        ) {
                            controlador = false;
                            System.out.println();
                            break;
                        } else {
                            continue;
                        }
                    }
                    System.out.println(
                        controlador == false
                            ? "Login realizado com sucesso!\n"
                            : "ERRO: Credenciais não registradas. Cadastre-se primeiro.\n"
                    );

                    break;
                case 2:
                    Cadastro cadastro = getCadastro();
                    Endereco enderecoUser = getEndereco();
                    Cliente cliente = new Cliente(cadastro);
                    cliente.addEndereco(enderecoUser);
                    clientes.add(cliente);
                    System.out.println("Cadastro realizado com sucesso!\n\n");
                    break;
                case 3:
                    System.out.print("Insira o nome do restaurante: ");
                    String nome = scan.next();
                    System.out.print("Insira o cnpj: ");
                    String cnpj = scan.next();
                    System.out.print("Insira os horarios de funcionamento: ");
                    
                    int abertura = getTempoAbertura();
                    int fechamento = getTempoFechamento();

                    System.out.print("Insira seu endereço: ");
                    Endereco enderecoComp = getEndereco();
                    HashMap<String, Integer> menu = getMenu();

                    Estabelecimento estabelecimento = new Estabelecimento(
                            nome,
                            cnpj,
                            abertura,
                            fechamento,
                            menu 
                    );
                    estabelecimento.addEndereco(enderecoComp);
                    estabelecimentos.add(estabelecimento);
                    

                    break;

                default:
                    System.out.println("Erro: Seleção inválida.");
                    break;

            }

        }

    }

    private static Cadastro getCadastro() {
        System.out.println("Digite as inforações");
        System.out.print("nome: ");
        String login = scan.next();

        System.out.print("CPF: ");
        String cpf = scan.next();

        System.out.print("email: ");
        String email = scan.next();

        System.out.print("senha: ");
        String senha = scan.next();

        return new Cadastro(login, cpf, email, senha);
    }

    private static Endereco getEndereco() {
        Scanner scanEndereco = new Scanner(System.in);
        System.out.println("Digite as inforações");
        System.out.print("Rua: ");
        String Rua = scanEndereco.nextLine();

        System.out.print("Número da Casa: ");
        String numCasa = scanEndereco.nextLine();

        System.out.print("Bairro: ");
        String Bairro = scanEndereco.nextLine();

        System.out.print("Cidade: ");
        String Cidade = scanEndereco.nextLine();

        // scanEndereco.close();
        return new Endereco(Rua, numCasa, Bairro, Cidade);
    }

    private static int getTempoAbertura() {
        int horaAbertura;
        int minAbertura;
        int abertura;

        do {
            System.out.println(
                    "Digite a HORA de abertura do seu estabelecimento\nhorários válidos são das 0 as 23)\n<NÃO DIGITE OS MINUTOS>\n>");
            horaAbertura = scan.nextInt();
        } while (horaAbertura < 0 && horaAbertura > 23);

        do {
            System.out.println(
                    "Digite os MINUTOS de abertura do seu estabelecimento\nhorários válidos são de 0 a 59\n<NÃO DIGITE AS HORAS>\n>");
            minAbertura = scan.nextInt();
        } while (minAbertura < 0 && minAbertura > 59);

        abertura = minAbertura + (horaAbertura * 60);

        return abertura;
    }

    private static int getTempoFechamento() {
        int horaFechamento;
        int minFechamento;
        int fechamento;

        do {
            System.out.println(
                    "Digite a HORA de fechamento do seu estabelecimento\nhorários válidos são das 0 as 23)\n<NÃO DIGITE OS MINUTOS>\n> ");
            horaFechamento = scan.nextInt();
        } while (horaFechamento < 0 && horaFechamento > 23);

        do {
            System.out.println(
                    "Digite os MINUTOS de abertura do seu estabelecimento\nhorários válidos são de 0 a 59\n<NÃO DIGITE AS HORAS>\n>");
            minFechamento = scan.nextInt();
        } while (minFechamento < 0 && minFechamento > 59);

        fechamento = minFechamento + (horaFechamento * 60);

        return fechamento;
    }

    private static HashMap<String, Integer> getMenu(){

        HashMap<String, Integer> menu = new HashMap<String, Integer>();
        System.out.println("Central do Menu");
        boolean controlador = true;
        int escolha;
        while (controlador) {
             System.out.println("""
            Gostaria de Adicionar um item
            _________________________________________________

            1- Adicionar prato 
            2- Remover prato
            3- Confirmar selação
            _________________________________________________

            """);
            System.out.println("Escolha as opções:");
            escolha = scan.nextInt();

            switch (escolha){
                case 1: 
                    System.out.println("Digite o nome do prato adicionalo.");
                    String prato = scan.next();
                    System.out.println("Digite o valor do prato adicionalo.");
                    int valor = (int) (scan.nextFloat() * 100);
                    valor = valor * 100;
                    menu.put(prato, valor);

                case 2:
                    if(menu.size() > 0){
                        System.out.println("Digite o prato para Removê-lo.");
                        String nome = scan.next();
                        menu.remove(nome);
                    } else {
                        System.out.println("Não há nenhum item registrado no menu.");
                    }
                case 3:
                    controlador = false;
                    break;
            }

        }
        return menu;
    }
}
