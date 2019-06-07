package daos;

import models.Speaking;

public interface SpeakingDAO {
	public Speaking getSpeakingByID(String id);
	public void addNewSpeaking(Speaking speaking);
	public void editSpeakingInfor(Speaking speaking);
}
