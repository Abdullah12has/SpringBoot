/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dujmovic.confapp.data;

import hr.algebra.dujmovic.confapp.model.Lecture;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author matij
 */
public interface LectureRepository extends CrudRepository<Lecture, Long>{
    List<Lecture> findByTitle(String title);
}
