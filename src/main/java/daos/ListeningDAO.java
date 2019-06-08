package daos;

import java.util.List;

import models.Listeninganswer;

public interface ListeningDAO {
	public List<Listeninganswer> getListeningAnswer(int no);
}
