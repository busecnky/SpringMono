package com.busecnky.SpringMono.repository;

import com.busecnky.SpringMono.repository.entity.Urun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUrunRepository extends JpaRepository<Urun,Long> {


}
