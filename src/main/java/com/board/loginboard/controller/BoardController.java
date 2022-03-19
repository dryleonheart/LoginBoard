package com.board.loginboard.controller;

import com.board.loginboard.dto.BoardDto;
import com.board.loginboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
@CrossOrigin("http://localhost:3000")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/search")
    public List<BoardDto> search(@RequestParam(value="searchWord") String searchWord){
        return boardService.searchBySearchWord(searchWord);
    }

    @GetMapping("")
    public List<BoardDto> fullList(){
        return boardService.getBoardList();
    }

    @GetMapping("/list")
    public List<BoardDto> list(@RequestParam(value="page", defaultValue = "1") int pageNum){
        return boardService.getBoardListByPage(pageNum);
    }

    @GetMapping("/pageList")
    public List<Integer> pageList(@RequestParam(value="page", defaultValue = "1") int pageNum){
        return boardService.getPageList(pageNum);
    }

    @PostMapping("/write")
    public ResponseEntity write(BoardDto boardDto){
        boardService.save(boardDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    public BoardDto page(@PathVariable("id") Long id) {
        return boardService.getPost(id);
    }

    @GetMapping("/post/edit/{id}")
    public BoardDto edit( @PathVariable("id") Long id) {
        return boardService.getPost(id);
    }

    @PostMapping("/post/edit/{num}")
    public ResponseEntity edit(BoardDto boardDto){
        boardService.save(boardDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        boardService.delete(id);

        return new ResponseEntity(HttpStatus.OK);
    }

}
