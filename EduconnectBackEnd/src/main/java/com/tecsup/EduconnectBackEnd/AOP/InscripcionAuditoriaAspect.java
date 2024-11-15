package com.tecsup.EduconnectBackEnd.AOP;


import com.tecsup.EduconnectBackEnd.models.AuditoriaInscripcion;

import com.tecsup.EduconnectBackEnd.models.Inscripcion;
import com.tecsup.EduconnectBackEnd.models.daos.AuditoriaInscripcionDao;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class InscripcionAuditoriaAspect {

    @Autowired
    private AuditoriaInscripcionDao auditoriaInscripcionDao;

    // Punto de corte para interceptar los métodos save y delete de InscripcionServiceImpl
    @Pointcut("execution(* com.tecsup.EduconnectBackEnd.services.InscripcionServiceImpl.save(..)) || " +
            "execution(* com.tecsup.EduconnectBackEnd.services.InscripcionServiceImpl.delete(..))")
    public void inscripcionMethods() {}

    // Registrar auditoría después de la ejecución de save o delete
    @AfterReturning(pointcut = "inscripcionMethods()", returning = "result")
    public void registrarAuditoria(JoinPoint joinPoint, Object result) {
        String metodo = joinPoint.getSignature().getName();
        String operacion = metodo.equals("save") ? "CREATE/UPDATE" : "DELETE";

        // Crear una entrada de auditoría
        AuditoriaInscripcion auditoria = new AuditoriaInscripcion();
        auditoria.setEntityName("Inscripcion");
        auditoria.setOperation(operacion);
        auditoria.setTimestamp(new Date());
        auditoria.setUserId(null);  // Puede obtenerse del contexto de seguridad si se requiere
        auditoria.setDetails("ID de Inscripción: " +
                (result != null ? ((Inscripcion) result).getId() : "ID no disponible"));


        // Guardar la auditoría
        auditoriaInscripcionDao.save(auditoria);
    }
}
