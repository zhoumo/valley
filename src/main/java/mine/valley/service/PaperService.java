package mine.valley.service;

import java.util.ArrayList;
import java.util.List;

import mine.valley.base.BaseService;
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
		return createQuestionList(text, paper, Question.TYPE_SINGLE_SELECTIONS);
	}

	public List<Question> createMultipleSelectionList(String text, Paper paper) {
		return createQuestionList(text, paper, Question.TYPE_MULTIPLE_SELECTIONS);
	}

	public List<Question> createEssayQuestionList(String text, Paper paper) {
		return createQuestionList(text, paper, Question.TYPE_ESSAY_QUESTIONS);
	}

	public List<Question> createQuestionList(String text, Paper paper, Short type) {
		List<Question> questionList = new ArrayList<Question>();
		JSONObject json = new JSONObject(text);
		for (Object key : json.keySet()) {
			if (key.toString().startsWith("Q")) {
				key = key.toString().substring(1);
				Question question = new Question();
				question.setQuestion(json.getString("Q" + key));
				question.setAnswer(json.getString("A" + key));
				question.setType(type);
				question.setLevel((short) 0);
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

	public Page<Paper> getAllPaper(Page<Paper> page) {
		return baseDao.findByPage("FROM Paper ORDER BY JOB", new Object[] {}, page);
	}

	public Paper getPaper(Long id) {
		List<Paper> paperList = baseDao.find("FROM Paper WHERE ID = ?", id);
		if (paperList.size() > 0) {
			Paper paper = paperList.get(0);
			System.out.println(paper.getQuestions().size());
			return paper;
		}
		return null;
	}
}
