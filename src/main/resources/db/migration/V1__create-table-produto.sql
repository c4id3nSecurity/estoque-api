CREATE SEQUENCE IF NOT EXISTS produtos_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE IF NOT EXISTS produtos (
    id BIGINT NOT NULL DEFAULT nextval('produtos_id_seq'::regclass),
    nome VARCHAR(100) NOT NULL,
    quantidade INTEGER NOT NULL,
    preco NUMERIC(19,2) NOT NULL,
    
    CONSTRAINT pk_produtos PRIMARY KEY (id)
);