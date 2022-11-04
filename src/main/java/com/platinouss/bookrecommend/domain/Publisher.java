package com.platinouss.bookrecommend.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Publisher extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();
}
