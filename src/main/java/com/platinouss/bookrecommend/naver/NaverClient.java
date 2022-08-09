package com.platinouss.bookrecommend.naver;

import com.platinouss.bookrecommend.naver.dto.SearchBookReq;
import com.platinouss.bookrecommend.naver.dto.SearchBookRes;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class NaverClient {

    @Value("${naver.client.id}")
    private String naverClientId;

    @Value("${naver.client.secret}")
    private String naverClientSecret;

    @Value("${naver.url.search.book}")
    private String naverBookUrl;

    public SearchBookRes localSearch(SearchBookReq searchBookReq) {
        URI uri = UriComponentsBuilder.fromUriString(naverBookUrl)
                .queryParams(searchBookReq.toMuiltiValueMap())
                .build()
                .encode()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverClientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ParameterizedTypeReference<SearchBookRes> responseType = new ParameterizedTypeReference<>(){};

        ResponseEntity<SearchBookRes> responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );

        return responseEntity.getBody();
    }
}
