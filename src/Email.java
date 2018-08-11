import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class Email {
    public static void main(String[] args) throws FileNotFoundException, IOException, AddressException, MessagingException{
        //есть два варика: настроить свой сервак, который будет отправлять письма, либо воспользоваться, например, сервером gmail
        // если мы будем пользовать gmail, то нужно создать файл .properties, в котором указать проперти gmail.
        final Properties properties = new Properties();
        properties.load(new FileInputStream("mail.properties"));
//        System.out.println(properties.getProperty("mail.smtps.auth"));


        // необходимо подгрузить jar-файл, т.к класс Session не найден. Искать его через поиск: java mail-> java mail api - oracle ->
        // downloads -> javamail project page on github -> javax.mail.jar -> скачиваем
        // project structure -> sdks-> справа плюс и добавляем jar-файл

        // для того, чтобы прога запустилась, нужно снизить уровень блокировки у gmail. Делается это через письмо, которое приходит на почту
        // после этого все будет работать.
        Session mailSession = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(mailSession);
        // указать свой e-mail
        message.setFrom(new InternetAddress("myemail@gmail.com"));
        // указать, кому отправлять письмо
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("nadegdavladyka@mail.ru"));
        // тема письма
        message.setSubject("message #1");
        // текст письма
        message.setText("This mail from java");

        Transport tr = mailSession.getTransport();
        tr.connect(null,"#####");
        tr.sendMessage(message,message.getAllRecipients());
        tr.close();
    }
}
