package net.andrewhatch.languages.jets.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import net.andrewhatch.languages.jets.discovery.ServiceAnnouncerImpl;
import net.andrewhatch.languages.jets.discovery.ServiceAnouncer;
import net.andrewhatch.languages.jets.discovery.ServiceDiscovery;

import java.io.IOException;

import javax.inject.Named;
import javax.jmdns.JmDNS;

public class DiscoveryModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(ServiceDiscovery.class).asEagerSingleton();
    bind(ServiceAnouncer.class).to(ServiceAnnouncerImpl.class).asEagerSingleton();
  }

  @Provides
  public JmDNS jmDNS() throws IOException {
    return JmDNS.create();
  }

  @Provides
  @Named("jets.service.type")
  public String serviceType() {
    return "_http._tcp.local.";
  }

  @Provides
  @Named("jets.service.name")
  public String serviceName() {
    return "jets";
  }
}
