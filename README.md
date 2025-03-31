# Banking Application

Modern bir bankacılık uygulaması. Spring Boot tabanlı bu uygulama, bireysel ve kurumsal müşteriler için çeşitli bankacılık işlemlerini yönetmeyi sağlar.

## 🚀 Özellikler

- 👤 Kullanıcı Yönetimi ve Güvenlik
  - JWT tabanlı kimlik doğrulama
  - Rol tabanlı yetkilendirme (USER, ADMIN)
  - Güvenli şifre yönetimi

- 🏦 Müşteri Yönetimi
  - Bireysel Müşteri İşlemleri
  - Kurumsal Müşteri İşlemleri
  - Müşteri Bilgilerinin Yönetimi

- 💰 Kredi İşlemleri
  - Bireysel Kredi Başvuruları
  - Kurumsal Kredi Başvuruları
  - Kredi Türü Yönetimi
  - Kredi Skorlaması

## 🛠 Teknolojiler

- **Backend**: Spring Boot 3.x
- **Güvenlik**: Spring Security + JWT
- **Veritabanı**: PostgreSQL
- **API Dokümantasyonu**: OpenAPI (Swagger)
- **Build Tool**: Maven
- **Java Sürümü**: 17

## 📦 Proje Yapısı

```
bank-app/
├── bank-app-core/           # Çekirdek modül (güvenlik, ortak bileşenler)
├── bank-app-entities/       # Veritabanı varlıkları
├── bank-app-repositories/   # Repository katmanı
├── bank-app-business/       # İş mantığı katmanı
└── bank-app-webapi/        # Web API katmanı
```

## 🚀 Başlangıç

### Ön Gereksinimler

- JDK 17
- Maven 3.x
- PostgreSQL 12+

### Veritabanı Kurulumu

1. PostgreSQL'de yeni bir veritabanı oluşturun:
```sql
CREATE DATABASE bankapp;
```

2. `application.properties` dosyasındaki veritabanı bağlantı bilgilerini güncelleyin:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bankapp
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### Uygulamayı Çalıştırma

1. Projeyi klonlayın:
```bash
git clone https://github.com/ceydaselamet/bank-app-using-cursor.git
```

2. Projeyi derleyin:
```bash
cd bank-app
mvn clean install
```

3. Uygulamayı başlatın:
```bash
cd bank-app-webapi
mvn spring-boot:run
```

## 📚 API Dokümantasyonu

API dokümantasyonuna aşağıdaki URL'den erişebilirsiniz:
```
http://localhost:8080/api/v1/
```

### Kimlik Doğrulama

1. Yeni kullanıcı kaydı:
```bash
curl -X POST http://localhost:8080/api/v1/auth/register \
-H "Content-Type: application/json" \
-d '{
    "username": "testuser",
    "email": "test@example.com",
    "password": "password123",
    "firstName": "Test",
    "lastName": "User"
}'
```

2. Giriş yapma:
```bash
curl -X POST http://localhost:8080/api/v1/auth/login \
-H "Content-Type: application/json" \
-d '{
    "username": "testuser",
    "password": "password123"
}'
```

## 🔐 Güvenlik

- Tüm şifreler BCrypt ile hashlenir
- JWT token'ları 24 saat geçerlidir
- Hassas endpoint'ler rol tabanlı yetkilendirme gerektirir

## 🤝 Katkıda Bulunma

1. Projeyi fork edin
2. Feature branch oluşturun (`git checkout -b feature/amazing-feature`)
3. Değişikliklerinizi commit edin (`git commit -m 'feat: Add amazing feature'`)
4. Branch'inizi push edin (`git push origin feature/amazing-feature`)
5. Pull Request oluşturun

