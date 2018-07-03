package br.com.hoptech.vaaguruapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.hoptech.vaaguruapi.domain.Rower;

@Repository
public interface RowerRepository extends JpaRepository<Rower, Integer>{
    
    List<Rower> findByTeams_id(Integer id);
    
}