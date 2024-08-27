package services;

import java.util.List;

import beans.Doc;
import dao.DocDao;

public class DocService {
	
	private DocDao docDao;
	
	public DocService(DocDao docDao) {
		this.docDao = docDao;
	}
	
	public Doc getDoc(int id) throws Exception {
		
		Doc doc = docDao.getDoc(id);
		if(doc == null) {
			throw new Exception("DocNotFound");
		}
		
		return doc;
	}
	
	public List<Doc> getAllDocs(int userId) {
		return docDao.getAllDocs(userId);
	}
	
	public int addDoc(Doc doc) throws Exception {
		
		int id = docDao.insertDoc(doc);
		if(id == -1) {
			throw new Exception("DocNotInserted");
		}
		return id;
	}
	
	public boolean updateDoc(Doc doc) throws Exception {
		
		boolean updated = docDao.updateDoc(doc);
		if(!updated) {
			throw new Exception("DocNotUpdated");
		}
		return updated;
	}
	
	public boolean deleteDoc(int id) throws Exception {
		
		boolean deleted = docDao.deleteDoc(id);
		if(!deleted) {
			throw new Exception("DocNotDeleted");
		}
		return deleted;
	}
}
