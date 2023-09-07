package chap07.AutoDeb;/*
package chap07.AutoDeb;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CardNumberValidator {
    public CardValidity validate(String cardNumber){
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://some-external-pg.com/card"))
                .header("Content-Type", "text/plain")
                .POST(HttpRequest.BodyPublishers.ofString(cardNumber))
                .build();
        try{
            HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
            switch (response.body()){
                case "ok": return CardValidity.VALID;
                case "bad": return CardValidity.INVALID;
                case "expired": return CardValidity.EXPIRED;
                case "theft" : return CardValidty.THEFT;
                default: return CardValidity.UNKNOWN;
            }
        }catch (IOException | InterruptedException e){
            return CardValidity.ERROR;
        }
    }
}
*/