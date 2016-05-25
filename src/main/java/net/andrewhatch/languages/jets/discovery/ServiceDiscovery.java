package net.andrewhatch.languages.jets.discovery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;

public class ServiceDiscovery implements ServiceListener {
  private static final Logger logger = LoggerFactory.getLogger(ServiceDiscovery.class);

  @Inject
  public ServiceDiscovery(final JmDNS jmDNS,
                          @Named("jets.service.type") final String serviceType) {
    jmDNS.addServiceListener(serviceType, this);
  }

  @Override
  public void serviceAdded(final ServiceEvent serviceEvent) {
    logger.trace("serviceAdded() - {}", serviceEvent.getName());
  }

  @Override
  public void serviceRemoved(final ServiceEvent serviceEvent) {
    logger.trace("serviceRemoved() - {}", serviceEvent.getName());
  }

  @Override
  public void serviceResolved(final ServiceEvent serviceEvent) {
    logger.trace("serviceResolved() - {}@{}", serviceEvent.getName(), serviceEvent.getInfo().getInetAddresses()[0]);
  }
}
