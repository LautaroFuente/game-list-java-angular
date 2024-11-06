package com.game_list.game_list.service;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.game_list.game_list.api.GameApiResponse;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class GameImageService {

    private final WebClient.Builder webClientBuilder;
    
    private static final String IMAGE_DIRECTORY = "src/main/resources/static/images/";

    @Autowired
    public GameImageService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public String getImageUrl(String gameName) {
        String apiUrl = "https://api.rawg.io/api/games";

        try {
            return webClientBuilder.baseUrl(apiUrl)
                    .build()
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("key", "YOUR_API_KEY")
                            .queryParam("page_size", "1")
                            .queryParam("search", gameName)
                            .build())
                    .retrieve()
                    .bodyToMono(GameApiResponse.class)
                    .map(response -> {
                        if (response != null && response.getResults() != null && !response.getResults().isEmpty()) {
                            return response.getResults().get(0).getBackgroundImage();
                        }
                        return null;
                    })
                    .block();
        } catch (WebClientResponseException ex) {

            ex.printStackTrace();
            return null;
        }

    }
    
    public String downloadImage(String imageUrl, String gameName) throws IOException {

        byte[] imageBytes = webClientBuilder.baseUrl(imageUrl)
                .build()
                .get()
                .retrieve()
                .bodyToMono(byte[].class) 
                .block();

        if (imageBytes != null) {
            String imageName = gameName.replaceAll("\\s+", "_").toLowerCase() + ".jpg";
            Path imagePath = Paths.get(IMAGE_DIRECTORY, imageName);

            Files.write(imagePath, imageBytes);

            return imagePath.toString();
        }

        return null;
    }

    public String saveDefaultImage() throws IOException {

        Path defaultImagePath = Paths.get(IMAGE_DIRECTORY, "broken_image.jpg");
        return defaultImagePath.toString();
    }
    
    
}
