package com.sabor.gourmet.aop;

import com.sabor.gourmet.domain.Bitacora;
import com.sabor.gourmet.repo.BitacoraRepo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class AuditAspect {

    private final BitacoraRepo bitacoraRepo;

    public AuditAspect(BitacoraRepo bitacoraRepo) {
        this.bitacoraRepo = bitacoraRepo;
    }

    // Registrar automáticamente toda acción guardar o actualizar
    @AfterReturning(pointcut = "execution(* com.sabor.gourmet.service.*.guardar(..)) || execution(* com.sabor.gourmet.service.*.actualizar(..))", returning = "resultado")
    public void registrarAccion(JoinPoint joinPoint, Object resultado) {
        String metodo = joinPoint.getSignature().getName();
        String entidad = joinPoint.getTarget().getClass().getSimpleName();
        log.info("🧾 AOP - Acción registrada: {} sobre {}", metodo, entidad);

        Bitacora b = new Bitacora();
        b.setAccion(metodo.toUpperCase());
        b.setEntidad(entidad);
        b.setFechaHora(LocalDateTime.now());
        bitacoraRepo.save(b);
    }
}
