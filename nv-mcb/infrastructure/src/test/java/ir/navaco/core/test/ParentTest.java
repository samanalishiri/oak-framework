package ir.navaco.core.test;

import ir.navaco.core.config.init.ContextBean;
import ir.navaco.core.test.config.init.SpringDataJpaConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TestTransaction;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        ContextBean.class,
        SpringDataJpaConfiguration.class})
public class ParentTest extends AbstractTransactionalJUnit4SpringContextTests {

    @BeforeClass
    public static void init() {
    }

    @Before
    public void setUp() {
        beforeMethod();
    }

    @After
    public void tearDown() {
        afterMethod();
    }

    public void beforeMethod() {

    }

    public void afterMethod() {

    }

    public void doInTransaction(Runnable runnable) {
        runnable.run();
        commitAndStartNewTransaction();
    }

    protected void commitAndStartNewTransaction() {
        TestTransaction.flagForCommit();
        TestTransaction.end();
        TestTransaction.start();
    }

    protected void rollbackAndStartNewTransaction() {
        TestTransaction.flagForRollback();
        TestTransaction.end();
        TestTransaction.start();
    }
}
