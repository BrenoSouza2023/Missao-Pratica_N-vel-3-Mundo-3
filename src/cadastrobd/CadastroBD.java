package cadastrobd;



import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridicaDAO;
import java.sql.SQLException;

import java.util.List;
import java.util.Scanner;

public class CadastroBD {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        PessoaFisicaDAO pfDao = new PessoaFisicaDAO();
        PessoaJuridicaDAO pjDao = new PessoaJuridicaDAO();

        int opcao;
        do {
            System.out.println("Menu:");
            System.out.println("1. Incluir");
            System.out.println("2. Alterar");
            System.out.println("3. Excluir");
            System.out.println("4. Exibir pelo ID");
            System.out.println("5. Exibir todos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    incluir(scanner, pfDao, pjDao);
                    break;
                case 2:
                    alterar(scanner, pfDao, pjDao);
                    break;
                case 3:
                    excluir(scanner, pfDao, pjDao);
                    break;
                case 4:
                    exibirPeloId(scanner, pfDao, pjDao);
                    break;
                case 5:
                    exibirTodos(scanner, pfDao, pjDao);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void incluir(Scanner scanner, PessoaFisicaDAO pfDao, PessoaJuridicaDAO pjDao) throws SQLException {
        System.out.print("Tipo (1. Física / 2. Jurídica): ");
        int tipo = Integer.parseInt(scanner.nextLine());

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Logradouro: ");
        String logradouro = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        if (tipo == 1) {
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            PessoaFisica pf = new PessoaFisica(0, nome, logradouro, cidade, estado, telefone, email, cpf);
            pfDao.incluir(pf);
        } else if (tipo == 2) {
            System.out.print("CNPJ: ");
            String cnpj = scanner.nextLine();
            PessoaJuridica pj = new PessoaJuridica(0, nome, logradouro, cidade, estado, telefone, email, cnpj);
            pjDao.incluir(pj);
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void alterar(Scanner scanner, PessoaFisicaDAO pfDao, PessoaJuridicaDAO pjDao) throws SQLException {
        System.out.print("Tipo (1. Física / 2. Jurídica): ");
        int tipo = Integer.parseInt(scanner.nextLine());

        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (tipo == 1) {
            PessoaFisica pf = pfDao.getPessoa(id);
            if (pf != null) {
                System.out.println("Dados atuais: ");
                pf.exibir();

                System.out.print("Novo Nome: ");
                pf.setNome(scanner.nextLine());
                System.out.print("Novo Logradouro: ");
                pf.setLogradouro(scanner.nextLine());
                System.out.print("Nova Cidade: ");
                pf.setCidade(scanner.nextLine());
                System.out.print("Novo Estado: ");
                pf.setEstado(scanner.nextLine());
                System.out.print("Novo Telefone: ");
                pf.setTelefone(scanner.nextLine());
                System.out.print("Novo Email: ");
                pf.setEmail(scanner.nextLine());
                System.out.print("Novo CPF: ");
                pf.setCpf(scanner.nextLine());

                pfDao.alterar(pf);
            } else {
                System.out.println("Pessoa física não encontrada.");
            }
        } else if (tipo == 2) {
            PessoaJuridica pj = pjDao.getPessoa(id);
            if (pj != null) {
                System.out.println("Dados atuais: ");
                pj.exibir();

                System.out.print("Novo Nome: ");
                pj.setNome(scanner.nextLine());
                System.out.print("Novo Logradouro: ");
                pj.setLogradouro(scanner.nextLine());
                System.out.print("Nova Cidade: ");
                pj.setCidade(scanner.nextLine());
                System.out.print("Novo Estado: ");
                pj.setEstado(scanner.nextLine());
                System.out.print("Novo Telefone: ");
                pj.setTelefone(scanner.nextLine());
                System.out.print("Novo Email: ");
                pj.setEmail(scanner.nextLine());
                System.out.print("Novo CNPJ: ");
                pj.setCnpj(scanner.nextLine());

                pjDao.alterar(pj);
            } else {
                System.out.println("Pessoa jurídica não encontrada.");
            }
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void excluir(Scanner scanner, PessoaFisicaDAO pfDao, PessoaJuridicaDAO pjDao) throws SQLException {
        System.out.print("Tipo (1. Física / 2. Jurídica): ");
        int tipo = Integer.parseInt(scanner.nextLine());

        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (tipo == 1) {
            pfDao.excluir(id);
        } else if (tipo == 2) {
            pjDao.excluir(id);
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void exibirPeloId(Scanner scanner, PessoaFisicaDAO pfDao, PessoaJuridicaDAO pjDao) throws SQLException {
        System.out.print("Tipo (1. Física / 2. Jurídica): ");
        int tipo = Integer.parseInt(scanner.nextLine());

        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (tipo == 1) {
            PessoaFisica pf = pfDao.getPessoa(id);
            if (pf != null) {
                pf.exibir();
            } else {
                System.out.println("Pessoa física não encontrada.");
            }
        } else if (tipo == 2) {
            PessoaJuridica pj = pjDao.getPessoa(id);
            if (pj != null) {
                pj.exibir();
            } else {
                System.out.println("Pessoa jurídica não encontrada.");
            }
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void exibirTodos(Scanner scanner, PessoaFisicaDAO pfDao, PessoaJuridicaDAO pjDao) throws SQLException {
        System.out.print("Tipo (1. Física / 2. Jurídica): ");
        int tipo = Integer.parseInt(scanner.nextLine());

        if (tipo == 1) {
            List<PessoaFisica> pessoas = pfDao.getPessoas();
            for (PessoaFisica pf : pessoas) {
                pf.exibir();
            }
        } else if (tipo == 2) {
            List<PessoaJuridica> pessoas = pjDao.getPessoas();
            for (PessoaJuridica pj : pessoas) {
                pj.exibir();
            }
        } else {
            System.out.println("Tipo inválido.");
        }
    }
}
