package br.com.hoptech.vaaguruapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.hoptech.vaaguruapi.domain.Schedule;
import br.com.hoptech.vaaguruapi.domain.Team;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer>{

    @Transactional(readOnly=true)
    List<Schedule> findAllByTeamIn(List<Team> teamList);
    
}
