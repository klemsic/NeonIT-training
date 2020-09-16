package cz.neonit.klemsa.training.domain.communication;

import java.util.Objects;

/**
 * Representation of direction between country code of origin and destination.
 * @author tomasklemsa
 */
public final class CommunicationCountryDirection {
    public final Integer originCc;
    public final Integer destinationCc;

    /**
     *
     * @param originCc
     * @param destinationCc
     */
    public CommunicationCountryDirection(Integer originCc, Integer destinationCc) {
        this.originCc = originCc;
        this.destinationCc = destinationCc;
    }

    /**
     *
     * @return
     */
    public Integer getOriginCc() {
        return originCc;
    }

    /**
     *
     * @return
     */
    public Integer getDestinationCc() {
        return destinationCc;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommunicationCountryDirection that = (CommunicationCountryDirection) o;
        return originCc == that.originCc &&
                destinationCc == that.destinationCc;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(originCc, destinationCc);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return originCc == null ? "" : originCc.toString()
                + "-" +
                destinationCc == null ? "" : destinationCc.toString();
    }
}
