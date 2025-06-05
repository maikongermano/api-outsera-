
# 🎬 API Outsera - Golden Raspberry Analyzer

API RESTful que analisa os vencedores da categoria **"Pior Filme"** do Golden Raspberry Awards, identificando os **produtores com maior e menor intervalo entre vitórias**.

---

## 🧰 Tecnologias Utilizadas

- ☕ Java 17  
- ⚙️ Spring Boot  
- 🌐 Spring Web + Spring Data JPA  
- 🧪 H2 Database (em memória)  
- 📂 OpenCSV  
- 📖 Swagger (Springdoc OpenAPI)

---

## 🚀 Como Executar o Projeto

1. **Clone o repositório:**
   ```bash
   git clone <seu-repositorio>
   cd api-outsera
   ```

2. **Execute a aplicação:**
   ```bash
   ./mvnw spring-boot:run
   ```

3. **Consulte o endpoint principal da API:**
   ```
   GET http://localhost:8080/produtores/intervalos
   ```

---

## 📊 Swagger - Documentação Interativa

Após iniciar a aplicação, acesse:

```
http://localhost:8080/swagger-ui.html
```

Você poderá visualizar e testar os endpoints da API diretamente via navegador.

---

## 🛢️ Console do Banco de Dados (H2)

Acesse a interface de administração do banco H2:

- 🔗 URL: [`http://localhost:8080/h2-console`](http://localhost:8080/h2-console)
- 🛠 JDBC URL: `jdbc:h2:mem:testdb`
- 👤 Usuário: `sa`
- 🔒 Senha: *(em branco)*

## 🧱 Estrutura de Arquitetura

O projeto segue os princípios da **Arquitetura Limpa**, com separação de responsabilidades e foco em escalabilidade e manutenibilidade.

```
src/main/java/com/outsera/api
├── ApiOutseraApplication.java      # Classe principal do Spring Boot
├── controller/                     # Controladores REST (interface com o cliente)
├── usecase/                        # Casos de uso / regras de negócio da aplicação
├── repository/                     # Interfaces JPA para acesso a dados
├── model/                          # Entidades e DTOs usados no domínio
└── initialization/                 # Carga automática de dados via CSV
```

### 🔍 Camadas explicadas:

- **controller/**: Endpoints expostos via HTTP com Spring MVC.
- **usecase/**: Contém a lógica de negócio principal da aplicação.
- **repository/**: Interfaces que acessam o banco usando Spring Data JPA.
- **model/**: Entidades que representam dados e objetos de transferência.
- **initialization/**: Scripts de boot, como a leitura de CSV ao iniciar o sistema.

Essa organização permite:
- Baixo acoplamento entre as camadas
- Alta testabilidade
- Manutenção facilitada e crescimento escalável
