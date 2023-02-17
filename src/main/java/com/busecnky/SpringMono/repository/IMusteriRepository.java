package com.busecnky.SpringMono.repository;


import com.busecnky.SpringMono.repository.entity.Musteri;
import com.busecnky.SpringMono.repository.view.ViewMusteri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

   /**
    * Spring BeanFactory ile oluşturacağı nesneleri belirlemek için belirli anotasyonları arar.
    * Bunlardan birincisi Veritabanı işlemleri için
    * @Repository dir.
    */

@Repository
public interface IMusteriRepository extends JpaRepository<Musteri,Long> {

   /**
    * !! DIKKAT !!
    * Spring in devraldığı repository interface lerinde, method tanımlamaları icin özel
    * Keyword ler kullanılmaktadır. bunlar Üzerinden sorgular olusturulur.
    * 1- ReturnType-> Musteri, List<Musteri>, Boolean, Integer v.s.
    * 2- find, kelimesini kullanıyoruz.
    * 3- By, ne icin arama yapılacağının belirlenmesi isaretlenmesi icin kullanılır.
    * 4- Entity Property[entity-> değiskenin adı], üzerinde çalıştığınız repository nin kullanmakta
    * olduğu entity sınıfından bir değişkenin birebir adını yazmalısınız.
    * ÇOOOOOOK DiKKAT!!!! burada varlık adı yazılırken büyük harf ile baslanır. yine dikkat
    * edilmesi gereken bir husus eğer değişken adı camelcase şeklinde (musteriAdSoyad) şeklinde
    * yazılmış ise buna dikkat ederek yazılmalıdır.
    * 5-Method için gerekli parametreler değişken türüne göre eklenirç
    * !!!DİKKATT!!! burada değişken adı önemli değil, değişken türü önemlidir.
    * select * from tblmusteri where ad=?
    *
    */


    List<Musteri> findByAd(String burayabirdegeryazmamgerekli);

    /**
     * select*from tblmusteri where ad=? and adres=?
     * @param bisey
     * @param baskabisey
     * @return
     */

    List<Musteri> findAllByAdAndAdres(String bisey,String baskabisey);

    /**
     * Hangi yaş grubunun hangi ürünleri daha fazla satın aldığını merak ediyorsunuz.
     * örn: 40 yaş üzeri müşterilerin listesi
     */
    List<Musteri> findAllByYasGreaterThan(Integer yas);   //yas>?

    List<Musteri> findAllByYasGreaterThanEqual(Integer yas);   //yas>=?

    /**
     * Belli bir harfin ya da kelimenin aranması LIKE,ILIKE
     * select*from tblmusteri ad like '?%'
     */

    List<Musteri> findAllByAdLike(String ad);

    /**
     * select*from tblmusteri where ad=? and adres=? and telefon=?
     * @param ad
     * @param adres
     * @param tel
     * @return
     */
    Musteri findByAdAndAdresAndTelefon(String ad,String adres, String tel);

    /**
     * select * from tblmusteri where yas=5000
     * Eğer sonuç yok ise, null dönebilir böyle durumlarda null kontrolü yapmak çokta
     * doğru değildir. Bunun yerine Optional kullanmak doğru olacaktır.
     */

    Optional<Musteri> findOptionalByTelefon(String telefon); //Eğer null ise empty

    Optional<List<Musteri>> findAllOptionalByAdres(String adres);

    /**
     * Order ->
     * -> ASC A...Z, 0...9
     * select * from tblmusteri order by yas
     * -> DESC Z...A, 9...0
     * select * from tblmusteri order by desc
     *
     * En yaşlı musteri kim?
     * select * from tblmusteri order by yas desc limit 1
     */

    List<Musteri> findByOrderByYas(); // yaşına göre küçükten büyüğe doğru sıralı liste verir.
    List<Musteri> findByOrderByYasDesc();

    Musteri findTopByOrderByYasDesc(); //En yaşlı müşteri

    /**
     * LIMIT Kullanımı
     */

    List<Musteri> findTop5ByOrderByYas(); // En genç 5 üyemiz

    Musteri findTopOptionalByOrderByYasDesc(); //En yaşlı müşteri optional olarak


    /**
     * select * from tblmusteri where yas>=18 and yas<=40 -- gençlerde satış oranı
     * between de başlangıç ve bitişleri dahil eder
     */

    List<Musteri> findAllByYasBetween(Integer start, Integer end); // yas>=? and yas<=? [baş,bit]

    List<Musteri> findAllByAdresAndYasBetween(String adres, Integer start, Integer end);

    /**
     * true - false
     */

    List<Musteri> findAllByState(boolean state); // aktif pasif kayıtları verir
    List<Musteri> findAllByStateTrue(); // true- aktif kayıtları verir
    List<Musteri> findAllByStateFalse(); //  pasif kayıtları verir


    /**
     * Ahmet -> Tbl("ahmet") false
     */
    //List<Musteri> findAllByAdAndAdres(String ad, String adres);
    //Böyle yazınca büyük küçük harfe takılıyo. Takılmasın diye aşağıdaki metotu yazıyoruz.

    List<Musteri> findAllByAdIgnoreCaseAndAdresIgnoreCase(String ad, String adres);

    /**
     * Belli isimlerin listesini çekelim
     * List<String> adListesi = {"Ahmet", "Ayşe", "Canan"}
     */

    List<Musteri> findAllByAdIn(List<String> adListesi);

    /**
     * JPQL ya da HQL veya NATIVESQL kullanılabilir.
     */
   @Query("select m from Musteri m where m.adres=?1")  //Buraya neden 1 yazdık. Birden fazla parametre istendiğinde
   //Onları numaralandırmak için
    List<Musteri> senBulTumunuMusterilerinAdresineGore(String adresOlabilir);

   //Verilen numaralandırma gelen parametrelerin sırasına göre olmalıdır. Burda örnek o yüzden verildi.
  //Ama normalde tabi sırasıyla yazılması daha doğru ve az karmaşık olur.
    @Query("select m from Musteri m where m.ad =?3 and m.adres =?1 and m.telefon =?2")
    Musteri musteriyiBul(String adres, String telefon, String ad);

    /**
     *  NATIVESQL Kullanımı
     *  Çalışan bir SQL sorgun var diyelim o sorguyu jpql e ya da hql e çevirmektense
     *  çat diye buraya kopyala yapıştır yapıp native olarak kullanmak daha mantıklı olabilir.
     *  O zamanlar yapıştırırız ve =? kısmını ve numaralandırmayı hallederiz
     */


    /**JPQL vs NATIVESQL farkı
     * JPQL de @Param("x") şeklinde yazdığımız için x ve y gibi değerler yazdıklarımızla eşleşeceği için
     * parametreleri yazma sıramız önemli değil.
     *
     * NATIVESQL de 1 ve 2 yazdığımız için parametreleri yazma sıralaması önemli
     *
     * JPQL de select c from Class as c where c.cName.cDetail gibi farklı classları birbirinden çekebiliyoruz.
     * Bize ileride rahatlık sağlar.
     *
     * NATIVESQL de classları çekemeyiz onun yerine join table yapmamız gerekir.
     */
    @Query(value = "select * from Musteri where ad =?1" , nativeQuery = true)
    List<Musteri> bulAdinaGore(String ad);

    /**
     * Sorgu parametlerinin kullanılarak tanımlanması ve method içinden çekilmesi
     */
    @Query("select m from Musteri m where m.ad =:ad and m.adres =:adres")
    List<Musteri> findAdandAdres(
            @Param("ad") String musteriadi,
            @Param("adres") String musteriadresi);
    @Query("select COUNT (m)>0 from Musteri m where m.tcKimlik=?1")
    Boolean musteriVarmi(String tckimlik);

    /**
     * ViewMusteri diye bi sınıf oluşturduk ama repository diyo ki ben bunu tanımıyorum.
     * Ben Musteri yi tanıyorum burdan gelen verileri sorguya çeviremiyorum.
     * O yüzden buna da sorgu yazmamız gerekmektedir.
     */
    @Query("select new com.busecnky.SpringMono.repository.view.ViewMusteri(m.id,m.ad) from Musteri m")
    List<ViewMusteri> findAllViewMusteri();






   }