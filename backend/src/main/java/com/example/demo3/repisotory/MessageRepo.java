package com.example.demo3.repisotory;

import com.example.demo3.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Long>  {
     Message findMessagesByIdMessage(Long idMessage);

}









