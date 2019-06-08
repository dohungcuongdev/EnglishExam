package daos;

import java.util.List;

import models.Reading;
import models.Readinganswer;

public interface ReadingDAO {
	public Reading getReadingById(String id);
	public void addNewReading(Reading reading);
	public List<Readinganswer> getReadingAnswer(int no);
}
