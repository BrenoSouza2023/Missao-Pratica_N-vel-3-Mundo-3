package cadastrobd.model;

import cadastro.model.util.ConectorBD;
import cadastro.model.util.SequenceManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaDAO {

    public PessoaFisica getPessoa(int id) {
        String sql = "SELECT * FROM pessoa p JOIN pessoa_fisica pf ON p.idpessoa = pf.pessoa_idpessoa WHERE p.idpessoa = ?";
        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmt = ConectorBD.getPrepared(conn, sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new PessoaFisica(
                        rs.getInt("idpessoa"),
                        rs.getString("nome"),
                        rs.getString("logradouro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("cpf")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<PessoaFisica> getPessoas() {
        List<PessoaFisica> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM pessoa p JOIN pessoa_fisica pf ON p.idpessoa = pf.pessoa_idpessoa";
        try (Connection conn = ConectorBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                pessoas.add(new PessoaFisica(
                        rs.getInt("idpessoa"),
                        rs.getString("nome"),
                        rs.getString("logradouro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("cpf")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoas;
    }

    public void incluir(PessoaFisica pf) {
        String sqlPessoa = "INSERT INTO pessoa (idpessoa, nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlPessoaFisica = "INSERT INTO pessoa_fisica (pessoa_idpessoa, cpf) VALUES (?, ?)";
        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmtPessoa = ConectorBD.getPrepared(conn, sqlPessoa);
             PreparedStatement stmtPessoaFisica = ConectorBD.getPrepared(conn, sqlPessoaFisica)) {
            int id = SequenceManager.getValue("PessoaIDSeq");
            stmtPessoa.setInt(1, id);
            stmtPessoa.setString(2, pf.getNome());
            stmtPessoa.setString(3, pf.getLogradouro());
            stmtPessoa.setString(4, pf.getCidade());
            stmtPessoa.setString(5, pf.getEstado());
            stmtPessoa.setString(6, pf.getTelefone());
            stmtPessoa.setString(7, pf.getEmail());
            stmtPessoa.executeUpdate();

            stmtPessoaFisica.setInt(1, id);
            stmtPessoaFisica.setString(2, pf.getCpf());
            stmtPessoaFisica.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterar(PessoaFisica pf) {
        String sqlPessoa = "UPDATE pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE idpessoa = ?";
        String sqlPessoaFisica = "UPDATE pessoa_fisica SET cpf = ? WHERE pessoa_idpessoa = ?";
        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmtPessoa = ConectorBD.getPrepared(conn, sqlPessoa);
             PreparedStatement stmtPessoaFisica = ConectorBD.getPrepared(conn, sqlPessoaFisica)) {
            stmtPessoa.setString(1, pf.getNome());
            stmtPessoa.setString(2, pf.getLogradouro());
            stmtPessoa.setString(3, pf.getCidade());
            stmtPessoa.setString(4, pf.getEstado());
            stmtPessoa.setString(5, pf.getTelefone());
            stmtPessoa.setString(6, pf.getEmail());
            stmtPessoa.setInt(7, pf.getId());
            stmtPessoa.executeUpdate();

            stmtPessoaFisica.setString(1, pf.getCpf());
            stmtPessoaFisica.setInt(2, pf.getId());
            stmtPessoaFisica.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sqlPessoaFisica = "DELETE FROM pessoa_fisica WHERE pessoa_idpessoa = ?";
        String sqlPessoa = "DELETE FROM pessoa WHERE idpessoa = ?";
        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmtPessoaFisica = ConectorBD.getPrepared(conn, sqlPessoaFisica);
             PreparedStatement stmtPessoa = ConectorBD.getPrepared(conn, sqlPessoa)) {
            stmtPessoaFisica.setInt(1, id);
            stmtPessoaFisica.executeUpdate();

            stmtPessoa.setInt(1, id);
            stmtPessoa.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}