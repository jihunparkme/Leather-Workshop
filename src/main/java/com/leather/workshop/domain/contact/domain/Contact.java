package com.leather.workshop.domain.contact.domain;

import com.leather.workshop.global.common.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Contact extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 50)
    private String email;

    @Column(length = 20, nullable = false)
    private String phoneNumber;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 20000, nullable = false)
    private String contents;

    @Builder
    public Contact(String name, String email, String phoneNumber, String title, String contents) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.title = title;
        this.contents = contents;
    }
}