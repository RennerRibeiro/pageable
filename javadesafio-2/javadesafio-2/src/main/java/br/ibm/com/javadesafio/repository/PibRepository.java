package br.ibm.com.javadesafio.repository;


import br.ibm.com.javadesafio.entity.PibEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PibRepository extends JpaRepository<PibEntity, Long> {


    List<PibEntity> findByData(Date data);

    List<PibEntity> findByDataBetween(Date startDate, Date endDate);

    
            @Query("select e from PibEntity e where year(e.data) = ?1")
    List<PibEntity> findByYear(@Param ("year") Integer year);

    @Query(
            value = "SELECT * FROM tb_divida WHERE valor = :valor",
            nativeQuery = true)
    List<PibEntity> findByValor2(Double valor);

    List<PibEntity> findByValor(Double valor);

}	 

