package net.andrewhatch.languages.jets.discovery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

public class ServiceAnnouncerImpl implements ServiceAnouncer {
  private static final Logger logger = LoggerFactory.getLogger(ServiceAnnouncerImpl.class);

  private final JmDNS jmDNS;
  private final String serviceType;
  private final String serviceName;

  @Inject
  public ServiceAnnouncerImpl(final JmDNS jmDNS,
                              @Named("jets.service.type") final String serviceType,
                              @Named("jets.service.name") final String serviceName) {
    this.jmDNS = jmDNS;
    this.serviceType = serviceType;
    this.serviceName = serviceName;
    this.announce();
  }

  @Override
  public void announce() {
    try {
      final ServiceInfo serviceInfo = getServiceInfo();
      logger.trace("Announcing service name:{} type:{}", serviceInfo.getName(), serviceInfo.getType());
      jmDNS.registerService(serviceInfo);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private ServiceInfo getServiceInfo() {
    return ServiceInfo.create(serviceType, serviceName, 9090, 0, 0, "hello");
  }
}
