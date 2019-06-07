package daos;

import models.Reading;

public interface ReadingDAO {
	public Reading getReadingById(String id);
	public void addNewReading(Reading reading);
}
