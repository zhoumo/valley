package mine.valley.thread;

import mine.valley.service.ExamService;

public class ExamDaemon extends Thread {

	private int time;

	private Long examId;

	private ExamService examService;

	public ExamDaemon(int time, Long examId, ExamService examService) {
		this.time = time;
		this.examId = examId;
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
		examService.endExam(examId);
	}
}
