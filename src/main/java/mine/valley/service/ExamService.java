package mine.valley.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import mine.valley.base.BaseService;
import mine.valley.constant.ExamStatus;
import mine.valley.entity.Exam;
import mine.valley.entity.Paper;
import mine.valley.entity.User;
import mine.valley.thread.ExamDaemon;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExamService extends BaseService {

	private Map<String, ExamDaemon> daemonMap = new ConcurrentHashMap<String, ExamDaemon>();

	public Long startExam(Paper paper, User user) {
		Exam exam = new Exam();
		exam.setUser(user);
		exam.setPaper(paper);
		exam.setCreateTime();
		exam.setStatus(ExamStatus.START.getValue());
		baseDao.save(exam);
		ExamDaemon daemon = new ExamDaemon(paper.getTime(), exam, this);
		daemon.setDaemon(true);
		daemonMap.put(paper.getId() + "-" + user.getId(), daemon);
		daemon.start();
		return exam.getId();
	}

	public void endExam(Exam exam) {
		exam.setStatus(ExamStatus.END.getValue());
		baseDao.save(exam);
		daemonMap.remove(exam.getPaper().getId() + "-" + exam.getUser().getId());
	}

	public int getTime(String id) {
		return daemonMap.get(id).getTime();
	}
}
