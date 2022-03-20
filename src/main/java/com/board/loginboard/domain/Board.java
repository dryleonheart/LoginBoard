package com.board.loginboard.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "board")      //제목, 내용, 작성자만 있는 가장 기본적인 구조
public class Board extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String owner;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;


    @Builder
    public Board(Long id, String title, String content, String owner){
        Assert.hasText(owner, "owner is empty");
        Assert.hasText(title, "title is empty");
        Assert.hasText(content, "content is empty");

        this.id = id;
        this.owner = owner;
        this.title = title;
        this.content = content;
    }

}
