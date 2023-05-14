# Electoral System

Brazil Federal elections emulator.

## Build and run

This project uses `gradle`. The default gradle rules apply. For instance:

- `gradle build` to build the .jar at `./build/libs/`
- `gradle test` to execute whatever test suites we have at the moment

After this point, everything is old docs and might not be accurate
---

## Como utilizar


OBS:

- O sistema já vem inicializado com 2 candidatos a presidente e 3 a deputado federal
- O sistema já vem com os dois gestores (de sessão e de candidaturas)
- O sistema já vem com todos os eleitores possíveis para utilizá-los basta checar o arquivo `voterLoad.txt`

No menu inicial para gerenciar candidatos e eleição siga pela opção 2:

- User: `emp` , Password: `12345` -> Cadastro e remoção de candidatos da eleição
- User: `cert` , Password: `54321` -> Inicialização/finalização da eleição (liberar pra poder votar) e mostrar o resultado ao final da eleição.

Além da senha de usuário é necessário a senha da eleição para completar operações relacionadas a gestão da eleição ou candidatos. Essa senha é a palavra `password`

Para votar também existe um eleitor com o título de eleitor nº 123456789012 que pode votar nos candidatos pré-cadastrados

## Execução teste

Para uma execução teste podemos seguir o seguinte passo:

- Ao iniciar a aplicação selecionar a opção 2 e logar com o user `cert`
- Escolher a opção 1 e inserir a senha da urna (`password`) para iniciar a votação
- Escolher a opção 0 para voltar ao menu inicial
- Escolher votar (opção 1) e inserir o nº `123456789012` do eleitor de teste
- Selecionar sim e votar respectivamente `123` , `12345` e `br`
- Escolher votar (opção 1) e inserir o nº `268888719264` (outro eleitor de teste)
- Selecionar sim e votar respectivamente `123` , `54321` e `12345`
- Escolher votar (opção 1) e inserir o nº `638991919941` (outro eleitor de teste)
- Selecionar sim e votar respectivamente `000` , `12345` e `00000`
- Escolher votar (opção 1) e inserir o nº `965575671024` (outro eleitor de teste)
- Selecionar sim e votar respectivamente `123` , `12345` e `00000`
- No menu inicial, selecionar a opção 2 e logar com o user `cert`
- Escolher a opção 2 e inserir a senha da urna (`password`) para encerrar a votação
- Escolher a opção 3 e inserir a senha da urna (`password`) para mostrar o resultado final da votação
- Escolher a opção 0 duas vezes para encerrar a aplicação
