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

=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=


(1) Iniciar sessão
(2) Finalizar sessão
(3) Mostrar resultados
(0) Sair
1
Insira a senha da urna
pass
Sessão inicializada

=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=


(1) Iniciar sessão
(2) Finalizar sessão
(3) Mostrar resultados
(0) Sair
0
Insira seu título de eleitor:
123456789012

=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=


Vamos começar!

OBS:
- 'nulo' para votar nulo
- 'br' para votar em branco


=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=


Vote para Presidente: 
123
ID not found, refreshing

=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=


Vote para Deputado Federal: 
12345
ID not found, refreshing

=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=


Vote para Deputado Federal: 
54321
ID not found, refreshing

=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=


Insira seu título de eleitor:

Eleitor não encontrado.
Certified Professional login

Insira seu usuário:
cert
Insira sua senha:
1

=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=


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
votes from plugin utils: {Deputado Federal_MG_54321=1, Deputado Federal_MG_12345=1, Presidente_123=1}

Resultado da eleição:

Vencedores
---------------------------------------------------------------------
|Tipo            |Localização     |Número          |Votos           |
|Deputado Federal|MG              |54321           |1               |
|Presidente      |MG              |123             |1               |
---------------------------------------------------------------------


---------------------------------------------------------------------
|Tipo            |Localização     |Número          |Votos           |
|Deputado Federal|MG              |54321           |1               |
|Deputado Federal|MG              |12345           |1               |
|Presidente      |MG              |123             |1               |
---------------------------------------------------------------------



=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=


(1) Iniciar sessão
(2) Finalizar sessão
(3) Mostrar resultados
(0) Sair
```

## Preconfigured users

Use `cert` to login as a certified professional. Use as password the digit
`1`. The _Urna_ password is `pass`.

The file `examples/voterLoad.txt` defines all the voters loaded by the Federal
and Municipal plugins. Please use one of those when trying out one of these
plugins.
