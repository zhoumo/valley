package mine.valley.service;

import java.util.ArrayList;
import java.util.List;

import mine.valley.base.BaseService;
import mine.valley.entity.Paper;
import mine.valley.entity.Question;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaperService extends BaseService {

	public List<Question> convertQuestionList(String text, Short type, Paper paper) {
		List<Question> questionList = new ArrayList<Question>();
		JSONObject json = new JSONObject(text);
		for (Object key : json.keySet()) {
			if (key.toString().startsWith("Q")) {
				key = key.toString().substring(1);
				Question question = new Question();
				question.setQuestionContent(json.getString("Q" + key));
				question.setQuestionAnswer(json.getString("A" + key));
				question.setQuestionType(type);
				question.setQuestionLevel((short) 0);
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
}
