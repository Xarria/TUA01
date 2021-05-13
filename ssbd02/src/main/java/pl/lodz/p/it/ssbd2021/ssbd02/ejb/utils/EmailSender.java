package pl.lodz.p.it.ssbd2021.ssbd02.ejb.utils;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import pl.lodz.p.it.ssbd2021.ssbd02.ejb.utils.interfaces.EmailSenderLocal;

import javax.ejb.Stateless;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Klasa implementująca mechanizm wysyłania wiadomości email.
 *
 * @author Karolina Kowalczyk
 */
@Stateless
public class EmailSender implements EmailSenderLocal {

    private static final Properties prop = new Properties();

    /**
     * Metoda pomocnicza, która zwraca dwuliterowy string w zależności jaki język posiada konto.
     *
     * @param accountLanguage Język przypisany do konta, na który ma zostać wysłany e-mail.
     * @return String "pl" jeśli do konta jest przypisany język polski, w przeciwnym wypadku zwraca "en".
     */
    private static String getMessageLanguage(String accountLanguage) {
        if (accountLanguage.toLowerCase().contains("pl")) {
            return "pl";
        } else {
            return "en";
        }
    }

    public void sendEmailChangeConfirmationEmail(String language, String recipientName, String recipientEmailAddress, String link) {
        try (InputStream input = EmailSender.class.getClassLoader().getResourceAsStream("mail.properties")) {

            prop.load(input);

            String messageLanguage = getMessageLanguage(language);
            String htmlText = prop.getProperty("mail.template.with.button")
                    .replace("TITLE", prop.getProperty("mail.email.change.title." + messageLanguage))
                    .replace("TEXT", prop.getProperty("mail.email.change.text." + messageLanguage))
                    .replace("LINK", prop.getProperty("mail.email.change.url") + link)
                    .replace("BUTTON_CAPTION", prop.getProperty("mail.email.change.button.text." + messageLanguage));
            String subject = prop.getProperty("mail.email.change.subject." + messageLanguage);
            sendEmail(recipientName, recipientEmailAddress, subject, htmlText);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void sendRegistrationEmail(String language, String recipientName, String recipientEmailAddress, String link) {
        try (InputStream input = EmailSender.class.getClassLoader().getResourceAsStream("mail.properties")) {

            prop.load(input);

            String messageLanguage = getMessageLanguage(language);
            String htmlText = prop.getProperty("mail.template.with.button")
                    .replace("TITLE", prop.getProperty("mail.registration.title." + messageLanguage))
                    .replace("TEXT", prop.getProperty("mail.registration.text." + messageLanguage))
                    .replace("LINK", prop.getProperty("mail.registration.url") + link)
                    .replace("BUTTON_CAPTION", prop.getProperty("mail.registration.button.text." + messageLanguage));
            String subject = prop.getProperty("mail.registration.subject." + messageLanguage);
            sendEmail(recipientName, recipientEmailAddress, subject, htmlText);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void sendChangedActivityEmail(String language, String recipientName, String recipientEmailAddress, boolean active) {
        try (InputStream input = EmailSender.class.getClassLoader().getResourceAsStream("mail.properties")) {

            prop.load(input);

            String htmlText;

            String messageLanguage = getMessageLanguage(language);
            if (active) {
                htmlText = prop.getProperty("mail.template")
                        .replace("TITLE", prop.getProperty("mail.activity.title." + messageLanguage))
                        .replace("TEXT", prop.getProperty("mail.activity.text." + messageLanguage)
                                .replace("CURRENT_ACTIVITY", prop.getProperty("mail.activity.active." + messageLanguage)));
            } else {
                htmlText = prop.getProperty("mail.template")
                        .replace("TITLE", prop.getProperty("mail.activity.title." + messageLanguage))
                        .replace("TEXT", prop.getProperty("mail.activity.text." + messageLanguage)
                                .replace("CURRENT_ACTIVITY", prop.getProperty("mail.activity.blocked." + messageLanguage)));
            }
            String subject = prop.getProperty("mail.activity.subject." + messageLanguage);
            sendEmail(recipientName, recipientEmailAddress, subject, htmlText);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void sendModificationEmail(String language, String recipientName, String recipientEmailAddress) {
        try (InputStream input = EmailSender.class.getClassLoader().getResourceAsStream("mail.properties")) {

            prop.load(input);

            String messageLanguage = getMessageLanguage(language);
            String htmlText = prop.getProperty("mail.template")
                    .replace("TITLE", prop.getProperty("mail.info.modification.title." + messageLanguage))
                    .replace("TEXT", prop.getProperty("mail.info.modification.text." + messageLanguage));
            String subject = prop.getProperty("mail.info.modification.subject." + messageLanguage);
            sendEmail(recipientName, recipientEmailAddress, subject, htmlText);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void sendAddAccessLevelEmail(String language, String recipientName, String recipientEmailAddress, String accessLevel) {
        try (InputStream input = EmailSender.class.getClassLoader().getResourceAsStream("mail.properties")) {

            prop.load(input);

            String messageLanguage = getMessageLanguage(language);
            String htmlText = prop.getProperty("mail.template")
                    .replace("TITLE", prop.getProperty("mail.modification.add.access.level.title." + messageLanguage))
                    .replace("TEXT", prop.getProperty("mail.modification.add.access.level.text." + messageLanguage)
                            .replace("ACCESS_LEVEL", accessLevel));
            String subject = prop.getProperty("mail.info.modification.subject." + messageLanguage);
            sendEmail(recipientName, recipientEmailAddress, subject, htmlText);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void sendRemoveAccessLevelEmail(String language, String recipientName, String recipientEmailAddress, String accessLevel) {
        try (InputStream input = EmailSender.class.getClassLoader().getResourceAsStream("mail.properties")) {

            prop.load(input);

            String messageLanguage = getMessageLanguage(language);
            String htmlText = prop.getProperty("mail.template")
                    .replace("TITLE", prop.getProperty("mail.modification.remove.access.level.title." + messageLanguage))
                    .replace("TEXT", prop.getProperty("mail.modification.remove.access.level.text." + messageLanguage)
                            .replace("ACCESS_LEVEL", accessLevel));
            String subject = prop.getProperty("mail.info.modification.subject." + messageLanguage);
            sendEmail(recipientName, recipientEmailAddress, subject, htmlText);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void sendRemovalEmail(String language, String recipientName, String recipientEmailAddress) {
        try (InputStream input = EmailSender.class.getClassLoader().getResourceAsStream("mail.properties")) {

            prop.load(input);

            String messageLanguage = getMessageLanguage(language);
            String htmlText = prop.getProperty("mail.template")
                    .replace("TITLE", prop.getProperty("mail.info.removal.title." + messageLanguage))
                    .replace("TEXT", prop.getProperty("mail.info.removal.text." + messageLanguage));
            String subject = prop.getProperty("mail.info.removal.subject." + messageLanguage);
            sendEmail(recipientName, recipientEmailAddress, subject, htmlText);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void sendAdminAuthenticationEmail(String language, String firstName, String email, String clientAddress) {
        try (InputStream input = EmailSender.class.getClassLoader().getResourceAsStream("mail.properties")) {

            prop.load(input);
            String date = new SimpleDateFormat("HH:mm dd/MM/yyyy").format(new Date());

            String messageLanguage = getMessageLanguage(language);
            String htmlText = prop.getProperty("mail.template")
                    .replace("TITLE", prop.getProperty("mail.info.admin.auth.title." + messageLanguage))
                    .replace("TEXT", prop.getProperty("mail.info.admin.auth.text." + messageLanguage)
                            .replace("IP_ADDRESS", clientAddress)
                            .replace("TIME", date));
            String subject = prop.getProperty("mail.info.admin.auth.subject." + messageLanguage);

            sendEmail(firstName, email, subject, htmlText);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Metoda wysyłająca wiadomość email.
     *
     * @param recipientName         Imię odbiorcy wiadomości.
     * @param recipientEmailAddress Adres email odbiorcy wiadomości.
     * @param subject               Temat wiadomości.
     * @param text                  Treść wiadomości.
     */
    private void sendEmail(String recipientName, String recipientEmailAddress, String subject, String text) {
        try (InputStream input = EmailSender.class.getClassLoader().getResourceAsStream("mail.properties")) {

            if (prop.getProperty("mail.send.flag").equals("false")) {   // Depending on profile dont send mails
                return;
            }

            prop.load(input);

            String username = prop.getProperty("mail.ferrytales.username");
            String password = prop.getProperty("mail.ferrytales.password");
            String emailAddress = prop.getProperty("mail.ferrytales.address");

            Email email = EmailBuilder.startingBlank()
                    .from("Ferrytales", emailAddress)
                    .to(recipientName, recipientEmailAddress)
                    .withSubject(subject)
                    .withHTMLText(text)
                    .buildEmail();

            Mailer mailer = MailerBuilder
                    .withSMTPServer("in-v3.mailjet.com", 587, username, password)
                    .withTransportStrategy(TransportStrategy.SMTP)
                    .buildMailer();

            mailer.sendMail(email);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
