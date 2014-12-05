package mine.valley.service;

import java.util.HashMap;

import mine.valley.base.BaseService;
import mine.valley.entity.Exam;
import mine.valley.entity.Paper;
import mine.valley.entity.User;
import mine.valley.thread.ExamDaemon;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExamService extends BaseService {

	private HashMap<String, ExamDaemon> daemons = new HashMap<String, ExamDaemon>();

	public void startExam(Paper paper, User user) {
		Exam exam = new Exam();
		exam.setUser(user);
		exam.setPaper(paper);
		exam.setCreateTime();
		exam.setStatus(Exam.START);
		baseDao.save(exam);
		ExamDaemon daemon = new ExamDaemon(paper.getTime(), exam, this);
		daemon.setDaemon(true);
		daemons.put(paper.getId() + "-" + user.getId(), daemon);
		daemon.start();
	}

	public void endExam(Exam exam) {
		exam.setStatus(Exam.END);
		baseDao.save(exam);
		daemons.remove(exam.getPaper().getId() + "-" + exam.getUser().getId());
	}

	public int getTime(String id) {
		return daemons.get(id).getTime();
	}
}
