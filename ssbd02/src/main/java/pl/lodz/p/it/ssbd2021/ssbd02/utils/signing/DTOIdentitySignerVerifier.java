package pl.lodz.p.it.ssbd2021.ssbd02.utils.signing;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.lodz.p.it.ssbd2021.ssbd02.exceptions.CommonExceptions;
import pl.lodz.p.it.ssbd2021.ssbd02.utils.security.SecurityConstants;

import java.text.ParseException;

import static com.nimbusds.jose.JWSAlgorithm.HS512;

/**
 * Klasa implementująca mechanizm podpisywania pól.
 *
 * @author Karolina Kowalczyk
 */
public class DTOIdentitySignerVerifier {

    private static final Logger logger = LogManager.getLogger();

    /**
     * Metoda generująca podpis.
     *
     * @param signableDTO Obiekt klasy implementującej interfejs SignableDTO.
     * @return Wygenerowany podpis.
     * W przypadku podania nieprawdiłowego wystąpienia błędu podczas generowania podpisu zwraca null.
     */
    public static String calculateDTOSignature(SignableDTO signableDTO) {
        try {
            JWSSigner signerJWS = new MACSigner(SecurityConstants.SECRET);
            JWSObject objectJWS = new JWSObject(new JWSHeader(HS512), new Payload(String.valueOf(signableDTO.getSignablePayload())));
            objectJWS.sign(signerJWS);

            return objectJWS.serialize();

        } catch (JOSEException ex) {
            logger.error(ex);
            throw CommonExceptions.createUnknownException();
        }
    }

    /**
     * Metoda sprawdzająca poprawność przekazaneego podpisu.
     *
     * @param header Podpis do sprawdzenia
     * @return True, jeśli podpis jest poprawny, w przeciwnym wypadku false.
     */
    public static boolean validateDTOSignature(String header) {

        try {
            JWSObject objectJWS = JWSObject.parse(header);
            JWSVerifier verifier = new MACVerifier(SecurityConstants.SECRET);

            return objectJWS.verify(verifier);

        } catch (JOSEException | ParseException ex) {
            logger.warn(ex);
        }
        return false;
    }

    /**
     * Metoda sprawdzająca integralność podpisanego obiektu.
     *
     * @param signableDTO Obiekt klasy implementującej interfejs SignableDTO.
     * @param header      Nagłówek zawierający podpis.
     * @return True, jeśli podpis jest poprawny i wartość podpisanego pola nie uległa zmianie, w przeciwnym wypadku false.
     */
    public static boolean verifyDTOIntegrity(String header, SignableDTO signableDTO) {
        try {
            final String ifMatchHeaderValue = JWSObject.parse(header).getPayload().toString();
            final String entitySignablePayloadValue = signableDTO.getSignablePayload().toString();

            return validateDTOSignature(header) && ifMatchHeaderValue.equals(entitySignablePayloadValue);
        } catch (ParseException ex) {
            logger.warn(ex);
        }
        return false;
    }

}
