package com.board.loginboard.controller;

import com.board.loginboard.dto.BoardDto;
import com.board.loginboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// 게시판 관련 Rest Api
@RestController
@RequestMapping("/api/board")
@CrossOrigin("http://localhost:3000")  // CORS 문제 처리
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 검색용 API -  제목에 searchWord를 포함하는 게시글 검색
    @GetMapping("/search")
    public List<BoardDto> search(@RequestParam(value="searchWord") String searchWord){
        return boardService.searchBySearchWord(searchWord);
    }

    // 전체 게시글 검색 API - FrontEnd에서 페이지 구분및 배치 할 경우
    @GetMapping("")
    public List<BoardDto> fullList(){
        return boardService.getBoardList();
    }


    // 게시글 페이지 별 검색 - BackEnd에서 페이지 구분 해주는 경우
    @GetMapping("/list")
    public List<BoardDto> list(@RequestParam(value="page", defaultValue = "1") int pageNum){
        return boardService.getBoardListByPage(pageNum);
    }

    // 게시글 총 페이지 수 조회 - BackEnd에서 페이지 구분 해주는 경우
    @GetMapping("/pageList")
    public int pageList(@RequestParam(value="page", defaultValue = "1") int pageNum){
        return boardService.getPageList(pageNum);
    }

    // 작성된 게시글 저장
    @PostMapping("/write")
    public ResponseEntity write(BoardDto boardDto) throws Exception{
        boardService.save(boardDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    // 게시글 상세 ID를 Key로 이용
    @GetMapping("/post/{id}")
    public BoardDto page(@PathVariable("id") Long id) {
        return boardService.getPost(id);
    }

    // 게시글 수정 및 저장은 Owner와 로그인 사용자 동일인일 경우만 Front에서 허용
    // 게시글 수정 후 저장
    @PostMapping("/post/edit/{id}")
    public ResponseEntity edit(BoardDto boardDto) throws Exception{
        boardService.save(boardDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    // 게시글 삭제
    @DeleteMapping("/post/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        boardService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
