package umc.server.baeksstreetmapserver.store.exception;

import javax.persistence.EntityNotFoundException;

public class StoreNotFoundException extends EntityNotFoundException {

	public StoreNotFoundException(String message) {
		super(message);
	}

}
