package com.tecsup.EduconnectBackEnd.models.daos;

import com.tecsup.EduconnectBackEnd.models.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
}


