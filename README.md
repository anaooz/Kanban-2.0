# Kanban-2.0
Repositório pro projeto de Digital Business Enablement

---

## Endpoints

- Quadro
  - Adicionar
  - Editar
  - Apagar
- Participantes / Administradores

---

### Cadastrar Quadro

`POST` api/quadro

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|---
|titulo | string | sim | o título da nota
|colaborador(es) | string | não | caso haja pessoas relacionadas à tarefa
|data|data|sim| a data limite para realização da tarefa
|cor |string |sim | o código de uma conta previamente cadastrada

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
---

### Detalhar Despesa

`GET` /api/quadro/{id}

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

**Códigos de Resposta**

| código | descrição
|-|-
| 200 | dados retornados com sucesso
| 404 | não existe quadro com o id informado
