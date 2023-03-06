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
  colaborador: 'Mateus',
  data: '05-03-2023',
  cor: 'vermelho'
}
```
