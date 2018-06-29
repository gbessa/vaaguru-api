package br.com.hoptech.vaaguruapi.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hoptech.vaaguruapi.domain.Rower;

@Repository
public interface RowerRepository extends JpaRepository<Rower, Integer>{

}
