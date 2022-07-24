## **HOMEWORK-5**

1. Spring Profile nedir? Properties ya da yml dosya formları ile isbasi uygulamasına test
   profile ekleyin.(5 Puan)
    1. Profil, üretim, geliştirme, test vb. gibi belirli bazı ortamlarda etkinleştirilmesi gereken bazı özellikler ve
       sınıflar kümesidir. Spring Boot, profile özgün özellik dosyaları oluşturarak bunu yapmanızı sağlar.
    2. application-{profile}.properties/yml şeklinde tanımlanır ve ana profilden aktif edilir.
    3. movie servisinde application.yml ve applivation-test.yml ile yapıldı.
2. SQL injection örnekleyin. Nasıl korunabiliriz?(5 Puan)
    1. SQL injection, veri tabanı altyapısına sahip tüm sistemlerde en yaygın görülen siber güvenlik tehdididir. Böyle
       bir altyapıya sahipseniz mutlaka gerekli önlemleri almalısınız.
    2. Web uygulamasının yaptığı SQL sorgusuna müdahale edilerek veri tabanında bulunan verilere yetkisiz erişilmesine
       sql injection denir.
    3. Kullanıcı adı ve şifre ile oturum açması sağlanan bir uygulama giriş sayfamızın olduğunu varsaalım. Burada bir
       kullanıcının “ufuk” kullanıcı adı ile birlikte “ufuk1997” şifresini kullanarak uygulamaya giriş yaptığını
       varsayın. Bu durumda uygulama aşağıdaki sorguyu sorup, doğru cevap alırsa kullanıcıya giriş izni verir.Bir
       hacker, bu sorgudaki parola sorgusunu kaldırarak giriş yapmayı dener. Bu şekilde veri tabanına sızma girişiminde
       bulunur. Oturum açma ekranındaki POST isteğini aşağıdaki sorguyla düzenleyip gönderirse veri tabanına yönetici
       rolüyle erişir.
    4. SQL injection nasıl engellenir sorusunun cevabı:
        1. Bilgi teknolojileri altyapınızda fiziksel cihazlarınkiler dâhil tüm yazılımların güncel olmasına dikkat edin.
        2. Web uygulamanızdaki tüm formlarda ben robot değilim doğrulamasını sağlayın.
        3. Uygulama kullanıcılarına çift faktörlü giriş yapmalarını zorlayın.
        4. Web uygulaması üzerinde gerçekleşen şüpheli sorguları ve istekleri analiz edebilen web tabanlı bir firewall (
           WAF)
           yazılımını devreye alın.
        5. Uygulamanızın arka planda sorduğu soruları gözden geçirin. Özellikle NULL karakterlerin yer aldığı sorguları
           tekrar
           düzenleyin.
        6. Sisteminizdeki kullanıcıları gözden geçirin. Yönetici ve tam yetkili rolündeki kullanıcı adlarında
           ‘administrator’,
           ‘admin’, ‘yönetici’ gibi akla gelebilecek kullanıcıları devre dışı bırakın. Ayrıca yetkili rolündeki
           kullanıcı adlarının
           gerçek isim veya uygulama ismi olmasına dikkat edin.
        7. Uygulamanın hızlı olması için kullanılan sorgu dizesi birleştirme yöntemi her zaman iyi fikir olmayabilir.
           Mümkün
           mertebe bu dize birleştirmelerden uzak durun veya bu sorguyu yetkiye bağlayın.
        8. IT altyapınız üzerinde yapılan tüm hareketleri merkezi log toplama veri tabanında toplayın. Şüpheli işlemler
           için anlık
           alarm tanımlayın ve yetkili kullanıcıya e-posta gönderimi sağlayın.


3. Aşağıdaki kurallara göre bir film öneri uygulaması yazın. (90 Puan)

### **Teknolojiler;**

* Min Java8+
* Spring Boot+
* Restfull+
* MySQL(payment servisi) - Postgre(movie servisi) - Mongo(email servisi)
* RabbitMQ(email servisinde kullanıldı)

### **Gereksinimler;**
*  KULLANILAN POSTMAN SORGULARI POSTMAN_COLLECTIN_IMPORT.json dosyasından import edilebilir.
* Kullanıcı sisteme kayıt olup, login olabilmelidir.(Login işlemi için email ve şifre bilgileri
gereklidir.)++
* Kullanıcı şifresi istediğiniz bir hashing algoritmasıyla database kaydedilmelidir.+
* Kullanıcılar sisteme film ekleyebilir ve bu filmler herkes tarafından görülebilir.+
* Kullanıcı kendi eklediği filmleri görebilmeli.(Profil sayfası gibi düşünün)+
* Kullanıcı şifresini ve ismini değiştirebilir.+
* Ücretli üye olmayan kullanıcılar sadece 3 film ekleyebilir.+
* Ücretli üye olmayan kullanıcılar filmlere yorum yapamaz.+
* Sisteme yeni bir film girildiğinde kullanıcılara email gider.++
* Sistemi takip edebilmek için gerekli gördüğünüz yerlere Log ekleyin.+

### **Sistem Kabulleri;**

* Ödeme işlemi senkron gerçekleşmelidir.(Feign Client kullanıldı)
* Ödeme servisi sadece ödeme bilgilerini kaydeder ve başarılı response döner.+
* Email gönderme işlemi asenkron gerçekleşmelidir.(Rabbitmq ile yapıldı.)
* Üyelikler 1-3-6-12 ay olarak alınabilir.++
