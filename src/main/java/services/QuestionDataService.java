package services;

import java.io.FileInputStream;
import com.google.gson.Gson;
import models.QuestionsModel;

public class QuestionDataService {

	public static QuestionsModel getQuestionsModelFor(String subjectName) {

		QuestionsModel questionsModel = null;
		try {
			String basePath = System.getProperty("user.dir");
			String filePath = basePath + "/src/main/java/data/";
			String fileName = "questions_" + subjectName + ".json";
			FileInputStream fileInputStream = new FileInputStream(filePath + fileName);
			byte[] buffer = new byte[fileInputStream.available()];
			fileInputStream.read(buffer);
			fileInputStream.close();

			String json = new String(buffer);
			Gson gson = new Gson();
			questionsModel = gson.fromJson(json, QuestionsModel.class);
		} catch (Exception e) {
			System.out.print(e);
			return null;
		}

		return questionsModel;
	}
}
