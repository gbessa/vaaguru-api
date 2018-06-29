package br.com.hoptech.vaaguruapi.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hoptech.vaaguruapi.domain.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer>{

}
