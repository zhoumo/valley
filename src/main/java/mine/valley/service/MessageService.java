package mine.valley.service;

import java.util.List;

import mine.valley.base.BaseService;
import mine.valley.entity.Message;
import mine.valley.entity.User;
import mine.valley.model.Page;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MessageService extends BaseService {

	public void sendMessage(User sender, User receiver, String title, String content) {
		Message message = new Message();
		message.setSender(sender);
		message.setReceiver(receiver);
		message.setTitle(title);
		message.setContent(content);
		message.setCreateTime();
		baseDao.save(message);
	}

	public Page<Message> getNoReadMessage(Page<Message> page, User receiver) {
		return baseDao.findByPage("FROM Message WHERE receiver.id = ? AND isRead = false ORDER BY createTime DESC", new Object[] { receiver.getId() }, page);
	}

	public void readAllMessage(User receiver) {
		List<Message> messageList = baseDao.find("FROM Message WHERE receiver.id = ? AND isRead = false ORDER BY createTime DESC", receiver.getId());
		for (Message message : messageList) {
			message.setIsRead(true);
			baseDao.save(message);
		}
	}
}
