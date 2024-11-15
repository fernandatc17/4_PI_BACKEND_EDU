package com.tecsup.EduconnectBackEnd.AOP;

import com.tecsup.EduconnectBackEnd.models.Auditoria;
import com.tecsup.EduconnectBackEnd.models.daos.AuditoriaDao;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class AuditoriaAspect {

    @Autowired
    private AuditoriaDao auditoriaRepository; // Repositorio para la entidad Auditoria

    // Define el punto de corte para los métodos save y delete en EventoServiceImpl
    @Pointcut("execution(* com.tecsup.EduconnectBackEnd.services.EventoServiceImpl.save(..)) || " +
            "execution(* com.tecsup.EduconnectBackEnd.services.EventoServiceImpl.delete(..))")
    public void eventoMethods() {}

    // Método que se ejecutará después de save o delete
    @AfterReturning(pointcut = "eventoMethods()", returning = "result")
    public void registrarAuditoria(JoinPoint joinPoint, Object result) {
        // Obtener el tipo de operación basada en el método interceptado
        String metodo = joinPoint.getSignature().getName();
        String operacion = metodo.equals("save") ? "CREATE/UPDATE" : "DELETE";

        // Crear la entrada de auditoría
        Auditoria auditoria = new Auditoria();
        auditoria.setEntityName("Evento"); // Nombre de la entidad afectada
        auditoria.setOperation(operacion); // Tipo de operación realizada
        auditoria.setTimestamp(new Date()); // Fecha y hora de la operación
        auditoria.setUserId(null); // De momento se deja en null ya que el usuario no se obtiene del contexto de seguridad
        auditoria.setDetails("Operación realizada en la entidad Evento con ID: " +
                (result != null ? result.toString() : "ID no disponible")); // Detalles del registro de auditoría

        // Guardar la entrada de auditoría en la base de datos
        auditoriaRepository.save(auditoria);
    }
}

