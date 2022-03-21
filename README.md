# ppads_projeto

---
## OnlyBrowserGames - Especificação

| Integrantes do Grupo | T.I.A |
| -------------------- | ----- |
| Gabriel              | 32091613 |
| Felipe               | 32010443 |
| Arthur               | 32055781 |
| Victor               | 32048343 |


**indice**
- [1. Introdução](#1-introdução)
- [2. Interessados](#2-interessados)
- [3. Objetivos Funcionais](#3-objetivos-funcionais)
- [4. Objetivos Não Funcionais](#4-objetivos-não-funcionais)
- [5. Diagrama de Casos de Uso](#5-diagrama-de-casos-de-uso)
    - [5.1 Descrição Detalhada dos Casos de Uso Principais](#51-descrição-detalhada-dos-casos-de-uso-principais)
- [6. Protótipos de Tela](#6-protótipos-de-tela)
- [7. Modelo de Domínio](#7-modelo-de-domínio)
- [8. Lista de Decisões de Arquitetura com justificativas](#8-lista-de-decisões-de-arquitetura-com-justificativas)
- [9. Diagramas de Classes de Projeto](#9-diagramas-de-classes-de-projeto)
- [10. Diagramas de Sequência de Projeto](#10-diagramas-de-sequência-de-projeto)


# 1. Introdução

É fato que a comunidade gamer se expandiu para todas as direções e a web certamente se encaixa neste processo. Muitos jogos podem ser encontrados diretamente pela internet, sem a necessidade qualquer download e a ideia do projeto OnlyBrowserGames é juntar o útil ao agradável, isto é, coletar diversos jogos que não necessitem de instalações e sejam acessíveis diretamente pelo navegador.

OnlyBrowserGames tem o objetivo de criar uma comunidade em que a interação entre usuários é o foco. Os membros terão a possibilidade de compartilhar seus feedbacks dos browser games jogados. A plataforma coletará os dados e a partir destes será feita uma análise que permitirá a adaptação da plataforma para a melhor experiência do usuário.

# 2. Interessados
O tipo de usuário que se destina o OnlyBrowserGames são majoritariamente Crianças e Jovens,
entre 10 e 25 anos, que são cativados pelo mundo dos jogos. Eles jogam pelo prazer que os jogos trazem, junto aos seus desafios, objetivos e diversão.

A acessabilidade é um fator primordial para esses jogadores, em que, seja possível acessar o jogo tanto no Mobile quanto no Desktop ou Notebook.

As principais características que se destacam neles são, seu otimismo e persistência, porém tratando-se de organização, eles tendem a ser desorganizados.

# 3. Objetivos Funcionais
3.a - O sistema deverá permitir que o Administrador possa cadastrar os BrowserGames que serão avaliados pelos membros.

3.b - O sistema deverá permitir que o Administrador possa editar e criar a lista de categorias do BrowserGames.

3.c - O sistema deverá permitir que qualquer Usuário não-Membro possa se cadastrar e posteriormente atualizar seus dados de cadastro.

3.d - O sistema deverá permitir que o Membro possa buscar os BrowserGames.

3.e - O sistema deverá permitir que o Membro avalie o BrowserGame selecionado.

3.f - O sistema deverá permitir que o Membro veja uma lista de todos as avaliações feitas por outros Membros para o BrowserGame selecionado.

3.g - O sistena deverá permitir que o Membro possa 'curtir' a avaliação de outro Membro.

3.h - O sistema deverá permitir que o Membro solicite uma lista avaliações mais úteis do OnlyBrowserGames.

3.i - O sistema deverá permitir que o Membro visualize as recomemdações que o OnlyBrowserGames oferece.

3.j - O sistema deverá permitir que o Administrador obtenha relatórios.

# 4. Objetivos Não Funcionais
4.a - O sistema devera organizar os  browser games nas seguintes categorias iniciais: Strategy, Shooter, Puzzle, Arcade, Role Playing Game (RPG), Sports, Action, Adventure.

4.b - O sistema devera solicitar as seguintes informações para fazer o cadastro de um novo browser game:  nome, categoria (conforme as opções do item anterior), URL de acesso ao jogo, URL do vídeo de demonstração (se houver), descrição em 255 caracteres e imagem ilustrativa.

4.c - O membro devera ser capaz de fazer a busca de browser games pelo nome, por parte do nome ou busca pela categoria (ao selecionar uma das categorias cadastradas, é apresentada uma lista dos browser games desta categoria em ordem alfabética). 

4.d - O membro devera ser capaz de avaliar os browser games definindo quantas estrelas (de 1 a 5) dará para o jogo e escrevendo um texto de até 255 caracteres. Caso o membro já tenha avaliado o browser game anteriormente, ele visualizará as informações da avaliação que havia feito e terá a opção de alterá-las (afinal, pode ser que tenha mudado a sua opinião sobre o jogo).

4.e - O sistema devera disponibilizar a opção "Esta avaliação foi útil para mim"  nas avaliações de outros membros.

4.f - O membro devera receber a lista das avaliações mais úteis ordenadas e mostrando primeiro as que receberam mais marcações na opção "Esta avaliação foi útil para mim”.

4.g - O sistema devera ter um algoritmo para dar recomendações de acordo com o "gosto" do membro.
- As recomendações serão baseadas nos browser games das 2 categorias mais avaliadas do membro.
- Deverão ser exibidos apenas os browser games que ainda não foram avaliados por esse membro, e que a media de avaliações seja maior que 3.

4.h - O sistema devera disponibilizar os relatórios ao administrador organizados por um determinado período (ou seja, definindo as datas inicial e final) de operação do OnlyBrowserGames:
- 5 jogos que receberam maior número de avaliações; 
- 5 membros que realizaram o maior número de avaliações;
- 5 jogos que têm a maior nota média de avaliação (neste caso, só devem ser levados em conta os jogos que já receberam pelo menos 4 avaliações no período); 
- 3 categorias que receberam maior número de avaliações.

# 5. Diagrama de Casos de Uso
![CasosDeUso - BrowserGames - PPADS](https://user-images.githubusercontent.com/63924505/156226714-55e84999-ef7f-401f-a169-e68d50f619f4.png)
---
![casos de uso v2 - OnlyBrowserGames](https://user-images.githubusercontent.com/63924505/158256606-24f9a8a9-4e30-498b-ac06-c3644775e3b3.png)

## 5.1 Descrição Detalhada dos Casos de Uso Principais

**Nome do caso de uso:** Cadastrar como Membro

**Ator:** Usuário não-Membro

**Resumo:** Para que o usuário acesse o OnlyBrowserGames,  ele deverá efetuar o cadastro na plataforma, garantindo suas permissões como membro.

**Pré-condições:**
* O usuário não é membro.

**Pós-condições:**
* O usuário pode navegar e interagir na plataforma de acordo com seu nível de permissão. 

**Fluxo principal:** 

1. O usuário seleciona a operação para efetuar o login.
2. O sistema solicita o nome completo, username, senha, data de nascimento, estado e país.
3. O usuário fornece as informações solicitadas.
4. O sistema verifica se as informações inseridas não correspondem a nenhum outro usuário, permitindo então, o cadastro.
5. O sistema inicia uma sessão e mostra a tela de catálogo.


**Fluxos de exceção**

Passo 4:
* Se o *username* é já existe, o caso de uso retorna para o passo 2.
* Se a data de nascimento for maior que a data atual, o caso de uso retorna para o passo 2.

---

**Nome do caso de uso:** Buscar BrowserGames cadastrados

**Ator:** Membro

**Resumo:** O Membro pode navegar pelo BrowserGames a partir da Home Page ou barra de pesquisa.

**Pré-condições:**
* O usuário é Membro.

**Pós-condições:**
* O Membro pode utilizar o sistema de busca.

**Fluxo principal:** 

1. O sistema disponibiliza uma tela com o catálogo de BrowserGames cadastrados juntamente com a barra de pesquisa.
2. O Membro navega pela Home Page.
3. O Membro escolhe o BrowserGames que optar.
4. O sistema exibe o BrowserGames selecionado.

**Fluxos de Alternativo**

Passo 2:
* O Membro utiliza a barra de pesquisa buscando pelo nome do BrowserGames ou parte do mesmo, avançando para o passo 3.

Passo 2:
* O Membro opta procurar por categorias na barra de pesquisa(ao invéz de nomes) listando em ordem alfabética.

---

**Nome do caso de uso:** Avaliar BrowserGame

**Ator:** Membro

**Resumo:** O usuário terá a oportunidade de avaliar o BrowserGame cadastrado a partir de sua experiência no jogo.

**Pré-condições:**
* O Membro acessou a página do BrowserGame escolhido.

**Pós-condições:**
* O Membro poderá avaliar ou alterar sua avaliação.

**Fluxo principal:** 

1. O Membro acessa o BrowserGame escolhido.
2. O Membro poderá avaliá-lo definindo a quantidade de estrelas e escrever um comentário.
3. O Membro avalia o BrowserGame.
4. A sistema salva a avaliação.

**Fluxos alternativo**

Passo 3:
* Se o Membro já possuir uma avaliação, ele poderá altera-la, o caso de uso avança para o passo 4.

---

**Nome do caso de uso:** Visualizar todas as avaliações feitas no BroserGame selecionado

**Ator:** Membro

**Resumo:** O Membro visualiza as avaliações feitas pelos outros Membros.

**Pré-condições:**
* O Membro acessou a página do BrowserGame escolhido.

**Pós-condições:**
* O Membro poderá visualizar as avaliações de outros Membros.

**Fluxo principal:** 

1. O Membro acessa o BrowserGame escolhido.
2. O sistema exibe as avaliações de outros Membros.

**Fluxos alternativo**

Passo 2:
* Se o Membro achar que a avaliação do outro usuário foi útil, poderá marcar a opção *esta avaliação foi util pra mim*.

---

**Nome do caso de uso:** Visualizar Recomendações do OnlyBrowserGames

**Ator:** Membro

**Resumo:** A partir de um algoritimo, o sistema irá fazer uma analise do *gosto* do Membro baseada em suas avaliações, exibindo uma lista de recomendações para o Membro.

**Pré-condições:**
* O Membro está na Home Page.

**Pós-condições:**
* O Membro navega nas recomendações apresentadas.

**Fluxo principal:** 

1. O Membro acessa a Home Page do OnlyBrowserGames
2. O sistema exibe as recomendações baseadas em seu algoritimo.
3. O Membro navega na lista de recomendações do OnlyBrowserGames.

---

**Nome do caso de uso:** Cadastrar BrowserGame

**Ator:** Administrador OnlyBrowserGames

**Resumo:** O Administrador pode cadastrar um BrowserGame.

**Pré-condições:**
* O usuário é cadastrado como *Administrador*.

**Pós-condições:**
* O Administrador tem a possibilidade de cadastrar um BrowserGame no sistema.

**Fluxo principal:** 

1. O usuário seleciona a operação de login no OnlyBrowserGames como *Administrador*.
2. O usuário insere as informações para login.
3. O sistema verifica as informações do usuário e valida suas permissões como *Admin*.
4. O sistema fornece suas permissões como *Admin*
5. O Administrador cadastra um BrowseGame inserindo nome, categoria cadastrada, URL de acesso ao joogo, descrição de 255 caracteres e imagem ilustrativa.
6. o sistema salva e exibe no catálogo o BrowseGame.

**Fluxos alternativo**

Passo 4:
* Se o BrowseGame tiver um URL do vídeo de demonstração, ele deve ser inserido juntamente com as outras informações.

**Fluxo de exceção**

Passo 3:
* Se o usuário não possuir as permissões ou não inserir as informações corretas para login como Administrador, o caso de uso retorna para o passo 2.

Passo 5:
* Se o *Administrador* cadastrar um jogo já listado ou com informações incorretas, a ação será interrompida.

---

**Nome do caso de uso:** Gerenciar BrowserGames cadastrados

**Ator:** Administrador OnlyBrowserGames

**Resumo:** O *Administrador* tem a possibilidade de editar a lista do catálogo, gerenciando as categorias.

**Pré-condições:**
* O usuário é Admin.

**Pós-condições:**
* O *Administrador* pode fazer alterações de acordo com sua vontande.

**Fluxo principal:** 

1. O Administrador navega na tela de gerenciamento.
2. O sistema exibe as categorias cadastradas e possibilita a edição.
3. O Administrador adiciona ou altera categoria.
4. O sistema salva as alterações.

**Fluxos alternativo**

Passo 3:
* Se o *Administrador* optar por não realizar alterações, o caso de uso retorna para o passo 2.

# 6. Protótipos de Tela

**Cadastro como Membro**
![Cadastro como Membro](https://user-images.githubusercontent.com/64604413/158080995-baecf4e9-7e0a-4ee0-91c9-78e6b144708d.png)

---
**Página Principal**
![Página Principal](https://user-images.githubusercontent.com/64604413/158081051-0198a251-f204-47f2-9f11-dead416909d8.png)

---
**Atualizar Cadastro**

![Atualizar Cadastro](https://user-images.githubusercontent.com/64604413/158081069-997c3065-48ec-4683-b9d0-f51730be4037.png)

---
**Página BrowserGame**
![Página BrowserGame](https://user-images.githubusercontent.com/64604413/158081100-310944c2-3c78-4d18-bb09-21123850fce9.png)

---
**Página do Admin**
![Página do Admin](https://user-images.githubusercontent.com/64604413/158081117-53fdf1ab-6f1b-4321-be68-039c5ebd5e65.png)

# 7. Modelo de Domínio

![Modelo Dominio - BrowserGames](https://user-images.githubusercontent.com/64552267/158102064-b79963bc-b85a-4a48-ba8e-35d5eda8d6ac.png)

# 8. Lista de Decisões de Arquitetura com justificativas

**8.a Objetivos arquiteturais**
- O sistema deverá ser uma aplicação web, hospedada em um servidor na web;
- O sistema deverá ser compatível com os navegadores Google Chrome e Firefox;
- O sistema poderá ser acessado via dispositivos móveis;
- Os dados deverão ser persistidos em uma base da dados (NoSQL ou relacional).

**8.b Definição de componentes**

![Componentes - OnlyBrowserGames](https://user-images.githubusercontent.com/64552267/158095589-484c6e7e-1561-4cd9-95e9-809df1d19bc8.png)

**8.c Definição do modelo de dados**

![Dados - OnlyBrowserGames](https://user-images.githubusercontent.com/64552267/158267143-89ac5c07-861f-46d6-a056-ac20e413687c.png)

**8.d Tecnologias utilizadas**
- Java;
- Spring Framework;
- Typescript;
- Angular;
- Mongo DB (NoSQL);
- Heroku (Hospedagem em cloud).

**8.e Definiçao da implantação do sistema**

![Implantação - OnlyBrowserGames](https://user-images.githubusercontent.com/64552267/158266639-06e28481-1b17-4634-9fe8-118abc158dd9.png)

# 9. Diagramas de Classes de Projeto

![Classes - OnlyBrowserGames](https://user-images.githubusercontent.com/64552267/158266475-007dee31-1e8f-47aa-95ed-7f6b29719b0b.png)

# 10. Diagramas de Sequência de Projeto

![Cadastrar como Membro - OnlyBrowserGames](https://user-images.githubusercontent.com/63924505/158255998-d1e6ca4a-f2b7-4414-ad5e-d4a204403ff1.png)
---
![Atualizar Informações de Cadastro - OnlyBrowserGames](https://user-images.githubusercontent.com/63924505/158047874-1c12e0c2-f232-4ab9-b946-35ae0a1d12a4.png)
---
![Buscar BrowserGames cadastrados - OnlyBrowserGames](https://user-images.githubusercontent.com/63924505/158047882-7577a5f4-f2ca-4d8b-bcc1-ca5851e4f76c.png)
---
![Avaliar BrowserGame - OnlyBrowserGames](https://user-images.githubusercontent.com/63924505/158047911-c5d4c7c5-cafe-4310-8c13-cb7fbcbb0b54.png)
---
![Solicitar lista de avaliações mais curtidas!úteis - OnlyBrowserGames](https://user-images.githubusercontent.com/63924505/158047917-37ab867b-bbad-4890-8a3c-01fd99399bbe.png)
---
![Visualizar avaliações feitas no BorwserGame - OnlyBrowserGames](https://user-images.githubusercontent.com/63924505/158047920-b7d35136-3389-4d7e-95af-cb50854b39a2.png)
---
![Visualizar Recomendações - OnlyBrowserGames](https://user-images.githubusercontent.com/64552267/158079928-a7368bde-052d-4b3a-82c5-17f3d5f823d5.png)
---
![Cadastrar BrowserGames - OnlyBrowserGames](https://user-images.githubusercontent.com/64552267/158079988-fb5577be-3f95-40cf-9488-f5c41334aac9.png)
---
![Editar Categoria - OnlyBrowserGames](https://user-images.githubusercontent.com/64552267/158080026-678f955f-08e4-4e8a-8ae1-fe2265a62213.png)
---
![Adicionar categoria - OnlyBrowserGames](https://user-images.githubusercontent.com/64552267/158080051-83559a39-febc-4390-8d0a-6fedb413983d.png)
---
![Obter Relatório - OnlyBrowserGames](https://user-images.githubusercontent.com/64552267/158080115-60de4fa4-138e-41ff-b138-e297d4964b4a.png)
---
