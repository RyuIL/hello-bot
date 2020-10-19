package com.example.hellobot.bot;

import com.example.hellobot.common.dataType.yn.YN;
import com.example.hellobot.common.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Author Ryu
 * @create 2020/10/15 11:33 오후
 */
@Service
@Transactional
@RequiredArgsConstructor
public class BotService {

    private final BotRepository botRepository;

    public BotDto.Res createBot(BotDto.CreationRequest req){
        if(req.getId() != null && req.getId() != 0 && botRepository.existsById(req.getId())){
            Bot bot = botRepository.findById(req.getId()).orElseThrow(DataNotFoundException::new);
            bot.update(req);
            botRepository.save(bot);
            return new BotDto.Res(bot);
        }
        Bot bot = this.botRepository.save(req.toEntity());
        return new BotDto.Res(bot);

    }

    public BotDto.Res updateActive(long id, YN active) {
        Bot bot = this.botRepository.findById(id).orElseThrow(DataNotFoundException::new);
        bot.updateActive(active);
        return new BotDto.Res(this.botRepository.save(bot));
    }


    public BotDto.Res getById(long id){
        Bot bot = this.botRepository.findById(id).orElseThrow(DataNotFoundException::new);
        return new BotDto.Res(bot);
    }
}
