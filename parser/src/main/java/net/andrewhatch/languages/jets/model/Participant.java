package net.andrewhatch.languages.jets.model;

import java.util.Objects;

public class Participant {
  private final String nodeIdentifier;

  public Participant(String nodeIdentifier) {
    this.nodeIdentifier = nodeIdentifier;
  }

  public String getNodeIdentifier() {
    return nodeIdentifier;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj instanceof Participant) {
      final Participant other = (Participant) obj;
      return Objects.equals(this.nodeIdentifier, other.nodeIdentifier);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(nodeIdentifier);
  }
}
