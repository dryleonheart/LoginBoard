package com.board.loginboard.dto;


import com.board.loginboard.domain.Board;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private String owner;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Board toEntity(){
        return Board
                .builder()
                .id(id)
                .owner(owner)
                .title(title)
                .content(content)
                .build();
    }

    @Builder
    public BoardDto(Long id, String owner,  String title, String content, LocalDateTime createdDate, LocalDateTime modifiedDate){
        this.id= id;
        this.owner = owner;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
