# Electoral System

Brazil Federal elections emulator.

## Build and run

This project uses `gradle`. The following gradle rules are actively used:

- `gradle fatjar` to build the .jar at `./build/libs/`
- `gradle test` to execute whatever test suites we have at the moment

## Example run

```shell
>> gradle fatjar
>> java -jar ./build/libs/software-reuse-tw.jar federal
Certified Professional login

Insira seu usuário:
cert
Insira sua senha:
1
(1) Iniciar sessão
(2) Finalizar sessão
(3) Mostrar resultados
(0) Sair
1
Insira a senha da urna
pass
Sessão inicializada


(1) Iniciar sessão
(2) Finalizar sessão
(3) Mostrar resultados
(0) Sair
0
Insira seu título de eleitor:
123456789012

- 'nulo' para votar nulo
- 'br' para votar em branco


Vote para Presidente: 
123

Vote para Deputado Federal: 
12345

Vote para Deputado Federal: 
54321

Insira seu título de eleitor:

Eleitor não encontrado.
Certified Professional login

Insira seu usuário:
cert
Insira sua senha:
1
(1) Iniciar sessão
(2) Finalizar sessão
(3) Mostrar resultados
(0) Sair
2
Insira a senha da urna:
pass
Sessão finalizada com sucesso

=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=


(1) Iniciar sessão
(2) Finalizar sessão
(3) Mostrar resultados
(0) Sair
3
Insira a senha da urna
pass

Resultado da eleição:

Vencedores
---------------------------------------------------------------------
|Tipo            |Localização     |Número          |Votos           |
|Deputado Federal|MG              |54321           |1               |
|Presidente      |                |123             |1               |
---------------------------------------------------------------------


---------------------------------------------------------------------
|Tipo            |Localização     |Número          |Votos           |
|Deputado Federal|MG              |54321           |1               |
|Deputado Federal|MG              |12345           |1               |
|Presidente      |                |123             |1               |
---------------------------------------------------------------------




(1) Iniciar sessão
(2) Finalizar sessão
(3) Mostrar resultados
(0) Sair

```

## Preconfigured users

Use `cert` to login as a certified professional. Use as password the digit
`1`. The _Urna_ password is `pass`.

The files `examples/*.txt` define all the voters loaded by the Federal, Municipal and Universidade plugins. Please use one of those when trying out one of these
plugins.
