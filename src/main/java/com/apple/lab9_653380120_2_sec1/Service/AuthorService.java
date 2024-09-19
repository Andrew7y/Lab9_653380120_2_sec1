package com.apple.lab9_653380120_2_sec1.Service;

import com.apple.lab9_653380120_2_sec1.Model.AuthorDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class AuthorService {
    private final RestTemplate restTemplate;
    private String baseUrl = "http://localhost:8085/api/authors";

    public AuthorService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AuthorDTO getAuthorById(Long id) {
        ResponseEntity<AuthorDTO> response = restTemplate.exchange(
                baseUrl + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<AuthorDTO>() {}
        );
        return response.getBody();
    }

    public List<AuthorDTO> getAllAuthors(){
        ResponseEntity<List<AuthorDTO>> response = restTemplate.exchange(
                baseUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AuthorDTO>>() {}
        );
        return response.getBody();
    }

    public void createAuthor(AuthorDTO author){
        restTemplate.postForObject(
                baseUrl,
                author,
                AuthorDTO.class
        );
    }

    public void updateAuthor(Long id, AuthorDTO author){
        restTemplate.put(
                baseUrl+"/"+id,
                author
        );
    }

    public void deleteAuthor(Long id){
        restTemplate.delete(
                baseUrl+"/"+id,
                AuthorDTO.class
        );
    }

}
