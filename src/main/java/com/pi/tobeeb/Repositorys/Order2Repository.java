package com.pi.tobeeb.Repositorys;

import com.pi.tobeeb.Entities.Order2;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Order2Repository extends CrudRepository<Order2, Integer> {
    @Query("SELECT o FROM Order2 o WHERE o.orderItems LIKE %:searchTerm% OR o.Status LIKE %:searchTerm% OR o.totalprice LIKE %:searchTerm%")
    public List<Order2> search(@Param("searchTerm") String searchTerm);
}
