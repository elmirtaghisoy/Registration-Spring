package com.elmirtaghisoy.socmed.controller;

import com.elmirtaghisoy.socmed.domain.Message;
import com.elmirtaghisoy.socmed.domain.Views;
import com.elmirtaghisoy.socmed.repo.MessageRepo;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {

    @Autowired
    private MessageRepo messageRepo;

    //  List metodu ile biz butun mesajlarimizi elde edirik
    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Message> list() {
        return messageRepo.findAll();
    }

    //  Biz bilirik ki spring birbasha olaraq idye gore obyekt qebul ede bilir.
    //  Ancaq ki burada urlden idni gotrumeyimizi path variableye bildirmeli olacagiq.
    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Message getOne(@PathVariable("id") Message message) {
        return message;
    }

    @PostMapping
    public Message create(@RequestBody Message message) {
        message.setCreationDate(LocalDateTime.now());
        return messageRepo.save(message);
    }

    @PutMapping("{id}")
    public Message update(
            @PathVariable("id") Message messageFromDb,
            @RequestBody Message message
    ) {
        //Bu metodla biz istifadeciden aldigimiz obyekti dbdeki obyekte kopyalayiriq id xaric
        BeanUtils.copyProperties(message, messageFromDb, "id");
        return messageRepo.save(messageFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message) {
        messageRepo.delete(message);
    }
}
