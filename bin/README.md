################################ EXPERIAN ################################ 

Esta API é responsavel pelo cadastro, exclusão e consulta de pessoas, bem como suas respectivas informaçoes de Afinidade e Score !



################################ ANOTAÇÕES ################################ 

################################ API EXTERNA ################################ 

API externa consulta CEP(ViaCEP) -> https://viacep.com.br/ws/{CEP}/json/


################################ BANCO DE DADOS ################################ 

Para o relacionamento de AFINIDADE com os estados criei a tabela ESTADO, onde ficará armazenado os estados e o id_afinidade que representa o relacionamento entre as tabelas.

O arquivo data.sql em src/resources foi criado para inicializar as tabelas no banco de dados.



################################ AUTENTICAÇÃO JWT ################################ 

Para a authenticação JWT utilizei o token cripitografado gerado no site -> jwt.io 

TOKEN = eyJhbGciOiJIUzM4NCIsInR5cCI6IkpXVCJ9.eyJwZXJmaWwiOiJBZG1pbiJ9.src2M46FdSqZ0HTZKllxop0DWHhaGlCjofGm_w7lLoCrwhLmwTlc90Z4uyJcrX09



################################ TESTES DE UNIDADE ################################ 

Para testar os metodos da service, foi necessario alterar o modificador de acesso para "public", desta forma foi possivel acessa-los da sua respectiva classe  de testes


################################ COLLECTION ################################

A collection para o consumo das rotas da API se encontra na pasta src/resources 



################################ SWAGGER ################################

Link da documentação da API gerada pelo Swagger -> http://localhost:8080/swagger-ui.html#/



################################ ACESSO AO BANCO DE DADOS ################################

Link para acesso ao console do banco de dados em memoria h2DB -> http://localhost:8080/h2-console



