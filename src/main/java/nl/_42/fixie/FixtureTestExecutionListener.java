package nl._42.fixie;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

import java.util.Map;

public class FixtureTestExecutionListener implements TestExecutionListener {

    @Override
    public void beforeTestMethod(TestContext testContext) {
        clear(testContext);
    }

    private void clear(TestContext testContext) {
        Map<String, Fixtures> fixtures = testContext.getApplicationContext().getBeansOfType(Fixtures.class);
        fixtures.values().stream().findFirst().ifPresent(Fixtures::clear);
    }

}
