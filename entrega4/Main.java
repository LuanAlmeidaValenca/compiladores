import quinas.lexer.*;
import quinas.parser.*;
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
            
            // Instancia o Lexer
            Lexer lexer = new Lexer(pbr);
            
            // Instancia o Parser passando o Lexer
            Parser parser = new Parser(lexer);

            System.out.println("=== Início da Análise Sintática ===");
            
            // Inicia a análise. Se o código fonte tiver erro de sintaxe, 
            // este método lança uma ParserException.
            Start arvore = parser.parse();
            
            System.out.println("=== Análise Sintática Concluída com Sucesso ===");

        } catch (LexerException e) {
            System.err.println("\n[ERRO LÉXICO]: " + e.getMessage());
        } catch (ParserException e) {
            System.err.println("\n[ERRO SINTÁTICO]: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("\n[ERRO DE LEITURA]: Não foi possível ler o arquivo: " + e.getMessage());
        }
    }
}