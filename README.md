# Kanban-2.0
Repositório pro projeto de Digital Business Enablement

---

## Endpoints

- Quadro
  - (Adicionar)[#adicionar-quadro]
  - Editar
  - Apagar
  - Lista

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
  colaborador: ['Mateus', 'Amanda'],
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

### Detalhar Despesa

**Campos da requisição**

`GET` /api/quadro/lista

**Exemplo de corpo de requisição**
```js
{
    valor: 'Exemplo de Nota',
    colaboradores: [{
		 colaborador: 'Mateus'
	}, {
		 colaborador: 'Amanda'
	}],
    data: '05-03-2023',
    cor: 'vermelho'
}
```
```js
{
    valor: 'Exemplo de Nota 2',
    colaboradores: 'João Carlos Lima'
    data: '01-03-2023',
    cor: 'azul'
}
```
**Códigos de Resposta**

| código | descrição
|-|-
| 200 | dados retornados com sucesso
| 404 | nenhum quadro encontrado
