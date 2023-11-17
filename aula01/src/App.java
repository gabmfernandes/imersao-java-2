import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import static java.lang.String.valueOf;


public class App {
    public static void main(String[] args) throws IOException, InterruptedException {

        //Fazer uma conexão HTTP para pegar o top 250 filmes do Imdb
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";

        HttpClient cliente = HttpClient.newHttpClient();
        URI endereco = URI.create(url);
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        // Extrair apenas os dados que interessam (titulo, poster, classificação)
        Gson gson = new Gson();
        Items items = gson.fromJson(body, Items.class);
        List<Filme> listaFilmes = items.items();

        //exibir e manipular os dados

        for (Filme filme: listaFilmes) {
            System.out.println("\n\nTítulo: " + filme.title());
            System.out.println("Poster: " + filme.image());
            int nota = (int) Double.parseDouble(filme.imDbRating());
            System.out.println("Classificação: " + filme.imDbRating());
            for (int i = 0; i<nota;i++){
                System.out.print("\u2B50");
            }

        }
    }
}

