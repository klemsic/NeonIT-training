package cz.neonit.klemsa.training.domain.msisdn;

import java.util.Objects;

import static java.lang.Integer.*;

/**
 * Representation of phone number in MSISDN format.
 * @author tomasklemsa
 */
public class MSISDN {
    private final int cc;
    private final int ndc;
    private final int sn;

    /**
     *
     * @param cc Country Code
     * @param ndc National Destination Code
     * @param sn Subscriber Number
     */
    public MSISDN(int cc, int ndc, int sn) {
        this.cc = cc;
        this.ndc = ndc;
        this.sn = sn;
    }

    /**
     *
     * @param MSISDN String representation of MSISDN
     * @return new MSISDN
     * @throws NumberFormatException if the String is not parsable to MSISDN
     */
    public static MSISDN valueOf(String MSISDN) throws NumberFormatException {
        Objects.requireNonNull(MSISDN);
        int cc,ndc,sn;
        try {
            cc = parseInt(MSISDN.substring(0, MSISDN.length() - 9));
            ndc = parseInt(MSISDN.substring(MSISDN.length() - 9, MSISDN.length() - 6));
            sn = parseInt(MSISDN.substring(MSISDN.length() - 6, MSISDN.length()));
        } catch (Exception e) {
            throw new NumberFormatException("Invalid MSISDN number format.");
        }
        return new MSISDN(cc, ndc, sn);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return String.valueOf(cc) + String.valueOf(ndc) + String.valueOf(sn);
    }

    /**
     *
     * @return Country Code
     */
    public int getCc() {
        return cc;
    }

    /**
     *
     * @return National Destination Code
     */
    public int getNdc() {
        return ndc;
    }

    /**
     *
     * @return Subscriber Number
     */
    public int getSn() {
        return sn;
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
        MSISDN msisdn = (MSISDN) o;
        return cc == msisdn.cc &&
                ndc == msisdn.ndc &&
                sn == msisdn.sn;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(cc, ndc, sn);
    }
}
