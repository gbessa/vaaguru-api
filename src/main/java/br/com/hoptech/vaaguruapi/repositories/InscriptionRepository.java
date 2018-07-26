package br.com.hoptech.vaaguruapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.hoptech.vaaguruapi.domain.Inscription;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Integer>{

    @Transactional(readOnly=true)
    List<Inscription> findBySchedule_id(Integer id);
    
}
