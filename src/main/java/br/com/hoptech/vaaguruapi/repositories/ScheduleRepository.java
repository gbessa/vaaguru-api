package br.com.hoptech.vaaguruapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.hoptech.vaaguruapi.domain.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer>{

}
