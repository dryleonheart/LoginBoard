package com.board.loginboard.service;

import com.board.loginboard.domain.Board;
import com.board.loginboard.dto.BoardDto;
import com.board.loginboard.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    private static final int POST_PER_PAGE = 10;
    private static final int PAGE_PER_BLOCK = 5;

    private BoardDto convertEntityToDto(Board board){
        return BoardDto
                .builder()
                .id(board.getId())
                .owner(board.getOwner())
                .title(board.getTitle())
                .content(board.getContent())
                .createdDate(board.getCreatedDate())
                .modifiedDate(board.getModifiedDate())
                .build();
    }

    @Transactional
    public List<BoardDto> searchBySearchWord(String searchWord){
        List<Board> boardList = boardRepository.findByTitleContaining(searchWord);
        List<BoardDto> boardDtoList = new ArrayList<>();

        if(!boardList.isEmpty()){
            for(Board board : boardList){
                boardDtoList.add(this.convertEntityToDto(board));
            }
        }

        return boardDtoList;
    }

    @Transactional
    public List<BoardDto> getBoardList(){
        List<Board> boardList = boardRepository.findAll();

        List<BoardDto> boardDtoList = new ArrayList<>();

        if(!boardList.isEmpty()){
            for(Board board : boardList){
                boardDtoList.add(this.convertEntityToDto(board));
            }
        }
        return boardDtoList;
    }

    @Transactional
    public List<BoardDto> getBoardListByPage(int pageNum){
        Page<Board> page = boardRepository.findAll(PageRequest.of(pageNum - 1, POST_PER_PAGE, Sort.by(Sort.Direction.ASC, "createdDate")));

        List<Board> boardList = page.getContent();
        List<BoardDto> boardDtoList = new ArrayList<>();

        if(!boardList.isEmpty()){
            for(Board board : boardList){
                boardDtoList.add(this.convertEntityToDto(board));
            }
        }
        return boardDtoList;
    }

    @Transactional
    public Long getBoardCount() {
        return boardRepository.count();
    }

    public int getPageList(int pageNum){
        return (int)(Math.ceil( ( Double.valueOf(this.getBoardCount())/POST_PER_PAGE ) ) );
    }

    @Transactional
    public BoardDto getPost(Long id){
        Optional<Board> boardWrap = boardRepository.findById(id);
        if(boardWrap.isEmpty())return new BoardDto();

        Board board = boardWrap.get();
        return  this.convertEntityToDto(board);
    }

    @Transactional
    public Long save(BoardDto boardDto) throws Exception{
        return boardRepository.save(boardDto.toEntity()).getId();
    }

    @Transactional
    public void delete(Long id){
        boardRepository.deleteById(id);
    }

}
