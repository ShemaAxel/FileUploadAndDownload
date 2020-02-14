package file.demo.repository;

import org.springframework.data.repository.CrudRepository;

import file.demo.model.DBFile;

public interface DBFileRepository extends CrudRepository<DBFile, String> {

}
