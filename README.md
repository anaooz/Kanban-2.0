# Kanban-2.0
Repositório pro projeto de Digital Business Enablement

---

## Endpoints

- Quadro
  - [Adicionar](#adicionar-quadro)
  - [Editar](#editar-quadro)
  - [Excluir](#excluir-quadro)
  - [Lista](#lista-de-quadros)

---

### Adicionar Quadro

**Campos da requisição**

`POST` api/quadro

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
| 201 | quadro cadastrada com sucesso
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
    colaboradores: 'joao@email.com'
    data: '01-03-2023',
    cor: 'azul'
}
```
**Códigos de Resposta**

| código | descrição
|-|-
| 200 | dados retornados com sucesso
| 404 | nenhum quadro encontrado
