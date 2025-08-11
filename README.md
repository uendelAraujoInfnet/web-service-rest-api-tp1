# web-service-rest-api-tp1
# Math API (Spring Boot) 🚀

API REST **sem estado** para operações matemáticas básicas: **adição**, **subtração**, **multiplicação**, **divisão** e **exponenciação**.  
Todos os endpoints aceitam **GET** (query params) e **POST** (JSON) com o **mesmo comportamento**.

> Inclui **site HTML de testes** 🌐 para você exercutar via navegador.

---

## 🧰 Pré‑requisitos

- **Java 21** ☕ (Temurin/Adoptium recomendado)
- **Maven** 3.9+ **ou** o **Maven Wrapper** do projeto (`mvnw` / `mvnw.cmd`)
- Porta **8080** livre (ou configure outra)

---

## ⬇️ Baixar/Clonar e Abrir

1. Extraia ou clone o projeto em uma pasta local.
2. Abra no **IntelliJ IDEA**: *File → Open…* e selecione a pasta que contém o `pom.xml`.
3. Confirme o **Project SDK = 21** (*File → Project Structure → Project*).

---

## ▶️ Como executar

### Windows (PowerShell)
```powershell
# executar testes
.\mvnw.cmd clean test

# subir a aplicação
.\mvnw.cmd spring-boot:run
```

### Linux/macOS
```bash
# executar testes
./mvnw clean test

# subir a aplicação
./mvnw spring-boot:run
```

A aplicação inicia em: **http://localhost:8080**

> Se preferir usar o Maven instalado no sistema, troque `./mvnw` por `mvn` e `.\mvnw.cmd` por `mvn`.

---

## 🌐 Testar via navegador — **Site HTML**

Abra **http://localhost:8080/math-tester.html**

1. Escolha a **operação** (add, subtract, multiply, divide, pow).
2. Escolha o **método** (GET/POST).
3. Informe `a` e `b`. A página aceita **vírgula** (`1,23`) **ou** **ponto** (`1.23`) — ela converte automaticamente.
4. Clique em **Executar**.
5. Veja a **Request** enviada e a **Response** recebida, formatadas.

Dicas:
- Se você configurou um **context-path**, por exemplo `server.servlet.context-path: /tp1`, a página continuará funcionando.
- Caso tenha sua própria página HTML, use esta lógica para detectar o context-path automaticamente antes de montar as URLs:
  ```html
  <script>
    function computeBase() {
      const path = location.pathname;               // p.ex.: "/tp1/math-tester.html" ou "/math-tester.html"
      const ctx  = path.replace(/\/[^\/]*$/, "");   // remove o último segmento -> "/tp1" ou ""
      return location.origin + ctx + "/api/v1/math";
    }
    const base = computeBase();
  </script>
  ```

---

## 🔗 Endpoints

**Base path:** `/api/v1/math`

### GET (query params)
```
/add?a=1.5&b=2.25
/subtract?a=5&b=3
/multiply?a=2&b=4
/divide?a=10&b=3
/pow?a=2&b=3   ← expoente inteiro (aceita negativo)
```

### POST (JSON) — mesmo corpo para todas
```json
{ "a": 10, "b": 3 }
```
Rotas:
```
POST /add
POST /subtract
POST /multiply
POST /divide
POST /pow
```

### Exemplos rápidos (clique/cole no navegador para GET)
- Soma: `http://localhost:8080/api/v1/math/add?a=1.5&b=2.25`
- Subtração: `http://localhost:8080/api/v1/math/subtract?a=5&b=3`
- Multiplicação: `http://localhost:8080/api/v1/math/multiply?a=2&b=4`
- Divisão: `http://localhost:8080/api/v1/math/divide?a=10&b=3`
- Potência: `http://localhost:8080/api/v1/math/pow?a=2&b=3`

> **Erros tratados (400)**: divisão por zero, JSON malformado, parâmetros ausentes ou inválidos e expoente não-inteiro em `pow`.

---

## ⚙️ Configuração

`src/main/resources/application.yml`
```yaml
server:
  port: 8080

spring:
  web:
    resources:
      add-mappings: true   # mantém Swagger e /math-tester.html acessíveis

app:
  math:
    scale: 2
    rounding: HALF_UP
```
- **scale**: casas decimais dos resultados (`BigDecimal`)
- **rounding**: modo de arredondamento (`HALF_UP`, etc.)

---

## 🧪 Testes automatizados

Rode:
```bash
./mvnw clean test
```
Os testes usam **MockMvc** e cobrem: soma, potência com expoente negativo e erro de divisão por zero.

---

## 🧭 Estrutura do projeto

```
src/
  main/
    java/com/example/mathapi/
      config/
        MathProperties.java           # binds de app.math.* (scale/rounding)
        BigDecimalConverter.java      # aceita vírgula/ponto em query params
      controller/
        MathController.java           # endpoints GET/POST
      handler/
        GlobalExceptionHandler.java   # erros padronizados (400/500)
      service/
        MathService.java              # regras de negócio com BigDecimal
      MathApiApplication.java
    resources/
      application.yml
      static/
        math-tester.html              # página web de testes
  test/java/com/example/mathapi/
    MathApiApplicationTests.java
```

---

## 🛠️ Solução de problemas (FAQ)

- **404 ao abrir `/math-tester.html` ou Swagger**
    - Confira se `spring.web.resources.add-mappings: true` está no `application.yml`.
    - Verifique se a página está em `src/main/resources/static/math-tester.html`.
    - Se você usa `server.servlet.context-path: /tp1`, as URLs ficam:
        - Página: `http://localhost:8080/tp1/math-tester.html`
        - API: `http://localhost:8080/tp1/api/v1/math/...`

- **404 nos endpoints**
    - A base correta é `/api/v1/math` (respeitando o **context-path**, se houver).
    - Garanta que `MathApiApplication` e `controller` estão sob o **mesmo pacote raiz** `com.example.mathapi` (o `@SpringBootApplication` precisa “ver” os controllers).

- **500 – Erro interno** ao enviar números com vírgula ou JSON faltando campos
    - Use a página HTML (ela **converte vírgula→ponto** automaticamente).
    - Para POST, envie JSON com `Content-Type: application/json`.
    - O handler global converte erros de entrada em **400**; se persistir, veja o log do console para a exceção exata.

- **Porta 8080 ocupada**
    - Mude a porta em `server.port` ou finalize o processo que está usando a porta.

---

## 🧩 Extras úteis

- Ver árvore de dependências:
  ```bash
  ./mvnw dependency:tree
  ```
- Ver atualizações disponíveis:
  ```bash
  ./mvnw versions:display-dependency-updates
  ./mvnw versions:display-plugin-updates
  ```

---

Feito com ❤️ para facilitar seus testes e sua apresentação! 😉
