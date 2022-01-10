package mobile.repo;

import mobile.model.Message;
import org.springframework.data.repository.CrudRepository;



public interface Messagerepo extends CrudRepository<Message, Long> {
    Message findMessagesByIdMessage(Long idMessage);
}
