package com.example.demo.controller;

import com.example.demo.dto.BoardDto;
import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {
    private final BoardService boardService;
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
    @GetMapping
    public List<BoardDto> getAllBoards(){
        return boardService.findAllBoards();
    }
    @PostMapping
    public BoardDto saveBoard(@RequestBody Board board) {
        return boardService.saveBoard(board);
    }
}
