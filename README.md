# Petstore Rest Assured

Projeto de testes automatizados de API construído com **Rest Assured** + **JUnit 5**, tendo como alvo a [Swagger Petstore](https://petstore.swagger.io/#/) — API pública de demonstração usada como estudo de caso em um processo de mentoria de QA.

O objetivo deste repositório é praticar e consolidar conceitos essenciais de automação de testes de API: estruturação de projeto, camada de serviço, massa de dados, tratamento de respostas, e diagnóstico de comportamentos inesperados em uma API real.

---

## 🧰 Tecnologias utilizadas

- **Java 11+**
- **Maven**
- **Rest Assured 6.x**
- **JUnit 5 (Jupiter)**
- **Hamcrest** (matchers de assertion)
- **Jackson Databind** (serialização/deserialização de POJOs)

---

## ✅ Pré-requisitos

- JDK 11 ou superior instalado (`java -version`)
- Maven instalado (`mvn -version`)
- IDE de sua preferência (recomendado: IntelliJ IDEA)

---
**Responsabilidade de cada camada:**

| Pasta | O que contém |
|---|---|
| `config` | Leitura do `.properties` (URL base, timeouts). |
| `models` | POJOs que representam o corpo das requisições/respostas (`Pet`, `User`, etc.). |
| `utils` | Fábricas de massa de dados e configuração reutilizável de requisição. |
| `services` | Camada que conhece os endpoints e monta as chamadas (Service Object Pattern). |
| `base` | Setup comum herdado por todas as classes de teste. |
| `tests` | Cenários de teste, organizados por domínio (`Pet`, `Store`, `User`). |

---

## ⚙️ Configuração

O arquivo `src/test/resources/config.properties` centraliza a URL base da API e outras configurações:

```properties
base.url=https://petstore.swagger.io/v2
request.timeout=5000
api.key=special-key
```

Nenhuma URL ou valor de configuração deve ser hardcoded diretamente nas classes de teste — tudo passa pelo `ConfigReader`.

---

## ▶️ Como executar os testes

Baixar as dependências e validar a build:
```bash
mvn clean install
```

Rodar toda a suíte de testes:
```bash
mvn clean test
```

Rodar uma classe específica:
```bash
mvn test -Dtest=PetApiTest
```

Os relatórios de execução ficam disponíveis em `target/surefire-reports` após rodar via Maven.

---

## ⚠️ Limitações conhecidas da API de demonstração

A Swagger Petstore é uma **API pública compartilhada**, usada por várias pessoas ao mesmo tempo, e possui comportamentos que não seguem estritamente a especificação REST, é importante ter em mente ao interpretar falhas:

- Alguns endpoints de escrita (`updatePetWithForm`, `deletePet`) podem retornar `200 OK` mesmo para IDs que já não existem, pois o servidor não valida existência do recurso antes de processar.
- Dados podem não ser persistidos de forma consistente entre chamadas (comportamento eventual, não transacional).
- Por isso, sempre que possível, os testes validam o **efeito colateral real** (ex.: uma busca `GET` após a ação) em vez de confiar apenas no status code retornado pela própria operação.
