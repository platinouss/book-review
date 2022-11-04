package com.platinouss.bookrecommend.domain;

import com.platinouss.bookrecommend.domain.enums.Gender;
import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_history_id")
    private Long id;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
