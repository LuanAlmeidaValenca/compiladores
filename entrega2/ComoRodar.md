# Tutorial

## 1 Java

Para testar se o arquivo $grupo_2.sable$ está funcional, precisamos do Java instalado na máquina para executar o $main.java$. Caso não tenha certeza se possui ele intalado, execute no terminal:

```bash
javac --version
```

- Caso retorne a versão (Ex: $javac 21.0.x$), está tudo certo
- Caso contrário, faça download do JDK 17 ou superior: <https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html>
- Para finalizar, compile o java com:

```bash
javac quinas/lexer/*.java quinas/node/*.java Main.java
```

## 2. Sable

Preferências para o sable, escolha se deseja usar a extensão do VScode (vá para 1.1) ou não (vá para 1.2)

### 2.1 Com extensão

- No VScode, vá para a aba de extensões e procure por

```bash
sable-helper
```

- Feito isso, ao abrir o arquivo $grupo_2.sable$, você conseguirá ver um botão de play na parte superior direita do seu VScode, ao passar o mouse por cima, deve aparecer "Rodar SableCC"
- Caso tudo esteja certo, dentro do arquivo, irá aparecer dentro de $/entrega2/$ uma pasta chamada $/quinas$
- Feito isso, pode passar para a etapa 3

### 2.2 Sem extensão

- Primeiramente, entre no link a seguir para instalar o arquivo compactado do sable: <https://sourceforge.net/projects/sablecc/files/NZDIS%20OQL/>
- Extraia o conteúdo do arquivo
- Navegue até \sablecc-3.7\lib/
- Copie o arquivo sablecc.jar e cole em /entrega2/
- Para executar o $grupo_2.sable$:

```bash
java -jar sablecc.jar grupo_2.sable
```

Este comando será responsável por criar a pasta $/entrega2/quinas/$ a partir do $.sable$ usado

## 3 Testar o Léxico

Feito as etapas anteriores basta editar o arquivo $teste.qui$ ou criar outro com a extensão $.qui$, salvar e executar o comando:

```bash
java Main <nome-do-arquivo.qui>
```

Deverá aparecer no terminal o início da análise léxica e o resultado com base em cada conteúdo adicionado no $.qui$
