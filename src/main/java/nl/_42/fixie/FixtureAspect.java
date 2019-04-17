package nl._42.fixie;

import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Aspect
@AllArgsConstructor
public class FixtureAspect {

  private final Fixtures fixtures;

  @Around("within(@nl._42.fixie.Fixture *) && execution(* *())")
  public Object before(final ProceedingJoinPoint joinPoint) {
    Object result = null;
    if (joinPoint.getSignature() instanceof MethodSignature) {
      MethodSignature signature = (MethodSignature) joinPoint.getSignature();
      Method method = signature.getMethod();
      result = fixtures.get(method, joinPoint::proceed);
    }
    return result;
  }

}
