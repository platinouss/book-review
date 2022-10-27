package com.platinouss.bookrecommend.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchBookReq {

    private String query = "";
    private int display = 10;
    private int start = 1;
    private String sort = "sim";

    public MultiValueMap<String, String> toMuiltiValueMap() {
        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

        map.add("query", query);
        map.add("display", String.valueOf(display));
        map.add("start", String.valueOf(start));
        map.add("sort", sort);

        return map;
    }
}
