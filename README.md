# ANALISADOR LÉXICO

### Autores:
Gustavo Ast Mariano - RA: 99843
Pedro da Cruz Chagas - RA: 94028

## Passos para executar:

1) Abrir o terminal na raiz do projeto e executar o comando `java -jar target/Analisador-Lexico-1.0.jar`
2) Informar o caminho do arquivo `.txt` contexto o código fonte

## Formato de saída dos Tokens e da Tabela de Chaves

Os tokens serão retornados no formato `<TIPO_DO_TOKEN, "VALOR">`, sequencialmente conforme o código fonte está escrito, sendo separados por vírgula.

A tabela de chaves será uma lista, onde o índice dos identificadores será o índice da própria lista e o valor do mesmo será o nome do identificador em si. Ambos serão retornados pelo analisador no seguinte formato: `INDICE -> VALOR`.