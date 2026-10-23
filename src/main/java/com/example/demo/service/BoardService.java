package com.example.demo.service;

import com.example.demo.dto.BoardDto;
import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<BoardDto> findAllBoards() {
        List<Board> boards = boardRepository.findAll();
        List<BoardDto> boardDtos = new ArrayList<>();
        for(Board board: boards){
            boardDtos.add(convertToDto(board));
        }
        return  boardDtos;
    }

    public BoardDto saveBoard(Board board) {
        Board savedBoard = boardRepository.save(board);
        return convertToDto(savedBoard);
    }

    private BoardDto convertToDto(Board board) {
        BoardDto boardDto = new BoardDto();
        boardDto.setId(board.getId());
        boardDto.setName(board.getName());
        boardDto.setWorkspaceId(board.getWorkspace().getId());
        return boardDto;
    }
}
