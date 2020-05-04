package com.elmirtaghisoy.socmed.repo;

import com.elmirtaghisoy.socmed.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Long> {
    
}
