# Calendar

Uma API para controle de rotina do dia a dia.

### Endpoints

- Eventos 
    - Cadastrar
    - Atualizar
    - Listar todos
    - Mostrar detalhes
    - Apagar
- Usuário
    - Cadastrar
    - Atualizar
    - Listar todos
    - Mostrar detalhes
    - Apagar


### Cadastrar Evento
`POST`/api/evento

|Campo|tipo|Obrigatório|descrição
|------ |------|:-----------: |---------
|Nome|text|sim|Nome do evento para poder indentificar.
|Horário| time | sim | Horário em que vai acontecer o evento.
|Lembrete| text | não | Mandar uma notificação de quando irá acontecer um determinado evento.
|usuário| text | sim | usuário em que vai ficar salvo o evento.

**Exemplo de corpo de requisição**
```js
{
  "nome": "Médico",
  "data": "09-03-2023",
  "horario": "15:30",
  "lembrete": "5 minutos antes",
  "user": "rm95628@fiap.com.br"
}

```

**Exemplo de corpo da respota**

**Cógigos de respota**

|código| descrição
| - | -
|200 | dados cadastrado com sucesso
|400 | os campos enviados sao invalidos

---------------------------------------------------------------------

### Atualizar Evento
`UPDATE`/api/evento/{id}

**Exemplo de corpo de requisição**
```js
{
  "nome": "Médico",
  "data": "09-03-2023",
  "horario": "15:30",
  "lembrete": "5 minutos antes",
  "user": "rm95628@fiap.com.br"
}

```
**Cógigos de respota**

|código| descrição
| - | -
|201 | dados atualizados com sucesso
|401 | não existe dado com o id informado

---------------------------------------------------------------------

### Listar todos
**Exemplo de corpo de requisição**
```js
{
  "nome": "Médico",
  "data": "09-03-2023",
  "horario": "15:30",
  "lembrete": "5 minutos antes",
  "user": "rm95628@fiap.com.br"
}
{
  "nome": "Dentista",
  "data": "19-02-2023",
  "horario": "12:30",
  "lembrete": "1 dia antes",
  "user": "rm95628@fiap.com.br"
}
```

**Cógigos de respota**

|código| descrição
| - | -
|202 | dados retornados com sucesso
|402 | não existe evento cadastrado

---------------------------------------------------------------------


### Detalhar Evento
`GET`/api/evento/{id}       
```js
{
  nome: 'Médico',
  data: '09-03-2023',
  horario: '15:30',
  lembrete: '5 minutos antes',
  conta: {
          conta_id: 1,
          conta_email: 'rm95628@fiap.com.br'
  }
}
```
**Cógigos de respota**

|código| descrição
| - | -
|202 | dados retornados com sucesso
|401 | não existe dado com o id informado

---------------------------------------------------------------------



### Apagar evento
`DELETE`/api/evento/{id}

|código| descrição
| - | -
|203 | dado apagado com sucesso
|401 | não existe dado com o id informado


---------------------------------------------------------------------

### Cadastrar Usuário
`POST`/api/usuario

|Campo|tipo|Obrigatório|descrição
|------ |------|:-----------: |---------
|Nome de Usuario|text|sim|Nome que identicará o usuário e também poderá ser utilizado para login
|Email| text | sim | email do usuário para efetuar o login
|Senha| text | sim | senha do usuário para efetuar o login

**Exemplo de corpo de requisição**
```js
{
  user: 'Fernando.Cesar',
  email: 'rm95628@fiap.com.br',
  senha: '12345abc'
}
```

**Exemplo de corpo da respota**

**Cógigos de respota**

|código| descrição
| - | -
|203 | conta cadastrada com sucesso
|400 | os campos enviados sao invalidos

---------------------------------------------------------------------

### Atualizar Usuario
`UPDATE`/api/usuario/{id}

**Exemplo de corpo de requisição**
```js
{
  user: 'FernandoCesxr',
  email: 'rm95628@fiap.com.br',
  senha: '1234'
}
```
**Cógigos de respota**

|código| descrição
| - | -
|200 | dados atualizados com sucesso
|401 | não existe dado com o id informado

---------------------------------------------------------------------



### Listar todos
`GET`/api/usuario
**Exemplo de corpo de requisição**
```js
{
  user: 'FernandoCesxr',
  email: 'rm95628@fiap.com.br',
  senha: '1234'
}
{
  user: 'Joao Pedro',
  email: 'rm956538@fiap.com.br',
  senha: '5r0JO9JW8&sV'
}

```


**Cógigos de respota**

|código| descrição
| - | -
|202 | dados retornados com sucesso
|403 | não existe usuario cadastrado


---------------------------------------------------------------------


### Detalhar usuário
`GET`/api/usuario/{id}       
```js
{
  user: 'Fernando.Cesar',
  email: 'rm95628@fiap.com.br',
  senha: '12345abc'
}
```

**Cógigos de respota**

|código| descrição
| - | -
|202 | dados retornados com sucesso
|402 | não existe dado com o id informado

---------------------------------------------------------------------

### Apagar usuario
`DELETE`/api/usuario/{id}

|código| descrição
| - | -
|203 | dado apagado com sucesso
|401 | não existe dado com o id informado





