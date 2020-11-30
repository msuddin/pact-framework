package com.thetestroom.provider;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.web.context.ConfigurableWebApplicationContext;

@RunWith(PactRunner.class)
@Provider("test_provider")
@PactFolder("pacts")
public class PactProviderTest {

    @TestTarget
    public final Target target = new HttpTarget(8083);

    @BeforeClass
    public static void start() {
        ConfigurableWebApplicationContext applicationContext =
                (ConfigurableWebApplicationContext) SpringApplication.run(ProviderApplication.class);
    }
}
