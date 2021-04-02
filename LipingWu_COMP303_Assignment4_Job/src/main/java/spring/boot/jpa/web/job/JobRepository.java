/*COMP303_Assignment4-Job-Web
 *Liping Wu 300958061
 *4-5-2020
 * */
package spring.boot.jpa.web.job;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends JpaRepository <Job, Integer>{


}
