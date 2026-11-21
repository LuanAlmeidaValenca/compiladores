import quinas.lexer.*;
import quinas.parser.*;
import quinas.node.*;
import quinas.analysis.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Uso: java Main <caminho_para_arquivo.qui>");
            return;
        }

        String caminhoArquivo = args[0];

        try {
            InputStreamReader isr = new InputStreamReader(
                new FileInputStream(caminhoArquivo), 
                StandardCharsets.UTF_8
            );
            PushbackReader pbr = new PushbackReader(new BufferedReader(isr), 1024);
            
            Lexer lexer = new Lexer(pbr);
            Parser parser = new Parser(lexer);

            System.out.println("=== Início da Análise Sintática ===");
            Start arvore = parser.parse();
            System.out.println("=== Análise Sintática Concluída com Sucesso ===\n");

            System.out.println("=== Árvore Sintática Abstrata (AST) ===");
            arvore.apply(new ImpressorAST());

        } catch (LexerException e) {
            System.err.println("\n[ERRO LÉXICO]: " + e.getMessage());
        } catch (ParserException e) {
            System.err.println("\n[ERRO SINTÁTICO]: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("\n[ERRO DE LEITURA]: Não foi possível ler o arquivo: " + e.getMessage());
        }
    }

    // Percorre a AST inteira e imprime cada nó indentado
    static class ImpressorAST extends DepthFirstAdapter {
        private int nivel = 0;

        private void imprimirIndentacao() {
            for (int i = 0; i < nivel; i++) {
                System.out.print("  ");
            }
        }

        @Override
        public void defaultIn(Node node) {
            imprimirIndentacao();
            System.out.println(node.getClass().getSimpleName());
            nivel++;
        }

        @Override
        public void defaultOut(Node node) {
            nivel--;
        }

        @Override
        public void defaultCase(Node node) {
            if (node instanceof Token && !(node instanceof EOF)) {
                Token token = (Token) node;
                imprimirIndentacao();
                System.out.println(token.getClass().getSimpleName() + ": \"" + token.getText() + "\"");
            }
        }
    }
}