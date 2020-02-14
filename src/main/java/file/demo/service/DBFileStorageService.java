package file.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import file.demo.exception.FileStorageException;
import file.demo.exception.MyFileNotFoundException;
import file.demo.model.DBFile;
import file.demo.repository.DBFileRepository;

@Service
public class DBFileStorageService {

	private static final Logger logger = LoggerFactory.getLogger(DBFileStorageService.class); 
	
	@Autowired
	private DBFileRepository dbFileRepository;

	public DBFile storeFile(MultipartFile file) {

		logger.info("storing the file");
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			if (fileName.contains("..")) {
				logger.info("contains special characters");

				throw new FileStorageException("Sorry File contains invalid path sequence " + fileName);
			}

			logger.info("saving the file");

			DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());
			return dbFileRepository.save(dbFile);

		} catch (Exception e) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", e);
		}
	}

	public DBFile getFile(String fileID) {

		return dbFileRepository.findById(fileID)
				.orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileID));

	}

}
