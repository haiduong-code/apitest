**Dùng git bash chạy curl**


**USER**

**NGƯỜI DÙNG ALICE ĐƯỢC ĐĂNG KÍ THÀNH CÔNG**
haidu@ADMIN MINGW64 ~
$ curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"alice","password":"123","role":"USER"}'
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Cu
                                 Dload  Upload   Total   Spent    Left  Sp
100    93    0    42  100    51    227    276 --:--:-- --:--:-- --:--:--  status":"registered","username":"alice"}



**ĐĂNG NHẬP THÀNH CÔNG, TRẢ VỀ TOKEN**
haidu@ADMIN MINGW64 ~
$ curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"alice","password":"123"}'
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Cu
                                 Dload  Upload   Total   Spent    Left  Sp
100   209    0   172  100    37   1362    293 --:--:-- --:--:-- --:--:--  token":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGljZSIsInJvbGVzIjpbIlJPTEVfVVNFUpYXQiOjE3NTkxMjg0NDAsImV4cCI6MTc1OTEzMjA0MH0.tSePvkSFuz0A7sFjhScjJL9XKwdo30fjwxupQ"}



**tạo blog thành công với tài khoản alice**
haidu@ADMIN MINGW64 ~
$ curl -X POST http://localhost:8080/api/blogs \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGljZSIsInJvbbIlJPTEVfVVNFUiJdLCJpYXQiOjE3NTkxMjg0NDAsImV4cCI6MTc1OTEzMjA0MH0.tSePvkSFujhScjJL9XKwdo3xouuom0fjwxupQ" \
  -H "Content-Type: application/json" \
  -d '{"title":"Hello","content":"World"}'
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Cu
                                 Dload  Upload   Total   Spent    Left  Sp
100   101    0    66  100    35   1124    596 --:--:-- --:--:-- --:--:--  id":1,"title":"Hello","content":"World","ownerUsername":"alice"}




**lấy danh sách các blog của mình thành công.**
haidu@ADMIN MINGW64 ~
$ curl -X GET http://localhost:8080/api/blogs/me \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGljZSIsInJvbbIlJPTEVfVVNFUiJdLCJpYXQiOjE3NTkxMjg0NDAsImV4cCI6MTc1OTEzMjA0MH0.tSePvkSFujhScjJL9XKwdo3xouuom0fjwxupQ"
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Cu
                                 Dload  Upload   Total   Spent    Left  Sp
100    68    0    68    0     0   6777      0 --:--:-- --:--:-- --:--:--  "id":1,"title":"Hello","content":"World","ownerUsername":"alice"}]



**cập nhật blog ID 1 thành công**
haidu@ADMIN MINGW64 ~
$ curl -X PUT http://localhost:8080/api/blogs/1 \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGljZSIsInJvbbIlJPTEVfVVNFUiJdLCJpYXQiOjE3NTkxMjg0NDAsImV4cCI6MTc1OTEzMjA0MH0.tSePvkSFujhScjJL9XKwdo3xouuom0fjwxupQ" \
  -H "Content-Type: application/json" \
  -d '{"title":"Updated Title","content":"Updated Content"}'
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Cu
                                 Dload  Upload   Total   Spent    Left  Sp
100   137    0    84  100    53   3281   2070 --:--:-- --:--:-- --:--:--  id":1,"title":"Updated Title","content":"Updated Content","ownerUsername":"}



**blog ID 1 đã được xóa thành công**
haidu@ADMIN MINGW64 ~
$ curl -X DELETE http://localhost:8080/api/blogs/1 \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGljZSIsInJvbbIlJPTEVfVVNFUiJdLCJpYXQiOjE3NTkxMjg0NDAsImV4cCI6MTc1OTEzMjA0MH0.tSePvkSFujhScjJL9XKwdo3xouuom0fjwxupQ"
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Cu
                                 Dload  Upload   Total   Spent    Left  Sp
  0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:--:--


**ADMIN**

 **Xoa User**
haidu@ADMIN MINGW64 ~
$ curl -X DELETE http://localhost:8080/api/users/alice \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGljZSIsInJvbbIlJPTEVfVVNFUiIsIlJPTEVfQURNSU4iXSwiaWF0IjoxNzU5MTMwNTE5LCJleHAiOjE3NTkxM9.OPVHX87rMa4_Nv58UA8bwBNhWMLmnzb1vxHStirXNOo"
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Cu
                                 Dload  Upload   Total   Spent    Left  Sp
  0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:--:--{"status":"deleted","username":"alice"}


