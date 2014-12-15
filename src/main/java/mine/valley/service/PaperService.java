package mine.valley.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mine.valley.base.BaseService;
import mine.valley.constant.ApplyType;
import mine.valley.constant.ExamStatus;
import mine.valley.constant.PaperStatus;
import mine.valley.constant.QuestionType;
import mine.valley.entity.Exam;
import mine.valley.entity.Paper;
import mine.valley.entity.Question;
import mine.valley.model.Page;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaperService extends BaseService {

	public List<Question> createSingleSelectionList(String text, Paper paper) {
		return createQuestionList(text, paper, QuestionType.SINGLE_SELECTIONS.getValue());
	}

	public List<Question> createMultipleSelectionList(String text, Paper paper) {
		return createQuestionList(text, paper, QuestionType.MULTIPLE_SELECTIONS.getValue());
	}

	public List<Question> createEssayQuestionList(String text, Paper paper) {
		return createQuestionList(text, paper, QuestionType.ESSAY_QUESTIONS.getValue());
	}

	public List<Question> createQuestionList(String text, Paper paper, Short type) {
		List<Question> questionList = new ArrayList<Question>();
		JSONObject json = new JSONObject(text);
		for (Object key : json.keySet()) {
			if (key.toString().startsWith("Q")) {
				key = key.toString().substring(1);
				Question question = new Question();
				if (key.toString().length() < 13) {
					question.setId(Long.parseLong(key.toString()));
				}
				question.setQuestion(json.getString("Q" + key));
				question.setAnswer(json.getString("A" + key));
				question.setDifficulty((short) json.getInt("D" + key));
				question.setType(type);
				question.setPaper(paper);
				question.setCreateTime();
				questionList.add(question);
			}
		}
		return questionList;
	}

	public void savePaper(Paper paper) {
		baseDao.save(paper);
	}

	public void deletePaper(Paper paper) {
		baseDao.delete(paper);
	}

	public Page<Paper> getSubmitPaper(Page<Paper> page) {
		return baseDao.findByPage("FROM Paper WHERE status >= ? ORDER BY status, job", new Object[] { PaperStatus.SUBMIT.getValue() }, page);
	}

	public Page<Paper> getPaperByAuthor(Page<Paper> page, Long userId) {
		return baseDao.findByPage("FROM Paper WHERE author.id = ? ORDER BY status, job", new Object[] { userId }, page);
	}

	public Page<Paper> getNoAuditPaper(Page<Paper> page, Long userId) {
		String search = "FROM Paper WHERE status = ? AND job.id IN (SELECT job.id FROM Apply WHERE user.id = ? AND type = ? AND approved = TRUE) ORDER BY status, job";
		return baseDao.findByPage(search, new Object[] { PaperStatus.SUBMIT.getValue(), userId, ApplyType.AUDITOR.getValue() }, page);
	}

	public Page<Paper> getExamPaper(Page<Paper> page, Long userId) {
		List<Exam> examList = baseDao.find("FROM Exam WHERE user.id = ?", new Object[] { userId });
		Map<Long, Exam> examMap = new HashMap<Long, Exam>();
		for (Exam exam : examList) {
			examMap.put(exam.getPaper().getId(), exam);
		}
		page = baseDao.findByPage("FROM Paper WHERE status = ? ORDER BY job", new Object[] { PaperStatus.AUDIT_YES.getValue() }, page);
		List<Paper> finishedPaper = new ArrayList<Paper>();
		for (Paper paper : page.getResult()) {
			Exam exam = examMap.get(paper.getId());
			if (exam == null) {
				continue;
			}
			Short status = exam.getStatus();
			if (status.equals(ExamStatus.END.getValue())) {
				finishedPaper.add(paper);
			} else {
				paper.setExamStatus(status);
				paper.setExamId(exam.getId());
			}
		}
		page.getResult().removeAll(finishedPaper);
		return page;
	}

	public Paper getPaper(Long id) {
		List<Paper> paperList = baseDao.find("FROM Paper WHERE ID = ?", id);
		if (paperList.size() > 0) {
			return paperList.get(0);
		}
		return null;
	}
}
