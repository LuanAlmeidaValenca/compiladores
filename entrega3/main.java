import quinas.lexer.*;
import quinas.node.*;
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

            System.out.println("=== Início da Análise Léxica ===");
            Token token;
            
            // Percorre os tokens até atingir o EOF
            while (!((token = lexer.next()) instanceof EOF)) {
                String nomeToken = token.getClass().getSimpleName().replace("T", "");
                String lexema = token.getText();
                int linha = token.getLine();
                int coluna = token.getPos();

                System.out.printf("[%03d:%03d] %-20s : '%s'\n", linha, coluna, nomeToken, lexema);
            }
            
            System.out.println("=== Análise Léxica Concluída com Sucesso ===");

        } catch (LexerException e) {
            System.err.println("\n[ERRO LÉXICO]: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("\n[ERRO DE LEITURA]: Não foi possível ler o arquivo: " + e.getMessage());
        }
    }
}