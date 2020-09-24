package cz.neonit.klemsa.training.domain.communication;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;

/**
 * Representation of direction between country code of origin and destination.
 * @author tomasklemsa
 */
public final class CommunicationCountryDirection {
    public Integer originCc;
    public Integer destinationCc;

    /**
     *
     * @param originCc
     * @param destinationCc
     */
    @JsonCreator
    public CommunicationCountryDirection(@JsonProperty("originCc") Integer originCc,
                                         @JsonProperty("destinationCc") Integer destinationCc) {
        this.originCc = originCc;
        this.destinationCc = destinationCc;
    }

    /**
     *
     * @return
     */
    @JsonGetter
    public Integer getOriginCc() {
        return originCc;
    }

    /**
     *
     * @return
     */
    @JsonGetter
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
        return Objects.equals(originCc, that.originCc) &&
                Objects.equals(destinationCc, that.destinationCc);
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(originCc, destinationCc);
    }

    @JsonValue
    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        return (originCc == null ? "" : originCc)
                + "-"
                + (destinationCc == null ? "" : destinationCc);
    }

    /**
     *
     * @param s
     * @return
     */
    @JsonCreator
    public static CommunicationCountryDirection valueOf(String s) {
        Objects.requireNonNull(s);
        Integer originCc = null;
        Integer destinationCc = null;

        if (s.contains("-")) {
            String[] values = s.split("-");
            if (values.length == 1) {
                originCc = values[0].equals("") ? null : Integer.valueOf(values[0]);
            } else if (values.length == 2) {
                originCc = values[0].equals("") ? null : Integer.valueOf(values[0]);
                destinationCc = values[1].equals("") ? null : Integer.valueOf(values[1]);
            } else {
                throw new NumberFormatException("Illegal format of CommunicationCountryDirection: " + s);
            }
        } else {
            throw new NumberFormatException("Illegal format of CommunicationCountryDirection: " + s);
        }
        return new CommunicationCountryDirection(originCc,destinationCc);
    }


}
