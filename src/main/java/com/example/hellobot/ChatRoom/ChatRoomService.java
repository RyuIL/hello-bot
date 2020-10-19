package com.example.hellobot.chatRoom;

import com.example.hellobot.chatRoom.chatMessage.ChatMessage;
import com.example.hellobot.chatRoom.chatMessage.ChatMessageDto;
import com.example.hellobot.chatRoom.chatMessage.ChatMessageRepository;
import com.example.hellobot.common.exception.DataNotFoundException;
import com.example.hellobot.common.exception.DuplicateChatRoomException;
import com.example.hellobot.question.Question;
import com.example.hellobot.question.QuestionDto;
import com.example.hellobot.question.QuestionRepository;
import com.example.hellobot.question.questionBody.QuestionBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author Ryu
 * @create 2020/10/19 5:47 오후
 */

@Service
@Transactional
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final QuestionRepository questionRepository;

    public ChatRoomDto.Res getById(long id){
        return this.chatRoomRepository.findById(id).orElseThrow(DataNotFoundException::new).toDto();
    }

    public ChatRoomDto.Res insertChatRoom(ChatRoomDto.CreationReq req){
        if(this.chatRoomRepository.findByAccountIdAndBotId(req.getAccountId(), req.getBotId()).isPresent()){
            throw new DuplicateChatRoomException("account : " + req.getAccountId()+ " : bot : "+req.getBotId());
        }
        ChatRoom chatRoom = req.toEntity();
        chatRoom = this.chatRoomRepository.save(chatRoom);
        return chatRoom.toDto();
    }

    public Optional<ChatRoomDto.Res> getByAccountIdAndBotId(long accountId, long botId) {
        return this.chatRoomRepository.findByAccountIdAndBotId(accountId, botId).map(ChatRoom::toDto);
    }

    public QuestionDto.Res sendMessage(ChatMessageDto.CreationReq req){
        ChatMessage chatMessage = chatMessageRepository.save(req.toEntity());
        Question question = questionRepository.findById(req.getPickQuestionId()).orElseThrow(DataNotFoundException::new);
        List<ChatMessage> chatMessages = question.getQuestionBodies()
                .stream()
                .sorted(Comparator.comparing(QuestionBody::getId))
                .map(questionBody -> questionBody.toChatMessage(req.getSenderId(), req.getChatMessageBody()))
                .collect(Collectors.toList());
        this.chatMessageRepository.saveAll(chatMessages);
        return question.toDto();
    }
}
