package mine.valley.thread;

import mine.valley.entity.Exam;
import mine.valley.service.ExamService;

public class ExamDaemon extends Thread {

	private int time;

	private Exam exam;

	private ExamService examService;

	public ExamDaemon(int time, Exam exam, ExamService examService) {
		this.time = time;
		this.exam = exam;
		this.examService = examService;
	}

	public int getTime() {
		return time;
	}

	@Override
	public void run() {
		while (time-- != 0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		examService.endExam(exam);
	}
}
