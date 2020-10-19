package com.example.hellobot.contents;

import com.example.hellobot.common.dataType.ActiveDto;
import com.example.hellobot.common.exception.DataNotFoundException;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Ryu
 * @create 2020/10/18 4:06 오후
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/contents")
public class ContentsController {

    private final ContentsService contentsService;

    @PutMapping("/{id}")
    ContentsDto.Res upsertContents(
            @RequestBody @ApiParam("생성 req") ContentsDto.CreationReq req
    ) {
        return contentsService.upsertContents(req);
    }

    @PutMapping("/{id}/active")
    ContentsDto.Res updateActive(
            @PathVariable("id") @ApiParam("id") Long id,
            @RequestBody @ApiParam("활성 여부 dto") ActiveDto activeDto
    ){
        return this.contentsService.updateActive(id, activeDto);
    }

    @GetMapping("/all")
    List<ContentsDto.Res> getAll(){
        return this.contentsService.getAll();
    }

    @GetMapping("/{id}")
    ContentsDto.Res getById(
            @PathVariable("id") @ApiParam("id") Long id
    ){
        return this.contentsService.getById(id).orElseThrow(DataNotFoundException::new);
    }
}
