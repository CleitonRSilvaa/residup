# ResidUP - A inovação que faltava no seu dia a dia!

[PROJETO INTEGRADOR III - Turma B]

## Introdução
O sistema foi desenvolvido para melhorar a comunicação entre os serviços do condomínio e os moradores do prédio, proporcionando uma experiência mais segura e organizada.
Ele é composto por três recursos:
* Controle de visitantes, permitindo que os moradores façam um pré-cadastro de visitantes para que apenas pessoas autorizadas tenham acesso às dependências do edifício.
* Gestão de reservas de áreas, como salões de festas e playgrounds, evitando conflitos de planejamento.
* Controle de ocorrências de morador para o administrador (possivelmente, síndico) permitindo que qualquer problema seja relatado e resolvido com maior velocidade e eficiência.


Utilizando essas funcionalidades, o sistema visa disponibilizar um ambiente mais harmônico e seguro para todos os moradores do condomínio.

## Pré-requisitos
Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
[Git](https://git-scm.com), [JAVA SDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).
 Além disto é bom ter um editor para trabalhar com o código como [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=windows)

## Instalação

- Git bash
##### Clone este repositório
 git clone https://github.com/CleitonRSilvaa/residup.git

- IntelliJ
1.	Iniciar o IntelliJ;
2.	Abra um novo projeto, selecionando a pasta do repositório;
3.	Clique no botão “Load” para ativar o carregamento do Maven;

 ![image](https://user-images.githubusercontent.com/88353387/234722458-0ac41803-7429-4ec7-9895-ab4dc7a86eff.png)

4.	Após o download, será apresentado as funções Maven no canto superior direito;
5.	Executar o comando Plugins/tomcat7/tomcat7:run

![image](https://user-images.githubusercontent.com/88353387/234722479-125a4dc5-28cc-4d75-9816-e8cc0ff13b6f.png)

6.	Após a execução, abrir a URL http://localhost:8080/ no seu navegador;
7.	Antes de fazer a execução do projeto na WEB, acessar a URL http://localhost:8080/console 
8.	Com o console aberto, informar a senha “sa”;
9.	Acessar o diretório residup/docs/modelo_DER_MER/ScriptTables_DB_H2.txt e copiar o texto;
10.	Informe o script na caixa de texto do banco, e clique em “Run”;
11.	Execute o projeto;

## Contribuição
## Autoria e suporte
Backend:
- Ana Carolina Guimarães / a.carolina1903@gmail.com
- Cleiton Ribeiro da Silva / cleiton_ribeiro@live.com
- Thiago Soares Bellico / thiagobellico@Outlook.com 

Frontend:
- Anderson Rodrigues de Souza / Andersonrodirgues@hotmail.com 
- Barbara Vitoria dos Santos Silva / barbaravitoria.sts@gamil.com
- Daniel Xavier Rodrigues / contato_danielrodrigues@outlook.com

## Referências
Algumas referências retiramos de alguns sites como; 

Getbootstrap.

Links: https://getbootstrap.com/docs/5.3/getting-started/introduction/

Utilizamos o Google para adicionar algumas fotos no projeto. 

https://www.google.com/search?q=sala+para+reuni%C3%A3o+em+sp&source=lnms&tbm=isch&sa=X&ved=2ahUKEwiyhaXf28b-AhXxANQKHW_3A9EQ_AUoAXoECAEQAw&biw=1366&bih=657&dpr=1

O Canva e o CorelDraw foi utilizado para o desenvolvimento da Logo e imagens.

https://www.canva.com/ 

https://www.coreldraw.com/br/free-trials/#:~:text=%C3%89%20necess%C3%A1ria%20conex%C3%A3o%20com%20a,conte%C3%BAdo%20e%20recursos%20on%2Dline. 

Todos os participantes colaboraram com o conhecimento adquirido ao  decorrer do curso SUPERIOR DE TECNOLOGIA EM ANÁLISE E DESENVOLVIMENTO DE SISTEMAS. 
## Funcionalidades
Para a versão morador:
1.    Reserva de áreas – salao de festas, churrasqueira, etc.
2.    Controle de acesso de visitantes
3.    Registro de ocorrências de problemas do apartamento (enviados para síndico)

Para a versão síndico:
1.    Reserva de áreas – relatório por ambiente 
2.    Controle de acesso de visitantes – lista de nomes dos visitantes permitidos por morador
3.    Registro de ocorrências de problemas do apartamento (enviados para síndico) – Lista de problemas por morador (formulário enviado)
## Telas 
## LOGO PRINCIPAL
![LOGO_2](https://user-images.githubusercontent.com/73199083/223888565-1238bf38-0241-404e-87d1-cac695306e4d.png)

## Prototipação HighFidelity
### Landing page
![tela inicial](https://user-images.githubusercontent.com/125705404/225486445-67bae7d1-1ae3-49a6-91d4-f0a64793911b.png)

### Telas Gerais
![tela_geral](https://user-images.githubusercontent.com/104398217/225482557-9819a548-7fda-4224-83fe-6af1a873bc10.png)
Telas Gerais

![tela_geral_dois](https://user-images.githubusercontent.com/104398217/225482627-5113b475-b720-43a8-b494-fec3df36425c.png)
Tela Login

## Versão Morador
![reserva_area_morador](https://user-images.githubusercontent.com/104398217/225482756-bf743d3d-3a92-44e9-b816-c60fdebcf013.png)
Reservar Área

![controle_visitantes_morador_high](https://user-images.githubusercontent.com/104398217/225482811-cf97cced-1dcf-49e9-a2bc-007e038deaf5.png)
Controle de Visitantes

![registro_ocorrencia](https://user-images.githubusercontent.com/104398217/225482867-5c74d129-7e82-41af-9e65-45e12ffb452f.png)
Registro de Ocorrências

![perfil_morador high](https://user-images.githubusercontent.com/104398217/225482928-21301aca-0fb7-4242-903c-04019a74be6f.png)
Perfil do Morador

### Prototipação LowFidelity
![controle_visitantes_morador](https://user-images.githubusercontent.com/104398217/225441172-7ef65f16-8093-4845-ad1a-eb9d42603749.png)
Controle De Visitantes do Morador

![controle_visitantes_sindico](https://user-images.githubusercontent.com/104398217/225441255-0d21b456-04d9-432b-a656-97b334491feb.png)
Controle de Visitantes do Síndico

![login_geral](https://user-images.githubusercontent.com/104398217/225441358-3bdb20c0-0b58-46a0-bf5d-260df15dd82a.png)
Login Geral

![ocorrencias_morador](https://user-images.githubusercontent.com/104398217/225441382-ad0ba460-11ab-4941-a751-387bead266a3.png)
Ocorrências do Morador

![ocorrencias_sindico](https://user-images.githubusercontent.com/104398217/225441403-5d07b841-466e-4203-9967-dd51f02d9df3.png)
Ocorrências do Síndico

![perfil_morador](https://user-images.githubusercontent.com/104398217/225441426-321aec87-ec2f-4866-87f8-47b4a82c6b2f.png)
Perfil do Morador

![reserva_morador](https://user-images.githubusercontent.com/104398217/225441453-4eefba0c-c67d-4102-b835-54245ac1b32d.png)
Reserva do Morador

![reservas_sindico](https://user-images.githubusercontent.com/104398217/225441470-a4c14b5b-e030-4ab8-af1d-96c83443da6c.png)
Reserva do Síndico


## DER e MER

![DER_ResidUP](https://user-images.githubusercontent.com/104398217/225476492-666b9120-322d-407d-a6b9-f35ea6a99c1a.jpg)
![MER_ResidUP_](https://user-images.githubusercontent.com/104398217/225476512-37dc69c7-f7a0-425a-9b29-8f1e01bf1b72.png)




