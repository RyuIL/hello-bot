package com.example.hellobot.contents;

import com.example.hellobot.common.dataType.ActiveDto;
import com.example.hellobot.common.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author Ryu
 * @create 2020/10/18 4:05 오후
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ContentsService {
    private final ContentsRepository contentsRepository;

    public ContentsDto.Res upsertContents(ContentsDto.CreationReq req) {
        if(req.getId()!=0 && req.getId() != null){
            Contents contents = this.contentsRepository.findById(req.getId()).orElseThrow(DataNotFoundException::new);
            contents.updateByDto(req);
            return contents.toDto();
        }else {
            return this.contentsRepository.save(req.toEntity()).toDto();
        }
    }

    public ContentsDto.Res updateActive(long id, ActiveDto active){
        Contents contents = this.contentsRepository.findById(id).orElseThrow(DataNotFoundException::new);
        contents.updateActive(active.getActive());
        this.contentsRepository.save(contents);

        return contents.toDto();
    }

    public List<ContentsDto.Res> getAll(){
        return this.contentsRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Contents::getId))
                .map(Contents::toDto)
                .collect(Collectors.toList());
    }

    public Optional<ContentsDto.Res> getById(long id){
        return this.contentsRepository.findById(id).map(Contents::toDto);
    }
}
