package com.platinouss.bookrecommend.domain;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Authority extends BaseEntity implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
    private Long id;

    private String authority;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
