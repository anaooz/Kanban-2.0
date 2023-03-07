# Kanban-2.0
Repositório pro projeto de Digital Business Enablement

---

## Endpoints

- Usuário
  - [Cadastrar](#cadastrar-usuário)
  - [Editar](#editar-usuário)
  - [Deletar](#deletar-usuário)

- Quadro
  - [Adicionar](#adicionar-quadro)
  - [Editar](#editar-quadro)
  - [Excluir](#excluir-quadro)
  - [Lista](#lista-de-quadros)

---
### Cadastrar Usuário
`POST` /api/usuario/cadastrar

**Campos da requisição**

| Campo | Tipo | Obrigatório | Descrição | 
|-------|------|-------------|-----------|
| login |String|  Sim | Aqui o usuário deverá preencher o campo com o seu e-mail. | 
| senha |String|  Sim | Aqui o usuário deverá preencher o campo com uma senha de 6 dígitos. | 

 Corpo de requisição 

```js
{
    login: 'brunasilva@email.com',
    senha: '205478'
}
```

 Códigos de resposta 
| Código | Descrição | 
|--------|------|
|200|Dados retornados com sucesso|
|400| Campos não preenchidos|

---

### Editar Usuário
`PUT` /api/usuario/{id}/editar

 **Corpo de requisição**

```js
{
    login: 'brunasouza@email.com',
    senha: '215579'
}
```

 Códigos de resposta 
| Código | Descrição | 
|--------|------|
|200|dados alterados com sucesso|
|400|campos não preenchidos|
|404|usuário não encontrado|

---

### Deletar Usuário
`DELETE` /api/usuario/{id}/deletar

 Códigos de resposta 
| Código | Descrição | 
|--------|------|
|200|usuário excluído com sucesso|
|404|usuário não encontrado|

---

### Adicionar Quadro

`POST` api/quadro

**Campos da requisição**

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|---
|titulo | string | sim | o título da nota
|colaborador(es) | string | não | caso haja pessoas relacionadas à tarefa
|data|data|sim| a data limite para realização da tarefa
|cor |string |sim | o código de uma conta previamente cadastrada

**Exemplo de corpo de requisição**

```js
{
  titulo: 'Exemplo de Nota',
  colaborador: ['mateus@email.com', 'amanda@email.com'],
  data: '05-03-2023',
  cor: 'vermelho'
}
```

**Códigos de Resposta**

| código | descrição
|-|-
| 201 | quadro cadastrado com sucesso
| 400 | campos não preenchidos
---

### Editar Quadro

`PUT` api/quadro/{id}/editar

**Exemplo de corpo de requisição**

```js
{
  titulo: 'Exemplo de Nota 1.1',
  colaborador: ['mateus@email.com', 'amanda@email.com', 'joao@email.com'],
  data: '06-03-2023',
  cor: 'laranja'
}
```

**Códigos de Resposta**

| código | descrição
|-|-
| 202 | quadro alterado com sucesso
| 400 | campos não preenchidos ou faltando
| 404 | nenhum quadro com o id informado
---

### Excluir Quadro

`DELETE` api/quadro/{id}/excluir

**Códigos de Resposta**

| código | descrição
|-|-
| 202 | quadro excluído com sucesso
| 404 | nenhum quadro com o id informado
---

### Lista de Quadros

**Campos da requisição**

`GET` /api/quadro/lista

**Exemplo de corpo de requisição**
```js
{
    valor: 'Exemplo de Nota',
    colaboradores: [{
		 colaborador: 'mateus@email.com'
	}, {
		 colaborador: 'amanda@email.com'
	}],
    data: '05-03-2023',
    cor: 'vermelho'
}
```
```js
{
    valor: 'Exemplo de Nota 2',
    colaboradores: 'joao@email.com',
    data: '01-03-2023',
    cor: 'azul'
}
```
**Códigos de Resposta**

| código | descrição
|-|-
| 200 | dados retornados com sucesso
| 404 | nenhum quadro encontrado
