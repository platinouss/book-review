package com.platinouss.bookrecommend.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchBookRes {

    private String lastBuildDate;    // 검색 결과를 생성한 시간이다.
    private int total;  // 검색 결과 문서의 총 개수를 의미한다.
    private int start;  // 검색 결과 문서 중, 문서의 시작점을 의미한다.
    private int display;    // 검색된 검색 결과의 개수이다.
    private List<SearchBookItem> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchBookItem {
        private String title;           // 검색 결과 문서의 제목을 나타낸다. 제목에서 검색어와 일치하는 부분은 태그로 감싸져 있다.
        private String link;            // 검색 결과 문서의 하이퍼텍스트 link를 나타낸다.
        private String image;           // 썸네일 이미지의 URL이다.
        private String author;          // 저자 정보이다.
        private int discount;           // 할인 가격 정보이다. 절판도서 등으로 가격이 없으면 나타나지 않는다.
        private String publisher;       // 출판사 정보이다.
        private long isbn;               // ISBN 넘버이다.
        private String description;     // 검색 결과 문서의 내용을 요약한 패시지 정보이다. 문서 전체의 내용은 link를 따라가면 읽을 수 있다. 패시지에서 검색어와 일치하는 부분은 태그로 감싸져 있다.
        private String pubdate;  // 출간일 정보이다.
    }

}
