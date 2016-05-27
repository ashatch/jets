package net.andrewhatch.languages.jets.discovery.services;

import net.andrewhatch.languages.jets.model.Participant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;

public class ServiceDiscovery implements ServiceListener {
  private static final Logger logger = LoggerFactory.getLogger(ServiceDiscovery.class);
  private final Participant self;
  private Map<String, Participant> map = new HashMap<>();

  @Inject
  public ServiceDiscovery(final JmDNS jmDNS,
                          @Named("jets.service.type") final String serviceType,
                          @Named("jets.self") final Participant self) {
    this.self = self;
    jmDNS.addServiceListener(serviceType, this);
  }

  @Override
  public void serviceAdded(final ServiceEvent serviceEvent) {}

  @Override
  public void serviceRemoved(final ServiceEvent serviceEvent) {
    logger.trace("serviceRemoved() - {}", serviceEvent.getName());
  }

  @Override
  public void serviceResolved(final ServiceEvent serviceEvent) {

    final Participant discoveredParticipant = serviceEventToParticipant(serviceEvent);

    if (!map.containsKey(discoveredParticipant.getNodeIdentifier()) &&
        !discoveredParticipant.equals(this.self)) {
      logger.trace("serviceResolved() - {}@{}",
          serviceEvent.getName(),
          serviceEvent.getInfo().getInetAddresses()[0]);
      map.put(discoveredParticipant.getNodeIdentifier(), discoveredParticipant);
    }
  }

  private Participant serviceEventToParticipant(ServiceEvent serviceEvent) {
    return new Participant(serviceEvent.getName());
  }
}
