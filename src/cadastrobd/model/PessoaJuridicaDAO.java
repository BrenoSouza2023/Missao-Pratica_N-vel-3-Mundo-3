package cadastrobd.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import cadastro.model.util.ConectorBD;
import cadastro.model.util.SequenceManager;

public class PessoaJuridicaDAO {

    public PessoaJuridica getPessoa(int id) {
        String sql = "SELECT * FROM pessoa p JOIN pessoa_juridica pj ON p.idpessoa = pj.pessoa_idpessoa WHERE p.idpessoa = ?";
        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmt = ConectorBD.getPrepared(conn, sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new PessoaJuridica(
                        rs.getInt("idpessoa"),
                        rs.getString("nome"),
                        rs.getString("logradouro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("cnpj")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<PessoaJuridica> getPessoas() {
        List<PessoaJuridica> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM pessoa p JOIN pessoa_juridica pj ON p.idpessoa = pj.pessoa_idpessoa";
        try (Connection conn = ConectorBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                pessoas.add(new PessoaJuridica(
                        rs.getInt("idpessoa"),
                        rs.getString("nome"),
                        rs.getString("logradouro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("cnpj")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoas;
    }

    public void incluir(PessoaJuridica pj) {
        String sqlPessoa = "INSERT INTO pessoa (idpessoa, nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlPessoaJuridica = "INSERT INTO pessoa_juridica (pessoa_idpessoa, cnpj) VALUES (?, ?)";
        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmtPessoa = ConectorBD.getPrepared(conn, sqlPessoa);
             PreparedStatement stmtPessoaJuridica = ConectorBD.getPrepared(conn, sqlPessoaJuridica)) {
            int id = SequenceManager.getValue("PessoaIDSeq");
            stmtPessoa.setInt(1, id);
            stmtPessoa.setString(2, pj.getNome());
            stmtPessoa.setString(3, pj.getLogradouro());
            stmtPessoa.setString(4, pj.getCidade());
            stmtPessoa.setString(5, pj.getEstado());
            stmtPessoa.setString(6, pj.getTelefone());
            stmtPessoa.setString(7, pj.getEmail());
            stmtPessoa.executeUpdate();

            stmtPessoaJuridica.setInt(1, id);
            stmtPessoaJuridica.setString(2, pj.getCnpj());
            stmtPessoaJuridica.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterar(PessoaJuridica pj) {
        String sqlPessoa = "UPDATE pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE idpessoa = ?";
        String sqlPessoaJuridica = "UPDATE pessoa_juridica SET cnpj = ? WHERE pessoa_idpessoa = ?";
        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmtPessoa = ConectorBD.getPrepared(conn, sqlPessoa);
             PreparedStatement stmtPessoaJuridica = ConectorBD.getPrepared(conn, sqlPessoaJuridica)) {
            stmtPessoa.setString(1, pj.getNome());
            stmtPessoa.setString(2, pj.getLogradouro());
            stmtPessoa.setString(3, pj.getCidade());
            stmtPessoa.setString(4, pj.getEstado());
            stmtPessoa.setString(5, pj.getTelefone());
            stmtPessoa.setString(6, pj.getEmail());
            stmtPessoa.setInt(7, pj.getId());
            stmtPessoa.executeUpdate();

            stmtPessoaJuridica.setString(1, pj.getCnpj());
            stmtPessoaJuridica.setInt(2, pj.getId());
            stmtPessoaJuridica.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sqlPessoaJuridica = "DELETE FROM pessoa_juridica WHERE pessoa_idpessoa = ?";
        String sqlPessoa = "DELETE FROM pessoa WHERE idpessoa = ?";
        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmtPessoaJuridica = ConectorBD.getPrepared(conn, sqlPessoaJuridica);
             PreparedStatement stmtPessoa = ConectorBD.getPrepared(conn, sqlPessoa)) {
            stmtPessoaJuridica.setInt(1, id);
            stmtPessoaJuridica.executeUpdate();

            stmtPessoa.setInt(1, id);
            stmtPessoa.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}