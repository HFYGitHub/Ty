# 鍦ㄧ嚎涓存椂鍓创鏉垮悗绔?
鏈」鐩湪鐜版湁澶氭ā鍧楀伐绋嬬殑 `admin` 妯″潡涓疄鐜颁竴涓畝鍗曠殑鍦ㄧ嚎涓存椂鍓创鏉?REST API锛岀敤浜庝繚瀛樸€佹煡璇㈠拰鍒犻櫎鐢ㄦ埛涓诲姩杈撳叆鐨勬枃鏈唴瀹广€?
鍓嶇鍙互浣跨敤浣犲崟鐙殑 Vue 椤圭洰璁块棶杩欎簺鎺ュ彛锛屾湰浠撳簱涓嶅寘鍚墠绔唬鐮併€?
## 鎶€鏈爤

- Java 8锛堟部鐢ㄥ綋鍓嶅伐绋嬮厤缃級
- Spring Boot 2.6.13锛堟部鐢ㄥ綋鍓嶅伐绋嬮厤缃級
- Spring Web
- MyBatis-Plus
- MySQL
- Maven

## 鏁版嵁搴撻厤缃?
褰撳墠椤圭洰娌跨敤 `admin` 妯″潡宸叉湁鏁版嵁搴撻厤缃紝榛樿杩炴帴 `ty` 鏁版嵁搴擄細

```yaml
spring:
  datasource:
    url: jdbc:mysql://127.0.0.1:3306/ty?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
    username: ty
    password: ty4766
```

鎸夊疄闄?MySQL 璐﹀彿淇敼 `username` 鍜?`password`銆?
## 寤鸿〃 SQL

MyBatis-Plus 涓嶄細鑷姩寤鸿〃锛岃鍦ㄥ綋鍓?admin 杩炴帴鐨勬暟鎹簱涓墜鍔ㄦ墽琛岋細

```sql
CREATE TABLE clip_record (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  content TEXT NOT NULL,
  create_time DATETIME NOT NULL
);
```

如果表已经创建过，执行下面 SQL 修改字段类型：

```sql
ALTER TABLE clip_record
MODIFY COLUMN content TEXT NOT NULL;
```

濡傛灉浣犳兂鍗曠嫭浣跨敤 `clip_db`锛屽厛鍒涘缓鏁版嵁搴擄細

```sql
CREATE DATABASE clip_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

鐒跺悗鎶?`admin/src/main/resources/application.yml` 閲岀殑鏁版嵁搴撳悕浠?`ty` 鏀逛负 `clip_db`锛屽啀鎵ц涓婇潰鐨勫缓琛?SQL銆?
## 鍚姩椤圭洰

鍦ㄩ」鐩牴鐩綍鎵ц锛?
```bash
mvn -pl common -am install -DskipTests
mvn -f admin/pom.xml spring-boot:run
```

褰撳墠椤圭洰淇濈暀鍘熸湁绔彛閰嶇疆锛宍admin` 妯″潡绔彛涓?`10522`銆?
## 鎵撳寘椤圭洰

```bash
mvn clean package
```

鍙墦鍖?`admin` 妯″潡锛?
```bash
mvn -pl admin -am clean package
```

## 杩愯 jar

```bash
java -jar admin/target/admin-0.0.1-SNAPSHOT.jar
```

## 鎺ュ彛璇存槑

鎺ュ彛鍓嶇紑锛歚/api/records`

### 鏂板鍐呭

`POST /api/records`

璇锋眰浣擄細

```json
{
  "content": "娴嬭瘯鍐呭"
}
```

瑙勫垯锛?
- `content` 涓嶈兘涓?`null`
- `content` 鍘婚櫎棣栧熬绌烘牸鍚庝笉鑳戒负绌?- 淇濆瓨鏃朵細鍘婚櫎棣栧熬绌烘牸
- 绌哄唴瀹硅繑鍥?HTTP 400

鎴愬姛鍝嶅簲锛?
```json
{
  "id": 1,
  "content": "娴嬭瘯鍐呭",
  "createTime": "2026-06-07T12:00:00"
}
```

### 鏌ヨ鍒楄〃

`GET /api/records`

鎸?`createTime` 鍊掑簭杩斿洖锛屾渶鏂板唴瀹瑰湪鏈€涓婃柟銆?
### 鍒犻櫎鍐呭

`DELETE /api/records/{id}`

濡傛灉 `id` 瀛樺湪鍒欏垹闄わ紱濡傛灉涓嶅瓨鍦ㄤ篃鐩存帴杩斿洖 HTTP 204銆?
## curl 娴嬭瘯鍛戒护

浠ヤ笅鍛戒护浣跨敤褰撳墠 `admin` 妯″潡绔彛 `10522`銆?
### 娣诲姞鍐呭

```bash
curl -X POST http://localhost:10522/api/records \
  -H "Content-Type: application/json" \
  -d "{\"content\":\"娴嬭瘯鍐呭\"}"
```

### 鏌ヨ鍒楄〃

```bash
curl http://localhost:10522/api/records
```

### 鍒犻櫎鍐呭

```bash
curl -X DELETE http://localhost:10522/api/records/1
```

### 绌哄唴瀹规牎楠?
```bash
curl -X POST http://localhost:10522/api/records \
  -H "Content-Type: application/json" \
  -d "{\"content\":\"   \"}"
```

杩斿洖锛?
```json
{
  "message": "鍐呭涓嶈兘涓虹┖"
}
```
