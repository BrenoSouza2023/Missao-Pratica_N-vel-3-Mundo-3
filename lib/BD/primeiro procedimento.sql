CREATE TABLE pessoajuridica (
  idpessoajuridica INTEGER NOT NULL,
  cnpj VARCHAR(20),
  quantidade INTEGER,
  valorunitario NUMERIC,
  PRIMARY KEY(idpessoajuridica)
);

CREATE TABLE produtos (
  idprodutos INTEGER NOT NULL,
  pdt_nome VARCHAR(20),
  pdt_quantidade INTEGER,
  pdt_preco DECIMAL,
  PRIMARY KEY(idprodutos)
);

CREATE TABLE pessoa (
  idpessoa INTEGER NOT NULL,
  pessoajuridica_idpessoajuridica INTEGER NOT NULL,
  nome VARCHAR(45),
  cpf_cnpj VARCHAR(20),
  pf_logradouro VARCHAR(20),
  contato VARCHAR(20),
  PRIMARY KEY(idpessoa),
  FOREIGN KEY(pessoajuridica_idpessoajuridica) REFERENCES pessoajuridica(idpessoajuridica)
);

CREATE TABLE pessoafisica (
  pessoa_idpessoa INTEGER NOT NULL,
  cpf VARCHAR(20),
  precovenda NUMERIC,
  PRIMARY KEY(pessoa_idpessoa),
  FOREIGN KEY(pessoa_idpessoa) REFERENCES pessoa(idpessoa)
);

CREATE TABLE usuarios (
  idusuarios INTEGER NOT NULL,
  pessoajuridica_idpessoajuridica INTEGER NOT NULL,
  usuarios VARCHAR(20),
  senha VARCHAR(6),
  PRIMARY KEY(idusuarios),
  FOREIGN KEY(pessoajuridica_idpessoajuridica) REFERENCES pessoajuridica(idpessoajuridica)
);

CREATE TABLE movimento (
  idmovimento INTEGER NOT NULL,
  usuarios_idusuarios INTEGER NOT NULL,
  pessoa_idpessoa INTEGER NOT NULL,
  produtos_idprodutos INTEGER NOT NULL,
  quantidade INTEGER,
  tipo VARCHAR(20),
  valor DECIMAL,
  PRIMARY KEY(idmovimento),
  FOREIGN KEY(usuarios_idusuarios) REFERENCES usuarios(idusuarios),
  FOREIGN KEY(pessoa_idpessoa) REFERENCES pessoa(idpessoa),
  FOREIGN KEY(produtos_idprodutos) REFERENCES produtos(idprodutos)
);



CREATE SEQUENCE PessoaIDSeq
    START WITH 1
    INCREMENT BY 1
    NO CYCLE;

