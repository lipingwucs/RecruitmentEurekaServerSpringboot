/*COMP303_Assignment4-Org-Web
 *Liping Wu 300958061
 *4-6-2020
 * */
package com.spring.boot.jpa.web;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository <Category, Integer> {

}
