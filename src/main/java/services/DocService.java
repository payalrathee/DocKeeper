package services;

import dao.DocDao;

public class DocService {
	
	private DocDao docDao;
	
	public DocService(DocDao docDao) {
		this.docDao = docDao;
	}
}
